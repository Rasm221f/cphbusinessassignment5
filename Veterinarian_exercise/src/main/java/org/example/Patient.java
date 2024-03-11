package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Patient {
    private String id;
    private String name;
    private String species;
}

