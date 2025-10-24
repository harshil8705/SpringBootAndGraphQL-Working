package com.springBoot.GraphQL.service;

import com.springBoot.GraphQL.entity.User;

import java.util.List;

public interface UserService {

    User addNewUser(User user);

    List<User> getAllUsers();

    User getUserById(Long userId);

    User updateUserById(Long userId, User user);

    String deleteUserById(Long userId);

}
