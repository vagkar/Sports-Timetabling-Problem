package com.thesis;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ParseXML {

    private Instance instance;

    public ParseXML() throws JAXBException {
        File file = new File("instances/test.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Instance.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Instance instance = (Instance) jaxbUnmarshaller.unmarshal(file);
        setInstance(instance);
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }
}
