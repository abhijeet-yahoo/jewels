 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class = "panel panel-warning" style="border-style: dotted;">
	<div class="panel-body">
			<div class="form-group">
				<form:form id ="stockExcelUplodFormId" action="/jewels/marketing/transactions/stockMt/excelUpload.html" enctype="multipart/form-data">
					<div class="col-sm-12">
						<label for="file" class="col-lg-2 control-label" style="font-size: 14px;"></label>
					</div>
					
					<div class="col-sm-12">
						<input type="file" name="excelfile" id="excelfile"  onchange="getFileData(this);" />
						<br>
						
						 <button type="submit" class="btn btn-warning">
    						 <span class="glyphicon glyphicon-upload">&nbsp;UPLOAD</span>
  						 </button>
						
						<!-- <a id="listingId1" class="btn btn-info" style="font-size: 14px" type="button"
							href="/jewels/manufacturing/masters/design.html">Design Listing
						</a> -->
						
						<input type="hidden" id="tempFileName" name="tempFileName"/>
						
					</div>
				</form:form>
			</div>
		
		
		<div class="row">
			
		</div>
		
			<div id="tableDivId" style="display: none">
			<table id="stockExcelTblId" data-toggle="table"
				data-toolbar="#toolbar" 
			    data-height="350">
				<thead>
					<tr class="btn-primary">
					<th data-field="barcode">Barcode</th>
						<th data-field="memoParty">Memo Party</th>
						<th data-field="designNo">Style No</th>
						<th data-field="purity">Purity</th>
						<th data-field="color">Color</th>
						<th data-field="category">Category</th>
						<th data-field="stoneType">StoneType</th>
						<th data-field="shape">Shape</th>
						<th data-field="quality">Quality</th>
						<th data-field="sieve">Sieve</th>
						<th data-field="department">Department</th>
						<th data-field="statusRemark">Remark</th>
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
		
		
		<div id="orderExcelSaveBtnId" class="col-sm-12" style="display: none">
			<form:form commandName="design" id="persistExcelData"
				action="/jewels/manufacturing/masters/design/excelSave.html"
				cssClass="form-horizontal designExcelForm">
				 <input type="submit" value="Save" class="btn btn-primary" >
			</form:form>
		</div>
			
		
		
		
		
	</div>
</div>




<script type="text/javascript">


$(document).ready(function(e){
	
	
	if($('#tableDispplay').val() === 'yes' && '${retVal}' === 'yes'){
		$('#tableDivId').css('display','block');
		$('#saveBtnId').css('display','block');
		popStockExcelErrorListing();
	}else if('${retVal}' === '1'){
		
		displayInfoMsg(this,null,"Stock Uploaded Successfully !");
	}
	
	
});


function getFileData(myFile){
   var file = myFile.files[0];
   var filename = file.name;
   $('#tempFileName').val(filename);
   
}



function popStockExcelErrorListing(){
	
	$("#stockExcelTblId")
	  .bootstrapTable(
		'refresh',{
			url : "/jewels/marketing/transactions/stockExcel/tableListing.html"
		});
}


var $table = $('#labChargeId');
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




	//-------------persist excel data------------//

	 $(document).
		on('submit',
			'form#persistExcelData',
			function(e){				
			
			var tableData = JSON.stringify($("#designExcelTblId").bootstrapTable('getData'));
			
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
	
	
	
	
	
	
	
	
	
	
	


</script>