package com.thesis.instance.constraints.capacity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "CA3")
public class CA3 {

    private int intp;
    private int max;
    private int min;
    private String mode1;
    private String mode2;
    private int penalty;
    private String stringTeams1;
    private List<Integer> teams1;
    private String stringTeams2;
    private List<Integer> teams2;
    private boolean soft;
    private String type;

    public int getIntp() {
        return intp;
    }

    @XmlAttribute(name = "intp")
    public void setIntp(int intp) {
        this.intp = intp;
    }

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

    public String getMode1() {
        return mode1;
    }

    @XmlAttribute(name = "mode1")
    public void setMode1(String mode1) {
        this.mode1 = mode1;
    }

    public String getMode2() {
        return mode2;
    }

    @XmlAttribute(name = "mode2")
    public void setMode2(String mode2) {
        this.mode2 = mode2;
    }

    public int getPenalty() {
        return penalty;
    }

    @XmlAttribute(name = "penalty")
    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public String getStringTeams1() {
        return stringTeams1;
    }

    @XmlAttribute(name = "teams1")
    public void setStringTeams1(String stringTeams1) {
        this.stringTeams1 = stringTeams1;
        loadTeams1();
    }

    public List<Integer> getTeams1() {
        if (teams1 == null)
            loadTeams1();
        return teams1;
    }

    public void setTeams1(List<Integer> teams1) {
        this.teams1 = teams1;
    }

    private void loadTeams1() {
        teams1 = new ArrayList<>();
        if (stringTeams1 != null || !stringTeams1.equals("")) {
            String[] numbers = stringTeams1.split(";");
            List<Integer> nums = new ArrayList<>();
            for (String s : numbers) {
                Integer i = Integer.parseInt(s);
                nums.add(i);
            }
            setTeams1(nums);
        }
    }

    public String getStringTeams2() {
        return stringTeams2;
    }

    @XmlAttribute(name = "teams2")
    public void setStringTeams2(String stringTeams2) {
        this.stringTeams2 = stringTeams2;
        loadTeams2();
    }

    public List<Integer> getTeams2() {
        if (teams2 == null)
            loadTeams2();
        return teams2;
    }

    public void setTeams2(List<Integer> teams2) {
        this.teams2 = teams2;
    }

    private void loadTeams2() {
        teams2 = new ArrayList<>();
        if (stringTeams2 != null || !stringTeams2.equals("")) {
            String[] numbers = stringTeams2.split(";");
            List<Integer> nums = new ArrayList<>();
            for (String s : numbers) {
                Integer i = Integer.parseInt(s);
                nums.add(i);
            }
            setTeams2(nums);
        }
    }

    public boolean isSoft() {
        return soft;
    }

    public void setSoft(boolean soft) {
        this.soft = soft;
    }

    public String getType() {
        return type;
    }

    @XmlAttribute(name = "type")
    public void setType(String type) {
        this.type = type;
        setSoft(this.type.equals("SOFT"));
    }
}
