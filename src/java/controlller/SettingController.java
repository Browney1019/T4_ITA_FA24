
package controlller;

import dao.SettingDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import java.sql.SQLException;
import model.Setting;
import service.SettingService;

/**
 *
 * @author admin
 */
@WebServlet(name = "SettingController", urlPatterns = {"/SettingController"})
public class SettingController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        SettingDAO dao = new SettingDAO();
        SettingService ss = new SettingService();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listSetting";
        }

//        try (PrintWriter out = response.getWriter()) {
        if (service.equals("removeSetting")) {
            int setting_id = Integer.parseInt(request.getParameter("setting_id"));
            ss.removeSetting(setting_id);
            response.sendRedirect("SettingController");
        }

        if (service.equals("updateSetting")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
//                int setting_id = Integer.parseInt(request.getParameter("setting_id"));
//                Vector<Setting> vector = ss.getSetting("SELECT * FROM setting WHERE setting_id=" + setting_id);
//                request.setAttribute("data", vector);
                dao.forwardToJSP(request, response, "/jsp/updateSetting.jsp");
            } else { 
                int setting_id = Integer.parseInt(request.getParameter("setting_id"));
                String name = request.getParameter("name"),
                        value = request.getParameter("value"),
                        status = request.getParameter("status");
                String description = request.getParameter("decription");
                int type_id = Integer.parseInt(request.getParameter("type_id"));
                int priority = Integer.parseInt(request.getParameter("priority"));

                Setting setting = new Setting(setting_id, name, value, type_id, priority, status, description, setting_id, type_id);
                ss.updateSetting(setting);
                response.sendRedirect("SettingController");
            }
        }

        if (service.equals("addSetting")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                dao.forwardToJSP(request, response, "/jsp/insertSetting.jsp");
            } else {
                String name = request.getParameter("name"),
                        value = request.getParameter("value"),
                        status = request.getParameter("status");
                int type_id = Integer.parseInt(request.getParameter("type_id"));
                int priority = Integer.parseInt(request.getParameter("priority"));
                String description = request.getParameter("decription");
                Setting setting = new Setting(type_id, name, value, type_id, priority, status, description, type_id, type_id);
                ss.insertSetting(setting);
                response.sendRedirect("SettingController");
            }
        }

        if (service.equals("listSetting")) {
            String submit = request.getParameter("submit");
            Vector<Setting> vector;
            if (submit == null) {
                vector = ss.getSetting("SELECT * FROM setting");
            } else {
                String name = request.getParameter("name");
                vector = ss.getSetting("SELECT * FROM setting WHERE name LIKE '%" + name + "%'");
            }
            request.setAttribute("data", vector);
            request.setAttribute("tableTitle", "List of Settings");
            request.setAttribute("pageTitle", "Settings Management");

//            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ListSettings.jsp");
//            dispatcher.forward(request, response);
            request.getRequestDispatcher("/jsp/ListSettings.jsp").forward(request, response);
        }
    }
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
