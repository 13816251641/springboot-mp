package com.lujieni.mp.controller;


import com.lujieni.mp.domain.po.PersonVO;
import com.lujieni.mp.service.PersonService;
import org.omg.CORBA.PERSIST_STORE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lujieni
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

/*    @GetMapping("/get_all")
    public List<PersonVO> getAll() {
        return personService.list();
    }*/

    @GetMapping("/get_all")
    public String getAll() {
        return "hello(123)";
    }

    @GetMapping("/save_all")
    public void saveAll() {
        List<PersonVO> list = new ArrayList<>();
        list.add(new PersonVO().setAge(25).setHobbyName("睡觉"));
        list.add(new PersonVO().setAge(12).setHobbyName("玩玩具"));
        personService.saveBatch(list);
    }

    @PostMapping("/save_one")
    public String saveOne(@RequestBody PersonVO personVO) {
       personService.save(personVO);
       return "success";
    }

    /*
       在Junit中写事务会失效,在正常的代码中书写是没问题的
     */
    @GetMapping("/use_transaction")
    @Transactional
    public void useTransaction(){
        PersonVO person = personService.getById(6);
        if(personService.updateById(person.setAge(1))){
            System.out.println("更新成功");
            System.out.println(person);
        }else{
            System.out.println("更新失败");
        }
      /*  personService.save(new Person().setAge(77));
        throw new RuntimeException();*/
    }
}