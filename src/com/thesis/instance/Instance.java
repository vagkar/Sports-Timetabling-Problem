package com.thesis.instance;

import com.thesis.instance.constraints.Constraints;
import com.thesis.instance.metadata.MetaData;
import com.thesis.instance.objective.Objective;
import com.thesis.instance.resources.Resources;
import com.thesis.instance.structure.Structure;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Instance")
public class Instance {
    private MetaData metaData;
    private Objective objective;
    private Structure structure;
    private Constraints constraints;
    private Resources resources;

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

    public Structure getStructure() {
        return structure;
    }

    @XmlElement(name = "Structure")
    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    @XmlElement(name = "MetaData")
    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Objective getObjectiveFunction() {
        return objective;
    }

    @XmlElement(name = "ObjectiveFunction")
    public void setObjectiveFunction(Objective objective) {
        this.objective = objective;
    }
}
