package ru.skillaurora.articleservice.dto.response;

public record TagResponse (
        Long id,
        String name,
        String description,
        Long countUsage
) {
}
