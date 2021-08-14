<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%;height :15cm;border-style: dotted;">

	

	<div class="panel-heading" >
		<span style="font-size: 18px;">Export Job Catalog </span>
	</div>
	
	<div class="panel-body">
	
	<div class="col-xs-12">
	
		<div class="col-xs-4">
		
	
			<div class="col-xs-1"></div>
			
			<div class="col-xs-12">
				<div class="form-group" id="dsPId">
					<div class="container-fluid">
						<div class="row">
							
							
								<div class="row">
									<div class="col-xs-12">&nbsp;</div>
								</div>
							
								<input type="search" id="searchEJCatalog"  class="search form-control" placeholder=" search " />
								
								<div class="row">
									<div class="col-xs-12">&nbsp;</div>
								</div>
								
								
								
								<!--//---------- EJCatalog list------------//-->
							
							
							
							<div id="EJCatalogTableDivId" >
								<table id="EJCatalogIdTbl" data-toggle="table" 
									data-click-to-select="false" data-side-pagination="server" 
									data-striped="true" data-height="350" >
										<thead>
											<tr class="btn-success" >
												<th data-field="state" data-checkbox="true" ></th>
												<th  data-field="itemNo" data-align="left" data-sortable="true" style="font-weight: bolder;">Job Catalog</th>
											</tr>
										</thead>
								</table> 
							</div>
							
								<div class="row">
									<div class="col-xs-12">&nbsp;</div>
								</div>
							
							<div class="col-xs-12">
								<div class="col-xs-6">
									<input type="text" id="itemNoId" name="itemNoId" class="form-control" placeholder="Enter Item No"/>
								</div>
								<div class="col-xs-3">
									<input type="button" id="saveBtn" name="saveBtn"  value="Add" class="btn btn-warning" style="width: 2cm" onclick="javascript:addItemNo()"  />
								</div>
								
								<div class="col-xs-3">
									<input type="button" id="deleteSel" name="deleteSel" value="Delete" class="btn btn-danger" style="width: 2cm" onclick="javascript:deleteItemNo(event)" />
								</div>
							</div>
							
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
							 
							<!-- 
								<div class="col-xs-12">
									<div class="col-xs-8"></div>
									<div class="col-xs-4">
										<input type="button" id="deleteSel" name="deleteSel" value="Delete" class="btn btn-danger" style="width: 2cm" onclick="javascript:deleteItemNo(event)" />
									</div>
								</div> 
							-->
						
					</div>
				</div>
			  </div>
			</div>
	
	
	</div>
	
	<div class="col-xs-8">
	
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div><div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
	
	<div class="panel panel-danger"  style="border-style: dotted; width: 15cm">
	
		<div class="panel-body">
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
	
		
			<div>
				<div class="col-xs-6">
					<input type="button" id="exportJobCatalog" name="exportJobCatalog" value="Catalog" class="btn btn-info" style="width: 4cm" onclick = "javascript:popReport(this.id)" />
				</div>
				
				<div class="col-xs-6">
					<input type="button" id="exportJobCostSheet" name="exportJobCostSheet" value="Cost Sheet" class="btn btn-info" style="width: 4cm" onclick = "javascript:popReport(this.id)" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
			<div>
				<div class="col-xs-6">
					<input type="button" id="exportJobAnnexure" name="exportJobAnnexure" value="Annexure" class="btn btn-info" style="width: 4cm" onclick = "javascript:popReport(this.id)" />
				</div>
				<div class="col-xs-6">
					<input type="button" id="exportJobCostingTag" name="exportJobCostingTag" value="Tag" class="btn btn-info" style="width: 4cm" onclick = "javascript:popReport(this.id)" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
		
	
		</div>
		
	 </div>	
	 
	 
	 
		  <div style="display: none">
				<form:form target="_blank"
					action="/jewels/manufacturing/masters/reports/download/exportJobCatalog.html"
					cssClass="form-horizontal orderFilter">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Report" class="btn btn-primary" id="genReportss"/>
								<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
							</div>
						</div>
				</form:form>
			</div>
			
			
			<div style="display: none">
				<form:form
					action="/jewels/manufacturing/masters/reports/download/excelReport/exportJobTag.html"
					cssClass="form-horizontal orderFilterExcel">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Excel Report" class="btn btn-primary" id="genExcelReportss"/>
							</div>
						</div>
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
			
					
					//----------ItemNo Search-------//
					
					$("#searchEJCatalog").on("keyup", function() {
					    var value = $(this).val();
					    
					    $("#EJCatalogIdTbl tr").each(function(index) {
					
					        
			
					            $row = $(this);
					            var id = $row.find('td:eq(1)').text();  
					            
					            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
					                $(this).hide();
					            }
					            else {
					                $(this).show();
					            }
					        
					    });
					});
				    
			
					
			deleteAllItemNo();
	
	});
	
	
	
	function refreshEJCatlogTable(){
		$("#EJCatalogIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/reports/exportJobCatalog/listing.html"
		});
	}
	
	

	
	function addItemNo(){
	
		//$('#tempItemno').val(ids);
	
		
		var itemNo = $.trim($("#itemNoId").val());
		
		if(!itemNo){
			displayMsg(this, null, 'Please Enter Item No');
		}else{
			
			/* 
			$.ajax({
					url:'/jewels/manufacturing/masters/reports/add/itemNo.html?itemNo='+$("#itemNoId").val(),
					type:'GET',
					success : function(data){
						if(data === '-2'){
							displayMsg(this, null, 'Item No  '+ itemNo +'  Already Present in The List');
						}else{
							refreshEJCatlogTable();	
						}
						
						$("#itemNoId").val('');
						
					}
					
				}); */
			 var ids = $.map( $("#EJCatalogIdTbl").bootstrapTable('getData'), function (row) {
	             return row.itemNo;
	         });
			 
			 ids = ids+'';
			 var temp = ids.split(",");
			 
			 
		 var status = true
			 
			 for(i=0;i<temp.length;i++){
				 
				 if(temp[i] === itemNo){
					 status = false;
				 }
				 
			 }
				
				
		
			if(status){
				
				 $("#EJCatalogIdTbl").bootstrapTable('insertRow', {
		              index: 0,
		              row: {
		            	  
		            	  itemNo:itemNo,
		            	  
		                  
		              }
		          });
				 
				
			}else{
				displayMsg(this, null, 'Item No  '+ itemNo +'  Already Present in The List');
				
			}
		 
			 $("#itemNoId").val('');
		 
		 
		}

	}	

	
	
	function deleteAllItemNo(){
		
	/* 	$.ajax({
			url : '/jewels/manufacturing/masters/reports/deleteAll/itemNo.html',
			type:'GET',
			success : function(data) {
				refreshEJCatlogTable();
			}
		}) */
		
		
		$('#EJCatalogIdTbl').empty();
		
	}
	
	
	
	function deleteItemNo(e){
		
		var data 	= $('#EJCatalogIdTbl').bootstrapTable('getSelections');
		var	itemNo 	= $.map(data, function(item) {
						return item.itemNo;
					  });

		if(itemNo.length > 0){
			
			displayDlg(
					e,
					'javascript:delItemNo();',
					'Delete',
					'Do you want to Delete The Selected Record  ?',
					'Continue');
			
		}else{
			displayMsg(this, null, 'Please Select Atleast 1 Record To Delete');
		}
		
		
		
	}
	
	
	 function delItemNo(){
		
		/*  $("#modalDialog").modal("hide");
		 
		var data 	= $('#EJCatalogIdTbl').bootstrapTable('getSelections');
		var	itemNo 	= $.map(data, function(item) {
						return item.itemNo;
					  });

		 $.ajax({
			url:'/jewels/manufacturing/masters/reports/delete/itemNo.html?itemNos='+itemNo,
			type:'GET',
			success:function(data){
				alert(data);
				refreshEJCatlogTable();
			}
		})	 */
		
		
		$("#modalDialog").modal("hide");
		 var ids = $.map( $("#EJCatalogIdTbl").bootstrapTable('getSelections'), function (row) {
             return row.itemNo;
         });
		 
	
		 $("#EJCatalogIdTbl").bootstrapTable('remove', {
	            field: 'itemNo',
	            values: ids
	        }); 
		 
	} 
	
	
	 
	 
	 
	 function popReport(btnId){
	 
		var data 	= $('#EJCatalogIdTbl').bootstrapTable('getData');
		var	itemNo 	= $.map(data, function(item) {
					  var vItem = "''"+item.itemNo+"''";
							return vItem;
					  });
		
		
		  $.ajax({
			 url:'/jewels/manufacturing/masters/reports/view/rept.html?itemNo='+itemNo
					 +"&btnId="+btnId,
			 type:'GET',
			success:function(data){
				
				
				
				if(data.indexOf("$") != -1){
					
					var tempVal = data.split("$");
					
					if(tempVal[0] === '-1'){
						$('#timeValCommonPdf').val(tempVal[1]);
						$("#genReportss").trigger('click');
					}else{
						 alert("Error , Contact admin");
					}
					
				}else if(data === "-6"){
					$("#genExcelReportss").trigger('click');
				}else{
					alert("error occoured,contact admin");
				}
				
			}
		 
		 }) 
		 
		 
	 }
	 
	 
	 
	 
	 
	

</script>



<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">
<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>
<script src="<spring:url value='/js/common/design.js' />"></script>
<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>
<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />



