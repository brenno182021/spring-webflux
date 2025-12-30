package br.com.dio.reactiveflashcards.api.controller.response;

import br.com.dio.reactiveflashcards.core.validation.MongoId;
import br.com.dio.reactiveflashcards.domain.document.Question;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public record StudyResponse(@JsonProperty("id")
                            String id,
                            @JsonProperty("userId")
                            String userId,
                            @JsonProperty("deckId")
                            String deckId,
                            @JsonProperty("questions")
                            List<Question> questions) {

    @Builder(toBuilder = true)
    public StudyResponse {
    }
}
