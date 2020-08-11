<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<c:url value="purchaseList.jsp" var="purchaseCode">
	<c:param name="purchaseCode" value="${p.purchaseCode}" />
</c:url>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function() {
		var $reviewContentObj = $("div.reviewContent>input");
		$reviewContentObj.focus();

		// review content 남은 글자수
		$reviewContentObj.keydown(function(e) {
			var content = $(this).val();
			var count = 50 - content.length;
			if (content.length > 50) {
				$(this).val($(this).val().substring(0, 50));
				count = 0;
			}
			$('.remainText').html("남은글자수: " + count + "/50자");
		});

		// 화면으로부터 전달받은 데이터,구매정보 입력받은 후기 내용 등록 ( ajax )
		var reviewBtnObj = $('button.reviewBtn');
		reviewBtnObj.click(function() {

			var reviewContent = $reviewContentObj.val();
			$.ajax({
				url : '${contextPath}/review/add',
				method : 'POST',
				data : {
					"purchaseCode" : "${param.purchaseCode}",
					"reviewContent" : reviewContent
				},
				success : function(data) {
					if (data.status == 'success') {
						alert('후기등록 성공!');
						location.reload();
					} else {
						alert('후기등록 실패!');
					}
				}
			}); // 리뷰등록
		});
	}); // end of load
</script>
</head>

<body>
	<!-- Modal -->
	<form id="reviewAddForm">
		<div class="modal fade" id="reviewModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">후기등록</h4>
					</div>
					<div class="modal-body">
						<div class="reviewContent">
							<input id="content" type="text" value="" size="50"
								placeholder="후기를 입력해주세요"><br>
							<br> <span class="remainText">남은글자수 : 50/50자 </span>
						</div>
						<button type="button" class="reviewBtn" data-dismiss="modal">등록하긔</button>
					</div>
				</div>
			</div>
		</div>
	</form>


</body>
</html>