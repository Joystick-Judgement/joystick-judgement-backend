package com.joystickjudgement.msgame.repositories;

import com.joystickjudgement.msgame.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> { }
