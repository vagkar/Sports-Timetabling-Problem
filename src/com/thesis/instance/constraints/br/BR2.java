package com.thesis.instance.constraints.br;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "BR2")
public class BR2 {

    private int intp;
    private String mode1;
    private String mode2;
    private int penalty;
    private String stringSlots;
    private List<Integer> slots;
    private String stringTeams;
    private List<Integer> teams;
    private String type;
    private boolean soft;

    public int getIntp() {
        return intp;
    }

    @XmlAttribute(name = "intp")
    public void setIntp(int intp) {
        this.intp = intp;
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

    public String getStringTeams() {
        return stringTeams;
    }

    @XmlAttribute(name = "teams")
    public void setStringTeams(String stringTeams) {
        this.stringTeams = stringTeams;
        loadTeams();
    }

    public List<Integer> getTeams() {
        if (teams == null)
            loadTeams();
        return teams;
    }

    public void setTeams(List<Integer> teams) {
        this.teams = teams;
    }

    private void loadTeams() {
        teams = new ArrayList<>();
        if (stringTeams != null || !stringTeams.equals("")) {
            String[] numbers = stringTeams.split(";");
            List<Integer> nums = new ArrayList<>();
            for (String s : numbers) {
                Integer i = Integer.parseInt(s);
                nums.add(i);
            }
            setTeams(nums);
        }
    }
}
