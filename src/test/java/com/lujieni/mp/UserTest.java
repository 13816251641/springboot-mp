package com.lujieni.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lujieni.mp.dao.UserMapper;
import com.lujieni.mp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther ljn
 * @Date 2019/11/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        log.info(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setName("陆捷旎");
        user.setAge(28);
        userMapper.insert(user);
    }

    @Test
    public void testDeleteById() {
        Long id = 7L;
        userMapper.deleteById(id);

    }

    /**
     * 通用删除操作 deleteByMap  map要写列名条件 不能是实体属性名
     */
    @Test
    public void testDeleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name","Billie");
        userMapper.deleteByMap(columnMap);
    }

    @Test
    public void testDelete() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("age",21);
        userMapper.delete(queryWrapper);
    }

    /**
     * 非全量更新,这里name设置了值就更新name字段,别的字段仍就保留
     */
    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(3L);
        user.setName("Angel");
        userMapper.updateById(user);
    }

    /**
     * 如果查询返回的数据有2条那么程序就会抛
     * 一个运行时异常!!!
     */
    @Test
    public void testSelectOne(){
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("name", "Angel").last("limit 1"));
    }

    /**
     * 根据 Wrapper 条件，查询全部记录,
     * 注意这里返回的类型是List<Map<String, Object>>
     */
    @Test
    public void testSelectMaps(){
        List<Map<String, Object>> list = userMapper.selectMaps(new QueryWrapper<User>().eq("name", "Angel"));
        log.info(list.toString());
    }

    /**
     * 注意:只返回第一个字段的值
     */
    @Test
    public void testSelectObjs(){
        List<Object> list = userMapper.selectObjs(new QueryWrapper<User>().eq("name", "Angel"));
        log.info(list.toString());
    }

    /**
     * 分页查询,注意要配置分页插件才可以!!!
     */
    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(1, 2);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, new QueryWrapper<User>().eq("name", "angel"));
        System.out.println(mapIPage.getCurrent());//当前页码
        System.out.println(mapIPage.getTotal());//满足数据的总条数
        /*按照当前的分页参数,返回数据可以分多少页*/
        System.out.println(mapIPage.getPages());
        /* 每页显示的条数 */
        System.out.println(mapIPage.getSize());
        /* 当前页的数据*/
        System.out.println(mapIPage.getRecords());
        log.info(mapIPage.toString());

    }

}
