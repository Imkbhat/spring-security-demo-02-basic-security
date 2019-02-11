<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page--Yahoo</h2>
	<hr>
	<p>
		Welcome to the luv2code company home page!
	</p>
	
	<!-- Logout Button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	
	<input type="submit" value="Logout"/>
	</form:form>

</body>

</html>