package ru.naumen.naumen_schooldairy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;
import ru.naumen.naumen_schooldairy.data.entity.Subject;
import ru.naumen.naumen_schooldairy.data.mapper.subject.SubjectMapper;
import ru.naumen.naumen_schooldairy.data.repository.SubjectRepository;

import java.util.List;

/**
 * Сервис для управления предметами
 */
@Service
@RequiredArgsConstructor
public class SubjectService {

    /**
     * Репозиторий для работы с предметами
     */
    private final SubjectRepository subjectRepository;

    /**
     * Mapper для преобразования данных предметов
     */
    private final SubjectMapper subjectMapper;

    /**
     * Получает список уникальных предметов для указанного класса.
     *
     * @param classId идентификатор класса, для которого нужно получить предметы.
     * @return список DTO с информацией о предметах, доступных для указанного класса.
     */
    public List<ResponseSubjectDto> getSubjectsForClass(Long classId) {
        List<Subject> subjects = subjectRepository.findAllDistinctForClass(classId);

        return subjects.stream().map(subjectMapper::toResponseDto).toList();
    }
}
