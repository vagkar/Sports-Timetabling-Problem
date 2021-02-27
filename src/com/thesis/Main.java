package com.thesis;

import com.thesis.entities.League;
import com.thesis.entities.Slot;
import com.thesis.entities.Team;
import com.thesis.xmlparser.XmlParser;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String fileName = SelectXmlFile.selectXmlFile();

        XmlParser xmlParser = new XmlParser();

        ArrayList<League> leagues = xmlParser.parseLeague(fileName);
        ArrayList<Slot> slots = xmlParser.parseSlots(fileName);
        ArrayList<Team> teams = xmlParser.parseTeams(fileName);

        System.out.println("========================================");
        for (League l : leagues) {
            System.out.println("Id: " + l.getId() + " Name: " + l.getName());
        }
        System.out.println("========================================");
        for (Team t : teams) {
            System.out.println("Id: " + t.getId() + " Name: " + t.getName() + " League: " + t.getLeague());
        }
        System.out.println("========================================");
        for (Slot s : slots) {
            System.out.println("Id: " + s.getId() + " Name: " + s.getName());
        }
        System.out.println("========================================");

    }
}
