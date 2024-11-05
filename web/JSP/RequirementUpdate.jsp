<%-- 
    Document   : RequirementUpdate
    Created on : Nov 5, 2024, 4:42:02 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "model.*" %>
<%@page import = "dao.*" %>
<%@page import = "java.util.*" %>
<%@page import = "java.time.*" %>
<%@page import = "java.time.format.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Requirement</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                <div>
                    <a href="requirementcontroller?requirementservice=" class="navbar-brand">Requirement Management</a>
                </div>
                <ul class="navbar-nav">
                    <li><a href="" class="nav-link">HomePage</a></li>
                </ul>
                <ul class="navbar-nav navbar-collapse justify-content-end">
                    <li><a href="" class="nav-link">Logout</a></li>
                </ul>
            </nav>
        </header>

        <%
            List<Setting> types = (List<Setting>) request.getAttribute("typeList");
            List<User> users = (List<User>) request.getAttribute("userList");
            List<String> statuses = (List<String>) request.getAttribute("statusList");
            Requirement requirement = (Requirement) request.getAttribute("requirement");
        %>

        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <p></p>
                    <form action="requirementcontroller">
                        <caption>
                            <h2>Update Requirement</h2>
                        </caption>

                        <fieldset class="form-group">
                            <label>Title*:</label> 
                            <input type="text" class="form-control" name="txtTitle" 
                                   required="required" minlength="5" 
                                   placeholder="<%= requirement.getTitle() %>">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Requirement Type*:</label> 
                            <select class="form-control" name="txtType">
                                <option value="<%= requirement.getType_id() %>" disabled selected>
                                    <%= requirement.getTypeName() %>
                                </option>
                                <option value="">None</option>
                                <% for(Setting type: types) { %>  
                                    <option value="<%= type.getSetting_id() %>"> 
                                        <%= type.getName() %> 
                                    </option>
                                <% } %>
                            </select>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Status*:</label> 
                            <select class="form-control" name="txtStatus">
                                <option value="<%= requirement.getStatus() %>" disabled selected>
                                    <%= statuses.get(requirement.getStatus()) %>
                                </option>
                                <% for(int i = 0; i < statuses.size(); i++) { %>  
                                    <option value="<%= i %>"> <%= statuses.get(i) %> </option>
                                <% } %>
                            </select>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Deadline:</label> 
                            <input type="date" value="<%= requirement.getFormattedDeadline() %>" 
                                   class="form-control" name="txtDeadline">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Assignee:</label>
                            <select class="form-control" name="slAssignee">
                                <option value="<%= requirement.getAssignee_id() %>" disabled selected>
                                    <%= requirement.getAssigneeName() %>
                                </option>
                                <% for(User user: users) { %>
                                    <option value="<%= user.getUserId() %>"> <%= user.getFullName() %> </option>
                                <% } %>
                            </select>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Description:</label> 
                            <textarea class="form-control" rows="4" name="txtDescription" 
                                      placeholder="<%= requirement.getDescription() %>"></textarea>
                        </fieldset>
                        
                        <input type="hidden" value="<%= requirement.getReq_id() %>" name="requirement_id">
                        <input type="hidden" value="requirementupdate" name="requirementservice">

                        <button type="submit" class="btn btn-success">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

