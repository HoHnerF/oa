package com.imooc.oa.controller;

import com.imooc.oa.biz.DepartmentBiz;
import com.imooc.oa.biz.EmployeeBiz;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.entity.Page;
import com.imooc.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private DepartmentBiz departmentBiz;

    @Autowired
    private EmployeeBiz employeeBiz;
    private int PAGE_SIZE = 5;

    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        map.put("list",employeeBiz.getAll());
        return "employee_list";
    }


    @RequestMapping("/selectEmpByPage")
    public String selectByPage(Map<String,Object> map, int currentPage, HttpServletRequest request){

        map.put("list",employeeBiz.getEmpAllByPage(currentPage));
        System.out.println(currentPage);
        int EmpCount = employeeBiz.getEmpCount();
        Page page1 = new Page();
        page1.setCurrentPage(currentPage);
        page1.setTotalSize(EmpCount);
        page1.setPageSize(PAGE_SIZE);
        page1.setTotalPage(EmpCount % PAGE_SIZE == 0 ? EmpCount / PAGE_SIZE : EmpCount / PAGE_SIZE + 1);
        request.setAttribute("page",page1);

        return "employee_list";
    }



    @RequestMapping("/to_add")
    public String toAdd(Map<String,Object> map){
        map.put("employee",new Employee());
        map.put("dlist",departmentBiz.getAll());
        map.put("plist", Contant.getPosts());
        return "employee_add";
    }
    @RequestMapping("/add")
    public String add(Employee employee){
        employeeBiz.add(employee);
        return "redirect:/employee/selectEmpByPage?currentPage="+1;
    }

    @RequestMapping(value = "/to_update",params = "sn")
    public String toUpdate(String sn,Map<String,Object> map){
        map.put("employee",employeeBiz.get(sn));
        map.put("dlist",departmentBiz.getAll());
        map.put("plist", Contant.getPosts());
        return "employee_update";
    }
    @RequestMapping("/update")
    public String update(Employee employee){
        employeeBiz.edit(employee);
        return "redirect:/employee/selectEmpByPage?currentPage="+1;
    }
    @RequestMapping(value = "/remove",params = "sn")
    public String remove(String sn){
        employeeBiz.remove(sn);
        return "redirect:/employee/selectEmpByPage?currentPage="+1;
    }

}
