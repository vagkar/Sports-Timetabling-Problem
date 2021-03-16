package com.thesis;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JAXBException {
        File file = new File("instances/test.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Resources.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Resources resources = (Resources) jaxbUnmarshaller.unmarshal(file);
        for (Team t : resources.getTeams())
            System.out.println(t.getId() + " " + t.getName() + " " + t.getLeague());
        System.out.println(resources.getLeague().get(0).getName());
    }
}
