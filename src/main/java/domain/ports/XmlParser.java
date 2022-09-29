package domain.ports;

import domain.model.ReadingBean;
import domain.model.Readings;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@Component
public class XmlParser implements FileParser{

    public List<ReadingBean> parseFile(File myFile){
        JAXBContext jaxbContext;
        try
        {
            jaxbContext = JAXBContext.newInstance(Readings.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Readings r= (Readings) jaxbUnmarshaller.unmarshal(myFile);
            return r.getReadings();
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
