package com.test.test.global.scheduler;

import com.test.test.domain.holiday.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class HolidaySyncScheduler {

    private final HolidayService holidayService;

    @Scheduled(cron = "0 0 1 2 1 *", zone = "Asia/Seoul")
    public void autoRefresh() {

        int currentYear = LocalDate.now().getYear();
        int previousYear = currentYear - 1;

        String countryCode = "KR";

        holidayService.refresh(countryCode, previousYear);
        holidayService.refresh(countryCode, currentYear);

        System.out.println("자동 갱신 완료!");
    }
}
