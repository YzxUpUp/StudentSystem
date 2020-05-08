package cn.yzx.StudentManagementSystem.service;

import cn.yzx.StudentManagementSystem.bean.page;
import cn.yzx.StudentManagementSystem.bean.student;

import java.util.List;
import java.util.Map;

public interface studentService {
    /**
     * 查询student表所有信息
     * @return
     */
    List<student> findAll();

    /**
     * 检查student表内是否有重复的学号
     * @param stu
     * @return
     */
    student duplicate(student stu);

    /**
     * 添加学生数据
     * @param stu
     */
    void insertStudent(student stu);

    /**
     * 分页查询
     * @param currentPage
     * @param rows
     * @param map
     * @return
     */
    page<student> findByPage(String currentPage, String rows, Map<String, String[]> map);

    /**
     * 删除单条学生信息
     * @param sno
     */
    void deleteSingle(String sno);

    /**
     * 修改学生信息
     * @param stu
     */
    void updateStudent(student stu);

    /**
     * 删除选中的信息
     * @param sids
     */
    void delSelected(String[] sids);

    /**
     * 查找学号是否存在
     * @param sno
     * @return
     */
    Map<String, Object> findSno(String sno);
}
