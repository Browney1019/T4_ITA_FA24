/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.SettingDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Vector;
import model.Setting;

/**
 *
 * @author admin
 */
public class SettingService {



    // Instance of SettingDAO to access data layer
    private final SettingDAO settingDAO;

    // Constructor initializes SettingDAO instance
    public SettingService() {
        settingDAO = new SettingDAO();
    }

    // Business logic to get settings
    public Vector<Setting> getAllSettings() {
        String query = "SELECT * FROM settings";
        return settingDAO.getSetting(query);
    }

    public boolean insertSetting(Setting setting) {
        return settingDAO.insertSetting(setting) > 0;
    }

    public boolean updateSetting(Setting setting) {
        return settingDAO.updateSetting(setting) > 0;
    }

    public boolean removeSetting(int id) {
        return settingDAO.RemoveSetting(id) > 0;
    }

    public boolean deactivateSetting(int id) {
        return settingDAO.changeActive(id, "inactive") > 0;
    }

     public List<Setting> getIssueTypeListById(int typeid) {
        return settingDAO.getIssueTypeListById(typeid);
    }
    
    public Setting getSettingById(int settingid) {
        return settingDAO.getSettingById(settingid);
    }
    public Vector<Setting> getSetting(String sql){
        return settingDAO.getSetting(sql);
    }
    
    public boolean validateString(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // Validation cho số nguyên: kiểm tra xem chuỗi có thể chuyển thành số nguyên hay không
    public boolean validateInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validation cho đối tượng Setting: kiểm tra đối tượng có null hay không
    public boolean validateSetting(Setting setting) {
        return setting != null &&
               validateString(setting.getName()) &&
               validateString(setting.getValue()) &&
               validateInt(String.valueOf(setting.getType())) &&
               validateInt(String.valueOf(setting.getPriority())) &&
               validateString(setting.getDescription());
    }

    // Validation cho LocalDateTime theo định dạng "yyyy-MM-dd HH:mm:ss"
    public boolean validateLocalDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Validation cho Date theo định dạng "yyyy-MM-dd"
    public boolean validateDate(String input) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        try {
            formatter.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}

