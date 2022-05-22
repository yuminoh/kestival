<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>축제</title>
    <link href="/css/style.css" rel="stylesheet">
    <style>
        .kestival {font-size:2em}
    </style>
    <nav class="py-2 bg-light border-bottom">
    <div class="container d-flex flex-wrap">
      <ul class="nav me-auto">
        <!-- <li class="nav-item"><a href="${pageContext.request.contextPath}" class="nav-link link-dark px-2 active" aria-current="page">Home</a></li> -->
        <li class="nav-item"><a href="/" class="nav-link link-dark px-2"><span class="kestival">K-estival</span></a></li>
        <li class="nav-item"><a href="/cate/list.do" class="nav-link link-dark px-2">Category</a></li>
        <li class="nav-item"><a href="/qna/list.do" class="nav-link link-dark px-2">Q&A</a></li>
        <li class="nav-item"><a href="/chatting" class="nav-link link-dark px-2">Chatbot</a></li>
        
      </ul>
      <ul class="nav">
      
      <c:choose>
        <c:when test="${id != null}">
           <li class='nav_item' ><a href='/member/update_read.do?memberno=${memberno}' class='nav-link link-dark px-2'>${id } <a/></li>
           <li class='nav_item'> <A href='/member/logout.do' class='nav-link link-dark px-2'>Logout</A></li>
        </c:when>
        <c:otherwise>
          <li class='nav-item'><a href='/member/login.do' class='nav-link link-dark px-2'>Login</a></li>
          <li class="nav-item"><a href="/member/insert.do" class="nav-link link-dark px-2">Sign up</a></li>
        </c:otherwise>
      </c:choose>
        
      </ul>
    </div>
  </nav>

    
    <!-- 
    <header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Carousel</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled">Disabled</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</header>  -->
  </header>
</head>

</html>