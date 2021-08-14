
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<div id="rowId">
	<div class="form-group">
		<div class="form-group">
			<form:form commandName="metalOutwardDt" id="metalOutwardDtt"
							action="/jewels/manufacturing/transactions/metalOutwardDt/add.html"
							cssClass="form-horizontal metalOutwardDttForm">

							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr class="btn-primary" class="panel-heading">
										<th>Location</th>
										<th>Metal</th>
										<th>Purity</th>
										<th>Color</th>
										<th>Invoice Wt</th>
										<th>Metal Wt</th>
										<th>Rate</th>
										<th>Amount</th>
										<!-- but on next row  <th>Remark</th>  -->
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><form:select path="department.id" class="form-control" disabled="true">
												<form:option value="" label="- Select Department -" />
												<form:options items="${departmentMap}" />
											</form:select></td>
										
										
										<td><form:select path="metal.id" class="form-control" onChange="javascript:popPurity(this.value);" disabled="true">
												<form:option value="" label="- Select Metal -" />
												<form:options items="${metalMap}" />
											</form:select></td>
										<td>
										
											<div id="purityId">
												<form:select path="purity.id" class="form-control" disabled="true">
													<form:option value="" label="- Select Purity -" />
													<form:options items="${purityMap}" />
												</form:select>
											</div>
										</td>
										<td>
											<div id="colorId">
												<form:select path="color.id" class="form-control" disabled="true">
													<form:option value="" label="- Select Color -" />
													<form:options items="${colorMap}" />
												</form:select>
											</div>
										</td>


										<td><form:input path="invWt" cssClass="form-control" /></td>
										<td><form:input path="metalWt" cssClass="form-control"  onChange="javascript:setAmount()" /></td>
										<td><form:input path="rate1" cssClass="form-control"
												onchange="javascript:setAmount()" /></td>
										<td><input type="text" id="aAmount" name="aAmount"
											class="form-control" disabled="true" /></td>
									</tr>
									<tr>
										<td colspan="10"><input type="submit" value="Save" class="btn btn-primary" /> 
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popMetalOutCancelBtn()">
										<form:input type="hidden" id="metalOutwardDtEntryId" path="id" />
										<input type="hidden" id="pInvNo" name="pInvNo" />
											<form:input type="hidden" path="department.id" />  
											<input type="hidden" id="prevMetalWt" name="prevMetalWt" /> 
											<input type="hidden" id="metalWt" name="metalWt" /> 
											<form:input	type="hidden" path="uniqueId" /> 
											<form:input type="hidden" path="amount" /></td>
									</tr>
								</tbody>
							</table>
						</form:form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(
			function(e) {
				$(".metalOutwardDttForm").validate(
						{
							rules : {
								'metal.id' : {
									required : true,
								},
								'purity.id' : {
									required : true,
								},
								'color.id' : {
									required : true,
								},
								invWt : {
									required : true,
									greaterThan : "0,0",
								}

							},

							messages : {
								invWt : {
									greaterThan : "This field is required"
								}
							},
							
						});
				
					setAmount();
				
				 if (window.location.href.indexOf('edit') != -1) {
						var prev = $('#metalWt').val();						
						$('#prevMetalWt').val(prev);	
						
					}else if (window.location.href.indexOf('view') != -1) {
						canViewFlag=true;
						
					//	$('#metalOutwardDtEntry').find('input, textarea, select').attr('disabled','disabled');
						$('#rowId').find('input, textarea, select').attr('disabled','disabled');
						
						$('#metalOutwardMtDivId').removeAttr('onclick');
						$('#metalOutwardMtDivId .btn').hide();
						$('#metalOutwardDtEntry .btn').hide();
						$('#mtListingBtnId').show();
						
						if($('#adminRightsMap').val() != true){
						
						$("#mOutwardDt").css('display', 'block');
						$('#metalOutwardMtDivId').find('input, textarea, select').attr('disabled','disabled');
						$('#mOutwardDt :input').attr('disabled',true);
						$('#mOutwardDt .btn').hide();
						$('#mOutwardDt .btn').removeAttr('href');
						}
						
					}

			});

</script>


<script src="/jewels/js/common/common.js"></script>