package thesis.timetable.hillclimbing;

import thesis.instance.Instance;
import thesis.solution.Solution;
import thesis.timetable.Timetable;

import java.util.Random;

public class HillClimbing {

    public static Solution bestSolution(Timetable timetable, Solution solution, Instance instance) {
        Random random = new Random();
        int i = 0;
        while (i < 10000) {
            i++;
            int moveOption = random.nextInt(2);
            executeMoveOption(timetable, moveOption);
            Solution tempSolution = new Solution();
            tempSolution.setObjectiveValue(timetable.computePenalties(instance));
            tempSolution.setGames(timetable.getScheduleMatches());
            if (solution.getMetaData().getObjectiveValue().getInfeasibility()
                    > tempSolution.getMetaData().getObjectiveValue().getInfeasibility()) {
                solution = tempSolution;
                timetable.printHashMapSchedule();
                System.out.println("Infeasibility: " + solution.getMetaData().getObjectiveValue().getInfeasibility()
                        + " Objective: " + solution.getMetaData().getObjectiveValue().getObjective());
            }
        }
        return solution;
    }

    private static void executeMoveOption(Timetable timetable, int moveOption) {
        switch (moveOption) {
            case 0:
                swapRematchMove(timetable);
                break;

            case 1:
                slotSwapMove(timetable);
                break;
        }
    }

    private static void swapRematchMove(Timetable timetable) {
        Random random = new Random();
        int team1 = random.nextInt(timetable.getTeamsSize());
        int team2 = random.nextInt(timetable.getTeamsSize());
        if (team1 == team2)
            return;
        timetable.swapRematch(team1, team2);
    }

    private static void slotSwapMove(Timetable timetable) {
        Random random = new Random();
        int slot1 = random.nextInt(timetable.getSlotsSize());
        int slot2 = random.nextInt(timetable.getSlotsSize());
        if (slot1 == slot2)
            return;
        timetable.swapSlots(slot1, slot2);
    }
}
