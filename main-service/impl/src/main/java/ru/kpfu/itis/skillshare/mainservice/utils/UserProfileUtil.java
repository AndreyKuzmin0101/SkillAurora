package ru.kpfu.itis.skillshare.mainservice.utils;

import ru.kpfu.itis.skillshare.mainservice.model.UserEntity;

import java.sql.Date;

public class UserProfileUtil {


    public static UserEntity processUser(UserEntity user) {
        user.setEmail(null);
        user.setPassword(null);
        if (!user.getEnabled()) {
            user.setUsername("deleted");
            user.setSkills(null);
            user.setProfileImage("https://res.cloudinary.com/debjgvnym/image/upload/v1714322938/deleted.jpg");
            user.setRoles(null);
            user.setRating(-999999);
            user.setRegisterDate(new Date(0));
            user.setAge(-52);
            user.setCountry(null);
            user.setCity(null);
            user.setRealName(null);
        }
        return user;
    }
}
