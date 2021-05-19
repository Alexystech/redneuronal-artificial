package com.slasher.redneuronal.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "neuronal_artificial")
public class NeuronalArtificial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNeuronalArtificial;

    @Column(nullable = false)
    private Float w0;

    @Column(nullable = false)
    private Float w1;

    @Column(nullable = false)
    private Float w2;

    @Column(nullable = false)
    private Float alfa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_logicalgate", referencedColumnName = "id_logicalgate", nullable = false)
    private LogicalGate logicalGate;

    public NeuronalArtificial(Float w0, Float w1, Float w2, Float alfa, LogicalGate logicalGate) {
        this.w0 = w0;
        this.w1 = w1;
        this.w2 = w2;
        this.alfa = alfa;
        this.logicalGate = logicalGate;
    }

    public NeuronalArtificial() {}

    public Float getW0() {
        return w0;
    }

    public void setW0(Float w0) {
        this.w0 = w0;
    }

    public Float getW1() {
        return w1;
    }

    public void setW1(Float w1) {
        this.w1 = w1;
    }

    public Float getW2() {
        return w2;
    }

    public void setW2(Float w2) {
        this.w2 = w2;
    }

    public Float getAlfa() {
        return alfa;
    }

    public void setAlfa(Float alfa) {
        this.alfa = alfa;
    }

    public LogicalGate getLogicalGate() {
        return logicalGate;
    }

    public void setLogicalGate(LogicalGate logicalGate) {
        this.logicalGate = logicalGate;
    }
}
