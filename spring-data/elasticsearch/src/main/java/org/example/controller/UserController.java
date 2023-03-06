package org.example.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.base.BaseController;
import org.example.base.BasePageCondition;
import org.example.base.BaseResp;
import org.example.base.Page;
import org.example.bean.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("用户")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, UserService> {

    @ApiOperation("批量插入用户(固定数据)")
    @GetMapping("/save-users")
    public BaseResp<List<User>> save() {
        List<User> users = Lists.newArrayList(
                new User().setId(1L).setName("小华").setAge(1).setHobby("打球呀"),
                new User().setId(2L).setName("小绿").setAge(2).setHobby("打游戏"),
                new User().setId(3L).setName("大蓝").setAge(3).setHobby("赖床")
        );
        return super.insertBatch(users);
    }

    @ApiOperation("分页查询")
    @PostMapping("/findPage")
    public BaseResp<Page<User>> findPage(@RequestBody BasePageCondition<User> basePageCondition) {
        return BaseResp.ok(service.findPage(basePageCondition.getCondition(), basePageCondition.getPageParam()));
    }
}
