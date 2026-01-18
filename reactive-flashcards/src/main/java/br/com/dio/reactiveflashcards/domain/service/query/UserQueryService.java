package br.com.dio.reactiveflashcards.domain.service.query;

import br.com.dio.reactiveflashcards.api.controller.request.UserPageRequest;
import br.com.dio.reactiveflashcards.domain.document.UserDocument;
import br.com.dio.reactiveflashcards.domain.document.UserPageDocument;
import br.com.dio.reactiveflashcards.domain.exception.EmailAlreadyUsedException;
import br.com.dio.reactiveflashcards.domain.exception.NotFoundException;
import br.com.dio.reactiveflashcards.domain.repository.UserRepository;
import br.com.dio.reactiveflashcards.domain.repository.impl.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

import static br.com.dio.reactiveflashcards.domain.exception.BaseErrorMessage.EMAIL_ALREADY_USED;
import static br.com.dio.reactiveflashcards.domain.exception.BaseErrorMessage.USER_NOT_FOUND;

@Service
@Slf4j
@AllArgsConstructor
public class UserQueryService {

    private final UserRepository userRepository;

    private final UserRepositoryImpl userRepositoryImpl;

    public Mono<UserDocument> findById(final String id){
        return userRepository.findById(id)
                .doFirst(() -> log.info("=== try to find user with id {}", id))
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundException(USER_NOT_FOUND.params("id",id).getMessage()))));
    }

    public Mono<UserDocument> findByEmail(final String email){
        return userRepository.findByEmail(email)
                .doFirst(() -> log.info("=== try to find user with email {}", email))
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundException(USER_NOT_FOUND.params("email",email).getMessage()))));
    }

    public Flux<UserDocument> findAll(){
        return userRepository.findAll();
    }

    public Mono<Void> verifyEmail(final UserDocument document){

        return findByEmail(document.email())
                .filter(stored -> stored.id().equals(document.id()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new EmailAlreadyUsedException(EMAIL_ALREADY_USED
                        .params(document.email()).getMessage()))))
                .onErrorResume(NotFoundException.class, e -> Mono.empty())
                .then();
    }

    public Mono<UserPageDocument> findOnDemand(final UserPageRequest request){
        return userRepositoryImpl.findOnDemand(request)
                .collectList()
                .zipWhen(documents -> userRepositoryImpl.count(request))
                .map(tuple -> UserPageDocument.Builder()
                        .limit(request.limit())
                        .currentPage(request.page())
                        .totalItems(tuple.getT2())
                        .content(tuple.getT1())
                        .build());
    }

}
