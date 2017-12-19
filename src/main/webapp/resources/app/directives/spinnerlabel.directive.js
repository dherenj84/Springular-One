myApp.directive('spinnerLabel', function() {
	function link(scope, element, attrs) {
		scope.$on("startSpinner", function() {
			scope.toggle = true;
		});
		scope.$on("stopSpinner", function() {
			scope.toggle = false;
		})
	}
	return {
		link : link,
		restrict : 'E',
		template : '<span ng-transclude></span> <span ng-show="toggle"><i class="fa fa-spinner fa-spin" aria-hidden="true"></i></span>',
		scope : {},
		transclude : true
	};
})