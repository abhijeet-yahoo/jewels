<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<form:form commandName="quotLabDt" id="quotLabDtEnt"
		action="/jewels/manufacturing/transactions/quotLabDt/add.html"
		cssClass="form-horizontal quotLabDtEntForm">

		<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
				<tr class="btn-warning">
					<th>Metal</th>
					<th>Labour</th>
					<th>Rate</th>
					<th>PerPcs</th>
					<th>%Gram</th>
					<th>Percent</th>
					<th>PerCarat</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				<td>
						<form:select path="metal.id" class="form-control"  onchange="javascript:popLabRateFromMasterItem()">
							<form:option value="" label="- Select Metal -" />
							<form:options items="${metalMap}" />
						</form:select>
					</td>
					<td>
						<form:select path="labourType.id" class="form-control"  onchange="javascript:popLabRateFromMasterItem()">
							<form:option value="" label="- Select LabourType -" />
							<form:options items="${labourTypeMap}" />
						</form:select>
					</td>
					<td><form:input path="labourRate" type="number" cssClass="form-control" /></td>
					<td><form:checkbox path="pcsWise"  /></td>
					<td><form:checkbox path="gramWise" /></td>
					<td><form:checkbox path="percentWise" /></td>
					<td><form:checkbox path="perCaratRate"/></td>

				</tr>

				<tr>
					<td colspan="12">
						<input type="submit" value="Save" class="btn btn-primary" />
						<input type="button" value="Cancel" class="btn btn-danger" onclick="javascript:popLabCancel()"/>
						<form:input type="hidden" path="id" id="quotLabDtPk" /> 
						<input type="hidden" id="forLabQuotMtId" name="forLabQuotMtId" /> 
						<input type="hidden" id="forLabQuotDtId" name="forLabQuotDtId" />
					</td>
				</tr>
			</tbody>
		</table>

	</form:form>
</div>


<script type="text/javascript">

$(document).ready(
		function(e){
			$(".quotLabDtEntForm")
					.validate(
							{	
							  rules : {
									'metal.id' : {
										required : true,
									},
									'labourType.id' : {
										required : true,
									},
									
									labourRate : {
										required : true,
										greaterThan : "0,0",
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
									labourRate : {
										greaterThan : "This field is required"
									}
								},
								

							});


		});
		



function popLabRateFromMasterItem(){
	
	
	if(!!$('#quotLabDtEnt #metal\\.id').val() && !!$('#quotLabDtEnt #labourType\\.id').val()){
				
		  $.ajax({
			  url:"/jewels/manufacturing/transactions/quotLabDt/labRateFromMaster.html?metalId="+$('#quotLabDtEnt #metal\\.id').val()
					  +"&labourId="+$('#quotLabDtEnt #labourType\\.id').val()
					   +"&quotLabDtId="+$('#quotLabDtEnt #quotLabDtPk').val()
					   +"&quotDtId="+$('#quotDtPKId').val(),
			   type:"GET",
				datatype:"JSON",
			  success : function(data) {
				  
				  $( '#quotLabDtEnt #perCaratRate1').prop("checked", false );	
					
				  $( '#quotLabDtEnt #pcsWise1').prop("checked", false );
				  $( '#quotLabDtEnt #gramWise1').prop("checked", false );
				  $( '#quotLabDtEnt #percentWise1').prop("checked", false );
				  			  
				  $.each(JSON.parse(data), function(key,value){
				
						
						if(key=='labourRate'){
							 $('#quotLabDtEnt #labourRate').val(value);
						}
						
						if(key=='perPcRate'){
							if(value ==true){
								
								$( '#quotLabDtEnt #pcsWise1').prop( "checked", true );	
							}
							 
							
						}
						
						if(key=='perGramRate'){
							if(value==true){
								$( '#quotLabDtEnt #gramWise1').prop( "checked", true );	
							}
							
							 
						}
						
						if(key=='percentage'){
							if(value==true){
								$( '#costLabDtItemEnt #percentWise1').prop( "checked", true );	
							}
							
							
						}
						
						if(key=='perCaratRate'){
							if(value==true){
								$( '#quotLabDtEnt #perCaratRate1').prop( "checked", true );	
							}
							 
						}
					  
					  
					  
					  
					  
					  
					  
					  
					  
					  
						
				    });
				
			 }
			  
		  });
	
	}
	
	
	
}


</script>