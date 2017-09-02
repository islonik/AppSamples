var app = angular.module("app", []);

app.controller("indexCtrl1", function ($scope) {
    $scope.welcome1 = "Hello, AngularJS";
    $scope.welcome2 = "I mean really?";
});

app.controller('indexCtrl3', function ($scope) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
});

app.controller('indexCtrl4', function ($scope, $http) {
    $scope.fullResp = "";
    $scope.ajax = function () {
        $http({
            url: '/ajax/angularjs/ajax/prefix/' + $scope.prefix,
            method: "POST",
            data: ""
        }).then(
            function (response) {
                // success
                $scope.fullResp = response.data;
                $scope.idResp = response.data.id;
            },
            function (error) { // optional
                // failed
                $scope.fullResp = "error " + error;
            }
        );
    }
});