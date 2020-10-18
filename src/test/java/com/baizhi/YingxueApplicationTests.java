package com.baizhi;

import com.baizhi.dao.*;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Category;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Key;
import java.util.List;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = YingxueApplication.class)
class YingxueApplicationTests {

    @Autowired
    private AdminMapper  adminMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper  userMapper;
    @Autowired
    private VideoMapper  videoMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRed; //操作key

    //测试管理员用户
    @Test
    public void testAdmin() {
         Admin admin=adminMapper.selectById(1);
        //System.out.println(category);
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
        //模糊查询
        //queryWrapper.like("name","软件");
        //查询所有
        List<Admin> list=adminMapper.selectList(queryWrapper);
        for(Admin admin1:list) {
            System.out.println(admin1);
        }
    }
    //测试普通用户
    @Test
    public void testUser() {

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        //模糊查询
        //queryWrapper.like("name","软件");
        //查询所有
        List<User> list=userMapper.selectList(queryWrapper);
        for(User user:list) {
            System.out.println(user);
        }
    }
    //测试类别
    @Test
    public void testCategory() {
        //Category category=categoryDao.selectById(1);
        //System.out.println(category);
        QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
        //模糊查询
        queryWrapper.eq("level","1");
        //查询所有
        List<Category> category=categoryMapper.selectList(queryWrapper);
        for(Category category1:category) {
            System.out.println(category1);
        }
/*
       //分页查询
        IPage<Category> page=new Page<>(2,5);

        IPage<Category> iPage=categoryDao.selectPage(page,null);
        System.out.println("信息总数："+iPage.getTotal());
        List<Category> list=iPage.getRecords();
        for (Category category:list){
            System.out.println(category);
        }*/
    }
    //测试视频
    @Test
    public void testVideo() {

        QueryWrapper<Video> queryWrapper=new QueryWrapper<>();
        //模糊查询
        //queryWrapper.like("name","软件");
        //查询所有
        List<Video> list=videoMapper.selectList(queryWrapper.like("title","美食"));
        for(Video video:list) {
            System.out.println(video);
        }
    }

    //测试Redis缓存
    @Test
    public void testRedes() {

     /* ValueOperations  operations=redisTemplate.opsForValue();
      operations.set("name","凯文");
        System.out.println(operations.get("name"));*/

        Set<String> keys=stringRed.keys("*");
        for(String key:keys){
            System.out.println(key);
        }
    }

}
