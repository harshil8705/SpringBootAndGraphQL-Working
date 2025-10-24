package com.springBoot.GraphQL.service;

import com.springBoot.GraphQL.entity.User;
import com.springBoot.GraphQL.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addNewUser(User user) {

        User newUser = User.builder()
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .orders(new ArrayList<>())
                .build();

        return userRepository.save(newUser);

    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    @Override
    public User getUserById(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with userId: " + userId + " doesn't exists."));

    }

    @Override
    public User updateUserById(Long userId, User user) {

        User oldUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with userId: " + userId + " doesn't exists."));

        oldUser.setEmail(user.getEmail());
        oldUser.setUsername(user.getUsername());
        oldUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(oldUser);
    }

    @Override
    public String deleteUserById(Long userId) {

        User userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with userId: " + userId + " doesn't exists."));

        userRepository.delete(userToDelete);

        return "User with userId: " + userId + " removed Successfully.";

    }

}
