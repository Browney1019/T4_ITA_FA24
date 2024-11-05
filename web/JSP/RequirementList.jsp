<%-- 
    Document   : RequirementList
    Created on : Nov 5, 2024, 4:41:44 AM
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
        <title>Requirement List</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            * {
                box-sizing: border-box;
            }
            form.example input[type=text] {
                padding: 5px;
                font-size: 17px;
                border: 1px solid grey;
                float: left;
                width: 80%;
                background: #f1f1f1;
            }
            form.example button {
                float: left;
                width: 20%;
                padding: 5px;
                background: #2196F3;
                color: white;
                font-size: 17px;
                border: 1px solid grey;
                border-left: none;
                cursor: pointer;
            }
            form.example button:hover {
                background: #0b7dda;
            }
            form.example::after {
                content: "";
                clear: both;
                display: table;
            }
        </style>
    </head>
    <body>
        <%
            List<Requirement> list = (List<Requirement>) request.getAttribute("list");
            UserDAO userDAO = new UserDAO();
        %>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div>
                    <a href="requirementcontroller?requirementservice=" class="navbar-brand">Requirement Management</a>
                </div>

                <ul class="navbar-nav">
                    <li><a href="" class="nav-link">HomePage</a></li>
                </ul>

                <form class="example" action="requirementcontroller">
                    <input type="text" placeholder="Search.." name="txtSearch">
                    <input type="hidden" value="requirementsearch" name="requirementservice" >
                    <button type="submit"><i class="fa fa-search"></i></button>
                </form>

                <ul class="navbar-nav navbar-collapse justify-content-end">
                    <li><a href="" class="nav-link">Logout</a></li>
                </ul>
            </nav>
        </header>

        <div class="row">
            <div class="container">
                <h3 class="text-center">Requirement List</h3>
                <hr>
                <div class="container text-left">
                    <a href="requirementcontroller?requirementservice=addform" class="btn btn-success">Add Requirement</a>
                </div>
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Status</th>
                            <th>Assigned To</th>
                            <th>Deadline</th>
                            <th>Created Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if(!list.isEmpty()){
                                int m = 1;
                                for(Requirement req: list) {
                                    LocalDateTime createdAt = req.getCreated_at();
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    String formattedCreatedAt = createdAt.format(formatter);
                                    User assignee = userDAO.getUserById1(req.getAssignee_id());
                        %>
                        <tr>
                            <td><%= m %></td>
                            <td><%= req.getTitle() %></td>
                            <%
                                int reqStatus = req.getStatus();
                                String sStatus = null;
                                if(reqStatus == 0){
                                    sStatus = "Pending";
                                } else if(reqStatus == 1){
                                    sStatus = "To Do";
                                } else if(reqStatus == 2){
                                    sStatus = "Doing";
                                } else if(reqStatus == 3){
                                    sStatus = "Done";
                                } else if(reqStatus == 4){
                                    sStatus = "Closed";
                                }
                            %>
                            <td><%= sStatus %></td>
                            <td><%= assignee.getFullName() %></td>
                            <td><%= req.getDeadline() %></td>
                            <td><%= formattedCreatedAt %></td>
                            <td>
                                <a href="requirementcontroller?requirementservice=requirementdetail&requirement_id=<%= req.getReq_id() %>">View Detail</a>
                                <a href="requirementcontroller?requirementservice=requirementdelete&requirement_id=<%= req.getReq_id() %>">Delete</a>
                            </td>
                        </tr>
                        <% 
                                    m++;
                                }
                            } else {
                        %>
                        <tr>
                            <td colspan="7" class="text-center">No requirements found.</td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>     
    </body>
</html>

