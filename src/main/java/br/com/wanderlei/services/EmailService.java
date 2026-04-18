package br.com.wanderlei.services;

import br.com.wanderlei.config.EmailConfig;
import br.com.wanderlei.data.dto.request.EmailRequestDTO;
import br.com.wanderlei.mail.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private EmailConfig emailConfig;

    public void sendSimpleEmail(EmailRequestDTO emailRequest) {
        emailSender
                .to(emailRequest.getTo())
                .withSubject(emailRequest.getSubject())
                .withMessage (emailRequest.getBody())
                .send(emailConfig);
    }


}
