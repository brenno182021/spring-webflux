package br.com.dio.reactiveflashcards.api.controller.request;

import br.com.dio.reactiveflashcards.core.validation.MongoId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Set;

public record StudyRequest(@MongoId
                           @JsonProperty("userId")
                           @Schema(description = "Identificador do usuário", example = "694feb2ade012e5042c00ede", format = "UUID")
                           String userId,
                           @MongoId
                           @JsonProperty("deckId")
                           @Schema(description = "Identificador do Deck", example = "694feb2ade012e5042c00ede", format = "UUID")
                           String deckId) {

    @Builder(toBuilder = true)
    public StudyRequest {
    }
}
