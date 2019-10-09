package com.imooc.oa.dao;


import com.imooc.oa.entity.Product;

import java.util.List;

public interface ProductDao {

    /**
     * 查询所有的图片 * @return
     */
    List<Product> list();

    /**
     * 上传一张图片 * @param product * @return
     */
    Integer save(Product product);

    /**
     * 删除图片
     */
    void delete(int id);
}
