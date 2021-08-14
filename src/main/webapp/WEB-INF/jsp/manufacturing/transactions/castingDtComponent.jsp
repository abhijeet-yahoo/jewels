
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>



<div class="panel-body">


	 <div class="form-group" id="dsPCompId">
		<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12">
						
						<div>
							<table id="castCompTableId" data-toggle="table"
								data-toolbar="#toolbarCompDtCast" data-pagination="false"
								data-side-pagination="server"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-height="350">
								<thead>
									<tr class="btn-primary">
										<th data-field="component" data-align="left">Component</th>
										<th data-field="purity" data-align="left">Purity</th>
										<th data-field="color" data-sortable="true">Color</th>
										<th data-field="metalWt" data-sortable="true">MetalWt</th>
										<th data-field="qty" data-sortable="true">Qty</th>
										<th data-field="action2" data-align="center">Delete</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
	
			</div>
		</div>


		<div id="castCompDtEntry" style="display: block">
			<div id="rowId">
				<div class="form-group">
					<div class="form-group">
						<form:form commandName="castingCompDt" id="castingCompDtEntry"
							action="/jewels/manufacturing/transactions/castingCompDt/add.html"
							cssClass="form-horizontal castingCompDtEntry">

							<table class="line-items editable table table-bordered">
								<thead class="panel-heading">
									<tr  class="btn-warning">
										<th>Component</th>
										<th>MetalWt</th>
										<th>Qty</th>  
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><form:select path="component.id" class="form-control">
												<form:option value="" label="- Select Component -" />
												<form:options items="${componentMap}" />
											</form:select>
										</td>
										<td><form:input path="metalWt" id="compMetalWt" cssClass="form-control" /></td>
										<td><form:input path="qty" cssClass="form-control"  /></td>
									</tr>
									<tr>
										<td colspan="10">
											<input type="submit" value="Save" class="btn btn-primary" /> 
											<form:input type="hidden" path="id" /> 
											<input type="hidden" id="treeId" name="treeId" />
										</td>
									</tr>
								</tbody>
							</table>
						</form:form>
					</div>
				</div>
			</div>
		</div>
		






</div> <!-- ending the panel body -->



<script type="text/javascript">


 $(document).ready(function(){
	
	 $(".castingCompDtEntry").validate(
				{
					rules : {
						
						'component.id' : {
							required : true,
						},
						metalWt : {
							required : true,
							greaterThan : "0",
						}

					},
					highlight : function(element) {
						$(element).closest('.form-group').removeClass(
								'has-success').addClass('has-error');
					},
					unhighlight : function(element) {
						$(element).closest('.form-group').removeClass(
								'has-error').addClass('has-success');
					},
					
					messages : {
						metalWt : {
							greaterThan : "This field is required"
						}
					}
					
				});
	 
 })
 
 
 
 
 	$(document)
			.on(
					'submit',
					'form#castingCompDtEntry',
					function(e) {
						
						$('#treeId').val($('#treeNo').val());

						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,

									success : function(data, textStatus, jqXHR) {
										
										if(data === "-1"){
											popCastingCompDt();	
											
											$('#component\\.id').val('');
											$('#purity\\.id').val('');
											$('#color\\.id').val('');
											$('#compMetalWt').val('0.0');
											$('#qty').val('0.0');
											
										}else{
											alert("error contact admin");
										}
										

									},
									error : function(jqXHR, textStatus, errorThrown) {

									}

								});
						
						e.preventDefault(); //STOP default action

					});


 
 
 	


</script>


