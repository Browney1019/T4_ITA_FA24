/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.IssueDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import model.Issue;

/**
 *
 * @author DELL
 */
public class IssueService {
    private final IssueDAO issueDAO;

    // Constructor initializes SettingDAO instance
    public IssueService() {
        issueDAO = new IssueDAO();
    }
    
    public List<Issue> getIssueList() {
        return issueDAO.getIssueList();
    }
    
    public List<Issue> searchIssue(String xSearch) {
        return issueDAO.searchIssueByTitle(xSearch);
    }

    // Business logic to get issues by assignee
    public List<Issue> getIssueListByAssignee(int assigneeId) {
        return issueDAO.getIssueListByAssignTo(assigneeId);
    }

    // Business logic to get issues by status
    public List<Issue> getIssueListByStatus(int status) {
        return issueDAO.getIssueListByStatus(status);
    }
    
     public Issue getIssueById(int issueId) {
        return issueDAO.getIssueById(issueId);
    }

    // Business logic to insert new setting
    public boolean insertIssue(Issue issue) {
        // Validate setting data (if needed)
        return issueDAO.insertIssue(issue) > 0;
    }

    // Business logic to update a setting
    public boolean updateIssue(Issue issue) {
        return issueDAO.updateIssue(issue) > 0;
    }

    // Business logic to remove a setting
    public boolean removeIssue(int id) {
        return issueDAO.deleteIssue(id) > 0;
    }
    
    public boolean validateString(String value) {
        return value != null && !value.trim().isEmpty(); // Check for non-null and non-empty
    }
    
    public boolean validateInt(String value) {
        try {
            Integer.parseInt(value);  // Thử chuyển đổi chuỗi thành int
            return true;               // Trả về true nếu chuyển đổi thành công
        } catch (NumberFormatException e) {
            return false;              // Trả về false nếu xảy ra lỗi
        }
    }
    
    public boolean validateIssue(Issue issue) {
        return issue != null; 
        
    }
    
    public boolean validateLocalDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // định dạng mong muốn
        try {
            LocalDateTime.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    public boolean validateDate(String input) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // định dạng mong muốn
        formatter.setLenient(false); // đảm bảo kiểm tra nghiêm ngặt
        try {
            Date date = formatter.parse(input); // nếu thành công, ngày hợp lệ
            return true;
        } catch (ParseException e) {
            return false; // nếu không hợp lệ, sẽ ném lỗi
        }
    }
    
//    private boolean validateIssue(Issue issue) {
//        if (issue == null) {
//            return false; // Issue object should not be null
//        }
//
//        // Validate string and integer properties
//        return validateString(issue.getTitle()) &&
//               validateInt(issue.getType_id()) &&
//               validateInt(issue.getReq_id()) &&
//               validateInt(issue.getAssigner_id()) &&
//               validateInt(issue.getAssignee_id()) &&
//               issue.getDeadline() != null && // Deadline cannot be null
//               validateString(issue.getDescription()) &&
//               validateInt(issue.getCreated_by_id()) &&
//               validateInt(issue.getUpdated_by_id());
//    }

}
