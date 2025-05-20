package co.edu.icesi.introspringboot2.mapper;

import co.edu.icesi.introspringboot2.dto.UserRequest;
import co.edu.icesi.introspringboot2.dto.UserResponse;
import co.edu.icesi.introspringboot2.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    UserResponse toDTO(User user);
    User toEntity(UserRequest dto);
}