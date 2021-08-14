 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/excelFormat.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class = "panel panel-warning" style="border-style: dotted;">
	<div class="panel-body">
	
	
		<div class="col-sm-12">
			<a href="#settingChargeFormat" data-toggle="modal">Req Format</a>
		</div>

		<div>
			<div class="form-group">
				<form:form action="/jewels/manufacturing/masters/settingCharge/commonExcelUpload.html" enctype="multipart/form-data">
				
					<!-- <div class="col-sm-12">
						<label for="file" class="col-lg-2 control-label" style="font-size: 13px;">File :</label>
					</div>
 -->
 
 					<div class="col-sm-12">
						<label for="file" class="col-lg-2 control-label" style="font-size: 14px;"></label>
					</div>					
					
					
					<div class="col-sm-12">
						<input name="excelfile" id="excelfile" type="file" onchange="getFileData(this);" />
						
						 <br>
						
						 <button type="submit" class="btn btn-warning">
    						 <span class="glyphicon glyphicon-upload">&nbsp;UPLOAD</span>
  						 </button>
						
						<a id="listingId2" class="btn btn-info" style="font-size: 14px" type="button"
							href="/jewels/manufacturing/masters/settingCharge.html">Setting Charge Listing
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
			<table id="settingChargeId" data-toggle="table"
				data-toolbar="#toolbar" data-pagination="false"
				data-side-pagination="server"
				data-striped="true" data-height="300">
				<thead>
					<tr class="btn-primary">
						<th data-field="party" data-align="left">Party</th>
						<th data-field="metal" data-align="left">Metal</th>
						<th data-field="stoneType" data-align="left">Stone Type</th>
						<th data-field="shape" data-align="left" >Shape</th>
						<th data-field="setting" data-align="left">Setting</th>
						<th data-field="settingType" data-align="left">Setting Type</th>
						<th data-field="fromWeight" data-align="left">From Wt</th>
						<th data-field="toWeight" data-align="left">To Wt</th>
						<th data-field="rate" data-align="left">Rate</th>
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
			<form:form commandName="settingCharge" id="persistExcelData"
				action="/jewels/manufacturing/masters/settingCharge/excelSave.html"
				cssClass="form-horizontal settingChargeForm">
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
		popSettingChargeListing();
	}
	
	if($('#tempFlag').val() === 'true'){
		$("#listingId").attr('href',"/jewels/manufacturing/masters/settingCharge/true.html");
	}else{
		$("#listingId").attr('href',"/jewels/manufacturing/masters/settingCharge/false.html");
	}
	
});


function getFileData(myFile){
   var file = myFile.files[0];
   var filename = file.name;
   $('#tempFileName').val(filename);
   $('#flagNm').val($('#tempFlag').val());
}



function popSettingChargeListing(){
	$("#settingChargeId")
	  .bootstrapTable(
		'refresh',{
			url : "/jewels/manufacturing/masters/settingCharge/tableListing.html"
		});
}


var $table = $('#settingChargeId');
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
			
			var tableData = JSON.stringify($("#settingChargeId").bootstrapTable('getData'));
			
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
						$("#settingChargeId").bootstrapTable('destroy');
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

	
	 
	
	
	 function getSettingChargeExcelFile(){
		 
		 var tblHeading = "";		
		 $('#settingChargeTbl th').each(function() {
		      var text = $(this).text();    
		      if(tblHeading === null || tblHeading === ""){
		    	  tblHeading = text;
		      }else{
		    	  tblHeading = tblHeading+","+text;
		      }
		  });
		
		 //alert(tblHeading);
		 
		 
		 $.ajax({
			 url:"/jewels/manufacturing/masters/settingCharge/downloadExcel/format.html?headingVal="+tblHeading,
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