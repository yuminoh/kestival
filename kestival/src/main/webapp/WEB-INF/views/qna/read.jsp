<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String id = (String)request.getSession().getAttribute("id");
    String mname = (String)request.getSession().getAttribute("mname");
%>
<c:set var="qnano" value="${qnaVO.qnano} "/>
<c:set var="qwriter" value="${qnaVO.qwriter} "/>
<c:set var="qtitle" value="${qnaVO.qtitle} "/>
<c:set var="qcontext" value="${qnaVO.qcontext} "/>
<c:set var="qdate" value="${qnaVO.qdate} "/>

<c:set var="replyno" value="${replyVO.replyno}"/>
<c:set var="qnano" value="${replyVO.qnano} "/>
<c:set var="rwiter" value="${replyVO.rwiter}"/>
<c:set var="rcontext" value="${replyVO.rcontext}"/>
<c:set var="rdate" value="${replyVO.rdate}"/>

<html>
<head>
<jsp:include page="../header.jsp" flush='false'/>
</head>
<body>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    showReplyList();
    
});
function showReplyList(){
    
    var url = "/qna/replylist.do";
    var id = "${sessionScope.id}";
    
    $.ajax({
        type: 'GET',
        url: url,
        data: {qnano : "${qnaVO.qnano}"},
        success: function(replylist) {
            var htmls = "";
            
            console.log(id);
            $(replylist).each(function(){
                htmls += '<div class="media text-muted pt-3" id="replyno' + this.replyno + '">';
                htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
                htmls += '<span class="d-block">';
                htmls += '<strong class="text-gray-dark" style="font-size: 1.3em">' + this.rwiter + '</strong>';
                htmls += '<span style="padding-left: 7px; font-size: 9pt">';
                if(id == this.rwiter){
                    console.log(this.rwiter);
                htmls += '<button type="button" class="btn btn-outline-secondary" id="btn_add"><a href="javascript:fn_reupdate(' + this.replyno + ', \'' + this.rwiter + '\', \'' + this.rcontext + '\')">update</a></button>'; //javascript:void(0)
                htmls += '<button type="button" class="btn btn-outline-secondary" id="btn_add"><a href="javascript:fn_deleteReply(' + this.replyno + ')" >delete</a></button>'; //
                }
                
                htmls += '</span>'; 
                htmls += '</span>';
                htmls += '<div style="font-size: 1.2em">' +this.rcontext+'</div>';
                htmls += '</p>';
                htmls += '</div>';
            });
            $("#replylist").html(htmls);
        }
    });
            
}
$(document).on('click', '#btn_add', function(){
    var rcontext = $('#rcontext').val();
    var paramData = {"qnano": '${qnaVO.qnano}'
            ,"rcontext": rcontext
    };
    
    console.log(paramData);
    $.ajax({
        url: "qna/reinsert.do"
        , data : paramData
        , type : 'POST'
        , dataType : 'text'
        , success: function(result){
            console.log(result);
            showReplyList();
            $('#rcontext').val('');
        }
    });
});
function fn_reupdate(replyno, rwiter, rcontext){
    var htmls = "";
    
    htmls += '<div class="media text-muted pt-3" id="replyno' + replyno + '">';
    htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
    htmls += '<span class="d-block">';
    htmls += '<strong class="text-gray-dark">' + rwiter + '</strong>';
    htmls += '<span style="padding-left: 7px; font-size: 9pt">';
    htmls += '<a href="javascript:fn_reupdateReply(' + replyno + ', \''+ rcontext + '\')" style="padding-right:5px">저장</a>';
    htmls += '<a href="javascript:showReplyList()">취소<a>';
    htmls += '</span>';
    htmls += '</span>';     
    htmls += '<textarea name="updatecontent" id="updatecontent" class="form-control" rows="3">';
    htmls += rcontext;
    htmls += '</textarea>';
    htmls += '</p>';
    htmls += '</div>';
    $('#replyno' + replyno).replaceWith(htmls);
    $('#replyno' + replyno + ' #rcontext').focus();
}
function fn_reupdateReply(replyno, rcontext){
    var updatecontent = $('#updatecontent').val();
    var paramData = {"rcontext": updatecontent
            , "replyno": replyno
    };
    
    $.ajax({
        url: "/qna/reupdate.do"
        , data : paramData
        , type : 'POST'
        , dataType : 'text'
        , success: function(result){
            console.log(result);
            showReplyList();
        }, error: function(error){
            console.log("에러: " + error);
        }
    });
}
function fn_deleteReply(replyno){
    var paramData = {"replyno": replyno};
    $.ajax({
        url: "/qna/redelete.do"
        , data : paramData
        , type : 'POST'
        , dataType : 'text'
        , success: function(result){
            showReplyList();
        }
    });
}
</script>
 <form class="needs-validation" name='frm' method='post' action='/qna/insert.do' novalidate>
    <div class="container">
    <br>
    <div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1" name='qwriter'>작성자: ${qnaVO.qwriter} </span>
</div>

<label for="basic-url" class="form-label">제목: ${qtitle} </label><br>

<label for="basic-url" class="form-label">내용: </label>
<div class="input-group">
  <label for="basic-url" class="form-label"> ${qcontext} </label>
  
</div>
<c:set var="qwriter" value="${qnaVO.qwriter} "/>
<c:set var="id" value="${sessionScope.id} "/>
<c:if test="${id == qwriter}">
    <button type="button" class="btn btn-outline-secondary"><a href="./update.do?qnano=${qnaVO.qnano}" class='nav-link link-dark px-2'>update</a></button>
    <button type="button" class="btn btn-outline-secondary"><a href="./delete.do?qnano=${qnaVO.qnano}" class='nav-link link-dark px-2'>delete</a></button>
</c:if>
<hr>  
<div id='reply'>
<form name='frm_reply' id='frm_reply' method='post'>

<div class="mb-3 row">
    <label for="staticEmail" class="col-sm-2 col-form-label">댓글 작성자: ${id} </label>
  </div> 
  <div class="col-12">
     <div class="input-group has-validation">
       <input type="text" class="form-control" id="rcontext" name="rcontext" placeholder="댓글" required="required">
    <div class="invalid-feedback">
     </div>
   </div>
 </div>
 <button type="button" class="btn btn-outline-secondary" id="btn_add">reply</button>
 </form> 
 <hr>

<div>
 <div id="replylist">
 
 </div>
</div>
     </div>
<jsp:include page="../footer.jsp" flush='false'/>
</body>
</html>
