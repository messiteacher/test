package com.test.test.domain.holiday.dto;

import lombok.Data;

@Data
public class HolidaySearchRequestDto {

    private String countryCode;
    private Integer year;
    private String type;
    private String from;
    private String to;
}
