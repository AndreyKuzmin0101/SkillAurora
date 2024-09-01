package ru.skillaurora.profileservice.repository.jooq;

import ru.skillaurora.profileservice.dto.response.AccountStatus;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.ProfileInfoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileInfoRepository {

    Optional<ProfileInfoEntity> findById(UUID userId);

    Optional<ProfileInfoEntity> findByUsername(String username);

    List<ProfileInfoEntity> findAll(List<UUID> ids);

    Optional<ProfileInfoEntity> findByIdWithCountryAndSkills(UUID userId);

    int updateUsername(UUID id, String username);

    int updateRealName(UUID id, String name);

    int updateAboutMe(UUID id, String aboutMe);

    int updateCountryId(UUID id, Short countryId);

    int updateAccountStatus(UUID id, AccountStatus status);

    void save(ProfileInfoEntity entity);

}
