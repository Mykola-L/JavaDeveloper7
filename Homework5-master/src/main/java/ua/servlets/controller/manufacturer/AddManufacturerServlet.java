package ua.servlets.controller.manufacturer;

import ua.servlets.DaoSingleton;
import ua.servlets.model.Manufacturer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddManufacturerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/add_manufacturer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml; charset=utf-8");
        try {
            String name = req.getParameter("name");
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setName(name);

            DaoSingleton.getINSTANCE().getManufacturerDAO().save(manufacturer);

            resp.getWriter().println("manufacturer added!<br><br>");
            resp.getWriter().println("<br>");

            String link = "<form action=\"listManufacturer\">\n" +
                    "<p><input type=\"submit\" value=\"Read all manufacturer\"></p>\n" +
                    "</form>";
            resp.getWriter().println(link);
        } catch (Exception e) {
            resp.getWriter().println("Error in adding manufacturer");
        }
    }
}
