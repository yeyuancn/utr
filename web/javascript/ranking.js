app.controller("rankController", function ($scope, $http, $cookies) {
    var matchUrl = rest_url + "MatchResultService/";
    var leagueUrl = rest_url + "LeagueService/";

    checkLogin($cookies, $http, init);

    function init(result) {
        $scope.loggedIn = result;
        if ($scope.loggedIn) {
            $scope.firstName = $cookies.get('first_name');
        }

        $http.get(leagueUrl + 'getCurrentDivisions/').success(function (data) {
                $scope.divisions = data;
        });
        $http.get(leagueUrl + 'getCurrentCatchAllDivision/').success(function (data) {
            $scope.divisionId = data.id;
            reloadPlayerResult();
        });
    }

    $scope.logout = function () {
        cleanCookie($cookies);
        $scope.loggedIn = false;
    }

    $scope.reload = function () {
        reloadPlayerResult();
    }

    function reloadPlayerResult() {
        $http.get(matchUrl + 'playerResults/' + $scope.divisionId).success(function (data) {
            $scope.playerResults = data;
        });
    }

    $scope.popup = function (playerId, fName, lName) {
        window.open("./match_hist.html?player_id=" + playerId + "&player_fname=" + fName
            + "&player_lname=" + lName, "Match Result for " + fName + " " + lName + ":",
            'width=960,height=700,top=100,left=100,scrollbars=yes');
    }
});