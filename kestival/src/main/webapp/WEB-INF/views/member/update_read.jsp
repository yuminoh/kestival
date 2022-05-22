<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="memberno" value="${memberVO.memberno} "/>
<c:set var="id" value="${memberVO.id} "/>
<c:set var="mname" value="${memberVO.mname} "/>
<c:set var="pw" value="${memberVO.pw} "/>
<c:set var="email" value="${memberVO.email} "/>
<c:set var="phone" value="${memberVO.phone} "/>
<c:set var="zipcode" value="${memberVO.zipcode} "/>
<c:set var="address1" value="${memberVO.address1} "/>
<c:set var="address2" value="${memberVO.address2} "/>
<c:set var="mdate" value="${memberVO.mdate} "/>

<!DOCTYPE html>
<html lang="ko">
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<head>
<jsp:include page="../header.jsp" flush='false'/>

</head>
<body>
<div class="container">
<DIV class='content_body'>
<div class="col-md-7 col-lg-8">
        <h4 class="mb-3">회원 정보</h4>
          <div class="row g-3">
            <div class="col-sm-6">
              <label for="mname" class="form-label">Name</label>
              <input type="text" class="form-control" id="mname" name="mname" value="${mname}"  disabled>
              <div class="invalid-feedback">
              </div>
            </div>
            <div class="col-12">
              <label for="id" class="form-label">ID</label>
              <div class="input-group has-validation">
                <input type="text" class="form-control" id="id" name="id" value="${id}"  disabled>
                <div class="invalid-feedback">
                필수 입력
              </div>
            </div>

            <div class="col-12">
              <label for="pw" class="form-label">PassWord</label>
              <input type="pw" class="form-control" id="pw" name="pw" value="${pw}"  disabled>
              <div class="invalid-feedback">
                필수 입력
              </div>
            </div>
            
            <div class="col-12">
              <label for="email" class="form-label">Email <span class="text-muted"></span></label>
              <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com" value="${email}"  disabled>
              <div class="invalid-feedback">
              </div>
            </div>
            
            <div class="col-12">
              <label for="phone" class="form-label">Phone</label>
              <input type="text" class="form-control" id="phone" name="phone" value="${phone}"  disabled>예) 010-0000-0000
              <div class="invalid-feedback">
                필수 입력
              </div>
            </div>
            
            <div class="form-group">
      <label for="zipcode" class="col-md-2 control-label" style='font-size: 0.9em;' disabled>우편번호</label>    
      <div class="col-md-10">
        <input type='text' class="form-control" name='zipcode' id='zipcode' 
                   value='' style='width: 30%;' value="${zipcode}" >
        <button type="button" id="btn_DaumPostcode" class="btn btn-info btn-md" disabled>우편번호 찾기</button>
      </div>
    </div>  

    <div class="form-group">
      <label for="address1" class="col-md-2 control-label" style='font-size: 0.9em;'>주소</label>    
      <div class="col-md-10">
        <input type='text' class="form-control" name='address1' id='address1' 
                   value='' style='width: 80%;' value="${address1}"  disabled>
      </div>
    </div>   

    <div class="form-group">
      <label for="address2" class="col-md-2 control-label" style='font-size: 0.9em;'>상세 주소</label>    
      <div class="col-md-10">
        <input type='text' class="form-control" name='address2' id='address2' 
                   value='' style='width: 80%;' value="${address2}"  disabled>
      </div>
    </div>   

    <div class="form-group">
      <div class="col-md-12">

          </div>


          <hr class="my-4">

          <button class="w-100 btn btn-primary btn-lg" type="submit"><a href="./update.do?memberno=${memberno }" class='nav-link link-dark px-2'>수정</a></button>
        </form>
      </div>
    </div>
  </div>
  </div>
<jsp:include page="../footer.jsp" flush='false'/>
</body>
</html>