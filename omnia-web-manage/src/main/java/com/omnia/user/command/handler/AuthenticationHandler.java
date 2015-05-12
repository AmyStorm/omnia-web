package com.omnia.user.command.handler;

import com.omnia.user.command.LoginCommand;
import com.omnia.user.command.LogoutCommand;
import com.omnia.user.domain.User;
import com.omnia.user.repository.UserRepository;
import com.omnia.user.vo.LoginSession;
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
        User user = userRepository.getUserByName(loginCommand.getUsername());
        if(user != null && user.authentication(loginCommand.getPassword())){
            return new LoginSession(loginCommand.getUsername(), loginCommand.getPassword());
        }else{
            return null;
        }
    }
    @CommandHandler
    public void logout(LogoutCommand logoutCommand){

    }
}
