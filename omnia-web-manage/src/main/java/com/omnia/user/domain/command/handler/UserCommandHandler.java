package com.omnia.user.domain.command.handler;

import com.omnia.user.domain.User;
import com.omnia.user.domain.command.CreateUserCommand;
import com.omnia.user.domain.command.LoginCommand;
import com.omnia.user.domain.command.LogoutCommand;
import com.omnia.user.repository.UserRepository;
import com.omnia.user.repository.impl.UserRepositoryImpl;
import com.omnia.user.vo.LoginSession;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by khaerothe on 2015/5/13.
 */
@Component
public class UserCommandHandler {

    @Autowired
    @Qualifier("userRepository")
    private Repository<User> repository;

    @Autowired
    private UserRepository userRepository;

    @CommandHandler
    public void createUser(CreateUserCommand command){
        final String id = UUID.randomUUID().toString();
        final String password = BCrypt.hashpw(command.getPassword(), BCrypt.gensalt());
        User user = new User(id, command.getUsername(), password);
        repository.add(user);
        UserRepositoryImpl.inMemoryUser.put(id, user);
    }

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
