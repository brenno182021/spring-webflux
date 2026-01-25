package br.com.dio.reactiveflashcards.api.controller.documentation;

import br.com.dio.reactiveflashcards.api.controller.request.DeckRequest;
import br.com.dio.reactiveflashcards.api.controller.response.DeckResponse;
import br.com.dio.reactiveflashcards.core.validation.MongoId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Deck", description = "Endpoints para manipulação de decks")
public interface DeckControllerDoc {

    @Operation(summary = "Endpoint para criar um novo deck")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "retorna o deck criado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DeckResponse.class))}),
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    Mono<DeckResponse> save(@Valid
                            @RequestBody
                            DeckRequest request);


    @Operation(summary = "Endpoint para buscar decks de uma api terceira")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "foram incluídos na base novos decks"),
    })
    @PostMapping(value = "sync")
    @ResponseStatus(NO_CONTENT)
    Mono<Void> sync();


    @Operation(summary = "Endpoint para buscar um deck pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "retorna o deck correspondente ao identificador",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DeckResponse.class))}),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "{id}")
    Mono<DeckResponse> findById(@Parameter(description = "Identificador do deck")
                                @PathVariable
                                @Valid
                                @MongoId(message = "{deckController.id}")
                                String id);


    @Operation(summary = "Endpoint para buscar todos os decks")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "retorna os decks cadastrados",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DeckResponse.class)))}),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    Flux<DeckResponse> findAll();


    @Operation(summary = "Endpoint para atualizar um deck")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "retorna o deck foi atualizado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DeckResponse.class))}),
    })
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, value = "{id}")
    Mono<DeckResponse> update(@Parameter(description = "Identificador do deck")
                              @PathVariable
                              @Valid
                              @MongoId(message = "{deckController.id}")
                              String id,
                              @Valid
                              @RequestBody
                              DeckRequest request);


    @Operation(summary = "Endpoint para excluir um deck")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "o deck foi excluído"),
    })
    @DeleteMapping(value = "{id}")
    @ResponseStatus(NO_CONTENT)
    Mono<Void> delete(@Parameter(description = "Identificador do deck")
                      @PathVariable
                      @Valid
                      @MongoId(message = "{deckController.id}")
                      String id);
}
