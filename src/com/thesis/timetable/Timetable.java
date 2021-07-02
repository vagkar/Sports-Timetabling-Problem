package com.thesis.timetable;

import com.thesis.instance.Instance;
import com.thesis.instance.constraints.capacity.CA1;
import com.thesis.instance.constraints.separation.SE1;
import com.thesis.instance.resources.Slot;
import com.thesis.solution.games.ScheduledMatch;
import com.thesis.solution.metadata.ObjectiveValue;

import java.util.*;

public class Timetable {

    private List<HashMap<Integer, Match>> timetable2 = new ArrayList<>();

    private Match[][] timetable;

    private Match[][] timetable3;

    public Timetable(int numberOfTeams , int timeSlots) {
        this.timetable = new Match[(numberOfTeams / 2)][timeSlots];
        for (int i = 0; i < numberOfTeams; i++) {
            this.timetable2.add(new HashMap<>());
        }
        this.setTimetable3(new Match[numberOfTeams][timeSlots]);
    }

    public Match[][] getTimetable() {
        return this.timetable.clone();
    }

    public void setTimetable(Match[][] timetable) {
        this.timetable = timetable.clone();
    }

    public void add(int period, int timeSlot, Match match) {
        this.timetable[period][timeSlot] = match;
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

    public Match[][] getTimetable3() {
        return this.timetable3;
    }

    public void setTimetable3(Match[][] timetable3) {
        this.timetable3 = timetable3.clone();
    }

    public void  addToTimetable3(int homeTeam, int timeSlot, Match match) {
        this.timetable3[homeTeam][timeSlot] = new Match(match.getHome(), match.getAway(), match.getTimeSlot());
        this.timetable3[homeTeam][timeSlot].setStatus("H");
        this.timetable3[match.getAway().getId()][timeSlot] = new Match(match.getHome(), match.getAway(), match.getTimeSlot());
        this.timetable3[match.getAway().getId()][timeSlot].setStatus("A");
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

    public ObjectiveValue CA1Penalty(List<CA1> ca1List) {
        int penalty = 0;
        int infeasibility = 0;
        for (CA1 ca1 : ca1List) {
            if (ca1.isSoft())
                continue;
            int c = 0;
            Integer team = ca1.getTeams();
            String mode = ca1.getMode();
            for (Integer slot : ca1.getSlots()) {
                String slotStatus = timetable3[team][slot].getStatus();
                if (slotStatus.equals(mode))
                    c++;
            }
            if (c > ca1.getMax()) {
                int deviation = c - ca1.getMax();
                if (ca1.isSoft()) {
                    penalty += deviation * ca1.getPenalty();
                } else {
                    infeasibility += deviation * ca1.getPenalty();
                }
            }
        }
        return new ObjectiveValue(infeasibility, penalty);
    }

    public ObjectiveValue SE1Penalty(List<SE1> se1List) {
        int penalty = 0;
        int infeasibility = 0;
        for (SE1 se1 : se1List) {
            ArrayList<Integer> teams = (ArrayList<Integer>) se1.getTeams();
            Collections.sort(teams);
            for (int i = 0; i < teams.size() - 1; i++) {
                for (int j = i+1; j < teams.size(); j++) {
                    Match match = timetable2.get(i).get(j);
                    Match match2 = timetable2.get(j).get(i);
                    int maxTimeSlot;
                    int minTimeSlot;
                    int deviation;
                    if (match.getTimeSlot().getId() > match2.getTimeSlot().getId()) {
                        maxTimeSlot = match.getTimeSlot().getId();
                        minTimeSlot = match2.getTimeSlot().getId();
                    } else {
                        minTimeSlot = match.getTimeSlot().getId();
                        maxTimeSlot = match2.getTimeSlot().getId();
                    }

                    deviation = maxTimeSlot - minTimeSlot;
                    if (deviation <= se1.getMin()) {
                        if (se1.isSoft()) {
                            penalty += ((se1.getMin() + 1) - deviation) * se1.getPenalty();
                        } else {
                            infeasibility += ((se1.getMin() + 1) - deviation) * se1.getPenalty();
                        }
                    }
                }
            }
        }
        return new ObjectiveValue(infeasibility, penalty);
    }

    public ObjectiveValue computePenalties(Instance instance) {
        int infeasibility = 0;
        int penalty = 0;
        //SE1
        ObjectiveValue objectiveValue = SE1Penalty(instance.getConstraints().getSE1());
        infeasibility += objectiveValue.getInfeasibility();
        penalty += objectiveValue.getObjective();
        //CA1
        objectiveValue = CA1Penalty(instance.getConstraints().getCA1());
        infeasibility += objectiveValue.getInfeasibility();
        penalty += objectiveValue.getObjective();
        return new ObjectiveValue(infeasibility, penalty);
    }
}
