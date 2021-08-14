<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
	
</style>


    <div class="modal fade" id="myShowConversionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Loose Conversion Details</h4>
			</div>

			<div class="modal-body">
			
			
	<div class="container-fluid">
		<div class="row">
		
		<div id="toolbarLoose">
						<a class="btn btn-danger" style="font-size: 15px" type="button"
							onclick="javascript:deleteLooseConv();"> <span
							class="glyphicon glyphicon-trash"></span>&nbsp;Delete
						</a>
					
					
					</div>
				
					<table id="looseConvTblDtId" data-toggle="table" data-toolbar="#toolbarLoose" data-search="true"
							data-height="350" data-striped="true" data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-checkbox="true">Select</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-align="left">Shape</th>
									<th data-field="quality" data-sortable="true">Quality</th>
									<th data-field="sieve" data-sortable="true">Sieve</th>
									<th data-field="size" data-sortable="true">Size</th>
									<th data-field="sizeGroupStr" data-sortable="true">Size Group</th>
									<th data-field="stone" data-sortable="true">Stone</th>
									<th data-field="carat" data-sortable="true">Carat</th>
									<th data-field="rate" data-sortable="true">Rate</th>
									<th data-field="amount" data-sortable="true">Amount</th>
									
								</tr>
							</thead>
						</table>
				
			</div>	
		</div>
		
		

			
			
			<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="hidden" id="looseConvId" name="looseConvId" />
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
			
<script type="text/javascript">

$(document).ready(
		function(e) {
			
	
			
			
		});
		
		
	
	function deleteLooseConv(){

		console.log('xxx   '+ $('#looseConvId').val());

		var data = $('#looseConvTblDtId').bootstrapTable('getAllSelections');
		var convDtId = $.map(data, function(item) {
			return item.id;
		});

		if(convDtId !=''){
			
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
			
			$.ajax({
				url : "/jewels/manufacturing/transactions/looseStnConv/deleteConvDt.html?convDtId="+convDtId,
				type : "POST",
				success : function(data, textStatus, jqXHR) {
					
					$.unblockUI();
					
					if (Number(data) == 1) {

						displayMsg(event, this,'Record delete sucessfully !');
					
						
					}else{
						
						displayMsg(event, this,"Can Not Delete, Stock Adjusted In Inward");
						
					
					}			
					popShowConversionDt($('#looseConvId').val());
					
					

				},
				error : function(jqXHR, textStatus, errorThrown) {

				}

			}); 
			
			}else{

				displayMsg(event, this,"Record Not Selected");
				} 
		
		}	
		


</script>			
			

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>			
			
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>