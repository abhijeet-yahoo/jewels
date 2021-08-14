<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalDesign.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%;">

<div class="panel-heading" 
	style="font-size: 18px;">Best Seller
</div>


<div class="panel-body"  >

			<div class="col-sm-12">
				<div id="betweenPeriod" class="col-sm-4" >	
					
					<label for="inputLabel4" class=".col-lg-2 text-right">Between Period </label>
					
						<div class="panel panel-primary" style="width: 100%;border-color: gray;">
						
						
							<div class="row">
								<div class="col-sm-12">
									<div class="col-lg-6 col-sm-6" >
										<label for="inputLabel6" class=".col-lg-6 text-right">From Dt : </label>
									</div>
									
									<div class="col-lg-6 col-sm-6" >
										<label for="inputLabel4" class=".col-lg-6 text-right">To Dt : </label>
									</div>
									
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-sm-12">
								
									<div class="col-lg-6 col-sm-6" >
										<input type="text" class="form-control" name ="fromBetDate" id="fromBetDate" />
									</div>
									
									<div class="col-lg-6 col-sm-6" >
										<input type="text" class="form-control" name ="toBetDate" id="toBetDate" />
									</div>
								
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
							
						</div>
							
					</div>	
					
					<div class="col-sm-6">
						<div class="row">
							<div class="col-xs-12">&nbsp;</div>
						</div>
						<div class="col-sm-3">
							<label for="exportQtyLbl" >Export Qty : </label>
						</div>
						<div class="col-sm-3">
							<input type="number" class="form-control" name="exportQty" id="exportQty"  />
						</div>
					</div>
					
					
					
				</div>	
	
	<div class="col-sm-12">
	
		<!-- 	<div class="col-sm-3">
				<label>Category </label><span style="display:inline-block; width:4.4cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myCategoryModal" onclick="javascript:popCategory(0)" ></button>
				<div>
					<textarea  name="categTextBox" id="categTextBox" style="height: 2cm; width:7cm"  disabled="true" ></textarea> 
				</div>
			</div>
			 -->
			 
			<div class="col-sm-3">
				<label>Party</label><span style="display:inline-block; width:3.6cm; "></span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myPartyModal" onclick="javascript:popParty(0)" ></button>
				<div>
					<textarea  name="partyTextBox" id="partyTextBox" style="height: 2cm; width:7cm"  disabled="true" ></textarea>
				</div>
			</div>
			
			<div class="col-sm-3">
				<label>Category</label><span style="display:inline-block; width:3.6cm; "></span>
				   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myCategoryModal" onclick="javascript:popCategory(0)" ></button>
				<div>
					<textarea  name="categTextBox" id="categTextBox" style="height: 2cm; width:7cm"  disabled="true" ></textarea>
				</div>
			</div>	 
			
			<div class="col-sm-3">
				<label>Sub Category</label><span style="display:inline-block; width:3.6cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#mySubCategoryModal" onclick="javascript:popSubCategory(0)" ></button>
				<div>
					<textarea  name="subCategTextBox" id="subCategTextBox" style="height: 2cm; width:7cm"  disabled="true" ></textarea>
				</div>
			</div>
			
			<div class="col-sm-3">
				<label>Concept</label><span style="display:inline-block; width:3.6cm; "></span>
				    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myConceptModal" onclick="javascript:popConcept(0)" ></button>
				<div>
					<textarea  name="conceptTextBox" id="conceptTextBox" style="height: 2cm; width:7cm"  disabled="true" ></textarea>
				</div>
			</div>
			
			
			</div>
			
			<div class="col-sm-3">
				&nbsp;&nbsp;&nbsp;
				<input type="button" value="Load Data" class="btn btn-warning" onclick="javascript:popDesignDetails()" />
				<input type="hidden" name="pFromBetDate" id="pFromBetDate"  value=""  />  
				<input type="hidden" name="pToBetDate" id="pToBetDate"  value=""  />
				<input type="hidden" name="pExportQty" id="pExportQty"  value=""  />
				<input type="hidden" name="pSubCategoryIds" id="pSubCategoryIds" value=""  />
				<input type="hidden" name="pCategoryIds" id="pCategoryIds" value=""  />
				<input type="hidden" name="pConceptIds" id="pConceptIds" value=""  />
				<input type="hidden" name="pPartyIds" id="pPartyIds" value=""  />
			</div>
			
			
			
			
				
		
	<div id="designDetailsTable">
		  
		  
		  	<div class="col-xs-12">
				<hr style="width: 100%; color: red; height: 3px; background-color: darkblue;" />
		    </div>
		  
			<div class="container-fluid">
					<div class="row" id="forDesignDetailsTab">
						
						<div id="odDivId" class="col-xs-10">
						
						<div class="col-xs-3">
							<input type="search" id="searchDesignFilterList"  class="search form-control" placeholder="Search" />
						</div>
						
						<div>&nbsp;</div>
						
							<div>
								<table id="designDetailTableId"  data-toggle="table"
									data-toolbar="#toolbar" 
									data-side-pagination="server"
									data-page-list="[5, 10, 20, 50, 100, 200, 500, 1000]" data-height="350"   >
									<thead>
										<tr class="btn-primary">
											<th data-field="state" data-checkbox="true"></th>
											<th data-field="party" data-align="left">Party</th>
											<th data-field="styleNo" data-align="left">Design No</th>
											<th data-field="exportQty" data-align="left">Export Qty</th>
	
										</tr>
									</thead>
								</table>
							</div>
								
								
								
						</div>
		
		
		
						<div id="odImgDivId" class="col-xs-2 center-block">
							
							<div style="height:52px">&nbsp;</div>
							<div class="panel panel-primary" style="width:100%; height:267px">
								<div class="panel-body">
									<div style="width:100%; height:142px">
										<div class="row center-block">
											<span id='styleImgId'></span> 
											<a id="oImgHRId" href="" data-lighter>
												<img id="ordImg" class="img-responsive" src="/jewels/uploads/manufacturing/blank.png" />
											</a>
										</div>
									</div>
									<div style="height:15px">&nbsp;</div>
									<div class="pull-left">
										<table id='stoneDtlsId' style="width:100%"
											class="line-items editable table table-bordered">
										</table>
									</div>
								</div>
							</div>
						</div>
						
					</div>
		
					
			</div>
			
			
			<div class="row">
			   <div class="col-xs-12">&nbsp;</div>
			</div>
				
			
	 </div>	
		
		<div class="col-sm-2" >
					<input type="button" value="Generate Report" name="genRpt" id="genRpt" class="btn btn-success" style="text-decoration:inherit;" onclick="javascript:getReport(event)"/>
					<input type="hidden" name="xml" id="xml" value="${xml}"  />
					
					
		 </div>
	
	
	</div>
	
	<div style="display: none">
		<form:form target="_blank"
			action="/jewels/manufacturing/masters/reports/download/BestSellerReport.html"
			cssClass="form-horizontal orderFilter">
				<div class="form-group">
					<div class="col-lg-12 col-sm-12" style="text-align: center">
						<input type="submit" value="Generate Report" class="btn btn-primary" id="genDesignReport"/>
						<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
					</div>
				</div>
		</form:form>
	</div>
	
	
	
</div>

<script type="text/javascript">


$(document).ready(function(e){
	
	
	 $("#fromBetDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		
		$("#toBetDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		
		
		
		//----------category Search-------//
		
		$("#searchCategory").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#categoryIdTbl tr").each(function(index) {
		
		        if (index != 0) {
		        	
		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();  
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
	 
	
	
	
		//----------subcategory Search-------//
		
		$("#searchSubCategory").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#subCategoryIdTbl tr").each(function(index) {
		
		        if (index != 0) {
		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();
		            var subCatId = $row.find('td:eq(2)').text();
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0 && subCatId.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
	
		
		
		//----------party Search-------//
		
		$("#searchParty").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#partyIdTbl tr").each(function(index) {
		
		        if (index != 0) {
		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();
		            var subCatId = $row.find('td:eq(2)').text();
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0 && subCatId.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
		
		
		//----------concept Search-------//
		
		$("#searchConcept").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#conceptIdTbl tr").each(function(index) {
		
		        if (index != 0) {
		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();
		            var conceptId = $row.find('td:eq(2)').text();
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0 && conceptId.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
		
		
		
	
	
});



var categStatus = "false";
function popCategory(val) {
	if(val === 0){
		if(categStatus === 'false'){
			$("#categoryIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/category/listing.html?opt=3",
			});
			categStatus = true;
		}
	}else{
		$("#categoryIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/category/listing.html?opt=3",
		});
		$('#categTextBox').val('');
	}
	
	
		
}


function popCategSave(){
	
	$("#myCategoryModal").modal("hide");
	var	data = $('#categoryIdTbl').bootstrapTable('getSelections');
	var	categNm = $.map(data, function(item) {
			return item.name;
		});
	
	var tempRes = categNm.toString().replace(/,/g, "\n");
	$('#categTextBox').val(tempRes);
}
 



//--subcategory-----//

var subCategStatus = "false";
function popSubCategory(val) {
	if(val === 0){
		if(subCategStatus === 'false'){
			$("#subCategoryIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/subCategory/listing.html?opt=3",
			});
			subCategStatus = true;
		}
	}else{
		$("#subCategoryIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/subCategory/listing.html?opt=3",
		});
		$('#subCategTextBox').val('');
	}
	
	
		
}




function popSubCategSave(){
	
	$("#mySubCategoryModal").modal("hide");
	var	data = $('#subCategoryIdTbl').bootstrapTable('getSelections');
	var	subCategNm = $.map(data, function(item) {
			return item.name;
		});
	
	var tempRes = subCategNm.toString().replace(/,/g, "\n");
	$('#subCategTextBox').val(tempRes);
}


// party

var partyStatus = "false";
function popParty(val) {
	if(val === 0){
		if(partyStatus === 'false'){
			$("#partyIdTbl").bootstrapTable('refresh', {
			//	url : "/jewels/manufacturing/masters/party/listing.html?opt=1",
				url : "/jewels/manufacturing/masters/party/report/listing.html"
			});
			partyStatus = true;
		}
	}else{
		$("#partyIdTbl").bootstrapTable('refresh', {
		//	url : "/jewels/manufacturing/masters/party/listing.html?opt=1",
			url : "/jewels/manufacturing/masters/party/report/listing.html"
		});
		$('#partyTextBox').val('');
	}
	
	
		
}


function popPartySave(){
	
	$("#myPartyModal").modal("hide");
	var	data = $('#partyIdTbl').bootstrapTable('getSelections');
	var	partyNm = $.map(data, function(item) {
			return item.name;
		});
	
	var tempRes = partyNm.toString().replace(/,/g, "\n");
	$('#partyTextBox').val(tempRes);
}


/// concept

var conceptStatus = "false";
function popConcept(val) {
	if(val === 0){
		if(conceptStatus === 'false'){
			$("#conceptIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/concept/listing.html?opt=3",
			});
			conceptStatus = true;
		}
	}else{
		$("#conceptIdTbl").bootstrapTable('refresh', {
			url : "/jewels/manufacturing/masters/concept/listing.html?opt=3",
		});
		$('#conceptTextBox').val('');
	}
	
	
		
}


function popConceptSave(){
	
	$("#myConceptModal").modal("hide");
	var	data = $('#conceptIdTbl').bootstrapTable('getSelections');
	var	conceptNm = $.map(data, function(item) {
			return item.name;
		});
	
	var tempRes = conceptNm.toString().replace(/,/g, "\n");
	$('#conceptTextBox').val(tempRes);
}




function popDesignDetails(){
	
	
	var data = "";
	var ids = "";
	
	 data = $('#categoryIdTbl').bootstrapTable('getSelections');
	 ids = $.map(data, function(item) {
		return item.id;
	}); 
	$('#pCategoryIds').val(ids); 

	data = $('#subCategoryIdTbl').bootstrapTable('getSelections');
	ids = $.map(data, function(item) {
		return item.id;
	});
	$('#pSubCategoryIds').val(ids);
	
	data = $('#conceptIdTbl').bootstrapTable('getSelections');
	ids = $.map(data, function(item) {
		return item.id;
	});
	$('#pConceptIds').val(ids);
		
	
	data = $('#partyIdTbl').bootstrapTable('getSelections');
	ids = $.map(data, function(item) {
		return item.id;
	});
	$('#pPartyIds').val(ids);
	
	
	$('#pFromBetDate').val($("#fromBetDate").val());
	$('#pToBetDate').val($("#toBetDate").val());
	$('#pExportQty').val($("#exportQty").val());
	
	
	if(!!$('#pFromBetDate').val() && !!$('#pToBetDate').val() && !!$('#pExportQty').val() && !!$('#pSubCategoryIds').val()){
				
		$("#designDetailTableId")
		.bootstrapTable(
				'refresh',
				{
					
					url : "/jewels/manufacturing/masters/reports/designFilter/bestSellerListing.html?&pSubCategoryIds="+$('#pSubCategoryIds').val()
							+"&pCategoryIds="+$('#pCategoryIds').val()
							+"&pConceptIds="+$('#pConceptIds').val()
							+"&pPartyIds="+$('#pPartyIds').val()
						    +"&pExportQty="+$('#pExportQty').val()
							+"&pFromBetDate="+$('#pFromBetDate').val()
							+"&pToBetDate="+$('#pToBetDate').val(),
							
						
				});
		
		
		
	}else{
		displayMsg(this,null,' Error :Select Date,Export Qty,Sub Category,Category,Party,Concept, can not blank');
		
	}
	

	
}


$('#designDetailTableId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
	
			var defImage = jQuery.parseJSON(JSON.stringify(row)).defaultImage;
			
			if ((defImage != 'null') && ($.trim(defImage).length > 0)) {
				$('#ordImg').attr('src', '/jewels/uploads/manufacturing/design/' + defImage);
				$('#oImgHRId').attr('href', '/jewels/uploads/manufacturing/design/' + defImage);
			}
			
	});





$("#searchDesignFilterList").on("keyup", function() {
    var value = $(this).val();
    
    $("#designDetailTableId tr").each(function(index) {

        if (index != 0) {
        	
            $row = $(this);
            var id1 = $row.find('td:eq(1)').text();
            var id2 = $row.find('td:eq(2)').text();
            
            
            if (id1.toLowerCase().indexOf(value.toLowerCase()) != 0 && id2.toLowerCase().indexOf(value.toLowerCase()) != 0) {
                $(this).hide();
            }
            else {
                $(this).show();
               
            }
        }
    });
}); 


function getReport(e){
	
	$.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });
	$('#genRpt').attr('disabled', 'disabled');
	
	var data = $('#designDetailTableId').bootstrapTable('getSelections');
	var styleids = $.map(data, function(item) {
		return item.styleid;
	}); 
 

	
	$.ajax({
		url:"/jewels/manufacturing/masters/reports/bestSellerReport.html",
		type:'POST',
		data: {
			tempStyleId:styleids+"",
			startdate:$('#pFromBetDate').val(),
			enddate:$('#pToBetDate').val(),
			expqty:$('#pExportQty').val(),
			scategcond:$('#pSubCategoryIds').val(),
			categcond:$('#pCategoryIds').val(),
			partycond:$('#pPartyIds').val(),
			conceptcond:$('#pConceptIds').val(),
			xml:$('#xml').val()
		}, 
		success: function(data){
			
			$.unblockUI();
			
			 if(data === '1'){
			 displayMsg(this, null, 'Select Atleast One Record !');
			 }else if(data === '-2'){
				 displayMsg(this, null, 'Error Occoured , contact admin !');
			 }else{
				 $('#timeValCommonPdf').val(data);
				 $("#genDesignReport").trigger('click'); 	
			 }
			 
			 $('#genRpt').removeAttr('disabled');

		},
		error:function(data){
			alert("error"+data);
			
		}
			
			
			
		});
	
	e.preventDefault();
	
	
	
}




</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>


