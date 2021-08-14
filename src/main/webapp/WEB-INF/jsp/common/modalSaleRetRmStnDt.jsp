<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
</style>



	<!--------- SaleRetRmStnDt modal --------->
	
	<div class="modal fade" id="mySaleRetRmStnDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Stone Receive Detail</h4>
	      </div>
	      
	      <div class="modal-body">
				
				<div class="form-group" id="saleRetRmStnDtModalDivId">
				<form:form commandName="saleRetRmStnDt" id="saleRetRmStnDtFormId"
					action="/jewels/marketing/transactions/saleRetRmStnDt/add.html"
					cssClass="form-horizontal saleRetRmStnDtForm">
				
				
				<div class="row">
				
				<div class="col-xs-12">
				
						<div class="row">
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Location:</label>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">StoneType:</label>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Shape:</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">SubShape:</label>
						</div>
						
										
						
						
					</div>
					<div class="row">
						<div class="col-lg-3 col-sm-3">
						<form:select path="department.id" class="form-control">
									<form:option value="" label=" Select Location " />
									<form:options items="${locationStnMap}" />
								</form:select>
							</div>	
						<div class="col-lg-3 col-sm-3">
							<form:select path="stoneType.id" class="form-control">
												<form:option value="" label=" SelectStoneType " />
												<form:options items="${stoneTypeMap}" />
											</form:select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:select path="shape.id" class="form-control"
											onChange="javascript:popSubShape(this.value);popQuality(this.value);popStoneChart(this.value);popSizeGroup(this.value)">
											<form:option value="" label="- Select Shape -" />
											<form:options items="${shapeMap}" />
										</form:select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<div id="subShapeId">
											<form:select path="subShape.id" class="form-control">
												<form:option value="" label="- Select SubShape -" />
												<form:options items="${subShapeMap}" />
											</form:select>
										</div>
						</div>
						
						
							
							
						
					</div>
					
					<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					
					<div class="row"> 
					
					
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Quality:</label>
						</div>
					
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Size:</label>
						</div>
						
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Sieve:</label>
						</div>	
					
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">SizeGroup:</label>
						</div>
						
					</div>
					
					
						<div class="row"> 
						<div class="col-lg-3 col-sm-3">
										<div id="qualityId">
												<form:select path="quality.id" class="form-control">
													<form:option value="" label="- Select Quality -" />
													<form:options items="${qualityMap}" />
												</form:select>
											</div>
				
							</div>					
						
					<div class="col-lg-3 col-sm-3">
					<div id="sizeId">
									<form:select path="size" class="form-control"
										onChange="javascript:getSizeMMDetails()">
										<form:option value="" label="- Select Size -" />
										<form:options items="${stoneChartMap}" />
									</form:select>
								</div>
						</div>
						
					<div class="col-lg-3 col-sm-3">
							<input type="text" id="vSieve" name="vSieve" class="form-control" readonly="readonly" />
						</div>	
						
					<div class="col-lg-3 col-sm-3">
							<input type="text" id="vSizeGroupStr"name="vSizeGroupStr" class="form-control" readonly="readonly" />
						</div>	
						
							
					
					</div>
		
		
				<div class="row">
						<div class="col-sm-12">&nbsp;</div>
					</div>
					<div class="row"> 
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Stone:</label>
						</div>	
					
					
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Carat:</label>
						</div>
						
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Diff Carat:</label>
						</div>	
					
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Rate:</label>
						</div>
						
					
					
					</div>
					
					<div class="row">
					<div class="col-lg-3 col-sm-3">
							<form:input path="stone" cssClass="form-control" />
						</div>	
						
					<div class="col-lg-3 col-sm-3">
							<form:input path="carat" cssClass="form-control" onchange="javascript:setAmountStn()" />
						</div>	
						
					<div class="col-lg-3 col-sm-3">
							<form:input path="diffCarat" cssClass="form-control"/>
						</div>		
						
					<div class="col-lg-3 col-sm-3">
						<form:input path="rate" cssClass="form-control" onchange="javascript:setAmountStn()" />
						</div>			
					
					
						
					</div>
					
					<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
							
							<div class="row">
								
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-2 text-right">Amount:</label>
						</div>	
									<div class="col-lg-3 col-sm-3">
										<label for="inputLabel3" class=".col-lg-2 text-right">Packet No:</label>
									</div>
									<div class="col-lg-3 col-sm-3">
										<label for="inputLabel3" class=".col-lg-2 text-right">Remark:</label>
									</div>
								</div>

							<div class="row">
									<div class="col-lg-3 col-sm-3">
							<form:input path="amount" cssClass="form-control" readonly="true"/>
						</div>	
										<div class="col-lg-3 col-sm-3">
											<form:input path="packetNo" cssClass="form-control" />
										</div>
									<div class="col-lg-6 col-sm-6">
										<form:input path="remark" cssClass="form-control" />
									</div>
									
							</div>
				
				</div>
				
				</div>
				
		
					<div class="row">
						<div class="col-lg-12">
							<form:input type="hidden" path="id" id="modalSaleRetRmStnDtId"/>
							<input type="hidden" id="vSaleRetRmStnMtId" name="vSaleRetRmStnMtId" />
							
						</div>
					</div>
					
				</form:form>
			</div>
				
		
	       
	      </div>
	      
	      <div class="modal-footer">
	        	<input type="button" value="Save" class="btn btn-default" onclick="javascript:popSaleRetRmStnDtSave()" id="saleRetRmStnBtnId"/>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       </div>
	      
	    </div>
	  </div>
	</div>
	
	
	<script type="text/javascript">

	$(document).ready(function(e) {
		$(".saleRetRmStnDtForm").validate({
			rules : {
			
				'department.id' : {
					required : true,
				},
				'stoneType.id' : {
					required : true,
				},
				'shape.id' : {
					required : true,
				},
				'quality.id' : {
					required : true,
				},
				size : {
					required : true,
				},
				carat : {
					required : true,
					greaterThan : "0,0",
				}

			},

			messages : {
				carat : {
					greaterThan : "This field is required"
				}
			},

		});
		
		

	})
	
	
	function popSubShape(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/subShape/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#subShapeId").html(data);
					}
				});
	}

	function popQuality(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/quality/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#qualityId").html(data);
					}
				});
	}

	function popStoneChart(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/stoneChart/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#sizeId").html(data);
					}
				});
	}

	function getSizeMMDetails() {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/sizeMM/details.html' />?shapeId='
							+ $('#shape\\.id').val()
							+ '&size='
							+ $('#size').val(),
					type : 'GET',
					success : function(data) {
						fldData = data.split("_");
						$("#vSieve").val(fldData[0]);
						$("#vSizeGroupStr").val(fldData[1]);
					}
				});
	}

	function popSizeGroup(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/sizeGroup/list.html' />?shapeId='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#sizeGroupId").html(data);
					}
				});
	}

	
	
	function popSaleRetRmStnDtSave(){
		
		
		var postData = $("#saleRetRmStnDtFormId").serializeArray();
	
		var formURL = $("#saleRetRmStnDtFormId").attr("action");
		$('#saleRetRmStnBtnId').attr('disabled', 'disabled');
		
		 if($(".saleRetRmStnDtForm").valid()){
		
			$.ajax({
				url : formURL,
				type : "POST",
				data : postData,
				success : function(data, textStatus, jqXHR) {  
						
					if(data === '1'){
						displayInfoMsg(this,null,"Record Added Successfully !");
						popSaleRetRmStnDt();
						
						 $('#saleRetRmStnDtModalDivId input[type="text"],textarea').val('');
						 $('#saleRetRmStnDtModalDivId input[type="number"]').val('0');
						 $('#saleRetRmStnDtModalDivId').find('select').val('');
						 $('#modalSaleRmStnDtId').val('');
					
					}else if(data === '2'){
						 displayInfoMsg(this,null,"Record Updated Successfully !");
						 $('#mySaleRetRmStnDtModal').modal('hide');
						 popSaleRetRmStnDt();
						 
						 $('#saleRetRmStnDtModalDivId input[type="text"],textarea').val('');
						 $('#saleRetRmStnDtModalDivId input[type="number"]').val('0');
						 $('#saleRetRmStnDtModalDivId').find('select').val('');
						 $('#modalSaleRetRmStnDtId').val('');
					
					}else{
						displayMsg(this,null,data);
					}
					
					$('#saleRetRmStnBtnId').removeAttr('disabled');
										
				},
				error : function(data, textStatus, jqXHR) {
					displayMsg(this,null,"Error Occoured , Contact Admin !");
				}
		
			});
		
		 }
		 $('#saleRetRmStnBtnId').removeAttr('disabled');
	}
	
	function setAmountStn() {
		
		
		var vCarat = $('#saleRetRmStnDtModalDivId #carat').val();
		var vrate = Number($('#saleRetRmStnDtModalDivId #rate').val());
		var vDiffCarat = $('#saleRetRmStnDtModalDivId #diffCarat').val();
		var vamount = Number(vCarat) * Number(vrate);
		
		var vvCarat = vCarat * 1;
		var dcarat = vDiffCarat * 1;
		var vvRate = vrate * 1;

		$('#saleRetRmStnDtModalDivId #amount').val(Number(vamount.toFixed(2)));
		$('#saleRetRmStnDtModalDivId #carat').val(vvCarat.toFixed(3));
		$('#saleRetRmStnDtModalDivId #rate').val(vvRate.toFixed(2));
		$('#saleRetRmStnDtModalDivId #diffCarat').val(dcarat.toFixed(3));

	}

	
	
	</script>
<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />



<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
	
	
	