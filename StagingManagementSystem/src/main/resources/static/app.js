import {currentCtrl, forecastCtrl} from "./manager/manager";

console.log(currentCtrl);
var routerApp = angular.module('routerApp', ['ui.router']);

routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/login');

    $stateProvider

        // HOME STATES AND NESTED VIEWS ========================================
        .state('login', {
            url: '/login',
            templateUrl: 'login/login.html'
        })

        .state('manager', {
            url: '/manager',
            templateUrl: 'manager/manager.html'
        })

        .state('manager.current', {
            url: '/current',
            templateUrl: 'manager/current.html',
            controller: currentCtrl
        });



        // nested list with custom controller
        // .state('home.list', {
        //     url: '/list',
        //     templateUrl: 'partial-home-list.html',
        //     controller: function($scope) {
        //         $scope.dogs = ['Bernese', 'Husky', 'Goldendoodle'];
        //     }
        // })

        // nested list with just some random string data
        // .state('home.paragraph', {
        //     url: '/paragraph',
        //     template: 'I could sure use a drink right now.'
        // })

        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        // .state('about', {
        //     url: '/about',
        //     views: {
        //         '': { templateUrl: 'partial-about.html' },
        //         'columnOne@about': { template: 'Look I am a column!' },
        //         'columnTwo@about': {
        //             templateUrl: 'table-data.html',
        //             controller: 'scotchController'
        //         }
        //     }
        //
        // });

});

routerApp.controller('navController', function($scope) {

    $scope.openMenu = function($mdOpenMenu, ev) {
        console.log("here")
      originatorEv = ev;
      $mdOpenMenu(ev);
    };

});


routerApp.controller('scotchController', function($scope) {

    $scope.scotches = [
        {
            name: 'Macallan 12',
            price: 50
        },
        {
            name: 'Chivas Regal Royal Salute',
            price: 10000
        },
        {
            name: 'Glenfiddich 1937',
            price: 20000
        }
    ];

    console.log("scotches loaded");

});
