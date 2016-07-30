'use strict';

App.controller('MapController',['$scope','$http',function ($scope, $http) {

    var global = this;

    var url = 'http://localhost:8080/rest/';

    global.RouteEntity = {id_route:null, origin:'', destination:''};

    global.maggase = {massage:''};

    global.sendToServer = function () {
        $http.post(url, global.maggase);

        global.maggase = {massage:''};
    }
}]);