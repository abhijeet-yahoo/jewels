 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class = "panel panel-warning" style="border-style: dotted;">
	<div class="panel-body">
			<div class="form-group">
				<form:form id ="partyexcelUplodFormId" action="/jewels/manufacturing/masters/party/excelUpload.html" enctype="multipart/form-data">
				
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
							href="/jewels/manufacturing/masters/party.html">Party Listing
						</a>
						
						<input type="hidden" id="tempFileName" name="tempFileName"/>
						
					</div>
				</form:form>
			</div>
		
		
		<div class="row">
			
		</div>
		
		<div id="tableDivId" style="display: none">
			<table id="partyExcelTblId" data-toggle="table"
				data-toolbar="#toolbar" 
			    data-height="350">
				<thead>
					<tr class="btn-primary">
						<th data-field="partyNm" style="font-weight: bolder;">Party Name</th>
						 <th data-field="partyCode" style="font-weight: bolder;">Code</th>
						 <th data-field="mailingName" style="font-weight: bolder;">Mailing Name</th>
						 <th data-field="ledgerGroup" style="font-weight: bolder;">Ledger Group</th>
						 <th data-field="country" style="font-weight: bolder;">Country</th>
						 <th data-field="state" style="font-weight: bolder;">State</th>
						 <th data-field="statusRemark" style="font-weight: bolder;">Remark</th>
						
						
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
		
		
		<div id="partyExcelSaveBtnId" class="col-sm-12" style="display: none">
			<form:form commandName="party" id="persistExcelData"
				action="/jewels/manufacturing/masters/party/excelSave.html"
				cssClass="form-horizontal partyExcelForm">
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
		popPartyExcelErrorListing();
	}else if('${retVal}' === '1'){
		
		displayInfoMsg(this,null,"Party Uploaded Successfully !");
	}
	
	
});


function getFileData(myFile){
   var file = myFile.files[0];
   var filename = file.name;
   $('#tempFileName').val(filename);
   
}



function popPartyExcelErrorListing(){
	
	$("#partyExcelTblId")
	  .bootstrapTable(
		'refresh',{
			url : "/jewels/manufacturing/masters/partyExcel/tableListing.html"
		});
}




	//-------------persist excel data------------//

	 $(document).
		on('submit',
			'form#persistExcelData',
			function(e){				
			
			var tableData = JSON.stringify($("#partyExcelTblId").bootstrapTable('getData'));
			
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
						$("#partyExcelTblId").bootstrapTable('destroy');
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