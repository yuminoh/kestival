<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<title>chatting</title>

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<%-- WebSocket 관련 설정 --%>
<script src="/js/sockjs.min.js"></script>  <%--/static 폴더 기준 --%>
<script src="/js/stomp.min.js"></script>  <%--/static 폴더 기준 --%>

<script type="text/JavaScript" src="/js/app.js"></script>  <%--/static 폴더 기준 --%>
          
</head>
<body>
<jsp:include page="../header.jsp" flush='false'/>
<div id="main-content" class="container">
    <H2>챗봇</H2>
            <form class="form-inline">
                <div class="form-group">
                    <label for="connect">연결:</label>
                    <button id="connect" class="btn btn-default" type="submit">연결</button>
                    <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">해제</button>
                </div>
            </form>
            <form class="form-inline">
                <div class="form-group" style="width: 90%;">
                    <label for="msg">문의</label>
                    <input type="text" id="msg" class="form-control" placeholder="내용을 입력하세요...." style="width:90%;">
                </div>
                <button id="send" class="btn btn-default" disabled type="submit">보내기</button>
            </form>


    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>
                <tr>
                    <th>메세지</th>
                </tr>
                </thead>
                <tbody id="communicate">
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp" flush='false'/>
</body>
</html>