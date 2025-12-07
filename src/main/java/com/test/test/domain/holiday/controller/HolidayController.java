package com.test.test.domain.holiday.controller;

import com.test.test.domain.holiday.dto.HolidaySearchRequestDto;
import com.test.test.domain.holiday.entity.Holiday;
import com.test.test.domain.holiday.service.HolidayService;
import com.test.test.global.scheduler.HolidaySyncScheduler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/holidays")
@Tag(name = "Holiday API", description = "공휴일 데이터 조회 및 관리 API")
public class HolidayController {

    private final HolidayService holidayService;
    private final HolidaySyncScheduler holidaySyncScheduler;

    @Operation(summary = "공휴일 데이터 로딩", description = "특정 국가 코드와 연도를 기반으로 공휴일 데이터를 외부 API에서 불러와 저장합니다.")
    @PostMapping("/load")
    public String load(@RequestParam String countryCode, @RequestParam int year) {

        holidayService.loadHolidaysForCountry(countryCode, year);
        return "로딩 완료!";
    }

    @Operation(summary = "공휴일 검색", description = "국가, 연도, 월 등 조건을 기반으로 공휴일을 검색합니다.")
    @GetMapping("/search")
    public Page<Holiday> search(HolidaySearchRequestDto req) {
        return holidayService.search(req);
    }

    @Operation(summary = "공휴일 데이터 새로고침", description = "해당 국가와 연도의 기존 데이터를 삭제하고 외부 API에서 다시 불러옵니다.")
    @PostMapping("/refresh")
    public List<Holiday> refresh(@RequestParam String countryCode, @RequestParam int year) {
        return holidayService.refresh(countryCode, year);
    }

    @Operation(summary = "공휴일 데이터 삭제", description = "특정 국가와 연도의 공휴일 데이터를 모두 삭제합니다.")
    @DeleteMapping("/{year}/{countryCode}")
    public String delete(@PathVariable String year, @PathVariable String countryCode) {

        holidayService.delete(year, countryCode);
        return "삭제 완료!";
    }

    @Operation(summary = "공휴일 자동 배치 수동 실행", description = "스케줄러에서 실행되는 전체 holiday 업데이트 작업을 수동으로 실행합니다.")
    @PostMapping("/batch/run")
    public String runBatchManually() {

        int currentYear = LocalDate.now().getYear();
        int previousYear = currentYear - 1;

        String countryCode = "KR";

        holidayService.refresh(countryCode, previousYear);
        holidayService.refresh(countryCode, currentYear);

        return "배치 수동 실행 완료!";
    }
}
