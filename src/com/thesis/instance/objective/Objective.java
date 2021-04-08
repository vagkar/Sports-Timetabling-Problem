package com.thesis.instance.objective;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ObjectiveFunction")
public class Objective {

    private String objective;

    public String getObjective() {
        return objective;
    }

    @XmlElement(name = "Objective")
    public void setObjective(String objective) {
        this.objective = objective;
    }
}
