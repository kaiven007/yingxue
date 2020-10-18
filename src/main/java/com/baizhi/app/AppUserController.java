package com.baizhi.app;

import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;
import com.baizhi.config.SendSmsUtil;
/**
 * 创建者：xw
 * 类的作用：前台用户
 * 创建时间：2020/10/11
 */
@RestController
@RequestMapping("app")
public class AppUserController {

    @Autowired
    private UserService  userService;
    @Autowired
    private RedisTemplate redisTemplate;

    //前台用户登录
    @RequestMapping("login")
    public Map<String, Object> login(String phone,String phoneCode){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
         List<User> list=userService.list(queryWrapper.eq("mobile",phone));
         if (list==null) {
             User  user=new User();
             user.setUsername(null);
             user.setMobile(phone);
             user.setSign(null);
             user.setHeadShow(null);
             user.setStatus("1");
             user.setRegTime(new Date());
             user.setScore(null);
             user.setWechat(null);
             userService.save(user);
             return result(phone);
         }
        //验证验证码是否正确
        ValueOperations opsForCluster = redisTemplate.opsForValue();
        /* if (!phoneCode.equals(opsForCluster.get("phone-code"))){
               String  message="验证码错误";
               return result(message);
          } */
         return result(list);
    }

    //前台用户获取验证码
    @RequestMapping("getPhoneCode")
    public Map<String, Object> getPhoneCode(String phone){
        //获取验证码随机数并保存
        Random r = new Random();
        String code = "" + r.nextInt(10) + r.nextInt(10) + r.nextInt(10) + r.nextInt(10);
        System.out.println(code);
        //存储到Redis
        //ValueOperations opsForCluster = redisTemplate.opsForValue();
       // opsForCluster.set( "phone-code", code, 5, TimeUnit.MINUTES);
        //发送验证码到用户手机
        SendSmsUtil.sendPhoneCode(phone, code);
        return result(phone);
    }

    //首页展示部分信息
    @RequestMapping("queryByUserDetail")
    public  Map<String, Object>  queryByUserDetail(String userId){

        return result(1);
    }

    //返回结果
    public Map<String, Object> result(Object obj) {
        Map<String, Object> result = new HashMap<>();
        result.put("status", 100);
        result.put("message", "登录成功！~");
        result.put("data", obj);
        return result;
    }
}
