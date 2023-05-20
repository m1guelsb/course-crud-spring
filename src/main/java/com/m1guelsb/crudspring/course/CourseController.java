package com.m1guelsb.crudspring.course;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {
  private final CourseRepository courseRepository;

  public CourseController(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CourseEntity create(@RequestBody @Valid CourseEntity courseDto) {
    return courseRepository.save(courseDto);
    // return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
  }

  @GetMapping
  public List<CourseEntity> list() {
    return courseRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseEntity> findById(@PathVariable @NotNull String id) {
    return courseRepository.findById(id).map(course -> ResponseEntity.ok().body(course))
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<CourseEntity> update(@PathVariable @NotNull String id,
      @RequestBody @Valid CourseEntity courseDto) {
    return courseRepository.findById(id)
        .map(course -> {
          course.setName(courseDto.getName());
          course.setCategory(courseDto.getCategory());
          CourseEntity updatedCourse = courseRepository.save(course);
          return ResponseEntity.ok().body(updatedCourse);
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable @NotNull String id) {
    return courseRepository.findById(id).map(course -> {
      courseRepository.deleteById(id);
      return ResponseEntity.noContent().<Void>build();
    })
        .orElse(ResponseEntity.notFound().build());
  }
}
