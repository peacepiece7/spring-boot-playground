package com.yearly.idol.api.yearly_idol.User.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yearly.idol.api.yearly_idol.Common.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserLocalSignUpDto {
    @Email
    private String email;

    @NotBlank
    private String userName;

    @Password
    private String password;
}
