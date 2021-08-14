<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>

<script src="/jewels/chosen/chosen.jquery.js"></script>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
		<span style="font-size: 18px;">Party Master</span>
		
		
		
	</div>
	<div class="form-group">
		<form:form commandName="party"
			action="/jewels/manufacturing/masters/party/add.html"
			cssClass="form-horizontal partyForm">

			<c:if test="${success eq true}">
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

				<div class="alert alert-success">Party ${action}
					successfully!</div>
			</c:if>

			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="row">
											<div class="col-md-6">
												<div class="box-subtitle" style="font-weight: bolder;"
													align="center">General</div>

												<div class="row">&nbsp;</div>
												
											

												<div class="form-group">
													<label for="inputEmail3" class="col-sm-4 control-label">Name</label>

													<div class="col-sm-7">
														<form:input path="name" cssClass="form-control"
															 autocomplete="off"/>
														<form:errors path="name" />
													</div>
												</div>
												
												 <div class="form-group">
													<label for="inputEmail3" class="col-sm-4 control-label">Code</label>
													<div class="col-sm-7">
														<form:input path="partyCode" cssClass="form-control" />
														<form:errors path="partyCode" />
													</div>
												</div>
												

												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Ledger
														Group</label>

													<div class="col-sm-7">
														<form:select path="ledgerGroup.id"
															class="form-control" style="width: 100%;" required="true"
															>
															<form:option value="" label="Select LedgerGroup" />
															<form:options items="${ledgerGroupMap}" />
														</form:select>
													</div>


												</div>


												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Type</label>

													<div class="col-sm-7">
														<form:select path="type.id"
															class="form-control" style="width: 100%;" required="true">
															<form:option value="" label="Select Type" />
															<form:options items="${ledgerTypeMap}" />
														</form:select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="customerType" class="col-sm-4 control-label">Customer Type</label>

													<div class="col-sm-7">
														<form:select path="customerType.id"
															class="form-control" style="width: 100%;" >
															<form:option value="" label="Select Customer Type" />
															<form:options items="${customerTypeMap}" />
														</form:select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="partyRegion" class="col-sm-4 control-label">Region</label>

													<div class="col-sm-7">
														<form:select path="partyRegion.id"
															class="form-control" style="width: 100%;" >
															<form:option value="" label="Select Region" />
															<form:options items="${partyRegionMap}" />
														</form:select>
													</div>
												</div>

												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Contact
														Person</label>

													<div class="col-sm-7">
														<form:input path="contactPerson" cssClass="form-control" />
														<form:errors path="contactPerson" />
													</div>
												</div>

												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Address</label>

													<div class="col-sm-7">
														<form:textarea path="address" cssClass="form-control" />
														<form:errors path="address" />
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Transport Area</label>

													<div class="col-sm-7">
														<form:input path="area" cssClass="form-control"
															 />
														<form:errors path="area" />
													</div>
												</div>

												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Zone</label>

													<div class="col-sm-7">
														<form:input path="zone" cssClass="form-control"
															 />
														<form:errors path="zone" />
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">City</label>

													<div class="col-sm-7">
														<form:input path="city" cssClass="form-control"
															 />
														<form:errors path="city" />
													</div>
												</div>
												<div class="form-group">
													<label for="inputPassword3" class="col-sm-4 control-label">Zip</label>

													<div class="col-sm-7">
														<form:input path="zip" cssClass="form-control"
															 />
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
															 />
														<form:errors path="officePhone" />
													</div>
												</div>

												<div class="form-group">
													<label for="fax" class="col-sm-4 control-label">Fax</label>
													<div class="col-sm-7">
														<form:input path="fax" cssClass="form-control"
															 />
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
															 />
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

												<%-- <div class="form-group">
													<label for="salesMan" class="col-sm-4 control-label">Our Relationship Manager</label>
													<div class="col-sm-7">
														<form:input path="salesMan" cssClass="form-control"
															 />
														<form:errors path="salesMan" />
													</div>
												</div> --%>
												
												<div class="form-group">
													<label for="partyRegion" class="col-sm-4 control-label">Our Relationship Manager</label>

													<div class="col-sm-7">
														<form:select path="employee.id"
															class="form-control" style="width: 100%;" >
															<form:option value="" label="Select Salesman" />
															<form:options items="${salesmanMap}" />
														</form:select>
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
													<label for="discount" class="col-sm-4 control-label">Discount
														 </label>
													<div class="col-sm-7">
														<form:input type="number" path="discount"
															cssClass="form-control" />
														<form:errors path="discount" />
													</div>
												</div>
												
												<div class="form-group">
													<label for="discountPercent" class="col-sm-4 control-label">Discount Percent
														 </label>
													<div class="col-sm-7">
														<form:input type="number" path="discountPercent"
															cssClass="form-control" />
														<form:errors path="discountPercent" />
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
															 />
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
															 />
														<form:errors path="gst" />
													</div>
												</div>




												<div class="form-group">
													<label for="panNo" class="col-sm-4 control-label">PAN
													</label>
													<div class="col-sm-7">
														<form:input path="panNo" cssClass="form-control"
															 />
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
												
												<div class="form-group">
													<label for="transporter" class="col-sm-4 control-label">Parent Party
													</label>
													<div class="col-sm-7">
															<form:select path="parentParty"	class="form-control" style="width: 100%;">
															<form:option value="" label="Select Parent Party" />
															<form:options items="${parentPartyMap}" />
														</form:select>
														
													</div>
												</div>
												
												<div class="box-subtitle" style="font-weight: bolder;"
													align="center">Rate Apply Types</div>
												<div class="row">&nbsp;</div>
												
														<div class="form-group">
													<label for="diawt" class="col-sm-4 control-label">Dia. Wt. Type
													</label>
													<div class="col-sm-7">
															<form:select path="diaWtType"	class="form-control" style="width: 100%;">
															<form:option value="" label="Select Dia Wt Type" />
															<form:options items="${partyDiaWtType}" />
														</form:select>
														
													</div>
												</div>
												
												
															
														<div class="form-group">
													<label for="diaRate" class="col-sm-4 control-label">Dia. Rate Type
													</label>
													<div class="col-sm-7">
															<form:select path="diaRateType"	class="form-control" style="width: 100%;">
															<form:option value="" label="Select Dia Rate Type" />
															<form:options items="${partyDiaRateType}" />
														</form:select>
														
													</div>
												</div>
												
																		
														<div class="form-group">
													<label for="diaRate" class="col-sm-4 control-label">Handling Type
													</label>
													<div class="col-sm-7">
															<form:select path="hdlgRateType" class="form-control" style="width: 100%;">
															<form:option value="" label="Select Hdlg Rate Type" />
															<form:options items="${partyLabRateType}" />
														</form:select>
														
													</div>
												</div>
												
												
												<div class="form-group">
													<label for="diaRate" class="col-sm-4 control-label">Setting Rate Type
													</label>
													<div class="col-sm-7">
															<form:select path="setRateType"	class="form-control" style="width: 100%;">
															<form:option value="" label="Select Setting Rate Type" />
															<form:options items="${partyLabRateType}" />
														</form:select>
														
													</div>
												</div>
												
												
															
														<div class="form-group">
													<label for="labRate" class="col-sm-4 control-label">Labour Rate Type
													</label>
													<div class="col-sm-7">
															<form:select path="labRateType"	class="form-control" style="width: 100%;">
															<form:option value="" label="Select Lab Rate Type" />
															<form:options items="${partyLabRateType}" />
														</form:select>
														
													</div>
												</div>
												
												
												
														<div class="form-group">
													<label for="metalWtAddPerc" class="col-sm-4 control-label">Metal Wt. Add%
														 </label>
													<div class="col-sm-7">
														<form:input type="number" path="metalWtAddPerc"
															cssClass="form-control" />
														<form:errors path="metalWtAddPerc" />
													</div>
												</div>
												
																
														<div class="form-group">
													<label for="labRate" class="col-sm-4 control-label">Metal Wt Type
													</label>
													<div class="col-sm-7">
															<form:select path="metalWtType"	class="form-control" style="width: 100%;">
															<form:option value="" label="Select Metal Wt Type" />
															<form:options items="${partyDiaWtType}" />
														</form:select>
														
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
											
											</div>
						
																		
								<div class="row">
										<div class="col-xs-12">&nbsp;</div>
									</div>
											
											<div class="row">
				<div class="col-xs-12">
					<%-- <div class="col-xs-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<span style="font-size: 16px;">Billing Address</span>
								<form:hidden path="addressList[0].addressType" value="B" />
							</div>
							<div class="panel-body">
								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1">Address Line1 :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[0].address1"
											cssClass="form-control" />
										<form:errors path="addressList[0].address1" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">Address Line2 :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[0].address2"
											cssClass="form-control" />
										<form:errors path="addressList[0].address2" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">Location Code :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[0].locationCode"
											cssClass="form-control" />
										<form:errors path="addressList[0].locationCode" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">City :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[0].city" cssClass="form-control" />
										<form:errors path="addressList[0].city" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">State :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[0].state"
											cssClass="form-control" />
										<form:errors path="addressList[0].state" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">Country :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[0].country" cssClass="form-control" />
										<form:errors path="addressList[0].country" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">Zip :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[0].zip" cssClass="form-control" />
										<form:errors path="addressList[0].zip" />
									</div>
								</div>
							</div>
						</div>
					</div> --%>

					<!-- <div class="col-xs-6"> -->
						<div class="panel panel-primary">
							<div class="panel-heading">
								<span style="font-size: 16px;">Shipping Address</span>
								<form:hidden path="addressList[1].addressType" value="S" />
							</div>

							<div class="panel-body">
								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">Address Line1 :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[1].address1"
											cssClass="form-control" />
										<form:errors path="addressList[1].address1" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">Address Line2 :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[1].address2"
											cssClass="form-control" />
										<form:errors path="addressList[1].address2" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">Location Code :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[1].locationCode"
											cssClass="form-control" />
										<form:errors path="addressList[1].locationCode" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">City :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[1].city" cssClass="form-control" />
										<form:errors path="addressList[1].city" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">State :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[1].state"
											cssClass="form-control" />
										<form:errors path="addressList[1].state" />
									</div>
								</div>

									<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">Country :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[1].country" cssClass="form-control" />
										<form:errors path="addressList[1].country" />
									</div>
								</div>

								<div class="form-group">
									<div class="col-xs-12">
										<label for="address1" class="col-sm-6">Zip :</label>
									</div>
									<div class="col-xs-12">
										<form:input path="addressList[1].zip" cssClass="form-control" />
										<form:errors path="addressList[1].zip" />
									</div>
								</div>
							</div>
						</div>
					<!-- </div> -->
				</div>
			</div>
											
											
			
						<c:if test="${canAdd || canEdit}">
							<div class="row">
								<div class="col-sm-offset-5 col-sm-7">
									<input type="submit" value="Save" class="btn btn-primary" /> <a
										class="btn btn-info" style="font-size: 15px" type="button"
										href="/jewels/manufacturing/masters/party.html">Party Listing</a>
									<form:input type="hidden" path="id" />
									<form:input type="hidden" path="addressList[0].id" />
									<form:input type="hidden" path="addressList[1].id" />
								</div>
							</div>
						</c:if>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function(e) {

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
						
						$(".partyForm")
								.validate(
										{
											rules : {
												name : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/masters/partyAvailable.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												},
												partyCode : {
													required : true,
													remote : {
														url : "<spring:url value='/manufacturing/masters/partyCodeAvailable.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
												},
												
												
												defaultFlag : {
													required : false,
													remote : {
														url : "<spring:url value='/manufacturing/masters/defaultPartyAvailable.html' />",
														type : "get",
														data : {
															id : function() {
																return $("#id")
																		.val();
															}
														}
													}
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
													remote : "Party already exists"
												},
												partyCode : {
													remote : "Party Code already exists"
												},
													
											defaultFlag : {
												remote : "Only one party can be default at a time"
											}
											
											
											}
										});

						$("input:text:visible:first").focus();
					});



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

	 function popParentParty(id){
		 
		 $
			.ajax({
				url : "/jewels/manufacturing/masters/parentParty/list.html?groupId="
						+ id,
				type : 'GET',
				success : function(data) {
					$("#parentParty").html(data);
					$("#parentParty").trigger("chosen:updated");
				}
			});
	 }



	 
</script>

<script src="/jewels/js/common/common.js"></script>
