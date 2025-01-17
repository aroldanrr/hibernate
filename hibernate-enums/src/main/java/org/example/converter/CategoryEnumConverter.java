package org.example.converter;

import org.example.model.EmployeeCategory;
import jakarta.persistence.AttributeConverter;

public class CategoryEnumConverter implements AttributeConverter<EmployeeCategory, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EmployeeCategory attribute) {
        if (attribute == null) {
            return null;
        }
        switch (attribute) {
            case JUNIOR:
                return 1;
            case SENIOR:
                return 2;
            case MANAGER:
                return 3;
            case C_LEVEL:
                return 4;
            default:
                throw new IllegalArgumentException("Unknown EmployeeCategory: " + attribute);
        }
    }

    @Override
    public EmployeeCategory convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        switch (dbData) {
            case 1:
                return EmployeeCategory.JUNIOR;
            case 2:
                return EmployeeCategory.SENIOR;
            case 3:
                return EmployeeCategory.MANAGER;
            case 4:
                return EmployeeCategory.C_LEVEL;
            default:
                throw new IllegalArgumentException("Unknown value: " + dbData);
        }
    }
}
