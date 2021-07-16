package thesis.instance.metadata;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MetaData")
public class MetaData {

    private String instanceName;
    private String dataType;
    private String contributor;
    private Date date;

    public String getInstanceName() {
        return instanceName;
    }

    @XmlElement(name = "InstanceName")
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getDataType() {
        return dataType;
    }

    @XmlElement(name = "DataType")
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getContributor() {
        return contributor;
    }

    @XmlElement(name = "Contributor")
    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public Date getDate() {
        return date;
    }

    @XmlElement(name = "Date")
    public void setDate(Date date) {
        this.date = date;
    }
}
