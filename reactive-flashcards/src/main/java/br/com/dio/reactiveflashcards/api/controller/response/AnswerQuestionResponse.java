package br.com.dio.reactiveflashcards.api.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.OffsetDateTime;

public record AnswerQuestionResponse(@JsonProperty("asked")
                                     @Schema(description = "Pergunta feita", example = "blue")
                                     String asked,
                                     @JsonProperty("askedIn")
                                     @Schema(description = "Momento em que a pergunta foi gerada", format = "datetime")
                                     OffsetDateTime askedIn,
                                     @JsonProperty("answered")
                                     @Schema(description = "Resposta fornecida", example = "azul")
                                     String answered,
                                     @JsonProperty("answeredIn")
                                     @Schema(description = "Momento em que a resposta foi gerada", format = "datetime")
                                     OffsetDateTime answeredIn,
                                     @JsonProperty("expected")
                                     @Schema(description = "Resposta esperada", example = "azul")
                                     String expected) {

    @Builder(toBuilder = true)
    public AnswerQuestionResponse {
    }
}
