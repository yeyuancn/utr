app.controller("rankController", function ($scope, $http, $cookies) {
    var matchUrl = rest_url + "MatchResultService/";
    var leagueUrl = rest_url + "LeagueService/";
    var playerUrl = rest_url + "PlayerService/";

    init();

    function init() {
        if ($cookies.get('player_id')) {
            $scope.playerId = parseInt($cookies.get('player_id'));

            // get updated data for the player
            $http.get(playerUrl + 'getPlayer/' + $scope.playerId).success(function (data) {
                $scope.divisionId = data.divisionId;

                var player = new Object();
                player.divisionId = $scope.divisionId;

                $http.post(leagueUrl + 'allDivisions/', player).success(function (data) {
                    $scope.divisions = data;
                });

                reloadPlayerResult();

            }).error(function (data) {
                console.log("Error getting player info: " + data.message);
            });

        }
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