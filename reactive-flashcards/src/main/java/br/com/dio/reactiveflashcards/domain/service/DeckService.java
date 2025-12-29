package br.com.dio.reactiveflashcards.domain.service;

import br.com.dio.reactiveflashcards.domain.document.DeckDocument;
import br.com.dio.reactiveflashcards.domain.repository.DeckRepository;
import br.com.dio.reactiveflashcards.domain.service.query.DeckQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Slf4j
@Service
public class DeckService {

    private final DeckRepository deckRepository;

    private final DeckQueryService deckQueryService;


    public Mono<DeckDocument> save(final DeckDocument document){
        return deckRepository.save(document)
                .doFirst(() -> log.info("==== try to save a follow deck {}", document));
    }

    public Mono<DeckDocument> update(final DeckDocument document){
        return deckQueryService.findById(document.id())
                .map(deck -> document.toBuilder()
                        .createdAt(deck.createdAt())
                        .updatedAt(deck.updatedAt())
                        .build())
                .flatMap(deckRepository::save)
                .doFirst(() -> log.info("=== Try to update a deck with follow info {}", document));
    }

    public Mono<Void> delete(final String id) {
        return deckQueryService.findById(id)
                .flatMap(deckRepository::delete)
                .doFirst(() -> log.info("=== Try to delete a deck with follow id {}", id));
    }


}
