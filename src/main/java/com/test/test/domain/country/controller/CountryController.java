package com.test.test.domain.country.controller;

import com.test.test.domain.country.entity.Country;
import com.test.test.domain.country.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
@Tag(name = "Country API", description = "국가 데이터 조회 및 관리 API")
public class CountryController {

    private final CountryService countryService;

    @Operation(summary = "전체 국가 데이터 조회", description = "DB에 저장된 모든 국가 목록을 반환합니다.")
    @GetMapping
    public List<Country> getAll() {
        return countryService.findAll();
    }

    @Operation(summary = "국가 데이터 로딩", description = "외부 API에서 전체 국가 목록을 불러와 저장합니다.")
    @PostMapping("/load")
    public String load() {

        countryService.loadAllCountries();
        return "로딩 완료!";
    }
}
