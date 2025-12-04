package com.test.test.domain.holiday.dto;

import lombok.Data;

@Data
public class HolidayUpsertRequest {

    private String countryCode;
    private int year;
}
