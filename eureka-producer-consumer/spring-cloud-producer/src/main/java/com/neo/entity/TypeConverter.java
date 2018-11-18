package com.neo.entity;

import javax.persistence.AttributeConverter;

public class TypeConverter implements AttributeConverter<Type, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Type type) {
        return type.getCode();
    }

    @Override
    public Type convertToEntityAttribute(Integer integer) {
        return Type.fromCode(integer);
    }
}
