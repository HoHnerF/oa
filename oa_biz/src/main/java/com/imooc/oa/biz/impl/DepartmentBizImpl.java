package com.imooc.oa.biz.impl;

import com.imooc.oa.biz.DepartmentBiz;
import com.imooc.oa.dao.DepartmentDao;
import com.imooc.oa.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentBizImpl implements DepartmentBiz {

   @Autowired
    private DepartmentDao departmentDao;
    private int PAGE_SIZE = 5;

    @Override
    public void add(Department department) {
       departmentDao.insert(department);
    }

    @Override
    public void edit(Department department) {
       departmentDao.update(department);
    }

    @Override
    public void remove(String sn) {
       departmentDao.delete(sn);
    }

    @Override
    public Department get(String sn) {
        return departmentDao.select(sn);
    }

    @Override
    public List<Department> getAll() {
        return departmentDao.selectAll();
    }

    @Override
    public List<Department> getDepAllByPage(int currentPage) {
        System.out.println("+++++++++++++++++++++++++++++"+currentPage+"************************");
        return departmentDao.selectAllByPage((currentPage-1)*PAGE_SIZE,PAGE_SIZE);
    }

    @Override
    public int getDepCount() {
        return departmentDao.selectCount();
    }


}
