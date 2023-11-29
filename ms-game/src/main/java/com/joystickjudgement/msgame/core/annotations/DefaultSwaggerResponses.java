package com.joystickjudgement.msgame.core.annotations;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

import static com.joystickjudgement.msgame.core.annotations.DefaultSwaggerResponses.SC_UNPROCESSABLE_ENTITY_CODE;
import static java.net.HttpURLConnection.*;

@ApiResponses(value = {
        @ApiResponse(
                responseCode = "" + HTTP_BAD_REQUEST,
                description = "Você fez uma requisição mal formada. Tente rever a documentação da mesma " +
                        "e garanta que os dados estão com a estrutura e valores corretos",
                content = @Content(schema = @Schema())
        ),
        @ApiResponse(
                responseCode = SC_UNPROCESSABLE_ENTITY_CODE,
                description = "Você fez uma requisição bem formada. " +
                        "Entretanto, não conseguimos processá-la devido a erros semânticos.",
                content = @Content(schema = @Schema())
        ),
        @ApiResponse(
                responseCode = "" + HTTP_UNAUTHORIZED,
                description = "Ocorreu um erro de autenticação. Você não foi capaz de se portar como um consumidor " +
                        "confiável da aplicação.",
                content = @Content(schema = @Schema())
        ),
        @ApiResponse(
                responseCode = "" + HTTP_NOT_FOUND,
                description = "Eu não consegui achar o recurso que você estava procurando." +
                        " Isso vale para qualquer coisa, desde uma URI que não exista até mesmo um objeto do " +
                        "banco de dados, ou alguma entidade em serviços terceiros.",
                content = @Content(schema = @Schema())
        ),
        @ApiResponse(
                responseCode = "" + HTTP_CONFLICT,
                description = "Sua requisição conflitou com algum recurso já existente dentro do servidor." +
                        " Por exemplo, você tentou criar um jogo com um nome que já existe.",
                content = @Content(schema = @Schema())
        ),
        @ApiResponse(
                responseCode = "" + HTTP_REQ_TOO_LONG,
                description = "É raro, mas de vez em quando acontece. Basicamente é quando você tem uma URI longa demais" +
                        " e o servidor não consegue interpretar toda aquela redação.",
                content = @Content(schema = @Schema())
        ),
        @ApiResponse(
                responseCode = "" + HTTP_INTERNAL_ERROR,
                description = "Quando retornado esse código é porque aconteceu algum erro interno dentro " +
                        "do servidor da aplicação.",
                content = @Content(schema = @Schema())
        ),
        @ApiResponse(
                responseCode = "" + HTTP_NOT_IMPLEMENTED,
                description = "Esse recurso ainda não está implementado.",
                content = @Content(schema = @Schema())
        ),
        @ApiResponse(
                responseCode = "" + HTTP_UNAVAILABLE,
                description = "O servidor não está conseguindo receber solicitações. " +
                        "Pode ser devido a um overhead de transações ou uma manutenção no servidor.",
                content = @Content(schema = @Schema())
        ),
        @ApiResponse(
                responseCode = "" + HTTP_GATEWAY_TIMEOUT,
                description = "O servidor não conseguiu obter uma resposta a tempo. Esse response geralmente é dado " +
                        "quando subimos uma nova versão do app e o pod ainda está reiniciando, portanto está inacessível, " +
                        " ou até mesmo quando nós ou algum serviço terceiro que integramos está com uma latência de rede" +
                        " muito alta e por isso sua requisição passou dos limites de tempo de resposta.",
                content = @Content(schema = @Schema())
        )
})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface DefaultSwaggerResponses {
        String SC_UNPROCESSABLE_ENTITY_CODE = "422";
}
