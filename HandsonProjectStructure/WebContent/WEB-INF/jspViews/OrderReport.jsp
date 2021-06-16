<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form  action="FatchRecordWithinDateRange.html" modelAttribute="billRangeObj">

From price: <form:input path="fromPrice"/><br>
To Price  : <form:input path="toPrice"/><br>
<input type="submit" value="Fatch Details">

<c:if test="${not empty pizzaOrderList}">
<table border="2">
<tr>
  <th>Order ID</th>
  <th>Customer Name</th>
  <th>Pizza Id</th>
  <th>Bill</th>
  <th>Quantity</th>
</tr>
   <c:forEach items="${pizzaOrderList}" var="itr">
   <tr>
   <td><c:out value="${itr.orderId}"/></td>
   <td><c:out value="${itr.customerName}"></c:out></td>
   <td><c:out value="${itr.pizzaId}"></c:out></td>
  <td> <fmt:formatNumber value="${itr.bill}" type="currency" currencySymbol="INR." ></fmt:formatNumber>
					</td>
					<td><c:out value="${itr.numberOfPiecesOrdered}"/></td>
   </tr>
   </c:forEach>  

</table>

</c:if>

</form:form>
</body>
</html>