package com.m1guelsb.crudspring.course;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
@SQLDelete(sql = "UPDATE courses SET status = 'DISABLED' WHERE id = ?")
public class CourseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotBlank
  @NotNull
  @Length(min = 3, max = 100)
  @Column(length = 100, nullable = false)
  private String name;

  @NotBlank
  @NotNull
  @Length(max = 10)
  @Column(length = 10, nullable = false)
  private String category;
}