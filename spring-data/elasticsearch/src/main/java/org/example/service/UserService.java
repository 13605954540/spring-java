package org.example.service;

import org.example.base.BaseService;
import org.example.base.Page;
import org.example.bean.User;
import org.example.condition.UserCondition;

public interface UserService extends BaseService<User, UserCondition> {

    Page<User> findPage(User user, Page page);
}
