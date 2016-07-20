var app = angular.module('myApp', []);
app.controller('dataDisplay', function ($scope, $http) {
    $http.get('http://localhost:8080/greeting').success(function (data){
        $scope.name = data.content;
    }).error(function () {
        alert("response err");
    })
});


