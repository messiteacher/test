package com.test.test.domain.holiday.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HolidaySearchRequestDto {

    private String countryCode;
    private Integer year;
    private String type;
    private LocalDate from;
    private LocalDate to;

    private int page = 0;
    private int size = 20;
}
