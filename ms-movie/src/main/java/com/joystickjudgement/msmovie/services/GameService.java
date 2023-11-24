package com.joystickjudgement.msmovie.services;

import com.joystickjudgement.msmovie.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    @Transactional
    public void create(GameDTO dto) {
        var dto =
        gameRepository.save(game);
    }
}
