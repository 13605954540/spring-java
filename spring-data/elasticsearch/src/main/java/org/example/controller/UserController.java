package org.example.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.example.base.BaseController;
import org.example.base.BaseResp;
import org.example.bean.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, UserService> {

    @GetMapping("/save-users")
    public BaseResp<List<User>> save() {
        List<User> users = Lists.newArrayList(
                new User().setId(1L).setName("小华").setAge(1).setHobby("打球呀"),
                new User().setId(2L).setName("小绿").setAge(2).setHobby("打游戏"),
                new User().setId(3L).setName("大蓝").setAge(3).setHobby("赖床")
        );
        return super.insertBatch(users);
    }
}
