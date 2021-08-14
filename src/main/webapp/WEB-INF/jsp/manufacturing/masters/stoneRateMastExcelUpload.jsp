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
			<a href="#stoneRateFormat" data-toggle="modal">Req Format</a>
		</div>

		<div>
			<div class="form-group">
				<form:form action="/jewels/manufacturing/masters/stoneRateMast/commonExcelUpload.html" enctype="multipart/form-data">
				
					<!-- <div class="col-sm-12">
						<label for="file" class="col-lg-2 control-label" style="font-size: 13px;">File :</label>
					</div> -->
					
					<div class="col-sm-12">
						<label for="file" class="col-lg-2 control-label" style="font-size: 14px;"></label>
					</div>			
					
					<div class="col-sm-12">
						<input name="excelfile" id="excelfile" type="file" onchange="getFileData(this);" />
						<br>
						
						 <button type="submit" class="btn btn-warning">
    						 <span class="glyphicon glyphicon-upload">&nbsp;UPLOAD</span>
  						 </button>
						
						<a id="listingId3" class="btn btn-info" style="font-size: 14px" type="button"
							href="/jewels/manufacturing/masters/stoneRateMast.html">Stone Rate Listing
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
			<table id="stoneRateId" data-toggle="table"
				data-toolbar="#toolbar" data-pagination="false"
				data-side-pagination="server"
				data-striped="true" data-height="300">
				<thead>
					<tr class="btn-primary">
					  	<th data-field="party" data-align="left" data-sortable="true">Party</th>
	                    <th data-field="stoneType" data-align="left" data-sortable="true">Stone Type</th>
						<th data-field="shape" data-align="left" data-sortable="true">Shape</th>
						<th data-field="quality" data-align="left" data-sortable="true">Quality</th>
						<th data-field="size" data-align="left" data-sortable="true">Size Group</th>
						<th data-field="stoneRate" data-sortable="false">PerCarat Rate</th>
						<th data-field="perPcRate" data-sortable="false">PerPc Rate</th>
						<th data-field="sieve" data-sortable="false">Sieve</th>
						<!-- <th data-field="deactive" data-formatter="inputFormatter">Deactive</th> -->
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
			<form:form commandName="stoneRateMast" id="persistExcelData"
				action="/jewels/manufacturing/masters/stoneRateMast/excelSave.html"
				cssClass="form-horizontal stoneRateMastForm">
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
		popStoneRateMastListing();
	}
	
	if($('#tempFlag').val() === 'exportClient'){
		$("#listingId").attr('href',"/jewels/manufacturing/masters/stoneRateMast/exportClient.html");
	}else{
		$("#listingId").attr('href',"/jewels/manufacturing/masters/stoneRateMast/vendorClient.html");
	}
	
});


function getFileData(myFile){
   var file = myFile.files[0];
   var filename = file.name;
   $('#tempFileName').val(filename);
   $('#flagNm').val($('#tempFlag').val());
}



function popStoneRateMastListing(){
	$("#stoneRateId")
	  .bootstrapTable(
		'refresh',{
			url : "/jewels/manufacturing/masters/stoneRateMast/tableListing.html"
		});
}


var $table = $('#stoneRateId');
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
			
			var tableData = JSON.stringify($("#stoneRateId").bootstrapTable('getData'));
			
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
						$("#stoneRateId").bootstrapTable('destroy');
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

	
	
	function getStoneRateExcelFile(){
		
		var tblHeading = "";		
		 $('#stoneRateTbl th').each(function() {
		      var text = $(this).text();    
		      if(tblHeading === null || tblHeading === ""){
		    	  tblHeading = text;
		      }else{
		    	  tblHeading = tblHeading+","+text;
		      }
		  });
	
		 
		 
		 $.ajax({
			 url:"/jewels/manufacturing/masters/stoneRate/downloadExcel/format.html?headingVal="+tblHeading,
			 type:"POST",
			 success:function(data){
				 
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