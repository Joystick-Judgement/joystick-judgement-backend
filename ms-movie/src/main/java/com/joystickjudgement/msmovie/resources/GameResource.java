package com.joystickjudgement.msmovie.resources;

import com.joystickjudgement.msmovie.dtos.GameDTO;
import com.joystickjudgement.msmovie.services.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "games")
@RequiredArgsConstructor
public class GameResource {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<EntityModel<GameDTO>> create(@RequestBody @Valid GameDTO dto) {
        return ResponseEntity.ok(gameService.create(dto));
    }

}
