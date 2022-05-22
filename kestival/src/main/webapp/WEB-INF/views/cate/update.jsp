<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="cateno" value="${cateVO.cateno} "/>
<c:set var= "adminno" value= "${cateVO.adminno}"/>
<c:set var="name" value="${cateVO.name} "/>
<c:set var="cdate" value="${cateVO.cdate} "/>

<html>
<head>
<jsp:include page="../header.jsp" flush='false'/>
</head>
<body>
 <form name="frm" method="post" action='/cate/update.do?cateno=${cateno}' novalidate>
    <div class="container">
   <br>
      <div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">작성자: ${id} </span>
</div>
<label for="basic-url" class="form-label">카테고리 이름</label>
<div class="input-group mb-3">
  <input type="text" class="form-control" id="basic-url" name="name" aria-describedby="basic-addon3" value="${name }">
</div>
<button type="submit" class="btn btn-outline-secondary">update</button>
     </div>
     </div>
</form>
<jsp:include page="../footer.jsp" flush='false'/>
</body>
</html>