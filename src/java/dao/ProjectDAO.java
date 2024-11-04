/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Project;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ProjectDAO extends DBContext{
    DBContext db = new DBContext();
    
    public Project getProjectById(int xProject_id) {
        int xStatus;
        String xName;
        Project x = null;
        String xSql = "select * from project where project_id = ?";
        ResultSet rs = null;
        java.sql.PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setInt(1, xProject_id);

            rs = ps.executeQuery();
            if (rs.next()) {
                xName = rs.getString("name");
                xStatus = rs.getInt("status");
                x = new Project(xProject_id, xName, xStatus);
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
    
    public static void main(String[] args) {
        ProjectDAO a = new ProjectDAO();
        Project x = a.getProjectById(1);
        System.out.println(x.getName());
    }
}
