<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


	<div class="panel panel-primary" style="width: 100%; " >
	<div class="panel-heading" >
		<span style="font-size: 18px;">PDC SET OPENING</span>
	</div>
	
	<div class="panel body">
		<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
                                 <div class="col-xs-2">
										<input type="search" id="searchPdcTran"  placeholder="Search"  class="search form-control" />
									</div>
								
		<div class="container-fluid">
	
						<div>
							<table id="pdcTranTableId" data-toggle="table"
								data-toolbar="#toolbar" data-pagination="false"
								data-side-pagination="server" data-search="false"
								 data-height="350" data-striped="true">
								<thead>
									<tr class="btn-primary">
									    <th data-field="state" data-checkbox="true"></th>
										<th data-field="createdDt" data-align="left">Date</th>
										<th data-field="styleId" data-align="left"  >StyleNo</th>
										<th data-field="version" data-align="left">Version</th>
										<th data-field="category" data-align="left">Category</th>
										<th data-field="purity" data-align="left">Purity</th>
										<th data-field="currentStk" data-align="center">Status</th>
										<th data-field="uploadImage" data-align="center">Image</th>
										<!-- <th data-field="action2" data-align="center">Delete</th> -->
									</tr>
								</thead>
							</table>
						</div>
	
		</div>
	
	
	<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
	
	
	<form:form commandName="pdcTranMt" id="pdcSubmit"
			action="/jewels/manufacturing/transactions/PDCTranMt/add.html"
			cssClass="form-horizontal pdcForm">
			
			<div class="row">
			<div class="col-lg-1 col-sm-1"></div>
					<div class="col-lg-1 col-sm-1">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Department:</label>
					</div>
					
					<div class="col-lg-2 col-sm-2" >
					<form:select path="department.id" class="form-control">
						<form:option value="" label="- Select Department -" />
						<form:options items="${departmentMap}" />
					</form:select>
				</div>
					
		    <div class="form-group">
				<div class="col-lg-1">
				      <input type="submit" value="Transfer" class="btn btn-primary" />
				    <input type="hidden" id="pODIds" name="pODIds" />
				      <input type="hidden" id="deptId" name="deptId" />
				</div>
	    	</div>
					
			</div>
	
	
	</form:form>
	
	</div> <!-- ending the panel body -->
	
</div> <!-- ending the main panel -->

<script type="text/javascript">


$(document)
.ready(
		function(e) {
			
			
			$("#design\\.styleNo").autocomplete({
				source : "/jewels/manufacturing/masters/styleNo/list.html",
				minLength : 2
			});
			
			popPdcTable();
		
			
			$("#searchPdcTran").on("keyup", function() {
			    var value = $(this).val();
			    
			    $("#pdcTranTableId tr").each(function(index) {
			
			        if (index != 0) {

			            $row = $(this);
			            
			            var iTNoId = $row.find('td:eq(1)').text();  
			            var styNoId = $row.find('td:eq(2)').text();  
			            var bId = $row.find('td:eq(4)').text();
			            
			            if (iTNoId.toLowerCase().indexOf(value.toLowerCase()) != 0 && styNoId.toLowerCase().indexOf(value.toLowerCase()) != 0 && bId.toLowerCase().indexOf(value.toLowerCase()) !=0 ) {
			                $(this).hide();
			            }
			            else {
			                $(this).show();
			            }
			            
			        }
			    });
			});
			

		});
		
		

$(document)
.on(
		'submit',
		'form#pdcSubmit',
		function(e) {
			
			$('#deptId').val($('#department\\.id').val());
			
			var data = $('#pdcTranTableId').bootstrapTable('getSelections');
			
			var ids = $.map(data, function (item) {
					return item.id;
			});
							
				
			$('#pODIds').val(ids);	
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");

			$
					.ajax({
						url : formURL,
						type : "POST",
						data : postData,

						success : function(data, textStatus, jqXHR) {
		
						
							
							if(data === '-2'){
								displayMsg(this, null, 'No record Selected to Transfer');
							}else{
								displayInfoMsg(this, null, 'Record Transfer Successfully');
								popPdcTable();
								
								$('#department\\.id').val('');
								
							} 
						
						},
						error : function(jqXHR, textStatus,
								errorThrown) {

						}

					});
			e.preventDefault();
		});



$(".pdcForm").validate({

	rules : {
		'department.id' : {
			required : true,

		},
	},
	highlight : function(element) {
		$(element).closest(
				'.form-group')
				.removeClass(
						'has-success')
				.addClass('has-error');
	},
	unhighlight : function(element) {
		$(element)
				.closest('.form-group')
				.removeClass(
						'has-error')
				.addClass('has-success');
	},

	
});



	function popPdcTable(){
		
		$('#pdcTranTableId').bootstrapTable('refresh',{
			url:"/jewels/manufacturing/transactions/PDCTranMt/listing.html",
		});
		
	}
	
	
	
	
	





</script>


<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/design.js"></script>

<script src="/jewels/js/common/order.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

	