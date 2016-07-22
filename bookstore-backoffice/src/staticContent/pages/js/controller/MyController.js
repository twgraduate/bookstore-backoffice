app.controller('MyCtrl', function($scope ,$http,getalldata) {
    $scope.showList =false;
    $scope.showAll = function () {
        $scope.showList = true;
    };

    getalldata.async().then(function (d) {
        $scope.myData = d;
    });
    
    $scope.gridOptions = {
        data :'myData',
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