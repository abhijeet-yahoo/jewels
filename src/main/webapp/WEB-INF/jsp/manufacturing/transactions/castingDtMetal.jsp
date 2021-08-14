
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<!-- <link href="https://unpkg.com/bootstrap-table@1.14.2/dist/bootstrap-table.min.css" rel="stylesheet"> -->

<link rel="stylesheet" href="/jewels/bootstrap-table-master/dist/bootstrap-table.min.css">

<!-- <script src="https://unpkg.com/bootstrap-table@1.14.2/dist/bootstrap-table.min.js"></script> -->
<script src="/jewels/bootstrap-table-master/dist/bootstrap-table.min.js"></script>

<div class="panel-body">
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<form:form commandName="castingDt" id="transferToTranMtt"
					action="/jewels/manufacturing/transactions/castingDtTrf/add.html"
					cssClass="form-horizontal transferToTranMtt">

					<table class="table">
						<tbody>
							<tr>
								<th class="col-xs-1">Department:</th>
								<td class="col-xs-2"><form:select path="department.id" class="form-control">
										<form:option value="" label="- Select department -" />
										<form:options items="${departmentMap}" />
									</form:select></td>
								<td class="col-xs-1">
									<input type="submit" value="Transfer" class="btn btn-primary"  id="transferSubmitBtn" /> 
									<form:input type="hidden" path="id" />
									<input type="hidden" name="pODIds" id="pODIds"> 
									<input type="hidden" name="metalWtt" id="metalWtt">
								</td>
								 
								<td class="col-xs-4" align="right"><input type="checkbox" id="chkTrf" title="Transfer Pending"
									onclick="javascript:readyToTransfer();" /> <strong>Display Only Pending For Transfer Bags</strong></td>
							</tr>
							
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>



	<div class="form-group" id="dsPId">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 ">
					<div>
						<table id="castingDtTab" data-toggle="table" data-search="true"  data-maintain-selected="true"  data-row-style="rowStyle"
						data-unique-id="id"
							data-height="350">
							<thead>
								<tr class="btn-primary">
									<th data-field="state" data-checkbox="true">Select</th>
									<th data-field="bagNo" >Bag No</th>
									<th data-field="styleNo" data-align="left">Style No</th>
									<th data-field="qty" data-align="left">Bag Qty</th>
									<th data-field="partName" data-align="left">Part No</th>
									<th data-field="partPcs" data-align="left">Part Pcs</th>
									<th data-field="metalWt" data-formatter="metalWtFormatter">Metal Wt</th>
									<th data-field="transfer">Transfered</th>
									<th data-field="action1">Delete</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="mUpdateId" style="display: none">
		<div class="row">
			<div class="form-group">
				<div class="col-xs-12">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th class="col-xs-8"></th>
								<th class="col-xs-1">Metal Wt:</th>
								<td class="col-xs-2"><input type="text" id="fldMetalWt"
									name="fldMetalWt" class="form-control" /></td>
								<td class="col-xs-1"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


	<div class="col-xs-12">
		<hr style="width: 100%; color: red; height: 2px; background-color: red;" />
	</div>
	
	<div id="toolbarAddBags">
		<div class="col-lg-3 col-sm-3">
			<a class="btn btn-primary" style="font-size: 15px" type="button"
				onClick="goToNextPage()"> <span class="glyphicon glyphicon-plus"></span>&nbsp;Add Bags
			</a> <input type="hidden" name="castingMtPk" id="castingMtPk" /> <input
				type="hidden" name="cDate" id="cDate" /> <input type="hidden"
				name="treeNo" id="treeNo" />
		</div>

	</div>

	<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>
	

</div>

<script type="text/javascript">

$(document).ready(function(){
	
	
	 $(function() {
		    $('#castingDtTab').bootstrapTable()
		  });
	
	
	
	 $(".transferToTranMtt").validate(
				{
					rules : {
						
						'department.id' : {
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
	 
 })
 
 
 

 
 
 </script>
