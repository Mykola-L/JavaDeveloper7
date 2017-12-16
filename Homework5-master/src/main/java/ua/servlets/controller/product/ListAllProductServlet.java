package ua.servlets.controller.product;

import ua.servlets.DaoSingleton;
import ua.servlets.model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListAllProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");

        List<Product> products = DaoSingleton.getINSTANCE().getProductDAO().getAll();

        for (Product product : products) {
            String productText = product.getName();

            String updateLinkProduct = "<a href=\"/updateProduct?product_id=" + product.getId() + "\">Update</a>";

            String readLinkProduct = "<a href=\"/product?product_id=" + product.getId() + "\">Read</a>";

            String deleteLinkProduct = "<a href=\"/deleteProduct?product_id=" + product.getId() + "\">Delete</a>";

            String finalLink = productText + " " + updateLinkProduct + " " + readLinkProduct + " "
                    + deleteLinkProduct;


            resp.getWriter().println(finalLink);
            resp.getWriter().println("<br><br>");
        }
        String addProductLink = "<form action=\"addProduct\">\n" +
                "<p><input type=\"submit\" value=\"Add new product\"></p>";

        String welcomePageText = "<a href=\"/choice\">Homepage</a>";

        resp.getWriter().println(addProductLink + "<br>" + welcomePageText);
    }
}
