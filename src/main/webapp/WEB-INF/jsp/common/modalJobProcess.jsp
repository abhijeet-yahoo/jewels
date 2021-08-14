<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
.modal-footer1 {
    padding: 10px;
    text-align: left;
    border-top: 1px solid #e5e5e5;
}
</style>



<!--------- Labour Type modal --------->

	<div class="modal fade" id="myProcessModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Process</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchProcess" class="search form-control" placeholder="Process Search " />
		       	</div>
	       </div>		       
	       
			<div id="processTableDivId">
				<table  id="processIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Process</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popProcess(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popProcessSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	<!--------- Process Dt modal --------->

	<div class="modal fade" id="myProcessDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Process</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchProcessDt" class="search form-control" placeholder="Process Search " />
		       	</div>
	       </div>		       
	       
			<div id="processDtTableDivId">
				<table  id="processDtIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Process</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popProcessDt(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popProcessDtSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	
	
	<!--------- Process Rec Dt modal --------->

	<div class="modal fade" id="myProcessRecDtModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Process</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	<div class="col-sm-2"></div>
	       	    <div class="col-sm-6"><span style="display:inline-block; width:51cm; "></span></div>
				<div class="col-sm-4">		       	
		       		<input type="search" id="searchProcessRecDt" class="search form-control" placeholder="Process Search " />
		       	</div>
	       </div>		       
	       
			<div id="processRecDtTableDivId">
				<table  id="processRecDtIdTbl" data-toggle="table" 
					data-click-to-select="true" data-side-pagination="server" 
					data-striped="true" data-height="350" >
					<thead>
					  <tr>
						 <th data-field="state" data-checkbox="true" ></th>
						 <th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">Process</th>
					 </tr>
					</thead>
				</table> 
			</div>
	       
	      </div>
	      
	      <div class="modal-footer">
	      	<a href="javascript:popProcessRecDt(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popProcessRecDtSave()">Save changes</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	

<script type="text/javascript">
	
	
	var $processTable = $('#processIdTbl');
    $(function () {
        $('#myProcessModal').on('shown.bs.modal', function () {
            $processTable.bootstrapTable('resetView');
        });
    }); 
    
   var $processDtTable = $('#processDtIdTbl');
    $(function () {
        $('#myProcessDtModal').on('shown.bs.modal', function () {
            $processDtTable.bootstrapTable('resetView');
        });
    });  
    
    
    var $processRecDtTable = $('#processRecDtIdTbl');
    $(function () {
        $('#myProcessRecDtModal').on('shown.bs.modal', function () {
            $processRecDtTable.bootstrapTable('resetView');
        });
    }); 
    


   
    
    
    
    
    
	
    </script>