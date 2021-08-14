<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
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



<div id="fgRetMtDivId">
<div class="panel panel-primary" style="width: 100%">
<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-3 col-sm-3 text-left"> <span
				style="font-size: 18px;">Issue To Factory</span>
			</label>
			<div class="text-right">
			
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="fgRetMtExcelPreviewBtn" onClick="javascript:fgRetListReport(1);">
			 <span class="fa fa-file-pdf"></span>Excel</button>
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="fgRetMtPreviewBtn" onClick="javascript:fgRetListReport(2);" />
			
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/transactions/fgRetMt.html">Listing</a> 
			
					<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-info" id="fgRetMtSubmitBtn"  onclick="javascript:fgRetMtSave()" />
			
			</div>

		</div>

	</div>
	
	<div class="form-group">
		<form:form commandName="fgRetMt" id="fgRetMtFormId"
			action="/jewels/manufacturing/transactions/fgRetMt/add.html"
			cssClass="form-horizontal fgRetMtForm">

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
						<form:select path="location.id" class="form-control" required="true" >
							<form:option value="" label=" Select Location " />
							<form:options items="${locationMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-3 col-sm-3">
						<form:select path="toLocation.id" class="form-control" required="true">
							<form:option value="" label="Select Location" />
							<form:options items="${reparinglocationMap}" />
						</form:select>
					</div>
					
					
				
				
				</div>
			</div>
			
				
	
			<div class="row">&nbsp;</div>
			
			<div class="row">
			<div class="col-xs-12">
			
				<form:input type="hidden" path="id"/>
					<form:input type="hidden" path="invSrNo"/>
					<input type="hidden" id="pLocationIds" name="pLocationIds" />
					<input type="hidden" id="pLocationToIds" name="pLocationToIds" />
					<input type="hidden" id="vTranDate" name="vTranDate" /></td>
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
	
	
	
	
<div id="frRetDtDivId" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-10">
					<div id="toolbar">
					<div class="row">
				
				
					<div class="col-sm-12">
					<input type="text" id="barcodeTxtId" name="barcodeTxtId" class="form-control" placeholder="Type Barcode To Add" onchange="javascript:popFgRetlistAdd()">
					</div>		
					
					
					</div>
					</div>
					<div>
						<table id="fgRetDtTblId" data-toggle="table" data-toolbar="#toolbar" data-search="true"
							data-height="350" data-click-to-select="true" data-striped="true">
							<thead>
								<tr class="btn-primary">
								<th data-field="stateRd" data-radio="true"></th>  
								<th data-field="action2" data-align="left" data-sortable="true" data-filter-control="input">Delete</th>	
								<th data-field="barcode" data-align="left" data-sortable="true" data-filter-control="input">Barcode</th>
								<th data-field="party" data-align="left" data-sortable="true" data-filter-control="input">Party</th>
								<th data-field="orderNo" data-align="left" data-sortable="true" data-filter-control="input">Order</th>
								<th data-field="refNo" data-align="left" data-sortable="true" data-filter-control="input">Ref</th>
								<th data-field="bagNo" data-align="left" data-sortable="true" data-filter-control="input">Bag</th>
								<th data-field="styleNo" data-align="left" data-sortable="true" data-filter-control="input">Style No</th>
								<th data-field="qty" data-align="left" data-sortable="true">Qty</th>
								<th data-field="grossWt" data-align="left" data-sortable="true">Gross Wt</th>
								<th data-field="totCarat" data-align="left" data-sortable="true">Total Cts</th>
								<th data-field="totStone" data-align="left" data-sortable="true">Total Stn</th>
									
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
		</div>
		</div>
	
	
	
	
	
	
</div>


</div>





<script type="text/javascript">

canViewFlag=false;
var mtid;
var mode;
	$(document)
			.ready(
					function(e) {
						
						


$('#fgRetMtDivId #location\\.id').chosen();
$("#fgRetMtDivId #toLocation\\.id").chosen();

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
						
						
						
						$(".fgRetMtForm")
								.validate(
										{
											rules : {
												invDate : {
													required : true,
												},
												
												'location.id' : {
													required : true,
												},
												'toLocation.id' : {
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
							
							$("#frRetDtDivId").css('display', 'block');
							mtid="${mtid}";
							popFgRetDt();
							
							$('#barcodeTxtId').focus();
							
							
						}
						else{

							
							$("#vTranDate").val('${currentDate}');					
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
					
							
						}



						$("#barcodeTxtId")
						.autocomplete(
							{
								source : '<spring:url value='/manufacturing/transactions/fgRetBarcode/autoFill/list.html' />?locationId='
									+ $('#location\\.id').val(),

								minLength : 2
							})

						

					});
	



$(document)
	.on(
		'submit',
		'form#fgRetMtFormId',
		 function(e){
			 $("#fgRetMtSubmitBtn").prop("disabled", true).addClass("disabled");
		});


function fgRetMtSave(){

	
	if($(".fgRetMtForm").valid()){
		$("form#fgRetMtFormId").submit();
		
	}
	

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


function popFgRetlistAdd(){


	if($('#barcodeTxtId').val() !=''){
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/fgRetDt/addBarcodeItem.html",
			type : 'POST',
			data :{
				barcode :$('#barcodeTxtId').val(),
				mtId :mtid,
				locationId :$('#location\\.id').val()},
			success : function(data) {
				
				if(data==='1'){
					
					popFgRetDt();
					
				}else{
					displayMsg(event, this, data);
				}
				
			}
		});
		
	}else{
		displayMsg(event, this, 'Enter Barcode To Add');
	}
		
}


function popFgRetDt() {
	$("#fgRetDtTblId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/fgRetDt/listing.html?mtId="+mtid,
					});
	
}


var dtdata='';

$('#fgRetDtTblId').bootstrapTable({}).on('load-success.bs.table',
		function(e, data) {
	   
	dtdata = $("#fgRetDtTblId").bootstrapTable('getData').length;
		
	 if(dtdata >0){

		    $('#pLocationToIds').val($('#fgRetMtDivId #toLocation\\.id').val());
			$('#pLocationIds').val($('#fgRetMtDivId #location\\.id').val());
			
			
			$('#fgRetMtDivId #toLocation\\.id').attr("disabled","disabled").trigger('chosen:updated');
			$('#fgRetMtDivId #location\\.id').attr("disabled","disabled").trigger('chosen:updated');
			
			}else{
				$('#fgRetMtDivId #toLocation\\.id').removeAttr('disabled').trigger('chosen:updated');
				$('#fgRetMtDivId #location\\.id').removeAttr('disabled').trigger('chosen:updated');
	
				
			}
		
	  })

	  
	
	
	function deleteFgRetDt(e,dtId){

			
			displayDlg(
					e,
					'javascript:deleteFgDt('+dtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
				
  }


function deleteFgDt(dtId){
	
	
	$("#modalDialog").modal("hide");
	
	 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
	 
					 $.ajax({
							url : "/jewels/manufacturing/transactions/fgRetDt/delete/"+ dtId + ".html",
							type : 'GET',
							success : function(data) {
								$.unblockUI();
							
								
								if(data === "1"){
									displayInfoMsg(event, this,'Delete sucessfully !');
									popFgRetDt();
								}else{
									displayMsg(event, this, data);
								}
							}
						}); 
	

}


function fgRetListReport(val){
	 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
	$
	.ajax({
		url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=fgRet"+"&opt="+val,
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

<script src="/jewels/js/common/design.js"></script>

<script src="<spring:url value='/js/bootstrap/lodash.min.js' />"></script>







