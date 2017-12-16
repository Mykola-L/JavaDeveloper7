<%@ page import="ua.servlets.model.Manufacturer" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.servlets.DaoSingleton" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.11.2017
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
<%!
    private List<Manufacturer> manufacturers = DaoSingleton.getINSTANCE().getManufacturerDAO().getAll();
%>
<h2>Update product</h2>
<form action="updateProduct" method="post">
    <p>Input new Name</p>
    <input type="text" name="name" value="<%=request.getAttribute("name")%>">
    <p>Input new Price</p>
    <input type="text" name="price" value="<%=request.getAttribute("price")%>">
    <p>Choice manufacturer</p>
    <select name="manufacturer"/>
    <%
        for (Manufacturer manufacturer : manufacturers) {
            out.println("<option ");
            if (manufacturer.getName().equals(request.getAttribute("manufacturerName")))
                out.println("selected");
            out.println(" value=\"" + manufacturer.getName() + "\">"
                    + manufacturer.getName() + "</option>");
        }
    %>
    </select>
    <br>
    <br>
    <input type="hidden" name="product_id" value="<%=request.getAttribute("productId")%>">
    <input type="submit" value="Update"/>
</form>

</body>
</html>
