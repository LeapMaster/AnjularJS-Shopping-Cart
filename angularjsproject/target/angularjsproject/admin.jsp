<%--
  * Created by Shea Prewett on 6/19/17.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>

    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <meta content="utf-8" http-equiv="encoding">

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="js/admin.js"></script>

    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="styles/shoppingCart.css" />

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>

</head>
<body>
    <div ng-app="adminPage" ng-controller="adminController" class="appDiv">
        <h1>Admin Page</h1>

        <a href="${pageContext.request.getContextPath()}"><h4>&lt;&lt;&lt;Back Home</h4></a>

        <h3>Insert New Product</h3>

        <p><input type="text" ng-model="nameQuery" placeholder="Search By Product Name" class="searchBox" autofocus></p>

        <label for="directionAscending">
            <input type="radio" ng-model="direction" id="directionAscending" checked>
            Ascending
        </label>
        <label for="directionDescending">
            <input type="radio" ng-model="direction" id="directionDescending" value="reversed">
            Descending
        </label>

        <form>
            <table class="table">
                <tr>
                    <th ng-click="orderByMe('name')">Product Name</th>
                    <th ng-click="orderByMe('price')">Product Price</th>
                    <th></th>
                </tr>
                <tr ng-repeat="product in products | orderBy:attributeOrderBy:direction | filter:nameQuery">
                    <td>{{product.name}}</td>
                    <td>{{product.price | currency}}</td>
                    <td>
                        <button type="button" ng-click="deleteProduct(product.id)">Delete</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" ng-model="newName" ng-bind="newName" placeholder="Enter product name"/>
                    </td>
                    <td>
                        <input type="number" step="0.01" ng-model="newPrice" ng-bind="newPrice" placeholder="0.00"/>
                    </td>
                    <td>
                        <button type="button" ng-click="newProduct(newName, newPrice)">Submit</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
