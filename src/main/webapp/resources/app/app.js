// Code goes here
var myApp = angular.module('myApp', [ 'ngRoute' ]);

/*
 * myApp.provider('urlConfigure', function UrlConfigureProvider() {
 * this.baseUrlViews = ''; this.$get = function urlProviderFactory() { return {
 * getUrl : function(partial) { return this.baseUrlViews + partial + ".html"; } }; };
 * });
 */

myApp.constant("baseUrlViews", "resources/app/views/");
myApp.constant("baseUrlComponents", "resources/app/components/");
myApp.constant("baseUrlDirective", "resources/app/directives/");

myApp.config([ '$locationProvider', '$routeProvider', 'baseUrlViews',
		function config($locationProvider, $routeProvider, baseUrlViews) {
			$locationProvider.hashPrefix('!');
			$routeProvider.when('/home', {
				templateUrl : baseUrlViews + 'dashboard.html'
			}).when('/view1', {
				templateUrl : baseUrlViews + 'view1.html'
			}).when('/view2', {
				templateUrl : baseUrlViews + 'view2.html'
			}).otherwise('/home');
		} ]);

myApp.controller('dashboardCtrl', function DashboardCtrl($rootScope, $scope,
		userService) {
	userService.getLoggedInUser().then(function(response) {
		$rootScope.breadcrumbs = [ {
			name : "Home",
			url : "#!home"
		} ];
		$rootScope.loggedInUser = response.data;
	});
	$(function() {
		$('[data-toggle="tooltip"]').tooltip()
	})
});