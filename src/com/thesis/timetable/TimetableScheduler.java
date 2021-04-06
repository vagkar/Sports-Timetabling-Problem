package com.thesis.timetable;

import com.thesis.Instance;
import com.thesis.timetable.scheduler.CircleMethod;
import com.thesis.timetable.scheduler.ScheduleMethod;

import java.util.Scanner;

public class TimetableScheduler {

    private Timetable timetable;
    private Instance instance;
    private int numberOfTeams;

    public TimetableScheduler(int numberOfTeams, Instance instance) {
        this.numberOfTeams = numberOfTeams;
        this.instance = instance;
    }

    public Timetable schedule() {
        ScheduleMethod scheduleMethod = selectScheduleMethod();
        this.timetable = scheduleMethod.schedule();
        this.timetable.printTimetable();
        return this.timetable;
    }

    private ScheduleMethod selectScheduleMethod() {
        System.out.println("1) Circle Method");
        System.out.print("Select schedule Method: ");
        int chosenMethod = new Scanner(System.in).nextInt();

        switch (chosenMethod) {
            case 0:
                System.exit(-1);

            case 1:
                return new CircleMethod(numberOfTeams, instance);

            default:
                System.out.println("Select a valid number from 1 to 1 or 0 to exit!");
                return selectScheduleMethod();
        }
    }
}
