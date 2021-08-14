 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/excelFormat.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class = "panel panel-warning" style="border-style: dotted;">
	<div class="panel-body">
		
	<div class="col-sm-12">
			<!-- <a href="#designMtFormat" data-toggle="modal">Req Format</a> -->
		</div>
			<div class="form-group">
				<form:form id ="barcodeExcelUplodFormId" action="/jewels/marketing/transactions/barcode/excelUpload.html" enctype="multipart/form-data">
				
					<div class="col-sm-12">
						<label for="file" class="col-lg-2 control-label" style="font-size: 14px;"></label>
					</div>
					
					<div class="col-sm-12">
						<input type="file" name="excelfile" id="excelfile"  onchange="getFileData(this);" />
						<br>
						
						 <button type="submit" class="btn btn-warning">
    						 <span class="glyphicon glyphicon-upload">&nbsp;UPLOAD</span>
  						 </button>
						
						<input type="hidden" id="tempFileName" name="tempFileName"/>
						
					</div>
				</form:form>
			</div>
		
		
		<div class="row">
			
		</div>
		
		<div id="tableDivId" style="display: none">
			<table id="barcodeExcelTblId" data-toggle="table"
				data-toolbar="#toolbar" 
			    data-height="350">
				<thead>
					<tr class="btn-primary">
						<th data-field="state" data-checkbox="true"></th>
								<th data-field="barcode" data-align="left">Barcode</th>
								<th data-field="styleNo" data-align="left">Design No</th>
								<th data-field="ktColor" data-align="left">Kt/Color</th>
								<th data-field="category" data-align="left">Category</th>
								<th data-field="stone" data-align="right">Stone</th>
								<th data-field="carat" data-align="right">Carat</th>
								<th data-field="statusRemark">Remark</th>
						
						
					</tr>
				</thead>
			</table>
		</div>
		
	<div>
			<input type="hidden" id="tableDispplay" name="tableDisplay" value="${tableDisp}" />
			<input type="hidden" id="tempFlag" name="tempFlag" value="${flag}" />
		</div>
		
		
		
	</div>
</div>




<script type="text/javascript">


$(document).ready(function(e){
	
	
	if($('#tableDispplay').val() === 'yes' && '${retVal}' === 'yes'){
		$('#tableDivId').css('display','block');
		$('#saveBtnId').css('display','block');
		popBarcodeExcelErrorListing();
	}else if('${retVal}' === '1'){
		
		displayInfoMsg(this,null,"Design Uploaded Successfully !");
	}
	
	
});


function getFileData(myFile){
   var file = myFile.files[0];
   var filename = file.name;
   $('#tempFileName').val(filename);
   
}



function popBarcodeExcelErrorListing(){
	
	$("#barcodeExcelTblId")
	  .bootstrapTable(
		'refresh',{
			url : "/jewels/manufacturing/masters/designExcel/tableListing.html"
		});
}






	//-------------persist excel data------------//

	 $(document).
		on('submit',
			'form#persistExcelData',
			function(e){				
			
			var tableData = JSON.stringify($("#barcodeExcelTblId").bootstrapTable('getData'));
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
		
			 $.ajax({
				url : formURL,
				type : "POST",
				data : {
					tableJsonData:tableData
				},
				success : function(data, textStatus, jqXHR){
					//alert(data);
					
					if(data === '-1'){
						displayMsg(this,null,'Data Verification failed');
					}else{
						displayInfoMsg(this,null,'Data Transfered Successfully');
						$("#designExcelTblId").bootstrapTable('destroy');
						$('#tableDivId').css('display','none');
						$('#saveBtnId').css('display','none');
					}
					
					
				},
				error : function(data, textStatus, jqXHR){
					alert("error");
				}
				
			})
			
			e.preventDefault(); 
			
		});
	
	
	
	
	
	
	
	


</script>