package com.slasher.redneuronal.service.impl;

import com.slasher.redneuronal.entity.LogicalGate;
import com.slasher.redneuronal.repository.LogicalGateRepository;
import com.slasher.redneuronal.service.LogicalGateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogicalGateServiceImpl implements LogicalGateService {

    private LogicalGateRepository logicalGateRepository;

    @Autowired
    public LogicalGateServiceImpl(LogicalGateRepository logicalGateRepository) {
        this.logicalGateRepository = logicalGateRepository;
    }

    @Override
    public boolean createLogicalGate(LogicalGate logicalGate) {
        if (logicalGate != null) {
            logicalGateRepository.save(logicalGate);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeLogicalGateById(int idLogicalGate) {
        if (idLogicalGate > 0) {
            logicalGateRepository.deleteById(idLogicalGate);
            return true;
        }
        return false;
    }

    @Override
    public LogicalGate getLogicalGateById(int idLogicalGate) {
        return logicalGateRepository.findById(idLogicalGate).get();
    }

    @Override
    public List<LogicalGate> getAllLogicalGates() {
        return (List<LogicalGate>) logicalGateRepository.findAll();
    }
}
