package com.slasher.redneuronal.repository;

import com.slasher.redneuronal.entity.NeuronalArtificial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeuronalArtificialRepository extends CrudRepository<NeuronalArtificial, Integer> {
}
