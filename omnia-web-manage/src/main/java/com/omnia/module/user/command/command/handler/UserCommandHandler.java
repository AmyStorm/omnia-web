package com.omnia.module.user.command.command.handler;

import com.omnia.module.user.command.command.CreateUserCommand;
import com.omnia.module.user.command.command.LoginCommand;
import com.omnia.module.user.command.command.LogoutCommand;
import com.omnia.module.user.command.domain.User;
import com.omnia.module.user.command.vo.LoginSession;
import com.omnia.module.user.query.repository.UserQueryRepository;
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
//        User userreal = repository.load("7a861c9a-4c05-4cd8-bdd7-f7befb4d3054");
        User user = userQueryRepository.getUserByName(loginCommand.getUsername());
//        User user = repository.load(new UserIdentifier(loginCommand.getUsername()));
        if(user != null && user.authentication(loginCommand.getPassword())){
            return new LoginSession(user.getIdentifier(), user.getUserName());
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
