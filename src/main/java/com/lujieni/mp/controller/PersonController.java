package com.lujieni.mp.controller;


import com.lujieni.mp.domain.po.Person;
import com.lujieni.mp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/get_all")
    public List<Person> getAll() {
        return personService.list();
    }

    @GetMapping("/save_all")
    public void save() {
        List<Person> list = new ArrayList<>();
        list.add(new Person().setAge(25).setHobbyName("睡觉"));
        list.add(new Person().setAge(12).setHobbyName("玩玩具"));
        personService.saveBatch(list);
    }
}