var app = angular.module('myApp', ['ngGrid']);
app.controller('MyCtrl', function($scope , $http) {
    $http.get('http://localhost:8080/bookstore-backoffice/book').success(function (data){
        $scope.myData = data;
    }).error(function () {
        alert("response err");
    });


    $scope.showList =false;

    
    $scope.showAll = function () {
        $scope.showList = true;
    }

    $scope.gridOptions = {
        data : 'myData',
        width : '100%',
        sortable : true,
        resizeable :true,
        columnDefs : [{
            field: 'name',
            displayName: 'Name',
            cellClass: 'grid-align'
        },{
            field: 'isbn',
            displayName: 'ISBN',
            cellClass: 'grid-align'
        },{
            field: 'author',
            displayName: 'Author',
            cellClass: 'grid-align'
        },{
            field: 'price',
            displayName: 'Price',
            cellClass: 'grid-align'
        },{
            field: 'imgUrl',
            displayName: 'ImgUrl',
            cellClass: 'grid-align'
        }]
    }

});