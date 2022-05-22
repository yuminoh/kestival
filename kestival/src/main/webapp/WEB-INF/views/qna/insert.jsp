<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<jsp:include page="../header.jsp" flush='false'/>
</head>
<body>
 <form class="needs-validation" name='frm' method='post' action='/qna/insert.do' novalidate>
    <div class="container">
    <div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1" name='qwriter'>작성자: ${id} </span>
</div>

<label for="basic-url" class="form-label">제목</label>
<div class="input-group mb-3">
  <input type="text" class="form-control" id="basic-url" name="qtitle" aria-describedby="basic-addon3">
</div>
<label for="basic-url" class="form-label">내용</label>
<div class="input-group">
  <textarea class="form-control" name="qcontext" aria-label="With textarea"></textarea>
  
</div>
<button type="submit" class="btn btn-outline-secondary">add</button>
     </div>
     </form>
<jsp:include page="../footer.jsp" flush='false'/>
</body>
</html>
