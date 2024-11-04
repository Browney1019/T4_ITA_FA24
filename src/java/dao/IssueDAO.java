/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
//import java.sql.Date;
import java.util.Date;
import java.util.List;
import model.Issue;

/**
 *
 * @author DELL
 */
public class IssueDAO extends DBContext {

    DBContext db = new DBContext();

    public List<Issue> getIssueList() {
        List<Issue> t = new ArrayList<>();
        int xIssue_id, xType_id, xReq_id, xAssigner_id, xAssignee_id, xStatus,
                xCreated_by_id, xUpdated_by_id;
        String xTitle, xDescription;
        Date xDeadline;
        LocalDateTime xStatus_date, xCreated_at, xUpdated_at;
        String xSql = "select * from issue ";
        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            //ps.setInt(1, xDebtor_id);
            ResultSet rs = ps.executeQuery();
            Issue x;
            while (rs.next()) {
                xIssue_id = rs.getInt("issue_id");
                xType_id = rs.getInt("type_id");
                xReq_id = rs.getInt("req_id");
                xAssigner_id = rs.getInt("assigner_id");
                xAssignee_id = rs.getInt("assignee_id");
                xStatus = rs.getInt("status");
                xCreated_by_id = rs.getInt("created_by_id");
                xUpdated_by_id = rs.getInt("updated_by_id");
                xTitle = rs.getString("title");
                xDescription = rs.getString("description");
                xDeadline = rs.getTimestamp("deadline");
                xStatus_date = rs.getTimestamp("status_date").toLocalDateTime();
                xCreated_at = rs.getTimestamp("created_at").toLocalDateTime();
                xUpdated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                x = new Issue(xIssue_id, xTitle, xType_id, xReq_id, xAssigner_id,
                        xAssignee_id, xDeadline, xStatus, xStatus_date, xDescription,
                        xCreated_at, xCreated_by_id, xUpdated_at, xUpdated_by_id);
                t.add(x);
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
    
    public List<Issue> getIssueListByPId(int xProjectId) {
        List<Issue> t = new ArrayList<>();
        int xIssue_id, xType_id, xReq_id, xAssigner_id, xAssignee_id, xStatus,
                xCreated_by_id, xUpdated_by_id;
        String xTitle, xDescription;
        Date xDeadline;
        LocalDateTime xStatus_date, xCreated_at, xUpdated_at;
        String xSql = "select * from issue where project_id = ?";
        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setInt(1, xProjectId);
            ResultSet rs = ps.executeQuery();
            Issue x;
            while (rs.next()) {
                xIssue_id = rs.getInt("issue_id");
                xType_id = rs.getInt("type_id");
                xProjectId = rs.getInt("project_id");
                xReq_id = rs.getInt("req_id");
                xAssigner_id = rs.getInt("assigner_id");
                xAssignee_id = rs.getInt("assignee_id");
                xStatus = rs.getInt("status");
                xCreated_by_id = rs.getInt("created_by_id");
                xUpdated_by_id = rs.getInt("updated_by_id");
                xTitle = rs.getString("title");
                xDescription = rs.getString("description");
                xDeadline = rs.getTimestamp("deadline");
                xStatus_date = rs.getTimestamp("status_date").toLocalDateTime();
                xCreated_at = rs.getTimestamp("created_at").toLocalDateTime();
                xUpdated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                x = new Issue(xIssue_id, xTitle, xType_id, xReq_id, xAssigner_id, 
                        xAssignee_id, xDeadline, xStatus, xStatus_date, xDescription, 
                        xCreated_at, xCreated_by_id, xUpdated_at, xUpdated_by_id,
                        xProjectId);
                t.add(x);
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Issue getIssueById(int xIssue_id) {
        int xType_id, xReq_id, xAssigner_id, xAssignee_id, xStatus,
                xCreated_by_id, xUpdated_by_id;
        String xTitle, xDescription;
        Date xDeadline;
        LocalDateTime xStatus_date, xCreated_at, xUpdated_at;
        Issue x = null;
        String xSql = "select * from issue where issue_id = ?";
        ResultSet rs = null;
        java.sql.PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(xSql);
            ps.setInt(1, xIssue_id);

            rs = ps.executeQuery();
            if (rs.next()) {
                xType_id = rs.getInt("type_id");
                xReq_id = rs.getInt("req_id");
                xAssigner_id = rs.getInt("assigner_id");
                xAssignee_id = rs.getInt("assignee_id");
                xStatus = rs.getInt("status");
                xCreated_by_id = rs.getInt("created_by_id");
                xUpdated_by_id = rs.getInt("updated_by_id");
                xTitle = rs.getString("title");
                xDescription = rs.getString("description");
                xDeadline = rs.getTimestamp("deadline");
                xStatus_date = rs.getTimestamp("status_date").toLocalDateTime();
                xCreated_at = rs.getTimestamp("created_at").toLocalDateTime();
                xUpdated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                x = new Issue(xIssue_id, xTitle, xType_id, xReq_id, xAssigner_id,
                        xAssignee_id, xDeadline, xStatus, xStatus_date, xDescription,
                        xCreated_at, xCreated_by_id, xUpdated_at, xUpdated_by_id);
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

    public int insertIssue(Issue x) {
        int a = 0;
        try {
            String xSql = "insert into Issue (title, type_id, req_id, "
                    + "assigner_id, assignee_id, deadline, description, "
                    + "created_by_id, updated_by_id, status)"
                    + " values (?,?,?,?,?,?,?,?,?,?)";
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, x.getTitle());
            ps.setInt(2, x.getType_id());
            ps.setInt(3, x.getReq_id() != null ? x.getReq_id() : 0);
            ps.setInt(4, x.getAssigner_id());
            ps.setInt(5, x.getAssignee_id());
            ps.setDate(6, new java.sql.Date(x.getDeadline().getTime()));
            if (x.getDescription() != null) {
                ps.setString(7, x.getDescription());
            } else {
                ps.setNull(7, java.sql.Types.VARCHAR);
            }
            ps.setInt(8, x.getCreated_by_id());
            ps.setInt(9, x.getUpdated_by_id() != null ? x.getUpdated_by_id() : 0);
            ps.setInt(10, x.getStatus());
            ps.executeUpdate();
            a = 1;
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public List<Issue> getIssueListByAssignTo(int xAssignee_id) {
        List<Issue> t = new ArrayList<>();
        String xTitle;
        Date xDeadline;
        int xStatus;
        LocalDateTime xCreated_at;

        String xSql = "SELECT * FROM issue\n"
                + "WHERE assignee_id = ?\n";

        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setInt(1, xAssignee_id);

            ResultSet rs = ps.executeQuery();
            Issue x;
            while (rs.next()) {
                xTitle = rs.getString("title");
                xDeadline = rs.getTimestamp("deadline");
                xStatus = rs.getInt("status");
                xCreated_at = rs.getTimestamp("create_at").toLocalDateTime();
                x = new Issue(xTitle, xAssignee_id, xDeadline, xStatus, xCreated_at);
                t.add(x);
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Issue> getIssueListByStatus(int xStatus) {
        List<Issue> t = new ArrayList<>();
        String xTitle;
        Date xDeadline;
        int xAssignee_id;
        LocalDateTime xCreated_at;

        String xSql = "SELECT * FROM issue\n"
                + "WHERE status = ?\n";

        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setInt(1, xStatus);

            ResultSet rs = ps.executeQuery();
            Issue x;
            while (rs.next()) {
                xTitle = rs.getString("title");
                xDeadline = rs.getTimestamp("deadline");
                xAssignee_id = rs.getInt("assignee_id");
                xCreated_at = rs.getTimestamp("create_at").toLocalDateTime();
                x = new Issue(xTitle, xAssignee_id, xDeadline, xStatus, xCreated_at);
                t.add(x);
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
    
    public List<Issue> searchIssueByTitle(String xSearch) {
        List<Issue> t = new ArrayList<>();
        int xIssue_id, xType_id, xReq_id, xAssigner_id, xAssignee_id, xStatus,
                xCreated_by_id, xUpdated_by_id, xProject_id;
        String xTitle, xDescription;
        Date xDeadline;
        LocalDateTime xStatus_date, xCreated_at, xUpdated_at;
        
        String xSql = "SELECT * FROM issue\n"
                + "WHERE title LIKE CONCAT('%', ?, '%');";

        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, xSearch);

            ResultSet rs = ps.executeQuery();
            Issue x;
            while (rs.next()) {
                xIssue_id = rs.getInt("issue_id");
                xType_id = rs.getInt("type_id");
                xProject_id = rs.getInt("project_id");
                xReq_id = rs.getInt("req_id");
                xAssigner_id = rs.getInt("assigner_id");
                xAssignee_id = rs.getInt("assignee_id");
                xStatus = rs.getInt("status");
                xCreated_by_id = rs.getInt("created_by_id");
                xUpdated_by_id = rs.getInt("updated_by_id");
                xTitle = rs.getString("title");
                xDescription = rs.getString("description");
                xDeadline = rs.getTimestamp("deadline");
                xStatus_date = rs.getTimestamp("status_date").toLocalDateTime();
                xCreated_at = rs.getTimestamp("created_at").toLocalDateTime();
                xUpdated_at = rs.getTimestamp("updated_at").toLocalDateTime();
                x = new Issue(xIssue_id, xTitle, xType_id, xReq_id, xAssigner_id, 
                        xAssignee_id, xDeadline, xStatus, xStatus_date, xDescription, 
                        xCreated_at, xCreated_by_id, xUpdated_at, xUpdated_by_id,
                        xProject_id);
                t.add(x);
            }
            db.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public int updateIssue(Issue x) {
        int a = 0;
        String xSql = "UPDATE issue "
                + "SET title = ?, type_id = ?, req_id = ?, assigner_id = ?, assignee_id = ?, deadline = ?, "
                + "description = ?, status = ?, updated_at = CURRENT_TIMESTAMP, updated_by_id = ? "
                + "WHERE issue_id = ?";
        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, x.getTitle());
            ps.setInt(2, x.getType_id());

            if (x.getReq_id() != null) {
                ps.setInt(3, x.getReq_id());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }

            ps.setInt(4, x.getAssigner_id());
            ps.setInt(5, x.getAssignee_id());
            ps.setDate(6, new java.sql.Date(x.getDeadline().getTime()));

            if (x.getDescription() != null) {
                ps.setString(7, x.getDescription());
            } else {
                ps.setNull(7, java.sql.Types.VARCHAR);
            }

            ps.setInt(8, x.getStatus());
            ps.setInt(9, x.getUpdated_by_id());
            ps.setInt(10, x.getIssue_id());
            ps.executeUpdate();
            a = 1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }
        return a;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.MARCH, 1); // Tháng trong Calendar bắt đầu từ 0
        Date deadline = calendar.getTime();
        IssueDAO x = new IssueDAO();
        Issue a = new Issue(10, "Homepage Bug", 13, 1, 2, 3, deadline, 2, "Fix the layout bug on the homepage after redesign", 1, 1);
        
//        Issue("Homepage Bug", 13, 1, 2, 3, deadline, 2, "Fix the layout bug on the homepage after redesign",
//                1, 1, 10);
        x.updateIssue(a);
    }

    public int deleteIssue(int xIssue_id) {
        int a = 0;
        String xSql = "delete from issue where issue_id = ?";
        try {
            java.sql.PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setInt(1, xIssue_id);
            ps.executeUpdate();
            a = 1;
            db.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
}
