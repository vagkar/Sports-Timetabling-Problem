package com.thesis.solution.games;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

//@XmlRootElement(name = "Games")
public class Games {
    private List<ScheduledMatch> games = new ArrayList<>();

   // @XmlElement(name = "ScheduledMatch")
    public List<ScheduledMatch> getGames() {
        return games;
    }

    public void setGames(List<ScheduledMatch> games) {
        this.games = games;
    }
}
