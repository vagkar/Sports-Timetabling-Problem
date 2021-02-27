package com.thesis.entities;

public class Team extends Resource {

    private int leagueId;

    public Team(int id, String name, int leagueId) {
        super(id, name);
        this.leagueId = leagueId;
    }

    public int getLeague() {
        return leagueId;
    }

    public void setLeague(int leagueId) {
        this.leagueId = leagueId;
    }

}
