import angular from 'angular';
import uiRouter from 'angular-ui-router';
import angularBootstrap from 'angular-bootstrap-npm';

var FusionCharts = require("fusioncharts");
require("fusioncharts/fusioncharts.charts")(FusionCharts);

console.log();

import { managerCtrl } from './manager-pages/manager';
import { batchCtrl } from './manager-pages/create/batch';
import { clientCtrl } from './manager-pages/create/client';
import { userCtrl } from './manager-pages/create/user';
import { reportCtrl } from './reports/reports';
import { nestedCtrl } from './reports/nestedGraph';
import { loginCtrl } from './login/login';
import { employedCtrl } from './reports/employed';


const routerApp = angular.module('routerApp', [uiRouter, angularBootstrap]);

routerApp.config(($stateProvider, $urlRouterProvider) => {
  $urlRouterProvider.otherwise('/login');

  $stateProvider // HOME STATES AND NESTED VIEWS
    .state('login', {
      url: '/login',
      templateUrl: 'login/login.html',
      controller: loginCtrl,
    })
    .state('manager', {
      url: '/manager',
      templateUrl: 'manager-pages/manager.html',
      controller: managerCtrl,
    })
    .state('manager.create', {
      url: '/create',
      templateUrl: 'manager-pages/create/create.html',
    })
    .state('manager.create.batch', {
      url: '/batch',
      templateUrl: 'manager-pages/create/batch.html',
      controller: batchCtrl,
    })
    .state('manager.create.user', {
      url: '/user',
      templateUrl: 'manager-pages/create/user.html',
      controller: userCtrl,

    })
    .state('manager.create.client', {
      url: '/client',
      templateUrl: 'manager-pages/create/client.html',
      controller: clientCtrl,
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
    .state('employed', {
    	url: '/employed',
    	templateUrl: 'reports/employed.html',
    	controller: employedCtrl,
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
