package com.lorenzo.api_imoveis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailHtml(String nome, String email, String descricao, String resposta) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("LQImoveis@gmail.com");
        helper.setTo(email);
        helper.setSubject("Re: Proposta LQ Imoveis");

        String html = """
            <h3>Estimado Cliente: %s</h3>
            <h3>%s </h3>
            <p>%s </p>
        """.formatted(nome, descricao, resposta);

        helper.setText(html, true);
        mailSender.send(message);
    }

    public void sendPasswordRecovery(String name, String email, String codigo)throws MessagingException{

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("LQImoveis@gmail.com");
        helper.setTo(email);
        helper.setSubject("Re: Proposta LQ Imoveis");

        String html = """
            <h3>Estimado Cliente: %s</h3>
            <h3>Aqui esta seu codigo de recuperação %s</h3>
            <p>Se não foi você que pediu essa recuperação só ignore este email<p>
        """.formatted(name, codigo);

        helper.setText(html, true);
        mailSender.send(message);
    }
}
