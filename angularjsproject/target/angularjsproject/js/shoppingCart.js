/**
 * Created by Shea Prewett on 6/15/17.
 */
var app = angular.module('shoppingCart', []);

app.controller('cartController', function($scope, $http) {

    // Instantiate empty list of products for shopping cart
    $scope.cart = {products:[]};

    // Add a product to the shopping cart
    $scope.addNewProduct = function(name, quantity, price) {
        // debug
        // console.log("adding");
        $scope.cart.products.push(
            {name:name,quantity:quantity,price:price}
        );
    }

    // Hard-coded JSON of products
    // TODO: Load from database API
    $scope.products = [
        {"id":1,"name": "Cooler","price": 19.99},
        {"id":2,"name": "Umbrella","price": 9.99},
        {"id":3,"name": "Propane Grill","price": 49.99},
        {"id":4,"name": "Grill Mat","price": 11.99},
        {"id":5,"name": "Patio Table","price": 84.99},
        {"id":6,"name": "Paper Plates (170 ct.)","price": 7.99},
        {"id":7,"name": "Plastic Utensils (100 ct.)","price": 14.99},
        {"id":8,"name": "Paper Napkins (500 ct.)","price": 4.99}
    ];

    // Custom orderBy function, to potentially be expanded for alternating order
    $scope.orderByMe = function(filter) {
        $scope.attributeOrderBy = filter;
    }

    // Takes index from list and removes object at that index
    $scope.removeProduct = function(index) {
        $scope.cart.products.splice(index, 1);
    }
});
