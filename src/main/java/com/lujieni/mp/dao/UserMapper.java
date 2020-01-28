package com.lujieni.mp.dao;

import com.lujieni.mp.domain.bo.UserCount;
import com.lujieni.mp.domain.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lujieni
 * @since 2019-11-20
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectMethod();

    List<User> selectMethod2();

    List<UserCount> selectMethod3(User user);

    List<User> selectMethod4(User user);

}
