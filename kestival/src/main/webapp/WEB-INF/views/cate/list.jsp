<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String id = (String)request.getSession().getAttribute("id");
    String manme = (String)request.getSession().getAttribute("mname");
%>
<html>
<head>
    <jsp:include page="../header.jsp" flush='false'/>
</head>
<body>
<div class="container">

<hr>
    <form class="d-flex" name='frm' action='/cate/list.do'>
      
    </form>
 </nav>
<table class="table">
  <thead>
    <tr>
    <th width="100"></th>
      <th scope="col" width="500">카테고리</th>
      <th scope="col" text-align="ringht" width="250">생성일</th>
      <th scope="col" width="250"></th>
      
    </tr>
  </thead>
  <tbody>
    <c:forEach var="cateVO" items="${list}">
        <c:set var="cateno" value="${cateVO.cateno}"/>
        <tr>
        <td width="100"></td>
            <td scope="row" width="500"><a href="../contents/list.do?cateno=${cateVO.cateno}">${cateVO.name}</a> </td>
            <td scope="row" text-align="ringht" width="250">${cateVO.cdate} </td>
            <td scope="row" text-align="right" width="250"><button type="button" class="btn btn-outline-secondary"><a href="./update.do?cateno=${cateVO.cateno}" class='nav-link link-dark px-2'>update</a></button>
    <button type="button" class="btn btn-outline-secondary"><a href="./delete.do?cateno=${cateVO.cateno}" class='nav-link link-dark px-2'>delete</a></button></th>
    
        </tr>
    </c:forEach>
  </tbody>
</table>

    <nav class="navbar navbar-light bg-light">
<button type="button" class="btn btn-outline-secondary" ><a href="/cate/insert.do" class='nav-link link-dark px-2'>add</a></button>
 
  </div>
  
  
<jsp:include page="../footer.jsp" flush='false'/>
</div>
</body>
</html>