package com.baizhi.service.impl;

import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
