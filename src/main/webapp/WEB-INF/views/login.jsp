<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/styles/app.css">
</head>
<body>
	<div class="container" style="margin-top: 5em;">
		<div class="row justify-content-md-center">
			<div class="card">
				<div class="card-body">
					<h4>
						Dashboard Login
						</h1>
						<form name='f' action="login" method='POST'>
							<div class="form-group">
								<label for="username">Username</label> <input type="username"
									class="form-control" id="username" name="username">
							</div>
							<div class="form-group">
								<label for="password">Password</label> <input type="password"
									class="form-control" id="password" name="password">
							</div>
							<button type="submit" class="btn btn-primary">
								<span>Sign In</span> <span class="toggleDisplay"><i
									class="fa fa-cog fa-spin" aria-hidden="true"></i></span>
							</button>
							<small id="invalidLogin" class="form-text font-weight-bold"
								style="display: none; color: red;">Wrong Username or
								Password</small>
						</form>
				</div>
			</div>

		</div>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<!-- <script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
		integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
		integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
		crossorigin="anonymous"></script> -->
	<script type="text/javascript">
		$(function() {
			$('.toggleDisplay').toggleClass('hidden');
			$('#username').focus();
			if (window.location.search == '?error=true') {
				$('#invalidLogin').css('display', "block");
			}
		});
		$("form").submit(function() {
			$('.toggleDisplay').toggleClass('hidden');
		});
	</script>
</body>
</html>