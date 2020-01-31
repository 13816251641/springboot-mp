package com.lujieni.mp.service.impl;

import com.lujieni.mp.domain.po.User;
import com.lujieni.mp.dao.UserMapper;
import com.lujieni.mp.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lujieni
 * @since 2019-11-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
