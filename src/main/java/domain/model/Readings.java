package domain.model;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Readings {

    @XmlElement
    private List<ReadingBean> reading;



    public Readings() {}
    public Readings(List<ReadingBean> readings) {
        super();

        this.reading = readings;
    }


    @XmlElement
    public List<ReadingBean> getReadings() {
        return reading;
    }
    public void setReadings(List<ReadingBean> reading) {
        this.reading = reading;
    }

}
