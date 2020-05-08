package cn.yzx.StudentManagementSystem.dao;

import cn.yzx.StudentManagementSystem.bean.student;

import java.util.List;
import java.util.Map;

public interface studentDao {
    /**
     * 查询student表所有信息
     * @return
     */
    List<student> findAll();

    /**
     * 查重
     * @param sno
     * @return
     */
    student duplicate(student stu);

    /**
     * 添加学生
     * @param stu
     */
    void insertStudent(student stu);

    /**
     * 分页查询
     * @param start
     * @param rows
     * @param map
     * @return
     */
    List<student> findPageValue(int start, int rows, Map<String, String[]> map);

    /**
     * 删除单条
     * @param sno
     */
    void deleteSingle(String sno);

    /**
     * 修改学生信息
     * @param stu
     */
    void updateStudent(student stu);

    /**
     * 查询student表所有信息
     * @return
     * @param map
     */
    List<student> findAllByPage(Map<String, String[]> map);

    student findSno(String sno);
}
