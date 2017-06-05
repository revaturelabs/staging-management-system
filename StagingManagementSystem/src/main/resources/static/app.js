import angular from 'angular';
import uiRouter from 'angular-ui-router';
import angularBootstrap from 'angular-bootstrap-npm';

var FusionCharts = require("fusioncharts");
require("fusioncharts/fusioncharts.charts")(FusionCharts);

console.log();

import { currentCtrl } from './manager/manager';
import { batchCtrl } from './manager/create/batch';
import { clientCtrl } from './manager/create/client';
import { userCtrl } from './manager/create/user';
import { profileCtrl } from './associate/profile/profile';
import { reportCtrl } from './reports/reports';
import { nestedCtrl } from './reports/nestedGraph';

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
    .state('manager.create', {
      url: '/create',
      templateUrl: 'manager/create/create.html',
    })
    .state('manager.create.batch', {
      url: '/batch',
      templateUrl: 'manager/create/batch.html',
      controller: batchCtrl,
    })
    .state('manager.create.user', {
      url: '/user',
      templateUrl: 'manager/create/user.html',
      controller: userCtrl,

    })
    .state('manager.create.client', {
      url: '/client',
      templateUrl: 'manager/create/client.html',
      controller: clientCtrl,
    })
    .state('associate', {
      url: '/associate',
      templateUrl: 'associate/associate.html',
    })
    .state('associate.profile', {
      url: '/profile',
      templateUrl: 'associate/profile/profile.html',
      controller: profileCtrl,
    })
    .state('reports', {
    	url: '/reports',
    	templateUrl: 'reports/reports.html',
    	controller: reportCtrl,
    })
    .state('reports.nestedGraph', {
      url: '/nestedGraph',
      templateUrl: 'reports/nestedGraph.html',
      controller: nestedCtrl,
    })


    // views: {
    //   '': { templateUrl: 'manager/manager.html' },
    //   'top': { templateUrl: 'manager/top.html' },
    //   'bottom': { templateUrl: 'manager/schedule.html'}
    //   }
    // }



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
