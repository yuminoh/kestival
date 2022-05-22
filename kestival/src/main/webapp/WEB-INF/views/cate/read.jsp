<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="cateno" value="${cateVO.cateno} "/>
<c:set var="adminno" value="${cateVO.adminno} "/>
<c:set var="name" value="${cateVO.name} "/>
<c:set var="cdate" value="${cateVO.cdate} "/>

<html>
<head>
<jsp:include page="../header.jsp" flush='false'/>
</head>
<body>
    <div class="container">
    
<label for="basic-url" class="form-label">카테고리 이름: ${name} </label><br>

    <button type="button" class="btn btn-outline-secondary"><a href="./update.do?cateno=${cateVO.cateno}" class='nav-link link-dark px-2'>update</a></button>
    <button type="button" class="btn btn-outline-secondary"><a href="./delete.do?cateno=${cateVO.cateno}" class='nav-link link-dark px-2'>delete</a></button>


</div>
     </div>
<jsp:include page="../footer.jsp" flush='false'/>
</body>
</html>

