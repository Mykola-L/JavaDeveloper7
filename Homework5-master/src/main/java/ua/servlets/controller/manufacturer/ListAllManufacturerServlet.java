package ua.servlets.controller.manufacturer;

import ua.servlets.DaoSingleton;
import ua.servlets.model.Manufacturer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListAllManufacturerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        List<Manufacturer> manufacturers = DaoSingleton.getINSTANCE().getManufacturerDAO().getAll();

        for (Manufacturer manufacturer : manufacturers) {
            String manufacturerText = manufacturer.getName();

            String updateLinkManufacturer = "<a href=\"/updateManufacturer?manufacturer_id=" + manufacturer.getId() + "\">Update</a>";

            String readLinkManufacturer = "<a href=\"/manufacturer?manufacturer_id=" + manufacturer.getId() + "\">Read</a>";

            String deleteLinkManufacturer = "<a href=\"/deleteManufacturer?manufacturer_id=" + manufacturer.getId() + "\">Delete</a>";

            String finalLink = manufacturerText + " " + updateLinkManufacturer + " " + readLinkManufacturer + " "
                    + deleteLinkManufacturer;

            resp.getWriter().println(finalLink);
            resp.getWriter().println("<br><br>");
        }
        String addManufacturerLink = "<form action=\"addManufacturer\">\n" +
                "<p><input type=\"submit\" value=\"Add new manufacturer\"></p>";

        String welcomePageText = "<a href=\"/choice\">Homepage</a>";

        resp.getWriter().println(addManufacturerLink + "<br>" + welcomePageText);
    }
}
