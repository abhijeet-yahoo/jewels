 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

 <%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>

<script src="/jewels/chosen/chosen.jquery.js"></script>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Client Kt Conversion</span>
	</div>

	<form:form commandName="clientKtConvMast" id="clientKtConvMastFormId"
		action="/jewels/manufacturing/masters/clientKtConvMast/add.html"
		cssClass="form-horizontal clientKtConvForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">CLientKtConv ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		  <div class="form-group">
			<label for="party" class="col-sm-2 control-label" id="partyId" >Party <span style="color: red;">*&nbsp;</span> :</label>
			<div class="col-sm-3">
				<form:select path="party.id" class="form-control" required="true">
					<form:option value="" label="--- Select Party---" />
					<form:options items="${partyMap}" />
				</form:select>
			</div>
		</div>
		
		
			  <div class="form-group">
			<label for="purity" class="col-sm-2 control-label" id="purityId" >Purity <span style="color: red;">*&nbsp;</span>:</label>
			<div class="col-sm-3">
				<form:select path="purity.id" class="form-control" required="true">
					<form:option value="" label="--- Select Purity---" />
					<form:options items="${purityMap}" />
				</form:select>
			</div>
		</div>
		
	<div class="form-group">
			<label for="purityConv" class="col-sm-2 control-label">Purity Conversion :</label>
			<div class="col-sm-3">
				<form:input path="purityConv" cssClass="form-control" tyep="number" autocomplete="off"/>
				<form:errors path="purityConv" />
			</div>
		</div>		

		<c:if test="${canAdd && canDelete}">
			<div class="form-group">
				<label for="Status" class="col-sm-2 control-label">De-Active
					:</label>
				<div class="col-sm-10">
					<form:checkbox path="deactive" value="0" />
				</div>
			</div>
		</c:if>
		
		<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="<spring:url value='/manufacturing/masters/clientKtConvMast.html' />">Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if>
		
	</form:form>
	
	
				
				
				
	
</div>
<script type="text/javascript">
 $(document).ready(
		function(e) {
			
			$('select').chosen();
			
			$.validator.setDefaults({ ignore: ":hidden:not(select)" });
		
								
			if ($("select.form-control").length > 0) {
			    $("select.form-control").each(function() {
			        if ($(this).attr('required') !== undefined) {
			            $(this).on("change", function() {
			                $(this).valid();
			            });
			        }
			    });
			} 
			
			
			$(".clientKtConvForm").validate(
				{
					
				
			});
			
			
		});
	
	
$(document).on('submit', 'form#clientKtConvMastFormId', function(e) {
	
	
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				console.log(" "+data);
				if(data === '-1'){
					
					displayMsg(this, null, 'Party And purity already exists');
			
				}else if(data === '-2'){
					
					displayInfoMsg(this, null, 'Client Kt Conversion added Successfully!');
					$('form#clientKtConvMastFormId select').val('').trigger('chosen:updated');
					$('#purityConv').val('');
				
					
				}else{
					displayInfoMsg(this, null, 'Client Kt Conversion updated Successfully!');
					$('form#clientKtConvMastFormId select').val('').trigger('chosen:updated');
					$('#purityConv').val('');
					setTimeout(function displayInfoMsg(){
						window.location.href = data;  
					}, 2000);
					
					
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
		
	});
	 

	 

							
	
</script>