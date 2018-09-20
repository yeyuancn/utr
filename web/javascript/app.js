var app = angular.module('utr', ['ngCookies']);
var rest_url = "rest/";

function getParamValue(param) {
    var query = window.location.search.substring(1);
    var vars = query.split('&');
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split('=');
        if (decodeURIComponent(pair[0]) == param) {
            return decodeURIComponent(pair[1]);
        }
    }
    return '';
}

function checkLogin(cookies, http, callback) {
    var accountUrl = rest_url + "AccountService/";
    if (cookies.get('utr_session_uuid')) {
        //validate the cookie before logged in
        var session = new Object();
        session.uuid = cookies.get('utr_session_uuid');

        http.post(accountUrl + 'validateUUID/', session).success(function(player){
            cookies.put("player_id", player.id);
            cookies.put("first_name", player.firstName);

            callback(true);
        }).error(function (data) {
            cleanCookie(cookies);
            callback(false);
        });
    } else {
        callback(false);
    }
}

function cleanCookie(cookies) {
    var allCookies = cookies.getAll();
    angular.forEach(allCookies, function (v, k) {
        cookies.remove(k);
    });
}


