<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>



<c:set var="optionText" value="Delete" />

<div class="panel panel-success">
	<div class="panel-body">

	<div class="form-group">
		<form:form commandName="readyBag"
			cssClass="form-horizontal readyBagForm">

			<c:if test="${success eq true}">
				<div class="col-xs-12">
					<div class="row">
						&nbsp;
						<div class="alert alert-success">Bag ${action} successfully!</div>
					</div>
				</div>
			</c:if>
			
			
			<div class="col-xs-12">
				
				
				<div class="col-sm-4">
				<div class="form-group">
					<label class="col-sm-4 text-right">Tran Date :</label>
					<div class="col-sm-7">
						<input type="text" id="trandate" name="trandate" Class="form-control">
						
					</div>
				</div>
				</div>
				
				
				<div class="col-sm-4">
				<div class="form-group">
					<label class="col-sm-4 text-right">Bag No:</label>
					<div class="col-sm-7">
						<form:input path="bagMt.name" cssClass="form-control"  onblur="javascript:popDetails()"/>
						<form:errors path="bagMt.name" />
					</div>
				</div>
				</div>
				
				
		</div>		
			
		</form:form>
	</div> 


	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-lg-12 col-sm-12 label label-default"
					style="font-size: 18px;">Ready Bag Return Details</span>
			</div>
		</div>
	</div>

<div class="form-group" id="dsPId">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div>
					<table id="readyBagId" data-toggle="table"
						data-toolbar="#toolbarBgDt" data-side-pagination="server" data-striped="true"
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="400">
						<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="partNm" data-sortable="true">Part No.</th>
								<th data-field="stoneType" data-sortable="true">Stn. Type</th>
								<th data-field="shape" data-sortable="true">Shape</th>
								<th data-field="quality" data-align="left">Quality</th>
								<th data-field="mm" data-align="center">Size</th>
								<th data-field="sieve" data-align="center">Sieve</th>
								<th data-field="stone" data-align="center">Stone</th>
								<th data-field="carat" data-align="center">Carat</th>
								<th data-field="setting" data-align="center">Setting</th>
								<th data-field="setType" data-align="center">Set Type</th>
								<th data-field="centerStone" data-formatter="centerStoneFormatter">CenterStn</th>
								<th data-field="action2" data-align="center">Delete</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
		<div class="row">
				<div class="col-sm-8"></div>
							
			<div class = "col-sm-4">
					
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" 
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats"   
									maxlength="7" size="7" disabled="disabled" />	
			</div>
					
			
				</div>






</div>
</div>
<%@include file="/WEB-INF/jsp/common/modal.jsp"%>


<script type="text/javascript">
	
	$(document).ready(
			function(e) {
				
				$(".readyBagForm").validate(
						{
							rules : {},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							},
							messages : {}
						});
			

				$("#bagMt\\.name")
				.autocomplete(
						{
							source : "<spring:url value='/manufacturing/transactions/transfer/list.html' />",
							minLength : 4,
							change: function( event, ui ) {
								
							//	validateBag();
							}
						});

				
				$("#trandate").datepicker({
					dateFormat : 'dd/mm/yy'
				});
			
				$("#trandate").val('${currentDate}');
				
				if('${canEditTranddate}' === "false"){
					$("#trandate").attr("disabled","disabled");
				}
				$("#bagMt\\.name").focus();
				
			});

			function validateBag(){
				
				$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/transfer/bagNoAvailable.html' />?bagMt.name='
							+ $('#bagMt\\.name').val()
							+ "&deptFrom.id="
							+ $('#location\\.id').val(),
					type : 'GET',
					success : function(data) {
						if(data=='false'){
							
							var txtNm= $("#location\\.id option:selected").text();
							
							alert('Bag not In '+txtNm+' Department');
							$("#bagMt\\.name").val('');
						}
						
					}
				})
			}
	
	
	$('#readyBagId').bootstrapTable({}).on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#readyBagId").bootstrapTable('getData'));
				var vStones = 0.0;
				var vCarat = 0.0;
	
				$.each(JSON.parse(data), function(idx, obj) {
					vStones		+= Number(obj.stone);
					vCarat		+= Number(obj.carat);
				});
				
				$('#vTotalStones').val(" " + vStones);
				$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
				
			});

	
	
	function popDept() {
		$.ajax({
			url : '/jewels/manufacturing/transactions/toDepartment/list.html',
			type : 'GET',
			success : function(data) {
				
				$("#deptId").html(data);
			}
		});
	}
	
	


	 
	 function popDetails(){
		 
		 if(!! $("#bagMt\\.name").val().trim()){
						 
						 $("#readyBagId")
							.bootstrapTable(
									'refresh',
									{
										url : "/jewels/manufacturing/transactions/readyBagReturn/listing.html?pBagNm="+ $("#bagMt\\.name").val(),
									});
					
			  
		 }
		 
	 }
	 
	 
	 
	
	 $('#readyBagId')
			.on(
					'check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table',
					function() {
						$('#trfId').prop(
								'disabled',
								(!$('#readyBagId').bootstrapTable('getSelections').length));
					});
	
	

	function trfData(e) {
		
		$('#trfId').prop('disabled', true);
		
		dpId = $('#deptId').val();

		if ($.trim(dpId).length == 0) {
			displayMsg(this, null, 'Select the department');
			$('#trfId').prop('disabled', false);
		} else {
			displayDlg(e, 'javascript:doTransfer();', 'Transfer Ready Bags', 'Do you want to transfer the selected rows?', 'Continue');
		}
	}
	
	function doTransfer() {
		
		$("#modalDialog").modal("hide");
		
		

	

		var ids = $.map($("#readyBagId").bootstrapTable('getSelections'),
				function(row) {
					return row.id;
				});

		dpId = $('#deptId').val();

		$.ajax({
			url : "/jewels/manufacturing/transactions/readyBag/add.html",
			type : "POST",
			data : {
				pBagNm : $("#bagMt\\.name").val(),
				pIds : '' + ids,
				pDeptId : dpId,
				pUpdGold : false,
				pTrandate : $('#trandate').val(),
				pLocationId :$("#location\\.id").val()
				//pUpdGold : $('#updGold').is(":checked")
			},
			success : function(data, textStatus, jqXHR) {
				
				if(data === '-2'){
					displayMsg(this, null, 'Bag Not in Diamond Department');
				}else if(data === '1'){
					/* $("#readyBagId").bootstrapTable('refresh',{
						url : "/jewels/manufacturing/transactions/readyBag/listing.html?pBagNm="+ $("#bagMt\\.name").val()
					}); */

					popDetails();
				}else{
					
					displayMsg(this, null, 'error : Contact Admin');
				}
				
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});



		$('#trfId').prop('disabled', false);

		
		
	}

	function colFormatter(value, lApply) {
		var color = '#000000';

		if (lApply == 0) {
			value = getNumVal(value);
		} else if (lApply == 1) {
			color = "#FFA500";

			value = '<div  style="color: ' + color + '"><strong>' + value
					+ '</strong></div>';
		} else if (lApply == 2) {
			color = "#40FF00";

			value = '<div  style="color: ' + color + '"><strong>' + value
					+ '</strong></div>';
		}

		return value;
	}

	function getNumVal(dt) {
		if (typeof dt === 'undefined') {
		} else {
			dt = dt.substring(dt.indexOf("<strong>") + 8, dt
					.indexOf("</strong>"));
		}

		return dt;
	}
	
	
	function centerStoneFormatter(value) {

		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}

		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';

	}
	
	function readyBagDelete(dtId){
		
		$.ajax({
			url : "/jewels/manufacturing/transactions/readyBag/delete/"+ dtId + ".html?tranDate="+$('#trandate').val(),
			type : 'GET',
			success : function(data) {
				if(data.indexOf("error")!="-1"){
					displayMsg(this,null,data);
				}else{
					
					 $("#readyBagId")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/manufacturing/transactions/readyBag/listing.html?pBagNm="+ $("#bagMt\\.name").val()
								});
				}
			
			}
		});
		
	}


	function popDeptTo() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/transfer/deptTo.html' />?departmentId='
							+ $('#location\\.id').val()+"&dropDownId=deptId",
					type : 'GET',
					success : function(data) {
						
						$("#deptId").html(data);
					
						
						}
				});
	}
	
</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>






