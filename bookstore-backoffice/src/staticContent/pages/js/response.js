var app = angular.module('myApp', []);
app.controller('dataDisplay', function ($scope, $http) {
    $http.get('http://localhost:8080/tony').success(function (data){
        $scope.name = data.data;
    }).error(function () {
        alert("response err");
    })
});


