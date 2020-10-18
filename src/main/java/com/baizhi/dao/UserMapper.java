package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {

    List<User> selectAllUser(@Param("begin") Integer begin, @Param("rows") Integer rows);

    List<User> selectView(String sex);

    List<User> selectUserMap(String sex);
}
