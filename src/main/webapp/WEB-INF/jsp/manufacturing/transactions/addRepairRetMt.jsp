<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>


<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<div id="repairRetMtDivId">
<div class="panel panel-primary" style="width: 100%">
<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-6 col-sm-6 text-left"> <span
				style="font-size: 18px;">Repair/ Customer Stock Return</span>
			</label>
			<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="repairRetMtExcelPreviewBtn" onClick="javascript:repairRetReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="repairRetMtPreviewBtn" onClick="javascript:repairRetReport(2);" />
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button" id="repairRetListingBtn"
					href="/jewels/manufacturing/transactions/repairRetMt.html">Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="repairRetMtSubmitBtn"  onclick="javascript:repairRetSave()" />
			

			</div>



		</div>

	</div>
	
	<div class="form-group">
		<form:form commandName="repairRetMt" id="repairRetMtFormId"
			action="/jewels/manufacturing/transactions/repairRetMt/add.html"
			cssClass="form-horizontal repairRetMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success"> ${action}
							successfully!</div>
					</div>
				</div>
			</c:if>

			<!-- Column Sizing -->
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Voucher
							No:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Voucher
							Date:</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Party:</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Location:</label>
					</div>
					
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<form:input path="invNo" cssClass="form-control" autocomplete="off" readonly="true"/>
						<form:errors path="invNo" />
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:input path="invDate" cssClass="form-control" disabled="true"/>
						<form:errors path="invDate" />
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="party.id" class="form-control" required="true" onchange="javascript:hideDtDiv();" disabled="false">
							<form:option value="" label=" Select Party " />
							<form:options items="${allPartyMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="location.id" class="form-control" required="true" onchange="javascript:hideDtDiv();"  disabled="false">
							<form:option value="" label=" Select Location " />
							<form:options items="${locationMap}" />
						</form:select>
					</div>
					
					
					
				</div>
			</div>

		<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Description :</label>
					</div>
				</div>
			</div>

	<div class="row">
				<div class="col-xs-12">
				<div class="col-lg-3 col-sm-3">
					<form:select path="hsnMast.id" class="form-control">
							<form:option value="" label=" Select Description" />
							<form:options items="${hsnMap}" />
						</form:select>
				</div>
				</div>
			</div>
				
	
			<div class="row">&nbsp;</div>
		
			<div class="row">
			<div class="col-xs-12">
				
				<form:input type="hidden" path="id"/>
					<form:input type="hidden" path="srNo"/>
				<input type="hidden" id="pPartyIds" name="pPartyIds" />
				<input type="hidden" id="pLocationIds" name="pLocationIds" />
				<input type="hidden" id="vTranDate" name="vTranDate" />
				
				</div>
			</div>
		</form:form>
		
		 <!-- Download Common pdf Report -->
		
			<div style="display: none">		
				<form:form target="_blank"  id="styleNotMatchForm"
					action="/jewels/manufacturing/masters/reports/download/report.html"
					cssClass="form-horizontal orderFilter">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Report" class="btn btn-primary" id="generateDataRpt"/>
								<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
							</div>
						</div>
				</form:form>
			</div>
			
			
			
					 <!-- Download Common Excel Report -->
			 
			 <div style="display: none">
				<form:form 	action="/jewels/manufacturing/masters/reports/download/Common/excelReport.html"
						cssClass="form-horizontal">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Excel Report" class="btn btn-primary" id="generateExcelReportss"/>
							</div>
						</div>
						
					<input type='hidden' name='pFileName' id='pFileName'/>
						
				</form:form>
			 </div>
		
	</div>
	
</div>
</div>


<div id="repairRetDtDivId" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-10">
					<div id="toolbar">
					
					<div class="row">
					
					
					<div class="col-sm-4">
					
					<a class="btn btn-info" style="font-size: 15px" type="button" id="pickupBtnId"
					onClick="javascript:repairBarcodePickupList()"
					href="javascript:void(0)"><span
				></span>&nbsp;Pickup</a>
					</div>
					
					</div>
				
					
					
			
					
					
					</div>
					<div>
						<table id="repairRetDtTblId" data-toggle="table" data-toolbar="#toolbar" data-search="true"
							data-height="350" data-striped="true" data-click-to-select="true">
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
						
						<input type="hidden" id="repairDtPKId" name="repairDtPKId" />
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
		

		
<!-- //hide on page load start here -->
	<div id="hideOnPageLoad" style="display: none" >
	
	<!------------------------------------------RepairRetMetal  -------------------------------------->
	
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Metal Details</span>
		</div>
	</div>

			
				<div>
					<table id="repairRetMetalTableId" data-toggle="table" 
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
								<th data-field="lossPerc" data-sortable="false" >Loss %</th>
								<th data-field="metalValue" data-sortable="false">Metal Value</th>
								<th data-field="mainMetal"  data-formatter="mianMetalFormatter">Main Metal</th>
							</tr>
						</thead>
					</table>
				</div>
			
			


		<!------------------------------------------ repairRetStnDt -------------------------------------->
		
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
						<table class="table-responsive"  id="repairRetStnDtTabId"
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
									<th data-field="rate" data-sortable="false"  >Rate</th>
									<th data-field="stoneValue" data-sortable="false">Stn Value</th>
									<th data-field="handlingRate" data-sortable="false">Hdlg Rate</th>
									<th data-field="handlingValue" data-sortable="false">Hdlg Value</th>
									<th data-field="setting" data-sortable="false">Setting</th>
									<th data-field="settingType" data-sortable="false">SetType</th>
									<th data-field="settingRate" data-sortable="false" >Set Rate</th>
									<th data-field="settingValue" data-sortable="false">Set Value</th>
									<th data-field="centerStone" data-sortable="false" data-formatter="inputFormatter">Center Stone</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<th data-field="actionLock" data-align="center" >Lock-Unlock</th>
									
								</tr>
							</thead>
						</table>
					</div>
						
						<input type="hidden" id="packStoneDtPKId" name="packStoneDtPKId" />
						
				
		
			
				<div>
					<span style="display:inline-block; width: 7cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />		
				</div>
			
			

		
		
		


	
	
		<!------------------------------------------ repairRetCompDt -------------------------------------->
		
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
						<table  id="repairRetCompDtTabId"
							data-toggle="table" data-toolbar="#toolbarCompDt"
							 data-click-to-select="true">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-radio="true"></th>
									<th data-field="action1" data-align="center">Edit</th>
									<th data-field="compName" data-sortable="true">Component</th>
									<th data-field="kt" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="metalWt" data-sortable="false">metal Wt</th>
									<th data-field="rate" data-sortable="false" data-formatter="compRateFormatter">Rate</th>
									<th data-field="compPcs" data-sortable="false">Qty</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="perPcs" data-sortable="false" data-formatter="inputFormatter">Per Pcs</th>
									<th data-field="perGram" data-sortable="false" data-formatter="inputFormatter">Per Gram</th>
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

canViewFlag=false;
var disableFlg = false;
var mtid;
var mode;
	$(document)
			.ready(
					function(e) {
						
						
//$('select').chosen();

$('#repairRetMtDivId #party\\.id').chosen();
$('#repairRetMtDivId #location\\.id').chosen();
$('#repairRetMtDivId #hsnMast\\.id').chosen();


						$.validator.setDefaults({ ignore: ":hidden:not(select)" });
					
											
					// validation of chosen on change
						if ($("select.form-control").length > 0) {
						    $("select.form-control").each(function() {
						        if ($(this).attr('required') !== undefined) {
						            $(this).on("change", function() {
						                $(this).valid();
						            });
						        }
						    });
						}
						
						
						
						$(".repairRetMtForm")
								.validate(
										{
											rules : {
												invDate : {
													required : true,
												},
												'party.id' : {
													required : true,
												},
												'location.id' : {
													required : true,
												},
												
									
											},

											messages : {
												
											}

										});
						
						
						
			

						$("#invDate").datepicker({
							dateFormat : 'dd/mm/yy'
						});
						
			

						if (window.location.href.indexOf('edit') != -1) {
							$("#vTranDate").val($("#invDate").val());
							
							$("#repairRetDtDivId").css('display', 'block');
							mtid="${mtid}";
							mode ="${model}";

							if(process('${currentDate}') > process($('#invDate').val())){

								disableFlg = true;
								popRepairRetDt(disableFlg);
								
								$('#pickupBtnId').hide();
								$('#repairRetMtSubmitBtn').hide();
								$('#packMtExcelPreviewBtn').show();
								$('#packMtPreviewBtn').show();	
								
							}else{
								disableFlg = false;
								popRepairRetDt(disableFlg);
								}
							
						}
						else{

							$("#vTranDate").val('${currentDate}');					
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
					
							
						}

						

					});

						
		

		function process(date){
			   var parts = date.split("/");
			   return new Date(parts[2], parts[1] - 1, parts[0]);
			}
	
	
	
	var packDtTableRow;
	var packDtLockStatus = 'null';
	var finalPrice = "";
	var dtdata='';
	 $('#repairRetDtTblId').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
				
				packDtTableRow = $element.attr('data-index');
				
				$('#repairDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
				
				var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
				packDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
				finalPrice = jQuery.parseJSON(JSON.stringify(row)).finalPrice;
				$('#tempImgDivId').empty();
				var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
				$('#tempImgDivId').append(tempDiv);
				
				
				$('#hideOnPageLoad').css('display','block');
			
					
				popRepairRetMetal();
				popRepairRetStnDetails();
				popRepairRetComptDetails();
			
			//	popPackLabDetails(disableFlg);
				
			
				
			});




	 var dtdata='';
		
		$('#repairRetDtTblId').bootstrapTable({}).on('load-success.bs.table',
				function(e, data) {
			   
			dtdata = $("#repairRetDtTblId").bootstrapTable('getData').length;

			
				
			 if(dtdata >0){

				 $('#pPartyIds').val($('#repairRetMtDivId #party\\.id').val());
					$('#pLocationIds').val($('#repairRetMtDivId #location\\.id').val());
					
					
					$('#repairRetMtDivId #party\\.id').attr("disabled","disabled").trigger('chosen:updated');
					$('#repairRetMtDivId #location\\.id').attr("disabled","disabled").trigger('chosen:updated');
					
					}else{
						$('#repairRetMtDivId #party\\.id').removeAttr('disabled').trigger('chosen:updated');
						$('#repairRetMtDivId #location\\.id').removeAttr('disabled').trigger('chosen:updated');
			
						
					}
				
			  }) 
	
	
	
	  $(document)
		.on(
			'submit',
			'form#repairRetMtFormId',
			 function(e){
				 $("#packMtSubmitBtn").prop("disabled", true).addClass("disabled");
			});
	
	
	
		function repairRetSave() {
			
			if($(".repairRetMtForm").valid()){
				$("form#repairRetMtFormId").submit();
				
			}
		}
	

	function popRepairRetDt(dateFlag) {
		$("#repairRetDtTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/repairRetDt/listing.html?mtId="+mtid+"&flag="+dateFlag,
						});

	
		
	}
	
	function popRepairRetMetal()
	{
		$("#repairRetMetalTableId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/repairRetMetalDt/listing.html?dtId="+$('#repairDtPKId').val(),
				});	
	}
	
	function popRepairRetStnDetails(){
		
		$("#repairRetStnDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/repairRetStnDt/listing.html?dtId="+$('#repairDtPKId').val(),
				});	
		
	}

	
	function popRepairRetComptDetails(){
		
		$("#repairRetCompDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/repairRetCompDt/listing.html?dtId="+$('#repairDtPKId').val()+"&disableFlg="+disableFlg,
				});	
		
	}
	
	function popPackLabDetails(disableFlg){
		$("#packLabDtTabId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/packLabDt/listing.html?packDtId="+$('#repairDtPKId').val()+"&disableFlg="+disableFlg,
				});	
	}
	
	
	

	
	
	function deleteDt(dtId){
		
		
		$("#modalDialog").modal("hide");
		
							
						 $.ajax({
								url : "/jewels/manufacturing/transactions/repairRetDt/delete/"+ dtId + ".html",
								type : 'GET',
								success : function(data) {
									if(data === "1"){
										displayInfoMsg(event, this,'Delete sucessfully !');
										popRepairRetDt(disableFlg);
									}else{
										displayMsg(event, this, data);
									}
								}
							}); 
		
	
	}
	
	function deleteRepairRetDt(e,dtId){

			
			displayDlg(
					e,
					'javascript:deleteDt('+dtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
				
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
	


	

	function popPackMetalRate(){
		$('#myPackRateModal').modal('show');
		popPackMetalRateTbl();
		
		setTimeout(function(){
			$('#packMetalRateIdTbl').bootstrapTable('resetView');
		}, 300);
		
	}
	
	
	
	function popPackQualityRate(){
		
		$('#myQltyRateChng').modal('show');
		 $('#myQltyRateChng').on('shown.bs.modal', function () {
			 shapeDropdown();
			 
	        });
		
		
	}
	
	
	
	$('#repairRetStnDtTabId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#repairRetStnDtTabId").bootstrapTable('getData'));

				
				var vStones = 0.0;
				var vCarat = 0.0;
	
				$.each(JSON.parse(data), function(idx, obj) {
					
					
					vStones		+= Number(obj.stone);
					vCarat		+= Number(obj.carat);
					
					
				});
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
				
			});
	
		
		


		/*  function displayDtSummary(){
			
			$('#myPackDtSummaryModal').modal('show');
			 $('#myPackDtSummaryModal').on('shown.bs.modal', function () {
				 popPackSummary();
		        });
		

			} */

		/* function popPackSummary(){

			$.ajax({
				url:"/jewels/marketing/transactions/packMt/dtSummary.html?mtId="+mtid,
				type :'GET',
				dataType:'JSON',
				success: function(data){
					
					if(data !== "-1"){
						
						$.each(data,function(k,v){
							
							$('#packDtSummaryDivId  #'+k).val(v);
						});
						
					}else{
						displayMsg(this,null,'Error Contact Admin');
					}
					
				}	
			})
		}  */


	function repairRetReport(val){
		$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
		
			$
			.ajax({
				url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=repairRet"+"&opt="+val,
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
	
	
	
	function hideDtDiv(){
		$('#repairRetDtDivId').css('display','none');
		}
	
	
	
	function repairBarcodePickupList(){

		 window.location.href = "/jewels/manufacturing/transactions/repairRetPickup.html?mtid="+mtid+"&partyId="+$('#party\\.id').val()
		 +"&locationId="+$('#location\\.id').val();
		}
	
	
	
	
			
	
</script>


<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
