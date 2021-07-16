package thesis.instance.constraints.game;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "GA1")
public class GA1 {

    private int max;
    private String stringMeetings;
    private List<Integer> meetings;
    private int min;
    private int penalty;
    private String stringSlots;
    private List<Integer> slots;
    private String type;
    private boolean soft;

    public int getMax() {
        return max;
    }

    @XmlAttribute(name = "max")
    public void setMax(int max) {
        this.max = max;
    }

    public String getStringMeetings() {
        return stringMeetings;
    }

    @XmlAttribute(name = "meetings")
    public void setStringMeetings(String stringMeetings) {
        this.stringMeetings = stringMeetings;
        loadMeetings();
    }

    public List<Integer> getMeetings() {
        if (meetings == null)
            loadMeetings();
        return meetings;
    }

    public void setMeetings(List<Integer> meetings) {
        this.meetings = meetings;
    }

    private void loadMeetings() {
        meetings = new ArrayList<>();
        if (stringMeetings!= null || !stringMeetings.equals("")) {
            String[] numbers = stringMeetings.split(";|,");
            List<Integer> nums = new ArrayList<>();
            for (String s : numbers) {
                Integer i = Integer.parseInt(s);
                nums.add(i);
            }
            setMeetings(nums);
        }
    }

    public int getMin() {
        return min;
    }

    @XmlAttribute(name = "min")
    public void setMin(int min) {
        this.min = min;
    }

    public int getPenalty() {
        return penalty;
    }

    @XmlAttribute(name = "penalty")
    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public String getStringSlots() {
        return stringSlots;
    }

    @XmlAttribute(name = "slots")
    public void setStringSlots(String stringSlots) {
        this.stringSlots = stringSlots;
        loadSlots();
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
