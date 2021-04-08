package com.thesis;

import com.thesis.instance.Instance;
import com.thesis.instance.resources.Team;
import com.thesis.timetable.TimetableScheduler;
import com.thesis.xmldata.ParseXML;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws JAXBException {
        ParseXML parseXML = new ParseXML();
        Instance instance = parseXML.getInstance();
        System.out.println("Instance Name: " + instance.getMetaData().getInstanceName()
                + " Date: " + instance.getMetaData().getDate().toString()
                + " Objective: " + instance.getObjectiveFunction().getObjective());

        ArrayList<Team> teams = (ArrayList<Team>) instance.getResources().getTeams();
        TimetableScheduler timetableScheduler = new TimetableScheduler(teams.size(), instance);

        timetableScheduler.schedule();

    }
}
