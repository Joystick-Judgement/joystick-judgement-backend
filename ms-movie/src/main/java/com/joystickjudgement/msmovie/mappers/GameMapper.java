package com.joystickjudgement.msmovie.mappers;

import com.joystickjudgement.msmovie.dtos.GameDTO;
import com.joystickjudgement.msmovie.entities.Game;
import org.mapstruct.Mapper;

@Mapper
public interface GameMapper {

    Game toDomain(final GameDTO dto);

    GameDTO toDTO(final Game game);

}
