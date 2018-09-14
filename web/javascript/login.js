app.controller('loginController', function ($scope, $http, $window, $cookies) {
    var accountUrl = rest_url + "AccountService/";
    var registerUrl = "register.html";

    $scope.login_error = false;

    $scope.login = function () {
        var newAccount = new Object();
        newAccount.email = $scope.login_email.trim();
        newAccount.password = $scope.login_passwd.trim();

        $http.post(accountUrl + 'login/', newAccount).success(function (player) {
            //player logged in
            $cookies.put("player_id",player.id);
            $cookies.put("player_fname",player.firstName);
            $window.location.href = "season.html";

        }).error(function (data) {
            $scope.login_error = true;
            $scope.login_errorMessage = data.message;
        });
    };

    $scope.register = function () {
        window.location.href=registerUrl;
    };

});

