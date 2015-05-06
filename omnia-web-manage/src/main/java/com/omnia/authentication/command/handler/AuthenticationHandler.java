package com.omnia.authentication.command.handler;

import com.omnia.authentication.command.LoginCommand;
import com.omnia.authentication.command.LogoutCommand;
import com.omnia.authentication.domain.AuthenticationToken;
import com.omnia.authentication.repository.UserRepository;
import com.omnia.authentication.vo.LoginSession;
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
    public LoginSession authenticationUser(LoginCommand loginCommand){
        AuthenticationToken authenticationToken = userRepository.authentication(loginCommand.getUsername(), loginCommand.getPassword());
        if(authenticationToken != null){
            return new LoginSession(loginCommand.getUsername(), loginCommand.getPassword());
        }else{
            return null;
        }
    }
    @CommandHandler
    public void logout(LogoutCommand logoutCommand){

    }
}
