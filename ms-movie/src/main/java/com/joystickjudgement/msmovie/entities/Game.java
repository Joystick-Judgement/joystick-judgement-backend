package com.joystickjudgement.msmovie.entities;

import com.joystickjudgement.msmovie.enums.GameGenre;
import com.joystickjudgement.msmovie.enums.GameParentalRating;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Game {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private String company;

    @Getter
    private Collection<GameGenre> genres;

    @Getter
    private String publisher;

    @Getter
    private LocalDate releaseDate;

    @Getter
    private GameParentalRating parentalRating;

    @Getter
    private BigInteger numberOfVisualizations;

    @Getter
    private BigInteger numberOfAssessments;

}
