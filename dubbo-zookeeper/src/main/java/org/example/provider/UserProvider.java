package org.example.provider;

import org.example.bean.User;

public interface UserProvider {

    User getUser(User user);

    User getUser(Long id, String name, Integer age);

    User getUser2(Long id, String name, Integer age);

    User getUser3();
}
