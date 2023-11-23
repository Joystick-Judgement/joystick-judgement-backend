package com.joystickjudgement.msmovie.entities;

import com.joystickjudgement.msmovie.enums.ReviewRating;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing(modifyOnCreate = false)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Review {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    @Getter
    private Long id;

    @Getter
    private String description;

    @Getter
    private ReviewRating rating;

    @Getter
    @Column(name = "upvotes")
    private Integer numberOfUpvotes;

    @Getter
    @Column(name = "downvotes")
    private Integer numberOfDownvotes;

    @CreatedDate
    @Column(name = "created_at")
    @Getter
    private final LocalDate createdAt = LocalDate.now();

}
