package com.thesis.constraints;

import com.thesis.constraints.separation.SE1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Constraints")
public class Constraints {

    List<SE1> se1Constraints;

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
}
