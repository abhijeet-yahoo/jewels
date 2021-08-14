<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" id="tabdiv">
		<span style="font-size: 18px;margin-right:63em;">Finding Rate</span>
	</div>
	
	<form:form commandName="findingRateMast" id="findingRateMastEntryId"
		action="/jewels/manufacturing/masters/findingRateMast/add.html"
		cssClass="form-horizontal findingRateMastForm">
		
		<c:if test="${param.success eq true}">
			<div class="alert alert-success">Finding Rate added ${action}
				successfully!</div>
		</c:if>
	  <div class="row">
			<div class="col-xs-12">&nbsp;</div>
      </div>
		    
    	<div class="form-group">
			<label for="name" class="col-sm-2 control-label" id="partLabelId">Party <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:select path="party.id" class="form-control">
					<form:option value="" label="--- Select Party---" />
					<form:options items="${partyMap}" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Finding <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:select path="component.id" class="form-control">
					<form:option value="" label="--- Select Finding ---" />
					<form:options items="${componentMap}" />
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
			<label for="name" class="col-sm-2 control-label">Rate <span style="color: red;">*</span></label>
			<div class="col-sm-5">
				<form:input path="rate" cssClass="form-control" />
				<form:errors path="rate" />
			</div>
	  </div>
	  <c:if test="${canEdit && canDelete}">
      <div class="form-group">
			<label for="name" class="col-sm-2 control-label">Per Pc Rate </label>
			<div class="col-sm-1" style="padding-right: 80px;">
				 <form:checkbox path="perPcRate" value="0" />
			</div>
	  </div>
	  </c:if>
	  <c:if test="${canEdit && canDelete}">
	  <div class="form-group">
			<label for="name" class="col-sm-2 control-label">Per Gram Rate </label>
			<div class="col-sm-1" style="padding-right: 80px;">
				<form:checkbox path="perGramRate" value="0" />
			</div>
	  </div>
	  </c:if>
    
   
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-4">
			 <c:if test="${canAdd || canEdit}">
				<input type="submit" value="Save" class="btn btn-primary" /> 
			</c:if>	
				<a id="listingId" class="btn btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/masters/findingRateMast.html">Finding Rate Listing</a>
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
			$(".findingRateMastForm")
					.validate(
							{
								rules : {
								rate : {
									required : true,
									greaterThan : "0,0",
									},
								'party.id' : {
										required : true,
									},
								'component.id' : {
										required : true,
									},
								'purity.id' : {
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
								
								messages : {
									rate : {
										greaterThan : "This field is required"
									}
								},
								
							});
			
			if (window.location.href.indexOf('view') != -1) {
				checkViewMode();
				
			}
			
			
			
			
		});	




 $(document)
	.on(
		'submit',
		'form#findingRateMastEntryId',
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
								displayMsg(this, null, 'Dublicate Entry Found, Please Check The Entry');
							}else if(data === '-2'){
								displayMsg(this, null, 'please select only one check box');
							}else{
								window.location.href = data;
							} 
							
						},

						error : function(jqXHR, textStatus,
								errorThrown) {
						}
					});
			e.preventDefault(); 
		}); 
	

</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/design.js"></script>

<script src="/jewels/js/common/order.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/common/viewModeOnly.js"></script>
			