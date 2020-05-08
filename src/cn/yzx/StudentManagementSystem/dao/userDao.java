package cn.yzx.StudentManagementSystem.dao;

import cn.yzx.StudentManagementSystem.bean.user;

public interface userDao {
    /**
     * 根据输入的用户名密码条件查询user表
     * @param u
     * @return
     */
    user findUser(user u);

    /**
     * 在user表中插入新的数据
     * @param username
     * @param password
     */
    void insertUser(String username, String password);
}
