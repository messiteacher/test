package com.test.test.domain.holiday.spec;

import com.test.test.domain.holiday.dto.HolidaySearchRequestDto;
import com.test.test.domain.holiday.entity.Holiday;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HolidaySpecification {

    public static Specification<Holiday> search(HolidaySearchRequestDto req) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (req.getCountryCode() != null) {
                predicates.add(cb.equal(root.get("countryCode"), req.getCountryCode()));
            }

            if (req.getYear() != null) {
                LocalDate start = LocalDate.of(req.getYear(), 1, 1);
                LocalDate end = LocalDate.of(req.getYear(), 12, 31);
                predicates.add(cb.between(root.get("date"), start, end));
            }

            if (req.getType() != null) {
                predicates.add(cb.like(root.get("types"), "%" + req.getType() + "%"));
            }

            if (req.getFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("date"), req.getFrom()));
            }

            if (req.getTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("date"), req.getTo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
