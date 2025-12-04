package com.test.test.domain.holiday.repository;

import com.test.test.domain.holiday.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    List<Holiday> findByCountryCodeAndDateBetween(String countryCode, String from, String to);
    void deleteByCountryCodeAndDateStartingWith(String year, String countryCode);
}
