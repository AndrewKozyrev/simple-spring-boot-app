package com.example.demo;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class User {
    private String name;
    private String surname;
    private Integer age;
    private Long id;
}
