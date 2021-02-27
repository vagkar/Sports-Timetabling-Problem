package com.thesis.xmlparser;

import com.thesis.entities.League;
import com.thesis.entities.Slot;
import com.thesis.entities.Team;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class XmlParser {

    public ArrayList<Team> parseTeams(String fileName) {

        ArrayList<Team> teams = new ArrayList<Team>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileName);
            NodeList teamList = doc.getElementsByTagName("team");
            for (int i = 0; i < teamList.getLength(); i++) {
                Node l = teamList.item(i);
                if (l.getNodeType() == Node.ELEMENT_NODE) {
                    Element team = (Element) l;
                    teams.add(new Team(Integer.parseInt(team.getAttribute("id")),
                            team.getAttribute("name"),
                            Integer.parseInt(team.getAttribute("league"))));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public ArrayList<Slot> parseSlots(String fileName) {

        ArrayList<Slot> slots = new ArrayList<Slot>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileName);
            NodeList slotList = doc.getElementsByTagName("slot");
            for (int i = 0; i < slotList.getLength(); i++) {
                Node s = slotList.item(i);
                if (s.getNodeType() == Node.ELEMENT_NODE) {
                    Element slot = (Element) s;
                    slots.add(new Slot(Integer.parseInt(slot.getAttribute("id")),
                            slot.getAttribute("name")));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return slots;
    }

    public ArrayList<League> parseLeague(String fileName) {

        ArrayList<League> leagues = new ArrayList<League>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileName);
            NodeList leagueList = doc.getElementsByTagName("league");
            for (int i = 0; i < leagueList.getLength(); i++) {
                Node l = leagueList.item(i);
                if (l.getNodeType() == Node.ELEMENT_NODE) {
                    Element league = (Element) l;
                    leagues.add(new League(Integer.parseInt(league.getAttribute("id")),
                            league.getAttribute("name")));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leagues;
    }
}
