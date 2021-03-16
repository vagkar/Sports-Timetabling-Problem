package com.thesis;

import com.thesis.constraints.Constraints;
import com.thesis.resources.Resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Instance")
public class Instance {
//    MetaData metaData;
//    Structure structure;
//    String objective;
    Constraints constraints;
    Resources resources;

    public Resources getResources() {
        return this.resources;
    }

    @XmlElement(name = "Resources")
    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Constraints getConstraints() {
        return constraints;
    }

    @XmlElement(name = "Constraints")
    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }
}
