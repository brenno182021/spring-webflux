package br.com.dio.reactiveflashcards.api.mapper;

import br.com.dio.reactiveflashcards.api.controller.request.DeckRequest;
import br.com.dio.reactiveflashcards.api.controller.response.DeckResponse;
import br.com.dio.reactiveflashcards.domain.document.DeckDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeckerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DeckDocument toDocument(final DeckRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DeckDocument toDocument(final DeckRequest request, final String id);

    DeckResponse toResponse(final DeckDocument document);
}
