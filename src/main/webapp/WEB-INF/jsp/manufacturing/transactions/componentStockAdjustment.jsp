<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>



	<div class="panel-body">
	
		
	 <div class=row class="col-xs-12">
	
		<div class="col-xs-6">
			<div class="panel panel-primary" style="width:16.2.5cm; height:10.2cm" >
				<div class="panel-body">
				
					<div class="container-fluid">
						<div class="row">
				
						<div id="toolbarCompVadd">
							<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:loadData()">
								<span class="glyphicon glyphicon-download"></span>&nbsp; LOAD
							</a>						
						</div>
					
						<div>
							<table class="table-responsive" id="compVaddList"
								data-toggle="table" data-toolbar="#toolbarCompVadd"
								data-side-pagination="server" data-click-to-select="true"
								data-select-item-name="radioNameCompVadd"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="320">
								<thead>
									<tr class="btn-primary">
										<th data-field="state" data-radio="true"></th>
										<th data-field="metal" data-sortable="false">Metal</th>
										<th data-field="compName" data-sortable="false">Component</th>
										<th data-field="purity" data-sortable="false">Purity</th>
										<th data-field="color" data-sortable="false">Color</th>
										<th data-field="purityConv" data-sortable="false">PurityConv</th>
										<th data-field="metalWt" data-sortable="false">Weight</th>
										<th data-field="compPcs" data-sortable="false">CompPcs</th>
										<th data-field="adjusted" data-sortable="false">Adjusted</th>
										<th data-field="action2" data-sortable="false">Delete</th>
									</tr>
								</thead>
							</table>
						</div>
					
					</div>
				</div>
				
				
				
				</div>
			</div>
		</div>
		
		
		
		<div class="col-xs-6">
	
			<div class="panel panel-primary" style="width:16.2.5cm; height:10.2cm">
							
					<div class="panel-body">
				
						<div class="container-fluid">
							<div class="row">
							
					
							<div id="toolbarCompAdj">
								<a  style="font-size: 15px;color: red" >
									<span></span>&nbsp; Adjustment Details
								</a>		
							</div>
							
							
						
							<div>
								<table class="table-responsive" id="compAdjList"
										data-toggle="table" data-toolbar="#toolbarCompAdj"
								data-side-pagination="server" data-click-to-select="true"
								data-select-item-name="radioNameCompAdj"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="320">
									<thead>
										<tr class="btn-primary">
											<th data-field="state" data-radio="true"></th>
											<th data-field="invNo" data-sortable="false">Inv No</th>
											<th data-field="invDate" data-sortable="false">Inv Date</th>
											<th data-field="componentName" data-sortable="false">Component</th>
											<th data-field="purity" data-sortable="false">Purity</th>
											<th data-field="color" data-sortable="false">Color</th>
											<th data-field="purityConv" data-sortable="false">PurityConv</th>
											<th data-field="adjustmentWt" data-sortable="false">Adjusted Wt</th>
											<th data-field="adjustmentPcs" data-sortable="false">Adjusted Pcs</th>
											
										</tr>
									</thead>
								</table>
							</div>
						
						</div>
					</div>
					
					
					
				</div>
				
					
					
					
			</div>
	
		</div>
		
					<div class="col-sm-10">
					
						
					<span style="display:inline-block; width: 4cm;"></span>
					Total Weight : <input type="text" id="vTotalWt" name="vTotalWt" value="${totalWt}" size="7" disabled="true" />		
				
					
					
						<span style="display:inline-block; width: 12cm;"></span>
						Total Adj Weight : <input type="text" id="vTotalAdjWt" name="vTotalAdjWt" value="${totalAdjWt}" size="7" disabled="true" />		
					</div>
					
					<div class="col-sm-2">
						<button class="btn btn-danger" id="clearAllCompAdjustment" onclick="javascript:popDeleteAllCompAdjustment()">Clear All Adjustment</button>
						</div>
				
	
	</div>
			
			
	<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
			
			
	<!-- //---------//-----------// -->
	
	<div class=row class="col-xs-12">
	 	
	 			<div class="form-group" id="dsPId">
	 				<div class="container-fluid">
							<div class="row">
						
							<div>
								<table class="table-responsive" id="compPurcListId"
									data-toggle="table" data-toolbar="#toolbarInw"
									data-side-pagination="server" data-pagination="false"
									data-page-list="[5, 10, 20, 50, 100, 200]" data-height="320">
									<thead>
										<tr class="btn-primary">
											<th data-field="state" data-checkbox="true"></th>
											<th data-field="invNo" data-sortable="false">Inv No</th>
											<th data-field="invDate" data-sortable="false">Inv Date</th>
											<th data-field="component" data-sortable="false">Comp Name</th>
											<th data-field="purity" data-sortable="false">Kt</th>
											<th data-field="color" data-sortable="false">Color</th>
											<th data-field="qty" data-sortable="false">Qty</th>
											<th data-field="metalWt" data-sortable="true">Metal Wt</th>
											<th data-field="balance" data-sortable="true">Balance Wt</th>
											
										</tr>
									</thead>
								</table>
							</div>
						
						</div>
					</div>
				</div>
	 	
	 </div>
	 
	 <div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		
	<div class="row" class="col-xs-12">
				<div class="form-group">
					<form:form commandName="vAddCompAdj" id="saveCompAdjustmentBtn"
							action="/jewels/manufacturing/transactions/vAddCompAdj/add.html"
							cssClass="form-horizontal saveCompAdjustmentBtn">
							
											<!-- <input type="button" id="fifoBtn" value="FIFO"  disabled="true" class="btn btn-info" onclick="javascript:doFifo()" style="width: 2.5cm"/> -->
											<input type="submit" value="Save Adjustment" id="saveCompAdj" disabled="true"  class="btn btn-primary" style="width: 4cm" />
											<input type="hidden" id="tempCompPurcId" name="tempCompPurcId" />
											<input type="hidden" id="adjustWtforComp" name="adjustWtforComp" />
											<input type="hidden" id="costMtIdPkforComp" name="costMtIdPkforComp" />
											<input type="hidden" id="vAddCompInvPkId" name="vAddCompInvPkId" />
										     <input type="hidden" id="totCompPcs" name="totCompPcs" />
											<input type="hidden" id="totAdjCompWt" name="totAdjCompWt" />
							
								</form:form>
				</div>
	
	</div>
		 <input type="hidden" id="vAddMtId" name="vAddMtId" />
		 <input type="hidden" id="vAddCompInvPk" name="vAddCompInvPk" />
	
	
	</div> <!-- ending the panel body -->
	
	
	

<script type="text/javascript">
	
	$(document).ready(function(){
		
		if(window.location.href.indexOf("edit") != -1){
			
			var strUrl = window.location.href;
			var tempVAddMtPk = strUrl.substring(window.location.href.indexOf("edit")+5);
			var vAddMtPk = tempVAddMtPk.split(".");
			$('#vAddMtId').val(vAddMtPk[0]);

		}
		
		
 })
	
	
	
	function loadData(){
		
		 $.ajax({
				url : "/jewels/manufacturing/transactions/add/vAddCompInv.html?&vAddMtId="+$('#vAddMtId').val(),
				type : 'GET',
				success : function(data) {
					
					displayMsg(this, null, 'Data Loaded Sucessfully');	
					popCompVadd();
					
				}
			});
		
		
	}
	
	
	function popCompVadd(){
		
		$("#compVaddList")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/vAddCompStockAdjust/listing.html?&vAddMtId="+$('#vAddMtId').val(),
				});
		
	}
	
	
	var vmetalid;
	var vcomponentid;
	var vpurityid;
	var vcolorid;
	var vCompInvId;
	var vMetalwt;
	var vCompPcs;
	var tempAdjustCompValue;
	$('#compVaddList').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				
				
				vCompInvId = jQuery.parseJSON(JSON.stringify(row)).id;
				vmetalid = jQuery.parseJSON(JSON.stringify(row)).metalid;
				vcomponentid = jQuery.parseJSON(JSON.stringify(row)).componentid;
				vpurityid = jQuery.parseJSON(JSON.stringify(row)).purityid;
				vcolorid = jQuery.parseJSON(JSON.stringify(row)).colorid;
				vMetalwt = jQuery.parseJSON(JSON.stringify(row)).metalWt;
				vCompPcs = jQuery.parseJSON(JSON.stringify(row)).compPcs;
				
				tempAdjustCompValue =  jQuery.parseJSON(JSON.stringify(row)).adjusted;
				
				if(tempAdjustCompValue === 'Yes'){
					$('#saveCompAdj').prop('disabled',true);	
				}else{
					$('#saveCompAdj').prop('disabled',false);
				}
				
				
				setTimeout(function(){
					popCompPurcList();
					popCompAdjListing();
				}, 500);
					
			
				
			})
			
			
			
		$('#compVaddList').bootstrapTable({}).on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#compVaddList").bootstrapTable('getData'));
				
				var vWt = 0.0;
				$.each(JSON.parse(data), function(idx, obj) {
					vWt		+= Number(obj.metalWt);
				});
				
				$('#vTotalWt').val(" " + parseFloat(vWt).toFixed(3));
				
			});
	
	
			
	//--------Component purchase listing table 3--//-------//
	
	
	function popCompPurcList(){
		
		$('#saveCompAdj').removeAttr('disabled');
		$('#fifoBtn').removeAttr('disabled');
		
		$('#compPurcListId').bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/transactions/fromCompPurcList/listing.html?&metalid="+vmetalid+"&componentid="+vcomponentid+"&purityid="+vpurityid+"&colorid="+vcolorid,
				})
		
		
	}
	
	
	function getNumVal(dt) {
		if (typeof dt === 'undefined') {
		} else {
			dt = dt.substring(dt.indexOf("<strong>") + 8, dt
					.indexOf("</strong>"));
		}

		return dt;
	}
	
	
	var stnTblRow = -1;
	$('#compPurcListId').bootstrapTable({}).on('click-row.bs.table',
			function(e, row, $element) {
				
				$('#totBalanceToAdj').val(getNumVal(jQuery.parseJSON(JSON.stringify(row)).balance));
				$('#fldTrfWeight').val(getNumVal(jQuery.parseJSON(JSON.stringify(row)).adjWt));
				stnTblRow = $element.attr('data-index');
				$('#fldTrfWeight').focus();
			});

	
		
		$('#fldTrfWeight').on('blur',
			function(e) {
		
					if(Number($('#fldTrfWeight').val()) > Number($('#totBalanceToAdj').val())){
						displayMsg(this, null, 'Adjusted Wt cannot be  greater than balance Wt');
					}else{
						
						$("#compPurcListId").bootstrapTable('updateRow', {
							index : stnTblRow,
							row : {
								state : true,
								adjWt : (Number($('#fldTrfWeight').val()).toFixed(3)),

							}
						});
						
						updateTotWt();
	
					}
				
			});
	
	
	
	function updateTotWt(){

		var data = $('#compPurcListId').bootstrapTable('getData');
		var vAdjWt = $.map(data, function(item) {
			return item.adjWt;
		});
		
		var totAdjWt = 0;
		for (i = 0; i < data.length; i++) {
			totAdjWt += Number(vAdjWt[i]);
		}

		$('#vTotalTranAdjWt').val(totAdjWt.toFixed(3));
		
	}

	
	//----------fifo//---//
	
	
	function doFifo(){
		
		
		var totWeight = Number($('#totMetalWtForAdj').val());
		
		var data = $('#compPurcListId').bootstrapTable('getData');
		
		var vBalance = $.map(data, function(item) {
			return item.balance;
		});
		
		var vAdjWt = $.map(data, function(item) {
			return item.adjWt;
		});
		
		var tempTotWeight = totWeight;
		
	
		for (i = 0; i < data.length; i++) {
			
			
			
			if(tempTotWeight === 0){
				break;
			}
			
				var totBalance  = Number(vBalance[i]);
				var totAdjWt 	= Number(vAdjWt[i]);
				
				if(totBalance <= tempTotWeight){
					
					$("#compPurcListId").bootstrapTable('updateRow', {
						index : i,
						row : {
							state : true,
							adjWt :totBalance.toFixed(3),
	
						}
					});
					
					tempTotWeight = tempTotWeight-totBalance;
					
				 }else{
					 
					 $("#compPurcListId").bootstrapTable('updateRow', {
							index : i,
							row : {
								state : true,
								adjWt :tempTotWeight.toFixed(3),
		
							}
						});
					 
					 tempTotWeight = 0;
					 
				 }
			
			
		}

		
		updateTotWt();
		
		
		
		
	}
	
	
	
	
	
	//-------save-----//--------//
	
	
	
	 $(document)
		.on(
			'submit',
			'form#saveCompAdjustmentBtn',
			 function(e){
				
					var data = $('#compPurcListId').bootstrapTable('getSelections');
					
					var vdtids = $.map(data, function(item) {
						return item.id;
					});
					
					
					
					$('#tempCompPurcId').val(vdtids);
					$('#costMtIdPkforComp').val($('#vAddMtId').val());
					$('#vAddCompInvPkId').val(vCompInvId);
					$('#totCompPcs').val(vCompPcs);
					$('#totAdjCompWt').val(vMetalwt);
					
					
					var postData = $(this).serializeArray();
					var formURL = $(this).attr("action");
					
					$.ajax({
						url : formURL,
						type : "POST",
						data : postData,
						success : function(data, textStatus, jqXHR) {
							
							if(data === '1'){
								popCompVadd();
								popCompPurcList();
								popCompAdjListing(); 
								$('#saveCompAdj').prop('disabled',true);
								displayInfoMsg(this, null, 'Adjustment Saved !');
							
							}else if(data === '-3'){
								displayMsg(this, null, 'Please Select atleast one record');
							}else if(data === '-2'){
								displayMsg(this, null, 'Data Alredy Adjusted');
								
							}else{
								
							}
							
						},
						
						error : function(data, textStatus, jqXHR){
							alert("ERROR");
						}
							
					})
					
					e.preventDefault();
					
				
		
	}); 
	
	
	//-------------right table ---no 2--//--------------//

	
	function popCompAdjListing(){
		
		$('#compAdjList').bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/transactions/vAddCompAdj/listing.html?&vAddCompInvId="+vCompInvId,
				})
		
	}
	
	
	
	 $('#compAdjList').bootstrapTable({}).on(
				'load-success.bs.table',
				function(e, data) {
					var data = JSON.stringify($("#compAdjList").bootstrapTable('getData'));
					
					var vAdjWt = 0.0;
					$.each(JSON.parse(data), function(idx, obj) {
						vAdjWt		+= Number(obj.adjustmentWt);
					});
					
					$('#vTotalAdjWt').val(" " + parseFloat(vAdjWt).toFixed(3));
					
				});
	
	
	//---delete ---//---//
	
	
	function popDeleteCompAdjustment(vAddCompInvId){
		
		$("#modalRemove").modal("hide");
		
		  $.ajax({
				url : "/jewels/manufacturing/transactions/vAddCompInv/delete/"+vAddCompInvId+ ".html",
						
				type : 'GET',
				success : function(data) {
					popCompVadd();
					popCompAdjListing();
					popCompPurcList();
					
				}
			});  
		
		 
	}
	
	
	function popDeleteAllCompAdjustment(){
		
		  $.ajax({
				url : "/jewels/manufacturing/transactions/vAddCompInv/deleteAllCompAdjument.html?&mtId="+$('#vAddMtId').val(),
						
				type : 'GET',
				success : function(data) {
					
					setTimeout(function(){
					
						popCompVadd();
						popCompAdjListing();
						popCompPurcList();
					
					}, 300);
					
				}
			});  
	
	}
	

 </script>


	<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

	<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

	<script src="/jewels/js/common/common.js"></script>

	<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

	<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

	<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
	