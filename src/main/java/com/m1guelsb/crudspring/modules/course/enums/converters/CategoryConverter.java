package com.m1guelsb.crudspring.modules.course.enums.converters;

import java.util.stream.Stream;

import com.m1guelsb.crudspring.modules.course.enums.CategoryEnum;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<CategoryEnum, String> {

  @Override
  public String convertToDatabaseColumn(CategoryEnum categoryEnum) {
    if (categoryEnum == null) {
      return null;
    }

    return categoryEnum.getValue();
  }

  @Override
  public CategoryEnum convertToEntityAttribute(String dbValue) {
    if (dbValue == null) {
      return null;
    }

    return Stream.of(CategoryEnum.values())
        .filter(category -> category.getValue().equals(dbValue))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
