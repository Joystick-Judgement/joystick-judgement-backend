package com.joystickjudgement.msmovie.services;

import com.joystickjudgement.msmovie.dtos.GameDTO;
import com.joystickjudgement.msmovie.mappers.GameMapper;
import com.joystickjudgement.msmovie.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    private final GameMapper gameMapper;

    @Transactional
    public EntityModel<GameDTO> create(GameDTO dto) {
        //add logs here
        var domain = gameRepository.save(gameMapper.toDomain(dto));
        return EntityModel.of(gameMapper.toDTO((domain)));
    }
}
