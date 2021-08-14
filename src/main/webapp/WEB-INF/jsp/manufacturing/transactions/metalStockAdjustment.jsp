<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>




<div class="panel-body">
	
<div class=row class="col-xs-12">
	
	
		<div class="col-xs-6">
			<div class="panel panel-primary" style="width:16.2.5cm; height:10.6cm">
				<div class="panel-body">
				
					<div class="container-fluid">
						<div class="row">
				
						<div id="toolbarMetalVaddDt">
							<a class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:loadMetalData()">
								<span class="glyphicon glyphicon-download"></span>&nbsp; LOAD
							</a>						
						</div>
					
						<div>
							<table class="table-responsive" id="metalVaddList"
								data-toggle="table" data-toolbar="#toolbarMetalVadd"
								data-side-pagination="server" data-click-to-select="true"
								data-select-item-name="radioNameMetalVadd"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="320">
								<thead>
									<tr class="btn-primary">
										<th data-field="state" data-radio="true"></th>
										<th data-field="metalName" data-sortable="false">Metal</th>
										<th data-field="netWt" data-sortable="false">Net Wt</th>
										<th data-field="pure" data-sortable="false">Pure</th>
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
		
		
		
		<div class="col-xs-6" >
	
			<div class="panel panel-primary" style="width:16.2.5cm; height:10.6cm">
							
					<div class="panel-body">
				
						<div class="container-fluid">
							<div class="row">
							
							<div class="row">&nbsp;</div>
							
							<div id="toolbarMettAdj">
								<a  style="font-size: 15px;color: red" >
									<span></span>&nbsp; Adjustment Details
								</a>
									
							</div>
							
						
							<div>
								<table class="table-responsive" id="metalAdjList"
								data-toggle="table" data-toolbar="#toolbarMetalAdj"
								data-side-pagination="server" data-click-to-select="true"
								data-select-item-name="radioNameMetalAdj"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="320">
									<thead>
										<tr class="btn-primary">
											<th data-field="state" data-radio="true"></th>
											<th data-field="invNo" data-sortable="false">Inv No</th>
											<th data-field="invDate" data-sortable="false">Inv Date</th>
											<th data-field="adjustmentWt" data-sortable="false">Adjusted Wt</th>
											<th data-field="rate" data-sortable="false">Rate</th>
											<th data-field="in999" data-sortable="false" data-formatter="inputFormatter">In 999</th>
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
					Total Pure : <input type="text" id="vTotalPure" name="vTotalPure" value="${totalPure}" size="7" disabled="disabled" />	
					
					<span style="display:inline-block; width: 8cm;"></span>
						Total Adj Pure : <input type="text" id="vTotalAdjPure" name="vTotalAdjPure" value="${totalAdjPure}" size="7" disabled="disabled" />		
				</div>
				
				<div class="col-sm-2">
						<button class="btn btn-danger" id="clearAllMetalAdjustment" onclick="javascript:popDeleteAllMetalAdjustment()">Clear All Adjustment</button>
						</div>
					
	
	</div>
	
	
	
	
	
	<!-- //---------//-----------// -->
	
	
 <div id="adjIdDownList" style="display:none">
	
	<div class=row class="col-xs-12">
	 	
	 			<div class="form-group" id="dsMetPId">
	 				<div class="container-fluid">
							<div class="row">
						
							<div>
								<table class="table-responsive" id="metalInwListId"
									data-toggle="table" data-toolbar="#toolbarMetalInw" data-search="true"
									data-side-pagination="server"  data-pagination="true" data-checkbox-header="false" data-single-select="true" data-click-to-select="true"
									data-page-list="[5, 10, 20, 50, 100, 200]" data-height="320" >
									<thead>
										<tr class="btn-primary">
											<th data-field="state" data-checkbox="true"></th>
											<th data-field="invNo" data-sortable="false">Inv No</th>
											<th data-field="invDate" data-sortable="false">Inv Date</th>
											<th data-field="balance" data-sortable="true">Balance Wt</th>
											<th data-field="rate" data-sortable="true">Rate</th>
											<th data-field="in999" data-sortable="false" data-formatter="inputFormatter">In 999</th>
											
											<!-- <th data-field="adjWt" data-sortable="true">Adjustment Wt</th> -->
										</tr>
									</thead>
								</table>
							</div>
						
						</div>
					</div>
				</div>
				
	 	
	 </div>
			
	<div class="row" class="col-xs-12">
					<div class="form-group">
						<form:form commandName="vAddMetalAdj" id="saveAdjustmentMetalBtn"
								action="/jewels/manufacturing/transactions/vAddMetalAdj/add.html" 
								cssClass="form-horizontal saveAdjustmentMetalBtn">
								
							
												<input type="submit" value="Save Adjustment" id="saveMetalAdj" name="saveMetalAdj" disabled="disabled" 
												 class="btn btn-primary" style="width: 4cm"/>
											    <input type="hidden" id="tempMetalInwId" name="tempMetalInwId" />
												<input type="hidden" id="adjustWt" name="adjustWt" />
												<input type="hidden" id="costMtIdPk" name="costMtIdPk" />
												<input type="hidden" id="vAddMetalInvPkId" name="vAddMetalInvPkId" />
												<input type="hidden" id="totMetalPureWt" name="totMetalPureWt" />
												<input type="hidden" id="totAdjMetalWt" name="totAdjMetalWt" />
												<input type="hidden" id="totPureWtForAdj" name="totPureWtForAdj" />
							    				<input type="hidden" id="totMBalanceToAdj" name="totMBalanceToAdj" />
							
										
							
							</form:form>
					</div>
			 </div>
	
		
		
		
		
	
		
		
	</div>
	
	
	<input type="hidden" id="vAddMtId" name="vAddMtId" />
	<input type="hidden" id="vAddMetalInvPk" name="vAddMetalInvPk" />
	
</div>  <!-- ending panel body -->


<script type="text/javascript">


$(document).ready(function(){
	
	$("#" + document.querySelector("#disableCompAdjust").id).attr("id", "compAdjust");
	
	$("#" + document.querySelector("#stoneAdjustmentId").id).attr("id", "stoneAdjustment");
	
	
	 if(window.location.href.indexOf("edit") != -1){
		var strUrl = window.location.href;
		var tempVAddMtPk = strUrl.substring(window.location.href.indexOf("edit")+5);
		var vAddMtPk = tempVAddMtPk.split(".");
		$('#vAddMtId').val(vAddMtPk[0]);
		//alert("XXXXXX");
	}
	 
	 
	 
	 
})

		function inputFormatter(value) {
		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}

		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';
	}
	
	
	function loadMetalData(){
		
		 $.ajax({
				url : "/jewels/manufacturing/transactions/add/vAddMetalInv.html?&vAddMtId="+$('#vAddMtId').val(),
				type : 'GET',
				success : function(data) {
					
					if(data === '-2'){
						displayMsg(this, null, 'Data Already Loaded');	
					}else{
						popMetalVadd();
					}
					
					
				}
			});
		
	}
	
	
	function popMetalVadd(){
	
		 $("#metalVaddList")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/vAddMetalStockAdjust/listing.html?&vAddMtId="+$('#vAddMtId').val(),
				});
	
	}
	
	
	$('#metalVaddList').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				$('#vAddMetalInvPk').val(jQuery.parseJSON(JSON.stringify(row)).id);
				$('#totPureWtForAdj').val(getNumVal(jQuery.parseJSON(JSON.stringify(row)).pure));
				
				
				var tempAdjustValue =  jQuery.parseJSON(JSON.stringify(row)).adjusted;
			
				if(tempAdjustValue === 'Yes'){
					
					$('#adjIdDownList').css('display','none');
					popMetalAdjListing();
				}else{
					$('#adjIdDownList').css('display','block');
					popMetalInwList();
				}
				
			})
			
			
			
		$('#metalVaddList').bootstrapTable({}).on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#metalVaddList").bootstrapTable('getData'));
				
				var vPure = 0.0;
				$.each(JSON.parse(data), function(idx, obj) {
					vPure		+= Number(obj.pure);
				});
				
				$('#vTotalPure').val(" " + parseFloat(vPure).toFixed(3));
				
			});
	
			

			
			//--------inward listing table 3--//-------//
	
	
	function popMetalInwList(){
		
		$('#saveMetalAdj').removeAttr('disabled');
				
		$('#metalInwListId').bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/transactions/fromMetalInwardList/listing.html?&vAddMetalInvId="+$('#vAddMetalInvPk').val(),
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
	$('#metalInwListId').bootstrapTable({}).on('click-row.bs.table',
			function(e, row, $element) {
				$('#totMBalanceToAdj').val(getNumVal(jQuery.parseJSON(JSON.stringify(row)).balance));
				$('#fldTrfPureWeight').val(getNumVal(jQuery.parseJSON(JSON.stringify(row)).adjWt));
				stnTblRow = $element.attr('data-index');
				$('#fldTrfPureWeight').focus();
			});

	
		
		$('#fldTrfPureWeight').on('blur',
			function(e) {
		
					if(Number($('#fldTrfPureWeight').val()) > Number($('#totMBalanceToAdj').val())){
						displayMsg(this, null, 'Adjusted Wt cannot be  greater than balance Wt');
					}else{
						
						$("#metalInwListId").bootstrapTable('updateRow', {
							index : stnTblRow,
							row : {
								state : true,
								adjWt : (Number($('#fldTrfPureWeight').val()).toFixed(3)),

							}
						});
						
						updateTotMetWt();
	
					}
				
			});
	
	
	
	function updateTotMetWt(){

		var data = $('#metalInwListId').bootstrapTable('getData');
		var vMAdjWt = $.map(data, function(item) {
			return item.adjWt;
		});
		
		var totMAdjWt = 0;
		for (i = 0; i < data.length; i++) {
			totMAdjWt += Number(vMAdjWt[i]);
		}

		$('#vTotalMetalsAdjWt').val(totMAdjWt.toFixed(3));
		
	} 

	
	//----------fifo//---//
	
	
	function doMetalFifo(){
		
		var totMetWeight = Number($('#totPureWtForAdj').val());
		
		var data = $('#metalInwListId').bootstrapTable('getData');
		
		var vMetBalance = $.map(data, function(item) {
			return item.balance;
		});
		
		var vMetAdjWt = $.map(data, function(item) {
			return item.adjWt;
		});
		
		var tempTotMetalWeight = totMetWeight;
		
		for (i = 0; i < data.length; i++) {
			
			if(tempTotMetalWeight === 0){
				break;
			}
			
				var totMetBalance  = Number(vMetBalance[i]);
				var totMetAdjWt 	= Number(vMetAdjWt[i]);
				
				if(totMetBalance <= tempTotMetalWeight){
					
					$("#metalInwListId").bootstrapTable('updateRow', {
						index : i,
						row : {
							state : true,
							adjWt :totMetBalance.toFixed(3),
	
						}
					});
					
					tempTotMetalWeight = tempTotMetalWeight-totMetBalance;
					
				 }else{
					 
					 $("#metalInwListId").bootstrapTable('updateRow', {
							index : i,
							row : {
								state : true,
								adjWt :tempTotMetalWeight.toFixed(3),
		
							}
						});
					 
					 tempTotMetalWeight = 0;
					 
				 }
			
			
		}

		
		updateTotMetWt();
		
		
		
		
	}
			

	
//-------save-----//--------//
	
	
	$(document)
		.on(
			'submit',
			'form#saveAdjustmentMetalBtn',
			 function(e){
				$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
			
					var data = $('#metalInwListId').bootstrapTable('getSelections');
					
					var vIds = $.map(data, function(item) {
						return item.id;
					});
					
				/* 	var tempVAdjWt = $.map(data, function(item) {
						return item.balance;
					}); */
					
					$('#tempMetalInwId').val(vIds);
					$('#adjustWt').val($('#totPureWtForAdj').val());
					$('#costMtIdPk').val($('#vAddMtId').val());
					$('#vAddMetalInvPkId').val($('#vAddMetalInvPk').val());
					/* $('#totMetalPureWt').val($('#totPureWtForAdj').val());
					$('#totAdjMetalWt').val($('#vTotalMetalsAdjWt').val()) */
					
					var postData = $(this).serializeArray();
					var formURL = $(this).attr("action");
					
					$.ajax({
						url : formURL,
						type : "POST",
						data : postData,
						success : function(data, textStatus, jqXHR) {
							$.unblockUI();
							
							if(data === '-1'){
					
								
							
								popMetalAdjListing();
								popMetalVadd();
								popMetalInwList();
								
								$('#saveMetalAdj').prop('disabled',true);
								displayInfoMsg(this, null, 'Adjustment Saved !');
								 $('#metalAdjList').focus();
								 
								 $('#adjIdDownList').css('display','none');
								 //popMetalInwList();
								
								//$('#divHideId').css('display','none');
								
								
							}else if(data === '-3'){
								displayMsg(this, null, 'Balance Wt Is Less Than Adjustment Pure Wt');
							}else{
								displayMsg(this, null, 'Please Select atleast one record');
							}
				
							
							
						},
						
						error : function(data, textStatus, jqXHR){
							$.unblockUI();
							alert("ERROR");
						}
							
					})
					
					e.preventDefault();
					
					
					
				
				
				
			
		
	})
	
	
/* 	function validateForm() {
	
		
		alert($('#totPureWtForAdj').val());
		
		alert($('#totMBalanceToAdj').val());
		
		if(Number($('#totPureWtForAdj').val()) > Number($('#totMBalanceToAdj').val())){
			displayMsg(this, null, 'Balance Wt Is Less Than Require Pure Wt');
			 return false;
		}
			
	
	
} */
	
	//-------------right table ---no 2--//--------------//
	
	function popMetalAdjListing(){
	
		$('#metalAdjList').bootstrapTable(
				'refresh',{
					url : "/jewels/manufacturing/transactions/vAddMetalAdj/listing.html?&vAddMetalInvId="+$('#vAddMetalInvPk').val(),
				})
	
	}
	
	
	
	
	
	$('#metalAdjList').bootstrapTable({}).on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#metalAdjList").bootstrapTable('getData'));
				
				var vAdjPure = 0.0;
				$.each(JSON.parse(data), function(idx, obj) {
					vAdjPure		+= Number(obj.adjustmentWt);
				});
				
				$('#vTotalAdjPure').val(" " + parseFloat(vAdjPure).toFixed(3));
				
			});
	
	
	
	
	
	
	//---delete ---//---//
	
	
	function popDeleteMetalAdjustment(vAddMetalInvId){
		
		$("#modalRemove").modal("hide");
		
		  $.ajax({
				url : "/jewels/manufacturing/transactions/vAddMetalInv/delete/"+vAddMetalInvId+ ".html",
						
				type : 'GET',
				success : function(data) {
					popMetalAdjListing();
					popMetalVadd();
				//	popMetalInwList();
				
					 $('#adjIdDownList').css('display','none');
					
				}
			});  
		
		 
	}
	
	
	function popDeleteAllMetalAdjustment(){
		
		$("#modalRemove").modal("hide");
		
		  $.ajax({
				url : "/jewels/manufacturing/transactions/vAddMetalInv/deleteAllMetalAdjument.html?&mtId="+$('#vAddMtId').val(),
						
				type : 'GET',
				success : function(data) {
					
						popMetalAdjListing();
						popMetalVadd();
						$('#adjIdDownList').css('display','none');	
					 
					
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
	<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
	
	


