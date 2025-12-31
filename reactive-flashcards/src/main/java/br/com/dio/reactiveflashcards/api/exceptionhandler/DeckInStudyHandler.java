package br.com.dio.reactiveflashcards.api.exceptionhandler;

import br.com.dio.reactiveflashcards.domain.exception.DeckInStudyException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CONFLICT;

@Slf4j
@Component
public class DeckInStudyHandler extends AbstractHandlerException<DeckInStudyException>{
    public DeckInStudyHandler(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    Mono<Void> handlerException(ServerWebExchange exchange, DeckInStudyException ex) {
        return Mono.fromCallable(() -> {
                    prepareExchange(exchange, CONFLICT);
                    return ex.getMessage();
                }).map(message -> buildError(CONFLICT, message))
                .doFirst(() -> log.error("==== DeckInStudyException", ex))
                .flatMap(problemResponse -> writeResponse(exchange, problemResponse));
    }
}
