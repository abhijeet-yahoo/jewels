
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>
<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>
<script src="/jewels/chosen/chosen.jquery.js"></script>

<div id="compInwDivId">
<div class="form-group">
	<div class="form-group">
		<form:form commandName="compInwardDt" id="compInwardDtt"
			action="/jewels/manufacturing/transactions/compInwardDt/add.html"
			cssClass="form-horizontal compInwardDttForm">


			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
					<tr class="btn-warning" class="panel-heading">
						<th>Location</th>
						<th>Metal</th>
						<th>Component</th>
						<th>Purity</th>
						<th>Color</th>
						<th>InvoiceWt</th>
						
					</tr>
				</thead>
				<tbody>
					<tr>
				
					<td><form:select path="department.id" class="form-control" required="true">
											<form:option value="" label="- Select Department -" />
												<form:options items="${departmentMap}" />
											</form:select></td>
											
						<td><form:select path="metal.id" class="form-control" required="true"
								onChange="javascript:popPurity(this.value);">
								<form:option value="" label="- Select Metal -" />
								<form:options items="${metalMap}" />
							</form:select></td>
						<td>
							<div id="componentId">
								<form:select path="component.id" class="form-control" required="true">
									<form:option value="" label="- Select Component -" />
									<form:options items="${componentMap}" />
								</form:select>
							</div>
						</td>
						<td>
							<div >
								<form:select path="purity.id"  class="form-control" required="true">
									<form:option value="" label="- Select Purity -" />
									<form:options items="${purityMap}" />
								</form:select>
							</div>
						</td>
						<td>
							<div id="colorId">
								<form:select path="color.id" class="form-control" required="true">
									<form:option value="" label="- Select Color -" />
									<form:options items="${colorMap}" />
								</form:select>
							</div>
						</td>
						
						<td><form:input path="invWt" cssClass="form-control" onchange="javascript:setAmount()"/></td>


										
					</tr>
		
				</tbody>
					<thead class="panel-heading">
					<tr class="btn-warning" class="panel-heading">
						<th>MetalWt</th>
						<th>Per Gms Rate</th>
						<th>Qty</th>
						<th>Rate</th>
						<th>Amount</th>
						<!-- but on next row  <th>Remark</th>  -->
					</tr>
				</thead>
				<tbody>
					<tr>
						
										<td><form:input path="metalWt" cssClass="form-control" onchange="javascript:setAmount()" /></td>
										<td><form:checkbox path="perGramRate" onchange="javascript:setAmount()" /></td>
										<td><form:input path="qty" cssClass="form-control" onchange="javascript:setAmount()" /></td>
										<td><form:input path="rate" cssClass="form-control" onchange="javascript:setAmount()" /></td>
										<!-- <td><input type="text" id="amount" name="amount" class="form-control" disabled="true" /></td> -->
										<td><form:input path="amount"  class="form-control" readonly="true" /></td>
					</tr>
					<tr>
						<td colspan="10">
							<input type="submit" value="Save" class="btn btn-primary" id="compInwardDtBtnId" />
										<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popCompInwardDtCancelBtn()"> 
											<%-- <form:input type="hidden" path="id" />  --%>
											<form:input type="hidden" id="compInwardDtEntryId" path="id" />
											<input type="hidden" id="pInvNo" name="pInvNo" />
											<form:input type="hidden" path="department.Id" />
											<form:input type="hidden" path="uniqueId" />
											
											<input type="hidden" id="vPurityId" name="vPurityId" />
											<input type="hidden" id="invWtt" name="invWtt" />
						</td>
					</tr>
				</tbody>
				
			</table>
		</form:form>
	</div>
</div>
</div>

<script type="text/javascript">
	$(document).ready(function(e) {
				
		$('#metal\\.id').chosen();
		$('#component\\.id').chosen();
		$('#purity\\.id').chosen();
		$('#color\\.id').chosen();
		$('#department\\.id').chosen();
		
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
		
		
		$(".compInwardDttForm").validate({
			rules : {
			
				invWt : {
					required : true,
					greaterThan : "0,0",
				},
				metalWt : {
					required : true,
					greaterThan : "0,0",
				}

			},

			messages : {
				invWt : {
					greaterThan : "This field is required"
				},
				metalWt : {
					greaterThan : "This field is required"
				}
			},

			

		});
		
		 if (window.location.href.indexOf('view') != -1) {
				
				canViewFlag=true;
				
				$('#compMtListngBtnId').show();
				$("#cInwardDt").css('display', 'block');
				$('#compInwDivId').find('input, textarea, select').attr('disabled','disabled');
				
				if($('#adminRightsMap').val() != true){
					
				$('#compInwardMtDivId .btn').hide();
				
				$('#compInwardMtDivId').find('input, textarea, select').attr('disabled','disabled');
				$('#cInwardDt :input').attr('disabled',true);
				$('#cInwardDt .btn').hide();
				$('#cInwardDt .btn').removeAttr('onclick');
				$('#compInwDivId .btn').hide();
				$('#compInwardDtBtnId').hide();
				$('#compInwDivId').find('input, textarea, select').attr('disabled','disabled');
				$('#compMtListngBtnId').show();
			}
			}	

		setAmount();
	});
	
	
	
	function popPurity(vSel) {
		
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/purityComp/list.html' />?metalId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#purity\\.id").html(data);
						$("#purity\\.id").trigger("chosen:updated");
					}
				});
	}
</script>





