<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
</style>



	<!--------- Auto Style Generation modal --------->
	
	<div class="modal fade" id="myAutoStyleGenerationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Auto Style Generation</h4>
	      </div>
	      
	      <div class="modal-body">
	      <form:form commandName="design" id="designModalDivId"
		action="/jewels/manufacturing/masters/autoDesignParam/add.html"
		cssClass="form-horizontal autoDesignForm">
	
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<div class="row">

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Category :</label>
			<div class="col-sm-4">
				<form:select path="category.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Category " />
							<form:options items="${categoryMap}" />
						</form:select>
			</div>
		</div>
		
		
		</div>
		
			<div class="row">

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Collection :</label>
			<div class="col-sm-4">
				<form:select path="collectionMaster.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Collection " />
							<form:options items="${collectionMap}" />
						</form:select>
			</div>
		</div>
		
		
		</div>
		
			<div class="row">

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Vendor :</label>
			<div class="col-sm-4">
					<form:select path="vendor.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Vendor" />
							<form:options items="${vendorMap}" />
						</form:select>
			</div>
		</div>
		
		
		</div>
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		
		<div class="row">
		<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10"> 
					<input type="button" value="Save" class="btn btn-default" onclick="javascript:autoDesignSave()"/>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		
		</div>
		
		</form:form>
	      
	      	</div>
	      
	      </div>
	      
	      </div>
	     </div> 
	     
	     
	     
	<script type="text/javascript">

	$(document).ready(function(){
		
		
	  $(".autoDesignForm").validate({
			rules : {
				'category.id' : {
					required : true,
				},
				'collectionMaster.id' : {
					required : true,
				},
				'vendor.id' : {
					required : true,
				},
			},highlight : function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			}
		});

	  $('#designModalDivId').find('select').val('');

	});

	function autoDesignSave(){
		
		var postData = $("#designModalDivId").serializeArray();
		
		var formURL = $("#designModalDivId").attr("action");

		 if($(".autoDesignForm").valid()){
				
				$.ajax({
					url : formURL,
					type : "POST",
					data : postData,
					success : function(data, textStatus, jqXHR) {

						if(data === 'added'){
							displayInfoMsg(this,null,"Record Added Successfully !");
							$('#myAutoStyleGenerationModal').modal('hide');
							popDesignListing();
							
							 $('#designModalDivId').find('select').val('');
							}else{
								displayMsg(this,null,data);
								}
					
							
						
					
					
						
					},
					error : function(data, textStatus, jqXHR) {
						displayMsg(this,null,"Error Occoured , Contact Admin !");
					}
			
				});
			
			 }
		
			}

	


	</script>
	<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

	<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

	<script src="<spring:url value='/js/common/design.js' />"></script>

	<script src="<spring:url value='/js/common/common.js' />"></script>

	<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

	<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />



	<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
		
		
			     
	     
	     
	     