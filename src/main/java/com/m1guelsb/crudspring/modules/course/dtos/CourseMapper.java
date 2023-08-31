package com.m1guelsb.crudspring.modules.course.dtos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.m1guelsb.crudspring.modules.course.CourseEntity;
import com.m1guelsb.crudspring.modules.course.enums.CategoryEnum;
import com.m1guelsb.crudspring.modules.lesson.dtos.LessonDTO;

@Component
public class CourseMapper {

  public CourseDTO toDTO(CourseEntity course) {
    if (course == null) {
      return null;
    }
    List<LessonDTO> lessons = course.getLessons().stream()
        .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getSourceURL()))
        .collect(Collectors.toList());

    return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessons);
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
    course.setCategory(convertCategoryValue(courseDTO.category()));
    return course;
  }

  public CategoryEnum convertCategoryValue(String value) {
    if (value == null) {
      return null;
    }

    return switch (value) {
      case "Front-end" -> CategoryEnum.FRONT_END;
      case "Back-end" -> CategoryEnum.BACK_END;
      default -> throw new IllegalArgumentException("Categoria inválida: " + value);
    };
  }
}
