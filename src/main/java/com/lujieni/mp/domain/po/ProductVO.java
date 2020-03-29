package com.lujieni.mp.domain.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("t_product")
public class ProductVO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Date ctime;
}
