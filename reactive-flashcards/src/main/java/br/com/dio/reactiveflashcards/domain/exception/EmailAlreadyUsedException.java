package br.com.dio.reactiveflashcards.domain.exception;

public class EmailAlreadyUsedException extends ReactiveFlashcardsException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
