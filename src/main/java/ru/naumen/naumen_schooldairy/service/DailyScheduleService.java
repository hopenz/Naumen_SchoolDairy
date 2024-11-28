package ru.naumen.naumen_schooldairy.service;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.RequestDailyScheduleDto;
import ru.naumen.naumen_schooldairy.data.dto.dailySchedule.ResponseDatesOfLessonsDto;
import ru.naumen.naumen_schooldairy.data.dto.lesson.RequestLessonDto;
import ru.naumen.naumen_schooldairy.data.entity.*;
import ru.naumen.naumen_schooldairy.data.mapper.dailySchedule.DailyScheduleMapper;
import ru.naumen.naumen_schooldairy.data.repository.DailyScheduleRepository;
import ru.naumen.naumen_schooldairy.data.repository.SchoolClassRepository;
import ru.naumen.naumen_schooldairy.data.repository.SubjectRepository;
import ru.naumen.naumen_schooldairy.exception.EntityNotFoundException;
import ru.naumen.naumen_schooldairy.service.specification.DailyScheduleSpecification;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DailyScheduleService {

    private final DailyScheduleMapper dailyScheduleMapper;

    private final DailyScheduleRepository dailyScheduleRepository;

    private final SchoolClassRepository classRepository;

    private final SubjectRepository subjectRepository;

    @Transactional
    public List<ResponseDatesOfLessonsDto> getDatesOfLessons(Long classId, Long subjectId) {
        Specification<DailySchedule> dailyScheduleSpecification = Specification
                .where(DailyScheduleSpecification.hasClassId(classId))
                .and(DailyScheduleSpecification.hasSubjectId(subjectId));
        List<DailySchedule> dailySchedules = dailyScheduleRepository.findAll(dailyScheduleSpecification);

        return dailySchedules.stream().map(dailyScheduleMapper::toResponseDatesOfLessonsDto).toList();
    }

    @Transactional
    public void createSchedule(RequestDailyScheduleDto schedule, @NotNull @Positive Long classId) {
        DailySchedule newSchedule = new DailySchedule();
        SchoolClass schoolClass = classRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class", classId));

        Set<Lesson> lessons = new HashSet<>();
        for (RequestLessonDto lessonDto : schedule.lessons()) {
            Lesson lesson = new Lesson();
            lesson.setLessonNumber(lessonDto.lessonNumber());
            lesson.setDailySchedule(newSchedule);
            Subject subject = subjectRepository.findByName(lessonDto.subjectName());
            if (subject != null) {
                lesson.setSubject(subject);
            } else {
                Subject subject1 = new Subject();
                subject1.setName(lessonDto.subjectName());
                subject1.setLessons(lessons);
                lesson.setSubject(subject1);
            }

            if (lessonDto.homeworkDescription() != null) {
                Homework homework = new Homework();
                homework.setDescription(lessonDto.homeworkDescription());
                homework.setLesson(lesson);
                lesson.setHomework(Set.of(homework));
            }

            lessons.add(lesson);
        }

        newSchedule.setClassField(schoolClass);
        newSchedule.setLessons(lessons);
        newSchedule.setDateDay(schedule.dateDay());
        newSchedule.setDayOfWeek(schedule.dayOfWeek());

        dailyScheduleRepository.save(newSchedule);
    }
}
