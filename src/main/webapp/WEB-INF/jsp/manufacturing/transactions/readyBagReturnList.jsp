<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>


<style>
.bacground{
bgcolor:"red";
}

.mySelected{
    background-color: #FFB6C1;
}

</style>




<div class="panel panel-primary">
	
	
	<div class="panel-heading" id="barcodeDivId">
		<label class="col-lg-5 text-left">
				<span style="font-size: 18px;">Ready Bag Return</span>
			</label>
		<div class="text-right">
		<a class="btn btn-xs btn-info" id="readyBagRetBackBtnId" style="font-size: 14px" type="button" onclick="javascript:readyBagRetBackBtn()"><b>Back</b></a>
		</div>	
		</div>
				
		
	</div>
	
	<div class="panel-body">

			<div class="row">
						
						


				<div class="table-responsive">
 					<table id="readyBagRetListtblId" data-toggle="table" data-search="true" data-toolbar="toolbarDt" data-maintain-meta-data="true" data-height="450"  >
					<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="partyCode" data-align="left" data-sortable="true" data-filter-control="input">Party</th>
								<th data-field="orderNo" data-align="left" data-sortable="true" data-filter-control="input">Order</th>
								<th data-field="styleNo" data-align="left" data-sortable="true" data-filter-control="input">Style No</th>
								<th data-field="purity" data-align="left" data-sortable="true" data-filter-control="input">Purity</th>
								<th data-field="color" data-align="left" data-sortable="true" data-filter-control="input">Color</th>
								<th data-field="bagNm" data-align="left" data-sortable="true">Bag</th>
								<th data-field="bagQty" data-align="left" data-sortable="true">Bag Qty</th>
								<th data-field="stone" data-align="left" data-sortable="true">Stone</th>
								<th data-field="carat" data-align="left" data-sortable="true">Carat</th>
							
							</tr>
						</thead>


					</table>
				<div class="row">&nbsp;
				</div>	
			
				</div>
				
				</div>
				
				<div class="row">
	<div class="form-group">
		<div class="col-xs-12">
			<form:form commandName="readyBagRetDt" id="returnToReadyBagRetDt"
				action="/jewels/manufacturing/transactions/readyBagRetDt/add.html"
				cssClass="form-horizontal transferForm">

				<table class="table table-bordered">
					<tbody>
						<tr>
							<td class="col-xs-1">
								<input type="submit" value="Return" class="btn btn-primary" id="returnBtnToReadyBagRetDt" /> 
								<form:input type="hidden" path="id" />							
								<input type="hidden" name="readyBagRetMtId" id="readyBagRetMtId" />
							</td>
							<td class="col-xs-11">
								<input type ="hidden" name="pBagIds" id="pBagIds"/>
							</td>												
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
	</div>
</div>
						 
		</div>
		
	</div>		
	
	
	
	<script type="text/javascript">


	var readyBagRetMtId;
	$(document)
	.ready(
			function(e) {
	if (window.location.href.indexOf('mtId') != -1) {

								var abcx = window.location.href;
								readyBagRetMtId = abcx.substring(window.location.href
										.indexOf('mtId') + 5);
								
								vOpt = abcx.substring(window.location.href
										.indexOf('&mOpt') + 6); 

								$('#readyBagRetMtId').val(readyBagRetMtId);
							
							}
				
				
				
				popBagDetails();

				
				
			});



	function popBagDetails(){

		
					
					$("#readyBagRetListtblId")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/returnReadyBagTrf/listing.html?mtId="+readyBagRetMtId,
									
							}); 
		
	} 


	function readyBagRetBackBtn(){
		window.location.href = "/jewels/manufacturing/transactions/readyBagRetMt/edit/"+readyBagRetMtId+".html"
		}

	


	$(document).on(
			'submit',
			'form#returnToReadyBagRetDt',
		function(e) {
			
			$('#returnBtnToReadyBagRetDt').attr('disabled', 'disabled');
			
			var data = $('#readyBagRetListtblId').bootstrapTable('getSelections');
		
			var ids = $.map(data, function(item) {
				return item.bagId;
			});
	
			$('#pBagIds').val(ids);
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
							
		 	$.ajax({ 
				url : formURL,
				type : "POST",
				data : postData,
				
				 success : function(data, textStatus, jqXHR) {	
					 
					 if(data == 1){
						 popBagDetails();
						 displayInfoMsg(this, null, 'RECORD TRANSFERED SUCESSFULLY !');
						 
					}else{
						 displayMsg(this,null,data);	
					}
					 

					 $('#returnBtnToReadyBagRetDt').removeAttr('disabled');
				
				},
				error : function(jqXHR, textStatus,
						errorThrown) {
					
					
				} 
				
			}); 
			e.preventDefault();  //STOP default action
		});


	</script>	
	
	


	<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

	<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

	<script src="<spring:url value='/js/common/common.js' />"></script>

	<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

	<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />	
	<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
	