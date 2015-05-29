package com.omnia.module.user.query.repository;


import com.omnia.module.user.command.domain.User;

/**
 * Created by Administrator on 2015/5/12.
 */
public interface UserQueryRepository {
    User getUserByName(String name);

}
