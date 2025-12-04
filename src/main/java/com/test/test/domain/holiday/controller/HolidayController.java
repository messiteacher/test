package com.test.test.domain.holiday.controller;

import com.test.test.domain.holiday.entity.Holiday;
import com.test.test.domain.holiday.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/holidays")
public class HolidayController {

    private final HolidayService holidayService;

    @GetMapping
    public List<Holiday> search(@RequestParam String countryCode, @RequestParam String from, @RequestParam String to) {
        return holidayService.search(countryCode, from, to);
    }

    @DeleteMapping("/{year}/{country}")
    public void delete(@PathVariable String year, @PathVariable String countryCode) {
        holidayService.delete(year, countryCode);
    }
}
