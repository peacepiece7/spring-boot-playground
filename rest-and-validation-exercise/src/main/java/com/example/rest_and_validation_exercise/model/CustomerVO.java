package com.example.rest_and_validation_exercise.model;


import com.example.rest_and_validation_exercise.annotation.KoreanOnly;
import com.example.rest_and_validation_exercise.annotation.PastOrPresentYear;
import com.example.rest_and_validation_exercise.annotation.PhoneNumber;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // @CHECK POINT!
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerVO {

    @NotBlank
    @KoreanOnly
    @EqualsAndHashCode.Include
    private String name;

    @NotBlank
    @PhoneNumber
    @EqualsAndHashCode.Include
    private String phoneNumber;

    @NotBlank
    @KoreanOnly
    @EqualsAndHashCode.Include
    private String animalName;

    @NotBlank
    private String address;

    @NotBlank
    @KoreanOnly
    private String kind;

    @PastOrPresentYear
    @Min(1950)
    private int yearOfBirth;
};