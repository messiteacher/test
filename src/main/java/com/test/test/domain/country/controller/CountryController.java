package com.test.test.domain.country.controller;

import com.test.test.domain.country.entity.Country;
import com.test.test.domain.country.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<Country> getAll() {
        return countryService.findAll();
    }

    @PostMapping("/load")
    public String load() {

        countryService.loadAllCountries();
        return "로딩 완료!";
    }
}
