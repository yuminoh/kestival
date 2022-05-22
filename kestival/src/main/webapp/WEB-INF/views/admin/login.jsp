<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../header.jsp" flush='false'/>
</head>
<body class="text-center">
<div class="container">
<div class="col-md-7 col-lg-8">
<br>
        <h4 class="mb-3">로그인</h4>
        <form class="needs-validation" name='frm' method='post' action='/admin/login.do' novalidate>

    
<main class="form-signin">
  
    <div class="form-floating">
      <input type="text" class="form-control" id="id" name="id" placeholder="아이디" required="required">
      <label for="floatingInput">아이디</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호" required="required">
      <label for="floatingPassword">비밀번호</label>
    </div>
<br>
    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign</button>
    <br>
    <button type="button" class="btn btn-outline-secondary" ><a href="/member/login.do" class='nav-link link-dark px-2'>member</a></button>
  
</main>
</form>
</div>
</div>
<jsp:include page="../footer.jsp" flush='false'/>
</body>
</html>