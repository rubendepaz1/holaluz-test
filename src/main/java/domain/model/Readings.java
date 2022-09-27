package domain.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


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
