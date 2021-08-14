 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/excelFormat.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class = "panel panel-warning" style="border-style: dotted;">
	<div class="panel-body">
	
	
	<div class="col-sm-12">
			<a href="#quotDtFormat" data-toggle="modal">Req Format</a>
		</div>
			<div class="form-group">
				<form:form id ="qoutDtexcelUplodFormId" action="/jewels/manufacturing/transactions/quotDt/excelUpload.html" enctype="multipart/form-data">
				
					<div class="col-sm-12">
						<label for="file" class="col-lg-2 control-label" style="font-size: 14px;"></label>
					</div>
					
					<div class="col-sm-12">
						<input type="file" name="excelfile" id="excelfile"  onchange="getFileData(this);" />
						<br>
						
						 <button type="submit" class="btn btn-warning">
    						 <span class="glyphicon glyphicon-upload">&nbsp;UPLOAD</span>
  						 </button>
						
						<a id="listingId1" class="btn btn-info" style="font-size: 14px" type="button"
							onClick="javascript:quotDtBackBtn()">Back To Quotation
						</a>
						
						<input type="hidden" id="tempFileName" name="tempFileName"/>
						<input type="hidden" id="quotMtPkId" name="quotMtPkId"/>
						
					</div>
				</form:form>
			</div>
		
		
		<div class="row">
			
		</div>
		
		<div id="tableDivId" style="display: none">
			<table id="quotExcelTblId" data-toggle="table"
				data-toolbar="#toolbar" 
			    data-height="350">
				<thead>
					<tr class="btn-primary">
						<th data-field="styleNo">Style No.</th>
						<th data-field="purity">Purity</th>
						<th data-field="color">Color</th>
						<th data-field="qty">Qty</th>
						<th data-field="netWt">Net Wt</th>
						<th data-field="prodSize">Prod Size</th>
						<th data-field="qty">Qty</th>
						<th data-field="stamp">Stamp</th>
						<th data-field="itemRemark">Item Remark</th>
						<th data-field="shape">Shape</th>
						<th data-field="quality">Quality</th>
						<th data-field="carat">Carat</th>
						<th data-field="rate">Rate</th>
						<th data-field="amount">Amount</th>
						<th data-field="statusRemark" data-align="left">Status Remark</th>
					</tr>
				</thead>
			</table>
		</div>
		
	<div>
			<input type="hidden" id="tableDispplay" name="tableDisplay" value="${tableDisp}" />
			<input type="hidden" id="tempFlag" name="tempFlag" value="${flag}" />
		</div>
		
		<div class="row">
			<div class="col-sm-12">&nbsp;</div>
		</div>
		
		
		<div id="quotDtExcelSaveBtnId" class="col-sm-12" style="display: none">
			<form:form commandName="quotDt" id="persistExcelData"
				action="/jewels/manufacturing/transactions/quotDt/excelSave.html"
				cssClass="form-horizontal quotDtExcelForm">
				 <input type="submit" value="Save" class="btn btn-primary" >
			</form:form>
		</div>
			
		
		
		
		
	</div>
</div>




<script type="text/javascript">

var quotMtId;
$(document).ready(function(e){

	if (window.location.href.indexOf('mtId') != -1) {

		var abcx = window.location.href;

		quotMtId=abcx.substring(window.location.href
					.indexOf('mtId') + 5);
		
		$('#quotMtPkId').val(quotMtId);
	}
	
	
	if($('#tableDispplay').val() === 'yes' && '${retVal}' === 'yes'){
		$('#tableDivId').css('display','block');
		$('#saveBtnId').css('display','block');
		quotMtId = '${quotMtPkId}';
		popQuotDtExcelErrorListing();
	}else if('${retVal}' === '1'){
		quotMtId = '${quotMtPkId}';
		
		displayInfoMsg(this,null,"Quatation Uploaded Successfully !");
	}
	
	
});


function getFileData(myFile){
   var file = myFile.files[0];
   var filename = file.name;
   $('#tempFileName').val(filename);
   
}



function popQuotDtExcelErrorListing(){
	
	$("#quotExcelTblId")
	  .bootstrapTable(
		'refresh',{
			url : "/jewels/manufacturing/transactions/quotDtExcel/tableListing.html"
		});
}






	//-------------persist excel data------------//

	 $(document).
		on('submit',
			'form#persistExcelData',
			function(e){				
			
			var tableData = JSON.stringify($("#labChargeId").bootstrapTable('getData'));
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
		
			 $.ajax({
				url : formURL,
				type : "POST",
				data : {
					tableJsonData:tableData,
					mtId : quotMtId
				},
				success : function(data, textStatus, jqXHR){
					//alert(data);
					
					if(data === '-1'){
						displayMsg(this,null,'Data Verification failed');
					}else{
						displayInfoMsg(this,null,'Data Transfered Successfully');
						$("#orderExcelTblId").bootstrapTable('destroy');
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
	
	
	
	
	
	//--------excel format download-------
	
	function getLabourExcelFile(){
		var tblHeading = "";		
		 $('#labourFormatTbl th').each(function() {
		      var text = $(this).text();    
		      if(tblHeading === null || tblHeading === ""){
		    	  tblHeading = text;
		      }else{
		    	  tblHeading = tblHeading+","+text;
		      }
		  });
		
		 //alert(tblHeading);
		 
		 
		 $.ajax({
			 url:"/jewels/manufacturing/masters/labourCharge/downloadExcel/format.html?headingVal="+tblHeading,
			 type:"POST",
			 success:function(data){
				// alert(data);
				 
				 if(data === '-2'){
					 alert(error);
				 }else{
					 window.location.href = "/jewels/manufacturing/masters/reports/download/excelformat.html?fileName="+data;
				 }
				 
			 },
			 error:function(data){
				 alert("error");
			 }
		 })
		 
		
	}



	function quotDtBackBtn(){
	
		window.location.href = "/jewels/manufacturing/transactions/quotMt/edit/"+ quotMtId + ".html"
		}
	
	
	
	//--------excel format upload-------//
	
	
	
	 function getQuotDtExcelFile(){
		 
		 var tblHeading = "";		
		 $('#quotDtTbl th').each(function() {
		      var text = $(this).text();    
		      if(tblHeading === null || tblHeading === ""){
		    	  tblHeading = text;
		      }else{
		    	  tblHeading = tblHeading+","+text;
		      }
		  });
		
		 //alert(tblHeading);
		 
		 
		 $.ajax({
			 url:"/jewels/manufacturing/transactions/quotDt/downloadExcel/format.html?headingVal="+tblHeading,
			 type:"POST",
			 success:function(data){
				 //alert(data);
				 
				 if(data === '-2'){
					 alert(error);
				 }else{
					 window.location.href = "/jewels/manufacturing/masters/reports/download/excelformat.html?fileName="+data;
				 }
				 
			 },
			 error:function(data){
				 alert("error");
			 }
		 })
		 
		 
	 }
	
	
	
	
	
	
	


</script>