package com.thesis.constraints;

import com.thesis.constraints.separation.SeparationConstraints;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Constraints")
public class Constraints {

    List<SeparationConstraints> separationConstraints;

    @XmlElement(name = "SeparationConstraints")
    public void setSeparationConstraints(List<SeparationConstraints> separationConstraints) {
        this.separationConstraints = separationConstraints;
    }

    public List<SeparationConstraints> getSeparationConstraints() {
        return this.separationConstraints;
    }
}
