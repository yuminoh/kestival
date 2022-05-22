<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<html>
<head>
<jsp:include page="../header.jsp" flush='false'/>
</head>

<body>
 <form class="needs-validation" name='frm' method='post' action='/contents/insert.do' novalidate enctype="multipart/form-data">
    <div class="container">
    <br>
    <input type="hidden" name="cateno" value="${param.cateno}"> <!-- 오류 조심, 컨트롤러에서 보내는 값과 동일해야함!! -->
    <input type="hidden" name="adminno" value="1"> <%-- 관리자 개발후 변경 필요 --%>
    <div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">작성자: ${id} </span>
</div>
<label for="basic-url" class="form-label">축제 이름</label>
<div class="input-group mb-3">
  <input type="text" class="form-control" id="basic-url" name="title" aria-describedby="basic-addon3">
</div>
<div class="col-auto">
  <label for="basic-url" class="form-label">축제 장소</label>
  <div class="row g-3">
  <div class="input-group mb-3">
  <input type="text" class="form-control" id="basic-url" name=location aria-describedby="basic-addon3">
</div>
<div class="col-auto">
  <label for="basic-url" class="form-label">축제 기간</label>
  <div class="row g-3">
  <div class="input-group mb-3">
  <input type="text" class="form-control" id="basic-url" name="perDate" aria-describedby="basic-addon3">
  </div>
</div>
</div>
</div>
     <label for="basic-url" class="form-label">내용</label>
    <div class="input-group">
  <textarea class="form-control" name="context" aria-label="With textarea"></textarea></div>
    <div class="form-group">
       <label class="control-label col-md-2">이미지</label>
       <div class="col-md-10">
         <input type='file' class="form-control" name='file1MF' id='file1MF' 
                    value='' placeholder="파일 선택">
       </div>
    </div>  
    <br>
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-outline-secondary">add</button>
      <button type="button" onclick="location.href='./list.do?cateno=${param.cateno}'" class="btn btn-outline-secondary">목록</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../footer.jsp" flush='false'/>
</body>
 
</html>
