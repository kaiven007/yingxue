package com.baizhi.controller;

import com.baizhi.anno.YxueLog;
import com.baizhi.config.Result;
import com.baizhi.config.SendSmsUtil;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/9/23
 */
@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate  redisTemplate;

    @RequestMapping("adminLogin")
    public  String adminLogin(Admin admin,String code,HttpSession session){
            //验证验证码是否正确
            //ValueOperations opsForCluster = redisTemplate.opsForValue();
            //if (!code.equals(opsForCluster.get("phone-code"))) throw new  RuntimeException("验证码错误") ;
            //登录验证
            QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", admin.getUsername());
            Admin admin1 = adminService.getOne(queryWrapper);
             if (admin1==null) throw new  RuntimeException("账号不存在");
             if (!admin.getPassword().equals(admin1.getPassword())) throw new  RuntimeException("密码不正确");
            session.setAttribute("username", admin.getUsername());
            System.out.println(admin1);
        return  "forward:/main.jsp";
    }
    //短信验证
  /* @ResponseBody
    @RequestMapping("sendPhoneCode")
    public Result<?> sendPhoneCode(String phone){
        System.out.println(phone);
        //获取验证码随机数并保存
        Random r = new Random();
        String code = "" + r.nextInt(10) + r.nextInt(10) + r.nextInt(10) + r.nextInt(10);
        System.out.println(code);
        //存储到Redis
        ValueOperations opsForCluster = redisTemplate.opsForValue();
        opsForCluster.set( "phone-code", code, 5, TimeUnit.MINUTES);
        //发送验证码到用户手机
        String responseData = SendSmsUtil.sendPhoneCode(phone, code);
        return  Result.ok(responseData);
    }*/
    //退出
    @RequestMapping("exit")
    public  String exit( HttpSession session){

       session.removeAttribute("username");
        return  "redirect:/login.jsp";
    }
}
