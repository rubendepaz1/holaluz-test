package domain.ports;

import domain.model.ReadingBean;

import java.io.File;
import java.util.List;

public interface FileParser {
    List<ReadingBean> parseFile(File myFile) throws Exception;
}
