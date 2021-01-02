package com.chi.chwitter.service;

import com.chi.chwitter.entity.Token;
import com.chi.chwitter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
    @Value("${server.baseurl}")
    String baseUrl;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * @param email
     * @throws MailException
     */
    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public SimpleMailMessage getRegistrationMailMessage(Token token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        User user = token.getUser();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("customerservice@chi-witter.herokuapp.com");
        mailMessage.setText("Hi " + user.getUsername() + "! Thank you for register for Chwitter. To confirm your account, please click here: "
                + baseUrl + "/user/confirm?token=" + token.getToken());
        return mailMessage;
    }
}
