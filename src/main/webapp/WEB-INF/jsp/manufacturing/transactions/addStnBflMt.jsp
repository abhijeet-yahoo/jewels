<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
						
		<div>
			<label class="col-lg-1">
				<span style="font-size: 18px;">StnBflMt</span>
			</label>
			<div class="text-right">
				<a class="btn btn-info" style="font-size: 15px" type="button" href="/jewels/manufacturing/transactions/stnBflMt.html">Listing</a>
			</div>
		</div>
			
		
	</div>

	<div class="form-group">
		<form:form commandName="stnBflMt" id="stnBflMt"
			action="/jewels/manufacturing/transactions/stnBflMt/add.html"
			cssClass="form-horizontal stnBflMtForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success">StnBflMt ${action}
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
					<div class="col-lg-1 col-sm-1">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							No:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="invNo" cssClass="form-control" readonly="true"/>
						<form:errors path="invNo" />
					</div>
					
					<div class="col-lg-1 col-sm-1">
						<label for="inputLabel3" class=".col-lg-2 text-right">Inv
							Date:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="invDate" cssClass="form-control" />
						<form:errors path="invDate" />
					</div>
					
					<div class="col-lg-1 col-sm-1">
						<label for="inputLabel3" class=".col-lg-2 text-right">Remarks:</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<form:input path="remark" cssClass="form-control" />
						<form:errors path="remark" />
					</div>
					
				<div class="col-lg-3 col-sm-3">
					<input type="submit" value="Save" class="btn btn-primary" id="stnBflMtSubmitBtn" /> 
					<!-- <a class="btn btn-info" style="font-size: 15px" type="button"
						href="/jewels/manufacturing/transactions/stnBflMt.html">Listing</a> -->
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="uniqueId" />		
				</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
		</form:form>
	</div>
	
	
<div class="row">
	<div class="form-group">
		<div class="col-xs-12">
			<span class="col-lg-12 col-sm-12 label label-default" style="font-size: 16px;">Stone
				Breakup</span>
		</div>
	</div>
</div>

<div class="form-group" id="dsPId">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="stnBflDtTab" data-toggle="table"
								data-toolbar="#toolbarDt1" data-click-to-highlight="true"
								data-select-item-name="radioName" data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
								<thead>
									<tr class = "btn-primary">
										<th data-field="tranType" data-align="left">TransactionType</th>
										<th data-field="inwardType" data-sortable="true">Inward</th>
										<th data-field="invNo" data-sortable="true">InvNo</th>
										<th data-field="stoneType" data-sortable="true">StoneType</th>
										<th data-field="shape" data-align="left">Shape</th>
										<!-- <th data-field="subShape" data-align="left">SubShape</th> -->
										<th data-field="quality" data-sortable="true">Quality</th>
										<th data-field="mm" data-sortable="true">Size</th>
										<th data-field="sieve" data-sortable="true">Sieve</th>
										<th data-field="sizeGroup" data-sortable="true">SizeGroup</th>
										<th data-field="rate" data-sortable="true">Rate</th>
										<th data-field="stone" data-align="right">Stone</th>
										<th data-field="carat" data-align="right">Carat</th>
										<th data-field="action2" data-align="center">Delete</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>


	<div class="row" >
		<div class="form-group">
			<div class="col-xs-1" >
				<form:form commandName="diamondBagging" id="changingTransferrr"
					action="/jewels/manufacturing/transactions/return/diamondChanging/transfer.html"
					cssClass="form-horizontal transferOfChangeing">
					<table class="table">
						<tbody>
							<tr>
								<td class="col-xs-1" >
									<input type="button" value="New Breakup" class="btn btn-info" onclick="javascript:goToNextPage()"/>
									<input type="hidden" id="tempDept" name="tempDept" />
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
				
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
</div>












<script type="text/javascript">


var mtId = "";
var dtId = "";

$(document).ready( function(e){
	
	$(".stnBflMtForm")
	.validate(
			{
				rules : {
					invNo : {
						required : true,
						remote : {
							url : "<spring:url value='/manufacturing/transactions/stnBflMt/invoiceNoAvailable.html' />",
							type : "get",
							data : {

								id : function() {
									return $("#id")
											.val();
								}
							}
						}
					},
					invDate : {
						required : true,
					},
					messages : {
						invNo : {
							remote : "Invoice No already exists"
						}
					}
				}
			});
	
	$("#invDate").datepicker({
		dateFormat : 'dd/mm/yy'
	});
	
	
	if(window.location.href.indexOf("edit") != -1){
		var url = window.location.href; 
		mtId = url.substring(url.indexOf("edit/")+5, url.indexOf(".html"));
		loadStnBflDtTable();
	}
	
	
});




 function loadStnBflDtTable(){
	 
	 
	 $("#stnBflDtTab")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/stnBflDt/listing.html?mtId="+mtId,
			});
	 
 }
 
 
 function deleteDt(e,dtId){
		
		displayDlg(
				e,
				'javascript:deleteStone('+dtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
		
	 
	
	
}




function deleteStone(dtId){
		
	$("#modalDialog").modal("hide");
	
	 $.ajax({
		url : "/jewels/manufacturing/transactions/stnBflDt/delete/"+ dtId + ".html",
		type : 'GET',
		success : function(data) {
			  if(Number(data) == 1){
		 		 
		 		loadStnBflDtTable();
				displayInfoMsg(event, this, 'Record Deleted Successfully');
				
			}else{
				displayMsg(event, this, 'Error Contact Admin');
			} 

		}
	});  
	

}

 
      
  function goToNextPage(){

	  var invno = $('#invNo').val();
	  var invdate = $('#invDate').val();
	  var remark = $('#remark').val();
	  
	  if(invNo ==='' || invdate === '' && remark === ''){
		  displayMsg(event, this, 'Please Create Invoice First');
	  }else{
		  
		  window.location.href = "/jewels/manufacturing/transactions/stnBflMtNewBreakup.html?MtId="+mtId;
	  }
	
	/*   window.location.href = "/jewels/manufacturing/transactions/stnBflMtNewBreakup.html?MtId="+mtId; */

   }

  
  

</script>




<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>