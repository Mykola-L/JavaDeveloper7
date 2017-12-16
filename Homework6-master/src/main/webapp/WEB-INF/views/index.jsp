<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/theme.css">
</head>
<body>
<c:set var="listsize" value="${fn:length(productList)}"/>
<div class="container"  >
    <div class="jumbotron">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

            <h2>Welcome ${pageContext.request.userPrincipal.name} |
                <a onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h2>

        </c:if>

        <c:if test="${listsize == 0}">
            <div>
                <p>No products yet. Want to start adding them?</p>
                <a href="${contextPath}/product/add"><button type="button" class="btn btn-success">Add a product
                </button></a>
            </div>
        </c:if>
    </div>

    <c:if test="${listsize > 0}">
        <div class="col-md-6 contacts-table">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Products</h3>
                </div>
                <div class="panel-body">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <div class="add-contact">
                            <button type="button" class="btn btn-success"><a href="${contextPath}/product/add">Add a product</a>
                            </button>
                        </div>
                    </sec:authorize>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Manufacturer</th>
                            <th>Price</th>
                            <th>Description</th>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <th>Update</th>
                                <th>Delete</th>
                            </sec:authorize>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${productList}" var="product">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.manufacturer}</td>
                                <td>${product.price}</td>
                                <td>${product.description}</td>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <td><a href="${contextPath}/product/${product.id}/update"><button type="button" class="btn btn-sm btn-info">Update</button></a></td>
                                    <td><a href="${contextPath}/product/${product.id}/delete"><button type="button" class="btn btn-sm btn-danger">Delete</button></a></td>
                                </sec:authorize>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>

</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>