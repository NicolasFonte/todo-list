angular.module('hello', ['ngRoute']).config(function ($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl: 'home.html',
        controller: 'home',
        controllerAs: 'controller'
    }).when('/login', {
        templateUrl: 'login.html',
        controller: 'navigation',
        controllerAs: 'controller'
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

}).controller('navigation',

    function ($rootScope, $http, $location, $route) {

        var self = this;

        self.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };

        var authenticate = function (credentials, callback) {

            var headers = credentials ? {
                authorization: "Basic "
                + btoa(credentials.username + ":"
                    + credentials.password)
            } : {};

            $http.get('user', {
                headers: headers
            }).then(function (response) {
                if (response.data.name) {
                    $rootScope.authenticated = true;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback($rootScope.authenticated);
            }, function () {
                $rootScope.authenticated = false;
                callback && callback(false);
            });

        }

        authenticate();

        self.credentials = {};
        self.login = function () {
            authenticate(self.credentials, function (authenticated) {
                if (authenticated) {
                    console.log("Login succeeded")
                    $location.path("/");
                    self.error = false;
                    $rootScope.authenticated = true;
                } else {
                    console.log("Login failed")
                    $location.path("/login");
                    self.error = true;
                    $rootScope.authenticated = false;
                }
            })
        };

        self.logout = function () {
            $http.post('logout', {}).finally(function () {
                $rootScope.authenticated = false;
                $location.path("/");
            });
        };

    }).controller('home', function ($http) {
    var self = this;
    self.repeat = false;

    // Retrieving tasks
    self.tasks = [];
    var getTasks = function () {
        $http.get('/api/tasks').then(function (response) {
            self.tasks = response.data;
        })
    };
    getTasks();

    // Adding new Tasks
    self.newTodo = {complete: false};
    self.addTodo = function () {
        $http.post('api/tasks/create', self.newTodo).finally(function () {
            console.log("task added");
        });
    };

    self.markCompleted = function (task) {
        $http.post('api/tasks/complete', self.newTodo).finally(function () {
            console.log("task completed");
        });
    };

    self.deleteTask = function (task) {
        $http.post('api/tasks/delete', self.newTodo).finally(function () {
            console.log("task deleted");
        });
    };


});
