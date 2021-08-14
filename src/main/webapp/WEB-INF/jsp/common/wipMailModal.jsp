<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<div class="modal fade" id="myMailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
	      		
	      		<div align="right">
	      			<input type="button" id="viewRptBtn" name="viewRptBtn" value="View Report" onclick="javascript:viewRpt();" class="modal-button btn-xs btn-info" />
			 		<input type="button" id="sendMailBtn" name="sendMailBtn" value="Send Mail" onclick="javascript:sendMail();" class="btn-xs btn-warning" />
			 		 &nbsp; &nbsp; &nbsp;
			 	  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	
			 	</div>
		     
	      </div>
	      
	      <div class="modal-body">
			 
			 <div class="row">
				<div class="col-lg-6 col-sm-6">
					<label for="inputLabel3" class=".col-lg-2 text-right">To :</label>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-sm-12">
					<input type="text" id="toId" name="toId" class="form-control">
				</div>
			</div>
			
			 &nbsp;
			
			 <div class="row">
				<div class="col-lg-6 col-sm-6">
					<label for="inputLabel3" class=".col-lg-2 text-right">Cc :</label>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-sm-12">
					<input type="text" id="ccId" name="ccId" class="form-control">
				</div>
			</div>
			 
			
			 &nbsp;
			
			 <div class="row">
				<div class="col-lg-6 col-sm-6">
					<label for="inputLabel3" class=".col-lg-2 text-right">Subject :</label>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-sm-12">
					<input type="text" id="subjectId" name="subjectId" class="form-control">
				</div>
			</div>
			
			&nbsp;
			 <div class="row">
				<div class="col-lg-6 col-sm-6">
					<label for="inputLabel3" class=".col-lg-2 text-right">Message :</label>
				</div>
			</div>
			<div id="txtEditor" name="txtEditor"></div>
			
			 
	       
	      </div>
	      
	      <div class="modal-footer">
	      	 <div align="center">
			 	<input type="button" id="sendMailBtnDowm" name="sendMailBtnDowm" value="Send Mail" onclick="javascript:sendMail();" class="btn-sm btn-warning" />
			 </div>
	      </div>
	      
	    </div>
	  </div>
</div>

<script type="text/javascript">
$(document).ready( function() {
$("#txtEditor").Editor();                    
});
</script>


<link href="/jewels/css/common/editor.css" rel="stylesheet" type="text/css" />
<script src="/jewels/js/common/editor.js"></script>

