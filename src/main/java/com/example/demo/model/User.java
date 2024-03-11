package com.example.demo.model;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class User {
    private Long id;
    private String name;
    private String surname;
    @ValidAge
    private Integer age;
}
