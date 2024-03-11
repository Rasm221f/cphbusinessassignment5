package dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

public class AppointmentDTO {
    private String id;
    private String date;
    private String patientId;
    private String reason;
}
