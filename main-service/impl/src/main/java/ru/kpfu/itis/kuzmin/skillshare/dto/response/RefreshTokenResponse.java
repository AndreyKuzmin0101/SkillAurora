package ru.kpfu.itis.kuzmin.skillshare.dto.response;

import ru.kpfu.itis.kuzmin.skillshare.model.UserEntity;

public record RefreshTokenResponse (UserEntity owner, String token){
}
