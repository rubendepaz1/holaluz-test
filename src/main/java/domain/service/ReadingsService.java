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
            List<ReadingBean> finalReadings = readings;
            medianPerClient.forEach((k, v) -> {
                try {
                    printSuspiciusReadings(k, v, finalReadings);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            throw new RuntimeException("Error during processing", e);
        }
    }

    private void printSuspiciusReadings(String client, Double median, List<ReadingBean> readingsBean) throws Exception {
        for (ReadingBean reading : readingsBean) {
            if(reading.getClient().equals(client)
                    && (reading.getReading() < ((50 * median) / 100)
                    || reading.getReading() > ((150 * median) / 100))){
                System.out.println("|"+reading.getClient()+"        "+"|"+reading.getPeriod()+"             |"+reading.getReading()+"               |"+ median);
            }
        }
    }

}
