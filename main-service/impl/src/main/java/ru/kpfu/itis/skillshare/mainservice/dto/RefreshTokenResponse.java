package ru.kpfu.itis.skillshare.mainservice.dto;


import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;

public record RefreshTokenResponse (UserEntity owner, String token){
}
