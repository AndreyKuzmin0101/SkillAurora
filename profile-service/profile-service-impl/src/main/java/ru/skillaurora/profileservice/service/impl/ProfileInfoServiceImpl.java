package ru.skillaurora.profileservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillaurora.profileservice.dto.request.AboutMeRequest;
import ru.skillaurora.profileservice.dto.request.BaseProfileRequest;
import ru.skillaurora.profileservice.dto.request.SkillsRequest;
import ru.skillaurora.profileservice.dto.response.AccountStatus;
import ru.skillaurora.profileservice.dto.response.BaseProfileInfoResponse;
import ru.skillaurora.profileservice.dto.response.ProfileInfoResponse;
import ru.skillaurora.profileservice.dto.response.SkillResponse;
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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileInfoServiceImpl implements ProfileInfoService {

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
            //TODO: обращение к сервису статей для получения названий
            List<SkillResponse> skills = null;
        }

//        profileInfoEntity.setSkills(skills);

        return profileInfoMapper.toResponse(profileInfoEntity);
    }

    @Transactional
    @Override
    public void updateUsername(UUID id, String username) {
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
                .orElseThrow(() ->  new CountryNotFoundException(countryCode));

        if (profileInfoRepository.updateCountryId(id, countryEntity.getId()) == 0) {
            throw new ProfileNotFoundException(id);
        };
    }

    @Transactional
    @Override
    public void updateSkills(UUID id, SkillsRequest skills) {
        profileInfoRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));

        //TODO: обращение к сервису статей
        List<Long> tagIds = null;

        skillRepository.saveAll(
                tagIds.stream()
                        .map(tagId -> new SkillEntity(id, tagId))
                        .toList()
        );
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
