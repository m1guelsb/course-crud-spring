package com.m1guelsb.crudspring.course;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class CourseService {
  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public CourseEntity create(@Valid CourseEntity courseDto) {
    return courseRepository.save(courseDto);
  }

  public List<CourseEntity> list() {
    return courseRepository.findAll();
  }

  public Optional<CourseEntity> findById(@PathVariable @NotNull String id) {
    return courseRepository.findById(id);
  }

  public Optional<CourseEntity> update(@NotNull String id, @Valid CourseEntity courseDto) {
    return courseRepository.findById(id)
        .map(course -> {
          course.setName(courseDto.getName());
          course.setCategory(courseDto.getCategory());
          return courseRepository.save(course);
        });
  }

  public boolean delete(@PathVariable @NotNull String id) {
    return courseRepository.findById(id).map(course -> {
      courseRepository.deleteById(id);
      return true;
    }).orElse(false);
  }

}
