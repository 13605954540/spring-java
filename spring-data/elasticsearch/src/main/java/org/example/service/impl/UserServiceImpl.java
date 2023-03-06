package org.example.service.impl;

import org.example.base.impl.BaseServiceImpl;
import org.example.bean.User;
import org.example.condition.UserCondition;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserCondition, UserRepository> implements UserService {
    
}
