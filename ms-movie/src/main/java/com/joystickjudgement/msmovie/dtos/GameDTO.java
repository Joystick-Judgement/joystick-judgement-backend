package com.joystickjudgement.msmovie.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.joystickjudgement.msmovie.enums.GameGenre;
import com.joystickjudgement.msmovie.enums.GameParentalRating;
import jakarta.validation.constraints.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public record GameDTO(

        @JsonProperty(access = READ_ONLY)
        Long id,

        @NotBlank
        @Size(min = 1, max = 100)
        String name,

        @NotBlank
        @Size(min = 5, max = 600)
        String description,

        @NotBlank
        @Size(min = 1, max = 60)
        String company,

        @NotEmpty
        Collection<GameGenre> genres,
        @NotEmpty
        @Size(min = 1, max = 60)
        String publisher,
        @NotNull
        LocalDate releaseDate,

        @NotNull
        GameParentalRating parentalRating,
        @PositiveOrZero
        @NotNull
        BigInteger numberOfVisualizations
) {}
