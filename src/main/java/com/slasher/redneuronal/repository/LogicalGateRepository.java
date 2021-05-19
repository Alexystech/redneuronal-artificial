package com.slasher.redneuronal.repository;

import com.slasher.redneuronal.entity.LogicalGate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogicalGateRepository extends CrudRepository<LogicalGate, Integer> {
}
