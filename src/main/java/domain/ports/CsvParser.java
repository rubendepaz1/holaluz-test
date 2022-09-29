package domain.ports;

import domain.model.ReadingBean;
import domain.ports.utils.CsvReader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvParser implements FileParser {

    @Override
    public List<ReadingBean> parseFile(File myFile) throws Exception {
        return CsvReader.beanBuilder(Paths.get(myFile.toURI()), ReadingBean.class);
    }
}
