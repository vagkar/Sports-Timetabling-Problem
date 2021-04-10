package com.thesis.solution.metadata;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ObjectiveValue")
public class ObjectiveValue {

    private int infeasibility;
    private int objective;

    public ObjectiveValue() {

    }
//
//    public ObjectiveValue(int infeasibility, int objective) {
//        setInfeasibility(infeasibility);
//        setObjective(objective);
//    }

    @XmlAttribute(name = "infeasibility")
    public int getInfeasibility() {
        return infeasibility;
    }

    public void setInfeasibility(int infeasibility) {
        this.infeasibility = infeasibility;
    }

    @XmlAttribute(name = "objective")
    public int getObjective() {
        return objective;
    }

    public void setObjective(int objective) {
        this.objective = objective;
    }
}
