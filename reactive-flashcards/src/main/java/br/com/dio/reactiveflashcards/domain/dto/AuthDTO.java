package br.com.dio.reactiveflashcards.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthDTO(@JsonProperty("token")
                      String token,
                      @JsonProperty("expiresIn")
                      Long expiresIn) {

}
