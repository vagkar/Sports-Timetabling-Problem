package thesis.timetable.scheduler;

import javafx.util.Pair;
import thesis.instance.Instance;
import thesis.instance.resources.Slot;
import thesis.instance.resources.Team;
import thesis.timetable.Match;
import thesis.timetable.Timetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class BergerMethod implements ScheduleMethod{

    private final Instance instance;
    private Timetable timetable;

    public BergerMethod(Instance instance) {
        this.timetable = new Timetable(instance);
        this.instance = instance;
    }

    @Override
    public Timetable schedule() {
        HashMap<Pair<Integer, Integer>, Integer> RRSchedule = new HashMap<>();
        ArrayList<Team> teams = (ArrayList<Team>) instance.getResources().getTeams();
        ArrayList<Slot> slots = (ArrayList<Slot>) instance.getResources().getSlots();
        Team constantTeam = teams.get(teams.size()-1);
        teams.remove(teams.size()-1);

        for (int i = 0; i < teams.size(); i++) {
            LinkedList<Team> slotsOrder = new LinkedList<>(teams);

            int slot = i;
            int period = 0;
            for (int j = 0; j < teams.size(); j++) {
                if (slot < teams.size()) {
                    RRSchedule.put(new Pair<>(i, j), slot);
                } else if (slot == teams.size()) {
                    slot = 0;
                    RRSchedule.put(new Pair<>(i, j), slot);
                }

                if (period > 2)
                    period = 0;

                if (slot % 2 == 1) {
                    if (i == j) {
                        Match match = new Match(constantTeam, teams.get(j), slots.get(slot));
                        timetable.putSchedule(match);
                        continue;
                    }
                    Match match = new Match(teams.get(i), teams.get(j), slots.get(slot));
                    timetable.putSchedule(match);
                } else {
                    if (i == j) {
                        Match match = new Match(teams.get(j), constantTeam, slots.get(slot));
                        timetable.putSchedule(match);
                        continue;
                    }
                    Match match = new Match(teams.get(j), teams.get(i), slots.get(slot));
                    timetable.putSchedule(match);
                }
                period++;
                slot++;
            }
        }
        return this.timetable;
    }
}
