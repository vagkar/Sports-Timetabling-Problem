package com.thesis;

import com.thesis.instance.Instance;
import com.thesis.instance.resources.Team;
import com.thesis.solution.Solution;
import com.thesis.timetable.Timetable;
import com.thesis.timetable.TimetableScheduler;
import com.thesis.xmldata.ParseXML;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;

public class App {

    public static void execute() throws JAXBException {
        ParseXML parseXML = new ParseXML();
        parseXML.unmarshall();
        Instance instance = parseXML.getInstance();

        ArrayList<Team> teams = (ArrayList<Team>) instance.getResources().getTeams();
        TimetableScheduler timetableScheduler = new TimetableScheduler(teams.size(), instance);
        Timetable timetable = timetableScheduler.schedule();

        Solution solution = new Solution();
        solution.setObjectiveValue(timetable.computePenalties(instance));
        solution.setGames(timetable.getScheduleMatches());

        parseXML.marshall(solution);

    }

}
