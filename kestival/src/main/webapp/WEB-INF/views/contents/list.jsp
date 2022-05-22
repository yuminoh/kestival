<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../header.jsp" flush='false'/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript">
 
function recom_ajax(contentsno, status_count) {
    console.log("-> recom_" + status_count + ": " + $('#recom_' + status_count).html());  // A tag body      
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
          var str = '';
          if (rdata.cnt == 1) {
            //$('#span_animation_' + status_count).hide();   // SPAN 태그에 animation 출력
            $('#recom_' + status_count).html('♥('+rdata.recom+')');     // A 태그에 animation 출력
          } else {
            // $('#span_animation_' + status_count).html("X");
            $('#recom_' + status_count).html('♥(X)');
          }
        },
        // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
        error: function(request, status, error) { // callback 함수
          console.log(error);
        }
      }
    );  //  $.ajax END

    $('#recom_' + status_count).html("<img src='/contents/images/ani04.gif' style='width: 10%;'>");
    // $('#span_animation_' + status_count).css('text-align', 'center');
    //$('#span_animation_' + status_count).html("<img src='/contents/images/ani04.gif' style='width: 10%;'>");
    //$('#span_animation_' + status_count).show(); // 숨겨진 태그의 출력
      
  }  
  
</script>
</head>
<body>
<div class="container">
<nav class="navbar navbar-light bg-light">
<button type="button" class="btn btn-outline-secondary" ><a href="/contents/insert.do?cateno=${param.cateno }" class='nav-link link-dark px-2'>add</a></button>
 

    <form class="d-flex" name='frm' action='/contents/list.do'>
    <input type="hidden" name="cateno" value="${param.cateno}">
      <input class="form-control me-2" type="text" placeholder="Search" name="word" id="word" style="width:300px;height:50px;" value="${word}">
      <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
 </nav>
<table class="table">
  <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col" class="text-center">사진</th>
      <th scope="col">축제 이름</th>
      <th scope="col">추천수</th>
      <th scope="col">작성일</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="contentsVO" items="${list }" varStatus="status">
        <c:set var="contentsno" value="${contentsVO.contentsno }" />
        <c:set var="recom" value="${contentsVO.recom }" />
        <c:set var="title" value="${contentsVO.title }" />
        <c:set var="file1" value="${contentsVO.file1 }" />
        <c:set var="rdate" value="${contentsVO.rdate }" />
        <tr>
            <td scope="row">${contentsVO.contentsno} </td>
            <td style='vertical-align: middle; text-align: center;'>
            <c:choose>
              <c:when test="${file1.endsWith('jpg') || file1.endsWith('png') || file1.endsWith('gif')}">
                <%-- /static/thema/storage/ --%>
                <IMG src="/contents/storage/${file1 }" style="width: 120px; height: 80px;">
              </c:when>
              <c:otherwise> 
                <IMG src="/contents/images/none2.png" style="width: 120px; height: 80px;">
              </c:otherwise>
            </c:choose>
          </td>  
          <td scope="row"><a href="/contents/read.do?cateno=${param.cateno }&contentsno=${contentsno}"><strong>${title}</strong></a>  </td>
          <td><button type='button' id="btn_recom" class="btn btn-info"><A id="recom_${status.count }" href="javascript:recom_ajax(${cateno }, ${status.count })" class="recom_link">♥(${recom })</A></button></td>
            
            <!-- <td scope="row"><a href="/contents/read.do?contentsno=${contentsno}&now_page=${param.now_page }&word=${param.word}"><strong>${title}</strong></a>  </td>-->
            <td scope="row">${contentsVO.rdate} </td>
        </tr>
    </c:forEach>
  </tbody>
</table>
<!-- 
<div class="btn-group me-2" role="group" aria-label="Second group">
    <c:if test="${pageb.prev }">
        <button type="button" class="btn btn-secondary"><a href='<c:url value="./list.do?cateno=${param.cateno}&page=${pageb.start-1 }&word=${param.word}"/>' class='nav-link link-dark px-2'></a></button>
    </c:if>
    <c:forEach begin="${pageb.start }" end="${pageb.end }" var="pageNum">
    
        <button type="button" class="btn btn-secondary"><a href='<c:url value="./list.do?cateno=${param.cateno}&page=${pageNum }&word=${param.word}"/>' class='nav-link link-dark px-2'>${pageNum }</a></button>
    
    </c:forEach>
    <c:if test="${pageb.next && pageb.end >0 }">
   
        <button type="button" class="btn btn-secondary"><a href='<c:url value="./list.do?cateno=${param.cateno}&page=${pageb.end+1 }&word=${param.word}"/>' class='nav-link link-dark px-2'></a></button>
   
    </c:if>
  </div>
   -->
      <DIV class='bottom_menu'>${paging}
      
  </DIV> 
  </div> 
 
<jsp:include page="../footer.jsp" flush='false'/>
</div>
</body>
</html>