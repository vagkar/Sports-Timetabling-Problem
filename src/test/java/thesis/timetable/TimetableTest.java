package thesis.timetable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import thesis.instance.Instance;
import thesis.solution.metadata.ObjectiveValue;
import thesis.xmldata.ParseXML;

import javax.xml.bind.JAXBException;

class TimetableTest {

    private static Instance instance;

    private static Timetable timetable;

    @BeforeAll
    public static void setUp() {
        ParseXML parseXML = new ParseXML();
        try {
            parseXML.unmarshall(1); // options range 1 - 10
            instance = parseXML.getInstance();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        TimetableScheduler timetableScheduler = new TimetableScheduler(instance.getResources().getTeams().size(), instance);
        timetable = timetableScheduler.schedule(1);
    }

    @Test
    void CA1PenaltyTest() {
        ObjectiveValue objectiveValue = timetable.CA1Penalty(instance.getConstraints().getCA1());
        Assertions.assertEquals(6, objectiveValue.getInfeasibility());
        Assertions.assertEquals(16, objectiveValue.getObjective());
    }

    @Test
    void CA2PenaltyTest() {
    }

    @Test
    void CA3PenaltyTest() {
    }

    @Test
    void CA4PenaltyTest() {
    }

    @Test
    void GA1PenaltyTest() {
    }

    @Test
    void BR1PenaltyTest() {
    }

    @Test
    void BR2PenaltyTest() {
    }

    @Test
    void FA2PenaltyTest() {
    }

    @Test
    void SE1PenaltyTest() {
    }

    @Test
    void computePenaltiesTest() {
    }
}