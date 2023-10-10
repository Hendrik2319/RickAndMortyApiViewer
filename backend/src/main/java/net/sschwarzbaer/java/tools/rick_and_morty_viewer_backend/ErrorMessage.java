package net.sschwarzbaer.java.tools.rick_and_morty_viewer_backend;

import java.time.ZonedDateTime;

public record ErrorMessage(String message, ZonedDateTime timestamp) {
    ErrorMessage(String message) {
        this(message, ZonedDateTime.now());
    }
}
