app.controller('loginController', function ($scope, $http, $window, $cookies) {
    var accountUrl = rest_url + "AccountService/";

    checkLogin($cookies, $http, init);

    function init(result) {
        $scope.login_error = false;

        $scope.loggedIn = result;
        if ($scope.loggedIn) {
            $scope.firstName = $cookies.get('first_name');
        }
    }

    $scope.logout = function () {
        cleanCookie($cookies);
        $scope.loggedIn = false;
    }

    $scope.login = function () {
        var newAccount = new Object();
        newAccount.email = $scope.login_email.trim();
        newAccount.password = $scope.login_passwd.trim();

        $http.post(accountUrl + 'login/', newAccount).success(function (session) {
            //player logged in
            console.info("session id " + session.uuid);
            $cookies.put("utr_session_uuid", session.uuid);
            $window.location.href = "match.html";

        }).error(function (data) {
            $scope.login_error = true;
            $scope.login_errorMessage = data.message;
        });
    };

    $scope.register = function () {
        window.location.href="register.html";
    };

});

