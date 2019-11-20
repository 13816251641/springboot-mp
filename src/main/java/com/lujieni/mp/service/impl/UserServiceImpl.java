package com.lujieni.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lujieni.mp.dao.UserMapper;
import com.lujieni.mp.entity.User;
import com.lujieni.mp.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @Auther ljn
 * @Date 2019/11/19
 * 继承 ServiceImpl，实现 UserService 接口
 *      ServiceImpl：该类实现了 IService 接口，需要两个泛型参数
 *           参数1：对应的 Mapper 类
 *           参数2：对应的 Pojo 类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    





}
