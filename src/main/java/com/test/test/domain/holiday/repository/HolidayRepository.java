package com.test.test.domain.holiday.repository;

import com.test.test.domain.holiday.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long>, JpaSpecificationExecutor<Holiday> {

    List<Holiday> findByCountryCodeAndDateBetween(String countryCode, LocalDate start, LocalDate end);
    void deleteByCountryCodeAndDateBetween(String countryCode, LocalDate from, LocalDate to);
}
