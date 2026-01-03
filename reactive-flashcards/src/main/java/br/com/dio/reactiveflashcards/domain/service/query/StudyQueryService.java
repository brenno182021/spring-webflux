package br.com.dio.reactiveflashcards.domain.service.query;

import br.com.dio.reactiveflashcards.domain.document.Question;
import br.com.dio.reactiveflashcards.domain.document.StudyDocument;
import br.com.dio.reactiveflashcards.domain.exception.NotFoundException;
import br.com.dio.reactiveflashcards.domain.repository.StudyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static br.com.dio.reactiveflashcards.domain.exception.BaseErrorMessage.*;

@Service
@Slf4j
@AllArgsConstructor
public class StudyQueryService {

    private final StudyRepository studyRepository;


    public Mono<StudyDocument> findPendingStudyByUserIdAndDeckId(final String userId, final String deckId){
        return studyRepository.findByUserIdAndCompleteFalseAndStudyDeck_DeckId(userId, deckId)
                .doFirst(() -> log.info("=== Try to get pending study with userId {} and deckId {}", userId, deckId))
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.defer(() ->
                        Mono.error(new NotFoundException(STUDY_DECK_NOT_FOUND.params(userId,deckId).getMessage()))));
    }

    public Mono<StudyDocument> findById(final String id){
        return studyRepository.findById(id)
                .doFirst(() -> log.info("=== try to find study with id {}", id))
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundException(STUDY_NOT_FOUND.params(id).getMessage()))));
    }

    public Mono<StudyDocument> verifyIfFinished(final StudyDocument document) {
        return Mono.just(document.complete())
                .filter(BooleanUtils::isFalse)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundException(STUDY_QUESTION_NOT_FOUND
                        .params(document.id()).getMessage()))))
                .thenReturn(document);
    }

    public Mono<Question> getLastPendingQuestion(final String id) {
        return findById(id)
                .doFirst(() -> log.info("=== Getting a study with id {}", id))
                .flatMap(this::verifyIfFinished)
                .doFirst(() -> log.info("=== Getting a current pending question in study {}", id))
                .map(StudyDocument::getLastPendingQuestion);
    }

    public Flux<StudyDocument> findAll(){
        return studyRepository.findAll()
                .doFirst(() -> log.info("=== Try to get all studys"));
    }



}
