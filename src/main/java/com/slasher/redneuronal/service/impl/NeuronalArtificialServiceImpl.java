package com.slasher.redneuronal.service.impl;

import com.slasher.redneuronal.entity.NeuronalArtificial;
import com.slasher.redneuronal.repository.NeuronalArtificialRepository;
import com.slasher.redneuronal.service.NeuronalArtificialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NeuronalArtificialServiceImpl implements NeuronalArtificialService {

    private NeuronalArtificialRepository neuronalArtificialRepository;

    @Autowired
    public NeuronalArtificialServiceImpl(NeuronalArtificialRepository neuronalArtificialRepository) {
        this.neuronalArtificialRepository = neuronalArtificialRepository;
    }

    @Override
    public boolean createNeuronalArtificial(NeuronalArtificial neuronalArtificial) {
        if (neuronalArtificial != null) {
            neuronalArtificialRepository.save(neuronalArtificial);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeNeuronalArtificialById(int idNeuronalArtificial) {
        if (idNeuronalArtificial > 0) {
            neuronalArtificialRepository.deleteById(idNeuronalArtificial);
            return true;
        }
        return false;
    }

    @Override
    public NeuronalArtificial getNeuronalArtificialById(int idNeuronalArtificial) {
        return neuronalArtificialRepository.findById(idNeuronalArtificial).get();
    }

    @Override
    public List<NeuronalArtificial> getAllNeuronalArtificials() {
        return (List<NeuronalArtificial>) neuronalArtificialRepository.findAll();
    }
}
