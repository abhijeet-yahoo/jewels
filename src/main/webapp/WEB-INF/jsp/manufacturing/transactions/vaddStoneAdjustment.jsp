<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

	<div class="panel-body">
	
	
	
<div class="row">
	
	
		<div class="col-xs-7">
			<div class="panel panel-primary" style="width:16.2.5cm; height:10.6cm">
				<div class="panel-body">
				
					<div class="container-fluid">
						<div class="row">
						<div id ="toolbarStnVadd">
						<div class="col-sm-1">
						<button class="btn btn-danger" id="btnFifoAllId" onclick="javascript:popFifoAll()">Auto FIFO Adjustment </button>
						</div>
						
						</div>
						
						
						
					
						<div>
							<table class="table-responsive" id="stnVaddList"
								data-toggle="table" data-toolbar="#toolbarStnVadd"	data-search="true" data-click-to-select="true" data-height="350">
								<thead>
									<tr class="btn-primary">
										<th data-field="state" data-radio="true"></th>
										<th data-field="stoneType" data-sortable="false">StoneType</th>
										<th data-field="shape" data-sortable="false">Shape</th>
								
									<c:if test="${vaddstnadjtype eq 'size' || vaddstnadjtype eq 'sizeGroup' || vaddstnadjtype eq 'quality'}">
										<th data-field="quality" data-sortable="false">Quality</th>
									</c:if>
									<c:if test="${(vaddstnadjtype eq 'size')}">
										<th data-field="size" data-sortable="false">Size</th>
									</c:if>
									<c:if test="${(vaddstnadjtype eq 'size') || (vaddstnadjtype eq 'sizeGroup')}">
										<th data-field="sizeGroup" data-sortable="false">Size Group</th>
									</c:if>
										<th data-field="carat" data-sortable="false">Tag Wt</th>
										<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="action2" data-sortable="false">Delete Adjustment</th>
										
									</tr>
								</thead>
							</table>
						</div>
					
					</div>
					
					
					
					
					
					
				</div>
				
			
				
				</div>
			</div>
		</div>
				
		<div class="col-xs-5" >
	
			<div class="panel panel-primary" style="width:16.2.5cm; height:10.6cm">
							
					<div class="panel-body">
				
						<div class="container-fluid">
							<div class="row">
							
							
							<div id="toolbarstnAdj">
								<a  style="font-size: 15px;color: red" >
									<span></span>&nbsp; Adjustment Details
								</a>
									
							</div>
							
						
								<div>
							<table class="table-responsive" id="vaddStnAdjTbl"
								data-toggle="table" data-toolbar="#toolbarstnAdj" 
								data-search="true"
								 data-height="350">
								<thead>
									<tr class="btn-primary">
										<th data-field="state" data-radio="true"></th>
										<th data-field="invNo" data-sortable="false">Invoice No</th>
										<th data-field="invDate" data-sortable="false">Invoice Date</th>
										<th data-field="adjustedWt" data-sortable="false">Adjusted Wt</th>
										<th data-field="rate" data-sortable="false">Rate</th>
										
									</tr>
								</thead>
							</table>
						</div>
						
						</div>
					</div>
					
					
					
					
				</div>
					
					
					
			</div>
	
		</div>
		
		
				
	</div>	
	
	
	<div class="row">
				<div class="col-sm-12">
					
					<div align="center">
								<label for="inputLabel3">Quality Rate Change</label>
							</div>
					
					<div class="col-sm-2"  id="shapeDropDownDivId">
								
						</div>	
					
						<div class="col-sm-2"  id="qualityDropDownDivId">
								
						</div>	
						
						<div class="col-sm-2"  id="sizeGroupDropDownDivId">
								
						</div>	
						
						<div class="col-sm-1" >
						
						<input type="number" id="fromRate" class="form-control" placeholder="From Rate">
								
						</div>	
						<div class="col-sm-1"  >
						<input type="number" id="toRate" class="form-control" placeholder="TO Rate">		
						</div>	
						
						<div class="col-sm-4">
						<button class="btn btn-info" id="btnRateChangeId" onclick="javascript:popChangeRate()">Change Rate</button>

						<button class="btn btn-danger" id="cleatAllStnAdjBtnId" onclick="javascript:popClearAllAdjustment()">Clear All Adjustment</button>
						</div>
					
					
	
	</div>
	
	</div>
	
	<div class="row">&nbsp;</div>
	
	<div class="row">
	<div class="col-xs-12">
	<div class="panel panel-primary" >
							
					<div class="panel-body">
				
						<div class="container-fluid">
							<div class="row">
							
							<div id="toolbarPurcStnVadd">
								<a  style="font-size: 15px;color: red" >
									<label for="inputLabel3">Stone Purchase Details</label>
								</a>
								
										
						<button class="btn btn-info" id="btnRateChangeId" onclick="javascript:popGroupQuality()">Group Quality Adjustment</button>

						
						
									
							</div>
							
								<div>
							<table class="table-responsive" id="purcStnVaddList" 
								data-toggle="table" data-toolbar="#toolbarPurcStnVadd" 
								data-search="true"
								 data-height="350">
								<thead>
									<tr class="btn-primary">
										<th data-field="state" data-checkbox="true"></th>
										<th data-field="invNo" data-sortable="false">Invoice No</th>
										<th data-field="invDate" data-sortable="false">Invoice Date</th>
										<th data-field="stonetypenm" data-sortable="false">StoneType</th>
										<th data-field="shapenm" data-sortable="false">Shape</th>
									<c:if test="${(vaddstnadjtype eq 'size') || (vaddstnadjtype eq 'sizeGroup') || (vaddstnadjtype eq 'quality')}">
										<th data-field="qualitynm" data-sortable="false">Quality</th>
									</c:if>
									<c:if test="${(vaddstnadjtype eq 'size')}">
										<th data-field="size" data-sortable="false">Size</th>
									</c:if>
									<c:if test="${(vaddstnadjtype eq 'size') || (vaddstnadjtype eq 'sizeGroup')}">
										<th data-field="sizegroupnm" data-sortable="false">Size Group</th>
									</c:if>
										<th data-field="carat" data-sortable="false">Carat</th>
										<th data-field="balcarat" data-sortable="false">Bal Carat</th>
										<th data-field="rate" data-sortable="false">Rate</th>
										<th data-field="adjCarat"  data-formatter="adjCaratFormatter">Adj Carat</th>
										
									</tr>
								</thead>
							</table>
						</div>
						
						<input type="hidden" id="totalAdjWtId" name="totalAdjWtId" />
						
						</div>
						
						
						
						
					</div>
					
					</div>
					
					
					
					
					
			</div>
	
	</div>
	
	
	</div>
	
	<div class="row">
	
	<div class="col-sm-2">
						<button class="btn btn-primary" id="btnManualAdjBtnId" onclick="javascript:popManualAdj()">Save Adjustment</button>
					</div>
					<div class="col-sm-1">
						<button class="btn btn-danger" id="btnFifoId" onclick="javascript:popFifo()">FIFO</button>
						</div>
						
						<div class="col-sm-8">
			
		<span style="display:inline-block; width: 1cm;"></span>
					<strong><b>Total Carat :  </b></strong><input type="text" id="totalCarat" name="totalCarat" maxlength="7" size="7"  readonly="readonly" style="font-weight: bold; border: none;" /> 
						&nbsp;&nbsp;
					<b>Total Adj Carat :  </b><input type="text" id="totalAdjCarat" name="totalAdjCarat" 	maxlength="7" size="7" readonly="readonly"  style="font-weight: bold;border: none;" />			
						&nbsp;&nbsp;								
					
					
					</div>
						
						
	</div>
		
					<input type="hidden" id="vAddMtId" name="vAddMtId" />
				
				</div>
				
				
				<script type="text/javascript">
				
				

				$(document).ready(function(){
					/* $("#" + document.querySelector("#disableCompAdjust").id).attr("id", "compAdjust"); */
					
					$("#" + document.querySelector("#metalAdjust").id).attr("id", "metalAdjust");
					
					
					 if(window.location.href.indexOf("edit") != -1){
						var strUrl = window.location.href;
						var tempVAddMtPk = strUrl.substring(window.location.href.indexOf("edit")+5);
						var vAddMtPk = tempVAddMtPk.split(".");
						$('#vAddMtId').val(vAddMtPk[0]);
						
						shapeDropdown();
						qualityDropdown();
						sizeGroupDropdown();
						
						setTimeout(function(){
						$('#vaddStnAdjTbl').bootstrapTable('resetView');
						$('#purcStnVaddList').bootstrapTable('resetView');
						
						}, 500);
						
					}
					 
					 
					 
					 
				})

				
				
				function popVaddStone(){
					
					 $("#stnVaddList")
						.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/vAddStnDt/adjustmentListing.html?&vAddMtId="+$('#vAddMtId').val(),
							});
				
				}
				
				
			
				var vStonetypeId;
				var vShapeId;
				var vQualityId;
				var vSizegroupId;
				var vSize;
				var vRate;
				var vCarat;
				var vQualityGroup;
				$('#stnVaddList').bootstrapTable({}).on(
						'click-row.bs.table',
						function(e, row, $element) {
							
							vStonetypeId = jQuery.parseJSON(JSON.stringify(row)).stonetypeid;
							vShapeId	 = jQuery.parseJSON(JSON.stringify(row)).shapeid;
							vQualityId   = jQuery.parseJSON(JSON.stringify(row)).qualityid;
							vSizegroupId = jQuery.parseJSON(JSON.stringify(row)).sizegroupid;
							vSize 		 = jQuery.parseJSON(JSON.stringify(row)).size;
							vRate 		 = jQuery.parseJSON(JSON.stringify(row)).rate;
							vCarat		 = jQuery.parseJSON(JSON.stringify(row)).carat;
							vQualityGroup=  jQuery.parseJSON(JSON.stringify(row)).qualityGroup;
							
							$('#totalCarat').val(Number(vCarat).toFixed(3));
					
							setTimeout(function(){
								popVaddStonePurchase();
								popVaddStoneAdj();
								totAdjCarat();	
								
							}, 500);
							
						})
				
				
				function popVaddStonePurchase(){
					
					 $("#purcStnVaddList")
						.bootstrapTable(
							'refresh',
							{		
								url : "/jewels/manufacturing/transactions/vAddStnDt/stnPurcAdjtListing.html?&stonetypeid="+vStonetypeId+"&shapeid="+vShapeId+
										"&qualityid="+vQualityId+"&sizegroupid="+vSizegroupId+"&size="+vSize+"&rate="+vRate,
							});
				
				}
				
				
				
				function popGroupQuality(){
										
					 $("#purcStnVaddList")
						.bootstrapTable(
							'refresh',
							{		
								url : "/jewels/manufacturing/transactions/vAddStnDt/stnPurcAdjtListing.html?&stonetypeid="+vStonetypeId+"&shapeid="+vShapeId+
										"&qualityid="+vQualityId+"&sizegroupid="+vSizegroupId+"&size="+vSize+"&rate="+vRate+"&qualityGroup="+vQualityGroup,
							});
				}
				
				
				function popVaddStoneAdj(){
					
					 $("#vaddStnAdjTbl")
						.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/vAddStnAdj/adjustmentListing.html?&stonetypeid="+vStonetypeId+"&shapeid="+vShapeId+
										"&qualityid="+vQualityId+"&sizegroupid="+vSizegroupId+"&size="+vSize+"&rate="+vRate+"&vAddMtId="+$('#vAddMtId').val(),
							});
				
						setTimeout(function(){
							popAdjWtDetails();
						}, 300);
						
					
				}
				
				
				
				
				function shapeDropdown() {
					$
							.ajax({

								url : "/jewels/manufacturing/transactions/vAddStnDt/shapeDropdown.html?&vAddMtId="+$('#vAddMtId').val(),
								type : 'GET',
								success : function(data) {

										$('#shapeDropDownDivId').html(data);
										
								}
							})

				}
				
				
				function qualityDropdown() {
					$
							.ajax({

								url : "/jewels/manufacturing/transactions/vAddStnDt/qualityDropdown.html?&vAddMtId="+$('#vAddMtId').val(),
								type : 'GET',
								success : function(data) {

										$('#qualityDropDownDivId').html(data);
										
								}
							})

				}
				
				
				function sizeGroupDropdown() {
					$
							.ajax({

								url : "/jewels/manufacturing/transactions/vAddStnDt/sizeGroupDropdown.html?&vAddMtId="+$('#vAddMtId').val(),
								type : 'GET',
								success : function(data) {

										$('#sizeGroupDropDownDivId').html(data);
										
								}
							})

				}
				
				
				function popChangeRate(){
					if($('#shapeId').val() !='' && $('#qualityId').val() !='' && $('#fromRate').val()>0 && $('#toRate').val()>0 ){
						$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
						
						$
						.ajax({

							url : "/jewels/manufacturing/transactions/vAddStnDt/updateStoneRate.html?&vAddMtId="+$('#vAddMtId').val()+
									"&shapeId="+$('#shapeId').val()+"&qualityId="+$('#qualityId').val()+"&fromRate="+$('#fromRate').val()+
									"&toRate="+$('#toRate').val()+"&sizeGroupId="+$('#sizeGroupId').val(),
							type : 'GET',
							success : function(data) {
								$.unblockUI();
								popVaddStone()
								
									
							}
						})
						
					}else{
						displayMsg(this,null,"Select Quality & Rates");	
						
					}
					
				}
				
				
			
				
			function popManualAdj(){
				
				$('#btnManualAdjBtnId').attr('disabled', 'disabled');
				$('#btnFifoId').attr('disabled', 'disabled');
				
				if(Number($('#totalAdjCarat').val()) > Number(vCarat) ){
					displayMsg(this,null,"Adjusted carat greater than carat");
				}else{
		
				var data = $('#purcStnVaddList').bootstrapTable('getSelections');
				
				var vdtIds = $.map(data, function(item) {
					return item.dtId;
				});
				
				var vAdjCarat = $.map(data, function(item) {
					return item.adjCarat;
				});
				
				
			$
				.ajax({

					url : "/jewels/manufacturing/transactions/vAddStnDtAdj/saveAdj.html?&vAddMtId="+$('#vAddMtId').val()
					+"&stonetypeid="+vStonetypeId+"&shapeid="+vShapeId+
						"&qualityid="+vQualityId+"&sizegroupid="+vSizegroupId+"&size="+vSize+"&rate="+vRate+"&carat="+vCarat+"&dtId="+vdtIds+"&adjCarat="+vAdjCarat,
					type : 'POST',
					success : function(data) {

						if (data === "-3") {
							displayMsg(this, null, 'Stock not found');
						} else if (data === "-2") {
							displayMsg(this, null,'No record selected to save');
							$('#btnManualAdjBtnId').removeAttr('disabled');
							$('#btnFifoId').removeAttr('disabled');
						
						}else{
							
							$('#btnManualAdjBtnId').removeAttr('disabled');
							$('#btnFifoId').removeAttr('disabled');
						
							popVaddStonePurchase();
							popVaddStone();
							popVaddStoneAdj();
							totAdjCarat();
							popAdjWtDetails();
					}
						}
				});	 		
				}
			}	
			
			
			
			function popFifoAll(e){
				
				$('#btnFifoAllId').attr('disabled', 'disabled');
				
				displayDlg(
						e,
						'javascript:doAllFifo();',
						'Fifo Adjustment',
						'Do you want to Adjust All Fifo  ?',
						'Continue');
			}
			
			
			function doAllFifo(){
				$("#modalDialog").modal("hide");
				
				$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
				$
				.ajax({

					url : "/jewels/manufacturing/transactions/vAddStnAdj/allFifoAdjustment.html?&vAddMtId="+$('#vAddMtId').val(),
					type : 'GET',
					success : function(data) {
						$.unblockUI();
						$('#btnFifoAllId').removeAttr('disabled');
						if (data === "1") {
							displayInfoMsg(this, null, 'Fifo Adjusted Successfully ');
						}else{
							displayMsg(this, null,'Error Contact Admin');
						}
						
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$.unblockUI();
					}
				})
				
			}
			
			
			function popFifo(){
				
				var data = $('#purcStnVaddList').bootstrapTable('getData');
				
				$('#btnFifoId').attr('disabled', 'disabled');
				
				var fifoData = $('#purcStnVaddList').bootstrapTable('getData');

				var vBalCarat = $.map(fifoData, function(item) {
					return item.balcarat;
				});
				
			
				var tempAdjCarat = vCarat;

				for (i = 0; i < fifoData.length; i++) {

				
					if (Number(vBalCarat[i]) <= Number(tempAdjCarat)) {

						$("#purcStnVaddList").bootstrapTable(
								'updateRow',
								{
									index : i,
									row : {
										state : true,
										adjCarat : vBalCarat[i],
										balcarat : 0.0,
																			}
								});

						tempAdjCarat = tempAdjCarat - vBalCarat[i];
						tempAdjCarat = tempAdjCarat.toFixed(3);

					} else {
						if (Number(vBalCarat[i]) >= Number(tempAdjCarat)) {

							$("#purcStnVaddList").bootstrapTable(
									'updateRow',
									{
										index : i,
										row : {
											state : true,
											adjCarat : tempAdjCarat,
											balcarat : (vBalCarat[i] - tempAdjCarat).toFixed(3),
												}
									});

							tempAdjCarat = 0.0;

						}
					}

					if (tempAdjCarat === 0.0) {
						break;
					}

				} // ending loop

				transferTotal();

			}
			
			
			function transferTotal() {

				var data = $('#purcStnVaddList').bootstrapTable('getData');

				var vAdjCarat = $.map(data, function(item) {
					return item.adjCarat;
				});

				var totAdjCarat = 0.0;

				for (i = 0; i < data.length; i++) {
					totAdjCarat += Number(vAdjCarat[i]);
				}

				$('#totalAdjCarat').val(Number(totAdjCarat).toFixed(3));

			}


			
			
			function adjCaratFormatter(value,row,index){
				
		 			return '<input class="form-control data-input"  style="width:75px" value="'+ value+ '" onchange="javascript:updateAdjCarat(this,'+index+','+jQuery.parseJSON(JSON.stringify(row)).balcarat+');totAdjCarat();"  />';
		 		
			}
			
			
			var paramAdjWt;
			var idxx;
			function updateAdjCarat(param,vidx,vbalcarat){
				
				if(param.value > 0){
					
					
					var data = JSON.stringify($("#purcStnVaddList").bootstrapTable('getData'));
					var adjCarat= 0.0;
					
					$.each(JSON.parse(data), function(idx, obj) {
						
						adjCarat	+= Number(obj.adjCarat);
						
					});
					
			
					
					var adjWt = (Number(adjCarat)+Number(param.value)).toFixed(3)  ;

					
							
							 if(Number(vbalcarat) < Number(param.value) || Number(vCarat) < Number(param.value) || Number(adjWt) > Number(vCarat)){
								 
					
								 $('#purcStnVaddList').bootstrapTable('updateRow', {
									 
									 
									 
										index : Number(vidx),
										row : {
											state : false,
											adjCarat : 0.0,
										}
									}); 
								 
								 $('#totalAdjCarat').val(Number(totalAdjWt));
								 
								displayMsg(this, null,'Adj Carat greater than carat');
									
									
								
							 }
							 else{
								
								 idxx = vidx;
								 paramAdjWt =param.value;
								 
								 $('#purcStnVaddList').bootstrapTable('updateRow', {
										index : Number(vidx),
										row : {
											state : true,
											adjCarat : param.value,
										}
									}); 
							  			
							 }
							
					
				 		 
				}else{
					
					 $('#purcStnVaddList').bootstrapTable('updateRow', {
							index : Number(vidx),
							row : {
								state : false,
								adjCarat : param.value,
							}
						}); 
					
				}
				 	}
			
		 		
		 		
		 	
			function totAdjCarat(){
				
		var data = JSON.stringify($("#purcStnVaddList").bootstrapTable('getData'));
				var vAdjCarat= 0.0;
				
				$.each(JSON.parse(data), function(idx, obj) {
					
					vAdjCarat	+= Number(obj.adjCarat);
					
				});
				
				var adjWt = (Number(totalAdjWt)+Number(vAdjCarat)).toFixed(3);

					
				$('#totalAdjCarat').val(Number(adjWt).toFixed(3));
				
				if(Number(adjWt) > Number(vCarat)){
					 
					var tempAdjWt = Number(adjWt) - Number(vAdjCarat);
					$('#totalAdjCarat').val(Number(tempAdjWt).toFixed(3));
					
			//		displayMsg(this, null,'Total Adjusted Carat greater than carat');	
				
					$('#purcStnVaddList').bootstrapTable('updateRow', {
						index : Number(idxx),
						row : {
							state : false,
							adjCarat : 0.0,
						}
					}); 
				} 
				
				
			}
				
		var totalAdjWt=0.0;
			function popAdjWtDetails(){
				
				var data = JSON.stringify($("#vaddStnAdjTbl").bootstrapTable('getData'));
				
				var vAdjCarat= 0.0;
				
				$.each(JSON.parse(data), function(idx, obj) {
					
					vAdjCarat	+= Number(obj.adjustedWt);
					
				});
				
				$('#totalAdjCarat').val(Number(vAdjCarat).toFixed(3));
				
			//	$('#totalAdjWtId').val(vAdjCarat);
				
				if(Number(vAdjCarat) >= 0){
			
				//	$('#totalAdjWtId').val(vAdjCarat);
					totalAdjWt = vAdjCarat;
					
					if(Number(vCarat) > Number(vAdjCarat)){
						
					//	totalAdjWt =  Number(vCarat) - Number(vAdjCarat);
						$('#btnManualAdjBtnId').removeAttr('disabled');
						$('#btnFifoId').removeAttr('disabled');
				
					}else if(Number(vCarat) <= Number(vAdjCarat)){
						$('#btnManualAdjBtnId').attr('disabled', 'disabled');
						$('#btnFifoId').attr('disabled', 'disabled');
					//	displayMsg(this, null,'Can not adjust carat');
					}
					
				}else{
					totalAdjWt=0.0;
				}
			
				
			}
			
			
			
			function deleteStoneAdjument(dtId){
				
				if(Number(totalAdjWt) > 0){
				
				$
				.ajax({

					url : "/jewels/manufacturing/transactions/vAddStnDtAdj/deleteAdjument.html?&vAddMtId="+$('#vAddMtId').val()
					+"&stonetypeid="+vStonetypeId+"&shapeid="+vShapeId+
					"&qualityid="+vQualityId+"&sizegroupid="+vSizegroupId+"&size="+vSize+"&rate="+vRate,
					type : 'GET',
					success : function(data) {

							if(data != 1){
								displayMsg(this, null,'Error Occured, Please Contact Administrator');
							}else{
								$('#btnManualAdjBtnId').removeAttr('disabled');
								$('#btnFifoId').removeAttr('disabled');
								
								setTimeout(function() {
									popVaddStonePurchase();
									popVaddStoneAdj();
									totAdjCarat();
									totalAdjWt=0.0;
									
								}, 500);
								
							}
							
					}
				})
				
				}
			}
			
			
			
			function popClearAllAdjustment(){
			
				$
				.ajax({

					url : "/jewels/manufacturing/transactions/vAddStnDtAdj/deleteAllAdjument.html?&vAddMtId="+$('#vAddMtId').val(),
					type : 'GET',
					success : function(data) {


						if(data != 1){
							displayMsg(this, null,'Error Occured, Please Contact Administrator');
						}else{
							$('#btnManualAdjBtnId').removeAttr('disabled');
							$('#btnFifoId').removeAttr('disabled');
							
							setTimeout(function() {
								popVaddStonePurchase();
								popVaddStoneAdj();
								totAdjCarat();
								totalAdjWt=0.0;
								
							}, 500);
							
						}
					}
				})

			}
			
			
				

				</script>


	<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

	<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

	<script src="/jewels/js/common/common.js"></script>

	<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

	<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

	<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
	<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
	