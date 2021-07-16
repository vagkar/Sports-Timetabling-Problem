package thesis.instance.constraints.capacity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "CapacityConstraints")
public class CapacityConstraints {

    private List<CA1> ca1Constraints;
    private List<CA2> ca2Constraints;
    private List<CA3> ca3Constraints;
    private List<CA4> ca4Constraints;

    public List<CA1> getCa1Constraints() {
        return ca1Constraints;
    }

    @XmlElement(name = "CA1")
    public void setCa1Constraints(List<CA1> ca1Constraints) {
        this.ca1Constraints = ca1Constraints;
    }

    public List<CA2> getCa2Constraints() {
        return ca2Constraints;
    }

    @XmlElement(name = "CA2")
    public void setCa2Constraints(List<CA2> ca2Constraints) {
        this.ca2Constraints = ca2Constraints;
    }

    public List<CA3> getCa3Constraints() {
        return ca3Constraints;
    }

    @XmlElement(name = "CA3")
    public void setCa3Constraints(List<CA3> ca3Constraints) {
        this.ca3Constraints = ca3Constraints;
    }

    public List<CA4> getCa4Constraints() {
        return ca4Constraints;
    }

    @XmlElement(name = "CA4")
    public void setCa4Constraints(List<CA4> ca4Constraints) {
        this.ca4Constraints = ca4Constraints;
    }
}
