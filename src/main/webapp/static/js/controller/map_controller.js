'use strict';

App.controller('MapController',['$scope','$http',function ($scope, $http) {
    
    var global = this;
    
    global.RouteEntity = {id_route:null, origin:'', destination:''};

    global.maggase = {massage:''};

    global.sendToServer = function () {
        alert("did something");
        $http.post('http://localhost:8080/rest/','lat=2&lon=3');
        global.maggase = {massage:''};
    };
}]);