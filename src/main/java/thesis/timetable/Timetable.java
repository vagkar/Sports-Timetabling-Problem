package thesis.timetable;

import javafx.util.Pair;
import thesis.instance.Instance;
import thesis.instance.constraints.br.BR1;
import thesis.instance.constraints.br.BR2;
import thesis.instance.constraints.capacity.CA1;
import thesis.instance.constraints.capacity.CA2;
import thesis.instance.constraints.capacity.CA3;
import thesis.instance.constraints.capacity.CA4;
import thesis.instance.constraints.fairness.FA2;
import thesis.instance.constraints.game.GA1;
import thesis.instance.constraints.separation.SE1;
import thesis.solution.games.ScheduledMatch;
import thesis.solution.metadata.ObjectiveValue;

import java.util.*;

public class Timetable {
    private Instance instance;

    /*
    * hashMapSchedule = 1(team), 5(team) : 0(slot)
    *
    * hashMapSlot = 1(team), 0(slot) : 6(team)
    *
    * hashMapStatus = 1(team), 0(slot) : True(home)
    * */

    private HashMap<Pair<Integer, Integer>, Integer> hashMapSchedule = new HashMap<>();

    private HashMap<Pair<Integer, Integer>, Integer> hashMapSlot = new HashMap<>();

    private HashMap<Pair<Integer, Integer>, Boolean> hashMapStatus = new HashMap<>();

    private List<ScheduledMatch> scheduledMatches;

    public HashMap<Pair<Integer, Integer>, Integer> getHashMapSchedule() {
        return this.hashMapSchedule;
    }

    // filling the list while printing Timetable to printHashMapSchedule
    public List<ScheduledMatch> getScheduleMatches() {
        return this.scheduledMatches;
    }

    public void putSchedule(Match match) {
        this.hashMapSchedule.put(new Pair<>(match.getHome().getId(), match.getAway().getId()),
                match.getTimeSlot().getId());
        putSlot(match);
        putStatus(match);
    }

    private void putSlot(Match match) {
        this.hashMapSlot.put(new Pair<>(match.getHome().getId(), match.getTimeSlot().getId()),
                match.getAway().getId());
        this.hashMapSlot.put(new Pair<>(match.getAway().getId(), match.getTimeSlot().getId()),
                match.getHome().getId());
    }

    private void putStatus(Match match) {
        this.hashMapStatus.put(new Pair<>(match.getHome().getId(), match.getTimeSlot().getId()),
                true);
        this.hashMapStatus.put(new Pair<>(match.getAway().getId(), match.getTimeSlot().getId()),
                false);
    }

    public int getTeamsSize() {
        return instance.getResources().getTeams().size();
    }

    public int getSlotsSize() {
        return instance.getResources().getSlots().size();
    }

    public void patch(int home, int away, int slot) {
        this.hashMapSchedule.replace(new Pair<>(home, away), slot);

        this.hashMapSlot.replace(new Pair<>(home, slot), away);
        this.hashMapSlot.replace(new Pair<>(away, slot), home);

        this.hashMapStatus.replace(new Pair<>(home, slot), true);
        this.hashMapStatus.replace(new Pair<>(away, slot), false);
    }

    public Timetable(Timetable timetable) {
        instance = timetable.instance;
        hashMapStatus = timetable.getHashMapStatus();
        hashMapSlot = timetable.getHashMapSlot();
        hashMapSchedule = timetable.getHashMapScheduleClone();
    }

    public Timetable(Instance instance) {
        this.instance = instance;
    }

    public void printHashMapSchedule() {
        List<ScheduledMatch> scheduledMatches = new ArrayList<>();
        for (int i = 0; i < instance.getResources().getSlots().size(); i++) {
            System.out.print("Slot " + i + "\t");
        }
        System.out.println();
        Map<Pair<Integer, Integer>, Boolean> printedMatches = new HashMap<>();

        int numberOfTeams = instance.getResources().getTeams().size();
        for (int i = 0; i < numberOfTeams / 2; i++) {
            for (int j = 0; j < instance.getResources().getSlots().size(); j++) {
                int team1 = i;
                int team2 = this.hashMapSlot.get(new Pair<>(team1, j));
                int failed = 0;
                while (printedMatches.get(new Pair<>(team1, team2)) != null
                        && printedMatches.get(new Pair<>(team2, team1)) != null) {
                    team1 = i + failed;
                    team2 = this.hashMapSlot.get(new Pair<>(team1, j));
                    if (team1 == numberOfTeams - 1)
                        break;
                    failed++;
                }
                if (this.hashMapStatus.get(new Pair<>(team1, j))) {
                    System.out.print("(" + team1 + "-" + team2 + ")\t");
                    printedMatches.put(new Pair<>(team1, team2), true);
                    scheduledMatches.add(new ScheduledMatch(team1, team2, j));
                } else {
                    System.out.print("(" + team2 + "-" + team1 + ")\t");
                    printedMatches.put(new Pair<>(team2, team1), true);
                    scheduledMatches.add(new ScheduledMatch(team2, team1, j));
                }
            }
            System.out.println();
        }
        this.scheduledMatches = scheduledMatches;
    }

    public void swapRematch(int team1, int team2) {
        int slot1 = hashMapSchedule.get(new Pair<>(team1, team2));
        int slot2 = hashMapSchedule.get(new Pair<>(team2, team1));

        patch(team1, team2, slot2);
        patch(team2, team1, slot1);
    }

    public void swapOpponents(int team1, int team2) {
        HashMap<Pair<Integer, Integer>, Integer> hashMapSlot = getHashMapSlot();
        HashMap<Pair<Integer, Integer>, Boolean> hashMapStatus = getHashMapStatus();
        for (int slot = 0; slot < instance.getResources().getSlots().size(); slot++) {
            int team1Opponent = hashMapSlot.get(new Pair<>(team1, slot));
            boolean team1Status = hashMapStatus.get(new Pair<>(team1, slot));
            int team2Opponent = hashMapSlot.get(new Pair<>(team2, slot));
            boolean team2Status = hashMapStatus.get(new Pair<>(team2, slot));
            if (team1Opponent == team2) {
                if (team1Status) {
                    patch(team2, team1, slot);
                } else {
                    patch(team1, team2, slot);
                }
            } else {
                if (team1Status) {
                    patch(team1, team2Opponent, slot);
                } else {
                    patch(team2Opponent, team1, slot);
                }
                if (team2Status) {
                    patch(team2, team1Opponent, slot);
                } else {
                    patch(team1Opponent, team2, slot);
                }
            }
        }
    }

    public void swapTeams(int team1, int team2) {
        HashMap<Pair<Integer, Integer>, Integer> hashMapSlot = getHashMapSlot();
        HashMap<Pair<Integer, Integer>, Boolean> hashMapStatus = getHashMapStatus();
        for (int slot = 0; slot < instance.getResources().getSlots().size(); slot++) {
            int team1Opponent = hashMapSlot.get(new Pair<>(team1, slot));
            boolean team1Status = hashMapStatus.get(new Pair<>(team1, slot));
            int team2Opponent = hashMapSlot.get(new Pair<>(team2, slot));
            boolean team2Status = hashMapStatus.get(new Pair<>(team2, slot));
            if (team1Opponent == team2) {
                if (team1Status) {
                    patch(team2, team1, slot);
                } else {
                    patch(team1, team2, slot);
                }
            } else {
                if (team1Status) {
                    patch(team2, team1Opponent, slot);
                } else {
                    patch(team1Opponent, team2, slot);
                }
                if (team2Status) {
                    patch(team1, team2Opponent, slot);
                } else {
                    patch(team2Opponent, team1, slot);
                }
            }
        }
    }

    public void swapSlots(int slot1, int slot2) {

        if (isPhasedViolatedCheckBySlots(slot1, slot2))
            return;

        HashMap<Pair<Integer, Integer>, Integer> hashMapSlot = getHashMapSlot();
        HashMap<Pair<Integer, Integer>, Boolean> hashMapStatus = getHashMapStatus();

        Map<Pair<Integer, Integer>, Boolean> swappedMatches = new HashMap<>();
        for (int team1 = 0; team1 < instance.getResources().getTeams().size(); team1++) {
            int team2Slot1 = hashMapSlot.get(new Pair<>(team1, slot1));
            boolean slot1Status = hashMapStatus.get(new Pair<>(team1, slot1));
            int team2Slot2 = hashMapSlot.get(new Pair<>(team1, slot2));
            boolean slot2Status = hashMapStatus.get(new Pair<>(team1, slot2));
            if (slot1Status) {
                if (swappedMatches.get(new Pair<>(team1,team2Slot1)) == null) {
                    patch(team1, team2Slot1, slot2);
                    swappedMatches.put(new Pair<>(team1, team2Slot1), true);
                }
            } else {
                if (swappedMatches.get(new Pair<>(team2Slot1,team1)) == null) {
                    patch(team2Slot1, team1, slot2);
                    swappedMatches.put(new Pair<>(team2Slot1, team1), true);
                }
            }
            if (slot2Status) {
                if (swappedMatches.get(new Pair<>(team1,team2Slot2)) == null) {
                    patch(team1, team2Slot2, slot1);
                    swappedMatches.put(new Pair<>(team1, team2Slot2), true);
                }
            } else {
                if (swappedMatches.get(new Pair<>(team2Slot2, team1)) == null) {
                    patch(team2Slot2, team1, slot1);
                    swappedMatches.put(new Pair<>(team2Slot2, team1), true);
                }
            }
        }
    }

    private boolean isPhasedViolatedCheckBySlots(int slot1, int slot2) {
        boolean isPhased = instance.getStructure().getFormat().getGameMode().equals("P");
        if (!isPhased)
            return false;
        int sizeSlots = instance.getResources().getSlots().size();
        boolean isSlotsPhased = (slot1 < sizeSlots/2 && slot2 < sizeSlots/2) ||
                (slot1 >= sizeSlots/2 && slot2 >= sizeSlots/2); /* ||
                (Math.abs(slot1-slot2) == sizeSlots/2); if already swapped random slots, this is wrong */
        return !isSlotsPhased;
    }

    public ObjectiveValue CA1Penalty(List<CA1> ca1List) {
        int penalty = 0;
        int infeasibility = 0;
        for (CA1 ca1 : ca1List) {
            int c = 0;
            int team = ca1.getTeams();
            String mode = ca1.getMode();
            for (int slot : ca1.getSlots()) {
                String slotStatus = hashMapStatus.get(new Pair<>(team, slot)) ? "H" : "A";
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
            int teams1 = ca2.getTeams1();
            for (int teams2 : ca2.getTeams2()) {
                int slot = hashMapSchedule.get(new Pair<>(teams1, teams2));
                if (ca2.getSlots().contains(slot)) {
                    if (mode1.equals("H") || mode1.equals("HA")) {
                        c++;
                    }
                }
                slot = hashMapSchedule.get(new Pair<>(teams2, teams1));
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
            for (int teams1 : ca3.getTeams1()) {
                List<Boolean> penaltySlots = new ArrayList<>(Arrays.asList(new Boolean[numberOfSlots]));
                Collections.fill(penaltySlots, false);
                for (int teams2 : ca3.getTeams2()) {
                    if (teams1 == teams2)
                        continue;
                    if (mode1.equals("H") || mode1.equals("HA")) {
                        penaltySlots.set(hashMapSchedule.get(new Pair<>(teams1, teams2)), true);
                    }
                    if (mode1.equals("A") || mode1.equals("HA")) {
                        penaltySlots.set(hashMapSchedule.get(new Pair<>(teams2, teams1)), true);
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
                for (int team1 : teams1) {
                    for (int team2 : teams2) {
                        if (team1 == team2)
                            continue;
                        switch (mode1) {
                            case "H":
                                if (slots.contains(hashMapSchedule.get(new Pair<>(team1, team2)))) {
                                    c++;
                                }
                                break;
                            case "A":
                                if (slots.contains(hashMapSchedule.get(new Pair<>(team2, team1)))) {
                                    c++;
                                }
                                break;
                            case "HA":
                                if (slots.contains(hashMapSchedule.get(new Pair<>(team1, team2)))
                                        || slots.contains(hashMapSchedule.get(new Pair<>(team2, team1)))) {
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
                for (int slot : slots) {
                    int c = 0;
                    for (int team1 : teams1) {
                        for (int team2 : teams2) {
                            if (team1 == team2)
                                continue;
                            switch (mode1) {
                                case "H":
                                    if (slot == hashMapSchedule.get(new Pair<>(team1, team2))) {
                                        c++;
                                    }
                                    break;
                                case "A":
                                    if (slot == hashMapSchedule.get(new Pair<>(team2, team1))) {
                                        c++;
                                    }
                                    break;

                                case "HA":
                                    if (slot == hashMapSchedule.get(new Pair<>(team1, team2))
                                    || slot == hashMapSchedule.get(new Pair<>(team2, team1))) {
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
            ArrayList<Integer> meetings = (ArrayList<Integer>) ga1.getMeetings();
            for (int i = 0; i < meetings.size(); i+=2) {
                if (ga1.getSlots().contains(hashMapSchedule.get(new Pair<>(meetings.get(i), meetings.get(i + 1))))) {
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
                String slotStatus = hashMapStatus.get(new Pair<>(team, slot)) ? "H" : "A" ;
                String prevSlotStatus = hashMapStatus.get(new Pair<>(team, slot - 1)) ? "H" : "A" ;
                switch (br1.getMode2()) {
                    case "H":
                        if (slotStatus.equals("H") && prevSlotStatus.equals("H")) {
                            breaks++;
                        }
                        break;
                    case "A":
                        if (slotStatus.equals("A") && prevSlotStatus.equals("A")) {
                            breaks++;
                        }
                        break;
                    case "HA":
                        if (slotStatus.equals(prevSlotStatus)) {
                            breaks++;
                        }
                        break;
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
            for (int team : br2.getTeams()) {
                for (int slot : br2.getSlots()) {
                    if (slot == instance.getResources().getSlots().size() - 1)
                        continue;
                    String slotStatus = hashMapStatus.get(new Pair<>(team, slot)) ? "H" : "A" ;
                    String nextSlotStatus = hashMapStatus.get(new Pair<>(team, slot + 1)) ? "H" : "A" ;
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

    public ObjectiveValue FA2Penalty(List<FA2> fa2List) {
        int infeasibility = 0;
        int penalty = 0;
        for (FA2 fa2 : fa2List) {
            HashMap<Pair<Integer, Integer>, Integer> homeMatches = new HashMap<>();
            for (int team : fa2.getTeams()) {
                int h = 0;
                for (int slot : fa2.getSlots()) {
                    String slotStatus = hashMapStatus.get(new Pair<>(team, slot)) ? "H" : "A" ;
                    if (slotStatus.equals("H")) {
                        h++;
                    }
                    homeMatches.put(new Pair<>(team, slot), h);
                }
            }
            ArrayList<Integer> teams = (ArrayList<Integer>) fa2.getTeams();
            for (int slot : fa2.getSlots()) {
                int maxDiff = 0;
                for (int indexTeam1 = 0; indexTeam1 < teams.size(); indexTeam1++) {
                    for (int indexTeam2 = indexTeam1 + 1; indexTeam2 < teams.size(); indexTeam2++) {
                        int team1 = teams.get(indexTeam1);
                        int team2 = teams.get(indexTeam2);
                        int diff = Math.abs(homeMatches.get(new Pair<>(team1, slot))
                                - homeMatches.get(new Pair<>(team2, slot)));
                        if (diff > maxDiff) {
                            maxDiff = diff;
                        }
                    }
                }
                if (maxDiff > fa2.getIntp()) {
                    int deviation = maxDiff - fa2.getIntp();
                    if (fa2.isSoft()) {
                        penalty += deviation * fa2.getPenalty();
                    } else {
                        infeasibility += deviation * fa2.getPenalty();
                    }
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
            for (int t1 = 0; t1 < teams.size() - 1; t1++) {
                for (int t2 = t1+1; t2 < teams.size(); t2++) {
                    int matchSlot1 = hashMapSchedule.get(new Pair<>(t1,t2));
                    int matchSlot2 = hashMapSchedule.get(new Pair<>(t2,t1));
                    int maxTimeSlot;
                    int minTimeSlot;
                    int deviation;
                    if (matchSlot1 > matchSlot2) {
                        maxTimeSlot = matchSlot1;
                        minTimeSlot = matchSlot2;
                    } else {
                        minTimeSlot = matchSlot1;
                        maxTimeSlot = matchSlot2;
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
        //FA2
        if (instance.getConstraints().getFa2Constraints() != null) {
            ObjectiveValue objectiveValue = FA2Penalty(instance.getConstraints().getFa2Constraints());
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

    public HashMap<Pair<Integer, Integer>, Integer> getHashMapSlot() {
        return (HashMap<Pair<Integer, Integer>, Integer>) hashMapSlot.clone();
    }

    public HashMap<Pair<Integer, Integer>, Boolean> getHashMapStatus() {
        return (HashMap<Pair<Integer, Integer>, Boolean>) hashMapStatus.clone();
    }

    private HashMap<Pair<Integer, Integer>, Integer> getHashMapScheduleClone() {
        return (HashMap<Pair<Integer, Integer>, Integer>) hashMapSchedule.clone();
    }
}
