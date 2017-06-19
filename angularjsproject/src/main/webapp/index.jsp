<%--
  * Created by Shea Prewett on 6/15/17.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <meta content="utf-8" http-equiv="encoding">

    <title>Shopping Cart</title>

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="js/shoppingCart.js"></script>

    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="styles/shoppingCart.css" />

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>

</head>

    <body>

        <div ng-app="shoppingCart" ng-controller="cartController" class="appDiv">

            <h1>Angular Shopping Cart Demo</h1>

            <p><input type="text" ng-model="nameQuery" placeholder="Search By Product Name" class="searchBox"></p>

            <p>Click the table headers to change the sorting order:</p>

            <label for="directionAscending">
                <input type="radio" ng-model="direction" id="directionAscending" checked>
                Ascending
            </label>
            <label for="directionDescending">
                <input type="radio" ng-model="direction" id="directionDescending" value="reversed">
                Descending
            </label>

            <button type="button" ng-click="orderByMe('')">Reset Order</button>

            <table border="1" width="100%" class="table">

                <tr>
                    <th ng-click="orderByMe('name')">Name</th>
                    <th ng-click="orderByMe('price')">Price</th>
                    <th>Order</th>
                </tr>

                <tr ng-repeat="product in products | orderBy:attributeOrderBy:direction | filter:nameQuery">
                    <td>{{product.name}}</td>
                    <td>{{product.price | currency}}</td>
                    <td>
                        <!-- WIP - Need to bind this to be accessible by addNewProduct function in some way -->
                        <!-- <input value="1" ng-model="quantity[product.id]" type="number" class="quantity"/> -->
                        <button type="button" ng-click="addNewProduct(product.name,1,product.price)">Add to cart</button>
                    </td>
                </tr>

            </table>

            <table border="1" width="100%" class="table" ng-if="cart.products.length > 0">

                <tr>
                    <th colspan="4">Shopping Cart</th>
                </tr>

                <tr>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th></th>
                </tr>

                <tr ng-repeat="product in cart.products">
                    <td class="col1">{{product.name}}</td>
                    <td class="col2">{{product.quantity}}</td>
                    <td class="col3">{{product.price | currency }}</td>
                    <td class="col4"><button type="button" ng-click="removeProduct($index)">Remove</button></td>
                </tr>
            </table>
            <a href="${pageContext.request.contextPath}/admin"><h4>Admin Page (Edit Products)</h4></a>

        </div>
    </body>
</html>
