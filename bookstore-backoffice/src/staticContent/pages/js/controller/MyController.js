app.controller('MyCtrl', function($scope ,$http,getalldata) {
    $scope.showList =false;

    $scope.editCondition = '';

    $scope.selectedRows=[];
    

    $scope.showAll = function () {
        $scope.showList = true;
    };




    getalldata.async().then(function (d) {
        $scope.myData = d;
    });

    $scope.editCurrentRow = function () {
        console.log($scope.selectedRows[0].isbn);
        angular.forEach($scope.myData, function(data, index){
            if(data.isbn == $scope.selectedRows[0].isbn){
                $scope.num = index;
            }
        });
    };

    $scope.filterOptions = {
        filterText : "",
        useExternalFilter : "true"
    };
    $scope.totalServerItems = 0;
    $scope.pagingOptions = {
        pageSizes : [10,15,20],
        pageSize : 10,
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
            cellClass: 'grid-align',
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
            field: 'img_url',
            displayName: 'ImgUrl',
            cellClass: 'grid-align'
        },{
            field: 'options',
            displayName: 'Options',
            cellClass: 'grid-align',
            cellTemplate : '<button  ng-click="editCurrentRow()">edit</button><span></span><button>del</button>',
            enableCellEdit:false
        }],
        multiSelect : false,
        enableCellSelection: false,
        enableRowSelection: true,
        enableCellEditOnFocus: true,
        selectedItems: $scope.selectedRows,
        cellEditableCondition : 'row.rowIndex == '+'num',
        enablePaging: true,
        showFooter: true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions
    };

});