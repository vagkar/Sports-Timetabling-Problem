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


        LinkedList<Team> oddHomeTeams = new LinkedList<>();
        LinkedList<Team> oddAwayTeams = new LinkedList<>();
        LinkedList<Team> evenHomeTeams = new LinkedList<>();
        LinkedList<Team> evenAwayTeams = new LinkedList<>();

        for (int i = 0; i < teams.size()/2; i++) {
            evenHomeTeams.add(teams.get(i));
            evenAwayTeams.add(teams.get(i + teams.size()/2));
        }

        for (int i = 0; i < teams.size()/2; i++) {
            oddAwayTeams.add(teams.get(i + 1));
            if (i == 0)
                oddHomeTeams.add(teams.get(0));
            else
                oddHomeTeams.add(teams.get(i + teams.size()/2));
        }

        Team constantTeam = teams.get(teams.size()-1);
        evenAwayTeams.removeLast();
        oddHomeTeams.removeLast();
        int teamsSize = teams.size();
        int timeSlots = slots.size();

        //Single RR
        for (int i = 0; i < timeSlots/2; i++) {
            for (int j = 0; j < teamsSize/2; j++) {
                if (i % 2 == 0) {
                    if (j == 0){
                        Match match = new Match(evenHomeTeams.get(j), constantTeam, slots.get(i));
                        timetable.putSchedule(match);
                        continue;
                    }
                    Match match = new Match(evenHomeTeams.get(j), evenAwayTeams.get(evenAwayTeams.size() - j), slots.get(i));
                    timetable.putSchedule(match);
                } else {
                    if (j == 0) {
                        Match match = new Match(constantTeam, oddAwayTeams.get(oddAwayTeams.size() - 1) , slots.get(i));
                        timetable.putSchedule(match);
                        continue;
                    }
                    Match match = new Match(oddHomeTeams.get(oddHomeTeams.size() - j), oddAwayTeams.get((oddAwayTeams.size()-1) - j), slots.get(i));
                    timetable.putSchedule(match);
                }
            }
            if (i % 2 == 0) {
                evenHomeTeams.add(evenAwayTeams.pop());
                evenAwayTeams.add(evenHomeTeams.pop());
            } else {
                oddHomeTeams.addFirst(oddAwayTeams.pop());
                oddAwayTeams.add(oddHomeTeams.getLast());
                oddHomeTeams.removeLast();
            }
        }


        //Double RR
        for (int i = timeSlots/2; i < timeSlots; i++) {
            for (int j = 0; j < teamsSize/2; j++) {
                if (i % 2 == 0) {
                    if (j == 0){
                        Match match = new Match(evenHomeTeams.get(j), constantTeam, slots.get(i));
                        timetable.putSchedule(match);
                        continue;
                    }
                    Match match = new Match(evenAwayTeams.get(evenAwayTeams.size() - j), evenHomeTeams.get(j), slots.get(i));
                    timetable.putSchedule(match);
                } else {
                    if (j == 0) {
                        Match match = new Match(constantTeam, oddAwayTeams.get(oddAwayTeams.size() - 1) , slots.get(i));
                        timetable.putSchedule(match);
                        continue;
                    }
                    Match match = new Match(oddAwayTeams.get((oddAwayTeams.size()-1) - j), oddHomeTeams.get(oddHomeTeams.size() - j), slots.get(i));
                    timetable.putSchedule(match);
                }
            }
            if (i % 2 == 0) {
                evenHomeTeams.add(evenAwayTeams.pop());
                evenAwayTeams.add(evenHomeTeams.pop());
            } else {
                oddHomeTeams.addFirst(oddAwayTeams.pop());
                oddAwayTeams.add(oddHomeTeams.getLast());
                oddHomeTeams.removeLast();
            }
        }
        return this.timetable;
    }
}
