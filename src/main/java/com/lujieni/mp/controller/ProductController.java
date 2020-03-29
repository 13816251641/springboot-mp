package com.lujieni.mp.controller;

import com.lujieni.mp.domain.po.ProductVO;
import com.lujieni.mp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/v1/get_all_list")
    public  List<ProductVO>  getAllList(){
        return  productService.list();
    }

}
