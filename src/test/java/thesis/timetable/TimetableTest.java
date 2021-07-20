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
        TimetableScheduler timetableScheduler = new TimetableScheduler(instance);
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
        ObjectiveValue objectiveValue = timetable.CA2Penalty(instance.getConstraints().getCA2());
        Assertions.assertEquals(0, objectiveValue.getInfeasibility());
        Assertions.assertEquals(0, objectiveValue.getObjective());
    }

    @Test
    void CA3PenaltyTest() {
        ObjectiveValue objectiveValue = timetable.CA3Penalty(instance.getConstraints().getCA3(), instance.getResources().getSlots().size());
        Assertions.assertEquals(8, objectiveValue.getInfeasibility());
        Assertions.assertEquals(330, objectiveValue.getObjective());
    }

    @Test
    void CA4PenaltyTest() {
        ObjectiveValue objectiveValue = timetable.CA4Penalty(instance.getConstraints().getCA4());
        Assertions.assertEquals(0, objectiveValue.getInfeasibility());
        Assertions.assertEquals(0, objectiveValue.getObjective());
    }

    @Test
    void GA1PenaltyTest() {
        ObjectiveValue objectiveValue = timetable.GA1Penalty(instance.getConstraints().getGa1Constraints());
        Assertions.assertEquals(0, objectiveValue.getInfeasibility());
        Assertions.assertEquals(4, objectiveValue.getObjective());
    }

    @Test
    void BR1PenaltyTest() {
        ObjectiveValue objectiveValue = timetable.BR1Penalty(instance.getConstraints().getBR1());
        Assertions.assertEquals(0, objectiveValue.getInfeasibility());
        Assertions.assertEquals(0, objectiveValue.getObjective());
    }

    @Test
    void BR2PenaltyTest() {
        ObjectiveValue objectiveValue = timetable.BR2Penalty(instance.getConstraints().getBR2());
        Assertions.assertEquals(18, objectiveValue.getInfeasibility());
        Assertions.assertEquals(0, objectiveValue.getObjective());
    }

    @Test
    void FA2PenaltyTest() {
        ObjectiveValue objectiveValue = timetable.FA2Penalty(instance.getConstraints().getFa2Constraints());
        Assertions.assertEquals(0, objectiveValue.getInfeasibility());
        Assertions.assertEquals(0, objectiveValue.getObjective());
    }

    @Test
    void SE1PenaltyTest() {
        ObjectiveValue objectiveValue = timetable.SE1Penalty(instance.getConstraints().getSE1());
        Assertions.assertEquals(0, objectiveValue.getInfeasibility());
        Assertions.assertEquals(900, objectiveValue.getObjective());
    }

    @Test
    void computePenaltiesTest() {
        ObjectiveValue objectiveValue = timetable.computePenalties(instance);
        Assertions.assertEquals(32, objectiveValue.getInfeasibility());
        Assertions.assertEquals(1250, objectiveValue.getObjective());
    }
}