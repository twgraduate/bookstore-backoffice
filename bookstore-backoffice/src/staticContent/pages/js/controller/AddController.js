var addpage = angular.module('addPage',['ngCookies']);
addpage.controller('AddForm',function($scope, $http,$cookieStore,$location){
    $scope.name = "";
    $scope.isbn = "";
    $scope.author = "";
    $scope.price = "";
    $scope.imgurl = "";
    $scope.description = "";


    $scope.addbook = function(){
        $scope.result = {
          "name": $scope.name,
          "isbn": $scope.isbn,
          "author": $scope.author,
          "price": $scope.price,
          "img_url": $scope.imgurl,
          "description": $scope.description
        };

        $http({
            method: 'POST',
            url: 'http://localhost:8080/bookstore-backoffice/book',
            data: $scope.result
        }).success(function (response) {
            if(response.msg == "Create a new book"){
                alert(response.msg);
                window.location.href = "../pages/index.html";
            }else{
                alert(response.msg);
            }
        });
    }

    $scope.cancel = function(){
       window.location.href = ("../pages/index.html");
    }
});

