<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


	<div class="panel body">
	
				

		<c:if test="${success eq true}">
			<div class="col-xs-12">
				<div class="alert alert-success"> ValueAddition ${action} successfully!</div>
			</div>
		</c:if>
			
				
	
		<div class="form-group">
			<form:form commandName="costingMt" 
				action="/jewels/manufacturing/transactions/vaddMt/add.html"
				cssClass="form-horizontal vaddMtForm">

				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Inv
								No :</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Inv
								Date :</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Party
								:</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Buyer
								:</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Bank
							 :</label>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-2 col-sm-2">
							<form:input path="invNo" disabled ="true"  cssClass="form-control" />
							<form:errors path="invNo" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="invDate" disabled ="true" cssClass="form-control" />
							<form:errors path="invDate" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:select path="party.id" disabled ="true" class="form-control">
								<form:option value="" label=" Select Party " />
								<form:options items="${partyMap}" />
							</form:select>
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:select path="buyer.id"  class="form-control">
								<form:option value="" label=" Select Buyer " />
								<form:options items="${partyMapBuyer}" />
							</form:select>
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:select path="bank"  class="form-control">
								<form:option value="" label=" Select Bank " />
								<form:options items="${bankMap}" />
							</form:select>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Sb
								No :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">E.D.F No:</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Terms of payment :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">MAWB :</label>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<form:input path="sbNo" cssClass="form-control" />
							<form:errors path="sbNo" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="grNo" cssClass="form-control" />
							<form:errors path="grNo" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:select path="payTerm"  class="form-control">
								<form:option value="" label=" Select Terms Of Payment " />
								<form:options items="${payTermMap}" />
							</form:select>
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="mawb" cssClass="form-control" />
							<form:errors path="mawb" />
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Pre - Carriage :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Port Of Discharge :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">HAWB :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Vessel/Flight No :</label>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<form:input path="preCarriage" cssClass="form-control" />
							<form:errors path="preCarriage" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="portOfDischarge" cssClass="form-control" />
							<form:errors path="portOfDischarge" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="hawb" cssClass="form-control" />
							<form:errors path="hawb" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="flightNo" cssClass="form-control" />
							<form:errors path="flightNo" />
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Port Of Loading :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Final Destination :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">I.T.C Code :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Country Of Origin Of Goods :</label>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<form:input path="portOfLoading" cssClass="form-control" />
							<form:errors path="portOfLoading" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="finalDestination" cssClass="form-control" />
							<form:errors path="finalDestination" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="itcCode" cssClass="form-control" value="${vItc}" />
							<form:errors path="itcCode" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="countryOfGoods" cssClass="form-control" value="${cog} " />
							<form:errors path="countryOfGoods" />
						</div>
					</div>
				</div>
				
				
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Country Of FinalDestination :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Freight :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Description :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Insurance Amount :</label>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<form:input path="countryOfFinalDestination" cssClass="form-control" />
							<form:errors path="countryOfFinalDestination" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="frieght" cssClass="form-control" />
							<form:errors path="frieght" />
						</div>
						<div class="col-lg-3 col-sm-3">	
							<form:select path="description"  class="form-control">
								<form:option value="" label=" Select Description " />
								<form:options items="${descriptionMap}" />
							</form:select>		
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="insuranceAmount" cssClass="form-control" />
							<form:errors path="insuranceAmount" />
						</div>
					</div>
				</div>
				
				
				
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">CIF / CFR :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Gold Loan :</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<label for="inputLabel3" class=".col-lg-2 text-right">Repair Remark:</label>
						</div>
						
					</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<form:input path="cif" cssClass="form-control" />
							<form:errors path="cif" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="loanGold" cssClass="form-control" />
							<form:errors path="loanGold" />
						</div>
						<div class="col-lg-6 col-sm-6">
							<form:input path="repairRemark" cssClass="form-control" />
							<form:errors path="repairRemark" />
						</div>
					</div>
				</div>
				
				
				
			
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-6 col-sm-6">
							<label for="inputLabel3" class=".col-lg-2 text-right">Insurance:</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<label for="inputLabel3" class=".col-lg-2 text-right">Auth. Signatory :</label>
						</div>
						
					</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-6 col-sm-6">
							<form:input path="insuredBy" cssClass="form-control" />
							<form:errors path="insuredBy" />
						</div>
						<div class="col-lg-6 col-sm-6">
							<form:input path="authSign" cssClass="form-control" />
							<form:errors path="authSign" />
						</div>
					</div>
				</div>
				
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-6 col-sm-6">
							<label for="inputLabel3" class=".col-lg-2 text-right">Remark:</label>
						</div>
						<div class="col-lg-6 col-sm-6">
							<label for="inputLabel3" class=".col-lg-2 text-right">Gold Details :</label>
						</div>
						
					</div>
				</div>


				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-6 col-sm-6">
							<form:input path="remark" cssClass="form-control" />
							<form:errors path="remark" />
						</div>
						<div class="col-lg-6 col-sm-6">
							<form:input path="otherRemark" cssClass="form-control" />
							<form:errors path="otherRemark" />
						</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				<div class="row">
				
				<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Exchange Rate :</label>
						</div>
							<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Direct Parcel :</label>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Disc :</label>
						</div>
						
						</div>
				</div>

		<div class="row">
				<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<form:input path="exchangeRate" cssClass="form-control" />
							<form:errors path="exchangeRate" />
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:checkbox path="directParcel"/>
							<form:errors path="directParcel" />
						</div>
						<div class="col-lg-3 col-sm-3">
							<form:input path="disc" cssClass="form-control" />
							<form:errors path="disc" />
						</div>
				
				<div class="col-lg-2 col-sm-2">
								<label> <form:checkbox path="vaddIn999"/> <b>As Per
										999</b></label>
							</div>
				</div>
			</div> 




			<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
			
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-5">
						<input type="submit" value="Save" class="btn btn-primary" /> <a
							class="btn btn-info" style="font-size: 15px" type="button"
							href="/jewels/manufacturing/transactions/vaddMt.html">ValueAddition Listing</a>
						<form:input type="hidden" path="id" />
						<form:input type="hidden" path="uniqueId" />
						
						
					</div>
				</div>

			</form:form>
		</div>
		
		
	</div>	<!-- ending the panel body -->
	
	
	
	
<script type="text/javascript">

$(document).ready(
		function(e){

			$("#"+ document.querySelector("#disableValueAdditionDetail").id).attr("id", "valueAddition");
			$("#"+ document.querySelector("#disableStockAdjustment").id).attr("id", "stockAdjustments");
			
			
		});

	
	
	
	
	
</script>
	
	
	
	
	
	