/**
 * Created by Shea Prewett on 6/15/17.
 */
var app = angular.module('shoppingCart', []);

app.controller('cartController', function($scope, $http) {

    // Instantiate empty list of products for shopping cart
    $scope.cart = {products:[]};

    // Add a product to the shopping cart
    $scope.addNewProduct = function(name, quantity, price) {
        $scope.cart.products.push(
            {name:name,quantity:quantity,price:price}
        );
    }


    $http.get('http://52.14.153.185:8080/angularjsproject/all')
    // $http.get('http://localhost:8080/all')
        .then(function(response) {
            $scope.products = response.data;
            console.log($scope.products);
        });


    // Custom orderBy function, to potentially be expanded for alternating order
    $scope.orderByMe = function(filter) {
        $scope.attributeOrderBy = filter;
    }

    // Takes index from list and removes object at that index
    $scope.removeProduct = function(index) {
        $scope.cart.products.splice(index, 1);
    }
});
