package co.edu.icesi.introspringboot2.mapper;

import co.edu.icesi.introspringboot2.dto.RoleDTO;
import co.edu.icesi.introspringboot2.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO dto);
}
