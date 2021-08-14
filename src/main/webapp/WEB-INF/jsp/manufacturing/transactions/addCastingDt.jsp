<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
        
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>
<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>



<div class="panel panel-primary" style="width: 100%">

	<div class="panel-heading">
		<span style="font-size: 18px;">Add To CastingDt</span>
	</div>
	
	<div class="panel-body">

	<!-- <div class="form-group">
		<label for="name" class="col-sm-1 control-label">Search : </label>
	 	<div class="col-sm-3">
			<input type="search" id="searchBags"  class="search form-control" placeholder="Search " />
		</div> 
	</div> -->

		

<div class="form-group" id="dsPId">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div>
					<table id="tranMt" data-toggle="table" data-search="true"  data-maintain-selected="true" data-height="350">
						<thead>
							<tr>							
								<th data-field="state" data-checkbox="true">Select</th>								
								<th data-field="party" data-sortable="true">Party</th>
								<th data-field="orderNo" data-sortable="true">OrderNo</th>
								<th data-field="bagNo" data-align="left" data-sortable="true">Bag No</th>
								<th data-field="style" data-align="left" data-sortable="true">Style No</th>
								<th data-field="partName" data-align="left" data-sortable="true">Part No</th>
								<th data-field="kt" data-sortable="true">KT</th>
								<th data-field="color" data-align="left" data-sortable="true">Color</th>
								<th data-field="pcs" data-align="left" data-sortable="true">Pcs</th>
								<th data-field="partPcs" data-align="left" data-sortable="true">Part Pcs</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="form-group">
		<div class="col-xs-12">
			<form:form commandName="castingDt" id="transferToCastingDt"
				action="/jewels/manufacturing/transactions/castingDt/add.html"
				cssClass="form-horizontal transferForm">

				<table class="table table-bordered">
					<tbody>
						<tr>
							<td class="col-xs-1">
								<input type="submit" value="Transfer" class="btn btn-primary" id="transferBtnToCasting" /> 
								<form:input type="hidden" path="id" />							
								<input type="hidden" name="castingMtId" id="castingMtId" />
							</td>
							<td class="col-xs-11">
								<a  class="btn btn-info" style="font-size: 15px" type="button" onClick="goToMainPage()" >Back
								</a>
								<input type="hidden" name="date" id="date" />
								<input type="hidden" name="treeNO" id="treeNO" />
								<input type="hidden" name="vTotCompWt" id="vTotCompWt" />
								<input type ="hidden" name="trfTblData" id="trfTblData"/>
							</td>												
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
	</div>
</div>



	</div>

</div> <!-- ending the main panel -->

<script type="text/javascript">

		
	$(document).ready(
			function(e) {
				if (window.location.href.indexOf('id') != -1) {

					var abcx = window.location.href;
					
					var castMtId = abcx.substring(window.location.href.indexOf('id') + 3);
					temp1 = castMtId.split("_");
					$('#castingMtId').val(temp1[0]);
					
					var tempDate = abcx.substring(window.location.href.indexOf('&cDate') + 7);
					temp = tempDate.split("_");
					$('#date').val(temp[0]);
					
					var tempTreeNo = abcx.substring(window.location.href.indexOf('&treeNo') + 8);
					temp2 = tempTreeNo.split("_");
					$('#treeNO').val(temp2[0]);	
					
					var tempTotCompWt = abcx.substring(window.location.href.indexOf('&totCompIssWt') + 14);
					$('#vTotCompWt').val(tempTotCompWt);
					
					popTranMt();
					
					
		/* 			$("#searchBags").on("keyup", function() {
					    var value = $(this).val();
					    
					    $("#tranMt tr").each(function(index) {
					
					        if (index != 0) {
			
					            $row = $(this);
					            var id = $row.find('td:eq(0)').text();  
					            var id1 = $row.find('td:eq(1)').text();  
					            var id2 = $row.find('td:eq(2)').text();
					            var id3 = $row.find('td:eq(3)').text();
					            var id4 = $row.find('td:eq(4)').text();
					            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0 && id1.toLowerCase().indexOf(value.toLowerCase()) != 0
					            		&& id2.toLowerCase().indexOf(value.toLowerCase()) !=0 && id3.toLowerCase().indexOf(value.toLowerCase()) !=0
					            		&& id4.toLowerCase().indexOf(value.toLowerCase()) !=0) {
					                $(this).hide();
					            }
					            else {
					                $(this).show();
					            }
					        }
					    });
					}); */
					
						
				}


				
			});
	
	
	
	
   function popTranMt() {
	   
	$("#tranMt")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/addCastingDt/listing.html?castingMtId="
								+ $("#castingMtId").val()
					});
		} 
	
	
	
	
	$(document).on(
			'submit',
			'form#transferToCastingDt',
		function(e) {
			
			$('#transferBtnToCasting').attr('disabled', 'disabled');
			
			var data = $('#tranMt').bootstrapTable('getSelections');
			
			$('#trfTblData').val(JSON.stringify(data));
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
							
			$.ajax({ 
				url : formURL,
				type : "POST",
				data : postData,
				
				 success : function(data, textStatus, jqXHR) {	
					 
					 if(data == 1){
						 popTranMt();
						 displayInfoMsg(this, null, 'RECORD TRANSFERED SUCESSFULLY !');
						 
					}else{
						 displayMsg(this,null,data);	
					}
					 

					 $('#transferBtnToCasting').removeAttr('disabled');
				
				},
				error : function(jqXHR, textStatus,
						errorThrown) {
					
					
				} 
				
			});
			e.preventDefault();  //STOP default action
		});
		
		function goToMainPage(){
			
			 window.location.href = "/jewels/manufacturing/transactions/castingDt.html?date="+$('#date').val() +"_" + "&treeId="+$('#treeNO').val()+"_"+"&totCompWt="+$('#vTotCompWt').val();
			
			
		}

</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>
