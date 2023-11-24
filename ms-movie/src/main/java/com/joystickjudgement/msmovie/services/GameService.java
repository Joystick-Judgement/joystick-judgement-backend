package com.joystickjudgement.msmovie.services;

import com.joystickjudgement.msmovie.dtos.GameDTO;
import com.joystickjudgement.msmovie.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

//    @Transactional
//    public EntityModel<GameDTO> create(GameDTO dto) {
//        var domain = gameRepository.save(dto.toDomain());
//        return EntityModel.of(GameDTO.fromDomain(domain));
//    }
}
