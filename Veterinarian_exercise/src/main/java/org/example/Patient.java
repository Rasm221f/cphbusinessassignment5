package org.example;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

public class Patient {
    private String id;
    private String name;
    private String species;
}

