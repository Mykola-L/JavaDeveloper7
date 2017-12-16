package ua.servlets.controller.manufacturer;

import ua.servlets.DaoSingleton;
import ua.servlets.model.Manufacturer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class UpdateManufacturerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        String manufacturerIdStr = req.getParameter("manufacturer_id");
        UUID manufacturerId = UUID.fromString(manufacturerIdStr);

        String name = req.getParameter("name");

        Manufacturer manufacturer = DaoSingleton.getINSTANCE().getManufacturerDAO().getById(manufacturerId);
        manufacturer.setName(name);

        DaoSingleton.getINSTANCE().getManufacturerDAO().update(manufacturer);

        resp.getWriter().println("Manufacturer updated!");
        resp.getWriter().println("<br>");
        resp.getWriter().println("<br>");

        String link = " <form action=\"listManufacturer\">\n" +
                "   <p><input type=\"submit\" value=\"Read all manufacturer\"></p>\n" +
                "  </form>";
        resp.getWriter().println(link);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String manufacturerIdStr = req.getParameter("manufacturer_id");
        UUID manufacturerId = UUID.fromString(manufacturerIdStr);

        Manufacturer manufacturer = DaoSingleton.getINSTANCE().getManufacturerDAO().getById(manufacturerId);
        String name = manufacturer.getName();

        req.setAttribute("manufacturerId", manufacturerId);
        req.setAttribute("name", name);

        req.getRequestDispatcher("/jsp/update_manufacturer.jsp").forward(req, resp);
    }
}
