<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
  body.dark-mode {
    background-color: #121212 !important;
    color: #e0e0e0 !important;
  }
  body.dark-mode .bg-light, 
  body.dark-mode .card,
  body.dark-mode .container-fluid.bg-light {
    background-color: #1e1e1e !important;
    color: #ffffff !important;
  }
  body.dark-mode .navbar-dark.bg-custom {
    background-color: #0f172a !important;
  }
  body.dark-mode .form-control {
    background-color: #2d2d2d !important;
    color: #ffffff !important;
    border-color: #444 !important;
  }
  body.dark-mode .table {
    color: #ffffff !important;
    background-color: #1e1e1e !important;
  }
</style>

<div class="container-fluid" style="height:10px;background-color:#303f9f"></div>

<div class="container-fluid p-3 bg-light">
  <div class="row">
    <div class="col-md-3"></div>
    <div class="col-md-6">
      <form class="form-inline my-2 my-lg-0" action="https://www.google.com/search" method="GET" target="_blank">
        <input class="form-control mr-sm-2" type="search" name="q" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
    </div>
    <div class="col-md-3">
      <a href="login.jsp" class="btn btn-success">Login</a>
      <a href="register.jsp" class="btn btn-primary">Register</a>
    </div>
  </div>
</div>

<nav class="navbar navbar-expand-lg navbar-dark bg-custom">
  <a class="navbar-brand" href="#">Rent Pay</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Welcome <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#">Our</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#">Exchange</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link disabled" href="#">System</a>
      </li>
    </ul>
    
    <div class="form-inline my-2 my-lg-0 ml-30">
     
      <button class="btn btn-light my-2 my-sm-0" type="button" onclick="toggleTheme()">Setting</button>
      <a href="ContactForm/index.html" class="btn btn-light my-2 my-sm-0 ml-2">Contact Us</a>
    </div>
  </div>
</nav>


<script>
  
  (function() {
    if (localStorage.getItem("theme") === "dark") {
      document.body.classList.add("dark-mode");
    }
  })();

  function toggleTheme() {
    document.body.classList.toggle("dark-mode");
    
    
    if (document.body.classList.contains("dark-mode")) {
      localStorage.setItem("theme", "dark");
    } else {
      localStorage.setItem("theme", "light");
    }
  }
</script>