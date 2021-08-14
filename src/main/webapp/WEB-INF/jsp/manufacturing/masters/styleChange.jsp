<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel" style="width: 100%">
	<div class="panel-body">


		<div align="center">
			<div class="panel panel-primary" style="width: 50%">
				<div class="panel-body">
				
				<div align="center">
					<span style="font-size: 18px;">Style Change</span>
				</div>
				
				<div class="col-xs-12">
					<hr style="width: 100%; color: red; height: 2px; background-color: red;" />
				</div>
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				<form:form commandName="styleChange" id="styleChangeId"
					action="/jewels/manufacturing/masters/styleChange/add.html"
					cssClass="form-horizontal styleChangeForm">
				
				
					<div class="row">
						<div class="col-xs-12">
						
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Design No :</label>
							</div>
							<div class="col-lg-5 col-sm-5">
								<form:input path="designNo" cssClass="form-control"  />
								<form:errors path="designNo" />
							</div>
							
							
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>
					
					
					<div class="row">
						<div class="col-xs-12">
						
						
						
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">New Style No :</label>
							</div>
							<div class="col-lg-5 col-sm-5">
								<form:input path="newStyleNo" cssClass="form-control"  />
								<form:errors path="newStyleNo" />
							</div>
							
							
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>
					
					<div class="col-xs-12">
						<hr style="width: 100%; color: red; height: 2px; background-color: skyblue;" />
					</div>
					
					<div class="row">
						<div class="col-xs-12">
							
							<input type="submit" value="update" class="btn btn-primary" style="width: 2cm" />
						
						</div>
					</div>
					
				
				
				</form:form>
				
				
				
				
				</div>
			</div>
		</div>



	</div>
</div>



<script type="text/javascript">


$(document).ready(function(){

	
	
	$("#designNo").autocomplete({
		source : "/jewels/manufacturing/masters/designNo/forStyleChangeModel.html",
		minLength : 2
	});
	
})



$(document)
		.on(
			'submit',
			'form#styleChangeId',
			 function(e){
				
				
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
				
				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR) {
						
						//alert(data);
						
						if(data === "-2"){
							displayMsg(this, null, 'Style No Already Exists');
						}else if(data === "-3"){
							displayMsg(this, null, 'Please Enter Valid Design No');
						}else{
							displayMsg(this, null, 'Style No Updated Sucessfully');
							$('#designNo').val('');
							$('#newStyleNo').val('');
							
						}
						
						
					},
					
					error : function(data, textStatus, jqXHR){
						
					}
						
				})
				
				e.preventDefault();
		
	})
	







</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/design.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />












