package com.m1guelsb.crudspring.modules.course.enums.converters;

import java.util.stream.Stream;

import com.m1guelsb.crudspring.modules.course.enums.StatusEnum;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusEnum, String> {

  @Override
  public String convertToDatabaseColumn(StatusEnum statusEnum) {
    if (statusEnum == null) {
      return null;
    }

    return statusEnum.getValue();
  }

  @Override
  public StatusEnum convertToEntityAttribute(String dbValue) {
    if (dbValue == null) {
      return null;
    }

    return Stream.of(StatusEnum.values())
        .filter(status -> status.getValue().equals(dbValue))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
