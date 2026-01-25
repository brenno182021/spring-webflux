package br.com.dio.reactiveflashcards.api.controller.documentation;

import br.com.dio.reactiveflashcards.api.controller.request.AnswerQuestionRequest;
import br.com.dio.reactiveflashcards.api.controller.request.StudyRequest;
import br.com.dio.reactiveflashcards.api.controller.response.AnswerQuestionResponse;
import br.com.dio.reactiveflashcards.api.controller.response.QuestionResponse;
import br.com.dio.reactiveflashcards.api.controller.response.StudyResponse;
import br.com.dio.reactiveflashcards.api.controller.response.UserResponse;
import br.com.dio.reactiveflashcards.core.validation.MongoId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "Study", description = "Endpoints para gerenciar estudos")
public interface StudyControllerDoc {

    @Operation(summary = "Endpoint para iniciar o estudo de um deck")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "o estudo foi criado e retorna a primeira pergunta gerada",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QuestionResponse.class))}),
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    Mono<QuestionResponse> start(@Valid
                                 @RequestBody
                                 StudyRequest request);


    @Operation(summary = "Endpoint para buscar a ultima pergunta não respondida")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "retorna a ultima pergunta que não foi respondida",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QuestionResponse.class))}),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "{id}/current-question")
    Mono<QuestionResponse> getCurrentQuestion(@Parameter(description = "Identificador do estudo")
                                              @Valid
                                              @PathVariable
                                              @MongoId(message = "{studyController.id}")
                                              String id);


    @Operation(summary = "Endpoint para responder a pergunta atual")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "retorna a pergunta, a resposta fornecida e a resposta esperada",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AnswerQuestionResponse.class))}),
    })
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE, value = "{id}/answer")
    Mono<AnswerQuestionResponse> answer(@Parameter(description = "Identificador do estudo")
                                        @Valid
                                        @PathVariable
                                        @MongoId(message = "{studyController.id}")
                                        String id,
                                        @Valid
                                        @RequestBody
                                        AnswerQuestionRequest request);

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    Flux<StudyResponse> findAll();


    @Operation(summary = "Endpoint para excluir um estudo")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "o estudo foi excluído"),
    })
    @DeleteMapping(value = "{id}")
    @ResponseStatus(NO_CONTENT)
    Mono<Void> delete(@Parameter(description = "Identificador do estudo")
                      @PathVariable
                      @Valid
                      @MongoId(message = "{studyController.id}")
                      String id);

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    Mono<Void> deleteAll();
}
