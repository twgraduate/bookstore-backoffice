var app = angular.module('myApp', ['ngGrid']);
app.controller('MyCtrl', function($scope , $http) {
    $scope.list = [];
    $http.get('http://localhost:8080/bookstore-backoffice/book').success(function (data){
        for (var bkNum in data){
            $scope.list.push(data[bkNum].name);
        }
        $scope.myData = data;
    }).error(function () {
        alert("response err");
    });


    $scope.showList =false;
    $scope.data=[];

    $scope.handleShowList = function ($event) {
        var name = $event.target.innerText;
        for (var i in $scope.myData){
                $scope.data.pop();
            if(name == $scope.myData[i].name){
                $scope.bookMsg =$scope.myData[i];
                $scope.data.push($scope.myData[i]);
            }
        }
        $scope.showList = !$scope.showList;
    };
    
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