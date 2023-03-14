package org.example.provider.impl;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.metadata.definition.model.FullServiceDefinition;
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

    @Override
    public User getUser(Long id, String name, Integer age) {
        System.err.println("------------------------ dubbo被请求到了哦 ------------------------");
        return new User().setId(id).setName(name).setAge(age);
    }

    @Override
    public User getUser2(Long id, String name, Integer age) {
        return new User().setId(id).setName(name).setAge(age);
    }

    @Override
    public User getUser3() {
        return new User().setId(1L).setName("1L").setAge(1);
    }
}
