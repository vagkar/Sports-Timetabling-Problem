package thesis.instance.resources;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Resources")
public class Resources {

    List<Team> teams;
    List<League> league;
    List<Slot> slots;

    @XmlElementWrapper(name = "Leagues")
    @XmlElement(name = "league")
    public void setLeague(List<League> league) {
        this.league = league;
    }

    public List<League> getLeague() {
        return this.league;
    }

    @XmlElementWrapper(name = "Teams")
    @XmlElement(name = "team")
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    @XmlElementWrapper(name = "Slots")
    @XmlElement(name = "slot")
    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public List<Slot> getSlots() {
        return slots;
    }
}
