package com.baizhi.service;

import com.baizhi.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 创建者：xw
 * 接口的作用：
 * 创建时间：2020/9/23
 */
public interface UserService extends IService<User> {

    List<User> queryAllUser(Integer page,Integer rows);

    Integer userCount();

    List<User> queryView(String sex);

    List<User> queryMap(String sex);

    User queryById(String id);

    void updateUser(User user);
}
