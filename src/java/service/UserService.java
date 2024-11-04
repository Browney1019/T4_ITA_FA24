/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UserDAO;
import model.User;

/**
 *
 * @author DELL
 */
public class UserService {
    private final UserDAO userDAO;

    // Constructor initializes SettingDAO instance
    public UserService() {
        userDAO = new UserDAO();
    }

    public User getUserById(int userId) {
        return userDAO.getUserById1(userId);
    }
}
