<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" style="text-align: center;">
		<span style="font-size: 18px;">Diamond Rate Change</span>
	</div>

	<div class="panel-body">

		<div class="col-xs-10">
			<form:form commandName="diamondBagging" id="diaChang"
				cssClass="form-horizontal diaChagForm">

				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left"
								style="font-size: 13px;">Order No:</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left"
								style="font-size: 13px;">Bag:</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left"
								style="font-size: 13px;">Client:</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left"
								style="font-size: 13px;">Style No:</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left"
								style="font-size: 13px;">Bag Pcs:</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left"
								style="font-size: 13px;">Current Department:</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-2 col-sm-2">
							<form:input path="orderDt.orderMt.invNo" cssClass="form-control" />
							<form:errors path="orderDt.orderMt.invNo" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="bagMt.name" cssClass="form-control" value=""
								onBlur="javascript:popDetails();" />
							<form:errors path="bagMt.name" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="orderDt.orderMt.party.name"
								cssClass="form-control" />
							<form:errors path="orderDt.orderMt.party.name" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="orderDt.design.styleNo" cssClass="form-control" />
							<form:errors path="orderDt.design.styleNo" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="bagMt.qty" cssClass="form-control" />
							<form:errors path="bagMt.qty" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" name="vDepartment" id="vDepartment"
								class="form-control" disabled="true" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left"
								style="font-size: 13px;">KT:</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left"
								style="font-size: 13px;">Color:</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<p class="text-center" style="font-size: 13px;">
								<strong>-- Total Stone --</strong>
							</p>
						</div>
						<div class="col-lg-2 col-sm-2">
							<p class="text-center" style="font-size: 13px;">
								<strong>-- Total Carat --</strong>
							</p>
						</div>
						
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-2 col-sm-2">
							<form:input path="orderDt.purity.name" cssClass="form-control" />
							<form:errors path="orderDt.purity.name" />
						</div>
						<div class="col-lg-2 col-sm-2">
							<form:input path="orderDt.color.name" cssClass="form-control" />
							<form:errors path="orderDt.color.name" />
						</div>
						<div class="col-sm-2 text-left">
							<input type="text" name="vTotalStones" id="vTotalStones"
								class="form-control" disabled="true" />
						</div>
						<div class="col-sm-2 text-left">
							<input type="text" name="vTotalCarats" id="vTotalCarats"
								class="form-control" disabled="true" />
						</div>
						
					</div>
				</div>
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>

				<div class="row">&nbsp;</div>


			</form:form>
		</div>



		<div class="col-lg-2 col-md-3 col-sm-4 col-xs-6 center-block">
			<div class="panel panel-primary" style=" height:170px;">
				<div class="panel-body" >
					<div style="width: 150px; height: 50px" class="col-sm-4">
						<a id="oImgHRId" href="/jewels/uploads/manufacturing/blank.png" data-lighter> 
							<img id="ordImgId" class="img-responsive" src="/jewels/uploads/manufacturing/blank.png" />
						</a>
					</div>
				</div>
			</div>
		</div>





		<div class="row" align="center">
			<div class="form-group">
				<div class="col-xs-12">
					<span style="font-size: 16px; ">Diamond Breakup</span>
				</div>
			</div>
		</div>





		<div class="form-group" id="dsPId">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						<div>
							<table id="diaBagRateDtId" data-toggle="table"
								data-toolbar="#toolbarDt1" data-click-to-highlight="true"
								data-select-item-name="radioName" data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
								<thead>
									<tr class = "btn-primary">
										<th data-field="state"  data-checkbox="true">Select</th>
										<th data-field="stoneType">Stone Type</th>
										<th data-field="shape" data-align="left">Shape</th>
										<th data-field="subShape">Sub Shape</th>
										<th data-field="quality">Quality</th>
										<th data-field="mm" data-align="right">Size/MM</th>
										<th data-field="sieve" data-align="right">Sieve</th>
										<th data-field="sizeGroup">Size Group</th>
										<th data-field="stones" data-align="right">Stone</th>
										<th data-field="carat" data-align="right">Carat</th>
										<th data-field="stnRate" data-align="center">Rate</th>
										<th data-field="setting">Setting</th>
										<th data-field="setType">Set Type</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		
		
			
	<div class="row" >
		<div class="form-group">
			<div class="col-xs-1" >
				
				<form:form commandName="diamondBagging" id="rateChange"
					action="/jewels/manufacturing/transactions/diamondRate/changeUpdate.html"
					cssClass="form-horizontal rateChange">

					<table class="table">
						<tbody>
							<tr>
								<td class="col-xs-1"></td>
								<td class="col-xs-1">
									<input type="submit" value="Save" class="btn btn-info" id="rateChangeSubmitBtn" /> 
									<form:input type="hidden" path="id" />
									<input type="hidden" id="pODIds" name="pODIds" />
									<input type="hidden" id="tempStnRate" name="tempStnRate" />
								</td>
							</tr>
						</tbody>
					</table>
					
				</form:form>
			</div>
		</div>
	</div>
		
		




	</div> <!-- ending the panel body -->

</div> <!-- ending the main panel -->

 <script type="text/javascript">


	$(document)
		.ready(
			function(e) {
			
				
				
			 $("#diaBagRateDtId").on("dblclick", "td:not(.active)", function () {
				
			    var $this = $(this);
			    
			    var $textbox = $("<input>", { type: "text",value: $this.addClass("active").text() });
			    $this.html($textbox);
			    $textbox.select();
			    $textbox.focus();
			    
			});
			 
			 
			 var stnTblRow = -1;
			 
				 $('#diaBagRateDtId').bootstrapTable({})
						.on(
								'click-row.bs.table',

								function(e, row, $element) {

									stnTblRow = $element.attr('data-index');
	       
							}); 
								
								
							
			 

			$("#diaBagRateDtId").on("blur", "input:text", function () {        
			    var $this = $(this);
			    $this.parent().removeClass("active").text($this.val());
			    
			    $("#diaBagRateDtId").bootstrapTable('updateRow', {
					index : stnTblRow,
					row : {
						state : true,
						stnRate : $this.val(),

					}
				}); 
			    
			    
			    
			}); 
				

				
		$("#bagMt\\.name")
			.autocomplete(
				{
					source : "<spring:url value='/manufacturing/transactions/diaRateChange/autoFill/list.html' />",
					minLength : 2
				});

		});


	

	function popDetails() {

		if($("#bagMt\\.name").val()!=""){
		
		$
			.ajax({
				url : "<spring:url value='/manufacturing/transactions/diaRateChange/diamondChanging/details.html?bagName=' />"
						+ $('#bagMt\\.name').val(),
				type : "GET",
				success : function(data, textStatus, jqXHR) {
					$.each(JSON.parse(data), function(idx, obj) {
						$('#orderDt\\.orderMt\\.invNo').val(obj.invNo);
						$('#orderDt\\.orderMt\\.party\\.name').val(obj.party);
						$('#orderDt\\.design\\.styleNo').val(obj.styleNo);
						$('#bagMt\\.qty').val(obj.bagPcs);
						$('#orderDt\\.purity\\.name').val(obj.kt);
						$('#orderDt\\.color\\.name').val(obj.color);
						$('#ordImgId').attr('src', obj.image);
						$('#oImgHRId').attr('href', obj.image);
						$('#vDepartment').val(obj.department);
						
						popImpStnAdj();
		
					});
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("in error");
				}
			});
		}

	}







	function popImpStnAdj() {
	
		$("#diaBagRateDtId")
			.bootstrapTable(
					'refresh',
					{
						url : "/jewels/manufacturing/transactions/impstnAdj/diamondRateChange/listing.html?bagName="
								+ $('#bagMt\\.name').val()
		
					});
	
	}


	
	
	$('#diaBagRateDtId').bootstrapTable({})
	.on(
		'load-success.bs.table',
		function(e, data) {
			var data = JSON.stringify($("#diaBagRateDtId").bootstrapTable('getData'));

			var vStone = 0;
			var vCarat = 0.0;

			$.each(JSON.parse(data), function(idx, obj) {
				vStone += Number(obj.stones);

			});
			
			$.each(JSON.parse(data), function(idx, obj) {
				vCarat += Number(obj.carat);

			});

			$('#vTotalStones').val(" " + parseFloat(vStone));
			$('#vTotalCarats').val(" " + parseFloat(vCarat).toFixed(3));
			
		})
		
	
		
		
		$(document)
		 .on(
			'submit',
			'form#rateChange',
			function(e) {

				
				//$('#returnSubmitBtn').attr('disabled', 'disabled');

				var data = $('#diaBagRateDtId').bootstrapTable('getSelections');

				var ids = $.map(data, function(item) {
					return item.id;
				});
				
				var vStnRate  = $.map(data, function(item) {
						
					if(item.stnRate === "" || item.stnRate === null){
						return item.stnRate = 0;
					}else{
						return item.stnRate;	
					}
					
				});
				
				
				$('#pODIds').val(ids);
				$('#tempStnRate').val(vStnRate);

				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");

				$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,

							success : function(data, textStatus, jqXHR) {
								
								if(data === '-1'){
									displayInfoMsg(this, null, 'Rate Applied Sucessfully !');
									popImpStnAdj();
								}else{
									displayMsg(this, null, 'Rate is Zero or blank, check the entry');
								}	
								
							},
							error : function(jqXHR, textStatus,
									errorThrown) {
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

