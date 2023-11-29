package com.joystickjudgement.msgame.resources;

import com.joystickjudgement.msgame.dtos.GameDTO;
import com.joystickjudgement.msgame.resources.docs.GameResourceDocs;
import com.joystickjudgement.msgame.services.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.joystickjudgement.msgame.core.entities.GlobalConstantsSingleton.GAME_RESOURCE_BASE_PATH;

@RestController
@RequestMapping(GAME_RESOURCE_BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class GameResource implements GameResourceDocs {

    private final GameService gameService;

    @PostMapping
    @Override
    public ResponseEntity<EntityModel<GameDTO>> create(@RequestBody @Valid GameDTO dto) {

        log.info("[START] -> Creating a game with data: {}", dto);

        var game = gameService.create(dto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(game.getContent().id())
                .toUri();

        log.info("[END] -> Game created with success! gameId: {}", game.getContent().id());

        return ResponseEntity.created(uri).body(game);
    }

}
