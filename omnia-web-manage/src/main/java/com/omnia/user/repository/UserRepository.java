package com.omnia.user.repository;

import com.omnia.user.domain.User;

/**
 * Created by Administrator on 2015/5/12.
 */
public interface UserRepository {
    User getUserByName(String name);
}
