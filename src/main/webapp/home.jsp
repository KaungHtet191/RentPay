<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent Pay</title>
<%@include file="UserPage/index2.jsp" %>
</head>
<body>
<h2>User:Home</h2>
              <c:if test="${not empty userobj}">
               <h1>Name:${userobj.name}</h1>
               <h1>Email:${userobj.email}</h1>
               </c:if>
</body>
</html>