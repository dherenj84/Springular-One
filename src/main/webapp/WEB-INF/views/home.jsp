<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>dashboard</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/styles/app.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
	integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
	crossorigin="anonymous"></script>
<script data-require="angularjs@1.6.4" data-semver="1.6.4"
	src="https://code.angularjs.org/1.6.4/angular.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular-route.min.js"></script>
<script src="resources/app/app.js"></script>
<script src="resources/app/directives/pageloader.directive.js"></script>
<script src="resources/app/directives/spinnerlabel.directive.js"></script>
<script src="resources/app/directives/utility.directives.js"></script>
<script src="resources/app/components/navbar.component.js"></script>
<script src="resources/app/components/grid.component.js"></script>
<script src="resources/app/services/service.js"></script>
<!-- Custom styles for this template -->
</head>

<body>
	<!-- <div class="row m-1 p-1">
		<ol class="breadcrumb" style="background-color: #fff;">
			<li class="breadcrumb-item" ng-repeat="breadcrumb in breadcrumbs"><a
				href="{{breadcrumb.url}}">{{breadcrumb.name}}</a></li>
		</ol>
	</div> -->

	<div class="container">
		<nav-bar user="loggedInUser"></nav-bar>
		<div class="container my-4" ng-view></div>
	</div>

</body>

</html>