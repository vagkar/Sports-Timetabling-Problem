package thesis.timetable;

import thesis.instance.Instance;
import thesis.timetable.scheduler.BergerMethod;
import thesis.timetable.scheduler.CircleMethod;
import thesis.timetable.scheduler.ScheduleMethod;

import java.util.Scanner;

public class TimetableScheduler {

    private Timetable timetable;
    private Instance instance;

    public TimetableScheduler(Instance instance) {
        this.instance = instance;
    }

    public Timetable schedule() {
        ScheduleMethod scheduleMethod = selectScheduleMethod();
        this.timetable = scheduleMethod.schedule();
        this.timetable.printHashMapSchedule();
        return this.timetable;
    }

    private ScheduleMethod selectScheduleMethod() {
        System.out.println("1) Circle Method");
        System.out.println("2) Berger Method");
        System.out.print("Select schedule Method: ");
        int methodOption = new Scanner(System.in).nextInt();

        switch (methodOption) {
            case 0:
                System.exit(-1);

            case 1:
                return new CircleMethod(instance);

            case 2:
                return new BergerMethod(instance);

            default:
                System.out.println("Select a valid number from 1 to 2 or 0 to exit!");
                return selectScheduleMethod();
        }
    }

    public Timetable schedule(int option) {
        ScheduleMethod scheduleMethod = selectScheduleMethod(option);
        this.timetable = scheduleMethod.schedule();
        this.timetable.printHashMapSchedule();
        return this.timetable;
    }

    private ScheduleMethod selectScheduleMethod(int option) {
        switch (option) {
            case 0:
                System.exit(-1);

            case 1:
                return new CircleMethod(instance);

            default:
                System.out.println("Select a valid number from 1 to 1 or 0 to exit!");
                return selectScheduleMethod();
        }
    }
}
