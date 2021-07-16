package thesis.solution;

import thesis.solution.games.ScheduledMatch;
import thesis.solution.metadata.MetaData;
import thesis.solution.metadata.ObjectiveValue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType( propOrder = {"metaData", "games"} )
@XmlRootElement(name = "Solution")
public class Solution {

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

    public void setObjectiveValue(ObjectiveValue objectiveValue) {
        metaData.setObjectiveValue(objectiveValue);
    }


}
