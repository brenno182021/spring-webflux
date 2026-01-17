package br.com.dio.reactiveflashcards.domain.mapper;

import br.com.dio.reactiveflashcards.domain.document.Card;
import br.com.dio.reactiveflashcards.domain.document.DeckDocument;
import br.com.dio.reactiveflashcards.domain.document.UserDocument;
import br.com.dio.reactiveflashcards.domain.dto.CardDTO;
import br.com.dio.reactiveflashcards.domain.dto.DeckDTO;
import br.com.dio.reactiveflashcards.domain.dto.MailMessageDTO;
import br.com.dio.reactiveflashcards.domain.dto.StudyDTO;
import jakarta.mail.MessagingException;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.mail.javamail.MimeMessageHelper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface DeckDomainMapper {



    @Mapping(target = "description", source = "info")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DeckDocument toDocument(final DeckDTO dto);

    @Mapping(target = "back", source = "answer")
    @Mapping(target = "front", source = "ask")
    Card toDocument(final CardDTO dto);
}
