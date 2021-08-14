<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel panel-primary" style="width: 100%">

<div class="panel-heading" style="text-align: center;">
		
	
	
	<div>
			<label class="col-lg-2 col-sm-2 text-left"> <span
				style="font-size: 18px;">Melting</span>
			</label>
			
			<div class="text-right">
			<button style="font-size: 14px; width: 3cm" class="btn btn-xs btn-success"  onClick="javascript:meltingStnRecReport();">
			 <span class="fa fa-file-pdf"></span>Preview</button>
			
			
			</div>
			
			
		</div>	
	
	</div>

	<div class="form-group">
		<form:form commandName="meltingMt" id="meltMtForm"
			action="/jewels/manufacturing/transactions/meltingMt/add.html"
			cssClass="form-horizontal meltingMtForm">

			<c:if test="${success eq true}">
				<div class="alert alert-success">Melting ${action}
					successfully!</div>
			</c:if>

			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
					<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">Inv
							No :</label>
					</div>
					<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">Inv
							Date :</label>
					</div>
					<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">Remark
							:</label>
					</div>
					<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">Loss In Pure
							:</label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div class="col-sm-2">
						<form:input path="invNo" cssClass="form-control" disabled="true"/>
						<form:errors path="invNo" />
					</div>
					<div class="col-sm-2">
						<form:input path="invDate" cssClass="form-control" autocomplete="off" />
						<form:errors path="invDate" />
					</div>
					<div class="col-sm-2">
						<form:input path="remark" cssClass="form-control" autocomplete="off" />
						<form:errors path="remark" />
					</div>
					<div class="col-sm-2">
						<form:input path="loss"  cssClass="form-control" onchange="javascript:totalLoss()" />
						<form:errors path="loss" />
					</div>
					<div class="col-sm-4">
						<input type="submit" value="Save" class="btn btn-primary" id="meltingSubmitBtn" /> 
						<a class="btn btn-info" style="font-size: 15px" type="button"
							href="/jewels/manufacturing/transactions/meltingMt.html">Listing</a>
						<form:input type="hidden" path="id" id="meltingMtId"/>
						<form:input type="hidden" path="uniqueId" />
						<form:input type="hidden" path="srNo" />
						<form:input type="hidden" path="invNo" />
						<input type="hidden" id="metLoss" name="metLoss" />
					</div>
				</div>
			</div>
		</form:form>
		
		
		<!-- Download Common pdf Report -->
		
			<div style="display: none">		
				<form:form target="_blank"  id="styleNotMatchForm"
					action="/jewels/manufacturing/masters/reports/download/report.html"
					cssClass="form-horizontal orderFilter">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Report" class="btn btn-primary" id="generateDataRpt"/>
								<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
							</div>
						</div>
				</form:form>
			</div>
		
		
		
	</div>


	<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>
	
	
	<div id="mAllDetails" style="display: none"> <!-- hide1 start -->
	
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-sm-12 label label-default text-right"
					style="font-size: 18px;">Issue</span>
			</div>
		</div>
	</div>	
	

	
	<div class="form-group">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				
				<div>
					<table id="meltingIssueDtId" data-toggle="table"
						data-toolbar="#toolbar" data-search="true" 						
						data-height="350">
						<thead>
							<tr>
								 <th data-field="location" data-align="left">Location</th>
								<th data-field="barcode" data-align="left">Barcode</th>
								<th data-field="purity" data-align="left">Purity</th>
								<th data-field="color" data-sortable="true">Color</th>
								<th data-field="freshMetalWt" data-sortable="true">Metal Wt</th>
								<th data-field="stones" data-sortable="true">Stone</th>
								<th data-field="carat" data-sortable="true">Carat</th>
								<th data-field="netWt" data-sortable="true">Net Wt</th>
								<th data-field="expcPureWt" data-sortable="true">ExpcPure Wt</th>
								<th data-field="bagNm" data-sortable="true">Bag No.</th>
								<th data-field="styleNo" data-sortable="true">Style No.</th>
								<th data-field="remark" data-sortable="true">Remark</th>
								<th data-field="action1" data-align="center">Edit</th>
								<th data-field="action2" data-align="center">Delete</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	
		


			<table class="line-items editable table">
				<tr class="panel-heading">
					<th>
						
						<span style="display:inline-block; width: 0.1cm;"></span>
							<a class="btn btn-info" style="font-size: 15px" type="button" href="javascript:goToIssueEntry();">
								<span class="glyphicon glyphicon-plus"></span>&nbsp;Add
							</a>
							
							<a class="btn btn-warning" style="font-size: 15px" type="button"
							 href="javascript:goToRejPickUp();">Melting Pickup</a>
							 
							 <a class="btn btn-warning" style="font-size: 15px" type="button"
							 href="javascript:goToStockPickUp();">Stock Pickup</a>
					
					<span style="display:inline-block; width: 3cm;"></span>
					
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />			
						&nbsp;&nbsp;								
					Total Net Wt : <input type="text" id="vTotalNetWts" name="vTotalNetWts" value="${totalNetWts}"  
									maxlength="7" size="7" disabled="disabled" />
						&nbsp;&nbsp;								
					Total ExpcPure Wt : <input type="text" id="vTotalExpcPureWts" name="vTotalExpcPureWts" value="${totalExpcPureWts}"
							maxlength="7" size="7" disabled="disabled" />
						</th>
						
				</tr>
			</table>
			
			
			
	<div id="mIssueEntry" style="display: none"> <!-- hide2 start -->				
	
	<div id="meltIssDtId">
		<form:form commandName="meltingIssDt" id="meltingIssDt"
			action="/jewels/manufacturing/transactions/meltingIssDt/add.html"
			cssClass="form-horizontal meltingIssDtForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
					<tr class="btn-warning" class="panel-heading">
						<th>Location</th>
						<th>Purity</th>
						<th>Color</th>
						<th>Metal Wt</th>
						<th>Stone</th>
						<th>Carat</th>
						<th>Net Wt</th>
						<th>ExcpPure Wt</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					
						<td><form:select path="deptLocation.id" class="form-control"  required="true">
												<form:option value="" label="- Select Location -" />
												<form:options items="${locationMetalMap}" />
											</form:select></td>
						
						<td>
							<form:select path="purity.id" id="issPurity" class="form-control"
								onChange="javascript:getPurityConversion();refreshData()">
								<form:option value="" label="- Select Purity -" />
								<form:options items="${purityMap}" />
							</form:select>
						</td>
						<td>
							<form:select path="color.id" id="issColor" class="form-control">
								<form:option value="" label="- Select Color -" />
								<form:options items="${colorMap}" />
							</form:select>
						</td>
						<td><form:input path="issFreshMetalWt"  cssClass="form-control" onblur="javascript:getNetWt();" /></td>
						<%-- <td><form:input path="issUsedMetalWt"   cssClass="form-control" onblur="javascript:popValidation();getNetWt();" /></td> --%>
						<td><form:input type="number" path="stone" id="issStone"  cssClass="form-control" min="0" pattern="[0-9]"
							onkeypress="return !(event.charCode == 46)" step="1"
							title="Must be an integer number"/></td>
						
						<!-- <input
							type="number" id="splitQty" name="splitQty" class="form-control"
							min="0" pattern="[0-9]"
							onkeypress="return !(event.charCode == 46)" step="1"
							title="Must be an integer number"> -->
						
						
						
						<td><form:input path="carat" id="issCarat" cssClass="form-control" onblur="javascript:getNetWt()"/></td>
						<td><form:input path="netWt" cssClass="form-control" disabled="true" /></td>
						<td><form:input path="excpPureWt" cssClass="form-control" disabled="true" /></td>
					</tr>
				<%-- 	<tr>
						<td colspan="10">
						<input type="submit" value="Save" class="btn btn-primary" id="issueSubmitBtnId" />
						<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMelIssCancelBtn()"> 
							<form:input type="hidden" path="id" id="issDtPk" />
							<form:input type="hidden" path="uniqueId" />
							<input type="hidden" id="pInvNo" name="pInvNo" />
							<input type="hidden" id="purityConversion" name="purityConversion" />
							<input type="hidden" id="prevNetWt" name="prevNetWt" />	
							<input type="hidden" id="prevKt" name="prevKt" />
							<input type="hidden" id="prevColor" name="prevColor" />
							<input type="hidden" id="currNetWt" name="currNetWt" /> 
							<input type="hidden" id="pExcpPureWt" name="pExcpPureWt" /> 
							<input type="hidden" id="pFMetalWt" name="pFMetalWt" />
								
						</td>
					</tr> --%>
				</tbody>
				
					<thead class="panel-heading">
					<tr class="btn-warning" class="panel-heading">
						<th>Remarks</th>
						
					</tr>
				</thead>
				
					<tbody>
					<tr>
						
						<td><form:input path="dtRemark"  cssClass="form-control"/></td>
					
					</tr>
					<tr>
						<td colspan="10">
						<input type="submit" value="Save" class="btn btn-primary" id="issueSubmitBtnId" />
						<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMelIssCancelBtn()"> 
							<form:input type="hidden" path="id" id="issDtPk" />
							<form:input type="hidden" path="uniqueId" />
							<input type="hidden" id="pInvNo" name="pInvNo" />
							<input type="hidden" id="purityConversion" name="purityConversion" />
							<input type="hidden" id="prevNetWt" name="prevNetWt" />	
							<input type="hidden" id="prevKt" name="prevKt" />
							<input type="hidden" id="prevColor" name="prevColor" />
							<input type="hidden" id="currNetWt" name="currNetWt" /> 
							<input type="hidden" id="pExcpPureWt" name="pExcpPureWt" /> 
							<input type="hidden" id="pFMetalWt" name="pFMetalWt" />
								
						</td>
					</tr>
				</tbody>
				
			</table>
		</form:form>
	</div>
</div>

</div>  <!-- hide2 end -->


	<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>


 <!-- <div id="mReceiveDetails" style="display: none"> --> <!-- hide3 start -->
	
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-lg-12 label label-default text-right"
					style="font-size: 18px;">Receive</span>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>
	
	<div class="form-group">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div>
					<table id="meltingReceiveDtId" data-toggle="table"
						data-toolbar="#toolbarRC" data-pagination="false"
						data-side-pagination="server"
						data-page-list="[5, 10, 20, 50, 100, 200]" 
						data-height="350">
						<thead>
							<tr>
							 	<th data-field="location" data-align="left">Location</th>
								<th data-field="purity" data-align="left">Purity</th>
								<th data-field="color" data-sortable="true">Color</th>
								<th data-field="freshMetalWt" data-sortable="true">Metal Wt</th>
								<th data-field="stone" data-sortable="true">Stone</th>
								<th data-field="carat" data-sortable="true">Carat</th>
								<th data-field="netWt" data-sortable="true">Net Wt</th>
								<th data-field="expcPureWt" data-sortable="true">Pure Wt</th>
								<th data-field="action1" data-align="center">Edit</th>
								<th data-field="action2" data-align="center">Delete</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	


	
	
	<table class="line-items editable table">
				<tr class="panel-heading">
					<th>
						
						<span style="display:inline-block; width: 0.3cm;"></span>
						<a class="btn btn-info" style="font-size: 15px" type="button"
							href="javascript:goTomReceiveEntry();">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;Add
					</a>
					
					<span style="display:inline-block; width: 10cm;"></span>
					
					Total Stone : <input type="text" id="vRecTotalStones" name="vRecTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vRecTotalCarats" name="vRecTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />			
						&nbsp;&nbsp;								
					Total Net Wt : <input type="text" id="vRecTotalNetWts" name="vRecTotalNetWts" value="${totalNetWts}"  
									maxlength="7" size="7" disabled="disabled" />
						&nbsp;&nbsp;								
					Total Pure Wt : <input type="text" id="vRecTotalExpcPureWts" name="vRecTotalExpcPureWts" value="${totalExpcPureWts}"
							maxlength="7" size="7" disabled="disabled" />
						</th>
						
				</tr>
			</table>
	
	

	
 <div id="mReceiveEntry" style="display: none"> <!-- hide4 start -->
 
	<div id="meltRecDtId">
		<div class="form-group">
			<div class="form-group">
				<form:form commandName="meltingRecDt" id="meltingRecDt"
					action="/jewels/manufacturing/transactions/meltingRecDt/add.html"
					cssClass="form-horizontal meltingRecDtForm">

					<table class="line-items editable table table-bordered">
						<thead class="panel-heading">
							<tr class="btn-warning" class="panel-heading">
								<th>Location</th>
								<th>Purity</th>
								<th>Color</th>
								<th>Metal Wt</th>
								<th>Stone</th>
								<th>Carat</th>
								<th>Net Wt</th>
								<th>Pure Wt</th>
							</tr>
						</thead>
						<tbody>
							<tr>
						
							<td><form:select path="deptLocation.id" class="form-control"  required="true">
												<form:option value="" label="- Select Location -" />
												<form:options items="${locationMetalMap}" />
											</form:select></td>
								
								 <td>
									<div id="purityId">
										<form:select path="purity.id" id="recPurity" class="form-control" onChange="javascript:getPurityRecConversion();refreshRecData()">
											<form:option value="" label="- Select Purity -" />
											<form:options items="${purityMap}" />
										</form:select>
									</div>
								</td> 
								<td>
									<div id="colorId">
										<form:select path="color.id" id="recColor" class="form-control">
											<form:option value="" label="- Select Color -" />
											<form:options items="${colorMap}" />
										</form:select>
									</div>
								</td>
								<td><form:input path="recFreshMetalWt" cssClass="form-control" onblur="javascript:getRecNetWt();" /></td>
								<td><form:input type="number" path="recStone" id="recStone"  cssClass="form-control" min="0" pattern="[0-9]"
							onkeypress="return !(event.charCode == 46)" step="1"
							title="Must be an integer number"/></td>
								<td><form:input path="recCarat"  cssClass="form-control" onblur="javascript:getRecNetWt()" /></td>
								<td><form:input path="recNetWt" cssClass="form-control" disabled="true" /></td>
								<td><form:input path="recExcpPureWt" cssClass="form-control" disabled="true" /></td>
							</tr>
							<tr>
								<td colspan="10">
									<input type="submit" value="Save" class="btn btn-primary" id="receiveSubmitBtnId" />
									<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMelRecCancelBtn()"> 
									<form:input type="hidden" path="id" id="recDtUniPk" />
									<form:input type="hidden" path="uniqueId" />
									<input type="hidden" id="recInvNo" name="recInvNo" />
									<input type="hidden" id="recPNetWt" name="recPNetWt" />
									<input type="hidden" id="recPExcpPureWt" name="recPExcpPureWt" />
									<input type="hidden" id="recPurityConversion" name="recPurityConversion" />
									<input type="hidden" id="pRecFMetalWt" name="pRecFMetalWt" />
									<!-- <input type="hidden" id="pRecUMetalWt" name="pRecUMetalWt" /> -->	
									<input type="hidden" id="vLoss" name="vLoss" />
									<input type="hidden" id="pvRecTotalExpcPureWts" name="pvRecTotalExpcPureWts" />
									<input type="hidden" id="pMeltingBal" name="pMeltingBal" />
									
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>
<!-- </div> -->
</div>
</div> <!--  hide4 End -->


<!--  </div>  -->  <!-- hide3 End -->

</div> <!-- hide1 End -->
</div>  <!-- ending the main panel -->


<script type="text/javascript">
var canViewFlag = false;
	$(document).ready(
			function(e) {
				
				$(".meltingMtForm").validate(
						{
							rules : {
								invDate : {
									required : true,
								},

							},
							
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							},
							
						});
				
				
				
				
				
				$(".meltingIssDtForm").validate(
						{
							rules : {
								
								
								'purity.id' : {
									required : true,
								},
								'color.id' : {
									required : true,
								},
								'deptLocation.id' : {
									required : true,
								},
								
								issFreshMetalWt : {
									required : true,
								},

							},
							
							
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							},
							
							
							
							
							
						});
				
				
				
				
				
				$(".meltingRecDtForm").validate(
						{
							rules : {
								
								
								'purity.id' : {
									required : true,
								},
								'color.id' : {
									required : true,
								},

								'deptLocation.id' : {
									required : true,
								},
								
								recFreshMetalWt : {
									required : true,
								},
								
								

							},
							
							
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							},
							
							
							
							
							
						});
				
				

				$("#invDate").datepicker({
					dateFormat : 'dd/mm/yy'
				});
				
				if (window.location.href.indexOf('edit') != -1) {
					$("#mAllDetails").css('display', 'block');
				}
				if (window.location.href.indexOf('view') != -1) {
					
					canViewFlag=true;
					$("#mAllDetails").css('display', 'block');
					
					$('#invDate').attr('disabled', true);
					$('#remark').attr('disabled', true);
					$('#meltingSubmitBtn').hide();
					
				//	$('#meltingMtDivId').find('input, textarea, button, select').attr('disabled','disabled');
					$('#mAllDetails :input').attr('disabled',true);
					$('#mAllDetails .btn').hide();
					$('#mAllDetails .btn').removeAttr('onclick');
					$('#mAllDetails .btn').removeAttr('href');
					
					
					
					
				}

				popMeltingIssDt();	
				popMeltingRecDt();
				totalStnCarat();
				totalRecStnCarat();

			});
	
	
	
	
	
	$(document)
	 .on(
		'submit',
		'form#meltMtForm',
		 function(e){
			 $("#meltingSubmitBtn").prop("disabled", true).addClass("disabled");
			
		});
	
	

	function popMeltingIssDt() {

		$("#meltingIssueDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/meltingIssDt/listing.html?pInvNo="
									+ $('#invNo').val()+"&canViewFlag="+canViewFlag,
						});
		
		}

	
	$(document)
			.on(
					'submit',
					'form#meltingIssDt',
					function(e) {
						
						$('#issueSubmitBtnId').attr('disabled', 'disabled');
						
						$("#pInvNo").val($("#invNo").val());
						$("#currNetWt").val($("#netWt").val());
						$("#pExcpPureWt").val($("#excpPureWt").val());
						$("#pFMetalWt").val($("#issFreshMetalWt").val());
						/* $("#pUMetalWt").val($("#issUsedMetalWt").val()); */
						
					
						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {
										
										
		
										if (data == "-1") {
											displayMsg(event, this,'Metal Stock Insufficient');
										}else if(data == "-2"){
											displayMsg(event, this,'UsedMetal Stock Insufficient');
										}else if(data == "-3"){
											displayMsg(event, this,'MetalWt or UsedMetalWt is compulsary');
										}else if(data == "-4"){
											displayMsg(event, this,'Only One MetalWt can be entered at time (Either Fresh Or Used)!');
										}else {
											popMeltingIssDt();
											$('form#meltingIssDt input[type="text"],texatrea, select').val('');
											
											$("#issFreshMetalWt").val('0.0');
											/* $("#issUsedMetalWt").val('0.0'); */
											$("#issStone").val('0');
											$("#issCarat").val('0.0');
											$("#netWt").val('0.0');
											$("#excpPureWt").val('0.0');
											$('#issPurity').focus();
											
											if(data == "2"){
												$('#mIssueEntry').css('display','none');
											}
											
											totalStnCarat();
											
											
											
											//editMetalMt();
											
										}
										
										$('#issueSubmitBtnId').removeAttr('disabled');
										
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
										
									}
								});
						e.preventDefault(); //STOP default action
					});
	

	
	function editMeltingIssDt(issDtId,bagId) {
		
		if(bagId>0){
			
			displayMsg(this, null, 'Can Not Edit Pickup Entry !!!');
			
			
		}else{
			$.ajax({
				url : "/jewels/manufacturing/transactions/meltingIssDt/edit/" + issDtId
						+ ".html",
				type : 'GET',
				success : function(data) {				
					$("#meltIssDtId").html(data);

					var prNetWt = $('#netWt').val();
					$('#prevNetWt').val(prNetWt); 
					
					var tempKt = $('#issPurity').val();
					$('#prevKt').val(tempKt); 
					
					
					var tempColor = $('#issColor').val();
					$('#prevColor').val(tempColor); 

					
					
					 getPurityConversion();	
					$('#mIssueEntry').css('display','block');
					$('#issPurity').focus();

				}
			});
		}
		
		
	
	}
	
	
	
	function totalStnCarat(){	
	
	$('#meltingIssueDtId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#meltingIssueDtId").bootstrapTable('getData'));

				//var vFreshMetal = 0.0;
				//var vUsedMetal = 0.0;
				//var vTotMetal = 0.0;
				var vStones = 0.0;
				var vCarat = 0.0;
				var vNetWt = 0.0;
				var vExpcPureWt = 0.0;
				
				
				$.each(JSON.parse(data), function(idx, obj) {
					
					//vFreshMetal +=  Number(obj.freshMetalWt);
					//vUsedMetal 	+= Number(obj.usedMetalWt);
					//vTotMetal 	+= Number(obj.totMetalWt);
					vStones		+= Number(obj.stones);
					vCarat		+= Number(obj.carat);
					vNetWt 		+= Number(obj.netWt);  
					vExpcPureWt += Number(obj.expcPureWt);
					
				});
				
				//$('#vTotalFreshMetals').val(" " + parseFloat(vFreshMetal).toFixed(3));
				//$('#vTotalUsedMetals').val(" " + parseFloat(vUsedMetal).toFixed(3));
				//$('#vTotalMetals').val(" " + parseFloat(vTotMetal).toFixed(3));
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
				$('#vTotalNetWts').val(" "+ parseFloat(vNetWt).toFixed(3));
				$('#vTotalExpcPureWts').val(" "+ parseFloat(vExpcPureWt).toFixed(3));
				//totalLoss();
			});
		
			
	
	}
	
	
	function getPurityConversion(){
		
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/pureId/purityConversion.html' />?PurityId='+ $('#issPurity').val(),
					type : 'GET',
					success : function(data) {
						$('#purityConversion').val(data);
					}

				});
		
	
		
		
	}
	
	
	
	
	function getNetWt(){
		var vCaratTemp = $('#issCarat').val();		
		var tempValue = vCaratTemp/5;
		var tempNetWt =  (parseFloat($('#issFreshMetalWt').val())) - (tempValue);
		$('#netWt').val(tempNetWt.toFixed(3));		
		getExpcPureWt();
	}
	
	
	function getExpcPureWt(){
		var vNetWtTemp = $('#netWt').val();	
		var vPurityConversion = $('#purityConversion').val();	
		if(vPurityConversion === "" || vPurityConversion === null){
			vPurityConversion = 0.0;
		}
		var vExpcPureWt = vNetWtTemp * vPurityConversion;
		$('#excpPureWt').val(vExpcPureWt.toFixed(3));		
	}
	
	
	
	
	function goToIssueEntry(){
		$("#mIssueEntry").css('display','block'); 
		$('#issPurity').focus();
		$('form#meltingIssDt input[type="text"],texatrea, select').val('');
		$('form#meltingIssDt select').val('');
		$("#issFreshMetalWt").val('0.0');
		$("#issStone").val('0');
		$("#issCarat").val('0.0');
		$("#netWt").val('0.0');
		$("#excpPureWt").val('0.0');
		$("#issDtPk").val('');
		
	}
	
	
	function goToRejPickUp(){
		
		if (! $('#meltingMtId').val()) {
			displayMsg(this, null, 'Contact Admin !!!');
		} else {
			window.location.href = "/jewels/manufacturing/transactions/meltingPickUp/add.html?meltingMtId="+$('#meltingMtId').val();
		}
		
	}

	function goToStockPickUp(){

		if (! $('#meltingMtId').val()) {
			displayMsg(this, null, 'Contact Admin !!!');
		} else {
			window.location.href = "/jewels/manufacturing/transactions/stockMeltingPickup/add.html?mtid="+$('#meltingMtId').val();
		}
		}
	
	
	
	$('#meltingIssueDtId').bootstrapTable({})
	.on('click-row.bs.table',
			function(e, row, $element) {
				$('#mIssueEntry').css('display','none');
			});
	
	
	
/* 	function roundUpStone(){
		var tempStone = $('#issStone').val();
		$('#issStone').val(parseFloat(tempStone).toFixed(0));
	} */
	
	
/* 	function popValidation(){
		
		if($("#issFreshMetalWt").val() > 0 && $("#issUsedMetalWt").val() > 0){
			displayMsg(event, this ,"Only One MetalWt can be entered at time (Either Fresh Or Used)!");	
			$("#issFreshMetalWt").val('0.0');
			$("#issUsedMetalWt").val('0.0');
		}
	} */
	
	
	function popMelIssCancelBtn(){
		$('#mIssueEntry').css('display','none');
	}

	

	
	/* ---------------MeltingRecDt functions---------------- */
	
		
	
	function popMeltingRecDt() {

		$("#meltingReceiveDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/meltingRecDt/listing.html?recInvNo="
									+ $('#invNo').val()+"&canViewFlag="+canViewFlag,
						});
		
			
		}
	
	

	$(document)
			.on(
					'submit',
					'form#meltingRecDt',
					function(e) {
						
						
						
						$('#receiveSubmitBtnId').attr('disabled', 'disabled');
											
						$("#recInvNo").val($("#invNo").val());
						$("#recPNetWt").val($("#recNetWt").val());
						$("#recPExcpPureWt").val($("#recExcpPureWt").val());
						$("#pRecFMetalWt").val($("#recFreshMetalWt").val());
						$("#vLoss").val($("#loss").val());
						$("#pvRecTotalExpcPureWts").val($('#vRecTotalExpcPureWts').val());
						
						var totalDiff=($('#vTotalExpcPureWts').val() - $('#vRecTotalExpcPureWts').val()).toFixed(3);
						
						$('#pMeltingBal').val(Number(totalDiff));
						
						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {

										if (data == "-3") {
											displayMsg(event, this,'FreshMetalWt or UsedMetalWt is compulsary');
										}else if(data == "-4"){
											displayMsg(event, this ,"Only One MetalWt can be entered at time (Either Fresh Or Used)!");
										}else if(data == "-5"){
											displayMsg(this, null, 'Please check issue-receive entries')
										}else {
											popMeltingRecDt();
											totalRecStnCarat();
											$('form#meltingRecDt input[type="text"],texatrea, select').val('');
											$("#recFreshMetalWt").val('0.0');
											$("#recStone").val('0');
											$("#recCarat").val('0.0');
											$("#recNetWt").val('0.0');
											$("#recExcpPureWt").val('0.0');
											$('#recPurity').focus();
											
											if(data == "2"){
												$('#mReceiveEntry').css('display','none');
											}
											
											//editMetalMt();
											
										}
										
										$('#receiveSubmitBtnId').removeAttr('disabled');	
										
										
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
									}
								});
						e.preventDefault(); //STOP default action
					});
	
	
	
	
	function getPurityRecConversion(){
		$
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/rec/purityConversion.html' />?PurityId='+ $('#recPurity').val(),
			type : 'GET',
			success : function(data) {
				$('#recPurityConversion').val(data);
			}

		});
	}
	
	
	function getRecNetWt(){
		/* var vxCaratTemp = $('#recCarat').val();		
		var vtempValue = vxCaratTemp/5;
		var vtempNetWt =  (parseFloat($('#recFreshMetalWt').val())) - (vtempValue); */
		
		var vtempNetWt =  (parseFloat($('#recFreshMetalWt').val()));
		$('#recNetWt').val(vtempNetWt.toFixed(3));		
		getRecExpcPureWt();
	}
	
	
	function getRecExpcPureWt(){
		var vxNetWtTemp = $('#recNetWt').val();	
		var vxPurityConversion = $('#recPurityConversion').val();	
		if(vxPurityConversion === "" || vxPurityConversion === null){
			vxPurityConversion = 0.0;
		}
		var vxExpcPureWt = vxNetWtTemp * vxPurityConversion;
		$('#recExcpPureWt').val(vxExpcPureWt.toFixed(3));		
	}
	
	

	function editMeltingRecDt(recDtId) {
		$.ajax({
			url : "/jewels/manufacturing/transactions/meltingRecDt/edit/" + recDtId
					+ ".html",
			type : 'GET',
			success : function(data) {
				$("#meltRecDtId").html(data);
				getPurityRecConversion();
				$('#mReceiveEntry').css('display','block');
				$('#recPurity').focus();

			}
		});
	}
	
	
	
	 function goTomReceiveEntry(){			
		$
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/checkingIssEntry/forMeltingMt.html' />?MeltingId='+ $('#meltingMtId').val(),
			type : 'GET',
			success : function(data) {
				
					if(data == 1){
						$("#mReceiveEntry").css('display','block');
						$('form#meltingRecDt input[type="text"],texatrea, select').val('');
						$("#recFreshMetalWt").val('0.0');
						$("#recStone").val(0);
						$("#recCarat").val('0.0');
						$("#recNetWt").val('0.0');
						$("#recExcpPureWt").val('0.0');
						$("#recDtUniPk").val('');
						$('#recPurity').focus();
					}
					else{
						displayMsg(event, this,'No Record Of Issue Found');
					}

			}

		});

	} 
	
	 
	 

		$('#meltingReceiveDtId').bootstrapTable({})
		.on(
				'click-row.bs.table',

				function(e, row, $element) {
					
					$('#mReceiveEntry').css('display','none');
					
				});
	 
	 
	
		
		function roundUpRecStone(){
			var xtempStone = $('#recStone').val();
			$('#recStone').val(parseFloat(xtempStone).toFixed(0));
		}
		
		
	/* 	function popRecValidation(){
			
			if($("#recFreshMetalWt").val() > 0 && $("#recUsedMetalWt").val() > 0){
				displayMsg(event, this ,"Only One MetalWt can be entered at time (Either Fresh Or Used)!");	
				$("#recFreshMetalWt").val('0.0');
				$("#recUsedMetalWt").val('0.0');
			}
		}
		 */
		
	
		
		function totalRecStnCarat(){
			
			$('#meltingReceiveDtId').bootstrapTable({})
			.on(
					'load-success.bs.table',
					function(e, data) {
						var data = JSON.stringify($("#meltingReceiveDtId").bootstrapTable('getData'));
						
						//var vFreshMetal = 0.0;
						//var vUsedMetal = 0.0;
						//var vTotMetal = 0.0;
						var vRStones = 0.0;
						var vRCarat = 0.0;
						var vRNetWt = 0.0;
						var vRExpcPureWt = 0.0;
						
						//alert(vRStones);
						
						$.each(JSON.parse(data), function(idx, obj) {
							
							//vFreshMetal +=  Number(obj.freshMetalWt);
							//vUsedMetal 	+= Number(obj.usedMetalWt);
							//vTotMetal 	+= Number(obj.totMetalWt);
							vRStones		+= Number(obj.stone);
							vRCarat		+= Number(obj.carat);
							vRNetWt 		+= Number(obj.netWt);  
							vRExpcPureWt += Number(obj.expcPureWt);
							
						});
						
						//$('#vTotalFreshMetals').val(" " + parseFloat(vFreshMetal).toFixed(3));
						//$('#vTotalUsedMetals').val(" " + parseFloat(vUsedMetal).toFixed(3));
						//$('#vTotalMetals').val(" " + parseFloat(vTotMetal).toFixed(3));
						$('#vRecTotalStones').val(" " + vRStones);
						$('#vRecTotalCarats').val(" " + parseFloat(vRCarat).toFixed(3));
						$('#vRecTotalNetWts').val(" "+ parseFloat(vRNetWt).toFixed(3));
						$('#vRecTotalExpcPureWts').val(" "+ parseFloat(vRExpcPureWt).toFixed(3));
						
						//totalLoss();
					
					});
			
			
			
		}
		
		
		
		
		
	function totalLoss(){
		
		var totalDiff=($('#vTotalExpcPureWts').val() - $('#vRecTotalExpcPureWts').val()).toFixed(3);
		
		$('#pMeltingBal').val(Number(totalDiff));
		
		if(Number(totalDiff)>=$("#loss").val()){
			$("#metLoss").val($("#loss").val());
			//alert();
			editMetalMt();
			
		}else{
			$("#loss").val(0.0);
			displayMsg(event, this,'Loss Is Greater Than Net Balance');
		}
		
			//$("#loss").val(($('#vTotalExpcPureWts').val() - $('#vRecTotalExpcPureWts').val()).toFixed(3));
			
		}
		
		
/*  	function calculateLossAgain(){
 		
 		
 		alert($('#vTotalExpcPureWts').val());
 		alert($('#vRecTotalExpcPureWts').val());
 		alert($('#recExcpPureWt').val());
		
		$("#loss").val(($('#vTotalExpcPureWts').val() - ($('#vRecTotalExpcPureWts').val()+$('#recExcpPureWt').val())).toFixed(3))
		alert($("#loss").val());
	}  */
	
	
	function editMetalMt(){
		
		$
		.ajax({
			url : '<spring:url value='/manufacturing/transactions/editLoss/melting.html' />?vLoss='
					+ $('#loss').val()
					+"&pInvNo="+$('#invNo').val(),
			type : 'GET',
			success : function(data) {
				//$('#purityConversion').val(data);
			}

		});

		
	}
		
		
	function popMelRecCancelBtn(){
		$('#mReceiveEntry').css('display','none');
	}
	
	function refreshData(){
		$('#issColor').val('');
		$('#issFreshMetalWt').val('0.0');
		$('#issStone').val(0);
		$('#issCarat').val('0.0');
		$('#netWt').val('0.0');
		$('#excpPureWt').val('0.0');
		
	}
	
	
	function refreshRecData(){
		$('#recColor').val('');
		$('#recFreshMetalWt').val('0.0');
		$('#recStone').val(0);
		$('#recCarat').val('0.0');
		$('#recWt').val('0.0');
		$('#recExcpPureWt').val('0.0');
		
	}


	function meltingStnRecReport(){

		$
		.ajax({
			url : "/jewels/manufacturing/transactions/meltingMt/meltingStnRecReport.html?mtId="+$('#meltingMtId').val(),
			type : 'POST',
			success : function(data, textStatus, jqXHR) {
			
					$('#timeValCommonPdf').val(data);
					$("#generateDataRpt").trigger('click');
				
			}
		});
		
		}
	
	
	
</script>


<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">
<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />










