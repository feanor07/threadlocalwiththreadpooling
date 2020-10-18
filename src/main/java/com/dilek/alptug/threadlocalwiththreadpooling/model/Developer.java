package com.dilek.alptug.threadlocalwiththreadpooling.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
    private Title title;
}
