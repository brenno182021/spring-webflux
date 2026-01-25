package br.com.dio.reactiveflashcards.api.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public record UserResponse(@JsonProperty("id")
                           @Schema(description = "Identificador do Usuário", format = "UUID", example = "694feb2ade012e5042c00ede")
                           String id,
                           @JsonProperty("name")
                           @Schema(description = "nome do usuário", example = "João")
                           String name,
                           @JsonProperty("email")
                           @Schema(description = "email do usuário", example = "joão@joao.com.br")
                           String email) {

    @Builder(toBuilder = true)
    public UserResponse {
    }
}
