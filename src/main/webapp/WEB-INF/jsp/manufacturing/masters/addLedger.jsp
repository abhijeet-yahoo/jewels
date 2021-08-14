 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>



	<div class="ledgerMainDiv" id="ledgerledgerDivId">
	<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Ledger Master</span>
	</div>

			<div>
			
				<hr>
				
				<div id="msgDiv" style="display: none">
										<div id="msgId" class="alert alert-success"></div>
									</div>
				

				<div class="box-body">
					
								<form:form commandName="ledger" id="ledgerDivId"
									action="/jewels/manufacturing/masters/ledger/add.html"
									cssClass="form-horizontal ledgerForm">
	
										<div class="row">
											<div class="col-md-6">
												<div class="box-subtitle" style="font-weight: bolder;"
													align="center">General</div>

												<div class="row">&nbsp;</div>
												
											

												<div class="form-group">
													<label for="inputEmail3" class="col-sm-4 control-label">Name</label>

													<div class="col-sm-7">
														<form:input path="name" cssClass="form-control"
															onblur="javascript:upperCase(this),popMailing();" autocomplete="off"/>
														<form:errors path="name" />
													</div>
												</div>
												
												 <div class="form-group">
													<label for="inputEmail3" class="col-sm-4 control-label">Code</label>

													<div class="col-sm-7">
														<form:input path="partyCode" cssClass="form-control"
															onblur="javascript:upperCase(this)"/>
														<form:errors path="partyCode" />
													</div>
												</div>
												
												
												<%-- <div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Mailing
														Name</label>

													<div class="col-sm-7">
														<form:input path="mailingName" cssClass="form-control" />
														<form:errors path="mailingName" />
													</div>
												</div> --%>

												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Ledger
														Group</label>

													<div class="col-sm-7">
														<form:select path="ledgerGroup.id"
															class="form-control" style="width: 100%;" required="true">
															<form:option value="" label="Select LedgerGroup" />
															<form:options items="${ledgerGroupMap}" />
														</form:select>
													</div>


												</div>


												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Type</label>

													<div class="col-sm-7">
														<form:select path="type.id"
															class="form-control" style="width: 100%;">
															<form:option value="" label="Select Type" />
															<form:options items="${ledgerTypeMap}" />
														</form:select>
													</div>
												</div>

												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Contact
														Person</label>

													<div class="col-sm-7">
														<form:input path="contactPerson" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="contactPerson" />
													</div>
												</div>

												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Address</label>

													<div class="col-sm-7">
														<form:textarea path="address" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="address" />
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Transport Area</label>

													<div class="col-sm-7">
														<form:input path="area" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="area" />
													</div>
												</div>

												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Zone</label>

													<div class="col-sm-7">
														<form:input path="zone" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="zone" />
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">City</label>

													<div class="col-sm-7">
														<form:input path="city" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="city" />
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Zip</label>

													<div class="col-sm-7">
														<form:input path="zip" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="zip" />
													</div>
												</div>
												
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Country</label>

													<div class="col-sm-7">
														<form:select path="country.id" class="form-control" style="width: 100%;"
														onChange="javascript:popState(this.value);">
															<form:option value="" label="Select Country" />
															<form:options items="${countryMap}" />
														</form:select>
													</div>


												</div>
												
												
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">State</label>

													<div class="col-sm-7">
														<form:select path="stateMast.id"
															class="form-control" style="width: 100%;">
															<form:option value="" label="Select State" />
															<form:options items="${stateMap}" />
														</form:select>
													</div>


												</div> 
												
										
												<div class="form-group">
													<label for="officePhone" class="col-sm-4 control-label">Office
														Phone</label>
													<div class="col-sm-7">
														<form:input path="officePhone" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="officePhone" />
													</div>
												</div>

												<div class="form-group">
													<label for="fax" class="col-sm-4 control-label">Fax</label>
													<div class="col-sm-7">
														<form:input path="fax" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="fax" />
													</div>
												</div>

												<div class="form-group">
													<label for="webSite" class="col-sm-4 control-label">WebSite</label>
													<div class="col-sm-7">
														<form:input path="webSite" cssClass="form-control"
															onblur="javascript:lowerCase(this)" />
														<form:errors path="webSite" />
													</div>
												</div>

												<div class="form-group">
													<label for="mobile" class="col-sm-4 control-label">Mobile</label>
													<div class="col-sm-7">
														<form:input path="mobile" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="mobile" />
													</div>
												</div>

												<div class="form-group">
													<label for="emailAddress" class="col-sm-4 control-label">Email
														Address</label>
													<div class="col-sm-7">
														<form:input path="emailAddress" cssClass="form-control"
															onblur="javascript:lowerCase(this)" />
														<form:errors path="emailAddress" />
													</div>
												</div>

												<div class="form-group">
													<label for="salesMan" class="col-sm-4 control-label">Our Relationship Manager</label>
													<div class="col-sm-7">
														<form:input path="salesMan" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="salesMan" />
													</div>
												</div>

												<div class="form-group">
													<div class="col-sm-offset-4 col-sm-7">
														<div class="checkbox">
															<label> <form:checkbox path="deactive" value="0" />
																Not In Use
															</label>
														</div>
													</div>
												</div>
												
												<div class="form-group">
													<div class="col-sm-offset-4 col-sm-7">
														<div class="checkbox">
															<label> <form:checkbox path="defaultFlag" value="0" />
																Defalut
															</label>
														</div>
													</div>
												</div>
												
												<div class="form-group">
													<div class="col-sm-offset-4 col-sm-7">
														<div class="checkbox">
															<label> <form:checkbox path="exportClient" value="0" />
																Export Client
															</label>
														</div>
													</div>
												</div>


											</div>
											
											
											<!-- first half div end -->
											<div class="col-md-6">
												
												<div class="box-subtitle" style="font-weight: bolder;"
													align="center">Credit Details</div>
												<div class="row">&nbsp;</div>

												<div class="form-group">
													<label for="creditDays" class="col-sm-4 control-label">Credit
														Days </label>
													<div class="col-sm-7">
														<form:input path="creditDays"
															cssClass="form-control" />
														<form:errors path="creditDays" />
													</div>
												</div>

												<div class="form-group">
													<label for="creditLimit" class="col-sm-4 control-label">Credit
														Limit </label>
													<div class="col-sm-7">
														<form:input type="number" path="creditLimit"
															cssClass="form-control" />
														<form:errors path="creditLimit" />
													</div>
												</div>

												<div class="form-group">
													<label for="rateOfInterest" class="col-sm-4 control-label">Rate
														Of Interest </label>
													<div class="col-sm-2">
														<form:input type="number" path="rateOfInterest"
															cssClass="form-control" />
														<form:errors path="rateOfInterest" />
													</div>


													<label for="per" class="col-sm-1 control-label">Per</label>
													<div class="col-sm-2">
														<form:input type="number" path="perDays"
															cssClass="form-control" />
														<form:errors path="perDays" />
													</div>
													<label for="days" class="control-label">Days</label>

												</div>


												<div class="form-group">
													<label for="notes" class="col-sm-4 control-label">Notes
													</label>
													<div class="col-sm-7">
														<form:textarea path="notes" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="notes" />
													</div>
												</div>


												<div class="col-sm-12">
													<div class="row">&nbsp;</div>
												</div>

												<div class="box-subtitle" style="font-weight: bolder;"
													align="center">Tax Details</div>
												<div class="row">&nbsp;</div>


												<div class="form-group">
													<label for="gst" class="col-sm-4 control-label">Party
														Type </label>
													<div class="col-sm-7">
														<form:select path="partyType" class="form-control"
															style="width: 100%;">
															<form:option value="" label="Select Party Type" />
															<form:options items="${partyTypeList}" />
														</form:select>
													</div>
												</div>


												<div class="form-group">
													<label for="gst" class="col-sm-4 control-label">GSTN
													</label>
													<div class="col-sm-7">
														<form:input path="gst" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="gst" />
													</div>
												</div>




												<div class="form-group">
													<label for="panNo" class="col-sm-4 control-label">PAN
													</label>
													<div class="col-sm-7">
														<form:input path="panNo" cssClass="form-control"
															onblur="javascript:upperCase(this)" />
														<form:errors path="panNo" />
													</div>
												</div>
												
												<div class="box-subtitle" style="font-weight: bolder;"
													align="center">Transporter Details</div>
												<div class="row">&nbsp;</div>
												
													<div class="form-group">
													<label for="transporter" class="col-sm-4 control-label">Transporter
													</label>
													<div class="col-sm-7">
															<form:select path="transporter"	class="form-control" style="width: 100%;">
															<form:option value="" label="Select Transporter" />
															<form:options items="${transporterList}" />
														</form:select>
														
													</div>
												</div>
												
												<div class="form-group">
													<label for="tranBooking" class="col-sm-4 control-label">Booking At
													</label>
													<div class="col-sm-7">
														<form:input path="tranBooking" cssClass="form-control" 	/>
														<form:errors path="tranBooking" />
													</div>
												</div>
												
												<div class="form-group">
													<label for="tranDest" class="col-sm-4 control-label">Destination
													</label>
													<div class="col-sm-7">
														<form:input path="tranDest" cssClass="form-control"	 />
														<form:errors path="tranDest" />
													</div>
												</div>
								
											</div>
											
											</div>
								
										
								
								<c:if test="${canAdd || canEdit}">
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<input type="submit" value="Save" class="btn btn-primary" /> 
											<a class="btn btn-info" style="font-size: 15px" type="button"
												href="<spring:url value='/manufacturing/masters/ledger.html' />">Ledger Listing</a>
											<form:input type="hidden" path="id" />
										</div>
									</div>
								</c:if>
											
									
								</form:form>
							
						
						
						
						
				
				</div>
			</div>

		</div>
	</div>

	

<script type="text/javascript">


	 $(document).ready(function(e) {

		 $('#ledgerDivId #ledgerGroup\\.id').chosen();

		$('select').chosen();

		$.validator.setDefaults({ ignore: ":hidden:not(select)" });
			// validation of chosen on change
			if ($("select.form-control").length > 0) {
			    $("select.form-control").each(function() {
			        if ($(this).attr('required') !== undefined) {
			            $(this).on("change", function() {
			                $(this).valid();
			            });
			        }
			    });
			}
			
		 				
			$(".ledgerForm")
			    .validate({
						rules : {
							name : {
								required : true,
								 minlength : 1, 
								remote : {
									url : "<spring:url value='/manufacturing/masters/ledgerAvailable.html' />",
									type : "get",
									data : {
										
										id : function() {
											return $("#id").val();
										}
									}
							
								}
							},
		
							mailingName : {
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
							name : {
								remote : "Ledger Name already exists"
							},
							code : {
								remote : "Ledger Code already exists"
							}

						}
					});
						
	 })
	 
	 
	 
	 function popMailing(){
		 $('#mailingName').val($('#name').val());
	 }
	 	 
	
	 function popState(id){
		 
		 $
			.ajax({
				url : "/jewels/manufacturing/masters/state/list.html?countryId="
						+ id,
				type : 'GET',
				success : function(data) {
					$("#stateMast\\.id").html(data);
					$("#stateMast\\.id").trigger("chosen:updated");
				}
			});
	 }
	 
	 
	
	 
	 
</script>




<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>

<script src="/jewels/js/common/design.js"></script>
 