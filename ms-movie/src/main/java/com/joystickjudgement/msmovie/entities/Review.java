package com.joystickjudgement.msmovie.entities;

import com.joystickjudgement.msmovie.enums.ReviewRating;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private Integer numberOfUpvotes;

    @Getter
    private Integer numberOfDownvotes;

    @CreatedDate
    @Getter
    private final LocalDate createdAt = LocalDate.now();

}
