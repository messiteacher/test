package com.test.test.domain.holiday.repository;

import com.test.test.domain.holiday.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;

public interface HolidayRepository extends JpaRepository<Holiday, Long>, JpaSpecificationExecutor<Holiday> {

    void deleteByCountryCodeAndDateBetween(String countryCode, LocalDate from, LocalDate to);
}
