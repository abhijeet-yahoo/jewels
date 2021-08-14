<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<div id="barcodeIssDivId">
<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" style="text-align: center;">

		<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Barcode Issue </span>
			</label>
			<div class="text-right">
			
			
			<input type="button" value="Preview" style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success" id="barcodeMtPreviewBtn" onClick="javascript:BarcodeStickerReport(2);" />
			
						</div>

		</div>

	</div>
	<div class="form-group">
		<form:form commandName="barcodeMt" id="barcodeIss"
			action="/jewels/manufacturing/transactions/barcodeMt/add.html"
			cssClass="form-horizontal barcodeMtForm">

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
					
					<!-- <div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-2 text-right">Department:</label>
					</div> -->
					
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<form:input path="invNo" cssClass="form-control" readonly="true"/>
						<form:errors path="invNo" />
					</div>
					<div class="col-lg-3 col-sm-3">
						<form:input path="invDate" cssClass="form-control" />
						<form:errors path="invDate" />
					</div>
				
				</div>
			</div>
	
			<div class="row">&nbsp;</div>
		
			<div class="row">
			<div class="col-xs-12">
				<div class="col-sm-4">
					<input type="submit" value="Save" class="btn btn-primary" id="barcodeIssSubmitBtn" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/barcodeMt.html">Listing</a>
					<form:input type="hidden" path="id"/>
					
						
				</div>
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
		
	</div>
</div>
</div>



<div id="barcodeIssDtDivId" style="display: none">
	<div class="panel panel-primary" style="width: 100%">
		<div class="panel-heading" >
			<span style="font-size: 18px;">Detail</span>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div id="toolbar">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:popBarcodeGenModal();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add Barcode
						</a>
						
					
					
					</div>
					<div>
						<table id="barcodeIssDtId" data-toggle="table" data-toolbar="#toolbar" data-search="true"
							data-height="350" data-striped="true">
							<thead>
								<tr class="btn-primary">
								<!-- <th data-field="action2" data-align="center">Delete</th> -->
								<th data-field="barcode" data-align="left" data-sortable="true" data-filter-control="input">Barcode</th>
								<th data-field="styleNo" data-align="left" data-sortable="true" data-filter-control="input">Style No</th>	
								<th data-field="bagNo" data-align="left" data-sortable="true" data-filter-control="input">Bag</th>
								<th data-field="kt" data-align="left" data-sortable="true" data-filter-control="input">Kt </th>																				
								<th data-field="color" data-align="left" data-sortable="true" data-filter-control="input">Color </th>		
								</tr>
							</thead>
						</table>
					</div>
					
				</div>		
			</div>
		</div>		
	</div>

</div>

<script type="text/javascript">



canViewFlag=false;
var mtid;
	$(document)
			.ready(
					function(e) {
						
						
$('select').chosen();
						
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
						
												
						$(".barcodeMtForm")
								.validate(
										{
											rules : {
												invDate : {
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
							
							$("#barcodeIssDtDivId").css('display', 'block');
							mtid="${mtid}";
							popBarcodeIssDt();
							
						}
						else{
												
							$("#invDate").val('${currentDate}');
							$("#invDate").val(new Date().toLocaleDateString('en-GB'));
												
						}

					});


$(document)
.on(
	'submit',
	'form#barcodeIss',
	 function(e){
		 $("#barcodeIssSubmitBtn").prop("disabled", true).addClass("disabled");
	});

function popBarcodeIssDt() {
	$("#barcodeIssDtId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/barcodeDt/listing.html?mtId="+mtid,
					});
}


function popBarcodeGenModal(){

	 window.location.href = "/jewels/manufacturing/transactions/barcodeGen.html?mtId="+mtid+"&mOpt=barcodeNew";
		
	}

function BarcodeStickerReport(val){

	$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
	
	$
	.ajax({
		url : "/jewels/marketing/transactions/packMt/packingList/report.html?mtId="+mtid+"&tranType=barcodeMt"+"&opt="+val,
		type : 'POST',
		success : function(data, textStatus, jqXHR) {

			$.unblockUI();
			
			if(val === 2){
				$('#timeValCommonPdf').val(data);
				$("#generateDataRpt").trigger('click');
				
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