package thesis.instance.constraints.br;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "BreakConstraints")
public class BreakConstraints {

    private List<BR1> br1Constraints;
    private List<BR2> br2Constraints;

    public List<BR1> getBr1Constraints() {
        if (br1Constraints == null)
            br1Constraints = new ArrayList<>();
        return br1Constraints;
    }

    @XmlElement(name = "BR1")
    public void setBr1Constraints(List<BR1> br1Constraints) {
        this.br1Constraints = br1Constraints;
    }

    public List<BR2> getBr2Constraints() {
        if (br2Constraints == null)
            br2Constraints = new ArrayList<>();
        return br2Constraints;
    }

    @XmlElement(name = "BR2")
    public void setBr2Constraints(List<BR2> br2Constraints) {
        this.br2Constraints = br2Constraints;
    }
}
