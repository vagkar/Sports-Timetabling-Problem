package com.thesis.instance.structure;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Format")
public class Format {

    private String leagueId;
    private int numberRoundRobin;
    private String compactness;
    private String gameMode;

    public String getGameMode() {
        return gameMode;
    }

    @XmlElement(name = "gameMode")
    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getCompactness() {
        return compactness;
    }

    @XmlElement(name = "compactness")
    public void setCompactness(String compactness) {
        this.compactness = compactness;
    }

    public int getNumberRoundRobin() {
        return numberRoundRobin;
    }

    @XmlElement(name = "numberRoundRobin")
    public void setNumberRoundRobin(int numberRoundRobin) {
        this.numberRoundRobin = numberRoundRobin;
    }

    public String getLeagueId() {
        return leagueId;
    }

    @XmlAttribute(name = "leagueIds")
    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }
}
