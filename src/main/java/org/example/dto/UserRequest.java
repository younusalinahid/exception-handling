package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "username should not be null")
    private String name;
    @Email(message = "invalid email address")
    private String email;
    @Pattern(regexp = "\\d{10}$",message = "invalid mobile number entered")
    private String mobile;
    private String gender;
    @Min(18)
    @Max(70)
    private int age;
    @NotBlank(message = "must not be blank")
    private String nationality;
}
