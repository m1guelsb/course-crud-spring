package com.m1guelsb.crudspring.modules.course;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.m1guelsb.crudspring.exception.NotFoundException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class CourseService {
  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
    this.courseRepository = courseRepository;
    this.courseMapper = courseMapper;
  }

  public List<CourseDTO> findAll() {
    return courseRepository.findAll()
        .stream()
        .map(courseMapper::toDTO)
        .collect(Collectors.toList());
  }

  public CourseDTO findById(@PathVariable @NotNull String id) {
    return courseRepository.findById(id).map(courseMapper::toDTO).orElseThrow(() -> new NotFoundException(id));
  }

  public CourseDTO create(@Valid @NotNull CourseDTO courseReqDTO) {
    return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(courseReqDTO)));
  }

  public CourseDTO update(@NotNull String id, @Valid @NotNull CourseDTO courseReqDTO) {
    return courseRepository.findById(id)
        .map(course -> {
          course.setName(courseReqDTO.name());
          course.setCategory(courseReqDTO.name());
          return courseRepository.save(course);
        }).map(courseMapper::toDTO).orElseThrow(() -> new NotFoundException(id));
  }

  public void delete(@PathVariable @NotNull String id) {
    courseRepository.delete(courseRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(id)));
  }

}
