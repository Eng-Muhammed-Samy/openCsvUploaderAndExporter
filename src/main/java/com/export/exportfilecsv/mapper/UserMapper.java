package com.export.exportfilecsv.mapper;

import com.export.exportfilecsv.entity.UserEntity;
import com.export.exportfilecsv.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toModel(UserEntity userEntity);
    UserEntity toEntity(User user);
}