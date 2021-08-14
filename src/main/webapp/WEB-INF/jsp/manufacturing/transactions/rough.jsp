<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>



<div class="container">

	<div class="row">
	
		<div id="asd">
	
			<div class="col-sm-4  thumbnail">
				<a href="">
				<img src="/jewels/uploads/manufacturing/design/irinaShyak.jpg">
				<p>hello</p>
				</a>
			</div>
			
			<div class="col-sm-4  thumbnail">
				<img src="/jewels/uploads/manufacturing/design/irinaShyak.jpg">
				<p>hello</p>
			</div>
			
				<div class="col-sm-4  thumbnail">
				<img src="/jewels/uploads/manufacturing/design/irinaShyak.jpg">
				<p>hello</p>
			</div>	
		
		</div>
	
	</div>


</div>


<div class="row">

<input type="button" id="btn" onclick="javascript:popA();">

</div>






<script type="text/javascript">



function popA(){
	
	
	/* 	var str = "<div class="col-sm-4  thumbnail">
					<a href="">
					<img src="/jewels/uploads/manufacturing/design/irinaShyak.jpg">
					<p>hello</p>
					</a>
				</div>"
	
				
		alert(str);	 */	
		
		for(i=1;i<=10;i++){
				
			$('#asd').append('<div class="col-sm-4  thumbnail"><a href=""><img src="/jewels/uploads/manufacturing/design/irinaShyak.jpg"><p>hello</p></a></div>');
		}
	
}


</script>


















<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">
<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

