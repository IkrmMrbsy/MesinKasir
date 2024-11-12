package org.example.Service;

import org.example.Database.DAO.UserDao;
import org.example.Model.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public User login(String username, String password){
        return  userDao.login(username, password);
    }
}
