<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalGrecDt.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<c:set var="optionText" value="Delete" />



<div id="grecMtMainDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<div>
			<label class="col-lg-5 text-left">
				<span style="font-size: 18px;">Goods Receipt</span>
			</label>
			<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="grecMtExcelPreviewBtn" onClick="javascript:grecListReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="grecMtPreviewBtn" onClick="javascript:grecListReport(2);" />
			
			
				<input type="button" value="Save" style="font-size: 14px" class="btn btn-xs btn-warning" onClick="javascript:grecMtSave();" id="grecMtSubmitBtn" /> 
				<a id="orderListingBtnId" class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					href="/jewels/marketing/transactions/grecMt.html">Listing</a>
			</div>
		</div>
	</div>


<c:if test="${success eq true}">
	<div class="col-xs-12">
		<div class="alert alert-success">Goods Receipt ${action} successfully!</div>
	</div>
</c:if>


<div class="form-group" id="grecMtDivId">
		<form:form commandName="grecMt" id="grecMtFormId"
			action="/jewels/marketing/transactions/grecMt/add.html"
			cssClass="form-horizontal grecMtForm">

			<!-- Column Sizing -->
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">GRN
							No:</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">GRN
							Date:</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right"> Type:</label>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Party:</label>
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
						<form:input path="invDate" cssClass="form-control" disabled="true" />
						<form:errors path="invDate" />
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="orderType.id" class="form-control" required="true">
							<form:option value="" label="- Select Type -" />
						<form:options items="${orderTypeMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="party.id" class="form-control" required="true">
							<form:option value="" label=" Select Party " />
							<form:options items="${partyMap}" />
						</form:select>
					</div>
					
					
					
				
				
				</div>
			</div>
				
				
				<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Location
							:</label>
					</div>
					
					</div>
					</div>
					
					<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<form:select path="location.id" class="form-control" required="true">
							<form:option value="" label=" Select Location " />
							<form:options items="${locationMap}" />
						</form:select>
					</div>
					
					</div>
					</div>
			
			<div class="row">
			<div class="col-xs-12">
			
			
				<form:input type="hidden" path="id"/>
				<form:input type="hidden" path="srNo"/>
				<input type="hidden" id="grecMtPartyId" name="grecMtPartyId" />
				<input type="hidden" id="pOrderTypeIds" name="pOrderTypeIds" />
				<input type="hidden" id="vTranDate" name="vTranDate" />
				<input type="hidden" id="pLocationIds" name="pLocationIds" />
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


<div id="openOnEdit" class="form-group" style="display: none;">
	<div class="row">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12" align="left">
				 <span class="col-sm-12 label label-default " 
					style="font-size: 18px;margin-left:auto; "> Details</span>
			</div>
		</div>
	</div>

	<div class="form-group" id="dsPId">
		<div class="container-fluid">
			<div class="row" id="forGrecDt" style="font-size: smaller;font-weight: bold;">
				<div id="gsDivId" class="col-xs-10">
					<div id="toolbarDt">
							<!-- <a id="dtAddBtnId" class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:addBarcode()"><span
										class="glyphicon glyphicon-plus"></span>&nbsp;Add Barcode
							</a> -->
							
							<a id="ordPickUpBtnId" class="btn btn-info" style="font-size: 15px" type="button" onClick="javascript:popOrderPickup()"><span
										class="glyphicon glyphicon-plus"></span>&nbsp;Order Pickup
							</a>
							
							<a id="applyBarcodeBtnId" class="btn btn-success" style="font-size: 15px" type="button" onClick="javascript:applyBarcodeFn()">
										Generate Barcode
							</a>
							
					</div>
					<div>
						<table  id="grecDtTabId" data-toggle="table" data-toolbar="#toolbarDt" data-search="true"
								data-side-pagination="server" data-click-to-select="true"
								data-select-item-name="radioNameGrecDt"
								data-pagination="true" data-height="450">
							<thead>
								<tr>
									<th data-field="stateRd" data-radio="true"></th>
									<th data-field="action2" data-align="center">Delete</th>
									
									<th data-field="barcode" data-sortable="false">Barcode</th>
									<th data-field="style"  data-sortable="false">Style No</th>
									<th data-field="ktCol" data-sortable="false">Kt-Col</th>
									<th data-field="grossWt" data-sortable="false">GrossWt</th>
									<th data-field="netWt" data-sortable="false">NetWt</th>
									<th data-field="metalValue" data-sortable="false">Metal Val</th>
									<th data-field="stoneValue" data-sortable="false">Stn Val</th>
									 <th data-field="compValue" data-sortable="false">find Val</th>
									<th data-field="labourValue" data-sortable="false">Lab Val</th>								
									<th data-field="finalPrice" data-sortable="false">FinalPrice</th>	
								
								</tr>
							</thead>
						</table>
						
						<div>
						<label for="inputLabel3" class=".col-lg-2 text-right">Remark:</label>
						<input type="text" class="form-control" id="itemremark" name="itemremark"  readonly="readonly">
						
						</div>
					</div>
					<input type="hidden" id="grecDtPKId" name="grecDtPKId" />
				</div>



				<div id="gsImgDivId" class="col-xs-2 center-block">
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
					
					
				</div>
				
			</div>

						
		</div>

		
	</div>
	
	
	
	
</div>


<!------------------ hide on page load start here------------------------------- -->
<div id="hideOnPageLoad" style="display: none">

													<!--------------------- grecMetalDt --------------------->
	<div class="row">
		<div class="form-group">
			<div class="col-lg-12 col-sm-12">
				<span class="col-lg-12 col-sm-12 label label-default text-right"
					style="font-size: 18px;">Metal Details</span>
			</div>
		</div>
	</div>


	<div class="form-group" id="metalDivId">
		<div class="container-fluid">
			<div class="row" id="forGrecMetal" style="font-size: smaller;font-weight: bold;">
				<div class="col-xs-12">
					<div>
						<table id="grecMetalTableId" data-toggle="table" data-toolbar="#toolbarGrecMetal">
							<thead>
								<tr>
									<th data-field="partNm" data-sortable="false">PartNm</th>
									<th data-field="purity" data-sortable="false">Purity</th>
									<th data-field="color" data-align="left">Color</th>
									<th data-field="qty" data-sortable="false">Qty</th>
									<th data-field="metalWt" data-sortable="false">Metal Wt</th>
									<th data-field="metalRate" data-sortable="false">Metal Rate</th>
									<th data-field="perGramRate" data-sortable="false">Per Gram Rate</th>
									<th data-field="lossPerc" data-sortable="false">Loss %</th>
									<th data-field="metalValue" data-sortable="false">Metal Value</th>
									<th data-field="mainMetal" data-formatter="inputFormatter" >Main Metal</th>
								</tr>
							</thead>
						</table>
				</div>
				
			</div>
		
		</div>

	</div>

</div> <!-- End of MetalDivId -->




<div class="row">
		<div class="col-sm-12">&nbsp;</div>
	</div>


	
	<!------------------------------------------ grecStnDt -------------------------------------->

		
		<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Stone Details</span>
		</div>
	</div>
		
		
	<div id="csDivId" class="col-xs-12">
		<div class="container-fluid">
			<div class="row" style="font-size: smaller;font-weight: bold;">
						
						<div id="toolbarStnDt">
						
					</div>
						
					<div>
						<table id="grecStnDtTabId"
							data-toggle="table" data-toolbar="#toolbarStnDt"
							 data-click-to-select="true"
							data-select-item-name="radioNameOrderStnDt"	>
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<th data-field="partNm" data-sortable="true">Part</th>
									<th data-field="stoneType" data-sortable="true">StoneType</th>
									<th data-field="shape" data-sortable="false">Shape</th>
									<th data-field="subShape" data-sortable="false">Sub Shape</th>
									<th data-field="quality" data-sortable="false">Quality</th>
									<th data-field="size" data-sortable="false">Size</th>
									<th data-field="sieve" data-sortable="false">Sieve</th>
									<th data-field="sizeGroup" data-sortable="false">SizeGroup</th>
									<th data-field="stone" data-sortable="false">Stone</th>
									<th data-field="carat" data-sortable="false">Carat</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="stoneValue" data-sortable="false">StoneValue</th>
									<th data-field="handlingRate" data-sortable="false">HandlingRate</th>
									<th data-field="handlingValue" data-sortable="false">HandlingValue</th>
									<th data-field="setting" data-sortable="false">Setting</th>
									<th data-field="settingType" data-sortable="false">SettingType</th>
									<th data-field="diaCateg" data-sortable="false">Dia Category</th>
									<th data-field="settingRate" data-sortable="false">SettingRate</th>
									<th data-field="settingValue" data-sortable="false">SettingValue</th>
									
								</tr>
							</thead>
						</table>
					</div>
						<input type="hidden" id="grecStnDtPkId" name="grecStnDtPkId" />
						
				</div>
			</div>
			
				<div class="row">
					
					<span style="display:inline-block; width: 6cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />	
				</div>
			
			
	 			<!-- entry for orderStnDt -->
	 			<div class="form-group">
					<div id="rowGrecStoneDtId" style="display: none">
					</div>
				</div>
	 
	 
	    </div>
	


 																<!-- GrecCompdt -->
	
	
	
	
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

	<div id="DCId">
		<div class="container-fluid" style="font-size: smaller;font-weight: bold;">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbarCompDt">
				</div>
					<div>
						<table  id="grecCompDtTabId"
							data-toggle="table" data-toolbar="#toolbarCompDt"
							data-click-to-select="true"
							data-select-item-name="radioNameGrecCompDt">
							<thead>
								<tr>
									<th data-field="state" data-radio="true"></th>
									<!-- <th data-field="action1" data-align="center">Edit</th>
									<th data-field="action2" data-align="center">Delete</th> -->
									<th data-field="compName" data-sortable="true">Component</th>
									<th data-field="kt" data-sortable="false">Purity</th>
									<th data-field="color" data-sortable="false">Color</th>
									<th data-field="metalWt" data-sortable="false">metalWt</th>
									<th data-field="rate" data-sortable="false">Rate</th>
									<th data-field="compQty" data-sortable="false">Pcs</th>
									<th data-field="value" data-sortable="false">Value</th>
									<th data-field="rLock" data-sortable="false" data-formatter="inputFormatter">Lock</th>
									<!-- <th data-field="actionLock" data-align="center">Lock-Unlock</th> -->
									
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>

		
		<!-- entry for grecCompDt -->
 			<div class="form-group">
				<div id="rowOrderCompDtId">
				</div>
			</div>
		
		
	</div>
	
	







</div>



<script type="text/javascript">

var canViewFlag = false;
var disableFlg = false;
var mtid;
$(document).ready(
		function(e) {
			$('#grecMtDivId #party\\.id').chosen();
			$('#grecMtDivId #orderType\\.id').chosen();
			$('#grecMtDivId #location\\.id').chosen();
			
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
			
			
			$(".grecMtForm").validate(
					{
						rules : {

								invDate : {
								required : true,
							},
							
						
							
						},
						highlight : function(element) {
							$(element).closest('.form-group').removeClass(
									'has-success').addClass('has-error');
						},
						unhighlight : function(element) {
							$(element).closest('.form-group').removeClass(
									'has-error').addClass('has-success');
						}
					});

		

			$("#invDate").datepicker({
				dateFormat : 'dd/mm/yy'
			});

		
	 	if (window.location.href.indexOf('edit') != -1) {
	 		$("#vTranDate").val($("#invDate").val());
				
	 		mtid ="${mtid}";
				
				$('#openOnEdit').css('display','block');
				
			//	popGrecDetails();

				if(process('${currentDate}') > process($('#invDate').val())){

					disableFlg = true;
					popGrecDetails(disableFlg);
					$('#grecMtMainDivId .btn').hide();
					$('#openOnEdit .btn').hide();
					
					$('#grecMtExcelPreviewBtn').show();
					$('#grecMtPreviewBtn').show();
					
					$('#orderListingBtnId').show();
				//	$('#dtSummaryBtnId').show();
				//	$('#packMtExcelPreviewBtn').show();
				//	$('#packMtPreviewBtn').show();	
				//	$('#barcodeTxtId').attr('readonly','readonly');
					
				}else{
					disableFlg = false;
					popGrecDetails(disableFlg);
					}
				
			}
			
			else {
				$("#vTranDate").val('${currentDate}');
				$("#invDate").val(new Date().toLocaleDateString('en-GB'));
				
			}
			 
		
			
			
	});

function process(date){
	   var parts = date.split("/");
	   return new Date(parts[2], parts[1] - 1, parts[0]);
	}
	
	
//----------MT save-------//

function grecMtSave(){

	if($(".grecMtForm").valid()){
		$("form#grecMtFormId").submit();
		
	}
	
}



$(document)
.on(
	'submit',
	'form#grecMtFormId',
	 function(e){
		 $("#grecMtSubmitBtn").prop("disabled", true).addClass("disabled");
	});

	
var dtdata='';

$('#grecDtTabId').bootstrapTable({}).on('load-success.bs.table',
		function(e, data) {
	   
	dtdata = $("#grecDtTabId").bootstrapTable('getData').length;
	
	 if(dtdata >0){

			 $('#grecMtPartyId').val($('#grecMtFormId #party\\.id').val());
			 $('#pOrderTypeIds').val($('#grecMtFormId #orderType\\.id').val());
			 $('#pLocationIds').val($('#grecMtFormId #location\\.id').val());

			 
			 $('#grecMtFormId #location\\.id').attr("disabled","disabled").trigger('chosen:updated');
			$('#grecMtFormId #party\\.id').attr("disabled","disabled").trigger('chosen:updated');
			$('#grecMtFormId #orderType\\.id').attr("disabled","disabled").trigger('chosen:updated');

		}else{
			$('#grecMtFormId #location\\.id').removeAttr('disabled').trigger('chosen:updated');
				$('#grecMtFormId #party\\.id').removeAttr('disabled').trigger('chosen:updated');
				$('#grecMtFormId #orderType\\.id').removeAttr('disabled').trigger('chosen:updated');
							
			}
		
	  })
	  
	


//------------------------------------------------------ grecDT -------------------------------------------------------------//

		function popGrecDetails(disableFlg) {
			$("#grecDtTabId").bootstrapTable('refresh',{
								url : "/jewels/marketing/transactions/grecDt/listing.html?mtId="+mtid+"&disableFlg="+disableFlg,
							});
			
			
			}



$('#hideOnPageLoad').css('display','none');	


var grecDtTableRow;
var grecDtLockStatus = 'null';
var finalPrice = "";
 $('#grecDtTabId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			
			grecDtTableRow = $element.attr('data-index');
			
			$('#rowGrecDtId').css('display','none');
			$('#grecDtPKId').val(jQuery.parseJSON(JSON.stringify(row)).id);
			
			var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
			grecDtLockStatus = jQuery.parseJSON(JSON.stringify(row)).rLock;
			finalPrice = jQuery.parseJSON(JSON.stringify(row)).finalPrice;
		
			$('#itemremark').val(jQuery.parseJSON(JSON.stringify(row)).remark);
			
			$('#tempImgDivId').empty();
			var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
			$('#tempImgDivId').append(tempDiv);
			
			$('#hideOnPageLoad').css('display','block');
			$('#shapeDropDownId').chosen();
			$('#qualityDropDownId').chosen();
			  $('#qualityDropDownId').val('').trigger('chosen:updated');

			
			
			 popGrecMetal(); 
			 popGrecStoneDetails();
			 popGrecComponentDetails()
		
		});


	
	
 
	function addBarcode(){
		 modalModeFlag = "add";
		 var validator = $( ".grecDtForm" ).validate();
			validator.resetForm();
			
			$('#prodDt').val($('#productionDate').val());
		 
		$('#myGrecDtModal').modal('show');
		
		
		
		setTimeout(function(){
			 $('#grecMetalDtTableDivId').css('display','none');
			$('#grecMetalDtIdTbl').bootstrapTable('resetView');
		}, 200);
		
		modelFlag = true;
		
		
		 $('#grecDtModalDivId input[type="text"],textarea').val('');
		 $('#grecDtModalDivId input[type="number"]').val('0');
		 $('#grecDtModalDivId').find('select').val('');
		 $('#modalGrecDtId').val('');
		 
		 $('#grecDtFormId #purity\\.id').val($('#grecMtFormId #purity\\.id').val());
		 $('#grecDtFormId #color\\.id').val($('#grecMtFormId #color\\.id').val());
		
		 $('#grecDtModalDivId  #design\\.mainStyleNo').focus();
		 $('#design\\.mainStyleNo').removeAttr('readonly','readonly');
		 
		 $('#grecDtFormId #purity\\.id').removeAttr('disabled','disabled');
		 $('#grecDtFormId #color\\.id').removeAttr('disabled','disabled');
		 
		 
	}
 
 
 
 
 
 /* ***************	MetalDt=========================== */

function popGrecMetal(){
	 
	  $("#grecMetalTableId").bootstrapTable('refresh',{
					url : "/jewels/marketing/transactions/grecMetalDt/listing.html?grecDtId="+$('#grecDtPKId').val(),
				});
	}

 
/* ***************	grecStnDt=========================== */

function popGrecStoneDetails(){
	
	$("#grecStnDtTabId")
		.bootstrapTable(
			'refresh',{
				url : "/jewels/marketing/transactions/grecStoneDt/listing.html?grecDtId="
						+ $('#grecDtPKId').val()+"&canViewFlag="+canViewFlag,
			});

	
}

/* ***************	grecCompDt=========================== */
	
function popGrecComponentDetails(){
	
	$("#grecCompDtTabId")
	.bootstrapTable(
		'refresh',
		{
			 url : "/jewels/marketing/transactions/grecCompDt/listing.html?grecDtId="
					+ $('#grecDtPKId').val()+"&canViewFlag="+canViewFlag,
					
					
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


$('#grecStnDtTabId').bootstrapTable({}).on(
		'load-success.bs.table',
		function(e, data) {
			var data = JSON.stringify($("#grecStnDtTabId").bootstrapTable('getData'));
			var vStones = 0.0;
			var vCarat = 0.0;

			$.each(JSON.parse(data), function(idx, obj) {
				vStones		+= Number(obj.stone);
				vCarat		+= Number(obj.carat);
			});
			
			$('#vTotalStones').val(" " + vStones);
			$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
			
		});


function deleteGrecDt(e,dtId){
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
							url : "/jewels/marketing/transactions/grecDt/delete/"+ dtId + ".html",
							type : 'GET',
							success : function(data) {
								if(data === "1"){
									displayInfoMsg(event, this,'Delete sucessfully !');
									popGrecDetails(disableFlg);
								}else{
									displayMsg(event, this, data);
								}
							}
						}); 
	

}

function popOrderPickup(){
	 window.location.href = "/jewels/marketing/transactions/orderPickupForGrn.html?mtid="+mtid+"&partyId="+$('#party\\.id').val()+"&orderTypeId="+$('#orderType\\.id').val();
}



		function applyBarcodeFn(){
			 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
						 $.ajax({
								url : "/jewels/marketing/transactions/generateBagBarcode.html?mtId="+mtid,
								type : 'GET',
								success : function(data) {
									if(data === "1"){
										displayInfoMsg(event, this,'Barcode Generated sucessfully !');
										popGrecDetails(disableFlg);
									}else{
										displayMsg(event, this, data);
									}

									$.unblockUI();
								}
							
							}); 
				}


		function grecListReport(val){
		$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
			
			$
			.ajax({
				url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=grec"+"&opt="+val,
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
		
	
 </script>
 
 
<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
 
 <script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
 