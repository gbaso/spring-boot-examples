package com.github.gbaso.examples.mail;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    final MailService mailService;

    @PostMapping("/send")
    void sendMail(@RequestBody SendMailRequest request) {
        var attachments = request.attachments().entrySet().stream().map(e -> new Mail.Attachment(e.getKey(), e.getValue(), UUID.randomUUID())).toList();
        var mail = new Mail(request.sender(), request.recipients(), attachments);
        mailService.sendMail(request.templateKey(), mail, request.params());
    }
 
}

record SendMailRequest(String templateKey, String sender, Set<String> recipients, Map<String, String> attachments, Map<String, Object> params) {}
