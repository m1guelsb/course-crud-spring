package com.m1guelsb.crudspring.modules.course;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(

                String id,

                @NotNull @NotBlank @Length(min = 3, max = 100) String name,

                @NotNull @Length(max = 10) String category) {
}
