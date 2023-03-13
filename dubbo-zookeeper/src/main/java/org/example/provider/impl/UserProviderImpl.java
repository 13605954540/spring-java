package org.example.provider.impl;

import org.apache.dubbo.config.annotation.Service;
import org.example.bean.User;
import org.example.provider.UserProvider;

@Service
public class UserProviderImpl implements UserProvider {


    @Override
    public User getUser(User user) {
        user.setAge(user.getAge() + 1);
        user.setId(user.getId() + 1);
        return user;
    }
}
