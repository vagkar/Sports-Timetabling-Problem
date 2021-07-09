package com.thesis.timetable;

import com.thesis.instance.Instance;
import com.thesis.instance.constraints.br.BR1;
import com.thesis.instance.constraints.br.BR2;
import com.thesis.instance.constraints.capacity.CA1;
import com.thesis.instance.constraints.capacity.CA2;
import com.thesis.instance.constraints.capacity.CA3;
import com.thesis.instance.constraints.capacity.CA4;
import com.thesis.instance.constraints.game.GA1;
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
//            if (ca1.isSoft())
//                continue;
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

    public ObjectiveValue CA2Penalty(List<CA2> ca2List) {
        int penalty = 0;
        int infeasibility = 0;
        for (CA2 ca2 : ca2List) {
            int c = 0;
            String mode1 = ca2.getMode1();
            Integer teams1 = ca2.getTeams1();
            for (Integer teams2 : ca2.getTeams2()) {
                Integer slot = timetable2.get(teams1).get(teams2).getTimeSlot().getId();
                if (ca2.getSlots().contains(slot)) {
                    if (mode1.equals("H") || mode1.equals("HA")) {
                        c++;
                    }
                }
                slot = timetable2.get(teams2).get(teams1).getTimeSlot().getId();
                if (ca2.getSlots().contains(slot)) {
                    if (mode1.equals("A") || mode1.equals("HA")) {
                        c++;
                    }
                }
            }
            if (c > ca2.getMax()) {
                int deviation = c - ca2.getMax();
                if (ca2.isSoft()) {
                    penalty += deviation * ca2.getPenalty();
                } else {
                    infeasibility += deviation * ca2.getPenalty();
                }
            }
        }
        return new ObjectiveValue(infeasibility, penalty);
    }

    public ObjectiveValue CA3Penalty(List<CA3> ca3List, int numberOfSlots) {
        int penalty = 0;
        int infeasibility = 0;
        for (CA3 ca3 : ca3List) {
            String mode1 = ca3.getMode1();
            for (Integer teams1 : ca3.getTeams1()) {
                List<Boolean> penaltySlots = new ArrayList<>(Arrays.asList(new Boolean[numberOfSlots]));
                Collections.fill(penaltySlots, false);
                for (Integer teams2 : ca3.getTeams2()) {
                    if (teams1 == teams2)
                        continue;
                    if (mode1.equals("H") || mode1.equals("HA")) {
                        penaltySlots.set(timetable2.get(teams1).get(teams2).getTimeSlot().getId(), true);
                    }
                    if (mode1.equals("A") || mode1.equals("HA")) {
                        penaltySlots.set(timetable2.get(teams2).get(teams1).getTimeSlot().getId(), true);
                    }
                }
                for (int i = 0; i < (penaltySlots.size() - ca3.getIntp() + 1); i++) {
                    int c = 0;
                    for (int j = 0; j < ca3.getIntp(); j++) {
                        if (penaltySlots.get(i + j)) {
                            c++;
                        }
                    }
                    if (c > ca3.getMax()){
                        int deviation = c - ca3.getMax();
                        if (ca3.isSoft()) {
                            penalty += deviation * ca3.getPenalty();
                        } else {
                            infeasibility += deviation * ca3.getPenalty();
                        }
                    }
                }
            }
        }
        return new ObjectiveValue(infeasibility, penalty);
    }

    public ObjectiveValue CA4Penalty(List<CA4> ca4List) {
        int infeasibility = 0;
        int penalty = 0;
        for (CA4 ca4 : ca4List) {
            String mode1 = ca4.getMode1();
            String mode2 = ca4.getMode2();
            ArrayList<Integer> teams1 = (ArrayList<Integer>) ca4.getTeams1();
            ArrayList<Integer> teams2 = (ArrayList<Integer>) ca4.getTeams2();
            ArrayList<Integer> slots = (ArrayList<Integer>) ca4.getSlots();
            int max = ca4.getMax();
            if (mode2.equals("GLOBAL")) {
                int c = 0;
                for (Integer team1 : teams1) {
                    for (Integer team2 : teams2) {
                        if (team1.equals(team2))
                            continue;
                        switch (mode1) {
                            case "H":
                                if (slots.contains(timetable2.get(team1).get(team2).getTimeSlot().getId())) {
                                    c++;
                                }
                                break;
                            case "A":
                                if (slots.contains(timetable2.get(team2).get(team1).getTimeSlot().getId())) {
                                    c++;
                                }
                                break;
                            case "HA":
                                if (slots.contains(timetable2.get(team1).get(team2).getTimeSlot().getId())
                                        || slots.contains(timetable2.get(team2).get(team1).getTimeSlot().getId())) {
                                    c++;
                                }
                                break;
                        }
                    }
                }
                if (c > max) {
                    int deviation = c - max;
                    if (ca4.isSoft()) {
                        penalty += deviation * ca4.getPenalty();
                    } else {
                        infeasibility += deviation * ca4.getPenalty();
                    }
                }
            } else if (mode2.equals("EVERY")) {
                for (Integer slot : slots) {
                    int c = 0;
                    for (Integer team1 : teams1) {
                        for (Integer team2 : teams2) {
                            if (team1.equals(team2))
                                continue;
                            switch (mode1) {
                                case "H":
                                    if (slot.equals(timetable2.get(team1).get(team2).getTimeSlot().getId())) {
                                        c++;
                                    }
                                    break;
                                case "A":
                                    if (slot.equals(timetable2.get(team2).get(team1).getTimeSlot().getId())) {
                                        c++;
                                    }
                                    break;

                                case "HA":
                                    if (slot.equals(timetable2.get(team1).get(team2).getTimeSlot().getId())
                                    || slot.equals(timetable2.get(team2).get(team1).getTimeSlot().getId())) {
                                        c++;
                                    }
                            }
                        }
                    }
                    if (c > max) {
                        int deviation = c - max;
                        if (ca4.isSoft()) {
                            penalty += deviation * ca4.getPenalty();
                        } else {
                            infeasibility += deviation * ca4.getPenalty();
                        }
                    }
                }
            }
        }
        return new ObjectiveValue(infeasibility, penalty);
    }

    public ObjectiveValue GA1Penalty(List<GA1> ga1List) {
        int infeasibility = 0;
        int penalty = 0;
        for (GA1 ga1 : ga1List) {
            int c = 0;
            List<Integer> meetings = ga1.getMeetings();
            for (int i = 0; i < meetings.size(); i+=2) {
                if (ga1.getSlots().contains(timetable2.get(meetings.get(i)).get(meetings.get(i + 1)).getTimeSlot().getId())) {
                    c++;
                }
            }
            int deviation = 0;
            if (c > ga1.getMax()) {
                deviation = c - ga1.getMax();
            } else if (c < ga1.getMin()) {
                deviation = ga1.getMin() - c;
            }
            if (ga1.isSoft()) {
                penalty += deviation * ga1.getPenalty();
            } else {
                infeasibility += deviation * ga1.getPenalty();
            }
        }
        return new ObjectiveValue(infeasibility, penalty);
    }

    public ObjectiveValue BR1Penalty(List<BR1> br1List) {
        int infeasibility = 0;
        int penalty = 0;
        for (BR1 br1 : br1List) {
            int breaks = 0;
            int team = br1.getTeams();
            for (Integer slot : br1.getSlots()) {
                if (slot == 0)
                    continue;
                String slotStatus = timetable3[team][slot].getStatus();
                String prevSlotStatus = timetable3[team][slot - 1].getStatus();
                if (br1.getMode2().equals("H")) {
                    if (slotStatus.equals("H") && prevSlotStatus.equals("H")) {
                        breaks++;
                    }
                } else if (br1.getMode2().equals("A")) {
                    if (slotStatus.equals("A") && prevSlotStatus.equals("A")) {
                        breaks++;
                    }
                } else if (br1.getMode2().equals("HA")) {
                    if (slotStatus.equals(prevSlotStatus)) {
                        breaks++;
                    }
                }
            }
            if (breaks > br1.getIntp()) {
                int deviation = breaks - br1.getIntp();
                if (br1.isSoft()) {
                    penalty += deviation * br1.getPenalty();
                } else {
                    infeasibility += deviation * br1.getPenalty();
                }
            }
        }
        return new ObjectiveValue(infeasibility, penalty);
    }

    public ObjectiveValue BR2Penalty(List<BR2> br2List) {
        int infeasibility = 0;
        int penalty = 0;
        for (BR2 br2 : br2List) {
            int breaks = 0;
            for (Integer team : br2.getTeams()) {
                ArrayList<Integer> slots = (ArrayList<Integer>) br2.getSlots();
                for (int i = 0; i < slots.size() - 1; i++) {
                    Integer slot = slots.get(i);
                    if (slot == slots.size() - 1)
                        continue;
                    String slotStatus = timetable3[team][slot].getStatus();
                    String nextSlotStatus = timetable3[team][slot + 1].getStatus();
                    if (slotStatus.equals(nextSlotStatus)) {
                        breaks++;
                    }
                }
            }
            if (breaks > br2.getIntp()) {
                int deviation = breaks - br2.getIntp();
                if (br2.isSoft()) {
                    penalty += deviation * br2.getPenalty();
                } else {
                    infeasibility += deviation * br2.getPenalty();
                }
            }
        }
        return new ObjectiveValue(infeasibility, penalty);
    }

    public ObjectiveValue SE1Penalty(List<SE1> se1List) {
        int infeasibility = 0;
        int penalty = 0;
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
        //CA1
        if (instance.getConstraints().getCA1() != null) {
            ObjectiveValue objectiveValue = CA1Penalty(instance.getConstraints().getCA1());
            infeasibility += objectiveValue.getInfeasibility();
            penalty += objectiveValue.getObjective();
        }
        //CA2
        if (instance.getConstraints().getCA2() != null) {
            ObjectiveValue objectiveValue = CA2Penalty(instance.getConstraints().getCA2());
            infeasibility += objectiveValue.getInfeasibility();
            penalty += objectiveValue.getObjective();
        }
        //CA3
        if (instance.getConstraints().getCA3() != null) {
            ObjectiveValue objectiveValue = CA3Penalty(instance.getConstraints().getCA3(), instance.getResources().getSlots().size());
            infeasibility += objectiveValue.getInfeasibility();
            penalty += objectiveValue.getObjective();
        }
        //CA4
        if (instance.getConstraints().getCA4() != null) {
            ObjectiveValue objectiveValue = CA4Penalty(instance.getConstraints().getCA4());
            infeasibility += objectiveValue.getInfeasibility();
            penalty += objectiveValue.getObjective();
        }
        //GA1
        if (instance.getConstraints().getGa1Constraints() != null) {
            ObjectiveValue objectiveValue = GA1Penalty(instance.getConstraints().getGa1Constraints());
            infeasibility += objectiveValue.getInfeasibility();
            penalty += objectiveValue.getObjective();
        }
        //BR1
        if (instance.getConstraints().getBR1() != null) {
            ObjectiveValue objectiveValue = BR1Penalty(instance.getConstraints().getBR1());
            infeasibility += objectiveValue.getInfeasibility();
            penalty += objectiveValue.getObjective();
        }
        //BR2
        if (instance.getConstraints().getBR2() != null) {
            ObjectiveValue objectiveValue = BR2Penalty(instance.getConstraints().getBR2());
            infeasibility += objectiveValue.getInfeasibility();
            penalty += objectiveValue.getObjective();
        }
        //SE1
        if (instance.getConstraints().getSE1() != null) {
            ObjectiveValue objectiveValue = SE1Penalty(instance.getConstraints().getSE1());
            infeasibility += objectiveValue.getInfeasibility();
            penalty += objectiveValue.getObjective();
        }
        return new ObjectiveValue(infeasibility, penalty);
    }
}
