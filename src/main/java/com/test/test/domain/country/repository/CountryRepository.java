package com.test.test.domain.country.repository;

import com.test.test.domain.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, String> {
    Optional<Country> findByCountryCode(String countryCode);
}
