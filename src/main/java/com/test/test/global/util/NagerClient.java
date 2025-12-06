package com.test.test.global.util;

import com.test.test.global.dto.NagerCountryDto;
import com.test.test.global.dto.NagerHolidayDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NagerClient {

    private final WebClient webClient;

    public List<NagerCountryDto> fetchAvailableCountries() {

        return webClient.get()
                .uri("/AvailableCountries")
                .retrieve()
                .bodyToFlux(NagerCountryDto.class)
                .collectList()
                .block();
    }

    public List<NagerHolidayDto> fetchHolidays(String countryCode, int year) {

        return webClient.get()
                .uri("/PublicHolidays/{year}/{country}", year, countryCode)
                .retrieve()
                .bodyToFlux(NagerHolidayDto.class)
                .collectList()
                .block();
    }
}
