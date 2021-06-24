package com.thesis.instance.constraints.separation;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "SE1")
public class SE1 {

    private String mode1;
    private int min;
    private int penalty;
    private List<Integer> teams = null;
    private boolean soft;
    private String type;
    private String stringTeams = null;

    public String getMode1() {
        return this.mode1;
    }

    @XmlAttribute(name = "mode1")
    public void setMode1(String mode1) {
        this.mode1 = mode1;
    }

    public int getMin() {
        return this.min;
    }

    @XmlAttribute(name = "min")
    public void setMin(int min) {
        this.min = min;
    }

    public int getPenalty() {
        return this.penalty;
    }

    @XmlAttribute(name = "penalty")
    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public List<Integer> getTeams() {
        if (teams == null)
            loadTeams();
        return this.teams;
    }

    public void setTeams(List<Integer> teams) {
        this.teams = teams;
    }

    private void loadTeams() {
        teams = new ArrayList<Integer>();
        if (stringTeams != null || !stringTeams.equals("")) {
            String[] numbers = stringTeams.split(";");
            List<Integer> nums = new ArrayList<Integer>();
            for (String s : numbers) {
                Integer i = Integer.parseInt(s);
                nums.add(i);
            }
            setTeams(nums);
        }
    }

    @XmlAttribute(name = "teams")
    public void setStringTeams(String stringTeams) {
        this.stringTeams = stringTeams;
        loadTeams();
    }

    public boolean isSoft() {
        return soft;
    }

    private void setSoft(boolean soft) {
        this.soft = soft;
    }

    public String getType() {
        return this.type;
    }

    @XmlAttribute(name = "type")
    public void setType(String type) {
        this.type = type;
        setSoft(this.type.equals("SOFT"));
    }

}
