package domain.ports;

import domain.model.ReadingBean;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingInt;

@Service
@ComponentScan({"domain.ports"})
@NoArgsConstructor
public class ReadingsService {

    public void findSuspiciusReadingsFromFile(String args, FileParser fileParser){
        try {

            File myFile = new File(args);
            List<ReadingBean> readings = null;
            readings = fileParser.parseFile(myFile);

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
