<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<jsp:include page="../header.jsp" flush='false'/>
</head>
<body>
 <main class="form-signin">
  
    <div class="form-floating">
      <input type="text" class="form-control" disabled>
      <label for="floatingInput">부적절한 접근입니다. </label>
    </div>
    <button class="w-100 btn btn-lg btn-primary" onclick="history.back()">Back</button>
  
</main>
<jsp:include page="../footer.jsp" flush='false'/>
</body>
</html>
