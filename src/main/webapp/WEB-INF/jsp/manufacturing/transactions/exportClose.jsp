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
					<span style="font-size: 18px;">EXPORT CLOSE</span>
				</div>
				
				<div class="col-xs-12">
					<hr style="width: 100%; color: red; height: 2px; background-color: red;" />
				</div>
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				<form:form commandName="costingMt" id="costingMtId"
					action="/jewels/manufacturing/transactions/exportClose/transfer.html"
					cssClass="form-horizontal costingMtForm">
				
				
					<div class="row">
						<div class="col-xs-12">
						
							<div class="col-lg-3 col-sm-3">
								<label for="inputLabel3" class=".col-lg-2 text-right">Invoice No :</label>
							</div>
							<div class="col-lg-8 col-sm-8">
								<form:select path="id" cssClass="form-control">
									<form:option value="" label="- Select Invoice No -" />
									<form:options items="${costMtMap}" />
								</form:select>
							</div>
							
						</div>
					</div>
					
					
					<div class="row">
						<div class="col-xs-12">&nbsp;</div>
					</div>
					
					<div class="col-xs-12">
						<hr style="width: 100%; color: red; height: 2px; background-color: #FF0066;" />
					</div>
					
					<div class="row">
						<div class="col-xs-12">
							<input type="submit" value="Close" class="btn btn-primary" style="width: 2cm" />
							<input type="hidden" id="costMtId" name="costMtId" />
						
						</div>
					</div>
					
				
				
				</form:form>
				
				
				
				
				</div>
			</div>
		</div>

	
	
	
	
	
	
	
	
	</div> <!-- ending the panel body -->
	
</div> <!-- ending the main panel -->


<script type="text/javascript">

	$(document).ready(function(e){
		
		/* $("#invNo").autocomplete(
				{
					source : "<spring:url value='/manufacturing/transactions/invNo/autoList.html' />",
					minLength : 2
				});
		 */
		
		
		
	});

	
	
	
	$(document)
	 .on(
		'submit',
		'form#costingMtId',
		 function(e){
			
			$('#costMtId').val($('#id').val());
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
			$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
			
			/* displayNotificationMsg(this, null, 'Work in progress,Please Wait'); */
			
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {
		
					$("#modalRemove").modal("hide");
					
					if(data === '1'){
						$('#id').val('');
						 displayConfirmation(e,'javascript:pageRelod();','Notification','Export Closed Sucessfully','Continue');
					}else if(data === '-1'){
						displayMsg(this, null, 'Problem Occoured , please contact admin ! ');
					}else{
						displayMsg(this, null, 'Bags Not IN FG Process = '+data);
					}
					$.unblockUI();
					
				},
				
				error : function(data, textStatus, jqXHR){
					displayMsg(this, null, 'Error Occoured, please contact admin !');
					$.unblockUI();
				}
					
			})
			
			e.preventDefault();
	
	})


	
	function pageRelod(){
			window.location.reload(true);
		}
	
	


</script>





<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>










