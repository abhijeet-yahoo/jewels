 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>

<script src="/jewels/chosen/chosen.jquery.js"></script>




<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Auto Style Param</span>
	</div>

	<form:form commandName="autoStyleParameter" id="autoStyleParamFormId"
		action="/jewels/manufacturing/masters/autoStyleParameter/add.html"
		cssClass="form-horizontal autoStyleParameterForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">AutoStyleParameter ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		  <div class="form-group">
			<label for="category" class="col-sm-2 control-label" id="categoryId" >Category <span style="color: red;">*&nbsp;</span> :</label>
			<div class="col-sm-4">
				<form:select path="category.id" class="form-control" required="true">
					<form:option value="" label="--- Select Catgory---" />
					<form:options items="${categoryMap}" />
				</form:select>
			</div>
		</div>
		
		
			  <div class="form-group">
			<label for="collection" class="col-sm-2 control-label" id="collectionId" >Collection <span style="color: red;">*&nbsp;</span>:</label>
			<div class="col-sm-4">
				<form:select path="collectionMaster.id" class="form-control" required="true">
					<form:option value="" label="--- Select collection---" />
					<form:options items="${collectionMap}" />
				</form:select>
			</div>
		</div>
		
	<div class="form-group">
			<label for="lastNo" class="col-sm-2 control-label">Last no &nbsp;:</label>
			<div class="col-sm-4">
				<form:input path="lastNo" cssClass="form-control" tyep="number" autocomplete="off"/>
				<form:errors path="lastNo" />
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
						href="<spring:url value='/manufacturing/masters/autoStyleParameter.html' />">Listing</a>
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
			
			$(".autoStyleParameterForm").validate(
				{
			
				
			});

			
		});
	
	
$(document).on('submit', 'form#autoStyleParamFormId', function(e) {
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
			
				if(data === '-1'){
					displayMsg(this, null, 'Category and Collection already exist!');
					 $('#hideOnPageLoad').css('display','none'); 
					
				}else if(data === '-2'){
					displayInfoMsg(this, null, ' Auto Style Parameter added Successfully!');
					$('form#autoStyleParamFormId select').val('').trigger('chosen:updated');
					$('#lastNo').val('');
				}else{
					
					displayInfoMsg(this, null, ' Auto Style Parameter updated Successfully!');
					$('form#autoStyleParamFormId select').val('').trigger('chosen:updated');
					$('#lastNo').val('');
					
					 /* window.location.href = data; */
				}
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});
	

	
</script>