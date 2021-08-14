<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<div id="consigDtDivId" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-10">
					<div id="toolbar1">
		
					<div class="row">
				
				
					<div class="col-sm-6">
					<input type="text" id="barcodeTxtId" name="barcodeTxtId" class="form-control" placeholder="Type Barcode To Add" onchange="javascript:popConsiglistAdd()">
					</div>		
					
					<div class="col-sm-3">
					<a class="btn btn-info" style="font-size: 15px" type="button" id="consigMtpickUpbtn"
					onClick="javascript:packingListPickup()"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Pickup</a>
					
					</div>
					
					<div class="col-sm-3">
					<a class="btn btn-info" style="font-size: 15px" type="button" id="consigSummaryBtnId"
					onClick="javascript:displayDtSummary()"
					href="javascript:void(0)"><span></span>&nbsp;Summary</a>
					
					</div>
				 	
					<!--
					<a class="btn btn-info" style="font-size: 15px" type="button"
					onClick="javascript:locationWisePickup()"
					href="javascript:void(0)"><span
					class="glyphicon glyphicon-plus"></span>&nbsp;Add</a> -->
					
					
					</div>
					</div>
					
					<div>
						<table id="consigDtTblId" data-toggle="table" data-toolbar="#toolbar1" data-search="true"
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
						
						<input type="hidden" id="consigDtPKId" name="consigDtPKId" />
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
					
					
					
			<div class="row" >
				<div class="form-group">
					<div class="col-xs-1">
						<input type="button" value="Apply Rate" class="btn btn-info" style="width: 190px" onclick="javascript:applyRate()"/>
					</div>
				</div>
				</div>
				
				<div class="row">
				<div>&nbsp;</div>
			</div>
		
					
				</div>
				
				
				
			</div>

			
		</div>
		

		
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
					<table id="consigMetalTableId" data-toggle="table" 
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
						<table class="table-responsive"  id="consigStnDtTabId"
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
						
						<input type="hidden" id="consigStoneDtPKId" name="consigStoneDtPKId" />
						
				
		
			
				<div>
					<span style="display:inline-block; width: 7cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />		
				</div>
			
			

	
		<!------------------------------------------ consigCompDt -------------------------------------->
		
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
						<table  id="consigCompDtTabId"
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
					
				
		<!-----------  consigLabDt ---------->
		
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
						<table id="consigLabDtTabId" data-toolbar="#toolbarLabDt"
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


	

</div>



<script type="text/javascript">


$(document)
.ready(
		function(e) {

			$("#barcodeTxtId")
			.autocomplete(
				{
					source : '<spring:url value='/marketing/transactions/barcode/autoFill/list.html' />?locationId='
						+ $('#location\\.id').val(),

					minLength : 2
				})
		
							
		});


var consigDtTableRow;
var consigDtLockStatus = 'null';
var finalPrice = "";
 $('#consigDtTblId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			
			consigDtTableRow = $element.attr('data-index');
			
			$('#consigDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
			
			var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
			consigDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
			finalPrice = jQuery.parseJSON(JSON.stringify(row)).finalPrice;
			$('#tempImgDivId').empty();
			var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
			$('#tempImgDivId').append(tempDiv);
			
			$('#hideOnPageLoad').css('display','block');
		
				
			popConsigMetal();
			popConsigStoneDetails();
			popConsigComponentDetails();
			popConsigLabDetails(); 
			
		
			
		});


	function popConsigDt(disableFlg) {
		$("#consigDtTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/consigDt/listing.html?mtId="+mtid+"&disableFlg="+disableFlg,
						});
	}


	function popConsigMetal()
	{
		$("#consigMetalTableId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/consigMetalDt/listing.html?consigDtId="+$('#consigDtPKId').val(),
				});	
	}


	function popConsigStoneDetails(){
		
		$("#consigStnDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/consigStnDt/listing.html?consigDtId="+$('#consigDtPKId').val(),
				});	
		
	}

	
	function popConsigComponentDetails(){
		
		$("#consigCompDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/consigCompDt/listing.html?consigDtId="+$('#consigDtPKId').val(),
				});	
		
	}
	
	function popConsigLabDetails(){
		$("#consigLabDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/consigLabDt/listing.html?consigDtId="+$('#consigDtPKId').val()+"&disableFlg="+disableFlg,
				});	
	}
	


	function packingListPickup(){

		$('#myPackingListModal').modal('show');

		setTimeout(function(){
			popPackingPickList();
			
			$('#pakingListDtTblId').bootstrapTable('resetView');
		}, 300);
		}


	function locationWisePickup(){
		$('#myLocationWisePickupModal').modal('show');
		 $('#myLocationWisePickupModal').on('shown.bs.modal', function () {
			 popDeptTo(); 	
	        });
		}


	function popDeptTo() {
	
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/consigMt/locationDropdown.html' />',
					type : 'GET',
					success : function(data) {
						
						$("#locationnm").html(data);
						$("#locationnm").trigger("chosen:updated");
						
								}
				});
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


	$('#consigStnDtTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#consigStnDtTabId").bootstrapTable('getData'));

				
				var vStones = 0.0;
				var vCarat = 0.0;
	
				$.each(JSON.parse(data), function(idx, obj) {
					
					
					vStones		+= Number(obj.stone);
					vCarat		+= Number(obj.carat);
					
					
				});
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
				
			});


	function popConsiglistAdd(){

		if($('#barcodeTxtId').val() !=''){
			
			
			$.ajax({
				url : "/jewels/marketing/transactions/consigMt/addBarcodeItem.html",
				type : 'POST',
				data :{barcode :$('#barcodeTxtId').val(),mtId :mtid,partyId:$('#party\\.id').val(),locationId:$('#location\\.id').val(),employeeId:$('#employee\\.id').val()},
				success : function(data) {
					
					if(data==='1'){
						
						popConsigDt(disableFlg);
						$('#barcodeTxtId').val('')
						
					}else{
						displayMsg(event, this, data);
					}
					
					
				

				}
			});
			
		}else{
			displayMsg(event, this, 'Enter Barcode To Add');
		}
				

		}


	function deleteConsigDt(e,dtId){
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
								url : "/jewels/marketing/transactions/consigDt/delete/"+ dtId + ".html",
								type : 'GET',
								success : function(data) {
									if(data === "1"){
										displayInfoMsg(event, this,'Delete sucessfully !');
										popConsigDt(disableFlg);
									}else{
										displayMsg(event, this, data);
									}
								}
							}); 
		
	
	}



	function displayDtSummary(){
		
		$('#myConsigDtSummaryModal').modal('show');
		 $('#myConsigDtSummaryModal').on('shown.bs.modal', function () {
			 popConsigSummary();
	        });
	

		}

	function popConsigSummary(){

		$.ajax({
			url:"/jewels/marketing/transactions/consigMt/dtSummary.html?mtId="+mtid,
			type :'GET',
			dataType:'JSON',
			success: function(data){
				
				if(data !== "-1"){
					
					$.each(data,function(k,v){
						$('#consigDtSummaryDivId  #'+k).val(v);
					});
					
				}else{
					displayMsg(this,null,'Error Contact Admin');
				}
				
			}	
		})
	}



	function consigReport(val){
		
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		$
		.ajax({
			url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=consig"+"&opt="+val,
			type : 'POST',
			success : function(data, textStatus, jqXHR) {
				$.unblockUI();
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

	function consigLooseStnReport(val){
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		$
		.ajax({
			url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=consigLooseStn"+"&opt="+val,
			type : 'POST',
			success : function(data, textStatus, jqXHR) {
				$.unblockUI();
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


	function consigLooseMetalReport(val){
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		$
		.ajax({
			url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=consigLooseMetal"+"&opt="+val,
			type : 'POST',
			success : function(data, textStatus, jqXHR) {
				$.unblockUI();
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




	var dtdata='';
	
	$('#consigDtTblId').bootstrapTable({}).on('load-success.bs.table',
			function(e, data) {
		   
		dtdata = $("#consigDtTblId").bootstrapTable('getData').length;

		

			
		 if(dtdata >0){

			 $('#pPartyIds').val($('#consigDivId #party\\.id').val());
				$('#pLocationIds').val($('#consigDivId #location\\.id').val());
				$('#pEmployeeIds').val($('#consigDivId #employee\\.id').val());
				
				$('#consigDivId #party\\.id').attr("disabled","disabled").trigger('chosen:updated');
				$('#consigDivId #location\\.id').attr("disabled","disabled").trigger('chosen:updated');
				$('#consigDivId #employee\\.id').attr("disabled","disabled").trigger('chosen:updated');
				
				}else{
					$('#consigDivId #party\\.id').removeAttr('disabled').trigger('chosen:updated');
					$('#consigDivId #location\\.id').removeAttr('disabled').trigger('chosen:updated');
					$('#consigDivId #employee\\.id').removeAttr('disabled').trigger('chosen:updated');
		
					
				}
			
		  })




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

