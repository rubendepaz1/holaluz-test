package com.rubendepaz.holaluztest;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;

public class ReadingBean {

    @CsvBindByPosition(position = 0)
    @Getter
    @Setter
    private String client;

    @CsvBindByPosition(position = 1)
    @Getter
    @Setter
    private String period;

    @CsvBindByPosition(position = 2)
    @Getter
    @Setter
    private int reading;
}
