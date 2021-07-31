package thesis.timetable.scheduler;

import thesis.instance.Instance;
import thesis.instance.resources.Slot;
import thesis.instance.resources.Team;
import thesis.timetable.Match;
import thesis.timetable.Timetable;

import java.util.ArrayList;
import java.util.LinkedList;

public class CircleMethod implements ScheduleMethod {

    private final Instance instance;
    private Timetable timetable;

    public CircleMethod(Instance instance) {
        this.timetable = new Timetable(instance);
        this.instance = instance;
    }

    @Override
    public Timetable schedule() {
        ArrayList<Team> teams = (ArrayList<Team>) instance.getResources().getTeams();
        ArrayList<Slot> slots = (ArrayList<Slot>) instance.getResources().getSlots();
        LinkedList<Team> homeTeams = new LinkedList<Team>();
        LinkedList<Team> awayTeams = new LinkedList<Team>();
        int allTeams = teams.size();
        int halfTeams = allTeams / 2;

        for (int i = 0; i < halfTeams; i++) {
            homeTeams.add(teams.get(i));
            awayTeams.addFirst(teams.get(i + halfTeams));
        }

        Team constantTeam = homeTeams.pop();
        int timeSlots = slots.size();

        //Single RR
        for (int i = 0; i < timeSlots/2; i++) {
            for (int j = 0; j < halfTeams; j++) {
                if (j == 0) {
                    Match match = new Match(constantTeam, awayTeams.get(j), slots.get(i));
                    timetable.putSchedule(match);
                    continue;
                }
                Match match = new Match(homeTeams.get(j - 1), awayTeams.get(j), slots.get(i));
                timetable.putSchedule(match);
            }

            homeTeams.addFirst(awayTeams.pop());
            awayTeams.add(homeTeams.getLast());
            homeTeams.removeLast();
        }

        //Double RR
        for (int i = timeSlots/2; i < timeSlots; i++) {
            for (int j = 0; j < halfTeams; j++) {
                if (j == 0) {
                    Match match = new Match(awayTeams.get(j), constantTeam, slots.get(i));
                    timetable.putSchedule(match);
                    continue;
                }
                Match match = new Match(awayTeams.get(j), homeTeams.get(j - 1), slots.get(i));
                timetable.putSchedule(match);
            }

            homeTeams.addFirst(awayTeams.pop());
            awayTeams.add(homeTeams.getLast());
            homeTeams.removeLast();
        }

        return this.timetable;
    }
}
