
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<form:form commandName="packLabDt" id="packLabDtEnt"
		action="/jewels/marketing/transactions/packLabDt/add.html"
		cssClass="form-horizontal packLabDtEntForm">

	<table class="line-items editable table table-bordered">
			<thead class="panel-heading">
				<tr class="btn-primary" class="panel-heading">
					<th>Metal</th>
					<th>Labour</th>
					<th>Rate</th>
					<th>Per Pcs</th>
					<th>Per Gram</th>
					<th>Percent</th>
					<th>Per Carat</th>
					<th>Lock</th>
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
						<form:select path="labourType.id" class="form-control" onchange="javascript:popLabRateFromMasterItem()">
							<form:option value="" label="- Select LabourType -" />
							<form:options items="${labourTypeMap}" />
						</form:select>
					</td>
					<td><form:input path="labourRate"  cssClass="form-control" onchange="javascript:lockLabRecord()"/></td>
					<td><form:checkbox path="perPcRate"  /></td>
					<td><form:checkbox path="perGramRate"  /></td>
					<td><form:checkbox path="percentage"  /></td>
					<td><form:checkbox path="perCaratRate" /></td>
					<td><form:checkbox path="rLock"  /></td>

				</tr>

				<tr>
					<td colspan="12">
								<input type="submit" value="Save" class="btn btn-primary" /> 
								<form:input type="hidden" path="id" id="packLabDtPk" />
								<input type="hidden" id="forLabPackMtId" name="forLabPackMtId" />
								<input type="hidden" id="forLabPackDtId" name="forLabPackDtId" /> 
					</td>
				</tr>
			</tbody>
		</table>

	</form:form>
</div>
<script type="text/javascript">

$(document).ready(
		function(e){
			
			$(".packLabDtEntForm")
					.validate(
							{	
							  rules : {
									'labourType.id' : {
										required : true,
									},
									
									labourRateItem : {
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
									labourRateItem : {
										greaterThan : "This field is required"
									}
								},
								

							});


		});
		
		
		
function popLabRateFromMasterItem(){
	
	
	if(!!$('#packLabDtEnt #metal\\.id').val() && !!$('#packLabDtEnt #labourType\\.id').val()){

				
		  $.ajax({
			  url:"/jewels/marketing/transactions/packLabDt/labRateFromMaster.html?metalId="+$('#packLabDtEnt #metal\\.id').val()
					  +"&labourId="+$('#packLabDtEnt #labourType\\.id').val()
					   +"&packLabDtId="+$('#packLabDtEnt #packLabDtPk').val()
					   +"&packDtId="+$('#packDtPKId').val(),
			   type:"GET",
				datatype:"JSON",
			  success : function(data) {
				  
				  if(data !=''){
					  $( '#packLabDtEnt #perCaratRate1').prop("checked", false );	
						
					  $( '#packLabDtEnt #perPcRate1').prop("checked", false );
					  $( '#packLabDtEnt #perGramRate1').prop("checked", false );
					  $( '#packLabDtEnt #percentage1').prop("checked", false );
					  			  
					  $.each(JSON.parse(data), function(key,value){
					
							
							if(key=='labourRate'){
								 $('#packLabDtEnt #labourRate').val(value);
							}
							
							if(key=='perPcRate'){
								if(value ==true){
									
									$( '#packLabDtEnt #perPcRate1').prop( "checked", true );	
								}
								 
								
							}
							
							if(key=='perGramRate'){
								if(value==true){
									$( '#packLabDtEnt #perGramRate1').prop( "checked", true );	
								}
								
								 
							}
							
							if(key=='percentage'){
								if(value==true){
									$( '#packLabDtEnt #percentage1').prop( "checked", true );	
								}
								
								
							}
							
							if(key=='perCaratRate'){
								if(value==true){
									$( '#packLabDtEnt #perCaratRate1').prop( "checked", true );	
								}
								 
							}
						  
						  
						  
						  
						  
						  
						  
						  
						  
						  
							
					    });
				  }
				  
				
			 }
			  
		  });
	
	}
	
	
	
}

function lockLabRecord(){
	
	$( '#packLabDtEnt #rLock1').prop( "checked", true );	
}



</script>