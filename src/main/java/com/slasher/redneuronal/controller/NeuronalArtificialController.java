package com.slasher.redneuronal.controller;

import com.slasher.redneuronal.entity.LogicalGate;
import com.slasher.redneuronal.entity.NeuronalArtificial;
import com.slasher.redneuronal.service.LogicalGateService;
import com.slasher.redneuronal.service.NeuronalArtificialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
public class NeuronalArtificialController {

    private NeuronalArtificial neuronalArtificial;
    private List<LogicalGate> logicalGates = new LinkedList<>();
    private List<Byte[][]> logicalGatesKeys = new LinkedList<>();

    private LogicalGateService logicalGateService;
    private NeuronalArtificialService neuronalArtificialService;

    private int epocas = 0;

    @Autowired
    public NeuronalArtificialController(LogicalGateService logicalGateService, NeuronalArtificialService neuronalArtificialService) {
        this.logicalGateService = logicalGateService;
        this.neuronalArtificialService = neuronalArtificialService;

        Byte[][] AND = {
                {0,0,0},
                {0,1,0},
                {1,0,0},
                {1,1,1}
        };

        Byte[][] OR = {
                {0,0,0},
                {0,1,1},
                {1,0,1},
                {1,1,1}
        };

        Byte[][] NAND = {
                {0,0,1},
                {0,1,1},
                {1,0,1},
                {1,1,0}
        };

        Byte[][] NOR = {
                {0,0,1},
                {0,1,0},
                {1,0,0},
                {1,1,0}
        };

        Byte[][] XNOR = {
                {0,0,1},
                {0,1,0},
                {1,0,0},
                {1,1,1}
        };

        Byte[][] XOR = {
                {0,0,0},
                {0,1,1},
                {1,0,1},
                {1,1,0}
        };

        logicalGatesKeys.add(AND);
        logicalGatesKeys.add(OR);
        logicalGatesKeys.add(NAND);
        logicalGatesKeys.add(NOR);
        logicalGatesKeys.add(XNOR);
        logicalGatesKeys.add(XOR);
    }

    @GetMapping("/index")
    public String getIndex(Model model) {

        neuronalArtificial = new NeuronalArtificial();
        logicalGates = logicalGateService.getAllLogicalGates();

        model.addAttribute("logicalGate", new LogicalGate());
        model.addAttribute("logicalGates", logicalGates);
        model.addAttribute("neuronalArtificial", neuronalArtificial);

        return "index";
    }

    @GetMapping("/result")
    public String getResult(Model model) {

        logicalGates = logicalGateService.getAllLogicalGates();

        model.addAttribute("logicalGate", new LogicalGate());
        model.addAttribute("logicalGates", logicalGates);
        model.addAttribute("neuronalArtificial", neuronalArtificial);

        Map<String, Float>resultData = new HashMap<>();
        Float epocasL = Float.parseFloat(Integer.toString(this.epocas));

        resultData.put("Peso 0", neuronalArtificial.getW0());
        resultData.put("Peso 1", neuronalArtificial.getW1());
        resultData.put("peso 2", neuronalArtificial.getW2());
        resultData.put("Epocas", epocasL);

        model.addAttribute("resultData", resultData);

        return "index";
    }

    @PostMapping("/calculate/result")
    public String postNeuronalArtificial(NeuronalArtificial neuronalArtificial) {
        neuronalArtificialService.createNeuronalArtificial(neuronalArtificial);

        //algoritmo de aprendizaje
        String logicalGateNameSelected = neuronalArtificial.getLogicalGate().getName();

        Stream<LogicalGate> gate = logicalGates.stream()
                .filter(
                        gatex -> gatex.getName().equals(logicalGateNameSelected)
                );

        int selectedIndex = logicalGates.indexOf(gate.findFirst().get());
        Byte[][] logicalGateDataSelected = logicalGatesKeys.get(selectedIndex);

        float weight0 = neuronalArtificial.getW0();
        float weight1 = neuronalArtificial.getW1();
        float weight2 = neuronalArtificial.getW2();

        float alfa = neuronalArtificial.getAlfa();

        boolean aprendiendo = true;
        int salidaEntera = 0;
        int epocas = 0;

        while ( aprendiendo ) {
            epocas += 1;
            aprendiendo = false;

            /*
             * empezamos con el aprendizaje usando los datos de nuestra compuerta logica
             */
            for ( int cont = 0; cont < 4; cont++ ) {
                float salidaReal = (
                        logicalGateDataSelected[cont][0] * weight0 +
                        logicalGateDataSelected[cont][1] * weight1 +
                        1 * weight2
                );

                if ( salidaReal >= 0 )
                    salidaEntera = 1;
                else
                    salidaEntera = 0;

                int error = (int) logicalGateDataSelected[cont][2] - salidaEntera;

                if ( error != 0 ) {
                    weight0 += alfa * error * logicalGateDataSelected[cont][0];
                    weight1 += alfa * error * logicalGateDataSelected[cont][1];
                    weight2 += alfa * error * 1;
                    aprendiendo = true;
                }
            }

            if ( !aprendiendo )
                break;
        }

        this.neuronalArtificial.setW0(weight0);
        this.neuronalArtificial.setW1(weight1);
        this.neuronalArtificial.setW2(weight2);
        this.epocas = epocas;

        return "redirect:/result";
    }
}
