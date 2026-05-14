package com.example.User.mapper;

import com.example.User.entity.User;
import com.example.User.entity.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDTO toDTO(User user);

    User toUser(UserDTO dto);

    void updateUserFromDto(UserDTO dto,
                           @MappingTarget User user);
}
