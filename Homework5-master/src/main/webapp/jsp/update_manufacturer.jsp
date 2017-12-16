<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.11.2017
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Manufacturer</title>
</head>
<body>
<%!

%>
<h2>Update manufacturer</h2>
<form action="updateManufacturer" method="POST">
    <p>Input new Name</p>
    <input type="text" name="name" value="<%=request.getAttribute("name")%>"/>
    <br>
    <input type="hidden" name="manufacturer_id" value="<%=request.getAttribute("manufacturerId")%>"/>
    <input type="submit" value="Update"/>
</form>

</body>
</html>
