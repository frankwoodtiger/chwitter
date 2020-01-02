package com.chi.chwitter.mapper;

import com.chi.chwitter.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    List<UserDTO> usersToUserDTOs(List<User> user);
}
