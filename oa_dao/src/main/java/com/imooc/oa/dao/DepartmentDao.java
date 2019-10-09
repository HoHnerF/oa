package com.imooc.oa.dao;

import com.imooc.oa.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gwj
 */
public interface DepartmentDao {
    /**
     * 每页显示的记录数
     */
    int PAGE_SIZE = 5;


    /**
     * 增加
     * @param department
     */
    void insert(Department department);

    /**
     * 修改
     * @param department
     */
    void update(Department department);

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
    Department select(String sn);

    /**
     *查询所有
     * @return
     */
    List<Department> selectAll();

    /**
     * 查询当前页
     * @return
     */
    List<Department> selectAllByPage(@Param("limit") int limit,@Param("offset") int offset);

    /**
     * 查询总的记录数
     * @return
     */
    int selectCount ();

}
