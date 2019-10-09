package com.imooc.oa.dao;

import com.imooc.oa.entity.Department;
import com.imooc.oa.entity.Employee;
import com.imooc.oa.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {

    /**
     * 增加
     * @param employee
     */
    void insert(Employee employee);

    /**
     * 修改
     * @param employee
     */
    void update(Employee employee);

    /**
     * 删除
     * @param sn
     */
    void delete(String sn);

    /**
     * 条件查询
     * @param sn
     * @return
     */
    Employee select(String sn);

    /**
     *查询所有
     * @return
     */
    List<Employee> selectAll();

    /**
     * 部门编号和职务获取员工
     * @param dsn
     * @param post
     * @return
     */
    List<Employee> selectByDepartmentAndPost(@Param("dsn") String dsn,@Param("post") String post);

    /**
     * 查询当前页
     * @return
     */
    List<Employee> selectAllByPage(@Param("limit") int limit, @Param("offset") int offset);

    /**
     * 查询总的记录数
     * @return
     */
    int selectCount ();



}
