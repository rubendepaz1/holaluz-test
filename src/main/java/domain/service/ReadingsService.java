package domain.service;

import domain.model.ReadingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;

@Service
@ComponentScan({"domain.service"})
public class ReadingsService {

    @Autowired
    CsvParser csvParser;

    @Autowired
    XmlParser xmlParser;

    public ReadingsService() throws Exception {
    }

    public void findSuspiciusReadings(String args){
        try {

            File myFile = new File(args);
            List<ReadingBean> readings = null;
            if (StringUtils.getFilenameExtension(myFile.getPath()).equals("csv")){
                readings = csvParser.parseCsv(myFile);
                System.out.println("Parsing CSV...");
            }
            if (StringUtils.getFilenameExtension(myFile.getPath()).equals("xml")){
                readings = xmlParser.parseXml(myFile);
                System.out.println("Parsing XML...");
            }

            Map<String, Double> medianPerClient = readings.stream()
                    .collect(Collectors.groupingBy((ReadingBean::getClient), averagingInt(ReadingBean::getReading)));

            System.out.println("Number of records: "+readings.size());
            System.out.println("| Client              | Month              | Suspicious         | Median");
            System.out.println(" -------------------------------------------------------------------------------");

            readings.forEach((r) ->{
                if((r.getReading() < ((50 * medianPerClient.get(r.getClient())) / 100)
                        || r.getReading() > ((150 * medianPerClient.get(r.getClient())) / 100))){
                    System.out.println("|"+r.getClient()+"        "+"|"+r.getPeriod()+"             |"+r.getReading()+"               |"+ medianPerClient.get(r.getClient()));
                }
            }
            );


        } catch (Exception e) {
            throw new RuntimeException("Error during processing", e);
        }
    }


}
