package com.identa.identaproject.mapper;

import com.identa.identaproject.dto.ProductDTO;
import com.identa.identaproject.dto.UserDTO;
import com.identa.identaproject.entities.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    User toUser(UserDTO dto);

    @InheritInverseConfiguration
    UserDTO fromProduct(User user);

    List<User> toUserList(List<UserDTO> userDTOS);

    List<UserDTO> fromUserList(List<User> users);
}
