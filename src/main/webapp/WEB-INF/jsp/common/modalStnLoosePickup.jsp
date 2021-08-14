<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
	.ui-autocomplete{
		z-index:1151 !important; 
	}
	
</style>



    <div class="modal fade" id="myStnLoosePickupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1150px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Loose Stone List</h4>
			</div>
			
		

			<div class="modal-body">
			
				
		
		<div class="row" id="stnLooseDtDivId">
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="shape">Shape</label>
					<input type="text" id="vshapeId" name="vshapeId" class="form-control" readonly="readonly" >
					
					</div>
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="quality">Quality</label>
					<input type="text" id="vqualityId" name="vqualityId" class="form-control" readonly="readonly" >
					
					</div>
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="sieve">Sieve</label>
					<input type="text" id="vsieve" name="vsieve" class="form-control" readonly="readonly" >
					
					</div>
					
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="size">Size</label>
					<input type="text" id="vsize" name="vsize" class="form-control" readonly="readonly" >
					
					</div>
					
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="vBalCarat">Bal Carat</label>
					<input type="text" id="vBalCarat" name="vBalCarat" class="form-control" readonly="readonly" >
					
					</div>
					
					<div class="form-group col-sm-2">
					<label class="control-label" for="avgRate">Avg Rate</label>
					<input type="text" id="avgRate" name="avgRate" class="form-control" readonly="readonly" >
					
					</div>
		
			<div class="form-group col-sm-2">
					<label class="control-label" for="balVal">Bal Amount</label>
					<input type="text" id="balVal" name="balVal" class="form-control" readonly="readonly" >
					
					</div>
		</div>	
		
		
		<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-lg-12 col-sm-12 label label-default" style="font-size: 16px;">Conversion Details
					</span>
			</div>
		</div>
	</div>
	
	<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
	
	<a class="btn btn-info" style="font-size: 15px" type="button" id="stkLooseStkConversionBtnId"
					onClick="javascript:stkLooseStkConversion()"
					href="javascript:void(0)"><span></span>&nbsp;Add</a>
	
	<div id="conversionDtId" style="display: none" >
	
	<div class="panel panel-primary" style="width: 100%">
	<div class="form-group">
		<form:form commandName="looseStkConversion" id="looseStkConversionDt"
			action="/jewels/manufacturing/transactions/looseStkConversion/add.html"
			cssClass="form-horizontal looseStkConversiontForm">

			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right">StoneType:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right">Shape:</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right">Quality:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right">Size:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right">Sieve:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right">Size Group:</label>
					</div>
					
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-2 col-sm-2">
						<div id="stoneTypeId">
							<form:select path="stoneType.id" class="form-control" required="true">
								<form:option value="" label=" SelectStoneType " />
								<form:options items="${stoneTypeMap}" />
							</form:select>
						</div>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:select path="shape.id" class="form-control" required="true"
							onChange="javascript:popSubShape(this.value);popQuality(this.value);popStoneChart(this.value);popSizeGroup(this.value)" > 
							<form:option value="" label="- Select Shape -" />
							<form:options items="${shapeMap}" />
						</form:select>
					</div>
				
					<div class="col-lg-2 col-sm-2">
						<div id="qualityId">
							<form:select path="quality.id" class="form-control" required="true">
								<form:option value="" label="- Select Quality -" />
								<form:options items="${qualityMap}" />
							</form:select>
						</div>
					</div>
					<div class="col-lg-2 col-sm-2">
						<div id="sizeId">
							<form:select path="size" class="form-control" required="true"
								onChange="javascript:getSizeMMDetails()">
								<form:option value="" label="- Select Size -" />
								<form:options items="${stoneChartMap}" />
							</form:select>
						</div>
					</div>
					<div class="col-lg-2 col-sm-2">
						<input type="text" id="vSieve" name="vSieve" class="form-control"
							disabled="disabled" />
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<input type="text" id="vSizeGroupStr"
						name="vSizeGroupStr" value="${sizeGroupName}" class="form-control" disabled="disabled" />
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-xs-12">
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right">Stone:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right">Carat:</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right">Rate:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right">Amount:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Packet No:</label>
						</div>
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-right">Remark:</label>
						</div>
					
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
					
					<div class="col-lg-2 col-sm-2">
						<form:input path="stone" cssClass="form-control" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="carat" cssClass="form-control" onchange="javascript:setAmount();" />
					</div>
				
					<div class="col-lg-2 col-sm-2">
						<form:input path="rate" cssClass="form-control"	onchange="javascript:setAmount()" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<input type="text" id="aAmount" name="aAmount"
							class="form-control" disabled="disabled" />
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<form:input path="packetNo" cssClass="form-control" />
					</div>
						
					<div class="col-lg-2 col-sm-2">
						<form:input path="remark" cssClass="form-control" />
					</div>
					
				</div>
			</div>
			
			
			
			
				<div class="row">
				<div class="col-xs-12">
					
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="form-group">
				<div class="col-sm-5">
					<input type="submit" value="Save" class="btn btn-primary" id="stnDtBtnId"/>
					<input type="button" value="Cancel" class="btn btn-danger" onclick="javascript:popCloseBtn()">
					<form:input type="hidden" id="stnLooseDtEntryId" path="id" />
					<form:input type="hidden" path="sieve" />
					<form:input type="hidden" path="department.id" />
					<form:input type="hidden" path="uniqueId" />
					<form:input type="hidden" path="amount" />
					<input type="hidden" id="vCarat" name="vCarat" />
					<input type="hidden" id="vStone" name="vStone" />
					<input type="hidden" id="sizeGroupStr" name="sizeGroupStr" />
					<input type="hidden" id="vMtId" name="vMtId" />
					<input type="hidden" id="vDtId" name="vDtId" />
					
				</div>
			</div>
		</form:form>
	</div>

	
	</div>
	
		
		
	
			
			
			
			<div>
			
			
			
			
			</div>
			
		<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>			
			
			</div>
			
			
			
			
			</div>
			</div>
			
			
			</div>
			
			</div>
			
			
			
			
	
	<script type="text/javascript">


	function stkLooseStkConversion(){
			$('#conversionDtId').css('display','block');
		}

	function popCloseBtn(){
		$('#conversionDtId').css('display','none');
		}


	var looseDtId = false;
	function setAmount() {
		
		looseDtId = false;

		var vCarat = $('#carat').val();
		var rate = $('#rate').val();
		var vDiffCarat = $('#diffCarat').val();
		var amount = vCarat * rate;
		
		var vvCarat = vCarat * 1;
		var dcarat = vDiffCarat * 1;
		var vRate = rate * 1;

		if(vvCarat > $('#vBalCarat').val()){
			displayMsg(event, this, 'Carat cannot be greater than Bal Carat');
			$('#carat').val(0);
		}else if( amount > $('#balVal').val()){
			displayMsg(event, this, 'Amount cannot be greater than Bal Amount');
			$('#aAmount').val(0);
			$('#amount').val(0);
			$('#rate').val(0);
		}else{

			if(vvCarat == $('#vBalCarat').val()){
				$('#rate').attr('readonly','readonly');
				$('#rate').val($('#avgRate').val());
				
				$('#aAmount').val($('#balVal').val());
				$('#amount').val($('#balVal').val());

				looseDtId = true;
				
			}else{
				$('#rate').removeAttr('readonly','readonly');
				$('#rate').val(vRate.toFixed(2));
				$('#aAmount').val(amount.toFixed(2));
				$('#amount').val(amount.toFixed(2));
				
				}
			
			
			$('#carat').val(vvCarat.toFixed(3));
			
			$('#diffCarat').val(dcarat.toFixed(3));
			}	
		
	}



	$(document)
	.on(
			'submit',
			'form#looseStkConversionDt',
			function(e) {
				
				
				$('#stnDtBtnId').attr('disabled', 'disabled');
			
				$('#sieve').val($('#vSieve').val());
				$('#sizeGroupStr').val($('#vSizeGroupStr').val());
				$('#vCarat').val($('#carat').val());  
				$('#vStone').val($('#stone').val());
				
				var postData = $(this).serializeArray();
				var formURL = $(this).attr("action");


				if($('#amount').val() != 0){
				
				$
						.ajax({
							url : formURL,
							type : "POST",
							data : postData,
							success : function(data, textStatus, jqXHR) {
								
								if(data === '-11'){
									displayMsg(event, this, 'BalCarat cannot be greater than Carat');
								}else{	

									if(looseDtId){
										$('#stnLooseConversionDtDivId').css('display','none')		
										}
									
									popStnLoosePickDt();
									popStnLooseConversionDt();
									getStnLooseBalanceStock();
									

									$('form#looseStkConversionDt select').val('').trigger('chosen:updated');
									$("#stone").val('0');
									$("#carat").val('0.0');
									$("#diffCarat").val('0.0');
									$('#rate').val('0.0');
									$('#aAmount').val('0.0');
									$('#vSieve').val('');
									$('#vSizeGroupStr').val('');
									
								
								}
								
								$('#stnDtBtnId').removeAttr('disabled');

							},
							error : function(jqXHR, textStatus,
									errorThrown) {
							}
						});
				}else{
					$('#stnDtBtnId').removeAttr('disabled');
					displayMsg(event, this, 'Amount cannot be zero');
					
					}
				
				e.preventDefault(); //STOP default action
			});







	
	</script>		
			