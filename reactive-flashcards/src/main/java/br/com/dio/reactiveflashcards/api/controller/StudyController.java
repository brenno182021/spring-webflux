package br.com.dio.reactiveflashcards.api.controller;

import br.com.dio.reactiveflashcards.api.controller.request.StudyRequest;
import br.com.dio.reactiveflashcards.api.controller.response.QuestionResponse;
import br.com.dio.reactiveflashcards.api.controller.response.StudyResponse;
import br.com.dio.reactiveflashcards.api.mapper.StudyMapper;
import br.com.dio.reactiveflashcards.core.validation.MongoId;
import br.com.dio.reactiveflashcards.domain.service.StudyService;
import br.com.dio.reactiveflashcards.domain.service.query.DeckQueryService;
import br.com.dio.reactiveflashcards.domain.service.query.StudyQueryService;
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
@RequestMapping("studies")
@Slf4j
@AllArgsConstructor
public class StudyController {


    private final StudyService studyService;
    private final StudyQueryService studyQueryService;
    private final DeckQueryService deckQueryService;


    private final StudyMapper studyMapper;


    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Mono<QuestionResponse> start(@Valid @RequestBody final StudyRequest request) {
        return studyService.start(studyMapper.toDocument(request))
                .doFirst(() -> log.info("=== try to create a study with follow request {}", request))
                .map(document -> studyMapper.toResponse(document.getLastPendingQuestion(), document.id()));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "{id}/current-question")
    public Mono<QuestionResponse> getCurrentQuestion(@Valid @PathVariable @MongoId(message = "{studyController.id}") final String id){
        return studyQueryService.getLastPendingQuestion(id)
                .doFirst(() -> log.info("=== try to get a next question in study {}", id))
                .map(question -> studyMapper.toResponse(question, id));
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Flux<StudyResponse> findAll(){
        return studyQueryService.findAll()
                .doFirst(() -> log.info("=== finding a users"))
                .map(studyMapper::toResponse);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> delete(@PathVariable @Valid @MongoId(message = "{studyController.id}") final String id){
        return studyService.delete(id)
                .doFirst(() -> log.info("=== Deleting a study with follow id {}", id));
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public Mono<Void> deleteAll(){
        return studyService.deleteAll()
                .doFirst(() -> log.info("=== Deleting ALL studies"));
    }



}
