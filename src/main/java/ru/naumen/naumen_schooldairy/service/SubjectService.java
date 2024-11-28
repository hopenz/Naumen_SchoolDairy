package ru.naumen.naumen_schooldairy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.naumen.naumen_schooldairy.data.dto.subject.ResponseSubjectDto;
import ru.naumen.naumen_schooldairy.data.entity.Subject;
import ru.naumen.naumen_schooldairy.data.mapper.subject.SubjectMapper;
import ru.naumen.naumen_schooldairy.data.repository.SubjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;

    public List<ResponseSubjectDto> getSubjectsForClass(Long classId) {
        List<Subject> subjects = subjectRepository.findAllDistinctForClass(classId);

        return subjects.stream().map(subjectMapper::toResponseDto).toList();
    }
}
