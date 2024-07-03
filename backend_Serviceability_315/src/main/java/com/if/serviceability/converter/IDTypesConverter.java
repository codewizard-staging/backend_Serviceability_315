package com.if.serviceability.converter;

import com.if.serviceability.enums.IDTypes;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = false)
public class IDTypesConverter implements AttributeConverter<IDTypes, Integer> {

    @Override
    public Integer convertToDatabaseColumn(IDTypes iDTypes) {
        return iDTypes != null ? iDTypes.ordinal() : null;
    }

    @Override
    public IDTypes convertToEntityAttribute(Integer dbData) {
		return IDTypes.getIDTypes(dbData);
    }
}
