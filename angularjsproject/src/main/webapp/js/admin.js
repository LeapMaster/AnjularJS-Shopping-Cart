/**
 * Created by Shea Prewett on 6/19/17.
 */
var app = angular.module('adminPage', []);

app.controller('adminController', function($scope, $http) {

    // Load the products in JSON form from the API
    $http.get('http://52.14.153.185:8080/angularjsproject/all')
        .then(function(response) {
            $scope.products = response.data;
        });

    // Insert new product from form fields
    $scope.newProduct = function(name, price) {
        // Insert product through API
        if (name.length > 0 && price > 0) {
            $http({
                method: "POST",
                url: "http://52.14.153.185:8080/angularjsproject/insert",
                data: JSON.stringify({name: name, price: price}),
                headers: {"Content-Type": "application/json"}
            }).then(function (response) {
                // If successful, insert product into local data
                var responseData = response.data;
                if (responseData.substring(0, 1) === "#") {
                    var newID = parseInt(responseData.substring(1, responseData.size));
                    console.log(newID);
                    $scope.products.push({"name": name, "price": price, id: newID});
                    for (var index = 0; index < $scope.products.length; index++) {
                        console.log($scope.products[index].id);
                    }
                    $scope.newName = "";
                    $scope.newPrice = "";
                    $scope.focusNew = true;
                }
            });
        }
    }

    // Delete product selected in table
    $scope.deleteProduct = function(id){
        // Delete product through API
        $http({
            method: "POST",
            url: "http://52.14.153.185:8080/angularjsproject/delete",
            data: JSON.stringify({id:id}),
            headers: {"Content-Type":"application/json"}
        }).then(function(response) {
            // Delete product in local data
            $scope.products = $scope.products.filter(function(item) {
                return (item.id !== id);
            });

        });
    }

    // Custom orderBy function, to potentially be expanded for alternating order
    $scope.orderByMe = function(filter) {
        $scope.attributeOrderBy = filter;
    }


});