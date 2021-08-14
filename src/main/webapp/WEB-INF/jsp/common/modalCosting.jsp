<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



	<!--------- CostingDt modal --------->

	<div class="modal fade" id="myCostingDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Costing Dt</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchId" class="search form-control" placeholder="Search " />
		       	</div>
	       </div>		       
	       
			<div id="costingDtTableDivId">
				<table  id="costingDtIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="itemNo" data-align="left" data-sortable="true" >Job No</th>
						 <th data-field="style" data-align="left" data-sortable="true" >Style No</th>
						 <th data-field="pcs" data-align="left" data-sortable="true" >Pcs</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popCostingDt(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	       </div>
	      
	    </div>
	  </div>
	</div>



 

	<script type="text/javascript">
	
	
	var $costingDtTable = $('#costingDtIdTbl');
    $(function () {
        $('#myCostingDtModal').on('shown.bs.modal', function () {
            $costingDtTable.bootstrapTable('resetView');
        });
    }); 
    
    
      
	
	</script>



