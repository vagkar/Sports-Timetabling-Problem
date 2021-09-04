package thesis;

import thesis.instance.Instance;
import thesis.instance.resources.Team;
import thesis.solution.Solution;
import thesis.timetable.Timetable;
import thesis.timetable.TimetableScheduler;
import thesis.timetable.hillclimbing.HillClimbing;
import thesis.xmldata.ParseXML;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;

public class App {

    public static void execute() throws JAXBException {
        ParseXML parseXML = new ParseXML();
        parseXML.unmarshall();
        Instance instance = parseXML.getInstance();

        TimetableScheduler timetableScheduler = new TimetableScheduler(instance);
        Timetable timetable = timetableScheduler.schedule();

        Solution solution = new Solution();
        solution.setObjectiveValue(timetable.computePenalties(instance));
        solution.setGames(timetable.getScheduleMatches());
        System.out.println("Infeasibility: " + solution.getMetaData().getObjectiveValue().getInfeasibility()
                + " Objective: " + solution.getMetaData().getObjectiveValue().getObjective() + "\n");

        solution = HillClimbing.bestSolution(timetable, solution, instance);

        parseXML.marshall(solution);

    }

}
