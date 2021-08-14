<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalTransfer.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Piece Production</span>
	</div>

	<div class="form-group">
		<form:form commandName="empPcsProduction"
			action="<spring:url value='/manufacturing/transactions/empPcsProduction/add.html' />"
			cssClass="form-horizontal empPcsProductionTransferForm">

			<c:set var="option" value="User" />

			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 text-right"></label> <label
					class="col-sm-2 text-right">Department:</label>
				<div class="col-sm-2">
					<form:select path="department.id" class="form-control"
						onChange="javascript:popEmployee();">
						<form:option value="" label=" Select department " />
						<form:options items="${departmentMap}" />
					</form:select>
				</div>
				<label class="col-sm-2 text-right">Bag No:</label>
				<div class="col-sm-2">
					<form:input path="bagMt.name" cssClass="form-control"
						onblur="javascript:displayDetails();popProdLabType();" />
					<form:errors path="bagMt.name" />
				</div>
				
				<div>					
				 	<button class="glyphicon glyphicon-list btn-primary"  style="font-size: 20px" type="button" onClick="javascript:openEmpPcsProductionTab()"></button>
							
					</div>
			</div>

		</form:form>
	</div>


</div>
<!-- ending the first panel -->


<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" >
		<span style="font-size: 18px;">Details</span>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div>
					<table id="pcsProdDetailId" data-toggle="table"
						data-toolbar="#toolbar" data-pagination="false"
						data-side-pagination="server"
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350">
						<thead>
							<tr class="btn-primary">
								<th data-field="employee" data-sortable="true">Employee</th>
								<th data-field="prodLabourType" data-sortable="true">ProdLabType</th>
								<th data-field="rate" data-sortable="true">Rate</th>
								<th data-field="styleNo" data-sortable="true">Style</th>
								<th data-field="bagPcs" data-align="left">Bag Pcs</th>
								<th data-field="prodPcs" data-align="left">Prod Pcs</th>
								<th data-field="recWt" data-sortable="true">RecWt</th>
								<!-- <th data-field="issWt" data-sortable="true">IssWt</th>
								<th data-field="loss" data-sortable="true">Loss</th> -->
								<th data-field="action1" data-align="center">Edit</th>
								<th data-field="action2" data-align="center">Delete</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="toolbar">
		<a class="btn btn-info" style="font-size: 15px" type="button"
			onclick="javascript:displayBreakUp()"> <span
			class="glyphicon glyphicon-plus"></span>&nbsp;Add
		</a>
	</div>

	<div id="entryForm" style="display: none">
		<div id="rowId">
			<div class="form-group">
				<div class="form-group">
					<form:form commandName="empPcsProduction"
						id="empPcsProductionEntry"
						action="/jewels/manufacturing/transactions/empPcsProduction/add.html"
						cssClass="form-horizontal empPcsProductionEntryForm">

						<table class="line-items editable table table-bordered">
							<thead class="panel-heading">
								<tr class="btn-warning" class="panel-heading">
									<th>Employee</th>
									<th>ProdLabType</th>
									<th>Rate</th>
									<th>StyleNo</th>
									<th>Bag Pcs</th>
									<th>Prod Pcs</th>
									<th>RecWt</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<div id="employeeId">
											<form:select path="employee.id" class="form-control">
												<form:option value="" label="- Select Employee -" />
												<form:options items="${employeeMap}" />
											</form:select>
										</div>
									</td>
									<td>
										<div id="prodLabTypeId">
											<form:select path="prodLabType.id" class="form-control"
												onChange="javascript:getRate(this.value) ">
												<form:option value="" label="- Select ProdLabType -" />
												<form:options items="${prodLabTypeMap}" />
											</form:select>
										</div>
									</td>
									<td><form:input path="rate" cssClass="form-control" disabled="true" /></td>
									<td><form:input path="orderDt.design.styleNo" id="styleNoId" cssClass="form-control" disabled="true" /></td>
									<td><form:input path="bagPcs" cssClass="form-control" disabled="true" /></td>
									<td><form:input path="prodPcs" cssClass="form-control" />
									<td><form:input path="recWt" cssClass="form-control" disabled="true" /></td>
									<%-- <td><form:input path="issWt" cssClass="form-control" onChange="javascript:setLoss()" /></td>
									<td><form:input path="loss" cssClass="form-control" /></td> --%>
								</tr>
								<tr>
									<td colspan="10">
									<input type="submit" value="Save" class="btn btn-primary" id="empPcsSubmitBtn" />
									<input type="button" value="cancel" class="btn btn-danger" onclick="javascript:popEmpPcsCancelBtn()"/> 
										<form:input type="hidden" path="id" /> 
										<input type="hidden" id="vDeptId" name="vDeptId">
										<input type="hidden" id="vBagNo" name="vBagNo"> 
										<input type="hidden" id="vRate" name="rate">
										<input type="hidden" id="vPcs" name="bagPcs">
										<input type="hidden" id="vRecWt" name="recWt">
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
<!-- ending the second panel -->


<script type="text/javascript">
	
	$(document).ready(function(e) {
	
	
		$(".empPcsProductionTransferForm").validate({
			
			rules : {
				'department.id' : {
					required : true,

				},
				'bagMt.name' : {
					required : true,
					remote : {
						url : "<spring:url value='/manufacturing/transactions/empPcsProduction/bagNoAvailable.html' />",
						type : "get",
						data : {

							'department.id' : function() {
								return $("#department\\.id").val();
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
				'bagMt.name' : {
					remote : "Bag not found on floor"
				}
			}

		});

			
	
	
	
		
		 $(".empPcsProductionEntryForm").validate({

			rules : {
				'employee.id' : {
					required : true,

				},
				'prodLabType.id' : {
					required : true,

				},
				prodPcs :  {
					required : true,
					greaterThan : "0.0",
				},
				bagPcs :  {
					required : true,
					greaterThan : "0.0",
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
				prodPcs : {
					greaterThan : "This field is required"
				},
				bagPcs : {
					greaterThan : "This field is required"
				}
			},
		
			
		});
		
		
		$("#bagMt\\.name")
			.autocomplete(
			{
				source : "<spring:url value='/manufacturing/transactions/empPcsProd/list.html' />",
				minLength : 2
			});

	}); 

	
	function displayDetails() {
		
		$("#entryForm").css('display', 'none');
		
		var vDeptId = $('#department\\.id').val();
		var vBagNo = $('#bagMt\\.name').val();
		
		if (!vDeptId || !vBagNo) {
			//display message if required (please select the department and bagNo) ,but at present no need 5/07/2016
		}else{
		
				$("#pcsProdDetailId")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/empPcsProduction/listing.html?deptId="
										+ $("#department\\.id").val()
										+ "&bagNo="
										+ $('#bagMt\\.name').val(),
							});
			
		}
	}

	function displayBreakUp() {

		var vDeptId = $('#department\\.id').val();
		var vBagNo = $('#bagMt\\.name').val();

		if (!vDeptId || !vBagNo) {
			displayMsg(event, this,'please select the department and bagNo');
		} else {

			$
					.ajax({
						url : '<spring:url value='/manufacturing/transactions/empPcsProduction/display.html' />?BagNo='
								+ $('#bagMt\\.name').val()
								+ "&departmentId="
								+ $('#department\\.id').val(),
						type : 'GET',
						success : function(data) {
							
							if(data === '-1'){
								displayMsg(event, this,'Bag not in '+ $('select[name=department\\.id] option:selected').text() +' department !');
							}else{	
								var temp = data.split("#");
								$('#styleNoId').val(temp[0]);
								$('#bagPcs').val(temp[1]);
								$('#recWt').val(temp[2]);
								$("#entryForm").css('display', 'block');
								$("#employee\\.id").focus();
								
								$('#id').val('');
								$("#employee\\.id").val('');
								$("#prodLabTypeId").val('');
								$('#rate').val('0.0');
								$('#prodPcs').val('0.0');
								//$('#issWt').val('0.0');
								//$('#loss').val('0.0');
							}
						}

					});
		}
	}

	
	function popEmployee() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/empPcsProduction/employee/list.html' />?departmentId='
							+ $('#department\\.id').val(),
					type : 'GET',
					success : function(data) {
						$("#employeeId").html(data);
					}
				});

	}

	
	function popProdLabType() {
		
		var vDeptId = $('#department\\.id').val();
		var vBagNo = $('#bagMt\\.name').val();
		
		if (!vDeptId || !vBagNo) {
			//display message if required (please select the department and bagNo) ,but at present no need 5/07/2016
		}else{
			
			$
					.ajax({
						url : '<spring:url value='/manufacturing/transactions/empPcsProduction/prodLabType/list.html' />?departmentId='
								+ $('#department\\.id').val()
								+ "&BagNo="
								+ $('#bagMt\\.name').val(),
						type : 'GET',
						success : function(data) {
							
							$("#prodLabTypeId").html(data);
						}
					});
		}

	}


	/* function setLoss() {
		var vIssWt = $('#issWt').val();
		var vRecWt = $('#recWt').val();
		var vLoss = vRecWt - vIssWt;
		$('#loss').val(vLoss);

	} */

	
	$(document)
			.on(
					'submit',
					'form#empPcsProductionEntry',
					function(e) {
						
						$('#empPcsSubmitBtn').attr('disabled', 'disabled');
						
						$('#vDeptId').val($('#department\\.id').val());
						$('#vBagNo').val($('#bagMt\\.name').val());
						$('#vProdLabType').val($('#prodLabTypeId').val());
						$('#vRate').val($('#rate').val());
						$('#vPcs').val($('#bagPcs').val());
						$('#vRecWt').val($('#recWt').val());
							
						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,
									success : function(data, textStatus, jqXHR) {
										displayDetails();
										$('form#empPcsProductionEntry input[type="text"],texatrea, select').val('');
										$('#department\\.id').val($('#vDeptId').val());
										$('#id').val('');
										
										
										$('#empPcsSubmitBtn').removeAttr('disabled');
										
										
										
										
									},
									error : function(jqXHR, textStatus,
											errorThrown) {
									}
								});
						e.preventDefault(); //STOP default action
					});
	
	

	function editEmpPcsProduction(dtId) {
		$.ajax({
			url : "/jewels/manufacturing/transactions/empPcsProduction/edit/" + dtId
					+ ".html",
			type : 'GET',
			success : function(data) {
				$("#entryForm").css('display', 'block');
				$("#rowId").html(data);
			}
		});
	}
	
	
	
	function popEmpPcsCancelBtn(){
		$('#entryForm').css('display','none');
	}
	
	
	
	function deleteEmpPcsProd(e,empPcsId){
		
		displayDlg(
				e,
				'javascript:deleteEmpPcsData('+empPcsId+');',
				'Delete',
				'Do you want to Delete  ?',
				'Continue');
		
		
	}
	
	
	
	function deleteEmpPcsData(empPcsPk){
		
		$("#modalDialog").modal("hide");
		
		$.ajax({
			
			url:'/jewels/manufacturing/transactions/empPcsProduction/delete/'+empPcsPk+'.html',
			data: 'GET',
			success : function(data){
				
				displayDetails();
				
			}
			
		})
		
		
	}
	
	
function openEmpPcsProductionTab(){
	
		
		if($('#department\\.id').val() !=''){
			
			$('#myTransferModal').modal('show');
			
			setTimeout(function(){
				
				
				$("#modalTransferTblId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/modalTransfer/listing.html?deptId="+$('#department\\.id').val()
									
						});

				
			}, 100);
						
			
		
					
			
		}
		else{ 
			 displayMsg(this, null, 'Please Select Department');
		}
		
		
		
	}


$('#modalTransferTblId').bootstrapTable({}).on(
		'dbl-click-row.bs.table',
		function(e, row, $element) {
		
			
			var bgNm=jQuery.parseJSON(JSON.stringify(row)).bagNo;
			
			$('#bagMt\\.name').val(bgNm);
			
			$('#myTransferModal').modal('hide');
			$('#bagMt\\.name').trigger('onblur');

			
		})

	

	
	
	
</script>

<script src="/jewels/js/common/tran.js"></script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />
