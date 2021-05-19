package com.slasher.redneuronal.service;

import com.slasher.redneuronal.entity.LogicalGate;

import java.util.List;

public interface LogicalGateService {
    boolean createLogicalGate(LogicalGate logicalGate);
    boolean removeLogicalGateById(int idLogicalGate);
    LogicalGate getLogicalGateById(int idLogicalGate);
    List<LogicalGate> getAllLogicalGates();
}
