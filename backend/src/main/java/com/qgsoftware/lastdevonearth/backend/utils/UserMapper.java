package com.qgsoftware.lastdevonearth.backend.utils;


import com.qgsoftware.lastdevonearth.backend.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userDetailsToUserDTO(UserDetails userDetails);
}
