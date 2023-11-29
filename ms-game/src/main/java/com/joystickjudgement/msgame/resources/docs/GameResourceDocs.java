package com.joystickjudgement.msgame.resources.docs;

import com.joystickjudgement.msgame.core.annotations.DefaultSwaggerHeaders;
import com.joystickjudgement.msgame.core.annotations.DefaultSwaggerResponses;
import com.joystickjudgement.msgame.dtos.GameDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static com.joystickjudgement.msgame.core.entities.GlobalConstantsSingleton.API_KEY_HEADER;
import static java.net.HttpURLConnection.HTTP_CREATED;

@Tag(description = "<b>Recurso que gerencia os jogos.</b>", name = "games-resource")
public interface GameResourceDocs {

    @Operation(
            summary = "Cria um novo jogo.",
            description = "Permite criar um novo jogo no sistema com certas informações sendo obrigatórias."
    )
    @Parameter(
            in = ParameterIn.HEADER,
            name = API_KEY_HEADER,
            required = true
    )
    @DefaultSwaggerHeaders
    @DefaultSwaggerResponses
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "" + HTTP_CREATED,
                    description = "Recurso criado com sucesso.",
                    headers = {
                            @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "URI do recurso criado",
                                    required = true
                            )
                    }
            )
    })
    ResponseEntity<EntityModel<GameDTO>> create(
            @Parameter(
                    name = "dto",
                    description = "Objeto que representa o jogo que será criado",
                    required = true
            )
            GameDTO dto
    );
}
