/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

	'use strict';

	var _manager = __webpack_require__(1);

	console.log(_manager.currentCtrl);
	var routerApp = angular.module('routerApp', ['ui.router']);

	routerApp.config(function ($stateProvider, $urlRouterProvider) {

	    $urlRouterProvider.otherwise('/login');

	    $stateProvider

	    // HOME STATES AND NESTED VIEWS ========================================
	    .state('login', {
	        url: '/login',
	        templateUrl: 'login/login.html'
	    }).state('manager', {
	        url: '/manager',
	        templateUrl: 'manager/manager.html'
	    }).state('manager.current', {
	        url: '/current',
	        templateUrl: 'manager/current.html',
	        controller: _manager.currentCtrl
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

	routerApp.controller('navController', function ($scope) {

	    $scope.openMenu = function ($mdOpenMenu, ev) {
	        console.log("here");
	        originatorEv = ev;
	        $mdOpenMenu(ev);
	    };
	});

	routerApp.controller('scotchController', function ($scope) {

	    $scope.scotches = [{
	        name: 'Macallan 12',
	        price: 50
	    }, {
	        name: 'Chivas Regal Royal Salute',
	        price: 10000
	    }, {
	        name: 'Glenfiddich 1937',
	        price: 20000
	    }];

	    console.log("scotches loaded");
	});

/***/ }),
/* 1 */
/***/ (function(module, exports) {

	"use strict";

	var currentCtrl = function currentCtrl($scope) {
	    $scope.test = "test";
	};

	var forecastCtrl = function forecastCtrl($scope) {};
	// export { currentCtrl, forecastCtrl }

/***/ })
/******/ ]);