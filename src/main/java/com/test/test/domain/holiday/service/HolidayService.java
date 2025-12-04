package com.test.test.domain.holiday.service;

import com.test.test.domain.holiday.entity.Holiday;
import com.test.test.domain.holiday.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayService {

    private final HolidayRepository holidayRepository;

    public List<Holiday> search(String countryCode, String from, String to) {
        return holidayRepository.findByCountryCodeAndDateBetween(countryCode, from, to);
    }

    public void delete(String year, String countryCode) {
        holidayRepository.deleteByCountryCodeAndDateStartingWith(year, countryCode);
    }
}
