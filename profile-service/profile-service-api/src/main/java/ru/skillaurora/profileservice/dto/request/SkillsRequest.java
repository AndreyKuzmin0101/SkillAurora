package ru.skillaurora.profileservice.dto.request;

import java.util.List;

public record SkillsRequest(
        List<Long> skillIds,
        List<TagRequest> newSkills
) {
}
