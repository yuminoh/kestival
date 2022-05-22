<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<head>
<jsp:include page="../header.jsp" flush='false'/>

<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function() { // 자동 실행
    
    $('#btn_DaumPostcode').on('click', DaumPostcode); // 다음 우편 번호
    $('#btn_close').on('click', setFocus); // Dialog창을 닫은후의 focus 이동
  });

  function setFocus() {  // focus 이동
    
    var tag = $('#btn_close').attr('data-focus'); 
    $('#' + tag).focus(); 
  
  }
</script>
</head>
<body>
<div class="container">
<DIV class='content_body'>
<div class="col-md-7 col-lg-8">
        <h4 class="mb-3">회원 가입</h4>
        <form class="needs-validation" name='frm' method='post' action='/member/insert.do' novalidate>
          <div class="row g-3">
            <div class="col-sm-6">
              <label for="mname" class="form-label">Name</label>
              <input type="text" class="form-control" id="mname" name="mname" placeholder="이름" value="" required="required">
              <div class="invalid-feedback">
                필수 입력
              </div>
            </div>
            <div class="col-12">
              <label for="id" class="form-label">ID</label>
              <div class="input-group has-validation">
                <input type="text" class="form-control" id="id" name="id" placeholder="아이디" required="required">
                <div class="invalid-feedback">
                필수 입력
              </div>
            </div>

            <div class="col-12">
              <label for="pw" class="form-label">PassWord</label>
              <input type="pw" class="form-control" id="pw" name="pw" placeholder="비밀번호" required="required">
              <div class="invalid-feedback">
                필수 입력
              </div>
            </div>
            
            <div class="col-12">
              <label for="email" class="form-label">Email <span class="text-muted"></span></label>
              <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com" required="required">
              <div class="invalid-feedback">
              </div>
            </div>
            
            <div class="col-12">
              <label for="phone" class="form-label">Phone</label>
              <input type="text" class="form-control" id="phone" name="phone" placeholder="전화번호" required="required">예) 010-0000-0000
              <div class="invalid-feedback">
                필수 입력
              </div>
            </div>
            
            <div class="form-group">
      <label for="zipcode" class="col-md-2 control-label" style='font-size: 0.9em;'>우편번호</label>    
      <div class="col-md-10">
        <input type='text' class="form-control" name='zipcode' id='zipcode' 
                   value='' style='width: 30%;' placeholder="우편번호">
        <button type="button" id="btn_DaumPostcode" class="btn btn-info btn-md">우편번호 찾기</button>
      </div>
    </div>  

    <div class="form-group">
      <label for="address1" class="col-md-2 control-label" style='font-size: 0.9em;'>주소</label>    
      <div class="col-md-10">
        <input type='text' class="form-control" name='address1' id='address1' 
                   value='' style='width: 80%;' placeholder="주소">
      </div>
    </div>   

    <div class="form-group">
      <label for="address2" class="col-md-2 control-label" style='font-size: 0.9em;'>상세 주소</label>    
      <div class="col-md-10">
        <input type='text' class="form-control" name='address2' id='address2' 
                   value='' style='width: 80%;' placeholder="상세 주소">
      </div>
    </div>   

    <div class="form-group">
      <div class="col-md-12">

<!-- ------------------------------ DAUM 우편번호 API 시작 ------------------------------ -->
<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 110px;position:relative">
  <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
</div>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    var element_wrap = document.getElementById('wrap');

    function foldDaumPostcode() {
        element_wrap.style.display = 'none';
    }

    function DaumPostcode() {
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                if(data.addressType === 'R'){
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                $('#zipcode').val(data.zonecode); // 5자리 새우편번호 사용 
                $('#address1').val(fullAddr);  // 주소 

                element_wrap.style.display = 'none';

                document.body.scrollTop = currentScroll;
                
                $('#address2').focus(); 
            },
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);

        element_wrap.style.display = 'block';
    }
</script>
<!-- ------------------------------ DAUM 우편번호 API 종료 ------------------------------ -->
            
          </div>


          <hr class="my-4">

          <button class="w-100 btn btn-primary btn-lg" type="submit">Sign up</button>
        </form>
      </div>
    </div>
  </div>
  </div>
<jsp:include page="../footer.jsp" flush='false'/>
</body>
</html>