/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDateTime;
/**
 *
 * @author admin
 */
public class Setting {

    private int setting_id;
    private String name;
    private String value;
    private int type_id;
    private int priority;
    private String status;
    private String description;
    private LocalDateTime createdAt;
    private int createdById;
    private LocalDateTime updatedAt;
    private int updatedById;

    public Setting() {
        
    }

    public Setting(int setting_id, String name) {
        this.setting_id = setting_id;
        this.name = name;
    }

    public Setting(int setting_id, String name, String value, int type_id, int priority, String status, String description, int createdById, int updatedById) {
        this.setting_id = setting_id;
        this.name = name;
        this.value = value;
        this.type_id = type_id;
        this.priority = priority;
        this.status = status;
        this.description = description;
        this.createdById = createdById;
        this.updatedById = updatedById;
    }
    
    public Setting(int setting_id, String name, String value, int type_id, int priority, String status, String description) {
        this.setting_id = setting_id;
        this.name = name;
        this.value = value;
        this.type_id = type_id;
        this.priority = priority;
        this.status = status;
        this.description = description;
    }

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(int updatedById) {
        this.updatedById = updatedById;
    }

    @Override
    public String toString() {
        return "Setting{" + "setting_id=" + setting_id + ", name=" + name + ", value=" + value + ", type_id=" + type_id + ", priority=" + priority + ", status=" + status + ", description=" + description + ", createdAt=" + createdAt + ", createdById=" + createdById + ", updatedAt=" + updatedAt + ", updatedById=" + updatedById + '}';
    }

    public Object getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}