package com.test.test.domain.holiday.service;

import com.test.test.domain.country.entity.Country;
import com.test.test.domain.country.service.CountryService;
import com.test.test.domain.holiday.entity.Holiday;
import com.test.test.domain.holiday.repository.HolidayRepository;
import com.test.test.global.dto.NagerHolidayDto;
import com.test.test.global.util.NagerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final CountryService countryService;
    private final NagerClient nagerClient;

    public void loadHolidaysForCountry(String countryCode, int year) {

        List<NagerHolidayDto> dtos = nagerClient.fetchHolidays(countryCode, year);
        Country country = countryService.findByCountryCode(countryCode);

        List<Holiday> holidays = dtos.stream()
                .map(dto -> Holiday.builder()
                        .date(LocalDate.parse(dto.getDate()))
                        .localName(dto.getLocalName())
                        .name(dto.getName())
                        .countryCode(dto.getCountryCode())
                        .fixed(dto.isFixed())
                        .global(dto.isGlobal())
                        .countries(null)
                        .launchYear(null)
                        .types(String.join(",", dto.getTypes())) // List<String> → String
                        .build())
                .toList();

        holidayRepository.saveAll(holidays);
    }

    public List<Holiday> search(String countryCode, String from, String to) {

        LocalDate start = LocalDate.parse(from);
        LocalDate end = LocalDate.parse(to);
        return holidayRepository.findByCountryCodeAndDateBetween(countryCode, start, end);
    }

    @Transactional
    public void delete(String year, String countryCode) {

        int y = Integer.parseInt(year);

        LocalDate start = LocalDate.of(y, 1, 1);
        LocalDate end = LocalDate.of(y, 12, 31);

        holidayRepository.deleteByCountryCodeAndDateBetween(countryCode, start, end);
    }
}
