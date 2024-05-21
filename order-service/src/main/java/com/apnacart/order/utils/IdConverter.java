package com.apnacart.order.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class IdConverter implements AttributeConverter<List<Long>, String> {
    @Override
    public String convertToDatabaseColumn(List<Long> floats) {
        return "TREST";//floats.stream().map(String::valueOf).reduce((a,b)-> a+","+b).orElse(null);
    }

    @Override
    public List<Long> convertToEntityAttribute(String s) {
        String[] sArray = s.split(", ");
        if(s.isEmpty()||s==""){
            return new ArrayList<>();
        }
        return Arrays.stream(sArray).map(Long::valueOf).collect(Collectors.toList());
    }
}
