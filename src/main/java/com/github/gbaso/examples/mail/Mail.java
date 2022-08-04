package com.github.gbaso.examples.mail;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record Mail(String sender, Set<String> recipients, List<Attachment> attachments) {
    public static record Attachment(String name, String location, UUID id) {}
}
