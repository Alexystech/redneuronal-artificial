package com.slasher.redneuronal.service;

import com.slasher.redneuronal.entity.NeuronalArtificial;

import java.util.List;

public interface NeuronalArtificialService {
    boolean createNeuronalArtificial(NeuronalArtificial neuronalArtificial);
    boolean removeNeuronalArtificialById(int idNeuronalArtificial);
    NeuronalArtificial getNeuronalArtificialById(int idNeuronalArtificial);
    List<NeuronalArtificial> getAllNeuronalArtificials();
}
