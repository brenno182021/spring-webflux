package br.com.dio.reactiveflashcards.api.controller.request;

import br.com.dio.reactiveflashcards.api.controller.request.enums.UserSortBy;
import br.com.dio.reactiveflashcards.api.controller.request.enums.UserSortDirection;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Sort;

import static br.com.dio.reactiveflashcards.api.controller.request.enums.UserSortBy.NAME;
import static br.com.dio.reactiveflashcards.api.controller.request.enums.UserSortDirection.ASC;
import static br.com.dio.reactiveflashcards.api.controller.request.enums.UserSortDirection.DESC;

public record UserPageRequest(@JsonProperty("sentence")
                              @Schema(description = "Texto para filtrar por nome e email (case insensitive)", example = "ana")
                              String sentence,
                              @PositiveOrZero
                              @JsonProperty("page")
                              @Schema(description = "Página solicitada", example = "1", defaultValue = "0")
                              Long page,
                              @Min(1)
                              @Max(50)
                              @JsonProperty("limit")
                              @Schema(description = "Tamanho da Página ", example = "30", defaultValue = "20")
                              Integer limit,
                              @JsonProperty("sortBy")
                              @Schema(description = "Campo para ordenação", enumAsRef = true, defaultValue = "NAME")
                              UserSortBy sortBy,
                              @JsonProperty("sortDirection")
                              @Schema(description = "Sentido da ordenação", enumAsRef = true, defaultValue = "ASC")
                              UserSortDirection sortDirection) {

    @Builder(toBuilder = true)
    public UserPageRequest {
        sortBy = ObjectUtils.defaultIfNull(sortBy, NAME);
        sortDirection = ObjectUtils.defaultIfNull(sortDirection, ASC);
        limit = ObjectUtils.defaultIfNull(limit, 20);
        page = ObjectUtils.defaultIfNull(page, 0L);
    }

    @Schema(hidden = true)
    public Sort getSort(){
        return sortDirection.equals(DESC) ? Sort.by(sortBy.getField()).descending() : Sort.by(sortBy.getField()).ascending();
    }

    @Schema(hidden = true)
    public Long getSkip(){
        return page > 0 ? (page - 1) * limit : 0;
    }

}
