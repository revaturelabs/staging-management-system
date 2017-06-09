import angular from 'angular';
import angularCookies from 'angular-cookies';
import uiRouter from 'angular-ui-router';
import FusionCharts from 'fusioncharts';

import { managerCtrl } from './manager-pages/manager';
import { managerHomeCtrl } from './manager-pages/home/home';
import managerCheckinsCtr from './manager-pages/home/checkin';
import { interviewsCtrl } from './manager-pages/home/interviews/interviews';
import managerCreateCtrl from './manager-pages/create/create';
import { batchCtrl } from './manager-pages/create/batch';
import { clientCtrl } from './manager-pages/create/client';
import { userCtrl } from './manager-pages/create/user';
import managerAdvancedAssociatesCtrl from './manager-pages/advanced/associates';
import profileCtrl from './associate-pages/profile/profile';
import associateInterviewCtrl from './associate-pages/interview/interview';
import associateCtrl from './associate-pages/associate';
import { reportCtrl } from './reports/reports';
import { nestedCtrl } from './reports/nestedGraph';
import { barCtrl } from './reports/barGraph';
import loginCtrl from './login/login';

require('fusioncharts/fusioncharts.charts')(FusionCharts);

const Visualizer = window['ui-router-visualizer'].Visualizer;

const routerApp = angular.module('routerApp', [uiRouter, angularCookies]);

routerApp.service('userService', function ($cookies) {
  this.user = $cookies.getObject('user') === undefined ? {} : $cookies.getObject('user');
  this.getUser = () => ({ ...this.user });
  this.setUser = (user) => {
    $cookies.putObject('user', user);
    this.user = { ...user };
  };
});

routerApp.run(($uiRouter, $trace) => {
  // Auto-collapse children in state visualizer
  const registry = $uiRouter.stateRegistry;

  const pluginInstance = $uiRouter.plugin(Visualizer);

  $trace.enable('TRANSITION');
});

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
      controller: managerCreateCtrl,
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
    .state('manager.home', {
      url: '/home',
      views: {
        '': {
          templateUrl: 'manager-pages/home/home.html',
          controller: managerHomeCtrl,
        },
        'available@manager.home': { templateUrl: 'manager-pages/home/available.html' },
        'priorityMapped@manager.home': {
          templateUrl: 'manager-pages/home/priorityMapped.html',
        },
        'interviews@manager.home': {
          templateUrl: 'manager-pages/home/interviews/interviews.html',
          controller: interviewsCtrl,
        },
        'checkins@manager.home': {
          templateUrl: 'manager-pages/home/checkins/checkin.html',
            controller: managerCheckinsCtr,
        },
      },
    })
    .state('manager.advanced', {
      url: '/advanced',
      templateUrl: 'manager-pages/advanced/advanced.html',
      controller: managerAdvancedAssociatesCtrl,
    })
    .state('manager.advanced.allassociates', {
      url: '/associates',
      templateUrl: 'manager-pages/advanced/associates.html',
    })
    .state('associate', {
      url: '/associate',
      templateUrl: 'associate-pages/associate.html',
      controller: associateCtrl,
    })
    .state('associate.home', {
      url: '/home',
      templateUrl: 'associate-pages/profile/profile.html',
      controller: profileCtrl,
    })
    .state('associate.associateInterview', {
      url: '/interview',
      templateUrl: 'associate-pages/interview/interview.html',
      controller: associateInterviewCtrl,
    })
    .state('associate.profile', {
      url: '/profile',
      templateUrl: 'associate-pages/profile/profile.html',
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
    .state('reports.barGraph', {
      url: '/barGraph',
      templateUrl: 'reports/barGraph.html',
      controller: barCtrl,
    });


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
