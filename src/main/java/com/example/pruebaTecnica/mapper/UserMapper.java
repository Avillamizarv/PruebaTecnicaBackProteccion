package com.example.pruebaTecnica.mapper;

import com.example.pruebaTecnica.dto.UserDTO;
import com.example.pruebaTecnica.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for User
 * @author Adriana Villamizar Vera
 * @since 1.0
 */
@Mapper
public interface UserMapper {
   UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    //DTO to Entity
    /**
     * Convert from DTO User to its entity
     *
     * @param userDTO
     * @return user
     */
    User toUser(UserDTO userDTO);

    //Entity to DTO
    /**
     * Convert from entity user to its DTO
     *
     * @param user
     * @return userDTO
     */
    @InheritInverseConfiguration
    UserDTO toUserDTO(User user);

}
