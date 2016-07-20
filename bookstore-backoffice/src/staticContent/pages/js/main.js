var app = angular.module('myApp', ['ngGrid']);
app.controller('MyCtrl', function($scope , $http) {
    $http.get('http://localhost:8080/bookstore-backoffice/book').success(function (data){
        $scope.bookName = data[0].name;
        $scope.myData = data;
    }).error(function () {
        alert("response err");
    });

    $scope.toggle = function () {

    }

    $scope.gridOptions = {
        data : 'myData',
        width : '100%',
    }
});