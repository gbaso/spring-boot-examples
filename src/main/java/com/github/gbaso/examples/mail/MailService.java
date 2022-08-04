package com.github.gbaso.examples.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.github.gbaso.examples.mail.Mail.Attachment;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final ResourceLoader resourceLoader;
    private final Configuration  freemarkerConfiguration;

    public void sendMail(String templateKey, Mail mail, Map<String, Object> params) {
        Map<String, Object> model = new HashMap<>(params);
        model.putAll(mail.attachments().stream().collect(Collectors.toMap(Attachment::name, a -> "cid:" + a.id().toString())));
        javaMailSender.send(mimeMessage -> {
            var mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED);
            mimeMessageHelper.setFrom(mail.sender());
            mimeMessageHelper.setTo(mail.recipients().toArray(new String[0]));
            mimeMessageHelper.setReplyTo(mail.sender());
            mimeMessageHelper.setSubject(processTemplate(templateKey + "_sbj.ftl", model));
            mimeMessageHelper.setText(processTemplate(templateKey + "_cnt.ftl", model), true);
            for (var attachment : mail.attachments()) {
                mimeMessageHelper.addInline(attachment.id().toString(), resourceLoader.getResource(attachment.location()));
            }
        });
    }

    private String processTemplate(String name, Map<String, Object> model) throws IOException, TemplateException {
        Template template = freemarkerConfiguration.getTemplate(name);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }

}
