package com.ies.spd.spdservices.controller;

import com.alibaba.fastjson.JSON;
import com.ies.spd.spdservices.constants.PageConstants;
import com.ies.spd.spdservices.controller.entity.UserPassword;
import com.ies.spd.spdservices.entity.User;
import com.ies.spd.spdservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Athor: 吴广庆
 * @Date: 2019-11-18
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录接口
     * @param userPassword
     * @return
     */
    @RequestMapping(value = "/login")
    public User login(@RequestBody UserPassword userPassword)throws Exception{
        if(userPassword!=null){
            return userService.login(userPassword.getLoginName(),userPassword.getPassword());
        }
        return null;
    }

    /**
     * 编辑或新建用户
     * @param user
     */
    @RequestMapping(value = "/save")
    public User saveUser(@RequestBody User user)throws DataIntegrityViolationException {
        System.out.println(JSON.toJSONString(user));
        user.setUpdateTime(new Date());
        return userService.save(user);
    }

    /**
     * 获取用户列表
     * @param pageConstants
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUserList")
    public Page<User> getEquipmentList(@RequestBody PageConstants pageConstants) throws Exception{
        System.out.println(JSON.toJSONString(pageConstants));
        Page<User> userPage = userService.getUserList(pageConstants);
        System.out.println(JSON.toJSONString(userPage));
        return userPage;
    }

    /**
     * 修改密码
     * @param userPassword
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/changePassword")
    public int changePassword(@RequestBody UserPassword userPassword) throws Exception{
        System.out.println(JSON.toJSONString(userPassword));
        int caunt = userService.updatePassword(userPassword.getLoginName(),userPassword.getPassword());
        System.out.println(JSON.toJSONString(caunt));
        return caunt;
    }
    /**
     * 禁用用户
     * @param userPassword
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteUser")
    public int deleteUser(@RequestBody UserPassword userPassword) throws Exception{
        System.out.println(JSON.toJSONString(userPassword));
        int caunt = userService.updateIsDelete(userPassword.getLoginName());
        System.out.println(JSON.toJSONString(caunt));
        return caunt;
    }
}
