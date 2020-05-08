package cn.yzx.StudentManagementSystem.service.Impl;

import cn.yzx.StudentManagementSystem.bean.page;
import cn.yzx.StudentManagementSystem.bean.student;
import cn.yzx.StudentManagementSystem.dao.Impl.studentDaoImpl;
import cn.yzx.StudentManagementSystem.dao.studentDao;
import cn.yzx.StudentManagementSystem.service.studentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class studentServiceImpl implements studentService {
    private studentDao dao = new studentDaoImpl();


    @Override
    public List<student> findAll() {
        return dao.findAll();
    }

    @Override
    public student duplicate(student stu) {
        return dao.duplicate(stu);
    }

    @Override
    public void insertStudent(student stu) {
        dao.insertStudent(stu);
    }

    @Override
    public page<student> findByPage(String _currentPage, String _rows, Map<String, String[]> map) {
        int currentPage = Integer.parseInt(_currentPage); //当前页数
        int rows = Integer.parseInt(_rows); //每页显示条数
        List<student> num = dao.findAllByPage(map);
        int totalCount = num.size(); //总数据量
        int start = (currentPage - 1) * rows;
        List<student> list = dao.findPageValue(start, rows, map); //每页显示的数据
        int totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1; //获取总页数
        page<student> page = new page<>();
        page.setCurrentPage(currentPage);
        page.setRows(rows);
        page.setList(list);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        return page;
    }

    @Override
    public void deleteSingle(String sno) {
        dao.deleteSingle(sno);
    }

    @Override
    public void updateStudent(student stu) {
        dao.updateStudent(stu);
    }

    @Override
    public void delSelected(String[] sids) {
        for (String sid : sids) {
            dao.deleteSingle(sid);
        }
    }

    @Override
    public Map<String, Object> findSno(String sno) {
        Map<String, Object> map = new HashMap<>();
        student stu = dao.findSno(sno);
        boolean exist = stu == null ? true : false;
        if(exist){
            //不存在相同学号
            map.put("snoExise",true);
            map.put("msg","可以使用的学号");
        }else {
            //存在相同学号
            map.put("snoExise",false);
            map.put("msg","该学号已被使用");
        }
        return map;
    }
}
