package com.rubendepaz.holaluztest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class HolaluzTestApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HolaluzTestApplication.class, args);
        System.out.println("Parsing csv...");

        try {
            List<ReadingBean> readingsBean = namedSyncColumnBeanExample();
            System.out.println("Parsing csv...");


        } catch (Exception e) {
            throw new RuntimeException("Error during csv processing", e);
        }

    }


    public static List<ReadingBean> namedSyncColumnBeanExample() throws Exception {
        URI uri = ClassLoader.getSystemResource("csv/2016-readings.csv").toURI();
        return CsvReader.beanBuilder(Paths.get(uri), ReadingBean.class);
    }

}
