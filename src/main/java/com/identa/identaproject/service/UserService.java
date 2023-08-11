package com.identa.identaproject.service;

import com.identa.identaproject.dto.UserDTO;
import com.identa.identaproject.entities.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll();
    void addUser(UserDTO userDTO);
    void save(User user);
    User findByName(String email);
}
