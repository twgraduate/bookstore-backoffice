app.service('updatebook',function($http,$scope) {
    $http({
        method: 'POST',
        url: 'http://localhost:8080/bookstore-backoffice/book/'+$scope.selectedRows[0].isbn,
        params: {
            'price': '$scope.selectedRows[0].price',
            'img_url': '$scope.selectedRows[0].img_url',
            'description': '$scope.selectedRows[0].description'
        }
    }).success(function(response) {$scope.putReturn = response.data;})
})
