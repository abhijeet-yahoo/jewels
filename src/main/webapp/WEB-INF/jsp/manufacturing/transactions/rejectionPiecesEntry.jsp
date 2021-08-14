<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">

	<!-- <div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div> -->

	<div align="center">
		<span style="font-size: 18px;">Rejection Pieces Entry</span>
	</div>

	<div class="col-xs-12">
		<hr style="width: 100%; color: red; height: 2px; background-color: red;" />
	</div>


	<div class="form-group" id="recPeEnId">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div>
					
				
					
					
						<table id="tranMtId" data-toggle="table"
							data-side-pagination="server" data-pagination="true" data-search="true"
							data-height="450">
							<thead>
								<tr>
									<th data-field="stateRd" data-radio="true"></th>  
									<th data-field="party" data-sortable="true">Party</th>
									<th data-field="orderNo" data-align="left" data-sortable="true">Order No</th>
									<th data-field="bagNo" data-align="left" data-sortable="true">Bag No</th>
									<th data-field="purity" data-sortable="true">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="pcs" data-sortable="true">Quantity</th>
									<th data-field="recWt" data-sortable="true">Rec Wt</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


	

	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<form:form commandName="tranMt" id="addToBagMt"
					action="/jewels/manufacturing/transactions/rejectionPiecesEntry/add.html"
					cssClass="form-horizontal transferForm">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td class="col-xs-9">
									<input type="submit" value="Recast" class="btn btn-primary" id="recastSubmitBtn" />
									<input type="hidden" name="pODIds" id="pODIds" />
									<input type="hidden" name="id" />	
								</td>
							</tr>
						</tbody>
					</table> 
				
				</form:form>
			</div>
		</div>
	</div>



</div>
<!-- ending the main panel -->

<script type="text/javascript">
	$(document).ready(function(e) {
		popTranMt();

	});

	function popTranMt() {
		$("#tranMtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/rejectionPiecesEntry/tranMt/listing.html"
						});
	}
	
	
	$(document).on(
			'submit',
			'form#addToBagMt',
			function(e) {
				
				
				
				$('#recastSubmitBtn').attr('disabled', 'disabled');
				
				var data = $('#tranMtId').bootstrapTable('getSelections');
				
				var ids = $.map(data, function (item) {
						return item.bagId;
				});
				
				$('#pODIds').val(ids);
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
								
				if(! $('#pODIds').val() ){
					 displayMsg(event, this,'Please select Bag For Recast');
				}else{
					
					$.ajax({ 
						url : formURL,
						type : "POST",
						data : postData,
						
						 success : function(data, textStatus, jqXHR) {
							 if(data ==='-1'){
								 displayMsg(event, this,'Error :Contact Administrator');
								 
								 
							 }else{
								 
								 displayInfoMsg(event, this,'Bag Recast Sucessfully New BagNm= '+data);
								 	popTranMt();

								 
								 
							 }
							 
							 								 	
							 	$('#recastSubmitBtn').removeAttr('disabled');
						},
						error : function(jqXHR, textStatus, errorThrown) {
							alert("something went wrong please contact Administrator")
						} 
						
					});
				}
								
				e.preventDefault(); //STOP default action
			});
	
	


	
	
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript">
	
</script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet"
	type="text/css" />