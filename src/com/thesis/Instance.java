package com.thesis;

import com.thesis.resources.Resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Instance")
public class Instance {
//    MetaData metaData;
//    Structure structure;
//    String objective;
    Resources resources;

    public Resources getResources() {
        return this.resources;
    }

    @XmlElement(name = "Resources")
    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
