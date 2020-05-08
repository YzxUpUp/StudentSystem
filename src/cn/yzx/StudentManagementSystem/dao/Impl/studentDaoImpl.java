package cn.yzx.StudentManagementSystem.dao.Impl;

import cn.yzx.StudentManagementSystem.bean.student;
import cn.yzx.StudentManagementSystem.dao.studentDao;
import cn.yzx.StudentManagementSystem.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class studentDaoImpl implements studentDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<student> findAll() {
        String sql = "select * from student;";
        List<student> list = template.query(sql, new BeanPropertyRowMapper<student>(student.class));
        return list;
    }

    @Override
    public student duplicate(student stu) {
        String sql = "select * from student where sno = ?;";
        try {
            student student = template.queryForObject(sql, new BeanPropertyRowMapper<student>(student.class), stu.getSno());
            return student;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insertStudent(student stu) {
        String sql = "insert into student values(?,?,?,?,?,?,?);";
        template.update(sql,stu.getSno(),stu.getSname(),stu.getSsex(),
                stu.getSage(),stu.getSaddress(),stu.getSqq(),stu.getSemail());
    }

    @Override
    public List<student> findAllByPage(Map<String, String[]> map) {
        String sql = "select * from student where 1 = 1";
        Set<String> keys = map.keySet();
        StringBuilder sbu = new StringBuilder(sql);
        for(String key : keys){
            if(!"currentPage".equals(key) && !"rows".equals(key)){
                String value = map.get(key)[0];
                if(value != null && !"".equals(value)){
                    String sqladd = " and " + key + " like " + "'%" + value + "%'";
                    sbu.append(sqladd);
                }
            }
        }
        List<student> list = template.query(sbu.toString(), new BeanPropertyRowMapper<student>(student.class));
        return list;
    }

    @Override
    public List<student> findPageValue(int start, int rows, Map<String, String[]> map) {
        String sql = "select * from student where 1 = 1";
        Set<String> keys = map.keySet();
        StringBuilder sbu = new StringBuilder(sql);
        for(String key : keys){
            if(!"currentPage".equals(key) && !"rows".equals(key)){
                String value = map.get(key)[0];
                if(value != null && !"".equals(value)){
                    String sqladd = " and " + key + " like " + "'%" + value + "%'";
                    sbu.append(sqladd);
                }
            }
        }
        sbu.append(" limit ? , ?");
        List<student> list = template.query(sbu.toString(), new BeanPropertyRowMapper<student>(student.class), start, rows);
        return list;
    }

    @Override
    public void deleteSingle(String sno) {
        String sql = "delete from student where sno = ?";
        template.update(sql,sno);
    }

    @Override
    public void updateStudent(student stu) {
        String sql = "update student set sname = ?,ssex = ?,sage = ?,saddress = ?,sqq = ?,semail = ? where sno = ?";
        template.update(sql,stu.getSname(),stu.getSsex(),
                stu.getSage(),stu.getSaddress(),stu.getSqq(),stu.getSemail(),stu.getSno());
    }

    @Override
    public student findSno(String sno) {
        String sql = "select * from student where sno = ?";
        try {
            student student = template.queryForObject(sql, new BeanPropertyRowMapper<student>(student.class), sno);
            return student;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
