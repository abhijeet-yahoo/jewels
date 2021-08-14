<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalStkTrfDtSummary.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>
<script src="/jewels/js/export/tableExport.min.js"></script>
<script src="/jewels/js/export/jspdf.min.js"></script>
<script src="/jewels/js/export/jspdf.plugin.autotable.js"></script>
<script src="/jewels/bootstrap-table-master/dist/extensions/export/bootstrap-table-export.js"></script> 




<style>
.bacground{
bgcolor:"red";
}

.mySelected{
    background-color: #FFB6C1;
}

</style>



<div id="stkTrfDivId">
<div class="panel panel-primary" style="width: 100%">
<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-3 col-sm-3 text-left"> <span
				style="font-size: 18px;">Inter Department Transfer </span>
			</label>
			<div class="text-right">
			
			<input type="button" value="Lock" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-info" id="lockInvoiceBtn" onClick="javascript:popLockInvoice();" />
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button" id="stkTrfListingBtnId"
					href="/jewels/marketing/transactions/stkTrfMt.html">Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="stkTrfMtSubmitBtn"  onclick="javascript:stkTrfMtSave()" />
			
			</div>

		</div>

	</div>
	
	<div class="form-group">
		<form:form commandName="stkTrfMt" id="stkTrfMtFormId"
			action="/jewels/marketing/transactions/stkTrfMt/add.html"
			cssClass="form-horizontal stkTrfMtForm">

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
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							No:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							Date:</label>
					</div>
					
				<!-- 	<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Party:</label>
					</div> -->
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">From Location:</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">To Location :</label>
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
						<form:select path="location.id" class="form-control" required="true" onchange="javascript:toDeptLocation();">
							<form:option value="" label=" Select Location " />
							<form:options items="${locationMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="toLocation.id" class="form-control" required="true">
							<form:option value="" label=" Select Location" />
							<form:options items="${departmentToMap}" />
						</form:select>
					</div>
					
					
				
				
				</div>
			</div>
			
				
	
			<div class="row">&nbsp;</div>
			
			<div class="row">
			<div class="col-xs-12">
			<div class="col-lg-1 col-sm-1"
								style="font-size: smaller; font-weight: bold;">
								<a onclick="javascript:popConsigMetalRate()">Metal Rates</a>
							</div>
			
				<form:input type="hidden" path="id"/>
					<form:input type="hidden" path="srNo"/>
				<input type="hidden" id="pLocationIds" name="pLocationIds" />
				<input type="hidden" id="pToLocationIds" name="pToLocationIds" />
				<input type="hidden" id="vTranDate" name="vTranDate" /></td>
				</div>
			</div>
		
		
		
		
		</form:form>
		
		
	
		
		
	</div>
	
</div>




	

<div id="stkTrfDtDivId" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-10">
					<div id="toolbar">
					<div class="row">
				
				
					<div class="col-sm-6">
					<input type="text" id="barcodeTxtId" name="barcodeTxtId" class="form-control" placeholder="Type Barcode To Add" onchange="javascript:popstkTrflistAdd()">
					</div>		
					
				
					
					<div class="col-sm-3">
					<a class="btn btn-info" style="font-size: 15px" type="button" id="stkTrfSummaryBtnId"
					onClick="javascript:displayDtSummary()"
					href="javascript:void(0)"><span
				></span>&nbsp;Summary</a>
					
					</div>
					
					
					</div>
					</div>
					<div>
						<table id="stkTrfDtTblId" data-toggle="table" data-toolbar="#toolbar" data-search="true"
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
						
						<input type="hidden" id="stkTrfDtPKId" name="stkTrfDtPKId" />
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
	
	<!------------------------------------------ stkTrfMetal  -------------------------------------->
	
	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Metal Details</span>
		</div>
	</div>

			
				<div>
					<table id="stkTrfMetalTableId" data-toggle="table" 
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
			
			


		<!------------------------------------------ stkTrfStnDt -------------------------------------->
		
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
						<table class="table-responsive"  id="stkTrfStnDtTabId"
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
						
						<input type="hidden" id="stkTrfStoneDtPKId" name="stkTrfStoneDtPKId" />
						
				
		
			
				<div>
					<span style="display:inline-block; width: 7cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />		
				</div>
			
			

		
		
		


	
	
		<!------------------------------------------ stkTrfCompDt -------------------------------------->
		
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
						<table  id="stkTrfCompDtTabId"
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
					
		
			
			
		
			
			
		
		
		
		
		
		
				
		<!-----------  stkTrfLabDt ---------->
		
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
						<table id="stkTrfLabDtTabId" data-toolbar="#toolbarLabDt"
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

















</div>







<script type="text/javascript">



canViewFlag=false;
var mtid;
var mode;
var disableFlg=false;
	$(document)
			.ready(
					function(e) {

						
						
						
//$('select').chosen();

$('#stkTrfDivId #party\\.id').chosen();
$('#stkTrfDivId #location\\.id').chosen();
$('#stkTrfDivId #hsnMast\\.id').chosen();
$("#stkTrfDivId #toLocation\\.id").chosen();

						
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
						
						
						
						$(".stkTrfMtForm")
								.validate(
										{
											rules : {
												invDate : {
													required : true,
												},
												
												'party.id' : {
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
							$("#stkTrfDtDivId").css('display', 'block');
							mtid="${mtid}";
							mode ="${model}";
							
							if(process('${currentDate}') > process($('#invDate').val())){
								popStkTrfDt(true);
								
								disableFlg = true;
								
								$('#stkTrfDivId .btn').hide();
								
								$('#stkTrfDivId').find('input, textarea, select').attr('disabled','disabled');
								$('#stkTrfDivId :input').attr('disabled',true);
								$('#stkTrfListingBtnId').show();
								$('#stkTrfSummaryBtnId').show();
								$('#barcodeTxtId').attr('readonly','readonly');
								
							}else{
								disableFlg = false;
								popStkTrfDt(false);
								} 
							
							
						}
						else{
					
							
							$("#vTranDate").val('${currentDate}');	
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
					
							
						}


						

						$("#barcodeTxtId")
						.autocomplete(
							{
								source : '<spring:url value='/marketing/transactions/barcode/autoFill/list.html' />?locationId='
									+ $('#location\\.id').val(),

								minLength : 2
							})
					
										

					});



						function process(date){
							   var parts = date.split("/");
							   return new Date(parts[2], parts[1] - 1, parts[0]);
							}


	function stkTrfMtSave() {
		
		if($(".stkTrfMtForm").valid()){
			$("form#stkTrfMtFormId").submit();
			
		}
		
	}


	  $(document)
		.on(
			'submit',
			'form#stkTrfMtFormId',
			 function(e){
				 $("#stkTrfMtSubmitBtn").prop("disabled", true).addClass("disabled");
			});


		var stkTrfDtTableRow;
		var stkTrfDtLockStatus = 'null';
		 $('#stkTrfDtTblId').bootstrapTable({}).on(
				'click-row.bs.table',
				function(e, row, $element) {
					
					stkTrfDtTableRow = $element.attr('data-index');
					
					$('#stkTrfDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
					
					var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
					stkTrfDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
					$('#tempImgDivId').empty();
					var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
					$('#tempImgDivId').append(tempDiv);
					
					$('#hideOnPageLoad').css('display','block');
				
						
					popStkTrfMetal();
					popStkTrfStoneDetails();
					popStkTrfComponentDetails();
				
					popStkTrfLabDetails(); 
					
				
					
				});


			function popStkTrfDt(disableFlg) {
				$("#stkTrfDtTblId")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/marketing/transactions/stkTrfDt/listing.html?mtId="+mtid+"&disableFlg="+disableFlg,
								});
			}


			function popStkTrfMetal()
			{
				$("#stkTrfMetalTableId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/stkTrfMetalDt/listing.html?stkTrfDtId="+$('#stkTrfDtPKId').val(),
						});	
			}


			function popStkTrfStoneDetails(){
				
				$("#stkTrfStnDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/stkTrfStnDt/listing.html?stkTrfDtId="+$('#stkTrfDtPKId').val()+"&disableFlg="+disableFlg,
						});	
				
			}

			
			function popStkTrfComponentDetails(){
				
				$("#stkTrfCompDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/stkTrfCompDt/listing.html?stkTrfDtId="+$('#stkTrfDtPKId').val()+"&disableFlg="+disableFlg,
						});	
				
			}
			
			function popStkTrfLabDetails(){
				$("#stkTrfLabDtTabId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/marketing/transactions/stkTrfLabDt/listing.html?stkTrfDtId="+$('#stkTrfDtPKId').val()+"&disableFlg="+disableFlg,
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


			$('#stkTrfStnDtTabId').bootstrapTable({})
			.on(
					'load-success.bs.table',
					function(e, data) {
						var data = JSON.stringify($("#stkTrfStnDtTabId").bootstrapTable('getData'));

						
						var vStones = 0.0;
						var vCarat = 0.0;
			
						$.each(JSON.parse(data), function(idx, obj) {
							
							
							vStones		+= Number(obj.stone);
							vCarat		+= Number(obj.carat);
							
							
						});
						
						$('#vTotalStones').val(" " + vStones);
						$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
						
					});


			function popstkTrflistAdd(){

				if($('#barcodeTxtId').val() !=''){
					
					
					$.ajax({
						url : "/jewels/marketing/transactions/stkTrfMt/addBarcodeItem.html",
						type : 'POST',
						data :{barcode :$('#barcodeTxtId').val(),mtId :mtid, tranType :"STOCKTRF"},
						success : function(data) {
							
							if(data==='1'){
								
								popStkTrfDt(disableFlg);
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


			function deleteStkTrfDt(e,dtId){
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
										url : "/jewels/marketing/transactions/stkTrfDt/delete/"+ dtId + ".html?tranType=STOCKTRF",
										type : 'GET',
										success : function(data) {
											if(data === "1"){
												displayInfoMsg(event, this,'Delete sucessfully !');
												popStkTrfDt(disableFlg);
												
												$('#hideOnPageLoad').css('display','none');
											}else{
												displayMsg(event, this, data);
											}
										}
									}); 
				
			
			}



			function displayDtSummary(){
				
				$('#myStkTrfDtSummaryModal').modal('show');
				 $('#myStkTrfDtSummaryModal').on('shown.bs.modal', function () {
					 popStkTrfSummary();
			        });
			

				}

			function popStkTrfSummary(){

				$.ajax({
					url:"/jewels/marketing/transactions/stkTrfMt/dtSummary.html?mtId="+mtid,
					type :'GET',
					dataType:'JSON',
					success: function(data){
						
						if(data !== "-1"){
							
							$.each(data,function(k,v){
								$('#stkTrfDtSummaryDivId  #'+k).val(v);
							});
							
						}else{
							displayMsg(this,null,'Error Contact Admin');
						}
						
					}	
				})
			}
	
			function toDeptLocation(){

				if($('#location\\.id').val() != ''){
				$
						.ajax({
							url:"/jewels/marketing/transactions/stkTrfMt/tolocationDropdown.html?locationId="+$('#location\\.id').val(),
							
							type : 'GET',
							success : function(data) {
								
								$("#toLocation\\.id").html(data);
								$("#toLocation\\.id").trigger("chosen:updated");
								
										}
						});
				}
				}



			var dtdata='';
			
			$('#stkTrfDtTblId').bootstrapTable({}).on('load-success.bs.table',
					function(e, data) {
				   
				dtdata = $("#stkTrfDtTblId").bootstrapTable('getData').length;
	
				 if(dtdata >0){

					 $('#pLocationIds').val($('#stkTrfDivId #location\\.id').val());
						$('#pToLocationIds').val($('#stkTrfDivId #toLocation\\.id').val());
					
						$('#stkTrfDivId #location\\.id').attr("disabled","disabled").trigger('chosen:updated');
						$('#stkTrfDivId #toLocation\\.id').attr("disabled","disabled").trigger('chosen:updated');
						
						}else{
							
							$('#stkTrfDivId #location\\.id').removeAttr('disabled').trigger('chosen:updated');
							$('#stkTrfDivId #toLocation\\.id').removeAttr('disabled').trigger('chosen:updated');
				
							
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

