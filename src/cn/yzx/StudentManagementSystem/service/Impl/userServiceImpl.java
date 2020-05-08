package cn.yzx.StudentManagementSystem.service.Impl;

import cn.yzx.StudentManagementSystem.bean.user;
import cn.yzx.StudentManagementSystem.dao.Impl.userDaoImpl;
import cn.yzx.StudentManagementSystem.dao.userDao;
import cn.yzx.StudentManagementSystem.service.userService;

public class userServiceImpl implements userService {
    private userDao dao = new userDaoImpl();

    @Override
    public user findUser(user u) {
        return dao.findUser(u);
    }

    @Override
    public void insertUser(String username, String password) {
        dao.insertUser(username,password);
    }
}
