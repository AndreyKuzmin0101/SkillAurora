package ru.skillaurora.profileservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillaurora.profileservice.client.ArticleServiceClient;
import ru.skillaurora.profileservice.dto.request.AboutMeRequest;
import ru.skillaurora.profileservice.dto.request.BaseProfileRequest;
import ru.skillaurora.profileservice.dto.request.SkillsRequest;
import ru.skillaurora.profileservice.dto.request.TagRequest;
import ru.skillaurora.profileservice.dto.response.AccountStatus;
import ru.skillaurora.profileservice.dto.response.BaseProfileInfoResponse;
import ru.skillaurora.profileservice.dto.response.ProfileInfoResponse;
import ru.skillaurora.profileservice.dto.response.SkillResponse;
import ru.skillaurora.profileservice.exception.model.BadRequestException;
import ru.skillaurora.profileservice.exception.model.CountryNotFoundException;
import ru.skillaurora.profileservice.exception.model.ProfileNotFoundException;
import ru.skillaurora.profileservice.mapper.ProfileInfoMapper;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.CountryEntity;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.ProfileInfoEntity;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.SkillEntity;
import ru.skillaurora.profileservice.repository.jooq.CountryRepository;
import ru.skillaurora.profileservice.repository.jooq.ProfileInfoRepository;
import ru.skillaurora.profileservice.repository.jooq.SkillRepository;
import ru.skillaurora.profileservice.service.ProfileInfoService;
import ru.skillaurora.profileservice.util.ProfileInfoConstants;
import ru.skillaurora.profileservice.util.ProfileInfoUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileInfoServiceImpl implements ProfileInfoService {

    private final ArticleServiceClient articleServiceClient;

    private final ProfileInfoRepository profileInfoRepository;
    private final CountryRepository countryRepository;
    private final SkillRepository skillRepository;

    private final ProfileInfoMapper profileInfoMapper;

    @Override
    public ProfileInfoResponse getById(UUID id) {
        ProfileInfoEntity profileInfoEntity = profileInfoRepository.findByIdWithCountryAndSkills(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));

        if (profileInfoEntity.getAccountStatus().equals(AccountStatus.DELETED.name())) {
            throw new ProfileNotFoundException(id);
        }

        if (profileInfoEntity.getSkills() != null) {
            List<Long> tagIds = profileInfoEntity.getSkills().stream().map(SkillResponse::id).toList();
            List<SkillResponse> skills = articleServiceClient.getAllByIds(tagIds);
            profileInfoEntity.setSkills(skills);
        }


        return profileInfoMapper.toResponse(profileInfoEntity);
    }

    @Transactional
    @Override
    public void updateUsername(UUID id, String username) {
        Optional<ProfileInfoEntity> optionalProfile = profileInfoRepository.findByUsername(username);
        if (optionalProfile.isPresent()) {
            if (optionalProfile.get().getId().equals(id)) return;
            throw new BadRequestException("Пользователь с username = %s уже существует".formatted(username));
        }

        if (profileInfoRepository.updateUsername(id, username) == 0)
            throw new ProfileNotFoundException(id);
    }

    @Transactional
    @Override
    public void updateRealName(UUID id, String name) {
        if (profileInfoRepository.updateRealName(id, name) == 0)
            throw new ProfileNotFoundException(id);
    }

    @Transactional
    @Override
    public void updateAboutMe(UUID id, AboutMeRequest aboutMe) {
        if (profileInfoRepository.updateAboutMe(id, aboutMe.aboutMe()) == 0)
            throw new ProfileNotFoundException(id);
    }

    @Transactional
    @Override
    public void updateCountry(UUID id, String countryCode) {
        CountryEntity countryEntity = countryRepository.findByCode(countryCode)
                .orElseThrow(() -> new CountryNotFoundException(countryCode));

        if (profileInfoRepository.updateCountryId(id, countryEntity.getId()) == 0) {
            throw new ProfileNotFoundException(id);
        }
    }

    @Transactional
    @Override
    public void updateSkills(UUID id, SkillsRequest skills) {
        profileInfoRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));

        skillRepository.deleteAllByUserId(id);

        List<Long> tagIds = new ArrayList<>();
        if (skills.skillIds() != null) tagIds.addAll(skills.skillIds());

        List<TagRequest> newSkills = skills.newSkills();
        if (newSkills != null && !newSkills.isEmpty()) {
            tagIds.addAll(
                    articleServiceClient.saveAll(skills.newSkills())
            );
        }

        if (!tagIds.isEmpty()) {
            skillRepository.saveAll(
                    tagIds.stream()
                            .distinct()
                            .map(tagId -> new SkillEntity(id, tagId))
                            .toList()
            );
        }
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        if (profileInfoRepository.updateAccountStatus(id, AccountStatus.DELETED) == 0)
            throw new ProfileNotFoundException(id);
    }

    @Override
    public List<BaseProfileInfoResponse> getAllBaseInfoByIds(List<UUID> userIds) {
        return profileInfoMapper.toBaseResponse(
                ProfileInfoUtil.processProfileInfo(profileInfoRepository.findAll(userIds))
        );
    }

    @Override
    public BaseProfileInfoResponse getBaseInfoById(UUID userId) {
        ProfileInfoEntity profileInfoEntity = profileInfoRepository.findById(userId)
                .orElseThrow(() -> new ProfileNotFoundException(userId));

        return profileInfoMapper.toBaseResponse(
                ProfileInfoUtil.processProfileInfo(profileInfoEntity)
        );
    }

    @Transactional
    @Override
    public void create(BaseProfileRequest baseProfile) {
        profileInfoRepository.findByUsername(baseProfile.username())
                .orElseThrow(() -> new BadRequestException("Пользователь с username = %s уже существует"
                        .formatted(baseProfile.username())
                ));

        ProfileInfoEntity profileInfoEntity = profileInfoMapper.toEntity(baseProfile);
        profileInfoEntity.setAccountStatus(AccountStatus.ACTIVE.name());
        profileInfoEntity.setRegisterDate(LocalDate.now());
        profileInfoEntity.setProfilePicture(ProfileInfoConstants.ACTIVE_DEFAULT_PROFILE_PICTURE);
        profileInfoRepository.save(profileInfoEntity);
    }

    @Override
    public BaseProfileInfoResponse getBaseInfoByUsername(String username) {
        ProfileInfoEntity profileInfoEntity = profileInfoRepository.findByUsername(username)
                .orElseThrow(() -> new ProfileNotFoundException(username));

        return profileInfoMapper.toBaseResponse(
                ProfileInfoUtil.processProfileInfo(profileInfoEntity)
        );
    }
}
