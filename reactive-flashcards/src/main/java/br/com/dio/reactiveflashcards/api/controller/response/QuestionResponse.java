package br.com.dio.reactiveflashcards.api.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.OffsetDateTime;

public record QuestionResponse(@JsonProperty("id")
                               @Schema(description = "Identificador do estudo", example = "694feb2ade012e5042c00ede", format = "UUID")
                               String  id,
                               @JsonProperty("asked")
                               @Schema(description = "pergunta atual do estudo", example = "azul")
                               String asked,
                               @JsonProperty("askedIn")
                               @Schema(description = "quando a pergunta ofi gerada", format = "datetime")
                               OffsetDateTime askedIn) {

    @Builder(toBuilder = true)
    public QuestionResponse {
    }
}
