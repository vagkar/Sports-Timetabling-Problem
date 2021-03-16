package com.thesis;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Teams")
public class Teams {

    List<Team> teams;

    @XmlElement(name = "team")
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        if (teams == null)
            teams = new ArrayList<Team>();
        return this.teams;
    }
}
