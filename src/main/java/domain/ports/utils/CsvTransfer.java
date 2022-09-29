package domain.ports.utils;

import domain.model.ReadingBean;

import java.util.ArrayList;
import java.util.List;

public class CsvTransfer {

    private List<ReadingBean> csvList;

    public CsvTransfer() {}


    public void setCsvList(List<ReadingBean> csvList) {
        this.csvList = csvList;
    }

    public List<ReadingBean> getCsvList() {
        if (csvList != null) return csvList;
        return new ArrayList<ReadingBean>();
    }
}
