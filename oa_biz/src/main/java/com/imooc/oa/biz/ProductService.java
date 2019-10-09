package com.imooc.oa.biz;

import com.imooc.oa.entity.Product;

import java.util.List;

public interface ProductService {


    List<Product> list();

    void save(Product product);

    void delete(int id);
}
