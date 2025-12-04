package com.test.test.domain.holiday.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HolidayResponseDto {

    private String date;
    private String localName;
    private String name;
    private String type;
    private boolean globalHoliday;
}
