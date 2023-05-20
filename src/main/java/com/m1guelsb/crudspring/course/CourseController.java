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

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CourseEntity create(@RequestBody @Valid CourseEntity courseDto) {
    return courseService.create(courseDto);
  }

  @GetMapping
  public List<CourseEntity> list() {
    return courseService.list();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseEntity> findById(@PathVariable @NotNull String id) {
    return courseService.findById(id)
        .map(course -> ResponseEntity.ok().body(course))
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<CourseEntity> update(@PathVariable @NotNull String id,
      @RequestBody @Valid CourseEntity courseDto) {
    return courseService.update(id, courseDto)
        .map(course -> ResponseEntity.ok().body(course))
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable @NotNull String id) {
    if (courseService.delete(id)) {
      return ResponseEntity.noContent().<Void>build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
