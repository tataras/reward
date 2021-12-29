package com.tatara.reward.user;

import com.tatara.reward.exceptions.ObjectNotExistException;
import com.tatara.reward.user.repository.UserRepository;
import com.tatara.reward.user.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);

    @Test
    public void shouldReturnUser() {
        // given
        UserService userService = new UserService(userRepository);
        when(userRepository.findById(1L)).thenReturn(Optional.of(new UserEntity(1L, "Aaa", "Bbbb")));

        // when
        UserEntity userEntity = userService.findById(1L);

        // then
        assertEquals(Long.valueOf(1), userEntity.getId());
        assertEquals("Aaa", userEntity.getFirstName());
        assertEquals("Bbbb", userEntity.getLastName());
    }

    @Test
    public void shouldThrowException() {
        // given
        UserService userService = new UserService(userRepository);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // when
        Exception exception = assertThrows(ObjectNotExistException.class, () -> userService.findById(1L));

        // then
        assertEquals("User with id 1 not found", exception.getMessage());
    }
}
