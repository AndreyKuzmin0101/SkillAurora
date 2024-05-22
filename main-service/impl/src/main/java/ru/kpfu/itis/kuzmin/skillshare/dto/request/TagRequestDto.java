package ru.kpfu.itis.kuzmin.skillshare.dto.request;

import java.util.Objects;

public record TagRequestDto(String name, String description) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagRequestDto that = (TagRequestDto) o;
        return Objects.equals(name, that.name);
    }
}
