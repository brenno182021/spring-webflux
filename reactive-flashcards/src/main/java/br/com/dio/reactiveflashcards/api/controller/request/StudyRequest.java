package br.com.dio.reactiveflashcards.api.controller.request;

import br.com.dio.reactiveflashcards.core.validation.MongoId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Set;

public record StudyRequest(@MongoId
                           @JsonProperty("userId")
                           String userId,
                           @MongoId
                           @JsonProperty("deckId")
                           String deckId) {

    @Builder(toBuilder = true)
    public StudyRequest {
    }
}
