package thesis.timetable.scheduler;

import thesis.instance.Instance;
import thesis.timetable.Timetable;

public class BergerMethod implements ScheduleMethod{

    private final Instance instance;
    private Timetable timetable;

    public BergerMethod(Instance instance) {
        this.timetable = new Timetable(instance.getResources().getTeams().size(),
                instance.getResources().getSlots().size());
        this.instance = instance;
    }

    @Override
    public Timetable schedule() {

        return null;
    }
}
