var app = angular.module('myApp', ['ngGrid']);
app.controller('MyCtrl', function($scope , $http) {
    $scope.filterOptions = {
        filterText : "",
        useExternalFilter : "true"
    };
    $scope.totalServerItems = 0;
    $scope.pagingOptions = {
        pageSizes : [5,10,15],
        pageSize : 5,
        currentPage : 1
    };
    $scope.setPagingData = function (data , page , pageSize) {
        var pageData = data.slice((page-1)*pageSize,page*pageSize);
        $scope.myData = pageData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };

    $scope.getPagedDataAsync = function (pageSize , page , searchText) {
        setTimeout(function () {
            var data;
            if(searchText){
                var ft = searchText.toLowerCase();
                $http.get('http://localhost:8080/bookstore-backoffice/book').success(function (largeLoad) {
                    data = largeLoad.filter(function (item) {
                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                    });
                    $scope.setPagingData(data , page , pageSize);
                });
            }else {
                $http.get('http://localhost:8080/bookstore-backoffice/book').success(function (largeLoad) {
                    $scope.setPagingData(largeLoad , page , pageSize);
                });
            }
        },100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize,$scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions',function (newVal,oldVal) {
        if(newVal !== oldVal && newVal.currentPage !== oldVal.currentPage){
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    },true);

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
        }],
        enablePaging: true,
        showFooter: true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions
    };

});