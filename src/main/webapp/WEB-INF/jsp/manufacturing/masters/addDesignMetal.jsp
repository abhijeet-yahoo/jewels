<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<form:form commandName="designMetal" id="designMetalId"
	action="/jewels/manufacturing/masters/designMetal/add.html"
	cssClass="form-horizontal designMetalForm">

	<table class="line-items editable table table-bordered">
		
		
		<thead class="panel-heading">
			<tr class="btn-warning">
				<th>Part</th>
				<th>Purity</th>
				<th>Color</th>
				<th>Qty</th>
				<th>Per Pc Wax Wt</th>
				<th>Per Pc Silver Wt</th>
				
				
			</tr>
		</thead>
		<tbody>
			<tr>			
				<td><form:select path="partNm.id" class="form-control">
						<form:option value="" label="- Select PartNm -" />
						<form:options items="${lookUpMastMap}" />
					</form:select>
				</td>
				<td><form:select path="purity.id" class="form-control" onchange="javascript:calculateSilverWt(),calculateWaxWt();">
						<form:option value="" label="- Select Purity -"/>
						<form:options items="${purityMap}" />
					</form:select></td>
				<td><form:select path="color.id" class="form-control">
						<form:option value="" label="- Select Color -" />
						<form:options items="${colorMap}" />
					</form:select></td>
				<td>
					<form:input type="number" path="metalPcs" cssClass="form-control"  />
				</td>
				<td>
					<form:input type="number" path="perPcWaxWt" cssClass="form-control"  onchange="javascript:calculateSilverWt();"/>
				</td>
				
				<td>
					<form:input type="number" path="perPcSilverWt" cssClass="form-control"  onchange="javascript:calculateWaxWt();"/>
				</td>
			
				
				
			</tr>
			
		</tbody>
		
		<thead class="panel-heading">
			<tr class="btn-warning">
				<th>Rpt Wt</th>
				<th>Per Pc Metal Wt</th>
				<th>Metal Rate</th>
				<th>Per Gram Rate</th>
				<th>Loss %</th>
				<th>Metal Value</th>
				<th>Main Metal</th>
			
				
			</tr>
		</thead>
		
			<tbody>
			<tr>
				<td>
					<form:input type="rptWt" path="rptWt" cssClass="form-control" />
				</td>
				<td>
					<form:input type="number" path="perPcMetalWeight" cssClass="form-control" onchange="javascript:calculatePerPcWaxWt();" />
				</td>
				<td>
					<form:input type="number" path="metalRate" cssClass="form-control"  />
				</td>
				<td>
					<form:input type="number" path="perGramRate" cssClass="form-control"  />
				</td>
				<td>
					<form:input type="number" path="lossPerc" cssClass="form-control"  />
				</td>
				<td>
					<form:input type="number" path="metalValue" cssClass="form-control" />
				</td>
				<td>
					<form:checkbox  path="mainMetal"  />
				</td>
			
				
				
				
			</tr>
			
		</tbody>
		
		
		
			<tr>
				<td colspan="14">
					<input type="submit" value="Save" class="btn btn-primary" />
					<input type="button" value="Cancel" class="btn btn-danger" onclick="javascript:popCancelDesignMetal()"/>
					<form:input type="hidden" path="id" id="desMetalId"/>
					<input type="hidden" id="desPkId" name="desPkId" />
				</td>
			</tr>
		
	</table>
</form:form>



<script type="text/javascript">
	$(document).ready(function(e) {
		$(".designMetalForm").validate({
			rules : {
				'purity.id' : {
					required : true,
				},
				'color.id' : {
					required : true,
				},
				'partNm.id' : {
					required : true,
				},
				metalPcs : {
					required : true,
					greaterThan : "0,0",
				},
			},
			highlight : function(element) {
				$(element).closest('.form-group')
						.removeClass('has-success')
						.addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group')
						.removeClass('has-error')
						.addClass('has-success');
			},
			messages : {
				metalPcs : {
					greaterThan : "This field is required"
				}
			},
		});
	});

	
	
	function calculateSilverWt(){
		
		$.ajax({
			url:"/jewels/manufacturing/masters/purity/conversion.html?purityId="+$('#designMetalId #purity\\.id').val(),
			type:"GET",
			success:function(data){
				
				
				if (Number($('#designMetalId #perPcWaxWt').val()) > 0) {
					
					$('#designMetalId #perPcMetalWeight').val(parseFloat(Number($('#designMetalId #perPcWaxWt').val())*Number(data)).toFixed(3));
										
					/* var mtlWt =$('#designMetalId #metalWeight').val();
					
					var mtlPcs =$('#designMetalId #metalPcs').val();
					
					$('#designMetalId #metalWeight').val(parseFloat(mtlWt*mtlPcs).toFixed(3));
 */					

 					$('#designMetalId #perPcSilverWt').val(parseFloat($('#designMetalId #perPcWaxWt').val() * 10.5).toFixed(3));
					
					/*
					var silWt=parseFloat($('#waxWt').val() * 10.5).toFixed(3);
					$('#designMetalId #silverWt').val(parseFloat(silWt*mtlPcs).toFixed(3)); */
					
				}
				
				
				
				
			
				
			},
			async:false,
		})
		
		
		
		
	}
	
	
	
	function calculateWaxWt(){
		
		$.ajax({
			url:"/jewels/manufacturing/masters/purity/conversion.html?purityId="+$('#designMetalId #purity\\.id').val(),
			type:"GET",
			success:function(data){
				
				
				if (Number($('#designMetalId #perPcMetalWeight').val()) > 0) {
					
					$('#designMetalId #perPcWaxWt').val(parseFloat($('#designMetalId #perPcSilverWt').val() / 10.5).toFixed(3));
					
					$('#designMetalId #perPcMetalWeight').val(parseFloat(Number($('#designMetalId #perPcWaxWt').val())*Number(data)).toFixed(3));
					
					/* var mtlWt =$('#designMetalId #metalWeight').val();
					
					var mtlPcs =$('#designMetalId #metalPcs').val();
					
					$('#designMetalId #metalWeight').val(parseFloat(mtlWt*mtlPcs).toFixed(3));
 */					
										
				}
				
				
			},
			async:false,
		})
		
		
	}
	

	function calculatePerPcWaxWt(){

		$.ajax({
			url:"/jewels/manufacturing/masters/purity/conversion.html?purityId="+$('#designMetalId #purity\\.id').val(),
			type:"GET",
			success:function(data){
					
					$('#designMetalId #perPcWaxWt').val(parseFloat(Number($('#designMetalId #perPcMetalWeight').val()) / Number(data)).toFixed(3));

					$('#designMetalId #perPcSilverWt').val(parseFloat($('#designMetalId #perPcWaxWt').val() * 10.5).toFixed(3));										
				
				
			},
			async:false,
		})
		}
	
	
	
/* 	
	function calSlvrWt() {
		
		
		if (Number($('#waxWt').val()) > 0) {
			$('#silverWt').val(parseFloat($('#waxWt').val() * 10.5).toFixed(3));
			$('#grms').val(parseFloat($('#waxWt').val() * $('#waxWtConv').val()).toFixed(3));
		}
	}

	function calWaxWt() {
		if (Number($('#silverWt').val()) > 0) {
			$('#waxWt').val(parseFloat($('#silverWt').val() / 10.5).toFixed(3));
			$('#grms').val(parseFloat($('#waxWt').val() * $('#waxWtConv').val()).toFixed(3));
		}
	}
	
	 */
	
	
</script>