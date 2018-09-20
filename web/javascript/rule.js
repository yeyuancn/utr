app.controller("ruleController", function ($scope, $http, $cookies) {

    checkLogin($cookies, $http, init);

    function init(result) {
        $scope.loggedIn = result;
        if ($scope.loggedIn) {
            $scope.firstName = $cookies.get('first_name');
        }
    }

    $scope.logout = function () {
        cleanCookie($cookies);
        $scope.loggedIn = false;
    }
});