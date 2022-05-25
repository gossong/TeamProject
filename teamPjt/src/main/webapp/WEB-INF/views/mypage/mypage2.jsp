<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
        integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<!--     <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script> -->
 <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>

<!-- 부트스트랩 드랍다운 작동하게 해주는 자바스크립트 -->
    <script type="text/javascript">
	    $(document).ready(function() {
	        $(".dropdown-toggle").dropdown();
	    });
    </script>


 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/mypage_css/mypage2.css">
	<script type="text/javascript">
	
	
	function Fnalert1(idx) {
			var funding_idx = idx;
		    window.location.href = "funding_modify.do?funding_idx="+funding_idx;
	}
	
	function Fnalert2(idx) {
		if(confirm("수정 시 제품 설명 pdf 파일을 다시 업로드 해야합니다. 그래도 하시겠습니까?")) {
		
			var funding_idx = idx;
			
		    window.location.href = "funding_modify_content.do?funding_idx="+funding_idx+"&check="+0;
		}
	}
	
	function Fnalert3(idx) {
		if(confirm("수정 시 공지 사항 사진 파일을 다시 업로드 해야합니다 그래도 하시겠습니까?")) {
		
			var funding_idx = idx;
			
		    window.location.href = "funding_modify_notice.do?funding_idx="+funding_idx+"&check="+1;
		}
	}
	
	function StoreSubmit(idx){
		var flag  = false;
		var path = '<%= request.getContextPath()%>';
		$.ajax({
			url:path+"/store/check_store.do?funding_idx="+idx,
			type:"get",
			success:function(data){
				console.log(data);
				if(data >= 1){
					
					alert('이미 스토어에 등록된 상품입니다.');
				}
				else{
					if(confirm("스토어 제품 신청 시 입력 양식을 다시 작성하셔야 합니다.")) {
						
						var funding_idx = idx;
						
					    window.location.href = path+"/store/store_registerFun.do?funding_idx="+funding_idx;
					}
					
				}
			},
			error:function(){	
				alert('오류 발생');
			}
		});
		
	
	}
	
	</script>
<!-- 스토어 스크립트 -->
<script type="text/javascript">
var path = '<%= request.getContextPath()%>';
//수량 추가
function Storealert1(idx) {
	
	var store_idx = idx;
    window.location.href = path+"/store/store_modify.do?store_idx="+store_idx;
}
//이미지 변경
function Storealert2(idx) {
	
	if(confirm("수정 시 제품 설명 pdf 파일을 다시 업로드 해야합니다. 그래도 하시겠습니까?")) {
		
		var store_idx = idx;
		
	    window.location.href = path+"/store/store_modify_content.do?store_idx="+store_idx+"&check="+0;
	}
}

//공지사항 변경
function Storealert3(idx) {
if(confirm("수정 시 제품 설명 pdf 파일을 다시 업로드 해야합니다. 그래도 하시겠습니까?")) {
		
		var store_idx = idx;
		
	    window.location.href = path+"/store/store_modify_notice.do?store_idx="+store_idx+"&check="+1;
	
}
}


</script>
<title>마이페이지</title>
</head>
<body>
<c:import url="/header.do"></c:import>

<main id="wrapper">

 <div class="container">
        <div class="row" style="margin-top: 10%;">
          <div class="col-md-4 col-sm-12" >
            <div style="box-shadow: 0px 0px 20px 5px rgba(0,0,0,0.05); border-radius: 10px;">
                <div class="row"> 
                    <div class="col-sm-12">
                     
                    </div>
                </div>
              
              <table class="table">
              	<thead>
              	<tr style="cursor:pointer;" onclick="location.href='my_info.do'">
              		<th colspan="3">
              			<h5>${login.member_name}님 > </h5>
              			${login.member_email}
                    </th>
              	</tr>
              		
              	</thead>
                <tbody>
                  <tr>
                      <td>이름</td>
                      <td>${member.member_name}</td>
                  </tr>
                  <tr>
                      <td>전화번호</td>
                      <td>${member.member_phone}</td>
                  </tr>
                  <tr>
                      <td>이메일</td>
                      <td>${member.member_email}</td>
                  </tr>
                </tbody>
              </table>

              </div>
            
            <!--소비자 & 메이커 전환-->
            <div class="row mt-3">
              <div class="col">
                <button type="button" class="button-13" onclick="location.href='mypage.do'">서포터 페이지</button>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <button type="button" class="button-13" onclick="location.href='mypage2.do'">메이커 페이지</button>
              </div>
            </div>
            <div class="row">
              <div class="col">
                <button type="button" class="button-59" onclick="location.href='funding_register.do'"><span style="display : block;">새 펀딩 오픈</span></button>
              </div>
              <div class="col">
                <button type="button" class="button-59" onclick="location.href='<%=request.getContextPath()%>/store/store_register.do'"><span style="display : block;">새 스토어 오픈</span></button>
              </div>
            </div>
          </div>
            <div class="col-md-8 col-sm-12 scroll_item">
              

              <!--펀딩&스토어 전환버튼-->
              <ul class="nav nav-tabs nav-justified" style="padding:10px 0px;">
                <li class="nav-item" >
                  <a class="nav-link active" data-toggle="tab" href="#my_funding" style="font-weight: 600; padding:10px">내 펀딩</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-toggle="tab" href="#my_store" style="font-weight: 600; padding:10px">내 스토어</a>
                </li>
              </ul>

              <!--내비게이션 탭 내용-->
              <div class="tab-content" >
                <div class="tab-pane fade show active" id="my_funding">
                  <!--펀딩 스크롤-->
                  <div class="mydiv" id="mydiv">
					<c:if test="${sellerFundingList.size()>0}">
					<c:forEach var="item" items="${sellerFundingList}">
					
	                    <div class="card mb-3">
	                      <div class="row item" onclick="location.href='../funding/view.do?funding_idx=${item.funding_idx}'" style="cursor:pointer;">
	                        <div class="col-lg-5 col-md-6">
		                          <!--이미지-->
		                      <div class="card img-container">
	                            <div class="embed-responsive embed-responsive-4by3" style="margin-top:10px">
	                              <img src="../resources/upload/funding/${item.funding_thumbnail}" class="card-img-top embed-responsive-item" alt="funding_img">
	                            </div>
		                      </div>
	                        </div>
	                        <div class="col-lg-7 col-md-6">
	                          <div class="card-body" style="margin-left: -20px;">
	                            <div style="font-weight:100">
	                            <div>
	                            	<c:choose>
	                            		<c:when test="${ item.funding_category eq 0 }"><span style="font-weight: bold; color: grey;">강아지 용품</span></c:when>
	                            		<c:when test="${ item.funding_category eq 1 }"><span style="font-weight: bold; color: grey;">고양이 용품</span></c:when>
	                            		<c:when test="${ item.funding_category eq 2 }"><span style="font-weight: bold; color: grey;">다른 동물 용품</span></c:when>
	                            	</c:choose>
	                            </div>
	                            <div style="text-align: right;">
	                            	<c:choose>
	                            		<c:when test="${ item.funding_current_state eq 0 }"><span style="font-weight: bold">펀딩 진행중</span></c:when>
	                            		<c:when test="${ item.funding_current_state eq 1 }"><span style="font-weight: bold; color: blue">펀딩 성공</span></c:when>
	                            		<c:when test="${ item.funding_current_state eq 2 }"><span style="font-weight: bold; color: #4E342E">펀딩 실패</span></c:when>
	                            	</c:choose>
	                            </div>
	                            </div>
	                            <h5 class="card-title"style="font-weight: 600; margin-bottom: 10px;">${ item.funding_title }</h5>
	                            <p class="card-text" >
	                                          
	                              <div class="row" style="position: absolute; bottom: 10px; right:50px">
	                                <div class="col" style=" font-weight: 600; color:red;">${Math.round(item.funding_current_price/item.funding_target_price*100)}% 달성</div>              	
	                              </div>
	                            </p>
	                       	 </div>
	                        </div> 
	                      </div>
	                      <c:choose>
	                      	<c:when test="${item.funding_current_state == 0}">
	                      	  <div class="row">
	                      	  <!-- 펀딩 진행중 -->
							    <div class="col" align="right">
							    	<button class="btn button-6" onclick="Fnalert1(${item.funding_idx})">수량 추가</button>
							     	<button class="btn button-6" onclick="Fnalert2(${item.funding_idx})">제품 설명 변경</button>
							     	<button class="btn button-6" onclick="Fnalert3(${item.funding_idx})" style="margin-right: 10px">공지사항 변경</button> 
							    </div>
							 </div>
	                      	</c:when>
	                      	<c:when test="${item.funding_current_state == 1}">
	                      		<div class="row">
	                      			<!-- 펀딩 성공 시 -> 스토어 신청 가능버튼 나오게 -->
							    <div class="col" align="right">
							    	 <button class="btn btn-outline-info" onclick="StoreSubmit(${item.funding_idx})" style="margin-right: 10px">스토어로 제품 신청</button>
							    </div>
							    </div>
	                      	</c:when>
	                     	<c:otherwise>
	                     		<div class="row">
	                     			<div class="col">
							    	 	<span style="font-weight: bold; color: grey;">해당 제품은 펀딩에 실패하였습니다.</span>
							    	</div>
	                     		</div>
	                     	</c:otherwise>
	                      </c:choose>
	                    </div>
	                     <hr class="hrTag">
                    	<br>
                    </c:forEach>
                    </c:if>
                  </div>
                </div>
                
                <!--스토어 내용-->
                <div class="tab-pane fade" id="my_store">
                  <!--스토어 스크롤-->
                  <div class="mydiv" id="mydiv">
					<c:if test="${sellerStoreList.size()>0}">
					<c:forEach var="item" items="${sellerStoreList}">
                    <div class="card mb-3">
                      <div class="row g-0">
                        <div class="col-lg-5 col-md-6">
                          <!--이미지-->
	                      <div class="card img-container">
                            <div class="embed-responsive embed-responsive-4by3" style="margin-top:10px">
                              <img src="../resources/image/funding_main/${item.store_thumbnail}" class="card-img-top embed-responsive-item" alt="funding_img">
                            </div>
	                      </div>
                        </div>
                        <div class="col-lg-7 col-md-6">
                          <div class="card-body" style="margin-left: -20px;">
                            <div style="font-weight:100">
                            	<c:choose>
                            		<c:when test="${ item.store_category eq 0 }">강아지 용품</c:when>
                            		<c:when test="${ item.store_category eq 1 }">고양이 용품</c:when>
                            		<c:when test="${ item.store_category eq 2 }">다른 동물 용품</c:when>
                            	</c:choose>
                            </div>
                            <h5 class="card-title"style="font-weight: 600; margin-bottom: 10px;">${ item.store_title }</h5>
                            <p class="card-text" >
                              <div class="row">
                                <div class="col">${ item.store_content }</div>
                              </div>                             
                              <div class="row" >
                                <div class="col">
                                 
                                 	<div class="btn-group" role="group" aria-label="Button group with nested dropdown">
									 <button type="button" class="button-6" onclick="location.href='<%= request.getContextPath()%>/mypage/store_admin.do?store_idx=${item.store_idx}'">관리</button>
									
									  <div class="btn-group" role="group">
									    <button id="btnGroupDrop1" type="button" class="btn button-6 dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
									      Dropdown
									    </button>
									    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
									      <a class="dropdown-item"  href='javascript:void(0);' onclick="Storealert1(${item.store_idx})">수량 추가</a>
									      <a class="dropdown-item"  href='javascript:void(0);' onclick="Storealert2(${item.store_idx})">제품 설명 변경</a>
									       <a class="dropdown-item"  href='javascript:void(0);' onclick="Storealert3(${item.store_idx})">공지 사항 변경</a>
									    </div> 

									  </div>
									</div>
                                </div>
                              </div>
                            </p>
                          </div>
                        </div> 
                      </div>
                    </div>
                    </c:forEach>
                    </c:if>
            </div>
            </div>
            </div>
            </div>
        </div>
    </div>
</main>
<c:import url="/footer.do"></c:import>
</body>
</html>