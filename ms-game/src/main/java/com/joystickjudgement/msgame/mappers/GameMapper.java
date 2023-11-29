package com.joystickjudgement.msgame.mappers;

import com.joystickjudgement.msgame.dtos.GameDTO;
import com.joystickjudgement.msgame.entities.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GameMapper {

    @Mapping(target = "numberOfVisualizations", ignore = true)
    Game toDomain(final GameDTO dto);

    GameDTO toDTO(final Game game);

}
