package com.joystickjudgement.msgame.mappers;

import com.joystickjudgement.msgame.dtos.GameDTO;
import com.joystickjudgement.msgame.entities.Game;
import org.mapstruct.Mapper;

@Mapper
public interface GameMapper {

    Game toDomain(final GameDTO dto);

    GameDTO toDTO(final Game game);

}
