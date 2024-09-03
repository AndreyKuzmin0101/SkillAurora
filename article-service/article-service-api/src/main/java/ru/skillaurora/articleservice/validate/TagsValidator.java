package ru.skillaurora.articleservice.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.skillaurora.articleservice.dto.request.TagsRequest;

import java.util.List;

public class TagsValidator implements ConstraintValidator<Tags, TagsRequest> {

    @Override
    public boolean isValid(TagsRequest value, ConstraintValidatorContext context) {
        List<Long> tagIds = value.tagIds();
        long count = tagIds.stream().filter(id -> id.compareTo(0L) > 0).count();
        return count == tagIds.size();
    }
}
