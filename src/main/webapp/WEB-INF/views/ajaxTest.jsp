<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Reply REST+Ajax Test</title>
<!-- jquery 1.8 -->
<script src="/resources/assets/js/jquery-1.8.3.min.js"></script>


<style>
#modifyDiv {
	width: 500px;
	height: 100px;
	background-color: gray;
	position: absolute;
	top: 20%;
	left: 30%;
	padding: 20px;
	z-index: 100;
}
</style>



</head>
<body>
	<h3>Reply REST+Ajax Test</h3>

	<div id="modifyDiv" style="display: none;">
		<span class="title-dialog" size="60"></span>번 댓글
		<div>
			<input type="text" id="reContent" size="60" />
		</div>

		<div>
			<button type="button" id="reModifyBtn">수정</button>
			<button type="button" id="reDelBtn">삭제</button>
			<button type="button" id="closeBtn">닫기</button>
		</div>

	</div>


	<div>
		<div>
			작성자: <input type="text" name="reply" id="writer" />
		</div>

		<div>
			&nbsp;&nbsp;댓 글: <input type="text" size="50" name="replyContent"
				id="addreContent" />
		</div>

		<br /> <br />
		<button id="submitBtn">댓글 작성</button>
	</div>


	<h4>댓글 리스트</h4>



	<ul id="reply">

	</ul>

	<script type="text/javascript">

	var bid = 1;
	function reListAll(){
	
	$.getJSON("/replies/selectAll/"+bid,function(data){
// 		console.log(data.length);
		var str="";
		
		$(data).each(function(){
			str +="<li data-rebid='" +this.rebid+"' class=' reList'>"
				+this.rebid+" | " +this.replyContent
				+"&nbsp;<button>수정</button>"
				+"</li>"
		});
		$("#reply").html(str);
	});
	} //reListAll()
	
	
	$("#reply").on("click",".reList button", function(){
		var li = $(this).parent();
		
		var rebid = li.attr("data-rebid");
		var reContent = li.text();
		
		//alert("댓글번호: "+rebid+"   수정할 내용:"+reContent);
		$(".title-dialog").html(rebid);
		$("#reContent").val(reContent);
		$("#modifyDiv").show("slow");
	});
	
	
	
	$("#submitBtn").on("click",function(){
		var reWriter = $("#writer").val();
		var reContent = $("#addreContent").val();
		
		$.ajax({
			type:'post',
			url:'/replies',
			headers:{
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" :"POST"
			},
			
			
			dataType:'text',
			data:JSON.stringify({
				bid : bid,
				replyer : reWriter,
				replyContent : reContent
			}),
			success:function(result){
				if(result=='success'){
					alert("댓글 등록 성공!!!")
					
					reListAll();
				}
			}
		})
	});
	
	
	//삭제처리
	$("#reDelBtn").on("click",function(){
		var rebid= $(".title-dialog").html();
		var reContent = $("#reContent").val();
		
		
		$.ajax({
				
			type:'delete',
			url:'/replies/'+rebid,
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override" : "DELETE"
				
			},
			
			dataType:'text',
			success:function(result){
				console.log("result:"+result);
				
				if(result=='success'){
					alert("삭제성공!!");
					
					$("#modifyDiv").hide("slow");
					reListAll();
				}
			}
		});//ajax
	
	});
	
	
	//수정처리	
	$("#reModifyBtn").on("click",function(){
		var rebid= $(".title-dialog").html();
		var reContent = $("#reContent").val();
		
		
		$.ajax({
				
			type:'put',
			url:'/replies/'+rebid,
			headers:{
				"Content-Type":"application/json",
				"X-HTTP-Method-Override" : "PUT"
				
			},
			data:JSON.stringify({replyContent:reContent}),
			
			dataType:'text',
			success:function(result){
				console.log("result:"+result);
				
				if(result=='success'){
					alert("수정성공!!");
					
					$("#modifyDiv").hide("slow");
					reListAll();
				}
			}

		});//ajax
	});	
	
	


	

</script>

</body>
</html>