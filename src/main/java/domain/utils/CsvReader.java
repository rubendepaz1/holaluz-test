package domain.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import domain.model.ReadingBean;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvReader {

    public static List<ReadingBean> beanBuilder(Path path, Class<? extends ReadingBean> clazz) throws Exception {
        CsvTransfer csvTransfer = new CsvTransfer();
        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean<ReadingBean> cb = new CsvToBeanBuilder<ReadingBean>(reader)
                    .withType(clazz)
                    .withSkipLines(1)
                    .build();

            csvTransfer.setCsvList(cb.parse());
        }

        return csvTransfer.getCsvList();
    }

}
