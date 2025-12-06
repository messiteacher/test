package com.test.test.global.dto;

import lombok.Data;

import java.util.List;

@Data
public class NagerHolidayDto {

    private String date;
    private String localName;
    private String name;
    private String countryCode;
    private boolean fixed;
    private boolean global;
    private List<String> types;
}
