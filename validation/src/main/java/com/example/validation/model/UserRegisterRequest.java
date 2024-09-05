package com.example.validation.model;

import com.example.validation.annotation.PhoneNumber;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterRequest {
    // @NotNull  != null
    // @NotEmpty != null & != ""

    private String name;
    private String nickName;

    @Size(min = 1, max = 12)
    @NotBlank
    private String password;

    @NotNull // 문자가 아니라서 @NotBlack 안됨!
    @Min(1)
    @Max(100)
    private Integer age;

    @Email()
    private String email;

    // @Pattern(regexp = "^01[0-9]-\\d{3,4}-\\d{4}$", message = "invalid phone number format")
    @PhoneNumber
    private String phoneNumber;


    @FutureOrPresent
    private LocalDateTime registerAt;


    // 복합 유효성 체크
    // 조건: name 또는 nickName 이 있어야 한다.
    // 반드시 is 로 시작 해야 한다.
    @AssertTrue(message = "name or nickName 은 반드시 1개가 존재해야합니다.")
    public boolean isNameCheck() {
      if(Objects.nonNull(name) && !name.isBlank())  {
          return true;
      }

      if(Objects.nonNull(nickName) && !nickName.isBlank()) {
          return true;

      }

      return false;
    };
}

