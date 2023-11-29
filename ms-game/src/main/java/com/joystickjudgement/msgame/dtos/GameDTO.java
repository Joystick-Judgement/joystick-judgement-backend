package com.joystickjudgement.msgame.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.joystickjudgement.msgame.enums.GameGenre;
import com.joystickjudgement.msgame.enums.GameParentalRating;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record GameDTO(

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Game's unique identifier", example = "1")
        Long id,

        @NotBlank
        @Size(max = 100)
        @Schema(description = "Game's name", example = "The Last of Us")
        String name,

        @NotBlank
        @Size(max = 600)
        @Schema(
                description = "Game's description",
                example = "The Last of Us is a 2013 action-adventure game developed by Naughty Dog and " +
                        "published by Sony Computer Entertainment."
        )
        String description,

        @NotBlank
        @Size(max = 60)
        @Schema(description = "Game's company", example = "Naughty Dog")
        String company,

        @NotEmpty
        @Schema(description = "Game's genres", examples = {"ACTION", "ADVENTURE"})
        Collection<GameGenre> genres,
        @NotEmpty
        @Size(max = 60)
        @Schema(description = "Game's publisher", example = "Sony Computer Entertainment")
        String publisher,

        @JsonFormat(pattern = "yyyy-MM-dd", shape = STRING)
        @NotNull
        @Schema(description = "Game's release date", example = "2013-06-14")
        LocalDate releaseDate,

        @NotNull
        @Schema(description = "Game's parental rating", example = "MATURE")
        GameParentalRating parentalRating,

        @Schema(accessMode = Schema.AccessMode.READ_ONLY,description = "Game's number of visualizations", example = "100")
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        BigInteger numberOfVisualizations
) {
}
