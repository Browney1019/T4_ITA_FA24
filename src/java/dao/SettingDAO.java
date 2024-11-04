/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.Setting;

/**
 *
 * @author DELL
 */
public class SettingDAO extends DBContext{
    public static void main(String[] args) {
        SettingDAO dao = new SettingDAO();
        dao.listAllSettings();
    }
    public Vector<Setting> getSetting(String sql) {
    Vector<Setting> vector = new Vector<>();
    try {
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = state.executeQuery(sql);
        while (rs.next()) {
            int setting_id = rs.getInt(1);
            String name = rs.getString(2),
                   value = rs.getString(3);
            int type_id = rs.getInt(4),
                priority = rs.getInt(5);
            String status = rs.getString(6),
                   description = rs.getString(7);
            java.sql.Timestamp created_at = rs.getTimestamp(8);
            int created_by_id = rs.getInt(9);
            java.sql.Timestamp updated_at = rs.getTimestamp(10);
            int updated_by_id = rs.getInt(11);

            Setting setting = new Setting(setting_id, name, value, type_id, priority, status, description, created_by_id, updated_by_id);
            
            vector.add(setting);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return vector;
}
    
    public void listAllSettings() {
    String sql = "SELECT * FROM setting"; // Đảm bảo tên bảng đúng
    try {
        Statement state = conn.createStatement();
        ResultSet rs = state.executeQuery(sql);
        while (rs.next()) {
            // Trích xuất các giá trị từ ResultSet
            int settingId = rs.getInt("setting_id"); // Adjust column names as per your settings table
            String name = rs.getString("name");
            String value = rs.getString("value");
            int typeId = rs.getInt("type_id");
            int priority = rs.getInt("priority");
            String status = rs.getString("status");
            String description = rs.getString("description");
            java.sql.Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
            int createdById = rs.getInt("created_by_id");
            java.sql.Timestamp updatedAtTimestamp = rs.getTimestamp("updated_at");
            int updatedById = rs.getInt("updated_by_id");

            // Chuyển đổi Timestamp sang LocalDateTime
            LocalDateTime createdAt = createdAtTimestamp != null ? createdAtTimestamp.toLocalDateTime() : null;
            LocalDateTime updatedAt = updatedAtTimestamp != null ? updatedAtTimestamp.toLocalDateTime() : null;

            // Tạo đối tượng Setting và in ra (Cập nhật theo constructor của lớp Setting)
            Setting setting = new Setting(settingId, name, value, typeId, priority, status, description, createdById, updatedById);
            System.out.println(setting);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


    public int insertSetting(Setting setting) {
    int n = 0;
    String sql = "INSERT INTO `settings` "
               + "(`name`, `value`, `type_id`, `priority`, `status`, `description`, "
               + "`created_at`, `created_by_id`, `updated_at`, `updated_by_id`) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement pre = conn.prepareStatement(sql);
       
        pre.setString(1, setting.getName());
        pre.setString(2, setting.getValue());
        pre.setInt(3, setting.getType_id());
        pre.setInt(4, setting.getPriority());
        pre.setString(5, setting.getStatus());
        pre.setString(6, setting.getDescription());
        pre.setInt(7, setting.getCreatedById());
        pre.setInt(8, setting.getUpdatedById());

        
        n = pre.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return n;
}
public int updateSetting(Setting setting) {
    int n = 0;
    String sql = "UPDATE `settings` "
               + "SET `name` = ?, "
               + "`value` = ?, "
               + "`type_id` = ?, "
               + "`priority` = ?, "
               + "`status` = ?, "
               + "`description` = ?, "
               + "`updated_at` = ?, "
               + "`updated_by_id` = ? "
               + "WHERE `setting_id` = ?";
    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, setting.getName());
        pre.setString(2, setting.getValue());
        pre.setInt(3, setting.getType_id());
        pre.setInt(4, setting.getPriority());
        pre.setString(5, setting.getStatus());
        pre.setString(6, setting.getDescription());
        pre.setInt(7, setting.getUpdatedById());
        pre.setInt(8, setting.getSetting_id());

        n = pre.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return n;
}
public int addSetting(Setting setting) {
    int n = 0;
    String sql = "INSERT INTO `settings` "
               + "(`setting_id`, `name`, `value`, `type_id`, `priority`, `status`, "
               + "`description`, `created_at`, `created_by_id`) "
               + "VALUES (" 
               + setting.getSetting_id() + ", '"
               + setting.getName() + "', '"
               + setting.getValue() + "', "
               + setting.getType_id() + ", "
               + setting.getPriority() + ", '"
               + setting.getStatus() + "', '"
               + setting.getDescription() + "', '"
               + setting.getCreatedById()+ ")";
//                System.out.println(sql);
    try {
        Statement statement = conn.createStatement();
        n = statement.executeUpdate(sql);
    } catch (SQLException ex) {
        Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return n;
}
public int RemoveSetting(int id) {
    int n = 0;
    String sql = "DELETE FROM settings WHERE setting_id=" + id;
    try {
        // Kiểm tra khóa ngoại trong bảng liên quan (ví dụ: orders hoặc bảng khác)
        ResultSet rs = getData("SELECT * FROM orders WHERE setting_id=" + id);
        if (rs.next()) { // tồn tại khóa ngoại
            // Thay đổi trạng thái active thành inactive
            changeActive(id, "inactive");
            return n; // Không thực hiện xóa, chỉ thay đổi trạng thái
        }
        
        // Nếu không tồn tại khóa ngoại, thực hiện xóa bản ghi
        Statement statement = conn.createStatement();
        n = statement.executeUpdate(sql);
    } catch (SQLException ex) {
        Logger.getLogger(SettingDAO.class.getName()).log(Level.SEVERE, "Error removing setting with ID: " + id, ex);
    }
    return n;
}
public int changeActive(int id, String status) {
    int n = 0;
    String sql = "UPDATE settings SET status = ? WHERE setting_id = ?";
    try {
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, status);
        pre.setInt(2, id);
        n = pre.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return n;
}
 public void forwardToJSP(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
 
    DBContext db = new DBContext();
    
    public List<Setting> getIssueTypeListById(int xType_id) {
        List<Setting> t = new ArrayList<>();
        int xSetting_id, xPriority, xStatus;
        String xName, xValue, xDescription;
        String xSql = "select * from setting where type_id = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setInt(1, xType_id);
            ResultSet rs = ps.executeQuery();
            Setting x;
            while (rs.next()) {
                xSetting_id = rs.getInt("setting_id");
                xPriority = rs.getInt("priority");
                xStatus = rs.getInt("status");
                xName = rs.getString("name");
                xValue = rs.getString("value");
                xDescription = rs.getString("description");
                x = new Setting(xSetting_id, xName, xValue, xType_id, xPriority,
                        xValue, xDescription);
                t.add(x);
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
    
    public Setting getSettingById(int xSetting_id) {
        String xName;
        Setting x = null;
        String xSql = "select * from setting where setting_id = ?";
        ResultSet rs = null;
        java.sql.PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setInt(1, xSetting_id);

            rs = ps.executeQuery();
            if (rs.next()) {
                xName = rs.getString("name");
                x = new Setting(xSetting_id, xName);
            }

            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                db.closeConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return (x);
    }
    
//    public static void main(String[] args) {
//        SettingDAO a = new SettingDAO();
//        Setting b = a.getSettingById(13);
//        System.out.println(b.getName());
//    }
 
}
