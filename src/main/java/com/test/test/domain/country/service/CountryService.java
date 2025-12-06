package com.test.test.domain.country.service;

import com.test.test.domain.country.entity.Country;
import com.test.test.domain.country.repository.CountryRepository;
import com.test.test.global.dto.NagerCountryDto;
import com.test.test.global.util.NagerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final NagerClient nagerClient;

    public void loadAllCountries() {

        List<NagerCountryDto> dtos = nagerClient.fetchAvailableCountries();

        List<Country> countries = dtos.stream()
                .map(dto -> Country.builder()
                        .countryCode(dto.getCountryCode())
                        .name(dto.getName())
                        .build())
                .toList();

        countryRepository.saveAll(countries);
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country findByCountryCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode)
                .orElseThrow(() -> new IllegalArgumentException("국가 코드 없음: " + countryCode));
    }
}
