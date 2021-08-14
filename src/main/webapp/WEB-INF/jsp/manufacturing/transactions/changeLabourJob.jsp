<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

 <div class="panel-body">


	

	 <div id="labTypeDivIdf" class="col-xs-6">


				<div class="container-fluid">
					<div class="row">
						
							<div>
								<table id="labJobTypeId" data-toggle="table" data-toolbar="#toolbarLI"
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
							
							<input type="hidden" id="iCostMtJobId" name="iCostMtJobId" />
					</div>
				</div>
			
			
			
			
			
				<div class="row">
					<div class="form-group">
						<div class="col-xs-1" >
							<form:form commandName="costJobLabDt" id="costJobLabDttId"
								action="/jewels/manufacturing/transactions/changeLabour/JobRate/setRate.html"
								cssClass="form-horizontal transferOfChgLabJobRate">
			
								<table class="table">
									<tbody>
										<tr>
											<td class="col-xs-1" >
												<input type="submit" value="Save" class="btn btn-primary" />
												<input type="hidden" id="labJobIdPk" name="labJobIdPk" />
												<input type="hidden" id="labJobRate" name="labJobRate" />
												<input type="hidden" id="mtIdCostJob" name="mtIdCostJob" />
											</td>
										</tr>
									</tbody>
								</table>
								
							</form:form>
						</div>
					</div>
				</div>
			
			
			
			


	</div>



 </div>
 
 
 
 <script type="text/javascript">

$(document).ready(function(e) {
		
		$('#cJobLabour').on('click', function() {
			popLabourType();
		});
		
		
		$('#iCostMtJobId').val($('#costJobMtId').val()); // for stoneChange
		
		//---------//--Labour Type Inline Editing---//
		
		
		 $("#labJobTypeId").on("dblclick", "td:not(.active)", function () {
				
			    var $this = $(this);
			    
			    var $textbox = $("<input>", { type: "text",value: $this.addClass("active").text() });
			    $this.html($textbox);
			    $textbox.select();
			    $textbox.focus();
			    
			});
			 
			 
			 var stnTblRow = -1;
			 
				 $('#labJobTypeId').bootstrapTable({})
						.on(
								'click-row.bs.table',

								function(e, row, $element) {

									stnTblRow = $element.attr('data-index');
	       
							}); 
								
								
							
			 

			$("#labJobTypeId").on("blur", "input:text", function () {        
			    var $this = $(this);
			    $this.parent().removeClass("active").text($this.val());
			    
			    $("#labJobTypeId").bootstrapTable('updateRow', {
					index : stnTblRow,
					row : {
						state : true,
						rate : $this.val(),

					}
				}); 
			    
			    
			    
			}); 
				
		
		
		
		
	})
	
	
	
	
	
	
	
	//-------------Change Labour Type Rate ------//-------//
	
	
	$(document).on('submit', 'form#costJobLabDttId', function(e) {
		
		var data =  $('#labJobTypeId').bootstrapTable('getSelections');
		
		var idss = $.map(data, function(item) {
			return item.id;
		});
		
		var hRate = $.map(data, function(item) {
			return item.rate;
		});
	
		$('#labJobIdPk').val(idss);
		$('#labJobRate').val(hRate);
		$('#mtIdCostJob').val($('#iCostMtJobId').val());
		
		
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
 
 
 
 
 
 
 
 
 
 