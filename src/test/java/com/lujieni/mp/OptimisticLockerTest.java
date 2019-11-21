package com.lujieni.mp;

import com.lujieni.mp.entity.Person;
import com.lujieni.mp.service.IPersonService;
import com.lujieni.mp.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther ljn
 * @Date 2019/11/21
 * 测试乐观锁
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OptimisticLockerTest {
    @Autowired
    private IPersonService iPersonService;

    /**
     * 乐观锁实现方式：
     *
     * 取出记录时，获取当前version
     * 更新时，带上这个version
     * 执行更新时， set version = newVersion where version = oldVersion
     * 如果version不对，就更新失败
     *
     * newVersion会回写到 entity 中
     * 仅支持updateById(id)与update(entity, wrapper)方法
     * 在update(entity, wrapper)方法下,wrapper不能复用!!!
     */
    @Test
    public void test(){
        Person person = iPersonService.getById(6);
        if(iPersonService.updateById(person.setAge(50))){
            System.out.println("更新成功");
            System.out.println(person);
        }else{
            System.out.println("更新失败");
        }
    }
}
