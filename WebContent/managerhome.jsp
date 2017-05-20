<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="/StagingManagementSystem/JavaScripts/AddAssociateControllerScript.js"></script>

	
<title>SMS</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"><span class="glyphicon glyphicon-globe"></span>
					Staging Management System</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right text-uppercase">
					<!-- Manager dropdown-->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
							Manager </a>
					<!-- navbar link logout -->
							<li><a href="javascript:formSubmit()"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>

							<script>
								function formSubmit() {
									document.getElementById("logoutForm")
											.submit();
								}
							</script>

							<!-- csrt for log out-->
							<form action="logout" method="post" id="logoutForm">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
							</form>

				</ul>
			</div>
		</div>
	</nav>
	



	
</body>
<script defer>
$( document ).ready( function( )//Document is ready
{
	$( document ).on( "change",".ReqTable.update .tablerow input",function( e )
	{
		var row = $( e.target ).closest( ".tablerow" );
		var javavar = $( row ).find( ".reqid" ).val( );
		var dotnetvar = $( row ).find( ".expdate" ).val( );
		var jtavar = $( row ).find( ".exptype" ).val( );
		var request = $.ajax( { url: "editRequestAsEmp" , type: "POST" , data: {javasesh : javavar , dotnetsesh : dotnetvar , jtasesh : jtavar , ajax: "true" } } );
	});
});S
</script>
</html>
