package com.joystickjudgement.msgame.entities;

import jakarta.persistence.EntityListeners;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing(modifyOnCreate = false)
public sealed interface Auditable permits Game, Review { }
