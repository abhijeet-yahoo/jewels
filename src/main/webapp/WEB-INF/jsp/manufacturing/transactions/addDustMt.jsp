<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">

	

	<div class="panel-heading">
		<div>
			<label class="col-lg-4 col-sm-4 text-left">
				<span style="font-size: 18px;">Dust Collection & Recovery </span>
			</label>
			<div class="text-right">
				<a class="btn btn-xs btn-info" style="font-size: 14px" type="button"
					href="/jewels/manufacturing/transactions/dustMt.html">Go To Listing</a>
				<input type="button" value="Save" style="font-size: 14px; width: 2cm" class="btn btn-xs btn-warning" id="dustSubmitBtn" onClick="javascript:dustSave();"/> 
		</div>
	 </div>
	 	
	</div>
	
	<div class="panel-body">

		<div class="form-group">
		<form:form commandName="dustMt" id="dustMtForm"
			action="/jewels/manufacturing/transactions/dustMt/add.html"
			cssClass="form-horizontal dustMtForm">

			<c:if test="${param.success eq true}">
				<div class="alert alert-success">DustMt ${action} successfully!</div>
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
						<label for="inputLabel3" class=".col-sm-2 text-right">From
							Period :</label>
					</div>
					<div class="col-sm-2">
						<label for="inputLabel3" class=".col-sm-2 text-right">To Period
							:</label>
					</div>
					<div class="col-sm-2">
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
						<form:input path="fromPeriod" cssClass="form-control" />
						<form:errors path="toPeriod" />
					</div>
					<div class="col-sm-2">
						<form:input path="toPeriod" cssClass="form-control" />
						<form:errors path="toPeriod" />
					</div>
					<div class="col-sm-2">
					</div>
					<div class="col-sm-4">
						<form:input type="hidden" path="id" id="meltingMtId"/>
						<form:input type="hidden" path="uniqueId" />
						<form:input type="hidden" path="srNo" />
						<form:input type="hidden" path="invNo" />
					</div>
				</div>
			</div>
		</form:form>
	</div>

	<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>




  <div id="mAllDetails" style="display: none"> <!-- hide1 start -->


	<div class="col-xs-6">
	
	<div class="panel panel-danger" style="border-style: dotted;">
	 
	 <div class="panel-body">
	 
	

	
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-sm-12 label label-default text-right"
					style="font-size: 18px;">Issue</span>
			</div>
		</div>
		
	

	
	<div class="form-group">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					
					<div>
						<table id="dustIssueDtId" data-toggle="table"
							data-toolbar="#toolbarIS" data-pagination="false"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" 
							data-height="350">
							<thead>
								<tr>
									<th data-field="department" data-align="left">Department</th>
									<th data-field="employee" data-align="left">Employee</th>
									<th data-field="other" data-sortable="true">Other</th>
									<th data-field="metalWt" data-sortable="true">Weight</th>
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
					
					<span style="display:inline-block; width: 2.5cm;"></span>
					
					Total Wt : <input type="text" id="vTotalMetalWts" name="vTotalMetalWts" value="${totalMetalWts}"
									maxlength="7" size="7" disabled="true" /> 
						&nbsp;&nbsp;
						</th>
						
				</tr>
			</table>
		
		

		
	<div id="meltIssDtId" style="display: none;">
		<div id="rowId">
		<form:form commandName="dustDt" id="dustIssDt"
			action="/jewels/manufacturing/transactions/dustDt/add.html"
			cssClass="form-horizontal dustIssDtForm">

			<table class="line-items editable table table-bordered">
				<thead class="panel-heading">
					<tr class="btn-warning" class="panel-heading">
						<th>Department</th>
						<th>Employee</th>
						<th>Other</th>
						<th>Weight</th>
						
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<form:select path="department.id" class="form-control" onchange="javascript:popEmplDustDt()">
								<form:option value="" label="- Select Department -" />
								<form:options items="${departmentMap}" />
							</form:select>
						</td>
						
						<td>
							<div id="employeeDivId">
							<form:select path="employee.id" class="form-control">
								<form:option value="" label="- Select Employee -" />
								<form:options items="${employeeMap}" />
							</form:select>
							</div>
						</td>
						<td><form:input path="other"  cssClass="form-control"  /></td>
						<td><form:input path="metalWt"   cssClass="form-control"  /></td>
					</tr>
					<tr>
						<td colspan="10">
							<input type="submit" value="Save" class="btn btn-primary" id="issueSubmitBtnId" />
							<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popCancel();"/> 
								<form:input type="hidden" path="id" id="issDtPk" />
								<input type="hidden" id="pInvNo" name="pInvNo" />
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
		</div>
	</div>
		
		
	</div>
	
	
	</div>
	</div>
	</div>
	
	<!------------//////////---------- receive -------////////--------->
	
	
	<div class="col-xs-6">
	
	<div class="panel panel-danger" style="border-style: dotted;">
	 
	 <div class="panel-body">
	
	
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-lg-12 label label-default text-right"
					style="font-size: 18px;">Receive</span>
			</div>
		</div>
	
	
	<div class="form-group">
		
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div>
						<table id="dustReceiveDtId" data-toggle="table"
							data-toolbar="#toolbarRC" data-pagination="false"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" 
							data-height="350">
							<thead>
								<tr>
									<th data-field="department" data-align="left">Department</th>
									<th data-field="employee" data-align="left">Employee</th>
									<th data-field="other" data-sortable="true">Other</th>
									<th data-field="burnWt" data-sortable="true">Burn Wt</th>
									<th data-field="purity" data-align="left">Purity</th>
									<th data-field="color" data-sortable="true">Color</th>
									<th data-field="createdDate" data-sortable="true">Date</th>
									<th data-field="metalWt" data-sortable="true">Metal Wt</th>
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
							<a class="btn btn-info" style="font-size: 15px" type="button" href="javascript:goToReceiveEntry();">
								<span class="glyphicon glyphicon-plus"></span>&nbsp;Add
							</a>
					
					<span style="display:inline-block; width: 2cm;"></span>
					
					Total MetalWt : <input type="text" id="vTotalRecMetalWts" name="vTotalRecMetalWts" value="${totalRecMetalWts}"
									maxlength="7" size="7" disabled="true" /> 
						&nbsp;&nbsp;
						</th>
						
				</tr>
			</table>
	
	
	
	
		<div id="dusttRecDtId" style="display: none;">
			<div id="rowRecId">
				<form:form commandName="dustRecDt" id="dustRecDtId"
					action="/jewels/manufacturing/transactions/dustRecDt/add.html"
					cssClass="form-horizontal dustRecDtForm">
		
						<table class="line-items editable table table-bordered">
						<thead class="panel-heading">
							<tr class="btn-warning" class="panel-heading">
								<th>Department</th>
								<th>Employee</th>
								<th>Other</th>
								<th>Burn Wt</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<form:select path="department.id" class="form-control" onchange="javascript:popEmpRecDt()">
										<form:option value="" label="- Select Department -" />
										<form:options items="${departmentMap}" />
									</form:select>
								</td>
								
								<td>
							<div id = "empDustRecDivId">	
							<form:select path="employee.id" class="form-control">
								<form:option value="" label="- Select Employee -" />
								<form:options items="${employeeMap}" />
							</form:select>
							</div>
						</td>
								
								<td><form:input path="other" cssClass="form-control"  /></td>
									<td><form:input path="burnWt"  id="burnWt" cssClass="form-control"  /></td>
								
							
							</tr>
							<%-- <tr>
								<td colspan="10">
									<input type="submit" value="Save" class="btn btn-primary" id="receiveSubmitBtnId" />
									<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popRecCancel();"/> 
										<form:input type="hidden" path="id" id="recDtPk" />
										<form:input type="hidden" path="uniqueId" id="uniqueId" />
										<input type="hidden" id="pInvRecNo" name="pInvRecNo" />
								</td>
							</tr> --%>
						</tbody>
						
						<thead>
								<tr class="btn-warning" class="panel-heading">
								
								<th>Purity</th>
								<th>Color</th>
								<th>Metal Wt</th>
							</tr>
						
						</thead>
						
						<tbody>
							<tr>
														
							
								<td>
									<form:select path="purity.id" class="form-control">
										<form:option value="" label="- Select Purity -" />
										<form:options items="${purityMap}" />
									</form:select>
								</td>
								<td>
									<form:select path="color.id" class="form-control">
										<form:option value="" label="- Select Color -" />
										<form:options items="${colorMap}" />
									</form:select>
								</td>
								<td><form:input path="metalWt"  id="recMetWt" cssClass="form-control"  /></td>
							</tr>
							<tr>
								<td colspan="10">
									<input type="submit" value="Save" class="btn btn-primary" id="receiveSubmitBtnId" />
									<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popRecCancel();"/> 
										<form:input type="hidden" path="id" id="recDtPk" />
										<form:input type="hidden" path="uniqueId" id="uniqueId" />
										<input type="hidden" id="pInvRecNo" name="pInvRecNo" />
								</td>
							</tr>
						</tbody>
						
					</table>
				</form:form>
			</div>
		</div>
	
	
	
	</div>
	
		</div>
		</div>
	</div>
	
	
	
	
	
	
	

	</div>
	

	
	
	</div> <!-- ending the panel body-->
</div> <!-- ending the main panel -->


<script type="text/javascript">

	$(document).ready(
			function(e) {
				
				$(".dustMtForm").validate(
						{
							rules : {
								
								fromPeriod : {
									required : true,
								},
								toPeriod : {
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
				
				
				
				
				
				$(".dustIssDtForm").validate(
						{
							rules : {								
								metalWt : {
									required : true,
									greaterThan : "0.0",
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
							
							messages : {
								metalWt : {
									greaterThan : "This field is required"
								}
							},
							
							
							
							
						});
				
				
				
				
				
				$(".dustRecDtForm").validate(
						{
							rules : {
								
								
								'purity.id' : {
									required : true,
								},
								'color.id' : {
									required : true,
								},
								burnWt : {
									required : true,
									greaterThan : "0.0",
								},
								metalWt : {
									required : true,
									greaterThan : "0.0",
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
							
							
							messages : {
								metalWt : {
									greaterThan : "This field is required"
								},
								burnWt : {
								greaterThan : "This field is required"
								}
							},
							
						});
				
				
				

				$("#fromPeriod").datepicker({
					dateFormat : 'dd/mm/yy'
				});
				
				$("#toPeriod").datepicker({
					dateFormat : 'dd/mm/yy'
				});
				
				
				
				if (window.location.href.indexOf('edit') != -1) {
					$("#mAllDetails").css('display', 'block');
				}
				
				popDustIssDt();
				popDustRecDt();
				
				

			});
	
	
	
	
	//-----dustMt-----//
	
	
	function dustSave(){
		$("form#dustMtForm").submit();
	}
	
	
	$(document)
	.on(
			'submit',
			'form#dustMtForm',
			function(e) {
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

				$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,
							success : function(data, textStatus, jqXHR) {
								
								//alert(data);
								
								if(data === '-1'){
									displayMsg(event, this,'Dates already present ');
								}else{
									window.location.href = data		
								}
								
										
							},
							error : function(jqXHR, textStatus,
									errorThrown) {
								
							}
						});
				e.preventDefault(); //STOP default action
				
				
	 });
	
	

	
	//-----------//issueDustDt----------//
	
	
	function popDustIssDt() {

		$("#dustIssueDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/dustDt/listing.html?pInvNo="
									+ $('#invNo').val()
						});
		
		}
	
	
	
	
	$(document)
	.on(
			'submit',
			'form#dustIssDt',
			function(e) {
				
				$('#issueSubmitBtnId').attr('disabled', 'disabled');
				
				$("#pInvNo").val($("#invNo").val());
			
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

				$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,
							success : function(data, textStatus, jqXHR) {
								
								//alert(data);
								
								if(data === '-1'){
									popDustIssDt();	
									$( 'form#dustIssDt input[type="text"],textarea, select').val('');
									$('form#dustIssDt select').val('');
									$('#issDtPk').val('');
									$('#metalWt').val('0.0');
								}else if(data === '-3'){
									popDustIssDt();	
									$( 'form#dustIssDt input[type="text"],textarea, select').val('');
									$('form#dustIssDt select').val('');
									$('#issDtPk').val('');
									$('#metalWt').val('0.0');
									$('#meltIssDtId').css('display','none');
								}else if(data === '-2'){
									displayMsg(event, this,'Department or Other is compulsary');
								}else if(data === '-8'){
									displayMsg(event, this,'Other Value Cannot be only in number form');
								}
								else{
									displayMsg(event, this,'Only one Entry at time,Either Department or Other can be Selected');
								}
								
			
								$('#issueSubmitBtnId').removeAttr('disabled');
								
							},
							error : function(jqXHR, textStatus,
									errorThrown) {
								
							}
						});
				e.preventDefault(); //STOP default action
			});

	
	
	
	
		function editDustIssDt(dtId){
			
			$.ajax({
				url : "/jewels/manufacturing/transactions/dustDt/editRights.html?dtId="+dtId,
				type : 'GET',
				success : function(data) {
					if(data === '1'){
					
						$('#meltIssDtId').css('display','block');
						$('#department\\.id').focus();
						$.ajax({
							url : "/jewels/manufacturing/transactions/dustDt/edit/"+ dtId + ".html",
							type : 'GET',
							success : function(data) {
								$("#rowId").html(data);
								$('#department\\.id').focus();
								
							}
						});
					
					}else{
						displayMsg(event, this,'Cannot edit');
					}
					
				}
			
			});
			
		}
	
	
	
	function goToIssueEntry(){
		$('#meltIssDtId').css('display','block');
		$('#department\\.id').focus();
		
		$( 'form#dustIssDt input[type="text"],textarea, select').val('');
		$('form#dustIssDt select').val('');
		
		$('#issDtPk').val('');
		$('#metalWt').val('0.0');
		
	}
	
	
	function popCancel(){
		$('#meltIssDtId').css('display','none');
	}
	
	
	
	
	
	$('#dustIssueDtId').bootstrapTable({})
	.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#dustIssueDtId").bootstrapTable('getData'));		
				var vMetalWt = 0.0;
		
				$.each(JSON.parse(data), function(idx, obj) {
					vMetalWt		+= Number(obj.metalWt);		
				});
				
				$('#vTotalMetalWts').val(" " + parseFloat(vMetalWt).toFixed(3));
			});
		
	
	
	
	function deleteDustDt(e,dustDtId){
		
		displayDlg(
				e,
				'javascript:deleteDustDtData('+dustDtId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
		
		
	}
	
	
	
	function deleteDustDtData(dustDtPk){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/dustDt/delete/'+dustDtPk+'.html',
			data: 'GET',
			success : function(data){
				
				//alert(data);
				
				if(data === '-1'){
					displayMsg(event, this,'Cannot Delete');
				}else{
					popDustIssDt();
				}
				
			}
			
		})
		
	}
	
	
	//-----------//Receive--//DustRecDt----------//
	
	
	
	function popDustRecDt() {

		$("#dustReceiveDtId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/dustRecDt/listing.html?pInvNo="
									+ $('#invNo').val()
						});
		
		}
	
	
	
	
	$(document)
	.on(
			'submit',
			'form#dustRecDtId',
			function(e) {
				
				$('#receiveSubmitBtnId').attr('disabled', 'disabled');
				
				$("#pInvRecNo").val($("#invNo").val());
				
			
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

				$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,
							success : function(data, textStatus, jqXHR) {
								
								// alert(data);
								
								if(data === '-1'){
									popDustRecDt();
									$( 'form#dustRecDtId input[type="text"],textarea, select').val('');
									$('form#dustRecDtId select').val('');
									$('#recDtPk').val('');
									$('#recMetWt').val('0.0');
									//$('#receiveSubmitBtnId').removeAttr('disabled');
								}else if(data === '-3'){
									popDustRecDt();
									$( 'form#dustRecDtId input[type="text"],textarea, select').val('');
									$('form#dustRecDtId select').val('');
									$('#recDtPk').val('');
									$('#recMetWt').val('0.0');
									$('#dusttRecDtId').css('display','none');
									//$('#receiveSubmitBtnId').removeAttr('disabled');
								}else if(data === '-11'){
									displayMsg(event, this,'Department or Other is compulsary');
								}else if(data === '-12'){
									displayMsg(event, this,'Only one Entry at time,Either Department or Other can be Selected');
								}else if(data === '-8'){
									displayMsg(event, this,'Other Value Cannot be only in number form');
								}else{
									displayMsg(this, null, ' Multiple Record Found In MetalTran, Stop The Entry and Please Contact Admin ');
								}
								
								$('#receiveSubmitBtnId').removeAttr('disabled');
								
							},
							error : function(jqXHR, textStatus,
									errorThrown) {
								
							}
						});
				e.preventDefault(); //STOP default action
			});

	
	
	
	
			function editDustRecDt(dtId){
				
				
				$.ajax({
					url : "/jewels/manufacturing/transactions/dustRecDt/editRights.html?dtId="+dtId,
					type : 'GET',
					success : function(data) {

						if(data === '1'){
						
							$('#dusttRecDtId').css('display','block');
							$('#purity\\.id').focus();
							
							$.ajax({
								url : "/jewels/manufacturing/transactions/dustRecDt/edit/"+ dtId + ".html",
								type : 'GET',
								success : function(data) {
									$("#rowRecId").html(data);
									$('#purity\\.id').focus();
								}
							});
							
						}else{
							displayMsg(event, this,'Cannot edit');
						}
						
					}
				});
				
			}
		
		
		
		function goToReceiveEntry(){
			$('#dusttRecDtId').css('display','block');
			$('#purity\\.id').focus();
			
			$( 'form#dustRecDtId input[type="text"],textarea, select').val('');
			$('form#dustRecDtId select').val('');
			
			$('#recDtPk').val('');
			$('#recMetWt').val('0.0');
			
		}
		
		
		function popRecCancel(){
			$('#dusttRecDtId').css('display','none');
		}
		
		
		
		$('#dustReceiveDtId').bootstrapTable({})
			.on(
				'load-success.bs.table',
				function(e, data) {
					var data = JSON.stringify($("#dustReceiveDtId").bootstrapTable('getData'));		
					var vMetalWt = 0.0;
			
					$.each(JSON.parse(data), function(idx, obj) {
						vMetalWt		+= Number(obj.metalWt);		
					});
					
					$('#vTotalRecMetalWts').val(" " + parseFloat(vMetalWt).toFixed(3));
				});
			
		
		
		
		function deleteRecDt(e,dustDtId){
			
			displayDlg(
					e,
					'javascript:deleteDustRecDtData('+dustDtId+');',
					'Delete',
					'Do you want to Delete  ?',
					'Continue');
			
			
		}
		
		
		
		function deleteDustRecDtData(dustDtPk){
			
			$("#modalDialog").modal("hide");
			
			$.ajax({
				
				url:'/jewels/manufacturing/transactions/dustRecDt/delete/'+dustDtPk+'.html',
				data: 'GET',
				success : function(data){

					if(data === '-1'){
						displayMsg(event, this,'Cannot Delete');
					}else{
						popDustRecDt();
					}
					
				}
				
			})
			
		}
			
		function popEmplDustDt(){
		$.ajax({
					
					url:'/jewels/manufacturing/transactions/dustDt/employee/list.html?deptId='+ $('#dustIssDt   #department\\.id').val(),
					data: 'GET',
					success : function(data){
					$('#employeeDivId').html(data);
						
					}
				})
			
		}
			
		function popEmpRecDt(){
			
			$.ajax({
				
				url:'/jewels/manufacturing/transactions/dustRecDt/employee/list.html?deptId='+$('#dustRecDtId #department\\.id').val(),
				data: 'GET',
				success : function(data){
					$('#empDustRecDivId').html(data);
				}
			})
			
		}
	
	
	
 </script>




<script src="/jewels/js/common/common.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />














