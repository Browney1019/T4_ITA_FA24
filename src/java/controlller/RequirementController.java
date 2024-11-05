/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import model.Requirement;
import service.RequirementService;

/**
 *
 * @author DELL
 */
public class RequirementController extends HttpServlet {

    private RequirementService requirementService;

    @Override
    public void init() throws ServletException {
        requirementService = new RequirementService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String rService = request.getParameter("requirementservice");

        if (rService == null || rService.isEmpty()) {
            displayRequirementList(request, response);

        } else if (rService.equals("requirementsearch")) {
            searchRequirementList(request, response);

        } else if (rService.equals("requirementdetail")) {
            displayRequirementDetail(request, response);

        } else if (rService.equals("addform")) {
            displayAddForm(request, response);

        } else if (rService.equals("requirementadd")) {
            handleRequirementAdd(request, response);

        } else if (rService.equals("updateform")) {
            displayUpdateForm(request, response);

        } else if (rService.equals("requirementupdate")) {
            handleRequirementUpdate(request, response);

        } else if (rService.equals("requirementdelete")) {
            handleRequirementDelete(request, response);

        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void displayRequirementList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/JSP/requirementList.jsp";
        List<Requirement> requirements = requirementService.getRequirementList();
        request.setAttribute("list", requirements);
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void searchRequirementList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/JSP/requirementList.jsp";
        String sSearch = request.getParameter("txtSearch");
        List<Requirement> requirements = requirementService.getRequirementsByOwner(Integer.parseInt(sSearch));
        request.setAttribute("list", requirements);
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void displayRequirementDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/JSP/requirementDetail.jsp";
        String sReq_id = request.getParameter("requirement_id");

        if (sReq_id != null && !sReq_id.isEmpty()) {
            int xReq_id = Integer.parseInt(sReq_id);
            Requirement requirement = requirementService.getRequirementById(xReq_id);

            if (requirement != null) {
                request.setAttribute("requirement", requirement);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void displayAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/JSP/requirementAdd.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void handleRequirementAdd(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String title = request.getParameter("txtTitle");
        int ownerId = Integer.parseInt(request.getParameter("txtOwner"));
        int complexityId = Integer.parseInt(request.getParameter("txtComplexity"));
        int statusId = Integer.parseInt(request.getParameter("txtStatus"));
        String description = request.getParameter("txtDescription");

        LocalDateTime createdAt = LocalDateTime.now();
        int createdById = 1; // Assuming current user ID

        boolean isInserted = requirementService.insertRequirement(title, ownerId, complexityId, statusId,
                description, createdAt, createdById);

        if (isInserted) {
            response.sendRedirect("requirementcontroller?requirementservice=");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void handleRequirementDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String sReq_id = request.getParameter("requirement_id");

        if (sReq_id != null && !sReq_id.isEmpty()) {
            int xReq_id = Integer.parseInt(sReq_id);
            requirementService.deleteRequirement(xReq_id);
            response.sendRedirect("requirementcontroller?requirementservice=");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void displayUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/JSP/requirementUpdate.jsp";
        String sReq_id = request.getParameter("requirement_id");

        if (sReq_id != null && !sReq_id.isEmpty()) {
            int xReq_id = Integer.parseInt(sReq_id);
            Requirement requirement = requirementService.getRequirementById(xReq_id);

            if (requirement != null) {
                request.setAttribute("requirement", requirement);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void handleRequirementUpdate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int reqId = Integer.parseInt(request.getParameter("requirement_id"));
        String title = request.getParameter("txtTitle");
        int ownerId = Integer.parseInt(request.getParameter("txtOwner"));
        int complexityId = Integer.parseInt(request.getParameter("txtComplexity"));
        int statusId = Integer.parseInt(request.getParameter("txtStatus"));
        String description = request.getParameter("txtDescription");

        LocalDateTime updatedAt = LocalDateTime.now();
        int updatedById = 1; // Assuming current user ID

        boolean isUpdated = requirementService.updateRequirement(reqId, title, ownerId, complexityId, statusId,
                description, updatedAt, updatedById);

        if (isUpdated) {
            response.sendRedirect("requirementcontroller?requirementservice=");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Requirement Controller Servlet";
    }
}
