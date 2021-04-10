package com.thesis.solution.games;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ScheduledMatch")
public class ScheduledMatch {
    private int home;
    private int away;
    private int slot;

    public ScheduledMatch() {}

    public ScheduledMatch(int home, int away, int slot) {
        setHome(home);
        setAway(away);
        setSlot(slot);
    }

    @XmlAttribute(name = "home")
    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    @XmlAttribute(name = "away")
    public int getAway() {
        return away;
    }

    public void setAway(int away) {
        this.away = away;
    }

    @XmlAttribute(name = "slot")
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
