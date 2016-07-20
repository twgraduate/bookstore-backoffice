var app = angular.module('myApp2', []);
var result;
app.controller('myCtrl', function ($scope, $http) {
    $http.get('http://localhost:8080/bookstore-backoffice/book').success(function (data){
        $scope.bkname = data.name;
        result = data;
    }).error(function () {
        alert("response err");
    });

    $scope.toggle = function () {
        $scope.bkisbn = result.isbn;
        $scope.bkprice = result.price;
        $scope.bkauthor = result.author;
        $scope.bkimg = result.imgUrl;
        $scope.bkdesc = result.description;


    }
});

