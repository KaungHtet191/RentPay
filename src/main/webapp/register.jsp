<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent : Register</title>
<%@include file="allComponent/allCss.jsp" %>
</head>
<body style="background-color:#f0f1f2;">
<%@include file="allComponent/navbar.jsp" %>

<div class="container p-2">
  <div class="row">
      <div class="col-md-4 offset-md-4">
         <div class="card">
            <div class="card-body">
            <h4 class="text-center">Registration Form</h4>
            
            <c:if test="${not empty succMsg}">
                <p class="text-center text-success">${succMsg}</p>
                <c:remove var="succMsg" scope="session"/>
            </c:if>
            
            <c:if test="${not empty failedMsg}">
                <p class="text-center text-danger">${failedMsg}</p>
                 <c:remove var="failedMsg" scope="session"/>
            </c:if>
            
             <form action="register" method="post">
               <div class="form-group">
                  <label for="exampleInputEmail1">Enter Full Name</label>
                  <input name="userName" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter full name" required="required">
               </div>
               <div class="form-group">
                  <label for="exampleInputEmail1">Email Address</label>
                  <input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" required="required">
               </div>
               <div class="form-group">
                  <label for="exampleInputEmail1">Phone No</label>
                  <input name="mobile" type="number" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter phone number" required="required">
               </div>
               <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" required="required">
               </div>
               <div class="form-check">
                  <input name="check" type="checkbox" class="form-check-input" id="exampleCheck1">
                  <label class="form-check-label" for="exampleCheck1">Agree terms & Condition</label>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            
            </div>
         </div>
      </div>
  </div>
</div>


<%@include file="allComponent/footer.jsp" %>
</body>
</html>