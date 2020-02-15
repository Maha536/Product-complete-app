<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- ${pageContext.request.contextPath} : Context Path ~ Base Url -->
<a href="${pageContext.request.contextPath}/student/entry">Add New Student</a>

<hr/>
<table border="1" width="100%">
	<thead>
		<th>NAME</th>
		<th>EMAIL</th>
		<th></th>
		<th></th>
	</thead>
	<tbody>
		<core:forEach var="student" items="${students}">
			<tr>
				<td>${student.name}</td>
				<td>${student.email}</td>
				<td><a href="${pageContext.request.contextPath}/student/update?id=${student.id}">UPDATE</a></td>
				<td><a href="${pageContext.request.contextPath}/student/delete/${student.id}">DELETE</a></td>
			</tr> 
		</core:forEach>
	</tbody>
</table>
</boy>
</html>