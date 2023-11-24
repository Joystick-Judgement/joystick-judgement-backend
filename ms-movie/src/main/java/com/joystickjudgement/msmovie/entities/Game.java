package com.joystickjudgement.msmovie.entities;

import com.joystickjudgement.msmovie.enums.GameGenre;
import com.joystickjudgement.msmovie.enums.GameParentalRating;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@ToString
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing(modifyOnCreate = false)
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
    @Column(name = "parental_rating")
    private GameParentalRating parentalRating;

    @Getter
    @Column(name = "visualizations")
    private BigInteger numberOfVisualizations;

    @Getter
    @OneToMany(orphanRemoval = true)
    private final Collection<Review> reviews = new ArrayList<>();

    @CreatedDate
    @Getter
    @Column(name = "created_at")
    private final LocalDate createdAt = LocalDate.now();

    @LastModifiedDate
    @Getter
    @Column(name = "updated_at")
    private LocalDate lastModifiedAt;

}
