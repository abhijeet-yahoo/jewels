<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel-body">


<!-- ---------------------------//-------------inward Change --------------//---->


<div id="InwardTypeDivIdf" class="col-xs-6">


			<div class="container-fluid">
					<div class="row">
						
							<div>
								<table id="changeInwardId" data-toggle="table" data-toolbar="#toolbarCI"
									data-pagination="false" data-side-pagination="server"
									data-click-to-select="false" data-select-item-name="checkboxNameCI"
									data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
									<thead>
										<tr class="btn-primary">
											<th data-field="state" data-checkbox="true"></th>
											<th data-field="inwardType" data-sortable="true">Inward Type</th>
											<th data-field="handlingRate" data-sortable="false">Rate</th>
										</tr>
									</thead>
								</table>
							</div>
					</div>
				</div>
			
			
	<div id="chgInwardUpdate" style="display: none">
		<div class="row">
			<div class="form-group">
				<div class="col-xs-12">
				<form:form commandName="costStnDt" >
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th class="col-xs-2">Rate :</th>
								<td class="col-xs-2"><input type="text" id="fldInwRate" name="fldInwRate" maxlength="7" size="7" /></td>
								<td class="col-xs-8"></td>
							</tr>
						</tbody>
					</table>
				</form:form>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="row" >
		<div class="form-group">
			<div class="col-xs-1" >
				<form:form commandName="costStnDt" id="inwardLabRate"
					action="/jewels/manufacturing/transactions/changeInward/setIRate.html"
					cssClass="form-horizontal transferOfChgInwRate">

					<table class="table">
						<tbody>
							<tr>
								<td class="col-xs-1" >
								<input type="submit" value="Save" class="btn btn-primary" /> 
									<form:input type="hidden" path="id" />
									<input type="hidden" id="inwTypeId" name="inwTypeId" />
									<input type="hidden" id="inwRate" name="inwRate" />
									<input type="hidden" id="iCostMtId" name="iCostMtId" />
									<input type="hidden" id="inwType" name="inwType" />
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>
	


</div>  <!-- ending the second div -->





	<div id="labTypeDivIdf" class="col-xs-6">


				<div class="container-fluid">
					<div class="row">
						
							<div>
								<table id="labTypeId" data-toggle="table" data-toolbar="#toolbarLI"
									data-pagination="false" data-side-pagination="server"
									data-click-to-select="false" data-select-item-name="checkboxLI"
									data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
									<thead>
										<tr class="btn-primary">
											<th data-field="state" data-checkbox="true"></th>
											<th data-field="labType" data-sortable="true">Labour Type</th>
											<th data-field="rate" data-sortable="false">Lab Rate</th>
										</tr>
									</thead>
								</table>
							</div>
					</div>
				</div>
			
			
			
			
			
				<div class="row" >
					<div class="form-group">
						<div class="col-xs-1" >
							<form:form commandName="costLabDt" id="costLabDttId"
								action="/jewels/manufacturing/transactions/changeLabourRate/setRate.html"
								cssClass="form-horizontal transferOfChgLabRate">
			
								<table class="table">
									<tbody>
										<tr>
											<td class="col-xs-1" >
												<input type="submit" value="Save" class="btn btn-primary" />
												<input type="hidden" id="labIdPk" name="labIdPk" />
												<input type="hidden" id="labRate" name="labRate" />
												<input type="hidden" id="mtIdCost" name="mtIdCost" />
											</td>
										</tr>
									</tbody>
								</table>
								
							</form:form>
						</div>
					</div>
				</div>
			
			
			
			


	</div>



















</div> <!-- ending panel body -->


<script type="text/javascript">

$(document).ready(function(e) {
		$('#cLabour').on('click', function() {
			popInwardType();
			popLabourType();
		});
		
		
		//$('#pCostMtId').val($('#costMtId').val());
		$('#iCostMtId').val($('#costMtId').val()); // for stoneChange
		
		
		
		
		//---------//--inward Type Inline Editing---//
		
		
		 $("#changeInwardId").on("dblclick", "td:not(.active)", function () {
				
			    var $this = $(this);
			    
			    var $textbox = $("<input>", { type: "text",value: $this.addClass("active").text() });
			    $this.html($textbox);
			    $textbox.select();
			    $textbox.focus();
			    
			});
			 
			 
			 var xstnTblRow = -1;
			 
				 $('#changeInwardId').bootstrapTable({})
						.on(
								'click-row.bs.table',

								function(e, row, $element) {

									xstnTblRow = $element.attr('data-index');
	       
							}); 
								
								
							
			 

			$("#changeInwardId").on("blur", "input:text", function () {        
			    var $this = $(this);
			    $this.parent().removeClass("active").text($this.val());
			    
			    $("#changeInwardId").bootstrapTable('updateRow', {
					index : xstnTblRow,
					row : {
						state : true,
						handlingRate : $this.val(),

					}
				}); 
			    
			    
			    
			}); 
				
		
		
		
		
		//---------//--Labour Type Inline Editing---//
		
		
		 $("#labTypeId").on("dblclick", "td:not(.active)", function () {
				
			    var $this = $(this);
			    
			    var $textbox = $("<input>", { type: "text",value: $this.addClass("active").text() });
			    $this.html($textbox);
			    $textbox.select();
			    $textbox.focus();
			    
			});
			 
			 
			 var stnTblRow = -1;
			 
				 $('#labTypeId').bootstrapTable({})
						.on(
								'click-row.bs.table',

								function(e, row, $element) {

									stnTblRow = $element.attr('data-index');
	       
							}); 
								
								
							
			 

			$("#labTypeId").on("blur", "input:text", function () {        
			    var $this = $(this);
			    $this.parent().removeClass("active").text($this.val());
			    
			    $("#labTypeId").bootstrapTable('updateRow', {
					index : stnTblRow,
					row : {
						state : true,
						rate : $this.val(),

					}
				}); 
			    
			    
			    
			}); 
				
		
		
		
		
})



/* 	function getNumVal(dt) {
		if (typeof dt === 'undefined') {
		} else {
			dt = dt.substring(dt.indexOf("<strong>") + 8, dt
					.indexOf("</strong>"));
		}

		return dt;
	} */



	//-------------------inward------------->>>
	
	
	/* var inwTblRow = -1;
	$('#changeInwardId').bootstrapTable({})
			.on(
					'click-row.bs.table',
					function(e, row, $element) {
						inwTblRow = $element.attr('data-index');
						
							$('#chgInwardUpdate').css('display','block');
							$('#fldInwRate').val(getNumVal(jQuery.parseJSON(JSON.stringify(row)).handlingRate));
							$('#fldInwRate').focus();
							$('#fldInwRate').select();
					});

	
	
	$('#fldInwRate').on('blur', function(e) {
		$("#changeInwardId").bootstrapTable('updateRow', {
			index : inwTblRow,
			row : {
				state : true,
				handlingRate : $('#fldInwRate').val(),
			}
		});
		
		$('#fldInwRate').val('');
		$('#chgInwardUpdate').css('display','none');
	}); */
	
	
	
	$(document).on('submit', 'form#inwardLabRate', function(e) {
		
		var data =  $('#changeInwardId').bootstrapTable('getSelections');
		
		var idss = $.map(data, function(item) {
			return item.id;
		});
		
		var hRate = $.map(data, function(item) {
			return item.handlingRate;
		});
		
		var vInwType = $.map(data, function(item) {
			return item.inwardType;
		});
	
		$('#inwTypeId').val(idss);
		$('#inwRate').val(hRate);
		$('#inwType').val(vInwType);
		
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				
				if(data == "-1"){
					displayMsg(this, null, 'Rate updated successfully!');
				}
				
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});
	
	
	
	
	
	
	
	//-------------Change Labour Type Rate ------//-------//
	
	
	$(document).on('submit', 'form#costLabDttId', function(e) {
		
		var data =  $('#labTypeId').bootstrapTable('getSelections');
		
		var idss = $.map(data, function(item) {
			return item.id;
		});
		
		var hRate = $.map(data, function(item) {
			return item.rate;
		});
	
		$('#labIdPk').val(idss);
		$('#labRate').val(hRate);
		$('#mtIdCost').val($('#iCostMtId').val());
		
		
		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				
				if(data === "-1"){
					displayInfoMsg(this, null, 'Rate updated successfully!');
				}else{
					displayMsg(this, null, 'No Record Was Selected ! ');
				}
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});
	
	

</script>

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

