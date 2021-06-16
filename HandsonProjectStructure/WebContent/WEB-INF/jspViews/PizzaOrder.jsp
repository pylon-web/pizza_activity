<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
}
</style>
</head>
<body>

	<form:form modelAttribute="pizzaOrderObj" method="post" action="SavePizzaOrder.html">
	Customer Name: <form:input path="customerName"/><br>
	
	Customer Contact: <form:input path="contactNumber"/><br>
	
	Pizza name: <form:select path="pizzaId">
	             <form:option value="" label="---Select---"></form:option>
	             <form:options items="${pizzaNamesAndId}"/>
	</form:select><br>
	
	Quantity: <form:input path="numberOfPiecesOrdered"/><br>
	<input type="submit" value="Order Pizza">
	<a href="index.jsp">Home</a>
	
	<spring:hasBindErrors name="pizzaOrderObj">
	<h3>All Errors</h3>
	<form:errors path="*" cssClass="error"></form:errors>
	</spring:hasBindErrors>
	


	</form:form>
</body>
</html>