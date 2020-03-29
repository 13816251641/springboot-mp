package com.lujieni.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lujieni.mp.dao.ProductMapper;
import com.lujieni.mp.domain.po.ProductVO;
import com.lujieni.mp.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductVO> implements ProductService {
}
