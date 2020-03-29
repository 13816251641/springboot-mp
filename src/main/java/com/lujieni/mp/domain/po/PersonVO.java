package com.lujieni.mp.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lujieni
 * @since 2019-11-20
 */
@Data
@Accessors(chain = true)
//@TableName("mp2.person")//同一个host下面的不同数据库,可以这样访问
@TableName("person")
public class PersonVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String hobbyName;

    private Integer age;

    @Version
    private Date updateTime;


}
