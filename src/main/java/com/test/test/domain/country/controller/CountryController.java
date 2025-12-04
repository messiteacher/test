package com.test.test.domain.country.controller;

import com.test.test.domain.country.entity.Country;
import com.test.test.domain.country.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<Country> getAll() {
        return countryService.findAll();
    }
}
