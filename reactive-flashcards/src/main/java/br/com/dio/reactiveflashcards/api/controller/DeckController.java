package br.com.dio.reactiveflashcards.api.controller;

import br.com.dio.reactiveflashcards.api.controller.request.DeckRequest;
import br.com.dio.reactiveflashcards.api.controller.response.DeckResponse;
import br.com.dio.reactiveflashcards.api.controller.response.UserResponse;
import br.com.dio.reactiveflashcards.api.mapper.DeckerMapper;
import br.com.dio.reactiveflashcards.core.validation.MongoId;
import br.com.dio.reactiveflashcards.domain.service.DeckService;
import br.com.dio.reactiveflashcards.domain.service.query.DeckQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequestMapping("decks")
@Slf4j
@AllArgsConstructor
public class DeckController {


    private final DeckService deckService;
    private final DeckQueryService deckQueryService;


    private final DeckerMapper deckerMapper;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Mono<DeckResponse> save(@Valid @RequestBody final DeckRequest request) {
        return deckService.save(deckerMapper.toDocument(request))
                .doFirst(() -> log.info("=== Saving a deck with follow data {}", request))
                .map(deckerMapper::toResponse);
    }


    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "{id}")
    public Mono<DeckResponse> findById(@PathVariable @Valid @MongoId(message = "{deckController.id}") final String id){
        return deckQueryService.findById(id)
                .doFirst(() -> log.info("=== finding a deck with follow id {}", id))
                .map(deckerMapper::toResponse);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Flux<DeckResponse> findAll(){
        return deckQueryService.findAll()
                .doFirst(() -> log.info("=== finding all decks"))
                .map(deckerMapper::toResponse);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, value = "{id}")
    public Mono<DeckResponse> update(@PathVariable @Valid @MongoId(message = "{deckController.id}") final String id,
                                    @Valid @RequestBody final DeckRequest request){
        return deckService.update(deckerMapper.toDocument(request, id))
                .doFirst(() -> log.info("=== Updating a deck with follow info [body: {}, id: {}]",request, id ))
                .map(deckerMapper::toResponse);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> delete(@PathVariable @Valid @MongoId(message = "{deckController.id}") final String id){
        return deckService.delete(id)
                .doFirst(() -> log.info("=== Deleting a deck with follow id {}", id));
    }

}
