package com.thesis;

import javax.xml.bind.annotation.*;

@XmlType( propOrder = { "id", "league", "name"} )
@XmlRootElement(name = "team")
public class Team {

    int id;
    String league;
    String name;

    public int getId() {
        return this.id;
    }

    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getLeague() {
        return this.league;
    }

    @XmlAttribute(name = "league")
    public void setLeague(String league) {
        this.league = league;
    }

    public String getName() {
        return this.name;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }
}
