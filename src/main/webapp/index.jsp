<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.DB.DBConnect" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent Pay</title>
<%@include file="allComponent/allCss.jsp" %>
<style>
.back-img{
background:url("img/currency.jpg");
height:50vh;
width:100%;
background-repeat:no-repeat;
margin-left:44px;
margin-top:30px;
}
.crd-ho:hover{
background-color:fcf7f7;

}
body{

}
.tit{
font-family:"Impact";
}
</style>
</head>
<body  style="background-color:#f7f7f7">



<%@include file="allComponent/navbar.jsp" %>
<div class="container-fluid back-img">
<!-- <h2 >class="text-right text-primary tit">Exchange Management System</h2> -->
</div>





<%@include file="allComponent/footer.jsp" %>
</body>
</html>