package com.thesis;

import com.thesis.resources.Slot;
import com.thesis.resources.Team;

public class Match {

    private Team home;
    private Team away;
    private Slot timeSlot;

    public Match(Team home, Team away, Slot timeSlot) {
        this.setHome(home);
        this.setAway(away);
        this.setTimeSlot(timeSlot);
    }

    public Slot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Slot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }
}
