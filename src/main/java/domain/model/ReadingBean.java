package domain.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReadingBean {

    @XmlAttribute(name = "clientID")
    @CsvBindByPosition(position = 0)
    @Getter
    @Setter
    private String client;

    @XmlAttribute
    @CsvBindByPosition(position = 1)
    @Getter
    @Setter
    private String period;

    @XmlValue
    @CsvBindByPosition(position = 2)
    @Getter
    @Setter
    private int reading;
}
