package com.thesis.solution;

import com.thesis.solution.games.ScheduledMatch;
import com.thesis.solution.metadata.MetaData;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Solution")
public class Solution {

//    private Timetable timetable;

    private MetaData metaData;
    private List<ScheduledMatch> games;

    public Solution() {
        metaData = new MetaData();
    }


    @XmlElement(name = "MetaData")
    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    @XmlElementWrapper(name = "Games")
    @XmlElement(name = "ScheduledMatch")
    public List<ScheduledMatch> getGames() {
        return games;
    }

    public void setGames(List<ScheduledMatch> games) {
        this.games = games;
    }

    public void setSolutionName(String solutionName) {
        metaData.setSolutionName(solutionName);
    }

    public void setInstanceName(String instanceName) {
        metaData.setInstanceName(instanceName);
    }

    public void setObjectiveValue(int infeasibility, int objective) {
        metaData.getObjectiveValue().setInfeasibility(infeasibility);
        metaData.getObjectiveValue().setObjective(objective);
    }


}
