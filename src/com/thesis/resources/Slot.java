package com.thesis.resources;

import javax.xml.bind.annotation.*;

@XmlType( propOrder = { "id", "name"} )
@XmlRootElement(name = "slot")
public class Slot {
    int id;
    String name;

    public int getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }
}
