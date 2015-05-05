package com.omnia.authentication.command;

import com.omnia.authentication.domain.AuthenticationToken;
import com.omnia.authentication.domain.command.LoginCommand;
import com.omnia.authentication.repository.UserRepository;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by khaerothe on 2015/5/5.
 */
@Component
public class AuthenticationHandler {

    @Autowired
    private UserRepository userRepository;

    @CommandHandler
    public AuthenticationToken authenticationUser(LoginCommand loginCommand){
        return userRepository.authentication(loginCommand.getUsername(), loginCommand.getPassword());
    }
}
