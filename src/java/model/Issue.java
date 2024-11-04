/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Issue {
    private int issue_id;
    private String title;
    private int type_id;
    private Integer req_id;
    private int assigner_id;
    private int assignee_id;
    private Date deadline;
    private int status;
    private LocalDateTime status_date;
    private String description;
    private LocalDateTime created_at;
    private int created_by_id;
    private LocalDateTime updated_at;
    private Integer updated_by_id;
    private int project_id;

    public Issue() {
    }

    public Issue(int issue_id, String title, int type_id, Integer req_id, int assigner_id, int assignee_id, Date deadline, int status, LocalDateTime status_date, String description, LocalDateTime created_at, int created_by_id, LocalDateTime updated_at, Integer updated_by_id, int project_id) {
        this.issue_id = issue_id;
        this.title = title;
        this.type_id = type_id;
        this.req_id = req_id;
        this.assigner_id = assigner_id;
        this.assignee_id = assignee_id;
        this.deadline = deadline;
        this.status = status;
        this.status_date = status_date;
        this.description = description;
        this.created_at = created_at;
        this.created_by_id = created_by_id;
        this.updated_at = updated_at;
        this.updated_by_id = updated_by_id;
        this.project_id = project_id;
    }
    
    
    public Issue(String title, int assignee_id, Date deadline, int status, LocalDateTime created_at) {
        this.title = title;
        this.assignee_id = assignee_id;
        this.deadline = deadline;
        this.status = status;
        this.created_at = created_at;
    }

    public Issue(int issue_id, String title, int type_id, Integer req_id, int assigner_id, int assignee_id, Date deadline, int status, String description, int created_by_id, Integer updated_by_id) {
        this.issue_id = issue_id;
        this.title = title;
        this.type_id = type_id;
        this.req_id = req_id;
        this.assigner_id = assigner_id;
        this.assignee_id = assignee_id;
        this.deadline = deadline;
        this.status = status;
        this.description = description;
        this.created_by_id = created_by_id;
        this.updated_by_id = updated_by_id;
    }

    public Issue(String title, int type_id, Integer req_id, int assigner_id, int assignee_id, Date deadline, int status, String description, int created_by_id, Integer updated_by_id, int project_id) {
        this.title = title;
        this.type_id = type_id;
        this.req_id = req_id;
        this.assigner_id = assigner_id;
        this.assignee_id = assignee_id;
        this.deadline = deadline;
        this.status = status;
        this.description = description;
        this.created_by_id = created_by_id;
        this.updated_by_id = updated_by_id;
        this.project_id = project_id;
    }

    public Issue(int issue_id, String title, int type_id, Integer req_id, int assigner_id, int assignee_id, Date deadline, int status, LocalDateTime status_date, String description, LocalDateTime created_at, int created_by_id, LocalDateTime updated_at, Integer updated_by_id) {
        this.issue_id = issue_id;
        this.title = title;
        this.type_id = type_id;
        this.req_id = req_id;
        this.assigner_id = assigner_id;
        this.assignee_id = assignee_id;
        this.deadline = deadline;
        this.status = status;
        this.status_date = status_date;
        this.description = description;
        this.created_at = created_at;
        this.created_by_id = created_by_id;
        this.updated_at = updated_at;
        this.updated_by_id = updated_by_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(int issue_id) {
        this.issue_id = issue_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public Integer getReq_id() {
        return req_id;
    }

    public void setReq_id(Integer req_id) {
        this.req_id = req_id;
    }

    public int getAssigner_id() {
        return assigner_id;
    }

    public void setAssigner_id(int assigner_id) {
        this.assigner_id = assigner_id;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(int assignee_id) {
        this.assignee_id = assignee_id;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getStatus_date() {
        return status_date;
    }

    public void setStatus_date(LocalDateTime status_date) {
        this.status_date = status_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public int getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(int created_by_id) {
        this.created_by_id = created_by_id;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getUpdated_by_id() {
        return updated_by_id;
    }

    public void setUpdated_by_id(Integer updated_by_id) {
        this.updated_by_id = updated_by_id;
    }
    
}
