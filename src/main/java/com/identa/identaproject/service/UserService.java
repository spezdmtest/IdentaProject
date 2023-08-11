package com.identa.identaproject.service;

import com.identa.identaproject.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll();
    void addUser(UserDTO userDTO);
}
