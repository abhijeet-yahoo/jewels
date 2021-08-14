<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>
<%@ include file="/WEB-INF/jsp/common/modalBarcodeExcelUpload.jsp"%>

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">

<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>


<div class="panel panel-primary">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Barcode Report</span>
	</div>
	
	
	<div class="panel-body">
	
	<div id="toolbar">
			<a class="btn btn-warning" style="font-size: 15px" type="button"
									href="#" onclick="popBarcodeExcel()"><span
									class="glyphicon glyphicon-upload"></span>&nbsp;Upload Excel</a>	
									
		
			<a class="btn btn-success" style="font-size: 15px" type="button" id="barcodeGenRptBtnId"
				onClick="barcodeGenReport(1)"> Preview
			</a> 
			
			
		
			<a class="btn btn-success" style="font-size: 15px" type="button" id="barcodeGenRptBtnId"
				onClick="barcodeGenReport(2)"> Excel
			</a> 
			
			<a class="btn btn-success" style="font-size: 15px" type="button" id="barcodeStickerRptBtnId"
				onClick="barcodeGenReport(3)">Barcode Sticker
			</a> 
			
			
			
			
			
		
	</div>
	
	
	
	<div class="table-responsive"> 
			<table id="barcodeTblId"  data-toggle="table"
						data-toolbar="#toolbar2" data-pagination="false"
						data-side-pagination="server"
					    data-height="450">
						<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true"></th>
								<th data-field="barcode" data-align="left">Barcode</th>
								
							</tr>
						</thead>
					</table>
	</div>
	
	
	

	
	
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
	
	
	<script type="text/javascript">



	function popBarcodeExcel() {
		$('#myBarcodeExcelUploadModal').modal('show');
		$('#fileBarcode').val('');
	}



	function popBarcodeListing(){

		$("#barcodeTblId")
		.bootstrapTable(
				'refresh',
				{
					url : "/jewels/marketing/transactions/barcodeFilter/tableListing.html"
				});
		}


	function barcodeGenReport(val){

	var pBagIds;

	$('#barcodeGenRptBtnId').attr("disabled", true);
	$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' }); 
		var data = $('#barcodeTblId').bootstrapTable('getAllSelections');
		/* var ids = $.map(data, function(item) {
			return item.bagId;
		}); */
		


		var barcodes = $.map(data, function(item) {
			return item.barcode;
		});
		pBagIds =barcodes;
		
		$.ajax({
			url : "/jewels/marketing/transactions/generateBarCodeReport.html?pBagIds="+pBagIds+"&opt="+val,
		 
			type : "POST",
			success : function(data, textStatus, jqXHR) {
				$.unblockUI();
				
				if(val === 2){
					$('#pFileName').val(data);
					$("#generateExcelReportss").trigger('click');
					
				}else{
					$('#timeValCommonPdf').val(data);
					$("#generateDataRpt").trigger('click');
					

					}	
				
			
				$('#barcodeGenRptBtnId').attr("disabled", false);

			},
			error : function(jqXHR, textStatus, errorThrown) {
				$('#barcodeGenRptBtnId').attr("disabled", false);
				$.unblockUI();

			}

		});

		}


	
	
	</script>
	
	
	<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
	
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>