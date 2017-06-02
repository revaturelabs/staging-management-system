import angular from 'angular';
import uiRouter from 'angular-ui-router';
import angularBootstrap from 'angular-bootstrap-npm';

import { currentCtrl } from './manager/manager';

const routerApp = angular.module('routerApp', [uiRouter, angularBootstrap]);

routerApp.config(($stateProvider, $urlRouterProvider) => {
  $urlRouterProvider.otherwise('/login');

  $stateProvider // HOME STATES AND NESTED VIEWS
    .state('login', {
      url: '/login',
      templateUrl: 'login/login.html',
    })
    .state('manager', {
      url: '/manager',
      templateUrl: 'manager/manager.html',
    })
    .state('manager.current', {
      url: '/current',
      templateUrl: 'manager/current.html',
      controller: currentCtrl,
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

routerApp.controller('navController', ($scope) => {
  // $scope.openMenu = ($mdOpenMenu, ev) => {
  //   $mdOpenMenu(ev);
  // };
});
