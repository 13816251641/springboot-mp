package com.lujieni.mp;

import com.lujieni.mp.domain.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Function;


/**
 * @Auther ljn
 * @Date 2019/11/17
 * 测试使用lambda表达式
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LambdaTest {

    public void useLambda(Function<User, ?> f, Integer num){
        User user = new User();
        user.setAge(28);
        Object apply = f.apply(user);
        System.out.println(apply);
    }

    @Test
    public void test(){
        useLambda(User::getAge,20);//User::getAge代表调用User的getAge方法
    }
}
