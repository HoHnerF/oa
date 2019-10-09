package com.imooc.oa.controller;

import com.imooc.oa.biz.ProductService;
import com.imooc.oa.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {


    //注入ProductService
    @Autowired
    private ProductService productService;

    //查询
    @RequestMapping("/sel")
    public String listUser(Model model) {
        List<Product> list = productService.list();
        model.addAttribute("list", list);
        System.out.println(list);
        return "xianshi";
    }

    /**
     * 保存商品
     *
     * @param product
     * @param map
     * @return
     * @throws IOException
     */
    @RequestMapping("/addProduct")
    public String fileUpload(MultipartFile file, Product product, ModelMap map) throws IOException {

        /**
         * 上传图片
         */
        //图片上传成功后，将图片的地址写到数据库
        String filePath = "E:\\upload";//保存图片的路径,tomcat中有配置
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        //新的文件名字，使用uuid随机生成数+原始图片名字，这样不会重复


        String newFileName = UUID.randomUUID() + originalFilename;

        //封装上传文件位置的全路径，就是硬盘路径+文件名
        File targetFile = new File(filePath, newFileName);
        //把本地文件上传到已经封装好的文件位置的全路径就是上面的targetFile
        file.transferTo(targetFile);
        product.setPimage(newFileName);
        //文件名保存到实体类对应属性上
        /**
         * 保存商品
         */
        productService.save(product);
        return "redirect:/sel"; //重定向到查询
    }



    @RequestMapping("/delete")
    public String delete(int id){
        productService.delete(id);
        return "redirect:/sel";
    }



}

