package ru.kpfu.itis.kuzmin.skillshare.dto.request;

import java.util.List;

public record UserRequestDto(String username, String realName, Integer age, String email, String country, String city, String password, String profileImage,
                             List<TagRequestDto> skills) {
}
