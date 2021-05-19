package com.slasher.redneuronal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "logicalgate")
public class LogicalGate {

    @Id
    @Column(name = "id_logicalgate")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLogicalGate;

    @Column(length = 40,nullable = false)
    private String name;

    @OneToOne
    private NeuronalArtificial neuronalArtificial;

    public LogicalGate(String name) {
        this.name = name;
    }

    public LogicalGate() {}

    public Integer getIdLogicalGate() {
        return idLogicalGate;
    }

    public void setIdLogicalGate(Integer idLogicalGate) {
        this.idLogicalGate = idLogicalGate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
