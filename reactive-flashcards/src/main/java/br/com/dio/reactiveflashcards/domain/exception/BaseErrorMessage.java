package br.com.dio.reactiveflashcards.domain.exception;


import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.MessageSource;


import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@RequiredArgsConstructor
public class BaseErrorMessage {

    @Setter
    private static MessageSource messageSource;

    public static final BaseErrorMessage GENERIC_EXCEPTION = new BaseErrorMessage("generic");

    public static final BaseErrorMessage GENERIC_NOT_FOUND = new BaseErrorMessage("generic.notFound");

    public static final BaseErrorMessage GENERIC_METHOD_NOT_ALLOW = new BaseErrorMessage("generic.methodNotAllow");

    public static final BaseErrorMessage GENERIC_BAD_REQUEST = new BaseErrorMessage("generic.badRequest");

    public static final BaseErrorMessage GENERIC_MAX_RETRIES = new BaseErrorMessage("generic.maxRetries");

    public static final BaseErrorMessage USER_NOT_FOUND = new BaseErrorMessage("user.NotFound");

    public static final BaseErrorMessage DECK_NOT_FOUND = new BaseErrorMessage("deck.NotFound");

    public static final BaseErrorMessage STUDY_DECK_NOT_FOUND = new BaseErrorMessage("studyDeck.NotFound");

    public static final BaseErrorMessage STUDY_NOT_FOUND = new BaseErrorMessage("study.NotFound");

    public static final BaseErrorMessage STUDY_QUESTION_NOT_FOUND = new BaseErrorMessage("studyQuestion.NotFound");

    public static final BaseErrorMessage DECK_IN_STUDY = new BaseErrorMessage("study.DeckInStudy");

    public static final BaseErrorMessage EMAIL_ALREADY_USED = new BaseErrorMessage("user.EmailAlreadyUsed");

    private final String key;

    private String[] params;


    public BaseErrorMessage params(final String... params) {
        this.params = ArrayUtils.clone(params);
        return this;
    }

    public String getMessage() {
        return messageSource.getMessage(key, params, Locale.getDefault());
    }


}
