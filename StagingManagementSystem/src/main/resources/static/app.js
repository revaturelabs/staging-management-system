import angular from 'angular';
import angularCookies from 'angular-cookies';
import uiRouter from 'angular-ui-router';
import FusionCharts from 'fusioncharts';
import moment from 'moment';

import { managerCtrl } from './manager-pages/manager';
import { managerHomeCtrl } from './manager-pages/home/home';
import managerCheckinsCtrl from './manager-pages/home/checkin/checkin';
import { interviewsCtrl } from './manager-pages/home/interviews/interviews';
import stagingGraphController from './manager-pages/home/staging-graph/staging-graph';
import attendanceGraphCtrl from './manager-pages/home/attendance-graph/attendance-graph';
import employmentGraphCtrl from './manager-pages/home/employment-graph/employment-graph';
import managerCreateCtrl from './manager-pages/create/create';
import { batchCtrl } from './manager-pages/create/batch';
import { clientCtrl } from './manager-pages/create/client';
import { userCtrl } from './manager-pages/create/user';
import { locCtrl } from './manager-pages/create/location';
import { jobCtrl } from './manager-pages/create/job';
import managerAdvancedCtrl from './manager-pages/advanced/advanced';
import profileCtrl from './associate-pages/profile/profile';
import associateInterviewCtrl from './associate-pages/interview/interview';
import associateCtrl from './associate-pages/associate';
import loginCtrl from './login/login';

require('fusioncharts/fusioncharts.charts')(FusionCharts);

const Visualizer = window['ui-router-visualizer'].Visualizer;

const routerApp = angular.module('routerApp', [uiRouter, angularCookies]);

routerApp.service('userService', function ($cookies) {
  this.user = $cookies.getObject('user') === undefined ? {} : $cookies.getObject('user');
  this.getUser = function(){
	  checkCookies();
	  return this.user
	 };
  this.setUser = (user) => {
    $cookies.putObject('user', user);
    this.user = { ...user };
  };

	// Roles
	var trainerRole = "ROLE_TRAINER";
	/**
	 * Retrieves role from cookie
	 * 
	 * @returns A cookie that contains the role
	 */
	function getCookie() {
		//TEST
		$cookies.getObject('token');
		console.log("Let's see what my sf role is: "+$cookies.get("role"))
		return $cookies.get("role");
	}
	//test
	this.tokenCookie = $cookies.getObject('token');
	
	
	/**
	 * Moves user to home page when entering root
	 */
	function checkCookies() {
		var role = getCookie();
		if (role === trainerRole)
			this.user={id:false,permisssion:true};
	};
});

routerApp.directive('scrollToBottom', ($timeout, $window) => {
  return {
    scope: {
        scrollToBottom: "="
    },
    restrict: 'A',
    link: (scope, element, attr) => {
      scope.$watchCollection('scrollToBottom', (newVal) => {
        if (newVal) {
          $timeout(() => {
            element[0].scrollTop = element[0].scrollHeight;
          }, 0);
        }
      });
    }
  };
});

routerApp.run(($uiRouter, $trace, $rootScope) => {

	//Ui Visualizer
  // Auto-collapse children in state visualizer
  // const registry = $uiRouter.stateRegistry;
  // $uiRouter.stateRegistry.get().map(s => s.$$state())
  //     .filter(s => s.path.length === 2 || s.path.length === 3)
  //     .forEach(s => s._collapsed = true);
  //
  // const pluginInstance = $uiRouter.plugin(Visualizer);
  //
  // $trace.enable('TRANSITION');

	//Global Functions
	$rootScope.dateConverter = (time) => {
    return moment(time).format('MMM D, hh:mm a');
	};
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
    .state('manager.create.location', {
      url: '/location',
      templateUrl: 'manager-pages/create/location.html',
      controller: locCtrl,
    })
    .state('manager.create.job', {
      url: '/job',
      templateUrl: 'manager-pages/create/job.html',
      controller: jobCtrl,

    })
    .state('manager.home', {
      url: '/home',
      views: {
        '': {
          templateUrl: 'manager-pages/home/home.html',
          controller: managerHomeCtrl,
        },
        'staging-graph@manager.home': {
          templateUrl: 'manager-pages/home/staging-graph/staging-graph.html',
          controller: stagingGraphController,
        },
        'attendance-graph@manager.home': {
          templateUrl: 'manager-pages/home/attendance-graph/attendance-graph.html',
          controller: attendanceGraphCtrl,
        },
        'employment-graph@manager.home': {
          templateUrl: 'manager-pages/home/employment-graph/employment-graph.html',
          controller: employmentGraphCtrl,
        },
        'interviews@manager.home': {
          templateUrl: 'manager-pages/home/interviews/interviews.html',
          controller: interviewsCtrl,
        },
        'checkins@manager.home': {
          templateUrl: 'manager-pages/home/checkin/checkin.html',
            controller: managerCheckinsCtrl,
        },
      },
    })
    .state('manager.associateView', {
      url: '/associate/:id',
      templateUrl: 'associate-pages/profile/profile.html',
      controller: profileCtrl,
    })
    .state('manager.advanced', {
      url: '/advanced',
      templateUrl: 'manager-pages/advanced/advanced.html',
      controller: managerAdvancedCtrl,
    })
    .state('manager.advanced.allassociates', {
      url: '/associates',
      templateUrl: 'manager-pages/advanced/associates/associates.html',
    })
    .state('manager.advanced.batches', {
      url: '/batches',
      templateUrl: 'manager-pages/advanced/batches/batches.html'
    })
    .state('manager.advanced.batches.edit', {
      url: '/edit/:id',
      templateUrl: 'manager-pages/create/batch.html',
      controller: batchCtrl,
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
});