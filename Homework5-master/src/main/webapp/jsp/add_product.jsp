<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.11.2017
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ua.servlets.model.Manufacturer" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.servlets.DaoSingleton" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Add Product</title>
</head>
<body>
<%!
    private List<Manufacturer> manufacturers = DaoSingleton.getINSTANCE().getManufacturerDAO().getAll();
%>
<h2>Add new product</h2>
<form action="addProduct" method="POST">
    <p>Input Name</p>
    <input type="text" name="name"/>
    <br>
    <p>Input Price</p>
    <input type="text" name="price"/>
    <br>
    <p>Choice Manufacturer</p>
    <select name="manufacturer"/>
    <%
        for (Manufacturer manufacturer : manufacturers) {
            out.println("<option value=\"" + manufacturer.getName() + "\">"
                    + manufacturer.getName() + "</option>");
        }
    %>
    </select>
    <br>
    <br>
    <input type="submit" value="Add"/>
</form>

</body>
</html>
