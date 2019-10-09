package com.imooc.oa.biz.impl;


import com.imooc.oa.biz.EmployeeBiz;
import com.imooc.oa.dao.EmployeeDao;
import com.imooc.oa.entity.Department;
import com.imooc.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeBizImpl implements EmployeeBiz {

    @Autowired
    private EmployeeDao employeeDao;
    private int PAGE_SIZE = 5;
    @Override
    public void add(Employee employee) {
        employee.setPassword("000000");
        employeeDao.insert(employee);
    }

    @Override
    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void remove(String sn) {
        employeeDao.delete(sn);
    }

    @Override
    public Employee get(String sn) {
        return employeeDao.select(sn);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.selectAll();
    }

    @Override
    public List<Employee> getEmpAllByPage(int currentPage) {
        return employeeDao.selectAllByPage((currentPage-1)*PAGE_SIZE,PAGE_SIZE);
       /* return employeeDao.selectAllByPage((currentPage-1)*PAGE_SIZE,PAGE_SIZE);*/
    }

    @Override
    public int getEmpCount() {
        return employeeDao.selectCount();
    }
}
