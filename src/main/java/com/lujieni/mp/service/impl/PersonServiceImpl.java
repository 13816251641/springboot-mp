package com.lujieni.mp.service.impl;

import com.lujieni.mp.domain.po.PersonVO;
import com.lujieni.mp.dao.PersonMapper;
import com.lujieni.mp.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lujieni
 * @since 2019-11-20
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, PersonVO> implements PersonService {

}
