package com.thesis.xmldata;

import com.thesis.instance.Instance;
import com.thesis.solution.Solution;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import java.io.File;

public class ParseXML {

    private Instance instance;
    private File file;

    public ParseXML() {

    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public void unmarshall() throws JAXBException {
        this.file = new SelectXML().getSelectedFile();
        JAXBContext jaxbContext = JAXBContext.newInstance(Instance.class);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Instance instance = (Instance) unmarshaller.unmarshal(file);
        setInstance(instance);
    }

    public void marshall(Solution solution) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Solution.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        solution.setInstanceName(file.getName());
        solution.setSolutionName("Solution-"
                + instance.getMetaData().getInstanceName() + ".xml");
//        marshaller.marshal(solution, (Result) System.in);
        marshaller.marshal(solution, new File("solutions/Solution-"
                + instance.getMetaData().getInstanceName() + ".xml"));
    }
}
