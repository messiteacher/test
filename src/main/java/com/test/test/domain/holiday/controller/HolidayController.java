package com.test.test.domain.holiday.controller;

import com.test.test.domain.holiday.entity.Holiday;
import com.test.test.domain.holiday.service.HolidayService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public List<Holiday> search(@RequestParam String countryCode, @RequestParam String from, @RequestParam String to) {
        return holidayService.search(countryCode, from, to);
    }

    @DeleteMapping("/{year}/{countryCode}")
    public String delete(@PathVariable String year, @PathVariable String countryCode) {

        holidayService.delete(year, countryCode);
        return "삭제 완료!";
    }
}
