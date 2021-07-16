package thesis.instance.structure;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Structure")
public class Structure {

   private Format format;

    public Format getFormat() {
        return format;
    }

    @XmlElement(name = "Format")
    public void setFormat(Format format) {
        this.format = format;
    }
}
