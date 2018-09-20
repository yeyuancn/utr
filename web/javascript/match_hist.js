app.controller("matchHistController", function($scope, $http, $cookies) {
	var matchUrl = rest_url + "MatchResultService/";
    checkLogin($cookies, $http, init);

    function init(result) {
        $scope.loggedIn = result;
        if ($scope.loggedIn) {
            loadResult(getParamValue("player_id"),  getParamValue("player_fname"),  getParamValue("player_lname") );
        }
	}

	$scope.loadResult = function(playerId, fname, lname) {
		loadResult(playerId, fname, lname);
	};

	function loadResult(playerId, fname, lname) {
		$scope.playerFName = fname;
		$scope.playerLName = lname;

		$http.get(matchUrl + 'matchResultsForPlayer/' + playerId).success(function(data) {
			$scope.matchResults = data;
		});
	}
});