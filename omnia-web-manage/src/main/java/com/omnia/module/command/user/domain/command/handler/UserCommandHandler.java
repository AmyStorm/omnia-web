package com.omnia.module.command.user.domain.command.handler;

import com.omnia.module.command.user.domain.User;
import com.omnia.module.command.user.domain.command.CreateUserCommand;
import com.omnia.module.command.user.domain.command.LoginCommand;
import com.omnia.module.command.user.domain.command.LogoutCommand;
import com.omnia.module.command.user.vo.LoginSession;
import com.omnia.module.query.user.repository.UserQueryRepository;
import com.omnia.module.query.user.repository.impl.UserQueryRepositoryImpl;
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
    private UserQueryRepository userQueryRepository;

    @CommandHandler
    public void createUser(CreateUserCommand command){
        final String id = UUID.randomUUID().toString();
        final String password = BCrypt.hashpw(command.getPassword(), BCrypt.gensalt());
        User user = new User(id, command.getUsername() , password);
        repository.add(user);
//        UserQueryRepositoryImpl.inMemoryUser.put(id, user);
    }

    @CommandHandler
    public LoginSession authenticationUser(LoginCommand loginCommand){
        User userreal = repository.load("56ef6121-90c4-45f1-ad82-c59d8f8d30bd");
        User user = userQueryRepository.getUserByName(loginCommand.getUsername());
//        User user = repository.load(new UserIdentifier(loginCommand.getUsername()));
        if(userreal != null && userreal.authentication(loginCommand.getPassword())){
            return new LoginSession(userreal.getIdentifier(), userreal.getUserName());
        }else{
            return null;
        }
    }
    @CommandHandler
    public void logout(LogoutCommand logoutCommand){
        User user = repository.load(logoutCommand.getIdentifier());
        user.logout();
    }
}
