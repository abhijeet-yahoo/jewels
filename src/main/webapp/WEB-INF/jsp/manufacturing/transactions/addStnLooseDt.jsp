
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<!-- <div id="rowId"> -->
<div class="form-group">
	<div class="form-group">
		<form:form commandName="stnLooseDt" id="stnLooseDtt"
			action="/jewels/manufacturing/transactions/stnLooseDt/add.html"
			cssClass="form-horizontal stnLooseDttForm">

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
						<form:input path="stone" cssClass="form-control" onchange="javascript:getPointer()" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="carat" cssClass="form-control" onchange="javascript:setAmount()" />
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
				<div class="col-xs-12">&nbsp;</div>
			</div>

			<div class="form-group">
				<div class="col-sm-5">
					<input type="submit" value="Save" class="btn btn-primary" id="stnDtBtnId"/>
					<input type="button" value="Close" class="btn btn-danger" onclick="javascript:popStoneInwCancelBtn()">
					<form:input type="hidden" id="stnLooseDtEntryId" path="id" />
					<form:input type="hidden" path="sieve" />
					<input type="hidden" id="pInvNo" name="pInvNo" />
					<form:input type="hidden" path="department.id" />
					<form:input type="hidden" path="uniqueId" />
					<form:input type="hidden" path="amount" />
					<input type="hidden" id="vCarat" name="vCarat" />
					<input type="hidden" id="vStone" name="vStone" />
					<input type="hidden" id="sizeGroupStr" name="sizeGroupStr" />
				</div>
			</div>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(e) {
		
		
		
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
			
		

		$(".stnLooseDttForm").validate({
			rules : {
				
				carat : {
					required : true,
					greaterThan : "0,0",
				},
		
		rate : {
			required : true,
			greaterThan : "0",
		}

			},

			messages : {
				carat : {
					greaterThan : "This field is required"
				},
				rate : {
					greaterThan : "This field is required"
				}
			
			},

		});

		setAmount();

	});
	
	
	
	function getPointer() {
		$.ajax({
			url : '/jewels/manufacturing/masters/designStone/stonePointer.html',
			type : "GET",
			data : {shape : $("#shape\\.id :selected").text(), size : $("#size").val()} ,
			success : function(data, textStatus, jqXHR) {
				$("#carat").val(parseFloat(Number(data) * $("#stone").val()).toFixed(3));
				$('#carat').trigger('onchange');
			
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});		
	}
</script>

<script src="/jewels/js/common/common.js"></script>