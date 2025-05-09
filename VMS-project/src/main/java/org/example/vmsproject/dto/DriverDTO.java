package org.example.vmsproject.dto;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String licenseNumber;
    private String phoneNumber;
}
