package br.com.dio.reactiveflashcards.domain.exception;

public class RetryExcepcion extends ReactiveFlashcardsException {

    public RetryExcepcion(String message, Throwable cause) {
        super(message, cause);
    }
}
