package com.thesis;

import com.thesis.constraints.separation.SE1;
import com.thesis.resources.League;
import com.thesis.resources.Slot;
import com.thesis.resources.Team;

import javax.xml.bind.JAXBException;

public class Main {
    public static void main(String[] args) throws JAXBException {
        ParseXML parseXML = new ParseXML();
        Instance instance = parseXML.getInstance();

        for (Team t : instance.getResources().getTeams())
            System.out.println(t.getId() + " " + t.getName() + " " + t.getLeague());

        System.out.println("==================================");

        League l = instance.getResources().getLeague().get(0);
        System.out.println(l.getId() + " " + l.getName());

        System.out.println("==================================");

        for (Slot s : instance.getResources().getSlots())
            System.out.println(s.getId() + " " + s.getName());

        System.out.println("==================================");

        SE1 se1;
        try {
            se1 = instance.getConstraints().getSE1().get(0);
            System.out.println(se1.getMode1() + " " + se1.getMin() + " " + se1.getTeams()
                    + " " + se1.getType());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No SE1 Constraint!");
        }

    }
}
