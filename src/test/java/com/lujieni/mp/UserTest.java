
package com.lujieni.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lujieni.mp.dao.UserMapper;
import com.lujieni.mp.domain.po.User;
import com.lujieni.mp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


/**
 * @Auther ljn
 * @Date 2019/11/15
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTest {

    @Autowired
    private UserService userService;


    /**
     * 这里调用的sql是写在xml里面的
     */
    @Test
    public void testSqlInXml(){
        UserMapper userMapper = (UserMapper) userService.getBaseMapper();
        userMapper.selectMethod4(null);
      /*  User user = userMapper.selectMethod();
          System.out.println(user);*/

     /*   List<User> users = userMapper.selectMethod2();
          System.out.println(users);*/

       /*
        User user = new User();
        user.setEmail("test1@baomidou.com");
        List<UserCount> users = userMapper.selectMethod3(user);这里传入null,sql也不会报错
        System.out.println(users);
        */

        /*List<User> users = userMapper.selectMethod4(null);
        System.out.println(users);*/

    }


    /*
        插入一条数据,即使你指定了id也不会帮你做更新,一直都是新增
        INSERT INTO user ( name ) VALUES ( '光头强' )
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setName("光头强123");
        user.setId(16L);
        userService.save(user);
    }

    /**
     * 测试批量插入,分多次batch,最后一个batch的时候
     * 如果sql有问题,前面的几次batch也会回滚!!!
     */
    @Test
    public void testSaveBatch(){
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setName("光头强");
        User user2 = new User();
        user2.setName("哈哈哈");
        User user3 = new User();
        user3.setName("哈哈哈");
        User user4 = new User();
        user4.setName("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        userService.saveBatch(list,1);
    }

    /**
     * 批量更新或者插入,实际会根据主键进行查询,
     * 如果存在就更新(有哪些值就更新哪些值),不
     * 存在就插入即可
     */
    @Test
    public void testSaveOrUpdateBatch(){
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setName("娃哈哈");
        User user2 = new User();
        user2.setId(3L);
        user2.setName("嘻嘻");
        list.add(user1);
        list.add(user2);
        userService.saveOrUpdateBatch(list);
    }

    /**
     * throwEx:true 返回数据大于1条就报错
     *         false 返回数据大于1条也不报错,只返回一条数据(由在数据库中的位置决定)
     */
    @Test
    public void testGetOne(){
        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getName, "Angel"), false);
        System.out.println(user);
    }

    /**
     * 根据 Wrapper，查询一条记录
     * 即使有多条符合的数据也只会返回一条记录
     */
    @Test
    public void testGetMap(){
        Map<String, Object> map = userService.getMap(new QueryWrapper<User>().lambda().eq(User::getName, "Angel"));
        System.out.println(map);
    }

    @Test
    public void testPage(){
        Page<User> page = new Page<>(1, 2);
        IPage<User> result = userService.page(page, new QueryWrapper<User>().lambda().eq(User::getName, "angel"));
        System.out.println(result.getCurrent());//当前页码,从1开始
        System.out.println(result.getTotal());//满足数据的总条数
        /*按照当前的分页参数,返回数据可以分多少页*/
        System.out.println(result.getPages());
        /* 每页显示的条数 */
        System.out.println(result.getSize());
        /* 当前页的数据*/
        System.out.println(result.getRecords());

    }


    /**
     * 查询所有
     */
    @Test
    public void testSelect() {
        log.info(("----- selectAll method test ------"));
        List<User> userList = userService.getBaseMapper().selectList(null);
        //Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    /**
     * 插入一条数据,即使你指定了id也不会帮你做更新,一直都是新增
     * INSERT INTO user ( name ) VALUES ( '陆捷旎' )
     */
    @Test
    public void testAdd() {
        User user = new User();
        user.setId(1L);
        user.setName("陆捷旎");
        userService.getBaseMapper().insert(user);
    }


    /**
     * 根据ID删除一条数据
     */
    @Test
    public void testDeleteById() {
        Long id = 15L;
        userService.getBaseMapper().deleteById(id);
    }

    /**
     * 根据ID批量删除数据,不会有性能问题
     * DELETE FROM user WHERE id IN ( ? , ? )
     */
    @Test
    public void testDeleteByIdInBatch() {
        List<Long> ids = Arrays.asList(17L, 18L);
        userService.getBaseMapper().deleteBatchIds(ids);
    }


    /**
     * 通用删除操作 deleteByMap
     * map要写列名条件 不能是实体属性名!!!
     * DELETE FROM user WHERE name = 'angel' AND age = 12
     */
    @Test
    public void testDeleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name","angel");
        columnMap.put("age",12);
        userService.getBaseMapper().deleteByMap(columnMap);
    }

    /**
     * 注意方法的嵌套
     * DELETE FROM user WHERE ( (age = 21 AND name = 'av') ) OR ( (age < 24 AND name = '张三') )
     * sql的优先级: and > or
     * 提倡如果是多个条件的聚合,使用嵌套的方式来处理更直观
     */
    @Test
    public void testDelete() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda();
        lambdaQueryWrapper.and(
                                i->i.eq(User::getAge,21).eq(User::getName,"av")
                              )
                          .or(
                                  i -> i.lt(User::getAge,24).eq(User::getName,"张三")
                             );
        userService.getBaseMapper().delete(lambdaQueryWrapper);
    }

    /**
     * 非全量更新,这里name设置了值就更新name字段,别的字段仍就保留
     */
    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(2L);
        user.setName("Kobe");
        userService.getBaseMapper().updateById(user);
    }

    /**
     * 如果查询返回的数据有2条那么程序就会抛
     * 一个运行时异常!!!
     */
    @Test
    public void testSelectOne(){
        User user = userService.getBaseMapper().selectOne(new QueryWrapper<User>().eq("name", "Angel").last("limit 1"));
    }

    /**
     * 根据 Wrapper 条件，查询kobe,纪念他!!!
     * 注意这里返回的类型是List<Map<String, Object>>
     */
    @Test
    public void testSelectMaps(){
        LambdaQueryWrapper<User> lambdaWrapper = new QueryWrapper<User>().lambda();
        lambdaWrapper.and(i->i.eq(User::getName,"kobe").eq(User::getAge,20));
        List<Map<String, Object>> list = userService.getBaseMapper().selectMaps(lambdaWrapper);
        log.info(list.toString());
    }

    /**
     * 注意:只返回第一个字段的值,这里是值!!!
     */
    @Test
    public void testSelectObjs(){
        List<Object> list = userService.getBaseMapper().selectObjs(new QueryWrapper<User>().eq("name", "Angel"));
        log.info(list.toString());
    }

    /**
     * 分页查询,注意要配置分页插件才可以!!!
     * 1.SELECT COUNT(1) FROM user WHERE (name = 'redis')
     * 2.SELECT id,name,email,age FROM user WHERE (name = 'redis') LIMIT 0,1
     */
    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(1, 1);
        IPage<User> iPage = userService.getBaseMapper().selectPage(page, new QueryWrapper<User>().lambda().eq(User::getName, "redis"));
        System.out.println(iPage.getCurrent());//当前页码,从1开始
        System.out.println(iPage.getTotal());//满足数据的总条数
        /*按照当前的分页参数,返回数据可以分多少页*/
        System.out.println(iPage.getPages());
        /* 每页显示的数据的条数 */
        System.out.println(iPage.getSize());
        /* 当前页的数据,这里是List<User>*/
        System.out.println(iPage.getRecords());
        //log.info(iPage.toString());
    }


    /**
     * 分页查询,注意要配置分页插件才可以!!!
     * 返回的数据是List<Map<String, Object>>的格式
     */
    @Test
    public void testSelectMapsPage(){
        Page<User> page = new Page<>(1, 2);
        IPage<Map<String, Object>> mapIPage = userService.getBaseMapper().selectMapsPage(page, new QueryWrapper<User>().eq("name", "angel"));
        System.out.println(mapIPage.getCurrent());//当前页码,从1开始
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

