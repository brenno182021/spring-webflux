package br.com.dio.reactiveflashcards.domain.mapper;

import br.com.dio.reactiveflashcards.domain.dto.MailMessageDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.MimeMessageHelper;

public abstract class MailMapperDecorator implements MailMapper {

    @Qualifier("delegate")
    @Autowired
    private MailMapper mailMapper;


    @Override
    public MimeMessageHelper toMimeMessageHelper(MimeMessageHelper helper, MailMessageDTO mailMessageDTO, String sender, String body) throws MessagingException {

        mailMapper.toMimeMessageHelper(helper, mailMessageDTO, sender, body);
        helper.setText(body, true);
        return helper;
    }
}
