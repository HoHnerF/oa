package com.imooc.oa.controller;

import com.imooc.oa.biz.DepartmentBiz;
import com.imooc.oa.entity.Department;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller("departmentController")
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentBiz departmentBiz;
    private int PAGE_SIZE = 5;

    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        /*
        * 通过modelmap 来存储数据
        * 用map类型做入参，
        * springmvc可以自动帮你绑定前端表单数据到入参map里
         *
         *
         * 返回时 自动会把这个map里的数据丢到modelandview里的那个map里 、
         * 自动把他扔给request
         *
         * 其实就是帮你做域中存值的步骤
         * */
       map.put("list",departmentBiz.getAll());
        return "department_list";
    }

    @RequestMapping("/selectByPage")
    public String selectByPage(Map<String,Object> map, int currentPage, HttpServletRequest request){

        map.put("list",departmentBiz.getDepAllByPage(currentPage));

        int depCount = departmentBiz.getDepCount();
        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setTotalSize(depCount);
        page.setPageSize(PAGE_SIZE);
        page.setTotalPage(depCount % PAGE_SIZE == 0 ? depCount / PAGE_SIZE : depCount / PAGE_SIZE + 1);
        request.setAttribute("page",page);
        System.out.println(page.getCurrentPage()+"      "+page.getTotalSize());
        return "department_list";
    }



    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("department",new Department());
        return "department_add";
    }
    @RequestMapping("/add")
    public String add(Department department){
        departmentBiz.add(department);
        return "redirect:list";
    }

    @RequestMapping(value = "/to_update",params = "sn")
    public String toUpdate(String sn,Map<String,Object> map){
        map.put("department",departmentBiz.get(sn));
        return "department_update";
    }
    @RequestMapping("/update")
    public String update(Department department){
        departmentBiz.edit(department);
        return "redirect:list";
    }
    @RequestMapping(value = "/remove",params = "sn")
    public String remove(String sn){
        departmentBiz.remove(sn);
        return "redirect:list";
    }


}
