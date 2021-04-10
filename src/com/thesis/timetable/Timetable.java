package com.thesis.timetable;

import com.thesis.instance.constraints.separation.SE1;
import com.thesis.instance.resources.Slot;
import com.thesis.solution.games.ScheduledMatch;

import java.util.*;

public class Timetable {

    private List<HashMap<Integer, Match>> timetable2 = new ArrayList<>();

    private Match[][] timetable;

    public Timetable(int numberOfTeams , int timeSlots) {
        this.timetable = new Match[(numberOfTeams / 2)][timeSlots];
        for (int i = 0; i < numberOfTeams; i++) {
            this.timetable2.add(new HashMap<>());
        }
    }

    public Match[][] getTimetable() {
        return this.timetable.clone();
    }

    public void setTimetable(Match[][] timetable) {
        this.timetable = timetable.clone();
    }

    public void add(int period, int timeslot, Match match) {
        this.timetable[period][timeslot] = match;
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

    public Slot getMatchTimeSlot(int home, int away) {
        return timetable2.get(home).get(away).getTimeSlot();
    }

    public void put(Match match) {
        this.timetable2.get(match.getHome().getId()).put(match.getAway().getId(), match);
    }

    public List<HashMap<Integer, Match>> getTimetable2() {
        return this.timetable2;
    }

    public void setTimetable2(List<HashMap<Integer, Match>> timetable1) {
        this.timetable2 = timetable1;
    }

    public List<ScheduledMatch> getScheduleMatches() {
        List<ScheduledMatch> scheduledMatches = new ArrayList<>();
        int periods = timetable.length;
        int timeSlots = timetable[0].length;
        for (int i = 0; i < timeSlots; i++) {
            for (int j = 0; j < periods; j++) {
                scheduledMatches.add(new ScheduledMatch(timetable[j][i].getHome().getId(),
                        timetable[j][i].getAway().getId(),
                        timetable[j][i].getTimeSlot().getId()));
            }
        }
        return scheduledMatches;
    }

    public int valuateSeparationConstraint(List<SE1> se1List) {
        int valuate = 0;
        for (SE1 se1 : se1List) {
            ArrayList<Integer> teams = (ArrayList<Integer>) se1.getTeams();
            Collections.sort(teams);
            for (Integer i : teams) {
                for (Integer j = i+1; j < teams.size(); j++) {
                    Match match = timetable2.get(i).get(j);
                    Match match2 = timetable2.get(j).get(i);
                    int maxTimeSlot;
                    int minTimeSlot;
                    if (match.getTimeSlot().getId() > match2.getTimeSlot().getId()) {
                        maxTimeSlot = match.getTimeSlot().getId();
                        minTimeSlot = match2.getTimeSlot().getId();
                    } else {
                        minTimeSlot = match.getTimeSlot().getId();
                        maxTimeSlot = match2.getTimeSlot().getId();
                    }
                    if (maxTimeSlot - minTimeSlot < se1.getMin()) {
                        if (se1.isTypeSoft()) {
                            valuate += se1.getPenalty();
                        }
                    }
                }
            }
        }
        return valuate;
    }
}
