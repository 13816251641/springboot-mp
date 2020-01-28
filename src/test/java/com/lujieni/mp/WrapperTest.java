package com.lujieni.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lujieni.mp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Function;

/**
 * @Auther ljn
 * @Date 2019/11/16
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WrapperTest {



    @Test
    public void test(){
        String sqlSelect = new QueryWrapper<User>()
                .apply("date_format(dateColumn,'%Y-%m-%d') = {0}", "2008-08-08").getSqlSegment();
        System.out.println(sqlSelect);
        String sqlSegment = new QueryWrapper<User>().eq("name", "lujieni").getSqlSegment();
        System.out.println(sqlSegment);
        Function<User, Integer> getAge = User::getAge;
        new QueryWrapper<User>().lambda().eq(User::getAge,23);
    }


    @Test
    public void test2(){
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.likeRight(User::getName, "张")
               .nested(i -> i.lt(User::getAge, 40).or().isNotNull(User::getEmail));
        System.out.println(lambdaQuery.getSqlSegment());

    }
}
