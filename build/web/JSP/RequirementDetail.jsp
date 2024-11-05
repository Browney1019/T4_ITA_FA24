<%-- 
    Document   : RequirementDetail
    Created on : Nov 5, 2024, 4:39:47 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.*" %>
<%@page import = "dao.*" %>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Requirement Detail</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <a href="requirementcontroller?requirementservice=" class="navbar-brand">Requirement Management</a>
                </div>

                <ul class="navbar-nav">
                    <li><a href=""
                           class="nav-link">HomePage</a></li>
                </ul>

                <ul class="navbar-nav navbar-collapse justify-content-end">
                    <li><a href=""
                           class="nav-link">Logout</a></li>
                </ul>
            </nav>
        </header>
        <%
            Requirement requirement = (Requirement) request.getAttribute("requirement");
            Setting setting = (Setting) request.getAttribute("setting");
            UserDAO userDAO = new UserDAO();
        %>
        <p></p>
        <div class="container col-md-8">
            <div class="card">
                <div class="card-body">
                    <caption>
                        <h2>View Requirement Detail</h2>
                    </caption>

                    <div class="form-row">
                        <fieldset class="form-group col-md-7">
                            <label>Title*:</label>
                            <input type="text" class="form-control" value="<%= requirement.getTitle() %>" disabled>
                        </fieldset>
                        
                        <fieldset class="form-group col-md-4">
                            <label>Deadline*:</label> 
                            <input type="text" value="<%= requirement.getDeadline() %>" class="form-control" disabled>
                        </fieldset>

                        <fieldset class="form-group col-md-7">
                            <label>Requirement Type*:</label> 
                            <input type="text" class="form-control" value="<%= setting.getName() %>" disabled>
                        </fieldset>

                        <%
                            User assigner = userDAO.getUserById1(requirement.getAssigner_id());
                        %>
                        <fieldset class="form-group col-md-4">
                            <label>Assigner*:</label> 
                            <input type="text" value="<%= assigner.getFullName() %>" class="form-control" disabled>
                        </fieldset>

                        <%
                            String sStatus = "Unknown";
                            switch(requirement.getStatus()) {
                                case 0: sStatus = "Pending"; break;
                                case 1: sStatus = "To Do"; break;
                                case 2: sStatus = "Doing"; break;
                                case 3: sStatus = "Done"; break;
                                case 4: sStatus = "Closed"; break;
                            }
                        %>
                        <fieldset class="form-group col-md-7">
                            <label>Status*:</label> 
                            <input type="text" class="form-control" value="<%= sStatus %>" disabled>
                        </fieldset>

                        <%
                            User assignee = userDAO.getUserById1(requirement.getAssignee_id());
                        %>
                        <fieldset class="form-group col-md-4">
                            <label>Assignee*:</label> 
                            <input type="text" value="<%= assignee.getFullName() %>" class="form-control" disabled>
                        </fieldset>

                        <fieldset class="form-group col-md-7">
                            <label>Description:</label> 
                            <textarea type="text" class="form-control" rows="4" disabled><%= requirement.getDescription() %></textarea>
                        </fieldset>

                        <fieldset class="form-group col-md-4">
                            <label>Created At:</label> 
                            <input type="datetime-local" value="<%= requirement.getCreated_at() %>" class="form-control" disabled>
                            <label>Updated At:</label> 
                            <input type="datetime-local" value="<%= requirement.getUpdated_at() %>" class="form-control" disabled>
                        </fieldset>
                    </div>
                    <div>
                        <button type="button" class="btn btn-success" style="background-color: gray; border-color: black;" onclick="history.back();">Back</button>
                        <a href="requirementcontroller?requirementservice=updateform&requirement_id=<%= requirement.getReq_id() %>" class="btn btn-success">Edit</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
