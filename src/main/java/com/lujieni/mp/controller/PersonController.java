package com.lujieni.mp.controller;


import com.lujieni.mp.entity.Person;
import com.lujieni.mp.service.IPersonService;
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
    private IPersonService iPersonService;

    @GetMapping("/get_all")
    public List<Person> getAll() {
        return iPersonService.list();
    }

    @GetMapping("/save_all")
    public void save() {
        List<Person> list = new ArrayList<>();
        list.add(new Person().setAge(25).setHobbyName("睡觉"));
        list.add(new Person().setAge(12).setHobbyName("玩玩具"));
        iPersonService.saveBatch(list);
    }
}