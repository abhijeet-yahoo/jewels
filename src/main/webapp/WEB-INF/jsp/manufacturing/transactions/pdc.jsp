<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


	<div class="panel panel-primary" style="width: 100%; " >
	<div class="panel-heading" >
		<span style="font-size: 18px;">PDC</span>
	</div>
	
	<div class="panel body">

		<div class="container-fluid">
	
						<div>
							<table id="pdcTableId" data-toggle="table"
								data-toolbar="#toolbar" data-pagination="true"
								data-side-pagination="server" data-search="true"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350" data-striped="true">
								<thead>
									<tr class="btn-primary">
										<th data-field="createdDt" data-align="left">Date</th>
										<th data-field="styleId" data-align="left"  >StyleNo</th>
										<th data-field="version" data-align="left">Version</th>
										<th data-field="category" data-align="left">Category</th>
										<th data-field="purity" data-align="left">Purity</th>
										<th data-field="currentStk" data-align="center">Status</th>
										<th data-field="uploadImage" data-align="center">Image</th>
										<th data-field="action1" data-align="center">Edit</th>
										<th data-field="action2" data-align="center">Delete</th>
									</tr>
								</thead>
							</table>
						</div>
	
		</div>
	
	
	
	 <div id="pdAddId">
		<form:form commandName="pdc" id="pdcSubmit"
				action="/jewels/manufacturing/transactions/pdc/add.html"
				cssClass="form-horizontal pdcForm">
				
				<div class="row">
				
				
				<div>&nbsp;</div>
				
					<label for="StyleNo" class="col-sm-2 control-label" >Style No : </label>
					<div class="col-sm-2">
						<form:input path="design.styleNo" cssClass="form-control"   />
						<form:errors path="design.styleNo" />
					</div>
							
							
					<label for="Purity" class="col-sm-1 control-label" >Purity : </label>
					<div class="col-sm-2">
						<form:select path="purity.id" class="form-control">
							<form:option value="" label=" Select Purity " />
							<form:options items="${purityMap}" />
						</form:select>
					</div>
				
					
					 <div class="form-group">
						<div class="col-lg-1">
							   <input type="submit" value="Save" class="btn btn-primary" />
							   <form:input type="hidden" path="id" />
						</div>
				    </div>
						
			
						
				</div>
		</form:form>
	</div>
	
	</div> <!-- ending the panel body -->
	
</div> <!-- ending the main panel -->

<script type="text/javascript">


$(document)
.ready(
		function(e) {
			
			$(".pdcForm")
					.validate(
							{
								rules : {
									'design.styleNo' : {
										required : true,
										remote : {
											url : "<spring:url value='/manufacturing/transactions/pdc/styleNoAvailable.html' />",
											type : "get",
										
										}

									},
							
								'purity.id' : {
									required : true,
									}
									
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

								messages : {
									'design.styleNo' : {
										remote : "StyleNo Already Exits"
									}
					
								
								}
					
							});
	
			$("#design\\.styleNo").autocomplete({
				source : "/jewels/manufacturing/masters/styleNo/list.html",
				minLength : 2
			});
			
			$("#design\\.styleNo").focus();
			
			popPdcTable();
			
			
		});
		
		

		$(document)
		.on(
				'submit',
				'form#pdcSubmit',
				function(e) {
					
					
					var postData = $(this).serializeArray();
					var formURL = $(this).attr("action");
		
						$
							.ajax({
								url : formURL,
								type : "POST",
								data : postData,
		
								success : function(data, textStatus, jqXHR) {
				
									
									if(data === '-2'){
										displayMsg(this, null, 'StyleNo Not Found in Master Please Contact Admin');
									}else if(data === '-3'){
										displayMsg(this, null, 'Please Enter StyleNo With Version');
									}else if(data === '-4'){
										popPdcTable();
										$('#design\\.styleNo').val('');
										$('#purity\\.id').val('');
									}else{
										popPdcTable();
										$('#design\\.styleNo').val('');
									}
									
		
								},
								error : function(jqXHR, textStatus,
										errorThrown) {
		
								}
		
							});
					e.preventDefault();
				});


	function popPdcTable(){
		
		$('#pdcTableId').bootstrapTable('refresh',{
			url:"/jewels/manufacturing/transactions/pdc/listing.html",
		});
		
	}
	
	
	function deletePdc(e,pdId,status){

		if(status === false){
			displayMsg(this, null, 'Cannot Delete StyleNo Already in Process');
		}else{
			
			displayDlg(
					e,
					'javascript:deletePdcFalse('+pdId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
		}		
  }
	
	
	function deletePdcFalse(id){
		
		$("#modalDialog").modal("hide");
			
			$.ajax({
				
				url:'/jewels/manufacturing/transactions/pdc/delete/'+id+'.html',
				data: 'GET',
				success : function(data){
					
					if(data === '-1'){
						popPdcTable();
					}else{
						displayMsg(this, null, 'Cannot Delete StyleNo Already in Process');
					}
					
				}
			
			});
				
		
	}

	
	
	
	function editpdc(pdId){

		$.ajax({
			url:"/jewels/manufacturing/transactions/pdc/editValid.html",
			type:"GET",
			success: function(data){
				
				if(data === 'true'){
					$.ajax({
						url:"/jewels/manufacturing/transactions/pdc/edit/"+pdId+".html",
						type:"GET",
						success: function(data){
							$('#pdAddId').html(data);
						}
					});
				}else{
					displayMsg(this, null, 'cannot edit the record contact admin');
				}
					
			}
		})
		
	}
	
	
	
	

</script>


<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/design.js"></script>

<script src="/jewels/js/common/order.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

	