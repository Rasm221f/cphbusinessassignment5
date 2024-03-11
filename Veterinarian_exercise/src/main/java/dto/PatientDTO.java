package dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

public class PatientDTO {
    private String id;
    private String name;
    private String species;
}