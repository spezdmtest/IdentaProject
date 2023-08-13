package com.identa.identaproject.controllers;

import com.identa.identaproject.dto.UserDTO;
import com.identa.identaproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    private List<UserDTO> listOfUsers = new ArrayList<>();

    private UserDTO userDTO = new UserDTO(1L,"test@test.com");

    @Test
    void getUserAll() throws Exception {
        listOfUsers.add(UserDTO.builder()
                .id(1L)
                .email("test@test.com")
                .build());

        ResultActions response = mockMvc.perform(get("/users", listOfUsers));
        response.andExpect(status().isOk())
                .andDo(print());
    }
}