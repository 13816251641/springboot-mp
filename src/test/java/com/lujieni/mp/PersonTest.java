package com.lujieni.mp;

import com.lujieni.mp.domain.po.Person;
import com.lujieni.mp.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther ljn
 * @Date 2020/2/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PersonTest {
    @Autowired
    private PersonService personService;

    @Test
    public void test01(){
        List<Person> list = personService.list();
        list.forEach(e->{
            System.out.println(e);
        });
    }
}
