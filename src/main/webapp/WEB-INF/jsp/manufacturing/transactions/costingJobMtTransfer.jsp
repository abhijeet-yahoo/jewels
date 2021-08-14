<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">

	<div class="panel-body">
	
	
	
	
	<div class="col-xs-12">
	
			<div class="col-xs-3">
				<input type="search" id="searchBags" class="search form-control" placeholder="Search" style="width: 5cm"/>
			</div>
			
			
			<div class="form-group"> 
				<label class="col-sm-2 text-right">Item No:</label>
				<div class="col-xs-2">
					<input type="text" id="vItemNo" name="vItemNo" class="form-control" onblur="javascript:popItemNos()"/>
				</div>
			</div>


	</div>
	
	
	
	
	
		<div class="form-group" id="dsPId">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="fromCostingDt" data-toggle="table"
								data-toolbar="#toolbarDt" data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
								<thead>
									<tr class="btn-primary">
										<th data-field="state" data-checkbox="true" >Select</th>
										<th data-field="itemNo"  data-sortable="true">ItemNo</th>
										<th data-field="orderNo" data-sortable="true">OrderNo</th>
										<th data-field="bagNo" data-align="left" data-sortable="true">Bag No</th>
										<th data-field="style" data-align="left" data-sortable="true">StyleNo</th>
										<th data-field="pcs" data-align="left" data-sortable="true">Pcs</th>
										<th data-field="grossWt" data-sortable="true">GrossWt</th>
										<th data-field="trfQty" data-align="left" data-sortable="true">TrfQty</th>
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
					<form:form commandName="costingDt" id="transferToCostJobDt"
						action="/jewels/manufacturing/transactions/transfer/costingJobDt.html"
						cssClass="form-horizontal transferForm">

						<table class="table table-bordered">
							<tbody>
								<tr>
									<td class="col-xs-1">
										<input type="submit" value="Transfer" class="btn btn-primary" id="trfButtonId"/> 
										<form:input type="hidden" path="id" />
										<input type="hidden" name="costingJobMtId" id="costingJobMtId" />
										<input type="hidden" name="pCostDtIds" id="pCostDtIds" />
										<input type="hidden" name="pTrfQty" id="pTrfQty" />
									</td>
									<td class="col-xs-1">
										<a class="btn btn-info"
											style="font-size: 15px" type="button" onClick="goToMainPage()">Back
										</a>
									</td>
									<td class="col-xs-10"></td>
									
								</tr>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
		 </div>
		
		
		
		
		
	
	
	
	
	</div> <!-- ending the  panel body -->
 
</div> <!-- ending the main panel -->


<script type="text/javascript">

	var xstnTblRow  = -1;

	$(document).ready(function(){
		
		
		//----------search---------//
		
		
		
		$("#searchBags").on(
					"keyup",
					function() {
						var value = $(this).val();

						$("#fromTranMt tr").each(
								function(index) {

									if (index != 0) {
										$row = $(this);

										var id = $row.find('td:eq(3)').text();
										if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
											$(this).hide();
										} else {
											$(this).show();
										}
									}

								
								});
						
					});
		
		
		
		
		if (window.location.href.indexOf('MtId') != -1) {

			var abcx = window.location.href;
			var costMtId = abcx.substring(window.location.href.indexOf('MtId') + 5);
			$('#costingJobMtId').val(costMtId);
				
		}
		
	

		 $("#fromCostingDt").on("dblclick", "td:not(.active)", function () {
			 
			 var column_num = parseInt( $(this).index());
			 
			 if(column_num === 7){
				
				 var $this = $(this);
		   		 var $textbox = $("<input>", { type: "text",value: $this.addClass("active").text(), width : 35 });
		    	 $this.html($textbox);
		         $textbox.select();
		         $textbox.focus();
				 
			 }
		
	    
		});
		
	

		 $("#fromCostingDt").on("blur", "input:text", function () {  
			   
			   var $this = $(this);
			   $this.parent().removeClass("active").text($this.val());
			  
			    
			    $("#fromCostingDt").bootstrapTable('updateRow', {
					index : xstnTblRow,
					row : {
						state : true,
						trfQty : $this.val(),
		
					}
				}); 
			    
			    
			    
			}); 		 
		 
		
	});


	
	
	
	
	$('#fromCostingDt').bootstrapTable({}).on(
			'click-row.bs.table',
			function(e, row, $element) {
			
				xstnTblRow = $element.attr('data-index');
				
			})
	
	
	
	
	
	

	
  function popItemNos(){
	  
	if(!!$('#vItemNo').val()){ 
	  		
		$("#fromCostingDt")
			.bootstrapTable(
				'refresh',
				{
					url : "/jewels/manufacturing/transactions/fromCostingDt/listing.html?itemNo="
							+ $('#vItemNo').val()
				});
		
	}else{

		var temp = "fake";
		
		$("#fromCostingDt")
		.bootstrapTable(
			'refresh',
			{
				url : "/jewels/manufacturing/transactions/fromCostingDt/listing.html?itemNo="
						+temp,
			});
		
	}
	
  }
	
  
  
  
  function goToMainPage(){
	  window.location.href = "/jewels/manufacturing/transactions/costingJobMt/edit/"+$('#costingJobMtId').val()+".html";
	  
  }
  
  
 
  
  $(document).on(
			'submit',
			'form#transferToCostJobDt',
		function(e) {
			
				
				//alert("XXXXXXXXXXX");
				
			$('#trfButtonId').attr('disabled', 'disabled');
			
			var data = $('#fromCostingDt').bootstrapTable('getSelections');
			
			var ids = $.map(data, function (item) {
					return item.id;
			});
			
			var vTrfQty = $.map(data, function (item) {
							if(item.trfQty == ''){
								return 0;
							}else{
								return item.trfQty;
							}
							
			});
			
			
			
							
			$('#pCostDtIds').val(ids);	
			$('#pTrfQty').val(vTrfQty);
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");
							
			$.ajax({ 
				url : formURL,
				type : "POST",
				data : postData,
				
				 success : function(data, textStatus, jqXHR) {	
					 
					 if(data === '-1'){
						 displayInfoMsg(this, null, "Data transfered Sucessfully !");
						 $('#vItemNo').val('')
						 popItemNos();
					 }else if(data === '-3'){
						 displayMsg(this, null, "Cannot Transfer With Quantity 0");
					 }else{
						 displayMsg(this, null, "Please Select Atleast One Record To Transfer !");
					 }
					 
					 $('#trfButtonId').removeAttr('disabled');
					 
				},
				error : function(jqXHR, textStatus,
						errorThrown) {
					
					alert("please select atleast one record to transfer");
					alert("record not found");
				} 
				
			});
			e.preventDefault();  
		});
	
	
  
  
  
  
  
  
  
  
	

</script>












