
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="form-group">
	<form:form commandName="jobRecLabDt" id="jobRecLabDtEnt"
		action="/jewels/manufacturing/transactions/jobRecLabDt/add.html"
		cssClass="form-horizontal jobRecLabDtEntForm">

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
					<td><form:checkbox path="pcsWise"  /></td>
					<td><form:checkbox path="perGramRate"  /></td>
					<td><form:checkbox path="percentWise"  /></td>
					<td><form:checkbox path="perCaratRate" /></td>
					<td><form:checkbox path="rLock"  /></td>

				</tr>

				<tr>
					<td colspan="12">
								<input type="submit" value="Save" class="btn btn-primary" /> 
								<form:input type="hidden" path="id" id="jobRecLabDtPk" />
								<input type="hidden" id="forLabJobRecMtId" name="forLabJobRecMtId" />
								<input type="hidden" id="forLabJobRecDtId" name="forLabJobRecDtId" /> 
					</td>
				</tr>
			</tbody>
		</table>

	</form:form>
</div>
<script type="text/javascript">

$(document).ready(
		function(e){
			
			$(".jobRecLabDtEntForm")
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
	
	
	if(!!$('#jobRecLabDtEnt #metal\\.id').val() && !!$('#jobRecLabDtEnt #labourType\\.id').val()){

				
		  $.ajax({
			  url:"/jewels/manufacturing/transactions/jobRecLabDt/labRateFromMaster.html?metalId="+$('#jobRecLabDtEnt #metal\\.id').val()
					  +"&labourId="+$('#jobRecLabDtEnt #labourType\\.id').val()
					   +"&jobRecLabDtId="+$('#jobRecLabDtEnt #jobRecLabDtPk').val()
					   +"&jobRecDtId="+$('#packDtPKId').val(),
			   type:"GET",
				datatype:"JSON",
			  success : function(data) {
				  
				  if(data !=''){
					  $( '#jobRecLabDtEnt #perCaratRate1').prop("checked", false );	
						
					  $( '#jobRecLabDtEnt #pcsWise1').prop("checked", false );
					  $( '#jobRecLabDtEnt #perGramRate1').prop("checked", false );
					  $( '#jobRecLabDtEnt #percentWise1').prop("checked", false );
					  			  
					  $.each(JSON.parse(data), function(key,value){
					
							
							if(key=='labourRate'){
								 $('#jobRecLabDtEnt #labourRate').val(value);
							}
							
							if(key=='perPcRate1'){
								if(value ==true){
									
									$( '#jobRecLabDtEnt #pcsWise1').prop( "checked", true );	
								}
								 
								
							}
							
							if(key=='perGramRate'){
								if(value==true){
									$( '#jobRecLabDtEnt #perGramRate1').prop( "checked", true );	
								}
								
								 
							}
							
							if(key=='percentage'){
								if(value==true){
									$( '#jobRecLabDtEnt #percentWise1').prop( "checked", true );	
								}
								
								
							}
							
							if(key=='perCaratRate'){
								if(value==true){
									$( '#jobRecLabDtEnt #perCaratRate1').prop( "checked", true );	
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