/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Requirement;

/**
 *
 * @author DELL
 */
public class RequirementDAO extends DBContext{
    DBContext db = new DBContext();

    public List<Requirement> getRequirementList() {
        List<Requirement> t = new ArrayList<>();
        int xReq_id, xOwner_id, xComplexity_id, xStatus_id, xCreated_by_id;
        String xTitle, xDescription;
        LocalDateTime xCreated_at;
        String xSql = "select * from requirement ";
        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ResultSet rs = ps.executeQuery();
            Requirement x;
            while (rs.next()) {
                xReq_id = rs.getInt("req_id");
                xOwner_id = rs.getInt("owner_id");
                xComplexity_id = rs.getInt("complexity_id");
                xStatus_id = rs.getInt("status_id");
                xCreated_by_id = rs.getInt("updated_by_id");
                xTitle = rs.getString("title");
                xDescription = rs.getString("description");
                xCreated_at = rs.getTimestamp("created_at").toLocalDateTime();
                x = new Requirement(xReq_id, xTitle, xOwner_id, xComplexity_id, xStatus_id, xDescription, xCreated_at, xCreated_by_id);
                t.add(x);
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
    
    public Requirement getRequirementById(int xReq_id) {
        String xTitle;
        Requirement x = null;
        String xSql = "select * from requirement where req_id = ?";
        ResultSet rs = null;
        java.sql.PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setInt(1, xReq_id);

            rs = ps.executeQuery();
            if (rs.next()) {
                xTitle = rs.getString("title");
                x = new Requirement(xReq_id, xTitle);
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
        RequirementDAO a = new RequirementDAO();
        List<Requirement> lst = a.getRequirementList();
        System.out.println(lst.size());
    }
    
    public int insertRequirement(String title, int owner_id, int complexity_id, int status_id, 
                             String description, LocalDateTime created_at, int created_by_id) {
    int result = 0;
    try {
        String xSql = "INSERT INTO requirement (title, owner_id, complexity_id, status_id, "
                    + "description, created_at, created_by_id) VALUES (?,?,?,?,?,?,?)";
        java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
        ps.setString(1, title);
        ps.setInt(2, owner_id);
        ps.setInt(3, complexity_id);
        ps.setInt(4, status_id);
        ps.setString(5, description);
        ps.setObject(6, created_at);
        ps.setInt(7, created_by_id);
        ps.executeUpdate();
        result = 1;
        db.closeConnection();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

    
    public List<Requirement> getRequirementListByAssignTo(int owner_id) { 
    List<Requirement> requirements = new ArrayList<>();
    String xSql = "SELECT * FROM requirement WHERE owner_id = ?";

    try {
        java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
        ps.setInt(1, owner_id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int req_id = rs.getInt("req_id");
            String title = rs.getString("title");
            int complexity_id = rs.getInt("complexity_id");
            int status_id = rs.getInt("status_id");
            String description = rs.getString("description");
            LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
            int created_by_id = rs.getInt("created_by_id");

            Requirement requirement = new Requirement(req_id, title, owner_id, complexity_id, status_id, description, created_at, created_by_id);
            requirements.add(requirement);
        }
        db.closeConnection();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return requirements;
}

    
    public List<Requirement> getRequirementListByStatus(int status_id) {
    List<Requirement> requirements = new ArrayList<>();
    String xSql = "SELECT * FROM requirement WHERE status_id = ?";

    try {
        java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
        ps.setInt(1, status_id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int req_id = rs.getInt("req_id");
            String title = rs.getString("title");
            int owner_id = rs.getInt("owner_id");
            int complexity_id = rs.getInt("complexity_id");
            String description = rs.getString("description");
            LocalDateTime created_at = rs.getTimestamp("created_at").toLocalDateTime();
            int created_by_id = rs.getInt("created_by_id");

            Requirement requirement = new Requirement(req_id, title, owner_id, complexity_id, status_id, description, created_at, created_by_id);
            requirements.add(requirement);
        }
        db.closeConnection();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return requirements;
}

    
    public void updateRequirement(int req_id, String title, int owner_id, 
            int complexity_id, int status_id, String description, 
            LocalDateTime updated_at, int updated_by_id) {
    String xSql = "UPDATE requirement "
                + "SET title = ?, owner_id = ?, complexity_id = ?, status_id = ?, "
                + "description = ?, updated_at = ?, updated_by_id = ? "
                + "WHERE req_id = ?";
    try {
        java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
        ps.setString(1, title);
        ps.setInt(2, owner_id);
        ps.setInt(3, complexity_id);
        ps.setInt(4, status_id);
        ps.setString(5, description);
        ps.setObject(6, updated_at);
        ps.setInt(7, updated_by_id);
        ps.setInt(8, req_id);
        ps.executeUpdate();
        db.closeConnection();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public void deleteRequirement(int req_id) {
    String xSql = "DELETE FROM requirement WHERE req_id = ?";
    try {
        java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
        ps.setInt(1, req_id);
        ps.executeUpdate();
        db.closeConnection();
    } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
