package com.joystickjudgement.msmovie.resources;

import com.joystickjudgement.msmovie.dtos.GameDTO;
import com.joystickjudgement.msmovie.services.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
public class GameResource {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<EntityModel<GameDTO>> create(@RequestBody @Valid GameDTO dto) {

        var game = gameService.create(dto);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(game.getContent().id())
                .toUri();

        return ResponseEntity.created(uri).body(game);
    }

}
