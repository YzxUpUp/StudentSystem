package cn.yzx.StudentManagementSystem.dao.Impl;

import cn.yzx.StudentManagementSystem.bean.user;
import cn.yzx.StudentManagementSystem.dao.userDao;
import cn.yzx.StudentManagementSystem.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class userDaoImpl implements userDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public user findUser(user u) {
        String sql = "select * from user where username = ? and password = ?;";
        try {
            user user = template.queryForObject(sql, new BeanPropertyRowMapper<user>(user.class),
                    u.getUsername(), u.getPassword());
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insertUser(String username, String password) {
        String sql = "insert into user values(?,?);";
        System.out.println(sql);
        template.update(sql,username,password);
    }
}
