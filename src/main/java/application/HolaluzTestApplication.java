package application;

import domain.ports.CsvParser;
import domain.ports.FileParser;
import domain.ports.ReadingsService;
import domain.ports.XmlParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

import java.io.File;

@SpringBootApplication
@ComponentScan({"domain.ports"})
public class HolaluzTestApplication{

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("HolaluzTestApplication: Application needs readings file as an argument");
            System.exit(0);
        }else {
            System.out.println("HolaluzTestApplication: Application started with args: "+args[0]);
        }
        File myFile = new File(args[0]);
        if(!myFile.exists()) {
            System.out.println("HolaluzTestApplication: Unable to find "+myFile+" file");
            System.exit(0);
        }

        ApplicationContext applicationContext = SpringApplication.run(HolaluzTestApplication.class, args);
        ReadingsService readingsService = applicationContext.getBean(ReadingsService.class);
        FileParser fileParser;
        if (StringUtils.getFilenameExtension(myFile.getPath()).equals("csv")){
            fileParser = new CsvParser();
            readingsService.findSuspiciusReadingsFromFile(args[0], fileParser);
            System.out.println("Parsing CSV...");
        }
        if (StringUtils.getFilenameExtension(myFile.getPath()).equals("xml")){
            fileParser = new XmlParser();
            readingsService.findSuspiciusReadingsFromFile(args[0], fileParser);
            System.out.println("Parsing XML...");
        }

    }

}
