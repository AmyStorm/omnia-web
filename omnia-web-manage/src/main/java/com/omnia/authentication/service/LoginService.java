package com.omnia.authentication.service;

import com.omnia.authentication.domain.AuthenticationToken;
import com.omnia.authentication.repository.UserRepository;
import com.omnia.authentication.vo.LoginSession;

/**
 * Created by khaerothe on 2015/4/29.
 */
public class LoginService {

    private UserRepository repository = null;
//    private UserRepository repository = new UserRepository();

    public LoginSession login(String userName, String password){
        AuthenticationToken token = repository.authentication(userName, password);
        if(token != null){
            return new LoginSession(token.getId(), userName);
        }else{
            return null;
        }
    }
}
