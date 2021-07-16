package thesis.instance.constraints.capacity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "CA1")
public class CA1 {

    private int max;
    private int min;
    private String mode;
    private int penalty;
    private List<Integer> slots = null;
    private String stringSlots = null;
    private int teams;
    private String type;
    private boolean soft;

    public int getMax() {
        return max;
    }

    @XmlAttribute(name = "max")
    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    @XmlAttribute(name = "min")
    public void setMin(int min) {
        this.min = min;
    }

    public String getMode() {
        return mode;
    }

    @XmlAttribute(name = "mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getPenalty() {
        return penalty;
    }

    @XmlAttribute(name = "penalty")
    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public List<Integer> getSlots() {
        if (slots == null)
            loadSlots();
        return slots;
    }

    public void setSlots(List<Integer> slots) {
        this.slots = slots;
    }

    private void loadSlots() {
        slots = new ArrayList<>();
        if (stringSlots != null || !stringSlots.equals("")) {
            String[] numbers = stringSlots.split(";");
            List<Integer> nums = new ArrayList<>();
            for (String s : numbers) {
                Integer i = Integer.parseInt(s);
                nums.add(i);
            }
            setSlots(nums);
        }
    }

    @XmlAttribute(name = "slots")
    public void setStringSlots(String stringSlots) {
        this.stringSlots = stringSlots;
        loadSlots();
    }

    public int getTeams() {
        return teams;
    }

    @XmlAttribute(name = "teams")
    public void setTeams(int teams) {
        this.teams = teams;
    }

    public String getType() {
        return type;
    }

    @XmlAttribute(name = "type")
    public void setType(String type) {
        this.type = type;
        setSoft(this.type.equals("SOFT"));
    }

    public boolean isSoft() {
        return soft;
    }

    public void setSoft(boolean soft) {
        this.soft = soft;
    }
}
