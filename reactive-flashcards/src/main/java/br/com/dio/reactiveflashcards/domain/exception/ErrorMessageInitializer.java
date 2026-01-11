package br.com.dio.reactiveflashcards.domain.exception;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessageInitializer implements ApplicationRunner {
    private final MessageSource messageSource;

    public ErrorMessageInitializer(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BaseErrorMessage.setMessageSource(messageSource);
    }
}
