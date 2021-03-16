package com.thesis;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Resources")
public class Resources {

    List<Team> teams;
    List<League> league;

    @XmlElementWrapper(name = "Leagues")
    @XmlElement(name = "league")
    public void setLeague(List<League> league) {
        this.league = league;
    }

    public List<League> getLeague() {
        if (league == null)
            league = new ArrayList<League>();
        return this.league;
    }

    @XmlElementWrapper(name = "Teams")
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
