package com.test.test.domain.country.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryResponseDto {

    private String code;
    private String name;
}
