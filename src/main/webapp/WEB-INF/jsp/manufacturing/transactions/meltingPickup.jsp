<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
        
<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">

	<div class="panel-heading">
		<span style="font-size: 18px;">Melting Pickup</span>
	</div>
	
	<div class="panel-body">

	<div class="form-group">
		<label for="name" class="col-sm-1 control-label">Search : </label>
		<div class="col-sm-3">
			<input type="search" id="searchBags"  class="search form-control" placeholder="Search " />
		</div>
	</div>

		

<div class="form-group" id="dsPId">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div>
					<table id="tranMtTblId" data-toggle="table" 
						data-toolbar="#toolbarDt"
						data-side-pagination="server" 
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="280">
						<thead>
							<tr>							
								<th data-field="state" data-checkbox="true">Select</th>								
								<th data-field="party" data-sortable="true">Party</th>
								<th data-field="orderNo" data-sortable="true">OrderNo</th>
								<th data-field="bagNo" data-align="left" data-sortable="true">Bag No</th>
								<th data-field="style" data-align="left" data-sortable="true">Style No</th>
								<th data-field="purity" data-align="left" data-sortable="true">Purity</th>
								<th data-field="color" data-align="left" data-sortable="true">Color</th>
								<th data-field="grossWt" data-align="left" data-sortable="true">Gross Wt</th>
								<th data-field="pcs" data-align="left" data-sortable="true">Pcs</th>
								
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
		<div class="col-lg-2 col-sm-2">
			<a class="btn btn-primary" style="font-size: 15px" type="button" id="meltIssBtnId"
				onClick="meltingtransfer()"> Issue For Melt
			</a> 
			
			
		</div>
		<div class="col-lg-2 col-sm-2">
			<a class="btn btn-info" style="font-size: 15px" type="button"
				onClick="goToMelting()"> Back
			</a> 
			
			
		</div>
		
		
		
	<%-- 		<form:form commandName="castingDt" id="transferToCastingDt"
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
			</form:form> --%>
		</div>
	</div>
</div>



	</div>

</div> <!-- ending the main panel -->

<script type="text/javascript">

var meltingMtId=0;		
	$(document).ready(
			function(e) {
				if (window.location.href.indexOf('meltingMtId') != -1) {

					var abcx = window.location.href;
					
					meltingMtId = abcx.substring(window.location.href.indexOf('meltingMtId') + 12);
										
								
					popTranMt();
					
					
					$("#searchBags").on("keyup", function() {
					    var value = $(this).val();
					    
					    $("#tranMtTblId tr").each(function(index) {
					
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
					});
					
						
				}


				
			});
	
	
	
	
   function popTranMt() {
	   
	$("#tranMtTblId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/meltingPickup/listing.html"
								
					});
		} 
	
	
	
	
	$(document).on(
			'submit',
			'form#transferToCastingDt',
		function(e) {
			
			
			
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
					
					alert("error : Contact Admin");
					
				} 
				
			});
			e.preventDefault();  //STOP default action
		});
		
	
	
	
	
		function goToMelting(){
			
			 window.location.href = "/jewels/manufacturing/transactions/meltingMt/edit/" + meltingMtId
				+ ".html"
			
			
		}
		
		
		function meltingtransfer(){
			
			
			$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
			
			$('#meltIssBtnId').attr('disabled', 'disabled');
			
			var data = $('#tranMtTblId').bootstrapTable('getSelections');
			
			tblSeldata =JSON.stringify(data);
			
						
		$.ajax({
			url : "/jewels/manufacturing/transactions/meltingPickup/save.html",
			type : "POST",
			data : {
				meltingMtId : meltingMtId,
				tblData : tblSeldata
			},
			
			success : function(data, textStatus, jqXHR) {
				
				 if(data == 1){
					 popTranMt();
					 displayInfoMsg(this, null, 'Melting Issue Successfully !');
					 
				}else{
					 displayMsg(this,null,data);	
				}
				 
				 
					$('#meltIssBtnId').removeAttr('disabled');
					
					$.unblockUI();
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				displayMsg(this,null,"Error Occoured Contact Admin");
				$.unblockUI();
				}
			
		});
			
			
			
			
		}

</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>

