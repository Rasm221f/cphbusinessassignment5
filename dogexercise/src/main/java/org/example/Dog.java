package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Dog {
    private int id;
    private String name;
    private String breed;
    private int age;
}