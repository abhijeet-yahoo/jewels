 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/excelFormat.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<!-- <style>
	input[type=file] {
		float: left;
	}
</style> -->

<div class = "panel panel-warning" style="border-style: dotted;">
	<div class="panel-body">
	
	
		<div class="col-sm-12">
			<a href="#labourFormat" data-toggle="modal">Req Format</a>
		</div>

		<div>
			<div class="form-group">
				<form:form action="/jewels/manufacturing/masters/labourCharge/commonExcelUpload.html" enctype="multipart/form-data">
				
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
							href="/jewels/manufacturing/masters/labourCharge.html">Labour Charge Listing
						</a>
						
						<input type="hidden" id="tempFileName" name="tempFileName"/>
						<input type="hidden" id="flagNm" name="flagNm"/>
					</div>
				</form:form>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">&nbsp;</div>
		</div>
		
		<div id="tableDivId" style="display: none">
			<table id="labChargeId" data-toggle="table"
				data-toolbar="#toolbar" data-pagination="false"
				data-side-pagination="server"
				data-striped="true" data-height="300">
				<thead>
					<tr class="btn-primary">
						<th data-field="party" data-align="left">Party</th>
						<th data-field="metal" data-align="left">Metal</th>
						<th data-field="purity" data-align="left">Purity</th>
						<th data-field="category" data-align="left">Category</th>
						<th data-field="labourType" data-align="left">LabourType</th>
						<th data-field="fromWeight" data-align="left">From Weight</th>
						<th data-field="toWeight" data-align="left">To Weight</th>
						<th data-field="rate" data-align="left">Rate</th>
						<th data-field="pcsRate" data-formatter="inputFormatter">% PcsRate</th>
						<th data-field="gramRate" data-formatter="inputFormatter">% GramRate</th>
						<th data-field="caratRate" data-formatter="inputFormatter">% CaratRate</th>
						<th data-field="percent" data-formatter="inputFormatter">Percentage</th>
						<th data-field="defLabour" data-formatter="inputFormatter">DefaultLabour</th>
						<th data-field="status" data-align="left">Status</th>
						<th data-field="remark" data-align="left">Remark</th>
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
		
		
		<div id="saveBtnId" class="col-sm-12" style="display: none">
			<form:form commandName="labourCharge" id="persistExcelData"
				action="/jewels/manufacturing/masters/labourCharge/excelSave.html"
				cssClass="form-horizontal labourChargeForm">
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
		popLabourChargeListing();
	}
	
	if($('#tempFlag').val() === 'exportClient'){
		$("#listingId").attr('href',"/jewels/manufacturing/masters/labourCharge/exportClient.html");
	}else{
		$("#listingId").attr('href',"/jewels/manufacturing/masters/labourCharge/vendorClient.html");
	}
	
});


function getFileData(myFile){
   var file = myFile.files[0];
   var filename = file.name;
   $('#tempFileName').val(filename);
   $('#flagNm').val($('#tempFlag').val());
}



function popLabourChargeListing(){
	$("#labChargeId")
	  .bootstrapTable(
		'refresh',{
			url : "/jewels/manufacturing/masters/labourCharge/tableListing.html"
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
			
			var tableData = JSON.stringify($("#labChargeId").bootstrapTable('getData'));
			
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
						$("#labChargeId").bootstrapTable('destroy');
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