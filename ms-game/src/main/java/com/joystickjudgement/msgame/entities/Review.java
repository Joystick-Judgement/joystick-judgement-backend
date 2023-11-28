package com.joystickjudgement.msgame.entities;

import com.joystickjudgement.msgame.enums.ReviewRating;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public non-sealed class Review implements Auditable {

    @Getter
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Getter
    private String description;

    @Getter
    @Enumerated(EnumType.STRING)
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
