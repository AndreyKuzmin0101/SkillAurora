package ru.skillaurora.profileservice.util;

import ru.skillaurora.profileservice.dto.response.AccountStatus;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.ProfileInfoEntity;

import java.util.List;

public class ProfileInfoUtil {

    public static ProfileInfoEntity processProfileInfo(ProfileInfoEntity profileInfo) {
        String accountStatus = profileInfo.getAccountStatus();
        if (AccountStatus.DELETED.name().equals(accountStatus)) {
            profileInfo.setProfilePicture(ProfileInfoConstants.DELETED_PROFILE_PICTURE);
            profileInfo.setUsername(ProfileInfoConstants.DELETED_PROFILE_USERNAME);
            profileInfo.setRating(Long.MIN_VALUE);
            profileInfo.setCountry(null);
            profileInfo.setSkills(null);
            profileInfo.setAboutMe(null);
            profileInfo.setCountryId(null);
            profileInfo.setRealName(null);
            profileInfo.setRegisterDate(null);
        }
        return profileInfo;
    }

    public static List<ProfileInfoEntity> processProfileInfo(List<ProfileInfoEntity> profileInfoEntities) {
        profileInfoEntities.forEach(ProfileInfoUtil::processProfileInfo);
        return profileInfoEntities;
    }
}
