package org.example;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Appointment {
    private String id;
    private String patientId;
    private String date;
    private String reason;


}

