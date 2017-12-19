myApp.directive('pageLoader', function(baseUrlDirective) {
	function link(scope, element, attrs) {
		scope.$on("loaded", function() {
			scope.loaded = true;
		})
	}

	return {
		link : link,
		restrict : 'E',
		transclude : true,
		scope : {},
		templateUrl : baseUrlDirective + 'pageloader.directive.html'
	}
});