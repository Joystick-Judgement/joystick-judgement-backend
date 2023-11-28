package com.joystickjudgement.msmovie.resources;

import com.joystickjudgement.msmovie.dtos.GameDTO;
import com.joystickjudgement.msmovie.services.GameService;
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

@RestController
@RequestMapping("games")
@RequiredArgsConstructor
@Slf4j
public class GameResource {

    private final GameService gameService;

    @PostMapping
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
