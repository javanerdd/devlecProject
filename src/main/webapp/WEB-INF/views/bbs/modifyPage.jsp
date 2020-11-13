<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    
<%@ include file="../include/header.jsp" %>
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
          	<h3><i class="fa fa-angle-right"></i>게시글 수정</h3>
            
          		
			<!-- role  HTML5에서 새롭게 추가된 속성
				 ARIA(Accessible Rich Internet Application)라는 HTML5의 상세 규격 중 하나	 
				 시각 장애인이 컴퓨터의 리더기를 사용해서 웹 페이지를 읽을 때 "해당 부분이 form이다" FKRH WJDDML GOWNSMS RJT
				 role은 필수적인 효소는 아니지만 화면용 리더기를 이용해야하는 사람들에게도 
				 불편함이 없는 사이트를 제공하고자 하는 경우에 이 속성을 이용함.-->          	

          	
          	<div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  
                   <form  role="form" class="form-horizontal style-form" method="post">
                   <input type="hidden" name="page" value="${pCri.page}" />
                   <input type="hidden" name="numPerPage" value="${pCria.numPerPage }"/>
                   
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i>수정내용</h4>
                  	  
                  	     <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">글번호</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="bid" value="${bbsVO.bid}" readonly="readonly">
                              </div>
                          </div>
                  	  
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">제목</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="subject" value="${bbsVO.subject}" >
                              </div>
                          </div>
                          
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">내용</label>
                              <div class="col-sm-10">
                              	<textarea class="form-control" name="content" rows="4" >${bbsVO.content }</textarea>
                              </div>
                          </div>
                          
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">작성자</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="writer" value="${bbsVO.writer}">
                              </div>
                          </div>
                          
                    
                    </form>
                  </div><!-- form-panel -->
          			<div class="form-group">
          				<div class="col-sm-12" align="center">
          					<button type="submit" id="btn_save"class="btn btn-primary">저장하기</button>
          					<button type="submit" id="btn_cancle"class="btn btn-warning">취소</button>
          				</div>
                    </div>
                    
                    <script>
                    	// $는 jQUery의 의미
                    	//id=# ,class=. 으로 들어간다.
						$(document).ready(function(){
							var frmObj =$("form[role='form']")
							
							console.log("폼태그 입니다..");
							
							
							$("#btn_save").on("click",function(){
								frmObj.attr("action","/bbs/modifyPage") //수정처리 페이지 이동
								frmObj.submit();
							})
							
							$("#btn_cancle").on("click",function(){
// 								self.location = "/bbs/list";
								self.location = "/bbs/pageList?page=${pCri.page}&numPerPage=${pCri.numPerPage}";
							})
							
						})               	
                    	
                    	
                    </script>
                    
                    
                    
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->
          	

		</section> <!--/wrapper -->
      </section><!-- /MAIN CONTENT -->
<%@ include file="../include/footer.jsp"%>

