package ru.kpfu.itis.skillshare.mainservice.utils;

import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;

import java.sql.Date;

public class UserProfileUtil {


    public static UserEntity processUser(UserEntity user) {
        if (!user.getEnabled()) {
            return UserEntity.builder()
                    .id(user.getId())
                    .username("deleted")
                    .profileImage("https://res.cloudinary.com/debjgvnym/image/upload/v1714322938/deleted.jpg")
                    .rating(-999999)
                    .registerDate(new Date(0))
                    .age(-52)
                    .build();
        } else {
            return UserEntity.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .skills(user.getSkills())
                    .profileImage(user.getProfileImage())
                    .roles(user.getRoles())
                    .rating(user.getRating())
                    .registerDate(user.getRegisterDate())
                    .age(user.getAge())
                    .country(user.getCountry())
                    .city(user.getCity())
                    .realName(user.getRealName())
                    .build();
        }
    }
}
