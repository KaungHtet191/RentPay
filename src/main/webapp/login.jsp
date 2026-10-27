<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent : Login</title>
<%@include file="allComponent/allCss.jsp" %>
</head>
<body style="background-color:#f0f1f2;">
<%@include file="allComponent/navbar.jsp" %>

<div class="container p-2">
  <div class="row">
      <div class="col-md-4 offset-md-4">
         <div class="card">
            <div class="card-body">
               <h5 class="text-center">Login</h5> 
               
              <c:if test="${not empty failedMsg}">
               <h5 class="text-center text-danger">${failedMsg}</h5>
               <c:remove var="failedMsg" scope="session" />
               </c:if>
                               
               <form action="login" method="post">
               
               <div class="form-group">
                  <label for="exampleInputEmail1">Email Address</label>
                  <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" required="required" name="email">
               </div>
               
               <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" required="required" name="password">
               </div>
            
                <div class="text-center">
                  <button type="submit" class="btn btn-primary">Login</button>
                  <br>
                  <a href="register.jsp">Create Account</a>
                </div>
                </form>
             
            
            </div>
         </div>
      </div>
  </div>
</div>

</body>
</html>