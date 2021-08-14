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


<div class="modal fade" id="myClientStyleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >

	<div class="modal-dialog modal-lg" style="width: 1250px;" role="document">
	
		<div class="modal-content">


			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>


				<h4 class="modal-title" id="myModalLabel">Client Style Details</h4>
			</div>

			<div class="modal-body">
			
			
	
		<div class="row">
				<div class="col-md-10">
				
					 <!-- <table id="clientStyleTblId" data-toggle="table"  data-search="true" data-toolbar="#toolbar"
								data-side-pagination="server"  data-maintain-selected="true"
								 data-pagination="true"	 data-height="350"  data-response-handler="responseHandler"> -->
								 <table id="clientStyleTblId" data-toggle="table"  data-search="true" data-toolbar="#toolbar"
								 data-maintain-meta-data="true" data-unique-id="id"  data-pagination="true"
									 data-height="350">
						<thead>
							<tr>
								<th data-field="state" data-checkbox="true">Select</th>
								<th data-field="barcode" data-sortable="false">Barcode</th>
								<th data-field="style" data-sortable="false">Style No</th>
								<th data-field="ktCol" data-sortable="false">Kt-Col</th>
								<th data-field="pcs" data-sortable="false">Pcs</th>
								<th data-field="trfQty" data-sortable="false" data-formatter="qtyFormatter">Ord Qty</th>
								<th data-field="grossWt" data-sortable="false">GrossWt</th>
								<th data-field="netWt" data-sortable="false">NetWt</th>
								<th data-field="qltyDesc" data-sortable="false">Qualitys</th>
								<th data-field="settDesc" data-sortable="false">Settings</th>
							</tr>
						</thead>
					</table>
				
			</div>

					<div id="odImgDivId" class="col-md-2 center-block">
						<div style="height: 52px">&nbsp;</div>
						<div class="panel panel-primary"
							style="width: 100%; height: 245px">
							<div class="panel-body">
								<div style="width: 80%; height: 168px">
									<div class="row center-block">
										<span id='styleImgId'></span>
										<div id="tempImgDivIdClient"></div>
									</div>
								</div>


							</div>
						</div>



					</div>
				</div>
		
		

			
			
		<div class="modal-footer">
		
			<input type="button" value="Save" id="orderSaveBtnId" class="btn btn-primary" onclick="javascript:orderQuotSave()"/>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					
					
					
				
				</div>			
			
			</div>
			
			
			</div>
			
			
			</div>
			
			</div>
			
	<script type="text/javascript">		
			
	/* 		 var $table = $('#clientStyleTblId')
	  var itemselections = []

	  function responseHandler(res) {
				 
       $.each(res.rows, function (i, row) {
	      row.state = $.inArray(row.id, itemselections) !== -1
	     
	    })
	    return res
	  }

	  $(function() {
	    $table.on('check.bs.table check-all.bs.table uncheck.bs.table uncheck-all.bs.table',
	      function (e, rowsAfter, rowsBefore) {
	    		    	
	        var rows = rowsAfter

	       if (e.type == 'uncheck-all') {
	          rows = rowsBefore
	        } 

	        var ids = $.map(!$.isArray(rows) ? [rows] : rows, function (row) {
	          return row.id
	        })
	        
	      
	        
	        var func = $.inArray(e.type, ['check', 'check-all']) > -1 ? 'union' : 'difference'
	        itemselections = window._[func](itemselections, ids)
	      
	      })
	  }) */
	  
	  

	  
	  
	  var TableRow;
$('#clientStyleTblId').bootstrapTable({}).on(
		'click-row.bs.table',
		function(e, row, $element) {
			
			TableRow = $element.attr('data-index');
			var tempDefaultImage = jQuery.parseJSON(JSON.stringify(row)).image;
			
			$('#tempImgDivIdClient').empty();
			var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+tempDefaultImage+'" /></a>'
			$('#tempImgDivIdClient').append(tempDiv);
			
		
		});
		
		
		

function qtyFormatter(value, row, index){

		
	
	 return '<input class="form-control data-input"  style="width:65px" value="'+ value+ '" onchange="javascript:updateQty(this,'+row.id+','+index+');"/>';
	
    
	
}


function updateQty(paramVal,dtid,vidx){
	
 if(paramVal.value > 0){
		
	/* 	 $('#clientStyleTblId').bootstrapTable('updateRow', {
				index : Number(vidx),
				row : {
					trfQty :paramVal.value,
					state :true,
				}
			});  */




			$('#clientStyleTblId').bootstrapTable('updateByUniqueId', {
	 	        id: dtid,
	 	        row: {
	 	        	trfQty :paramVal.value,
					state :true,
	 	        }
	 	      });


		
	}
		

		
	}
	
	
	function orderQuotSave(){
		if(Number($("#clientStyleTblId").bootstrapTable('getSelections').length) > 0){
			var data = JSON.stringify($("#clientStyleTblId").bootstrapTable('getSelections'));
			
			$.ajax({
				url : "/jewels/manufacturing/masters/addOrder/quotation.html",
				type : "POST",
				data : {
					data : data,
					ordMtId : $('#orderMtId').val(),
				},
				success : function(data, textStatus, jqXHR) {
					
					if(data == '1'){
					
						displayInfoMsg(this,null,"Order Added Successfully");
						popClientStyleTable();
						popOrderDetails('edit');
						
					}else{
					
						displayMsg(this,null,'Record Not Save ,Contact Admin');
					}
					
					
					
				},
				error : function(jqXHR, textStatus, errorThrown) {
				}
			});
		}else{
			displayMsg(this,null,"Record Not Selected");
		}
		
		
	}
	  
	  </script>
	  <%-- <script src="<spring:url value='/js/bootstrap/lodash.min.js' />"></script> --%>