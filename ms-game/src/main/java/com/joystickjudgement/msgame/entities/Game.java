package com.joystickjudgement.msgame.entities;

import com.joystickjudgement.msgame.enums.GameGenre;
import com.joystickjudgement.msgame.enums.GameParentalRating;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public non-sealed class Game implements Auditable {

    @Getter
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String company;

    @Getter
    @Enumerated(EnumType.STRING)
    private final Collection<GameGenre> genres = new ArrayList<>();

    @Getter
    @Setter
    private String publisher;

    @Getter
    @Setter
    private LocalDate releaseDate;

    @Column(name = "parental_rating")
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private GameParentalRating parentalRating;

    @Column(name = "visualizations")
    @Getter
    @Setter
    private BigInteger numberOfVisualizations;

    @OneToMany(orphanRemoval = true)
    @Getter
    private final Collection<Review> reviews = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at")
    @Getter
    private final LocalDate createdAt = LocalDate.now();

    @LastModifiedDate
    @Column(name = "updated_at")
    @Getter
    @Setter
    private LocalDate lastModifiedAt;

}
