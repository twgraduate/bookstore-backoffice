app.service('getalldata',function($http) {
    var getalldata = {
        async : function(){
            var promise = $http.get('http://localhost:8080/bookstore-backoffice/book').then(function (response) {
                console.log(response);
                return response.data;
            });
            return promise;
        }
    };
    return getalldata;
});