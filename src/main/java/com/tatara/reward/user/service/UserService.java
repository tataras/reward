package com.tatara.reward.user.service;

import com.tatara.reward.exceptions.ObjectNotExistException;
import com.tatara.reward.user.UserEntity;
import com.tatara.reward.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserEntity user) {
         userRepository.save(user);
    }

    public UserEntity findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotExistException("User with id " + userId + " not found"));
    }
}
