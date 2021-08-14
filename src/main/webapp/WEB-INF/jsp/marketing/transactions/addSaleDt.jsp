<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<%@ include file="/WEB-INF/layout/taglib.jsp"%>


	<div id="saleDtDivId" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-10">
					<div id="toolbar1">
					<div class="row">
					
					
					<div class="col-sm-3">
					<a class="btn btn-info" style="font-size: 15px" type="button" id="salePickupBtnId"
					onClick="javascript:packingListPickup()"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Pickup</a>
					
					</div>
				 	
				 	 <div class="col-sm-2"></div>
				 	
				 	 <div class="col-sm-3">
					<a class="btn btn-success" style="font-size: 15px" type="button"
					onClick="javascript:applyGstRates()" id="saleApplyRatesBtnId"
					href="javascript:void(0)"><span
					></span>&nbsp;Apply Rates</a>
					</div> 
					
					<!-- <div class="col-sm-3">
					<a class="btn btn-success" style="font-size: 15px" type="button"
					onClick="javascript:applyGstRates()"
					href="javascript:void(0)"><span
					></span>Apply Rates</a>
					</div> -->
					
					</div>
					</div>
					<div>
						<table id="saleDtTblId" data-toggle="table" data-toolbar="#toolbar1" data-search="true"
							data-height="350" data-click-to-select="true" data-striped="true">
							<thead>
								<tr class="btn-primary">
								<th data-field="stateRd" data-radio="true"></th>  
										<th data-field="action2" data-align="center">Delete</th>
										<th data-field="srNo">Sr. No.</th>
										<th data-field="barcode">Barcode</th>
										<th data-field="style" data-sortable="false" class="span5">Style No</th>
										<th data-field="ktCol" data-sortable="false">Kt-Col</th>
										<th data-field="pcs" data-sortable="false">Pcs</th>
										<th data-field="grossWt" data-sortable="false">Gross Wt</th>
										<th data-field="netWt" data-sortable="false">Net Wt</th>
										<th data-field="metalValue" data-sortable="false">Metal Value</th>
										<th data-field="stoneValue" data-sortable="false">Stn Value</th>
										<th data-field="compValue" data-sortable="false">Comp Value</th>
										<th data-field="hallMark" data-sortable="false">HallMarking</th>
										<th data-field="lazerMark" data-sortable="false">LazerMarking</th>
										<th data-field="grading" data-sortable="false">Grading</th>
										<th data-field="engraving" data-sortable="false">Engraving</th>
										<th data-field="labourValue" data-sortable="false">Lab Value</th>
										<th data-field="setValue" data-sortable="false">Set Value</th>
										<th data-field="handlingCost" data-sortable="false">Hdlg Cost</th>
										<th data-field="fob" data-sortable="false">Fob</th>
										<th data-field="other" data-sortable="false">Other</th>
										<th data-field="discAmount" data-sortable="false">Disc Amt</th>
										<th data-field="finalPrice" data-sortable="false">Final Price</th>
										<th data-field="rLock" data-sortable="false"data-formatter="inputFormatter">Lock</th>  
										<th data-field="actionLock" data-align="center"></th>
									
									
								</tr>
							</thead>
						</table>
						
						<input type="hidden" id="saleDtPKId" name="saleDtPKId" />
					</div>
					
					
					
					
				</div>
				<div id="odImgDivId" class="col-xs-2 center-block">
					<div style="height:52px">&nbsp;</div>
					<div class="panel panel-primary" style="width:100%; height:245px">
						<div class="panel-body">
							<div style="width:80%; height:168px">
								<div class="row center-block">
									<span id='styleImgId'></span> 									
									<div id="tempImgDivId">
										
									</div>
								</div>
							</div>
							<div style="height:15px">&nbsp;</div>
							<div class="pull-left">
								<table id='stoneDtlsId' style="width:100%"
									class="line-items editable table table-bordered">
								</table>
							</div>
						</div>
					</div>
			
				
				<div class="row">
				<div>&nbsp;</div>
			</div>
					
					
					
				</div>
				
				
			</div>
			
			
		</div>
		

<div class="container-fluid">
			<div class="row">
				<div class="col-xs-10">
		
<!-- //hide on page load start here -->
	<div id="hideOnPageLoad" style="display: none" >
	
	
	
	
	<!------------------------------------------ consigMetal  -------------------------------------->
	
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Metal Details</span>
		</div>
	</div>

			
				<div>
					<table id="saleMetalTableId" data-toggle="table" 
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
								<th data-field="metalValue" data-sortable="false">Metal Value</th>
								<th data-field="mainMetal"  data-formatter="mianMetalFormatter">Main Metal</th>
							</tr>
						</thead>
					</table>
				</div>
			
			


		<!------------------------------------------ consigStnDt -------------------------------------->
		
			<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<div class="row">
			<div class="form-group">
				<div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Stone Details</span>
				</div>			
			</div>
		</div>

		
	
			
											
						
					<div >
						<table class="table-responsive"  id="saleStnDtTabId"
							data-toggle="table" 
							data-click-to-select="true"
							>
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="partNm" data-sortable="true">Part Name</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-sortable="false">Shape</th>
									<th data-field="quality" data-sortable="false">Quality</th>
									<th data-field="size" data-sortable="false">Size</th>
									<th data-field="sieve" data-sortable="false">Sieve</th>
									<th data-field="sizeGroup" data-sortable="false">SizeGroup</th>
									<th data-field="stone" data-sortable="false">Stone</th>
									<th data-field="carat" data-sortable="false">Carat</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="stoneValue" data-sortable="false">Stn Value</th>
									<th data-field="handlingRate" data-sortable="false">Hdlg Rate</th>
									<th data-field="handlingValue" data-sortable="false">Hdlg Value</th>
									<th data-field="setting" data-sortable="false">Setting</th>
									<th data-field="settingType" data-sortable="false">SetType</th>
									<th data-field="settingRate" data-sortable="false">Set Rate</th>
									<th data-field="settingValue" data-sortable="false">Set Value</th>
									<th data-field="centerStone" data-sortable="false" data-formatter="inputFormatter">Center Stone</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center" >Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
						
						<input type="hidden" id="saleStoneDtPKId" name="saleStoneDtPKId" />
						
				
		
			
				<div>
					<span style="display:inline-block; width: 7cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />		
				</div>
					
		


	
	
		<!------------------------------------------ saleCompDt -------------------------------------->
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		
		
		<div class="row">
			<div class="form-group">
				<div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Component Details</span>
				</div>			
			</div>
		</div>
		
	<div id="toolbarCompDt">
		
						
						
	</div>
					<div >
						<table  id="saleCompDtTabId"
							data-toggle="table" data-toolbar="#toolbarCompDt"
							 data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="compName" data-sortable="true">Component</th>
									<th data-field="kt" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="metalWt" data-sortable="false">metal Wt</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="compPcs" data-sortable="false">Qty</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center">Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
					
		
		
		
		
		
		
				
		<!-----------  saleLabDt ---------->
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		
		<div class="row">
		<div class="form-group">
		 <div class="col-lg-12 col-sm-12">
					<span class="col-lg-12 col-sm-12 label label-default text-right"
						style="font-size: 18px;">Labour Details</span>
				</div>
		</div>
		</div>
			 
								
					<div id="toolbarLabDt">
						
					</div>
				
					<div>
						<table id="saleLabDtTabId" data-toolbar="#toolbarLabDt"
							data-toggle="table" 
							data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th>
									<th data-field="metal" data-sortable="true">Metal</th>
									<th data-field="labourType" data-sortable="true">LabourType</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="perPcs" data-sortable="false" data-formatter="inputFormatter">Per Pcs</th>
									<th data-field="perGram" data-sortable="false" data-formatter="inputFormatter">Per Gram</th>
									<th data-field="percent" data-sortable="false" data-formatter="inputFormatter">Percentage</th>
									<th data-field="perCaratRate" data-sortable="false" data-formatter="inputFormatter">Per Carat Rate</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center">Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
						
						
						
					</div>
				
		
		
		</div> <!-- ending the hide on page load -->	
		
		
	
			
			</div>
			
				<div class="col-xs-2">
					<div class="row">
					
					<div class="col-sm-12 form-group">

						<label for="totalPcs" class="col-sm-4 control-label">Pcs</label>
						<div class="col-sm-8">
							<input type="number" id="totalPcs" class="form-control" name="totalPcs"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					<div class="col-sm-12 form-group">

						<label for="totMetalValue" class="col-sm-4 control-label">Metal Value</label>
						<div class="col-sm-8">
							<input type="number" id="totMetalValue" class="form-control" name="totMetalValue"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					<div class="col-sm-12 form-group">

						<label for="totStoneValue" class="col-sm-4 control-label">Stone Value</label>
						<div class="col-sm-8">
							<input type="number" id="totStoneValue" class="form-control" name="totStoneValue"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					
					<div class="col-sm-12 form-group">

						<label for="totSettingValue" class="col-sm-4 control-label">Setting Value</label>
						<div class="col-sm-8">
							<input type="number" id="totSettingValue" class="form-control" name="totSettingValue"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					<div class="col-sm-12 form-group">

						<label for="totHandlingValue" class="col-sm-4 control-label">Handling Value</label>
						<div class="col-sm-8">
							<input type="number" id="totHandlingValue" class="form-control" name="totHandlingValue"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					
						<div class="col-sm-12 form-group">

						<label for="totLabourValue" class="col-sm-4 control-label">Labour Value</label>
						<div class="col-sm-8">
							<input type="number" id="totLabourValue" class="form-control" name="totLabourValue"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					<div class="col-sm-12 form-group">

						<label for="totalFob" class="col-sm-4 control-label">FOB</label>
						<div class="col-sm-8">
							<input type="number" id="totalFob" class="form-control" name="totalFob"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					<div class="col-sm-12 form-group">

						<label for="otherCharges" class="col-sm-4 control-label">Other Charges</label>
						<div class="col-sm-8">
							<input type="number" id="otherCharges" class="form-control" name="otherCharges" value=0
								 style="text-align: right;" onchange="javascript:otherChargesFun();"/>
						</div>

					</div>
					
					
					
					<div class="col-sm-12 form-group" id="cgstDivId" style="display: none">

						<label for="cgst" class="col-sm-4 control-label">Cgst</label>
						<div class="col-sm-8">
							<input type="number" id="cgst" class="form-control" name="cgst"
							readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					
					<div class="col-sm-12 form-group" id="sgstDivId" style="display: none">

						<label for="sgst" class="col-sm-4 control-label">Sgst</label>
						<div class="col-sm-8">
							<input type="number" id="sgst" class="form-control" name="sgst"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
				
					
					
					<div class="col-sm-12 form-group" id="igstDivId" style="display: none">

						<label for="igst" class="col-sm-4 control-label">Igst</label>
						<div class="col-sm-8">
							<input type="number" id="igst" class="form-control" name="igst"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
				
					
					<div class="col-sm-12 form-group">

						<label for="totFinalPrice" class="col-sm-4 control-label">Final Price</label>
						<div class="col-sm-8">
							<input type="number" id="totFinalPrice" class="form-control" name="totFinalPrice"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					
					
					</div>
		
		
	</div>
			
			
		</div>
		
	
	
	
	</div>


	

</div>



</div>


<script type="text/javascript">

$(document)
.on(
	'submit',
	'form#saleMtFormId',
	 function(e){
		 $("#saleMtSubmitBtn").prop("disabled", true).addClass("disabled");
	});


/* var saleDtTableRow;
var saleDtLockStatus = 'null';
var finalPrice = "";
 $('#saleDtTblId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			
			saleDtTableRow = $element.attr('data-index');
			
			$('#saleDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
			
			var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
			saleDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
			finalPrice = jQuery.parseJSON(JSON.stringify(row)).finalPrice;
			$('#tempImgDivId').empty();
			var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
			$('#tempImgDivId').append(tempDiv);
			
			$('#hideOnPageLoad').css('display','block');
		
				
			popSaleMetal();
			popSaleStoneDetails();
			popSaleComponentDetails();
		
			popSaleLabDetails(); 
			
		
			
		}); */


	/* function popSaleDt() {
		
		$("#saleDtTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/saleDt/listing.html?mtId="+mtid,
						});
	}


	function popSaleMetal()
	{
		$("#saleMetalTableId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/saleMetalDt/listing.html?saleDtId="+$('#saleDtPKId').val(),
				});	
	}


	function popSaleStoneDetails(){
		
		$("#saleStnDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/saleStnDt/listing.html?saleDtId="+$('#saleDtPKId').val(),
				});	
		
	}

	
	function popSaleComponentDetails(){
		
		$("#saleCompDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/saleCompDt/listing.html?saleDtId="+$('#saleDtPKId').val(),
				});	
		
	}
	
	function popSaleLabDetails(){
		$("#saleLabDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/saleLabDt/listing.html?saleDtId="+$('#saleDtPKId').val(),
				});	
	} */
	


	function packingListPickup(){

		$('#myConsigPickupModal').modal('show');

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

	function lossPercFormatter(value, row, index){
		
		var tempId = 'lossPercval' + Number(index);

		var vId = row.id;
	    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:75px" value="'+ value+ '" onchange="javascript:updateLossPerc(this,'+vId+')"  />';
	}

	

	
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


	$('#saleStnDtTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#saleStnDtTabId").bootstrapTable('getData'));

				
				var vStones = 0.0;
				var vCarat = 0.0;
	
				$.each(JSON.parse(data), function(idx, obj) {
					
					
					vStones		+= Number(obj.stone);
					vCarat		+= Number(obj.carat);
					
					
				});
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
				
			});





	function deleteSaleDt(e,dtId){
		displayDlg(
				e,
				'javascript:deleteDt('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');

		}


	function deleteDt(dtId){
		
		$("#modalDialog").modal("hide");
						
						 $.ajax({
								url : "/jewels/marketing/transactions/saleDt/delete/"+ dtId + ".html",
								type : 'GET',
								success : function(data) {
									if(data === "1"){
										displayInfoMsg(event, this,'Delete sucessfully !');
										popSaleDt(disableFlg);
										
									}else{
										displayMsg(event, this, data);
									}
								}
							}); 
		
	
	}



function displaySaleDtSummary(){
		
		$('#mySaleDtSummaryModal').modal('show');
		 $('#mySaleDtSummaryModal').on('shown.bs.modal', function () {
			 popSaleSummary();
	        });
	

		}

	function popSaleSummary(){

		$.ajax({
			url:"/jewels/marketing/transactions/saleMt/dtSummary.html?mtId="+mtid,
			type :'GET',
			dataType:'JSON',
			success: function(data){
				
				if(data !== "-1"){
					
					$.each(data,function(k,v){
						$('#saleDtSummaryDivId  #'+k).val(v);
					});
					
				}else{
					displayMsg(this,null,'Error Contact Admin');
				}
				
			}	
		})
	}


	/* 
	$('#saleDtTblId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#saleDtTblId").bootstrapTable('getData'));

				
				var vTotPcs = 0.0;
				var vTotMetalValue = 0.0;
				var vTotStoneValue = 0.0;
				var vTotSettingValue = 0.0;
				var vTotHandlingValue = 0.0;
				var vTotLabourValue = 0.0;
				var vTotFob = 0.0;
				

				
				$.each(JSON.parse(data), function(idx, obj) {
					
					vTotPcs		+= Number(obj.pcs);
					vTotMetalValue		+= Number(obj.metalValue);
					vTotStoneValue		+= Number(obj.stoneValue);
					vTotSettingValue		+= Number(obj.setValue);
					vTotHandlingValue		+= Number(obj.handlingCost);
					vTotLabourValue		+= Number(obj.labourValue);
					vTotFob		+= Number(obj.fob);
					
				});
				
				
				$('#totalPcs').val(vTotPcs);
				$('#totMetalValue').val((vTotMetalValue).toFixed(2));
				$('#totStoneValue').val((vTotStoneValue).toFixed(2));
				$('#totSettingValue').val((vTotSettingValue).toFixed(2));
				$('#totHandlingValue').val((vTotHandlingValue).toFixed(2));
				$('#totLabourValue').val((vTotLabourValue).toFixed(2));
				$('#totalFob').val((vTotFob).toFixed(2));

				
			}); */







	
	

	var vvcgst = 0.0;
	var vvsgst = 0.0;
	var vvigst = 0.0;
	function applyGst(){

		if($('#hsnMast\\.id').val() != '' ){
		$.ajax({
			url:"/jewels/marketing/transactions/saleMt/applyGst.html?hsnId="+$('#hsnMast\\.id').val(),
			type :'GET',
			dataType:'JSON',
			success: function(data){

				var finalPrice = (Number($('#totalFob').val()) + Number($('#otherCharges').val())).toFixed(2);
				
				var vSgst = 0.0;
				var vCgst = 0.0;
				var vIgst = 0.0;
			
				$.each(data, function(k, v) {

					if(lookType === "Local"){
					if (k == "igst") {
						vIgst=(Number(finalPrice) * Number(v)/100).toFixed(0);
						vvigst = v;
						$('#igst').val(vIgst);
						$('#pIgst').val(vIgst);

						 $('#totFinalPrice').val((Number(finalPrice) + Number(vIgst)).toFixed(2));

						 $('#vFinalPrice').val($('#totFinalPrice').val()); 
						
					}
					if (k == "cgst") {
						vCgst=(Number(finalPrice) * Number(v)/100).toFixed(0);
						vvsgst = v;
						$('#cgst').val(vCgst);
						$('#pCgst').val(vCgst);

						 
						
					}

					if (k == "sgst") {
						vSgst=(Number(finalPrice) * Number(v)/100).toFixed(0);
						vvsgst = v;
						$('#sgst').val(vSgst);
						$('#pSgst').val(vSgst);

						 $('#totFinalPrice').val((Number(finalPrice) + Number(vSgst) + Number(vCgst)).toFixed(2));

						 $('#vFinalPrice').val($('#totFinalPrice').val());
						
					}
					}else{
						 $('#totFinalPrice').val((Number(finalPrice) ).toFixed(2));

						 $('#vFinalPrice').val($('#totFinalPrice').val()); 
						}

				});

						
				if(lookType === "Local"){			
				if(partyType === "Local"){
				
						 vIgst = 0.0;
						 vSgst =Number($('#sgst').val()).toFixed(0);
						 vCgst = Number($('#cgst').val()).toFixed(0);
						 
					}else{
						vIgst =  Number($('#igst').val()).toFixed(0);
						 vSgst = 0.0;
						 vCgst = 0.0;
					}
				}else{
					vIgst = 0.0;
					 vSgst = 0.0;
					 vCgst = 0.0;
					}


				 $('#pSgst').val(vSgst);
				 $('#pCgst').val(vCgst);
				 $('#pIgst').val(vIgst);

				$.ajax({
					url:"/jewels/marketing/transactions/saleMt/addSummaryDetails.html?mtId="+mtid+"&fob="+ $('#totalFob').val()+"&sgst="+vSgst
					+"&cgst="+ vCgst +"&igst="+vIgst+"&otherCharges="+$('#otherCharges').val()+"&finalPrice="+  $('#totFinalPrice').val(),
					type :'GET',
					success: function(data){
						
						if(data === "-1"){
							displayMsg(this,null,'Error Contact Admin');
						}
					}	
				})

				
			}	
		})
		}
		}

	


	function otherChargesFun(){

		setTimeout(function(){
				 applyGst();
				}, 100);
		
		var finalPrice = (Number($('#totalFob').val()) + Number($('#otherCharges').val())).toFixed(2);
		
		var vSgst = 0.0;
		var vCgst = 0.0;
		var vIgst = 0.0;
		var totprice = 0.0
		
	

		$('#vOtherCharges').val($('#otherCharges').val());
		
		}


	var dtdata='';
	
	$('#saleDtTblId').bootstrapTable({}).on('load-success.bs.table',
			function(e, data) {
		   
		dtdata = $("#saleDtTblId").bootstrapTable('getData').length;

		var data = JSON.stringify($("#saleDtTblId").bootstrapTable('getData'));

		
		var vTotPcs = 0.0;
		var vTotMetalValue = 0.0;
		var vTotStoneValue = 0.0;
		var vTotSettingValue = 0.0;
		var vTotHandlingValue = 0.0;
		var vTotLabourValue = 0.0;
		var vTotFob = 0.0;
		

		
		$.each(JSON.parse(data), function(idx, obj) {
			
			vTotPcs		+= Number(obj.pcs);
			vTotMetalValue		+= Number(obj.metalValue);
			vTotStoneValue		+= Number(obj.stoneValue);
			vTotSettingValue		+= Number(obj.setValue);
			vTotHandlingValue		+= Number(obj.handlingCost);
			vTotLabourValue		+= Number(obj.labourValue);
			vTotFob		+= Number(obj.fob);
			
		});

		


		setTimeout(function(){
		
		$('#totalPcs').val(vTotPcs);
		$('#totMetalValue').val((vTotMetalValue).toFixed(2));
		$('#totStoneValue').val((vTotStoneValue).toFixed(2));
		$('#totSettingValue').val((vTotSettingValue).toFixed(2));
		$('#totHandlingValue').val((vTotHandlingValue).toFixed(2));
		$('#totLabourValue').val((vTotLabourValue).toFixed(2));
		$('#totalFob').val(Math.round(vTotFob));

		}, 30);
		
		 if(dtdata >0){

			    $('#pPartyIds').val($('#saleDivId #party\\.id').val());
				$('#pLocationIds').val($('#saleDivId #location\\.id').val());
				
				$('#saleDivId #party\\.id').attr("disabled","disabled").trigger('chosen:updated');
				$('#saleDivId #location\\.id').attr("disabled","disabled").trigger('chosen:updated');
				
				}else{
					$('#saleDivId #party\\.id').removeAttr('disabled').trigger('chosen:updated');
					$('#saleDivId #location\\.id').removeAttr('disabled').trigger('chosen:updated');
		
					
				}
			
		  })
		  
		  
		  function salesReport(val){

			$
		.ajax({
			url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=sale"+"&opt="+val,
			type : 'POST',
			success : function(data, textStatus, jqXHR) {
				
				if(val === 2){
					$('#timeValCommonPdf').val(data);
					$("#generateDataRpt").trigger('click');
					
				}else{
					$('#pFileName').val(data);
					$("#generateExcelReportss").trigger('click');

					}	
				
			}
		});
		  }





function applyGstRates(){

	//	otherChargesFun();
		applyGst();
		

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

<script src="<spring:url value='/js/bootstrap/lodash.min.js' />"></script>
