 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>

<script src="/jewels/chosen/chosen.jquery.js"></script>




<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Exchange Rate Master</span>
	</div>

	<form:form commandName="exchangeRate" id="exchangeRateFormId"
		action="/jewels/manufacturing/masters/exchangeRate/add.html" cssClass="form-horizontal exchangeRateForm">
		<c:if test="${success eq true}">
			<div class="alert alert-success">Exchange Rate ${action} successfully!</div>
		</c:if>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

				<div class="form-group">
			<label for="fromDate" class="col-sm-2 control-label">From Date
				:</label>
			<div class="col-sm-3">
				<form:input path="fromDate" cssClass="form-control" autocomplete="off"/>
				<form:errors path="fromDate" />
			</div>
		</div>
			
			
			<div class="form-group">
			<label for="toDate" class="col-sm-2 control-label">To Date
				:</label>
			<div class="col-sm-3">
				<form:input path="toDate" cssClass="form-control" autocomplete="off" />
				<form:errors path="toDate" />
			</div>
		</div>
			
		 <div class="form-group">
			<label for="currency" class="col-sm-2 control-label" id="currencyId" >Currency <span style="color: red;">*&nbsp;</span>:</label>
			<div class="col-sm-3">
				<form:select path="countryMaster.id" class="form-control" required="true">
					<form:option value="" label="--- Select currency---" />
					<form:options items="${countryMap}" />
				</form:select>
			</div>
		</div>
			
			<div class="form-group">
			<label for="exChngeRate" class="col-sm-2 control-label">Exchange Rate:</label>
			<div class="col-sm-3">
				<form:input path="exChngeRate" cssClass="form-control" />
				<form:errors path="exChngeRate" />
			</div>
		</div>
			
			
		<div class="row">
			<div class></div>
			</div>	&nbsp;
	
		<c:if test="${canAdd && canDelete}">
	<%-- 		<div class="form-group">
				<label for="Status" class="col-sm-2 control-label">De-Active
					:</label>
				<div class="col-sm-10">
					<form:checkbox path="deactive" value="0" />
				</div>
			</div> --%>
		</c:if>
		
		
		<div class="row">
		<div class="col-sm-4"></div>
		
		
			<div class="col-sm-4">
			
					<c:if test="${canAdd || canEdit}">
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Save" class="btn btn-primary" /> 
					<a class="btn btn-info" style="font-size: 15px" type="button"
						href="<spring:url value='/manufacturing/masters/exchangeRate.html' />">Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		</c:if></div>
			</div>

		
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
			
			$(".exchangeRateForm").validate(
				{
			
				
			});

			
		 	
			$("#fromDate").datepicker({
				dateFormat : 'dd/mm/yy'
			});
			
			$("#toDate").datepicker({
				dateFormat : 'dd/mm/yy'
			});
			
			
		}); 


 	
 	 	
/* 	
$(document).on('submit', 'form#exchangeRateFormId', function(e) {
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				if(data === "-1"){
					 displayMsg(this, null, data);
				}else{
					window.location.href = data;	
				}
				
				
				
			},
			
			error : function(data, textStatus, jqXHR){
				alert("ERROR");
			}
				
		})
		e.preventDefault(); //STOP default action
	}); */
	
 
	
</script>
<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">
<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

