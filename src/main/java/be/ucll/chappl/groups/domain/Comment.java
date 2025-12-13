package be.ucll.chappl.groups.domain;

import java.time.Instant;

public record Comment(Instant timestamp, String content, int score) {
    public Comment {
        if (timestamp == null) throw new IllegalArgumentException("timestamp is required");
        if (content == null || content.isBlank()) throw new IllegalArgumentException("content is required");
        content = content.trim();
    }
}
