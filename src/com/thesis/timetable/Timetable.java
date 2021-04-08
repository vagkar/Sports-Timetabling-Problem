package com.thesis.timetable;

import com.thesis.Match;
import com.thesis.instance.resources.Team;

import java.util.HashMap;

public class Timetable {

    private HashMap<Team, Match>[] timetable1;

    private Match[][] timetable;

    public Timetable(int numberOfTeams , int timeSlots) {
        this.timetable = new Match[(numberOfTeams / 2)][timeSlots];
        this.timetable1 = new HashMap[timeSlots];
    }

    public Match[][] getTimetable() {
        return this.timetable.clone();
    }

    public void setTimetable(Match[][] timetable) {
        this.timetable = timetable.clone();
    }

    public void printTimetable() {
        for (int i = 0; i < timetable[0].length; i++) {
            System.out.print(timetable[0][i].getTimeSlot().getName() + "\t");
        }
        System.out.println();
        for (int i = 0; i < timetable.length; i++) {
            for (int j = 0; j < timetable[i].length; j++) {
                System.out.print("(" + timetable[i][j].getHome().getId() + "-" + timetable[i][j].getAway().getId() + ")\t");
            }
            System.out.println();
        }
    }

    public void put(int timeSlot, Match match) {
        this.timetable1[timeSlot].put(match.getHome(), match);
    }

    public HashMap<Team, Match>[] getTimetable1() {
        return this.timetable1.clone();
    }

    public void setTimetable1(HashMap<Team, Match>[] timetable1) {
        this.timetable1 = timetable1;
    }
}
