package com.joystickjudgement.msmovie.services;

import com.joystickjudgement.msmovie.dtos.GameDTO;
import com.joystickjudgement.msmovie.mappers.GameMapper;
import com.joystickjudgement.msmovie.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository gameRepository;

    private final GameMapper gameMapper;

    @Transactional
    public EntityModel<GameDTO> create(GameDTO dto) {

        log.info("[START] -> Creating a game in: {}, starting mapper DTO to domain", GameService.class.getSimpleName());

        var dtoToDomain = gameMapper.toDomain(dto);

        log.info(
                "[MIDDLE] -> DTO to domain mapper finished: transformed data: {}, starting save in database",
                dtoToDomain
        );

        var domain = gameRepository.save(dtoToDomain);

        log.info(
                "[MIDDLE] -> Game saved in the database with success! game data: {}, starting to mapper domain to DTO"
                , domain
        );

        var result = gameMapper.toDTO(domain);

        log.info("[END] -> Domain to DTO transformation result: {}", result);

        return EntityModel.of(result);
    }
}
