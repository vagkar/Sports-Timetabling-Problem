package thesis.timetable.hillclimbing;

import thesis.instance.Instance;
import thesis.solution.Solution;
import thesis.timetable.Timetable;

import java.util.Random;

public class HillClimbing {

    private static String moveUsed = "";

    public static Solution bestSolution(Timetable timetable, Solution solution, Instance instance) {
        Random random = new Random();
        int i = 0;
        while (i < 10000) {
            Timetable tempTimetable = new Timetable(timetable);
            int moveOption = random.nextInt(4);
            executeMoveOption(tempTimetable, moveOption);
            Solution tempSolution = new Solution();
            tempSolution.setObjectiveValue(tempTimetable.computePenalties(instance));


            if (solution.getMetaData().getObjectiveValue().getInfeasibility()
                    > tempSolution.getMetaData().getObjectiveValue().getInfeasibility()) {
                solution = tempSolution;
                timetable = tempTimetable;
                System.out.println(moveUsed);
                timetable.printHashMapSchedule();
                solution.setGames(timetable.getScheduleMatches());
                System.out.println("Infeasibility: " + solution.getMetaData().getObjectiveValue().getInfeasibility()
                        + " Objective: " + solution.getMetaData().getObjectiveValue().getObjective()
                        + "\n");
            }

            if (solution.getMetaData().getObjectiveValue().getInfeasibility()
                    == tempSolution.getMetaData().getObjectiveValue().getInfeasibility()
                    && solution.getMetaData().getObjectiveValue().getObjective()
                    > tempSolution.getMetaData().getObjectiveValue().getObjective()) {
                solution = tempSolution;
                timetable = tempTimetable;
                System.out.println(moveUsed);
                timetable.printHashMapSchedule();
                solution.setGames(timetable.getScheduleMatches());
                System.out.println("Infeasibility: " + solution.getMetaData().getObjectiveValue().getInfeasibility()
                        + " Objective: " + solution.getMetaData().getObjectiveValue().getObjective()
                        + "\n");
            }
            i++;
        }
        return solution;
    }

    private static void executeMoveOption(Timetable timetable, int moveOption) {
        switch (moveOption) {
            case 0:
                moveUsed = "Swap Rematch Move";
                swapRematchMove(timetable);
                break;

            case 1:
                moveUsed = "Swap Slots Move";
                swapSlotMove(timetable);
                break;

            case 2:
                moveUsed = "Swap Teams Move";
                swapTeamsMove(timetable);
                break;

            case 3:
                moveUsed = "Swap Opponents Move";
                swapOpponentsMove(timetable);
                break;
        }
    }

    private static void swapOpponentsMove(Timetable timetable) {
        Random random = new Random();
        int team1 = random.nextInt(timetable.getTeamsSize());
        int team2 = random.nextInt(timetable.getTeamsSize());
        if (team1 == team2)
            return;
        timetable.swapOpponents(team1, team2);
    }

    private static void swapTeamsMove(Timetable timetable) {
        Random random = new Random();
        int team1 = random.nextInt(timetable.getTeamsSize());
        int team2 = random.nextInt(timetable.getTeamsSize());
        if (team1 == team2)
            return;
        timetable.swapTeams(team1, team2);
    }

    private static void swapRematchMove(Timetable timetable) {
        Random random = new Random();
        int team1 = random.nextInt(timetable.getTeamsSize());
        int team2 = random.nextInt(timetable.getTeamsSize());
        if (team1 == team2)
            return;
        timetable.swapRematch(team1, team2);
    }

    private static void swapSlotMove(Timetable timetable) {
        Random random = new Random();
        int slot1 = random.nextInt(timetable.getSlotsSize());
        int slot2 = random.nextInt(timetable.getSlotsSize());
        if (slot1 == slot2)
            return;
        timetable.swapSlots(slot1, slot2);
    }
}
