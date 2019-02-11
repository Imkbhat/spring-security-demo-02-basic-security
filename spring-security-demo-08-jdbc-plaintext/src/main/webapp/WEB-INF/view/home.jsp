<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

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
	<!-- Displaying User Id and Role -->
	
	<hr>
	<p>
		User: <security:authentication property="principal.username"/>
	</p>
	<p>
		Role(s) : <security:authentication property="principal.authorities"/>
	</p>
	
	<hr>
	<!-- Link Restriction -->
	<!-- Add link to leaders(Managers) -->
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders">LeaderShip Meetings</a>
			(Only for Manager Peeps)
		</p>
		
		<hr>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/systems">IT Systems Meetings</a>
			(Only for Admin Peeps)
		</p>
		
		<hr>
	</security:authorize>
	
	<!-- Logout Button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	
	<input type="submit" value="Logout"/>
	</form:form>

</body>

</html>