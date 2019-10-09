package com.imooc.oa.biz.impl;

import com.imooc.oa.biz.ProductService;
import com.imooc.oa.dao.ProductDao;
import com.imooc.oa.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    //注入ProductMapper
    @Autowired
    private ProductDao productMapper;

    @Override
    public List<Product> list() {

        return productMapper.list();
    }

    @Override
    public void save(Product product) {
        productMapper.save(product);
    }

    @Override
    public void delete(int id) {
        productMapper.delete(id);
    }

}



