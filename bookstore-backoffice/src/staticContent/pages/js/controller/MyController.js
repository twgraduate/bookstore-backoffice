app.controller('MyCtrl', function ($scope, $http,$cookieStore,getalldata) {
    $scope.showList = true;
    $scope.editCondition = '';
    $scope.selectedRows = [];
    $scope.originRows = [];
    $scope.checkedRows = [];
    $scope.editable = false;
    $scope.currentRow = [];
    $scope.btnEdit = "edit";
    $scope.btnDelete = "delete";
    $scope.allData = '';
    $scope.firstVisitFlag = true;

    $scope.showAll = function () {
        $scope.showList = true;
    };


    $scope.openAddPage = function () {
         window.location.href = "../pages/addpage.html";
    };



    $scope.returnCurrentRow = function (row) {
        if ($scope.checkedRows.indexOf(row) >= 0) {
            var pos = $scope.checkedRows.indexOf(row);
            $scope.checkedRows.splice(pos, 1);
        }
        else {
            $scope.editable = true;
            $scope.checkedRows.push(row);
        }
        if ($scope.checkedRows.length != 1) {
            $scope.row = 11;
            $scope.btnEdit = "edit";
            $scope.btnDelete = "delete";
            $scope.editable = false;
            $scope.editCurrentRow();
        }
        else {
            $scope.editable = true;
            $scope.row = $scope.checkedRows[0];
        }
    };

    $scope.delCurrentRow = function () {
        if ($scope.btnDelete == "delete") {
            $scope.isbnArray = [];
            for (var k = 0; k < $scope.checkedRows.length; k++) {
                $scope.isbnArray.push($scope.myData[$scope.checkedRows[k]].isbn);
            }

            $scope.isbnJson = '{';
            for (var i in $scope.isbnArray) {
                $scope.isbnJson = $scope.isbnJson + "'isbn" + i + "':'" + $scope.isbnArray[i] + "',";
            }
            $scope.isbnJson = $scope.isbnJson.substr(0, $scope.isbnJson.length - 1);
            $scope.isbnJson += '}';
            //发送delete消息
            $http({
                method: 'DELETE',
                url: "http://localhost:8080/bookstore-backoffice/book/"+$scope.isbnArray[0],
                data: $scope.isbnJson,
            }).success(function (response) {
                for (var k = 0;k< $scope.checkedRows.length;k++){
                    $scope.myData.splice($scope.checkedRows[k],1);
                    $scope.check.checked = false;
                }
            })
        }
        else {
            angular.copy($scope.originRows[0], $scope.myData[$scope.num]),
                $scope.btnEdit = "edit",
                $scope.btnDelete = "delete"
        }
    };

    $scope.editCurrentRow = function () {
        if ($scope.btnEdit == "edit" && $scope.row < 10) {
            $scope.num = $scope.row;
            $scope.btnEdit = "save";
            $scope.btnDelete = "cancel";
            $scope.gridOptions.selectRow($scope.num, true);
            angular.copy($scope.selectedRows, $scope.originRows);
        }
        else {
            //发送put消息
            $http({
                method: 'PUT',
                url: 'http://localhost:8080/bookstore-backoffice/book/' + $scope.selectedRows[0].isbn,
                data: {
                    'price': $scope.myData[$scope.num].price,
                    'img_url': $scope.myData[$scope.num].img_url,
                    'description': $scope.myData[$scope.num].description
                }
            }).success(function (response) {
                $scope.putReturn = response.data;
            });

            $scope.btnEdit = "edit";
            $scope.btnDelete = "delete";
        }

    };

    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: "true"
    };
    $scope.totalServerItems = 0;
    $scope.pagingOptions = {
        pageSizes: [10,20,30],
        pageSize: 10,
        currentPage: 1
    };
    $scope.setPagingData = function (data, page, pageSize) {
        var pageData = data.slice((page - 1) * pageSize, page * pageSize);
        $scope.myData = pageData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };

    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
        setTimeout(function () {
            var data;
            if (searchText) {
                var ft = searchText.toLowerCase();
                $http.get('http://localhost:8080/bookstore-backoffice/book').success(function (largeLoad) {
                    data = largeLoad.filter(function (item) {
                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                    });
                    $scope.setPagingData(data, page, pageSize);
                });
            } else {
                if($scope.firstVisitFlag){
                     getalldata.async().then(function (d) {
                        $scope.allData = d;
                        $scope.setPagingData(d, page, pageSize);
                     });
                }
                else{
                    $scope.setPagingData($scope.allData , page, pageSize);
                }
            }
        }, 100);
    };

    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
            $scope.firstVisitFlag = false;
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);




    $scope.gridOptions = {
        data: 'myData',
        width: '100%',
        sortable: true,
        resizeable: true,
        columnDefs: [{
            field: 'name',
            displayName: 'Name',
            cellClass: 'grid-align',
        }, {
            field: 'isbn',
            displayName: 'ISBN',
            cellClass: 'grid-align'
        }, {
            field: 'author',
            displayName: 'Author',
            cellClass: 'grid-align'
        }, {
            field: 'price',
            displayName: 'Price',
            cellClass: 'grid-align'
        }, {
            field: 'img_url',
            displayName: 'ImgUrl',
            cellClass: 'grid-align'
        }, {
            field: 'description',
            displayName: 'Description',
            cellClass: 'grid-align'
        }, {
            field: 'options1',
            displayName: 'Check',
            cellClass: 'grid-align',
            cellTemplate: '<input type="checkbox" ng-model="check" ng-click="returnCurrentRow(row.rowIndex)">',
            enableCellEdit: false
        }],
        multiSelect: false,
        enableCellSelection: false,
        enablePaging : true,
        showFooter: true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions,
        enableRowSelection: true,
        enableCellEditOnFocus: true,
        selectedItems: $scope.selectedRows,
        cellEditableCondition: 'row.rowIndex == ' + 'num',
        enablePaging: true,
        showFooter: true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions
    };


});