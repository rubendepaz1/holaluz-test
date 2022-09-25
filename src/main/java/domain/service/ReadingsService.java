package domain.service;

import domain.model.ReadingBean;
import domain.utils.CsvReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;

@Service
public class ReadingsService {


    public ReadingsService() throws Exception {
    }

    public void findSuspiciusReadings(String[] args){
        try {

            File myFile = new File(args[0]);
            URI fileUri = null;
            if(myFile.exists()) {
                fileUri = myFile.toURI();
            }else {
                System.out.println("Unnable to find "+args[0]+" file");
                System.exit(0);
            }

            List<ReadingBean> readings = CsvReader.beanBuilder(Paths.get(fileUri), ReadingBean.class);

            Map<String, Double> medianPerClient = readings.stream()
                    .collect(Collectors.groupingBy((ReadingBean::getClient), averagingInt(ReadingBean::getReading)));

            System.out.println("| Client              | Month              | Suspicious         | Median");
            System.out.println(" -------------------------------------------------------------------------------");
            medianPerClient.forEach((k, v) -> {
                try {
                    printSuspiciusReadings(k, v, readings);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            throw new RuntimeException("Error during csv processing", e);
        }
    }

    private void printSuspiciusReadings(String client, Double median, List<ReadingBean> readingsBean) throws Exception {

        for (ReadingBean reading : readingsBean) {
            if(reading.getClient().equals(client) && (reading.getReading() < ((50 * median) / 100) || reading.getReading() > ((150 * median) / 100))){
                System.out.println("|"+reading.getClient()+"        "+"|"+reading.getPeriod()+"             |"+reading.getReading()+"               |"+ median);
            }
        }
    }

}
