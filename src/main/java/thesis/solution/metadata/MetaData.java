package thesis.solution.metadata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * MetaData for Solution File
 *
 */
@XmlType( propOrder = {"solutionName", "instanceName", "objectiveValue"} )
@XmlRootElement(name = "MetaData")
public class MetaData {
    private String solutionName;
    private String instanceName;
    private ObjectiveValue objectiveValue;

    public MetaData() {
        objectiveValue = new ObjectiveValue();
    }

    @XmlElement(name = "ObjectiveValue")
    public ObjectiveValue getObjectiveValue() {
        return objectiveValue;
    }

    public void setObjectiveValue(ObjectiveValue objectiveValue) {
        this.objectiveValue = objectiveValue;
    }

    @XmlElement(name = "InstanceName")
    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    @XmlElement(name = "SolutionName")
    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }
}
