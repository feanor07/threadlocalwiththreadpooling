package com.dilek.alptug.threadlocalwiththreadpooling.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.beans.Transient;

@Data
@AllArgsConstructor
public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
    private Title title;

    @Transient
    public String getFullName() {
        return firstName.concat(" ").concat(lastName);
    }
}
