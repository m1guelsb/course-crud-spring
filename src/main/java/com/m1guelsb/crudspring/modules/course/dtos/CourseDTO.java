package com.m1guelsb.crudspring.modules.course.dtos;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.m1guelsb.crudspring.modules.course.enums.CategoryEnum;
import com.m1guelsb.crudspring.modules.course.enums.validation.ValueOfEnum;
import com.m1guelsb.crudspring.modules.lesson.dtos.LessonDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
		String id,
		@NotBlank @NotNull @Length(min = 5, max = 100) String name,
		@NotNull @Length(max = 10) @ValueOfEnum(enumClass = CategoryEnum.class) String category,
		@NotNull @NotEmpty @Valid List<LessonDTO> lessons) {
}