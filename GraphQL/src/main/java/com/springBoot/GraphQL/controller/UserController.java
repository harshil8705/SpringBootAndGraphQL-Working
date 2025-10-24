package com.springBoot.GraphQL.controller;

import com.springBoot.GraphQL.dto.UserInput;
import com.springBoot.GraphQL.entity.User;
import com.springBoot.GraphQL.service.UserServiceImpl;
import com.springBoot.GraphQL.util.UserBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserBuilder userBuilder;

    @MutationMapping
    public User addNewUser(@Argument("input") UserInput input) {

        User user = userBuilder.buildUserFromUserInput(input);

        return userService.addNewUser(user);

    }

    @QueryMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();

    }

    @QueryMapping
    public User getUserById(@Argument Long userId) {

        return userService.getUserById(userId);

    }

    @MutationMapping
    public User updateUserById(@Argument Long userId, @Argument("input") UserInput input) {

        User user = userBuilder.buildUserFromUserInput(input);

        return userService.updateUserById(userId, user);

    }

    @MutationMapping
    public String deleteUserById(@Argument Long userId) {

        return userService.deleteUserById(userId);

    }

}
