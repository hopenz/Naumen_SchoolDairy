package ru.naumen.naumen_schooldairy.service.specification;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import ru.naumen.naumen_schooldairy.data.entity.DailySchedule;
import ru.naumen.naumen_schooldairy.data.entity.Lesson;
import ru.naumen.naumen_schooldairy.data.entity.SchoolClass;
import ru.naumen.naumen_schooldairy.data.entity.Subject;

public class DailyScheduleSpecification {

    public static Specification<DailySchedule> hasClassId(Long classId) {
        return (root, query, criteriaBuilder) -> {
            if (classId == null) return null;
            Join<DailySchedule, SchoolClass> classJoin = root.join("classField");
            return criteriaBuilder.equal(classJoin.get("id"), classId);
        };
    }

    public static Specification<DailySchedule> hasSubjectId(Long subjectId) {
        return (root, query, criteriaBuilder) -> {
            if (subjectId == null) return null;
            Join<DailySchedule, Lesson> lessonJoin = root.join("lessons");
            Join<Lesson, Subject> subjectJoin = lessonJoin.join("subject");
            return criteriaBuilder.equal(subjectJoin.get("id"), subjectId);
        };
    }
}
