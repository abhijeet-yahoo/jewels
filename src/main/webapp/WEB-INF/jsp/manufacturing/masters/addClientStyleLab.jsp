<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" id="tabdiv">
		<span style="font-size: 18px;margin-right:62em;">Style Labour Rate</span>
	</div>
	
	
	<form:form commandName="clientStyleLab"
		action="/jewels/manufacturing/masters/clientStyleLab/add.html"
		cssClass="form-horizontal labourChargeForm">

		<c:if test="${param.success eq true}">
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="alert alert-success">Labour ${param.action} successfully!</div>
		</c:if>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
        </div>	
	  <div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="partLabelId">Party <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:select path="party.id" class="form-control">
						<form:option value="" label="--- Select Party ---" />
						<form:options items="${partyMap}" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Style No <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:input path="design.mainStyleNo" cssClass="form-control"/>
			</div>
	   </div>
	   
	   <div class="form-group">
			<label for="name" class="col-sm-2 control-label">Metal <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:select path="metal.id" class="form-control">
					<form:option value="" label="--- Select Metal ---" />
					<form:options items="${metalMap}" />
				</form:select>
			</div>
	   </div>
	   
	   <div class="form-group">
			<label for="name" class="col-sm-2 control-label">Purity <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:select path="purity.id" class="form-control">
					<form:option value="" label="--- Select Purity ---" />
					<form:options items="${purityMap}" />
				</form:select>
			</div>
	   </div>
	   
	
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Labour Type <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:select path="labourType.id" class="form-control">
					<form:option value="" label="--- Select LabourType ---" />
					<form:options items="${labTypeMap}" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">From Weight <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:input path="fromWeight" cssClass="form-control" />
				<form:errors path="fromWeight" />
			</div>
	   </div>
	   <div class="form-group">
			<label for="name" class="col-sm-2 control-label">To Weight <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:input path="toWeight" cssClass="form-control" />
				     <form:errors path="toWeight" />
			</div>
	   </div>
	   <div class="form-group">
			<label for="name" class="col-sm-2 control-label">Rate <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:input path="rate" cssClass="form-control" />
				<form:errors path="rate" />
			</div>
	   </div>
	   
	  
	   
	   <div class="form-group">
	   		<div class="col-sm-1"></div>
	   			<div class="col-sm-2">
					<label> <form:checkbox path="perPcsRate" /> <b>PerPcsRate</b></label>
				</div>
				<div class="col-sm-2">
					<label> <form:checkbox path="perGramRate" /> <b>PerGramRate</b></label>
				</div>
				<div class="col-sm-2">
					<label> <form:checkbox path="percentage" /> <b>Percentage</b></label>
				</div>
				<div class="col-sm-2">
					<label> <form:checkbox path="perCaratRate" /> <b>PerCaratRate</b></label>
				</div>
			</div>
			
	   
	  
		
	
		
		

	   
	<div class="row">
			<div class="col-xs-12">&nbsp;</div>
    </div>
		
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-5">
				<c:if test="${canAdd || canEdit}">
					<input type="submit" value="Save" class="btn btn-primary" /> 
				</c:if>	
					<a id="listingId"
						class="btn btn-info" style="font-size: 14px" type="button"
						href="/jewels/manufacturing/masters/clientStyleLab.html">Listing</a>
					<form:input type="hidden" path="id" />
				</div>
			</div>
		
	</form:form>
	
	 <input type="hidden" id="viewOnly" name="viewOnly" value="${canView}">
	 <input type="hidden" id="editRight" name="editRight" value="${editRight}">
	
	</div>
	
	
		
<script type="text/javascript">
	
	$(document)
	.ready(
			function(e) {
				
				$("#design\\.mainStyleNo").autocomplete({
					source: "/jewels/manufacturing/masters/design/autoFillList.html",
					minLength : 2
				});
				
				
				
				$(".labourChargeForm")
						.validate(
								{
									rules : {
									
										'design.mainStyleNo' : {
											required : true,
										},
									'metal.id' : {
											required : true,
										},
									'party.id' : {
											required : true,
										},
									'labourType.id' : {
											required : true,
										},
									
									fromWeight : {
											required : true,
											greaterThan : "0,0",
										},
									toWeight : {
											required : true,
											greaterThan : "0,0",
										},
									rate : {
											required : true,
											greaterThan : "0,0",
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
									
									messages : {
										fromWeight : {
											greaterThan : "This field is required"
										},
										toWeight : {
											greaterThan : "This field is required"
										},
										rate : {
											greaterThan : "This field is required"
										}
									},
									
								});
				//$("input:text:visible:first").focus();
				
					if (window.location.href.indexOf('view') != -1) {
				       checkViewMode();
				
			      }
					
				
			});	

	

	
	
	
	
	$(document)
	.on(
		'submit',
		'form#clientStyleLab',
		function(e) {
			
			var postData = $(this).serializeArray();
			var formURL = $(this).attr("action");

			$
					.ajax({
						url : formURL,
						type : "POST",
						data : postData,
						success : function(data, textStatus, jqXHR) {	
							if(data === '-1'){
								displayMsg(this, null, 'Party,Design and LabourType exists');
							}else if(data === '-2'){
								displayMsg(this, null, 'Please select one rate only');
							}else if(data === '-3'){
								displayMsg(this, null, 'Please select atleast one rate');
							}else if(data === '-4'){
								displayMsg(this, null, 'Dublicate entry found, check the entry');
							}else if(data === '-5'){
								displayMsg(this, null, 'ToWeight Always Greater Than FromWeight');
							}else if(data.indexOf("id") != -1){
								window.location.href = data;
							}else{
								window.location.href = data;
							}
							

						},

						error : function(jqXHR, textStatus,errorThrown) {

						}
					});
			e.preventDefault(); //STOP default action
		});

	
	
	
	
	
	
	
	
	
</script>
	
<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/common/viewModeOnly.js"></script>
	