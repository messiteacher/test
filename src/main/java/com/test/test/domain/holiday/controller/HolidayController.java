package com.test.test.domain.holiday.controller;

import com.test.test.domain.holiday.dto.HolidaySearchRequestDto;
import com.test.test.domain.holiday.entity.Holiday;
import com.test.test.domain.holiday.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/holidays")
public class HolidayController {

    private final HolidayService holidayService;

    @PostMapping("/load")
    public String load(@RequestParam String countryCode, @RequestParam int year) {

        holidayService.loadHolidaysForCountry(countryCode, year);
        return "로딩 완료!";
    }

    @GetMapping("/search")
    public Page<Holiday> search(HolidaySearchRequestDto req) {
        return holidayService.search(req);
    }

    @PostMapping("/refresh")
    public List<Holiday> refresh(@RequestParam String countryCode, @RequestParam int year) {
        return holidayService.refresh(countryCode, year);
    }

    @DeleteMapping("/{year}/{countryCode}")
    public String delete(@PathVariable String year, @PathVariable String countryCode) {

        holidayService.delete(year, countryCode);
        return "삭제 완료!";
    }
}
