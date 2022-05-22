<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <c:set var="contentsno" value="${contentsVO.contentsno }" />
<c:set var="cateno" value="${contentsVO.cateno }" />
<c:set var="title" value="${contentsVO.title }" /> 
<c:set var="context" value="${contentsVO.context }" />
<c:set var="file1" value="${contentsVO.file1 }" />
<c:set var="file1saved" value="${contentsVO.file1saved }" />
<c:set var="thumb1" value="${contentsVO.thumb1 }" />
<c:set var="size1_label" value="${contentsVO.size1_label }" />
<c:set var="recom" value="${contentsVO.recom }" />
<c:set var="size1" value="${contentsVO.size1 }" />

<html>
<head>
<jsp:include page="../header.jsp" flush='false'/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


<script type="text/javascript">
  $(function(){
      $('#btn_recom').on("click", function() { update_recom_ajax(${contentsVO.contentsno}); });
  });

  function update_recom_ajax(contentsno) {
        var params = "";
        // params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
        params = 'contentsno=' + contentsno; // 공백이 값으로 있으면 안됨.
        $.ajax(
          {
            url: '/contents/update_recom_ajax.do',
            type: 'post',  // get, post
            cache: false, // 응답 결과 임시 저장 취소
            async: true,  // true: 비동기 통신
            dataType: 'json', // 응답 형식: json, html, xml...
            data: params,      // 데이터
            success: function(rdata) { // 응답이 온경우
              console.log('-> rdata: '+ rdata);
              var str = '';
              if (rdata.cnt == 1) {
                // console.log('-> btn_recom: ' + $('#btn_recom').val());  // X
                // console.log('-> btn_recom: ' + $('#btn_recom').html());
                $('#btn_recom').html('♥('+rdata.recom+')');
                $('#span_animation').hide();
              } else {
                $('#span_animation').html("지금은 추천을 할 수 없습니다.");
              }
            },
            // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
            error: function(request, status, error) { // callback 함수
              console.log(error);
            }
          }
        );  //  $.ajax END

        // $('#span_animation').css('text-align', 'center');
        $('#span_animation').html("<img src='/contents/images/ani04.gif' style='width: 8%;'>");
        $('#span_animation').show(); // 숨겨진 태그의 출력
      }  
</script>
</head>

<body>
 <form name="frm" method="post" action='/contents/update.do?contentsno=${contentsno}' novalidate>
    <div class="container">
    <br>
    <input type="hidden" name="cateno" value="${param.cateno}"> <!-- 오류 조심, 컨트롤러에서 보내는 값과 동일해야함!! -->
    <input type="hidden" name="adminno" value="1"> <%-- 관리자 개발후 변경 필요 --%>
    <div class="input-group mb-3">
</div>
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
      <div class="card-body">
        <c:set var="file1saved" value="${file1saved.toLowerCase() }" />
        <DIV style="width: 50%; float: left; margin-right: 10px;">
            <c:choose>
              <c:when test="${thumb1.endsWith('jpg') || thumb1.endsWith('png') || thumb1.endsWith('gif')}">
                <%-- /static/thema/storage/ --%>
                <IMG src="/contents/storage/${file1saved}" style="width: 100%;"> 
              </c:when>
              <c:otherwise> <!-- 기본 이미지 출력 -->
                <IMG src="/contents/images/none2.png" style="width: 100%;"> 
              </c:otherwise>
            </c:choose>
            
            </div>
        </DIV>
        <DIV style="width: 47%; height: 260px; float: left; margin-right: 10px; margin-bottom: 30px;">
          축제명: 
          <input type="text" class="form-control" id="basic-url" name="title" aria-describedby="basic-addon3" value="${title}">
          내용: 
          <textarea class="form-control" name="context">${context}</textarea>
          <br>
          <br>
          <br>
           <DIV>
          <c:if test="${file1.trim().length() > 0 }">
            첨부 파일: <A href='/download?dir=/contents/storage&filename=${file1saved}&downname=${file1}'>${file1}</A> (${size1_label})  
          </c:if>
        </DIV>
          <button type='button' id="btn_recom" class="btn btn-info">♥(${recom })</button>
          <span id="span_animation"></span>
        </DIV> 
       </div>
   
    <button type="submit" class="btn btn-outline-secondary">update</button>

  
</DIV>
 </form>
 
<jsp:include page="../footer.jsp" flush='false'/>
</body>
 
</html>
