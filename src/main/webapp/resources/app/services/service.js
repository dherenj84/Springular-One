myApp.service('userService', function($http) {
	this.getLoggedInUser = function() {
		return $http.get('getLoggedInUser');
	};
});