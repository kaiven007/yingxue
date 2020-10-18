package com.baizhi.service.impl;

import com.baizhi.dao.UserMapper;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/9/23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> queryAllUser(Integer page,Integer rows) {
        Integer  begin=rows*(page-1);
       List<User> list=userMapper.selectAllUser(begin,rows);
        return list;
    }

    @Override
    public Integer userCount() {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        Integer integer=userMapper.selectCount(queryWrapper);
        return  integer;
    }

    @Override
    public List<User> queryView(String sex) {
        List<User> list=userMapper.selectView(sex);
        return list;
    }
    @Override
    public List<User> queryMap(String sex) {
        List<User> list=userMapper.selectUserMap(sex);
        return list;
    }

    @Override
    public User queryById(String id) {

         User user=userMapper.selectById(id);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }
}
