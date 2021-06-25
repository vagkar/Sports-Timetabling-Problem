package com.thesis.instance.constraints;

import com.thesis.instance.constraints.br.BR1;
import com.thesis.instance.constraints.br.BR2;
import com.thesis.instance.constraints.br.BreakConstraints;
import com.thesis.instance.constraints.capacity.*;
import com.thesis.instance.constraints.fairness.FA2;
import com.thesis.instance.constraints.game.GA1;
import com.thesis.instance.constraints.separation.SE1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Constraints")
public class Constraints {

    private List<SE1> se1Constraints;
    private CapacityConstraints capacityConstraints;
    private BreakConstraints breakConstraints;
    private List<FA2> fa2Constraints;
    private List<GA1> ga1Constraints;

    @XmlElementWrapper(name = "SeparationConstraints")
    @XmlElement(name = "SE1")
    public void setSE1(List<SE1> separationConstraints) {
        this.se1Constraints = separationConstraints;
    }

    public List<SE1> getSE1() {
        if (se1Constraints == null)
            se1Constraints = new ArrayList<SE1>();
        return this.se1Constraints;
    }

    @XmlElement(name = "CapacityConstraints")
    public void setCapacityConstraints(CapacityConstraints capacityConstraints) {
        this.capacityConstraints = capacityConstraints;
    }

    public List<CA1> getCA1() {
        return this.capacityConstraints.getCa1Constraints();
    }

    public List<CA2> getCA2() {
        return this.capacityConstraints.getCa2Constraints();
    }

    public List<CA3> getCA3() {
        return this.capacityConstraints.getCa3Constraints();
    }

    public List<CA4> getCA4() {
        return this.capacityConstraints.getCa4Constraints();
    }

    @XmlElement(name = "BreakConstraints")
    public void setBreakConstraints(BreakConstraints breakConstraints) {
        this.breakConstraints = breakConstraints;
    }

    public List<BR1> getBR1() {
        return this.breakConstraints.getBr1Constraints();
    }

    public List<BR2> getBR2() {
        return this.breakConstraints.getBr2Constraints();
    }

    public List<FA2> getFa2Constraints() {
        if (fa2Constraints == null)
            fa2Constraints = new ArrayList<>();
        return fa2Constraints;
    }

    @XmlElementWrapper(name = "FairnessConstraints")
    @XmlElement(name = "FA2")
    public void setFa2Constraints(List<FA2> fa2Constraints) {
        this.fa2Constraints = fa2Constraints;
    }

    public List<GA1> getGa1Constraints() {
        if (ga1Constraints == null)
            ga1Constraints = new ArrayList<>();
        return ga1Constraints;
    }

    @XmlElementWrapper(name = "GameConstraints")
    @XmlElement(name = "GA1")
    public void setGa1Constraints(List<GA1> ga1Constraints) {
        this.ga1Constraints = ga1Constraints;
    }
}