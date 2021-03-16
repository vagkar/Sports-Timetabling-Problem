package com.thesis;

import com.thesis.resources.Slot;
import com.thesis.resources.Team;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {
    public static void main(String[] args) throws JAXBException {
        File file = new File("instances/test.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Instance.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Instance instance = (Instance) jaxbUnmarshaller.unmarshal(file);
        for (Team t : instance.getResources().getTeams())
            System.out.println(t.getId() + " " + t.getName() + " " + t.getLeague());
        System.out.println(instance.getResources().getLeague().get(0).getName());
        for (Slot s : instance.getResources().getSlots())
            System.out.println(s.getId() + " " + s.getName());

        System.out.println(instance.getConstraints().getSE1().get(0).getTeams());
    }
}
