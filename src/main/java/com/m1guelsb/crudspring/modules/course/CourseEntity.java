package com.m1guelsb.crudspring.modules.course;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.m1guelsb.crudspring.modules.course.enums.CategoryEnum;
import com.m1guelsb.crudspring.modules.course.enums.StatusEnum;
import com.m1guelsb.crudspring.modules.course.enums.converters.CategoryConverter;
import com.m1guelsb.crudspring.modules.course.enums.converters.StatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
@SQLDelete(sql = "UPDATE courses SET status = 'DISABLED' WHERE id = ?")
@Where(clause = "status = 'ENABLED'")
public class CourseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotBlank
  @NotNull
  @Length(min = 3, max = 100)
  @Column(length = 100, nullable = false)
  private String name;

  @NotNull
  @Column(length = 10, nullable = false)
  @Convert(converter = CategoryConverter.class)
  private CategoryEnum category;

  @NotNull
  @Column(length = 10, nullable = false)
  @Convert(converter = StatusConverter.class)
  private StatusEnum status = StatusEnum.ENABLED;
}