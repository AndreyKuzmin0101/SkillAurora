package ru.skillaurora.profileservice.repository.jooq.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import ru.skillaurora.profileservice.dto.response.AccountStatus;
import ru.skillaurora.profileservice.dto.response.SkillResponse;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.CountryEntity;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.ProfileInfoEntity;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.SkillEntity;
import ru.skillaurora.profileservice.repository.jooq.ProfileInfoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.skillaurora.profileservice.model.jooq.schema.tables.Country.COUNTRY_ENTITY;
import static ru.skillaurora.profileservice.model.jooq.schema.tables.ProfileInfo.PROFILE_INFO_ENTITY;
import static ru.skillaurora.profileservice.model.jooq.schema.tables.Skill.SKILL_ENTITY;

@Repository
@RequiredArgsConstructor
public class ProfileInfoRepositoryImpl implements ProfileInfoRepository {

    private final DSLContext dsl;

    @Override
    public Optional<ProfileInfoEntity> findById(UUID userId) {
        return dsl.selectFrom(PROFILE_INFO_ENTITY)
                .where(PROFILE_INFO_ENTITY.ID.eq(userId))
                .fetchOptionalInto(ProfileInfoEntity.class);
    }

    @Override
    public Optional<ProfileInfoEntity> findByUsername(String username) {
        return dsl.selectFrom(PROFILE_INFO_ENTITY)
                .where(PROFILE_INFO_ENTITY.USERNAME.eq(username))
                .fetchOptionalInto(ProfileInfoEntity.class);
    }

    @Override
    public List<ProfileInfoEntity> findAll(List<UUID> ids) {
        return dsl.selectFrom(PROFILE_INFO_ENTITY)
                .where(PROFILE_INFO_ENTITY.ID.in(ids))
                .fetchInto(ProfileInfoEntity.class);
    }

    @Override
    public Optional<ProfileInfoEntity> findByIdWithCountryAndSkills(UUID userId) {
        Result<Record> result = dsl.select()
                .from(PROFILE_INFO_ENTITY)
                .leftOuterJoin(COUNTRY_ENTITY).on(COUNTRY_ENTITY.ID.eq(PROFILE_INFO_ENTITY.COUNTRY_ID))
                .leftOuterJoin(SKILL_ENTITY).on(SKILL_ENTITY.USER_ID.eq(PROFILE_INFO_ENTITY.ID))
                .where(PROFILE_INFO_ENTITY.ID.eq(userId))
                .fetch();

        Optional<ProfileInfoEntity> optionalProfile = Optional.empty();
        if (result.isNotEmpty()) {
            ProfileInfoEntity profileInfoEntity = result.into(PROFILE_INFO_ENTITY).into(ProfileInfoEntity.class).get(0);

            CountryEntity countryEntity = result.into(COUNTRY_ENTITY).into(CountryEntity.class).get(0);
            profileInfoEntity.setCountry(countryEntity.getId() != null ? countryEntity : null);

            List<SkillEntity> skills = result.into(SKILL_ENTITY).into(SkillEntity.class);
            profileInfoEntity.setSkills(skills.get(0).getTagId() != null ? skills.stream()
                    .map(skillEntity -> new SkillResponse(skillEntity.getTagId(), null))
                    .toList() : null);

            optionalProfile = Optional.of(profileInfoEntity);
        }
        return optionalProfile;
    }

    @Override
    public int updateUsername(UUID id, String username) {
        return dsl.update(PROFILE_INFO_ENTITY)
                .set(PROFILE_INFO_ENTITY.USERNAME, username)
                .where(PROFILE_INFO_ENTITY.ID.eq(id))
                .execute();
    }

    @Override
    public int updateRealName(UUID id, String name) {
        return dsl.update(PROFILE_INFO_ENTITY)
                .set(PROFILE_INFO_ENTITY.REAL_NAME, name)
                .where(PROFILE_INFO_ENTITY.ID.eq(id))
                .execute();
    }

    @Override
    public int updateAboutMe(UUID id, String aboutMe) {
        return dsl.update(PROFILE_INFO_ENTITY)
                .set(PROFILE_INFO_ENTITY.ABOUT_ME, aboutMe)
                .where(PROFILE_INFO_ENTITY.ID.eq(id))
                .execute();
    }

    @Override
    public int updateCountryId(UUID id, Short countryId) {
        return dsl.update(PROFILE_INFO_ENTITY)
                .set(PROFILE_INFO_ENTITY.COUNTRY_ID, countryId)
                .where(PROFILE_INFO_ENTITY.ID.eq(id))
                .execute();
    }

    @Override
    public int updateAccountStatus(UUID id, AccountStatus status) {
        return dsl.update(PROFILE_INFO_ENTITY)
                .set(PROFILE_INFO_ENTITY.ACCOUNT_STATUS, status.name())
                .where(PROFILE_INFO_ENTITY.ID.eq(id))
                .execute();
    }

    @Override
    public void save(ProfileInfoEntity entity) {
        dsl.insertInto(PROFILE_INFO_ENTITY)
                .set(dsl.newRecord(PROFILE_INFO_ENTITY, entity))
                .execute();
    }

    @Override
    public int updateRating(UUID id, Long updatedRating) {
        return dsl.update(PROFILE_INFO_ENTITY)
                .set(PROFILE_INFO_ENTITY.RATING, updatedRating)
                .where(PROFILE_INFO_ENTITY.ID.eq(id))
                .execute();
    }
}
