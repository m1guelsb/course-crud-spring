package com.m1guelsb.crudspring.modules.course;

import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

  public CourseDTO toDTO(CourseEntity course) {
    if (course == null) {
      return null;
    }
    return new CourseDTO(course.getId(), course.getName(), course.getCategory());
  }

  public CourseEntity toEntity(CourseDTO courseDTO) {
    if (courseDTO == null) {
      return null;
    }

    CourseEntity course = new CourseEntity();
    if (courseDTO.id() != null) {
      course.setId(courseDTO.id());
    }
    course.setName(courseDTO.name());
    course.setCategory(courseDTO.category());

    return course;
  }
}
