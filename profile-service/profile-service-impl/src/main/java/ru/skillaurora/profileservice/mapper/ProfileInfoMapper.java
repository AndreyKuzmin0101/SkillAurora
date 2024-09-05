package ru.skillaurora.profileservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillaurora.profileservice.dto.request.BaseProfileRequest;
import ru.skillaurora.profileservice.dto.response.BaseProfileInfoResponse;
import ru.skillaurora.profileservice.dto.response.ProfileInfoResponse;
import ru.skillaurora.profileservice.model.jooq.schema.tables.pojos.ProfileInfoEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface ProfileInfoMapper {

    @Mapping(source = "userId", target = "id")
    ProfileInfoEntity toEntity(BaseProfileRequest request);

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "accountStatus", target = "status")
    ProfileInfoResponse toResponse(ProfileInfoEntity entity);

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "accountStatus", target = "status")
    BaseProfileInfoResponse toBaseResponse(ProfileInfoEntity entity);

    List<BaseProfileInfoResponse> toBaseResponse(List<ProfileInfoEntity> entities);

}
