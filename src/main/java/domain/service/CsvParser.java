package domain.service;

import domain.model.ReadingBean;
import domain.utils.CsvReader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvParser {

    public List<ReadingBean> parseCsv(File myFile) throws Exception {
        return CsvReader.beanBuilder(Paths.get(myFile.toURI()), ReadingBean.class);
    }
}
