package thesis;

import thesis.instance.Instance;
import thesis.instance.resources.Team;
import thesis.solution.Solution;
import thesis.timetable.Timetable;
import thesis.timetable.TimetableScheduler;
import thesis.xmldata.ParseXML;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;

public class App {

    public static void execute() throws JAXBException {
        ParseXML parseXML = new ParseXML();
        parseXML.unmarshall();
        Instance instance = parseXML.getInstance();

        ArrayList<Team> teams = (ArrayList<Team>) instance.getResources().getTeams();
        TimetableScheduler timetableScheduler = new TimetableScheduler(instance);
        Timetable timetable = timetableScheduler.schedule();

        timetable.swapSlots(2, 5);
        timetable.printHashMapSchedule();
        timetable.swapRematch(1, 5);
        timetable.printHashMapSchedule();

        Solution solution = new Solution();
        solution.setObjectiveValue(timetable.computePenalties(instance));
        solution.setGames(timetable.getScheduleMatches());

        parseXML.marshall(solution);

    }

}
