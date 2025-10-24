package com.springBoot.GraphQL.util;

import com.springBoot.GraphQL.dto.UserInput;
import com.springBoot.GraphQL.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {

    public User buildUserFromUserInput(UserInput input) {

        return User.builder()
                .username(input.getUsername())
                .email(input.getEmail())
                .phoneNumber(input.getPhoneNumber())
                .build();

    }

}
