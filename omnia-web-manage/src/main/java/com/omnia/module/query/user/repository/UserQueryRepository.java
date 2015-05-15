package com.omnia.module.query.user.repository;


import com.omnia.module.command.user.domain.User;

/**
 * Created by Administrator on 2015/5/12.
 */
public interface UserQueryRepository {
    User getUserByName(String name);

}
