package br.com.dio.reactiveflashcards.api.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Set;

public record DeckResponse(@JsonProperty("id")
                           @Schema(description = "Identificador do Deck", format = "UUID", example = "694feb2ade012e5042c00ede")
                           String id,
                           @JsonProperty("name")
                           @Schema(description = "Nome do Deck", example = "Estudo de inglês")
                           String name,
                           @JsonProperty("description")
                           @Schema(description = "Descrição do Deck", example = "deck de estudo de inglês para iniciantes")
                           String description,
                           @JsonProperty("cards")
                           @Schema(description = "Cards que compõe o Deck")
                           Set<CardResponse> cards) {

    @Builder(toBuilder = true)
    public DeckResponse {
    }
}
