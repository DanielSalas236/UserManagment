package com.ditech.usermanagment.controller;

import com.ditech.usermanagment.model.User;
import com.ditech.usermanagment.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllUsers() throws Exception {
        // Organizar
        User user1 = new User();
        user1.setId(Long.valueOf(1L));
        user1.setUsername("testuser1");
        user1.setEmail("user1@example.com");
        user1.setActive(true);

        User user2 = new User();
        user2.setId(Long.valueOf(2L));
        user2.setUsername("testuser2");
        user2.setEmail("user2@example.com");
        user2.setActive(true);

        List<User> userList = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(userList);

        // Ejecutar y Verificar
        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Listados de usuarios con éxito")))
                .andExpect(jsonPath("$.value", hasSize(2)))
                .andExpect(jsonPath("$.value[0].id", is(1)))
                .andExpect(jsonPath("$.value[0].username", is("testuser1")))
                .andExpect(jsonPath("$.value[0].email", is("user1@example.com")))
                .andExpect(jsonPath("$.value[1].id", is(2)))
                .andExpect(jsonPath("$.value[1].username", is("testuser2")))
                .andExpect(jsonPath("$.value[1].email", is("user2@example.com")));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testCreateUser() throws Exception {
        // Organizar
        User userRequest = new User();
        userRequest.setUsername("newuser");
        userRequest.setEmail("newuser@example.com");
        userRequest.setActive(true);

        User savedUser = new User();
        savedUser.setId(Long.valueOf(3L));
        savedUser.setUsername("newuser");
        savedUser.setEmail("newuser@example.com");
        savedUser.setActive(true);

        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        // Ejecutar y Verificar
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(200)))
                .andExpect(jsonPath("$.message", is("Usuario creado con éxito")));

        verify(userService, times(1)).saveUser(any(User.class));
    }
}
