package br.com.dio.reactiveflashcards.domain.document;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.OffsetDateTime;

public record Question(String asked,
                       @Field("asked_in")
                       OffsetDateTime askedIn,
                       String answered,
                       @Field("answered_in")
                       OffsetDateTime answeredIn,
                       String expected) {

    @Builder(toBuilder = true)
    public Question {
    }
}
