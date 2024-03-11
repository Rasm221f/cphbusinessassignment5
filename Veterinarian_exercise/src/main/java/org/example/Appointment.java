package org.example;


import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

public class Appointment {
    private String id;
    private String patientId;
    private String date;
    private String reason;


}

