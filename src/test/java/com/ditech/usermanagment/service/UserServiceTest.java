package com.ditech.usermanagment.service;

import com.ditech.usermanagment.exception.EntityNotFoundException;
import com.ditech.usermanagment.model.User;
import com.ditech.usermanagment.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSaveUser() {
        // Organizar
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");

        User savedUser = new User();
        savedUser.setId(Long.valueOf(1L));
        savedUser.setUsername("testuser");
        savedUser.setEmail("test@example.com");

        when(userRepository.save(user)).thenReturn(savedUser);

        // Ejecutar
        User result = userService.saveUser(user);

        // Verificar
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserByIdThrowsException() {
        // Organizar
        Long userId = (Long) 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Ejecutar y Verificar
        assertThrows(EntityNotFoundException.class, () -> {
            userService.getUserById(userId);
        });

        verify(userRepository, times(1)).findById(userId);
    }
}
