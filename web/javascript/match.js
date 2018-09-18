app.controller("matchController", function ($scope, $http, $cookies) {
    var playerUrl = rest_url + "PlayerService/";
    var matchUrl = rest_url + "MatchResultService/";
    var leagueUrl = rest_url + "LeagueService/";

    init();

    $scope.submit = function () {
        $scope.sendError = false;
        $scope.sendSuccess = false;

        var result = new Object();
        result.divisionId = $scope.divisionId;
        result.winnerId = $scope.playerId;
        result.loserId = $scope.opponent.id;
        result.set1Score = $scope.score_you1 + ":" + $scope.score_opp1;
        result.set2Score = $scope.score_you2 + ":" + $scope.score_opp2;
        result.set3Score = $scope.score_you3 + ":" + $scope.score_opp3;
        result.matchMemo = $scope.matchMemo;
        result.enterByWinner = true;

        var values = $scope.matchDate.split('/');
        result.matchDate = new Date(parseInt(values[2]), parseInt(values[0]) - 1, parseInt(values[1]), 0, 0, 0, 0);

        $http.post(matchUrl + 'addMatchResult/', result).success(function (data) {
            $scope.sendSuccess = true;
            reloadMatchResult();
        }).error(function (data) {
            $scope.sendError = true;
            $scope.errorMessage = data.message;
        });
    };

    $scope.deleteResult = function (id) {
        $http.delete(matchUrl + 'deleteMatchResult/' + id).success(function () {
            reloadMatchResult();
        });
    };

    $scope.canDelete = function (winnerId, loserId) {
        if ($scope.playerId == winnerId || $scope.playerId == loserId) {
            return true;
        }
        return false;
    };

    function init() {
        if ($cookies.get('player_id')) {

            $scope.playerId = parseInt($cookies.get('player_id'));

            // get updated data for the player
            $http.get(playerUrl + 'getPlayer/' + $scope.playerId).success(function (data) {
                $scope.fName = data.firstName;
                $scope.lName = data.lastName;
                $scope.divisionId = data.divisionId;
                $scope.resultDivisionId = $scope.divisionId;
                $scope.oppRequired = true;
                $scope.scoreRange = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"];

                var player = new Object();
                player.id = $scope.playerId;
                player.divisionId = $scope.divisionId;

                $http.post(playerUrl + 'allOtherPlayers/', player).success(function (data) {
                    $scope.players = data;
                });

                $http.get(leagueUrl + 'getCurrentDivisions/').success(function (data) {
                    $scope.divisions = data;
                });

                reloadMatchResult();

            }).error(function (data) {
                console.log("Error getting player info: " + data.message);
            });
        }
    }

    $scope.$watch('opponent', function () {
        if ($scope.opponent) {
            $scope.oppRequired = false;
        }
        else {
            $scope.oppRequired = true;
        }
    });

    $scope.$watch('matchDate', function () {
        $scope.dateRequired = false;
        $scope.dateInvalid = false;
        if ($scope.matchDate) {
            if (!isValidDate($scope.matchDate)) {
                $scope.dateInvalid = true;
            }
        }
        else {
            $scope.dateRequired = true;
        }
    });

    // Validates that the input string is a valid date formatted as "mm/dd/yyyy"
    function isValidDate(dateString) {
        // First check for the pattern
        if (!/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(dateString))
            return false;

        // Parse the date parts to integers
        var parts = dateString.split("/");
        var day = parseInt(parts[1], 10);
        var month = parseInt(parts[0], 10);
        var year = parseInt(parts[2], 10);

        // Check the ranges of month and year
        if (year < 1000 || year > 3000 || month == 0 || month > 12)
            return false;

        var monthLength = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

        // Adjust for leap years
        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
            monthLength[1] = 29;

        // Check the range of the day
        return day > 0 && day <= monthLength[month - 1];
    };

    $scope.reload = function () {
        reloadMatchResult();
    }

    function reloadMatchResult() {
        $http.get(matchUrl + 'matchResults/' + $scope.resultDivisionId).success(function (data) {
            $scope.matchResults = data;
        });

        $scope.opponent = null;
        $scope.score_you1 = "";
        $scope.score_you2 = "";
        $scope.score_you3 = "";
        $scope.score_opp1 = "";
        $scope.score_opp2 = "";
        $scope.score_opp3 = "";
        $scope.matchMemo = "";
        $scope.showMemo = false;

        var d = new Date();
        $scope.matchDate = (d.getMonth() + 1) + "/" + d.getDate() + "/" + d.getFullYear();
    }

    $scope.popup = function (playerId, fName, lName) {
        window.open("./match_hist.html?player_id=" + playerId + "&player_fname=" + fName + "&player_lname=" + lName,
            "Match Result for " + fName + " " + lName + ":", 'width=960,height=700,top=100,left=100,scrollbars=yes');
    }

});