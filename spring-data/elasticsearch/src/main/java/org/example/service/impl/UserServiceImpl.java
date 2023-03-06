package org.example.service.impl;

import io.netty.util.internal.StringUtil;
import org.example.base.CusQueryBuilder;
import org.example.base.Page;
import org.example.base.impl.BaseServiceImpl;
import org.example.bean.User;
import org.example.condition.UserCondition;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserCondition, UserRepository> implements UserService {

    @Autowired
    public ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public Page<User> findPage(User user, Page page) {
        return Page.of(
                elasticsearchRestTemplate.search(
                    new CusQueryBuilder<User>()
                        .eq(!StringUtil.isNullOrEmpty(user.getName()), "name", user.getName())
                        .eq(user.getAge() != null, "age", user.getAge())
                        .like(!StringUtil.isNullOrEmpty(user.getHobby()), "hobby", user.getHobby())
                        .limit(page.getCurrent(), page.getSize())
                        .orderByDesc("age")
                        .build(),
                        User.class),
                page
        );
    }
}
