package application;

import domain.service.ReadingsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan({"domain.service"})
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
        readingsService.findSuspiciusReadings(args[0]);


    }

}
