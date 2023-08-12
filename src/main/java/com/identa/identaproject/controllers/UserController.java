package com.identa.identaproject.controllers;

import com.identa.identaproject.dto.UserDTO;
import com.identa.identaproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String list (Model model) {
        List<UserDTO> listUsers = userService.getAll();
        model.addAttribute("users", listUsers);
        return "users";
    }

    @PostMapping
    public ResponseEntity<Void> addUser(UserDTO userDTO) {
        userService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @MessageMapping("/users")
    public void messageAddUser(UserDTO userDTO) {
        userService.addUser(userDTO);
    }
}
