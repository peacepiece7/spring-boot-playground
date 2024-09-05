package com.example.rest_and_validation_exercise.model;


import com.example.rest_and_validation_exercise.annotation.KoreanOnly;
import com.example.rest_and_validation_exercise.annotation.PastOrPresentYear;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MedicalRecordVO {

    @DateTimeFormat // ISO 8601 YYYY-MM-DDThh:hh:ss
    private String treatmentDate;

    @NotBlank
    private String treatmentDetails;

    @NotBlank
    @KoreanOnly
    private String owner;

    @NotBlank
    @KoreanOnly
    private String animalName;

    @NotBlank
    @KoreanOnly
    private String kind;

    @PastOrPresentYear
    private int yearOfBirth;
}
