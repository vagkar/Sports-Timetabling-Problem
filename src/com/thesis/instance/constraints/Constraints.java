package com.thesis.instance.constraints;

import com.thesis.instance.constraints.capacity.*;
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
}