/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.*;
import java.time.LocalDateTime;
import java.util.List;
import model.*;

/**
 *
 * @author DELL
 */
public class RequirementService {

    private final RequirementDAO requirementDAO;

    // Constructor initializes SettingDAO instance
    public RequirementService() {
        requirementDAO = new RequirementDAO();
    }

    public Requirement getRequirementById(int reqId) {
        return requirementDAO.getRequirementById(reqId);
    }
    
    public List<Requirement> getRequirementList() {
        return requirementDAO.getRequirementList();
    }
    
    public List<Requirement> getAllRequirements() {
        return requirementDAO.getRequirementList();
    }

    // Business logic to get requirements by status
    public List<Requirement> getRequirementsByStatus(int statusId) {
        return requirementDAO.getRequirementListByStatus(statusId);
    }

    // Business logic to get requirements by owner
    public List<Requirement> getRequirementsByOwner(int ownerId) {
        return requirementDAO.getRequirementListByAssignTo(ownerId);
    }

    // Business logic to insert a new requirement
    public boolean insertRequirement(String title, int ownerId, int complexityId, int statusId, 
                                     String description, LocalDateTime createdAt, int createdById) {
        return requirementDAO.insertRequirement(title, ownerId, complexityId, statusId, 
                                                description, createdAt, createdById) > 0;
    }

    // Business logic to update a requirement
    public boolean updateRequirement(int reqId, String title, int ownerId, int complexityId, 
                                     int statusId, String description, LocalDateTime updatedAt, 
                                     int updatedById) {
        requirementDAO.updateRequirement(reqId, title, ownerId, complexityId, statusId, 
                                         description, updatedAt, updatedById);
        return true;
    }

    // Business logic to delete a requirement
    public boolean deleteRequirement(int reqId) {
        requirementDAO.deleteRequirement(reqId);
        return true;
    }
    
    // Business logic to get a requirement by ID
//    public Requirement getRequirementById(int reqId) {
//        return requirementDAO.getRequirementById(reqId);
//    }
    
}
