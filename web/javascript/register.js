app.controller('registerController', function($scope, $http, $window, $cookies) {
	var accountUrl = rest_url + "AccountService/";
	init();

	function init() {
		$scope.fName = '';
		$scope.lName = '';
		$scope.email = '';
		$scope.phone = '';
		$scope.passwd1 = '';
		$scope.passwd2 = '';

		$scope.error_passwd_length = false;
        $scope.error_email_length = false;
        $scope.error_phone_length = false;

        $scope.error_email = false;
        $scope.error_phone = false;
        $scope.error_passwd_match = false;
		$scope.incomplete = true;

		$scope.failedReg = false;
		$scope.errorMessage = '';

        $scope.$watch('passwd1',function() {$scope.test();});
        $scope.$watch('passwd2',function() {$scope.test();});
        $scope.$watch('email',function() {$scope.test();});
        $scope.$watch('phone',function() {$scope.test();});
    }

	$scope.test = function() {
        $scope.error_passwd_length = false;
		$scope.error_email_length = false;
        $scope.error_phone_length = false;

        $scope.error_email = false;
        $scope.error_phone = false;
        $scope.error_passwd_match = false;
        $scope.incomplete = false;

		if ($scope.passwd1.length != 0 && $scope.passwd2.length != 0 && $scope.passwd1 != $scope.passwd2) {
			$scope.error_passwd_match = true;
		}
		if ($scope.passwd1.length > 0 && $scope.passwd1.length < 6) {
			$scope.error_passwd_length = true;
		}
		if ($scope.email.length > 0 && !validateEmail($scope.email))
		{
			$scope.error_email = true;
		}

		if ($scope.fName.trim().length == 0 ||
			$scope.lName.trim().length == 0 ||
			$scope.passwd1.trim().length == 0 ||
			$scope.passwd2.trim().length == 0 ||
            $scope.phone.trim().length == 0 ||
			$scope.email.trim().length == 0)
		{
			$scope.incomplete = true;
		}

		if ($scope.email.length > 38) {
			$scope.error_email_length = true;
		}
        if ($scope.phone.length > 0 && ($scope.phone.length > 15 || $scope.phone.length < 10)) {
            $scope.error_phone_length = true;
        }
        if ($scope.phone.length > 0 && !validatePhone($scope.phone))
        {
            $scope.error_phone = true;
        }

    };

	function validateEmail(email) {
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		return re.test(email);
	}

	function validatePhone(phone) {
        re = /^[0-9]+$/;
		return re.test(phone);
	}

	$scope.createAccount = function() {
		if($scope.bot)
		{
			return;
		}

        var newAccount = new Object();
        newAccount.email = $scope.email.trim();
        newAccount.password = $scope.passwd1.trim();
        newAccount.phone = $scope.phone.trim();
        newAccount.firstName = $scope.fName.trim();
        newAccount.lastName = $scope.lName.trim();

        $http.post(accountUrl + 'createAccount/', newAccount).success(function(player){

			//player logged in after successful registration
			$cookies.put("player_id",player.id);
            $cookies.put("player_fname",player.firstName);
            $window.location.href = "match.html";
        }).error(function (data) {
            $scope.failedReg = true;
            $scope.errorMessage = data.message;
        });

	};
});