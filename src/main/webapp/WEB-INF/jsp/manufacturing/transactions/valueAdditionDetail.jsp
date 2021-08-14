
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

 <%@ include file="/WEB-INF/layout/taglib.jsp"%>
 
 <%@ include file="/WEB-INF/jsp/common/modalVAddMetalRates.jsp"%>
 
 <link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
 
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>
 


<div class="panel-body" style="padding: 25px">
	<div class="form-group">
	
	
	
				
			<div id="vaddtoolbar">
				<a  onclick="javascript:popCostValueMetalRate()">Metal Rates & Wastage</a>
			</div>
			
			
		
		<!------------ vAddDtListing -----------------------//-------------->
		
			
				<div class="row">
						<div class="table-responsive">
							<table id="costDtValueTabId"
								data-toggle="table" data-height="400" data-search="true"   data-toolbar="#vaddtoolbar"
								data-side-pagination="server" data-click-to-select="true"
								 data-pagination="true"	>
								<thead>
									<tr class="btn-primary">
										<th data-field="stateRd" data-radio="true"></th>  
									<!-- 	<th data-field="itemNo" data-sortable="true">Item No</th>
										<th data-field="party" data-sortable="false">Party</th>
										<th data-field="ordNo" data-sortable="false">Order No</th>
										<th data-field="ordRefNo" data-sortable="false">OrdRefNo</th> -->
										<th data-field="style" data-sortable="false">Style</th>
										<th data-field="purity" data-sortable="false">Kt</th>
										<th data-field="color" data-sortable="false">Color</th>
										<th data-field="pcs" data-sortable="false">Pcs</th>
										<th data-field="grossWt" data-sortable="false">Gross Wt</th>
										<th data-field="netWt" data-sortable="false">Net Wt</th>
										<th data-field="lossPerc" data-sortable="false">Loss %</th>
										<th data-field="lossWt" data-sortable="false" >Loss Wt</th>
										<th data-field="metalValue" data-sortable="false">Metal Value</th>
										<th data-field="lossValue" data-sortable="false">Loss Value</th>
										<th data-field="totMetalValue" data-sortable="false">Tot Metal Value</th>
										<th data-field="stoneValue" data-sortable="false">Stone Value</th>
										<th data-field="fob" data-sortable="false">Fob</th>
										<th data-field="finalPrice" data-sortable="false">Final Price</th>
										<th data-field="perPcsPrice" data-sortable="false">PerPcs Price</th>
										<!-- <th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
										<th data-field="actionLock" data-sortable="false">LockUnlock</th> -->
									</tr>
								</thead>
							</table>
						</div>
						<input type="hidden" id="vAddDtPKId" name="vAddDtPKId" />
												
					</div>
	
			
	
			
			<!----------------------------------------- ValueAddition Load Button ------------------------------>
		
		
		
			<div class="row" >
				<div class="form-group">
					<div class="col-xs-1" >
						<form:form commandName="costingMt" id="loadValueAddition"
							action="/jewels/manufacturing/transactions/load/valueAddition.html"
							cssClass="form-horizontal loadValueAdditionForm">
							
							<input type="submit" value="Load ValueAddition" class="btn btn-primary" style="color: lime;" id="loadBtnId" /> 
											<input type="hidden" id="pInvNo" name="pInvNo" />
							
				<!-- 			<table class="table">
								<tbody>
									<tr>
										<td class="col-xs-1" >
											
										</td>
										<td class="col-xs-1" ></td>
										<td class="col-xs-1" >
											<input type="button" value="Apply Rate" class="btn btn-info" style="color: yellow;" onclick="javascript:popApplyRate()"/> 
										</td>
									</tr>
								</tbody>
							</table> -->
						</form:form>
					</div>
					
					<input type="hidden" id=costMtId name=costMtId />
				</div>
			</div>
		
	

	
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			
			
		<div id="hideShow" style="display: none">
		
		<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default">Metal Details</span>
		</div>
		</div>

			
				<div>
					<table class="table-responsive" id="costMetalDtValueTabId" data-toggle="table" 
							data-unique-id="id" >
						<thead>
							<tr class="btn-primary">
								<th data-field="partNm" data-sortable="false">PartNm</th>
								<th data-field="purity" data-sortable="false">Purity</th>
								<th data-field="color" data-align="left">Color</th>
								<th data-field="qty" data-sortable="false">Qty</th>
								<th data-field="metalWt" data-sortable="false">Metal Wt</th>
								<th data-field="metalRate" data-sortable="false">Metal Rate</th>
								<th data-field="perGramRate" data-sortable="false">Per Gram Rate</th>
								<th data-field="lossPerc" data-sortable="false" data-formatter="lossPercFormatter">Loss %</th>
								<th data-field="lossWt" data-sortable="false" >Loss Wt</th>
								<th data-field="lossValue" data-sortable="false">Loss Value</th>
								<th data-field="metalValue" data-sortable="false">Metal Value</th>
								<th data-field="mainMetal" data-formatter="mianMetalFormatter">Main Metal</th>
							</tr>
						</thead>
					</table>
				</div>
		
		
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
	<!------------------------------------------ vAddStnDt -------------------------------------->
			
			<div class="row">
				<div class="form-group">
					<div class="col-lg-12 col-sm-12">
						<span class="col-lg-12 col-sm-12 label label-default text-right">Stone Details</span>
					</div>
				</div>
			</div>
			
			
			
				<div class="container-fluid">
					<div class="row">
						<div>
							<table class="table-responsive" id="costStnDtValueTabId"
								data-toggle="table">
								<thead>
									<tr class="btn-primary">
										<th data-field="state" data-radio="true"></th>
										<th data-field="stoneType" data-sortable="true">StoneType</th>
										<th data-field="shape" data-sortable="false">Shape</th>
										<th data-field="quality" data-sortable="false">      Quality</th>
										<th data-field="size" data-sortable="false">Size</th>
										<th data-field="sizeGroup" data-sortable="false">SizeGroup</th>
										<th data-field="stone" data-sortable="false">Stone</th>
										<th data-field="carat" data-sortable="false">Carat</th>
										<th data-field="rate" data-sortable="false">Rate</th>
										<th data-field="stoneValue" data-sortable="false">Stone Value</th>
										<!-- <th data-field="handlingRate" data-sortable="false">Handling Rate</th>
										<th data-field="handlingValue" data-sortable="false">Handling Value</th>
										<th data-field="setting" data-sortable="false">Setting</th>
										<th data-field="settingType" data-sortable="false">Setting Type</th>
										<th data-field="settingRate" data-sortable="false">Setting Rate</th>
										<th data-field="settingValue" data-sortable="false">Setting Value</th> -->
										
									</tr>
								</thead>				
							</table>						
						</div>
					</div>
				</div>
			
			
			
			
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			
			<!------------------------------------------ vAddCompDt -------------------------------------->
			
			<div class="row">
				<div class="form-group">
					<div class="col-lg-12 col-sm-12">
						<span class="col-lg-12 col-sm-12 label label-default text-right">Component Details</span>
					</div>
				</div>
			</div>
			
			
			<div class="container-fluid">
				<div class="row">
					<div>
						<table class="table-responsive" id="costCompDtValueTabId"
							data-toggle="table" data-unique-id="id" >
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="compName" data-sortable="true">Component</th>
									<th data-field="kt" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="metalWt" data-sortable="false">Metal Wt</th>
									<th data-field="perGramMetalRate" data-sortable="false">Metal Rate</th>
									<th data-field="lossPerc" data-sortable="false" data-formatter="lossPercCompFormatter">Loss %</th>
									<th data-field="lossWt" data-sortable="false" >Loss Wt</th>
									<th data-field="lossValue" data-sortable="false">Loss Value</th>
									<th data-field="metalValue" data-sortable="false">Metal Value</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="value" data-sortable="false">Value</th>
									<!-- <th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th> -->
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			
			
			
			
	 </div>	
	 
	 
	
	
	
		
	</div> 
		

</div> <!-- ending the panel -->



<script type="text/javascript">

	var xstnTblRow  = -1;

	$(document).ready(
			function(e){
				
				if (window.location.href.indexOf('edit') != -1) {
					var abcx = window.location.href;
					var cost = abcx.substring(window.location.href.indexOf('edit') + 5);
					var tempCost = cost.split(".");
					$('#costMtId').val(tempCost[0]);
					
					
					var url = "/jewels/manufacturing/transactions/vadd/reportExcel.html?mtId=" + $('#costMtId').val();
					$("#vaddExcelId").attr("href",url)
					
				}
				
				$('#vDetails').on('click', function() {
					popCostingDetails();
				});
				
				
				
				
			});


	
	


	//=============== VAddMt (Entry in costing Mt)--//-----------//
	
	
	
	$(document)
	.on(
			'submit',
			'form#updateCosting',
			function(e) {
			
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

				$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,

							success : function(data, textStatus, jqXHR) {								
								
								if(data === '-1'){
									displayMsg(this, null, 'data updated sucessfully');
								}

							},
							error : function(jqXHR, textStatus,
									errorThrown) {

							}

						});
				
				e.preventDefault();
			});

	
	
	
	

	//=============== VAddDt--//-----------//

	function popCostingDetails() {
		
		$("#costDtValueTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/vAddCostDt/listing.html?pInvNo="+ $('#invNo').val()
						});
	
	}
	
	
	
	
	$('#costDtValueTabId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
			
				xstnTblRow = $element.attr('data-index');
				
				$('#vAddDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
		
				popStoneValueDetails();
				popCompValueDetails();
				popMetalValueDetails();
				
				$("#hideShow").css('display','block');
				
			})
	
	
	//=============== value Addition Load--//-----------//
	
	
	$(document).
			on('submit',
				'form#loadValueAddition',
				function(e){
				
				
				$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
				
				$('#loadBtnId').attr('disabled',true);
				$("#pInvNo").val($('#invNo').val());
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");
				 
			
				 $.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR){
						
						if(data=='1'){
							displayInfoMsg(this, null, 'Data uploaded sucessfully');
						}else{
							displayMsg(this, null, 'Data Not Upload Contact Admin');
						}
						
						$.unblockUI();
						popCostingDetails();
						
						
						$('#loadBtnId').attr('disabled',false);
		
					},
					error : function(data, textStatus, jqXHR){
						
						alert("error,please contact admin");
						
					}
					
				})
				
				e.preventDefault(); 
				
			})
		
			 
			
		
			// ------------ ApplyRate -----------------------//---------->
	
	
			function popApplyRate(){
				
				$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
				 var postData = $('#updateCosting').serializeArray();
				$
				.ajax({
					url : "/jewels/manufacturing/transactions/vAddDt/applyRate.html?invNo="+$('#invNo').val()
					+ "&vAdded=" + $('#vAdded').val(),
					type : 'POST',
					success : function(data) {
						$.unblockUI();
						if(data === '-1'){
							displayInfoMsg(this, null, 'Rate Applied Sucessfully ! ');
							popCostingDetails();
							$("#hideShow").css('display','none');
						}
						
					}
				});
				

				
			}
			
		
			
			
			// ------------ vAddMetalDt -----------------------//---------->
			
			
			function popMetalValueDetails() {
			
				$("#costMetalDtValueTabId")
						.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/vAddCostMetalDt/listing.html?vAddDtId="+ $('#vAddDtPKId').val()
							});
		
			}
	
			
			
	
			
		// ------------ vAddStnDt -----------------------//---------->
			
			
		function popStoneValueDetails() {
		
			$("#costStnDtValueTabId")
					.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/vAddCostStnDt/listing.html?vAddDtId="+ $('#vAddDtPKId').val()
						});
	
		}

		
		
		// ------------ vAddComponentDt -----------------------//---------->
		
		
		function popCompValueDetails() {
		
			$("#costCompDtValueTabId")
					.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/vAddCostCompDt/listing.html?vAddDtId="+ $('#vAddDtPKId').val()
						});
	
		}
		
		
		
		//---------//--do lock unlock-----//
		
		function doCostDtLockUnLock(vAdddtIdPk){
						
			$.ajax({
				url : "/jewels/manufacturing/transactions/vAddDt/lockUnlock.html?vAddDtId="+vAdddtIdPk,
				type : 'GET',
				success : function(data) {
					
				if(data == 1){
						
					updateVAddTable($('#vAddDtPKId').val());
						
					popStoneValueDetails();
					popCompValueDetails();
					popMetalValueDetails();

					}
				
					
			}
		});
	}
		
		
	
	/* 		//---------//--onload function-----//
		 
	$('#costDtValueTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#costDtValueTabId").bootstrapTable('getData'));

				var vTotPcs = 0.0;
				var vTotFob = 0.0;
				var vTotVAddTotal=0.0;
				var vTotStoneValue=0.0;
				var vTotMetalValue = 0.0;
				
				$.each(JSON.parse(data), function(idx, obj) {
					vTotPcs		      += Number(obj.pcs);
					vTotFob		      += Number(obj.fob);
					vTotVAddTotal     += Number(obj.vAddTotal);
					vTotStoneValue    += Number(obj.metalValue);
					vTotMetalValue    += Number(obj.stoneValue);
 			});
				
				$('#totPcs').val(" " + vTotPcs);
				$('#totFob').val(" " + vTotFob.toFixed(2));
 				$('#totVAddTotal').val(" "+vTotVAddTotal);
				$('#totStoneValue').val(" "+vTotStoneValue);
				$('#totMetalValue').val(" "+vTotMetalValue); 
			}); */
				
		
	function mianMetalFormatter(value) {
		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}
		
		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';
	}
	
	function popCostValueMetalRate(){
		$('#myCostValueRateModal').modal('show');
		popCostValueMetalRateTbl();
		
		setTimeout(function(){
			$('#costValueMetalRateIdTbl').bootstrapTable('resetView');
		}, 300);
		
	}
	
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
		
	
	var tempMetalWtSrNo = 0;
	function lossPercFormatter(value, row, index){
		
		var tempId = 'lossPercval' + Number(index);
		
		var vId = row.id;
		
	    	
	    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateLossPerc(this,'+vId+')"  />';
	    	
	    
	}	
	
	
	function updateLossPerc(param, val) {
		var tempId = param.id.substring(11);

		$('#lossPercval' + tempId).val(param.value);

		$('#costMetalDtValueTabId').bootstrapTable('updateByUniqueId', {
			id : val,
			row : {
				lossPerc : param.value
			}
		});

		updateTotLossPerc(val, param.value);

	}

	function updateTotLossPerc(id, vLossPerc) {
		$
				.ajax({

		url : "/jewels/manufacturing/transactions/vaddMt/updateLossPerc.html?id="
				+ id + "&lossPerc=" + vLossPerc,
		type : 'GET',
		success : function(data) {
			popMetalValueDetails();
			updateVAddTable($('#vAddDtPKId').val());
			
		}

	});
}
	
	
	

	var tempCompMetalWtSrNo = 0;
	function lossPercCompFormatter(value, row, index){
		
		var tempId = 'lossCompPercval' + Number(index);
		
		var vId = row.id;
		
	    	
	    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateCompLossPerc(this,'+vId+')"  />';
	    	
	    
	}	
	
	
	function updateCompLossPerc(param, val) {
		var tempId = param.id.substring(15);

		$('#lossCompPercval' + tempId).val(param.value);

		$('#costCompDtValueTabId').bootstrapTable('updateByUniqueId', {
			id : val,
			row : {
				lossPerc : param.value
			}
		});

		updateTotLossPercComp(val, param.value);

	}
	
	
	function updateTotLossPercComp(id, vLossPerc) {
		
		alert('in function');
		$
				.ajax({

		url : "/jewels/manufacturing/transactions/vaddMt/updateLossPercComp.html?id="
				+ id + "&lossPerc=" + vLossPerc,
		type : 'GET',
		success : function(data) {
			popCompValueDetails();
			updateVAddTable($('#vAddDtPKId').val());
			
		}

	});
}
	

	
	
	
	
	
	
		
		function updateVAddTable(dtId){
			$.ajax({
				url : "/jewels/manufacturing/transactions/vAddDt/getData/"+dtId+".html",
				type : 'GET',
				dataType : 'JSON',
				success : function(data) {
					
						$('#costDtValueTabId').bootstrapTable('updateRow', {
						index : Number(xstnTblRow),
						row : data
					});
					
					
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

<script src="/jewels/js/common/design.js"></script>

