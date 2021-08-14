<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script>

function doDelete(event, element) {
	if ('preventDefault' in event) {
		event.preventDefault();
	}
	event.returnValue = false; // for IE

	$("#modalRemove .deleteBtn").attr("href", element);
	$("#modalRemove").appendTo('body').modal();
}

function displayMsg(event, element, msg) {
	if ('preventDefault' in event) {
		event.preventDefault();
	}
	event.returnValue = false; // for IE

	$("#mgsId").html(msg);

	$("#modalDisplay").appendTo('body').modal();
}








function displayBackDatedMsg(event, element) {
	if ('preventDefault' in event) {
		event.preventDefault();
	}
	event.returnValue = false; // for IE

	$("#mgsId").html(' Back Dated Entry, Contact Administrator');

	$("#modalDisplay").appendTo('body').modal();
}



function displayDlg(event, element, msgTitle, msg, btnLbl) {
	if(typeof event == "undefined") {}
	else {
		if ('preventDefault' in event) {
			event.preventDefault();
		}
		event.returnValue = false; // for IE		
	}
	

	$("#msgTitleId").html(msgTitle);
	$("#mDlgMsg").html(msg);
	$("#btnLblId").html(btnLbl);

	$("#modalDialog .continueBtn").attr("href", element);
	$("#modalDialog").appendTo('body').modal();
}



function displayConfirmation(event,element, msgTitle, msg, btnLbl){
	
	if(typeof event == "undefined") {}
	else {
		if ('preventDefault' in event) {
			event.preventDefault();
		}
		event.returnValue = false; // for IE		
	}

	$("#msgTitleIdxx").html(msgTitle);
	$("#mDlgMsgConfirm").html(msg);
	$("#btnLblIdConfirm").html(btnLbl);

	$("#modalDialogConfirm .continueConformBtn").attr("href", element);
	$("#modalDialogConfirm").appendTo('body').modal();
	
	
}


function displayInfoMsg(event, element, msg) {
	if ('preventDefault' in event) {
		event.preventDefault();
	}
	event.returnValue = false; // for IE

	$("#mgsInfoId").html(msg);

	$("#modalInfoDisplay").appendTo('body').modal();
}



function displayNotificationMsg(event, element, msg) {
	if ('preventDefault' in event) {
		event.preventDefault();
	}
	event.returnValue = false; // for IE

	$("#mgsNotiInfoId").html(msg);

	$("#modalNotificationDisplay").appendTo('body').modal();
}


//------------data backup confirmation---///

function dataBackupConfirm(event, element, msgTitle, msg, btnLbl) {
	if(typeof event == "undefined") {}
	else {
		if ('preventDefault' in event) {
			event.preventDefault();
		}
		event.returnValue = false; // for IE		
	}
	

	$("#msgDbId").html(msgTitle);
	$("#mDbMsg").html(msg);
	$("#btnDbIdd").html(btnLbl);

	$("#modalDialogDb .continueDbBtn").attr("href", element);
	$("#modalDialogDb").appendTo('body').modal();
}




/* function displaySpinner(event, element) {
	if ('preventDefault' in event) {
		event.preventDefault();
	}
	event.returnValue = false; // for IE

	$("#mgsId").html(msg);

	$("#modalDisplay").appendTo('body').modal();
}
 */


 function displayToreranceMsg(event, element, msg) {
		if ('preventDefault' in event) {
			event.preventDefault();
		}
		event.returnValue = false; // for IE

		$("#mgsInfoIdTorerance").html(msg);

		$("#modalInfoDisplayTorerance").appendTo('body').modal();
	}



</script>

<!-- Modal -->
<c:if test="${empty optionText}">
	<c:set var="optionText" value="Deactivate"> </c:set>
</c:if>



<!-------- dataBackup confirmation -------->

<div class="modal fade" id="modalDialogDb" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="msgDbId" align="center" >Message</h4>
			</div>
			<div class="modal-body" style="height: 10cm"><p class="text-center" id="mDbMsg"></p>
				<div align="center"><img class="img-responsive" src="/jewels/uploads/manufacturing/dbimg.jpg" id="imagepreview"  alt="image" ></div>
				 <div align="center"><span style="color: #7f1a1a;font-size: large;" >Before Backup , Ensure That No One is Using The System</span></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-success continueDbBtn"  id="btnDbIdd"></a>
			</div>
		</div>
	</div>
</div>





<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><c:out value="${optionText}" /> <c:out value="${option}" /></h4>
			</div>
			<div class="modal-body"><p class="text-center">Do you want to <c:out value="${fn:toLowerCase(optionText)}" /> this <c:out value="${option}" />?</p></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger deleteBtn"><c:out value="${optionText}" /></a>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="modalDisplay" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title text-danger" id="myModalLabel"><b>Warning Message</b></h4>
			</div>
			<div class="modal-body"><p class="text-center text-danger"><b id="mgsId">You do not have the necessary <br> privileges to perform this operation</b></p></div>
			<div class="modal-footer">
				<!-- <marquee>Something Went Wrong</marquee> -->
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="msgTitleId">Message</h4>
			</div>
			<div class="modal-body"><p class="text-center" id="mDlgMsg"></p></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger continueBtn"  id="btnLblId"></a>
			</div>
		</div>
	</div>
</div>



<div class="modal fade" id="modalDialogConfirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="msgTitleIdxx">Message</h4>
			</div>
			<div class="modal-body"><p class="text-center" id="mDlgMsgConfirm"></p></div>
			<div class="modal-footer">
				
				<a href="" class="btn btn-success continueConformBtn" style="width:4cm;" id="btnLblIdConfirm"></a>
			</div>
		</div>
	</div>
</div>




<!-- Modal -->
<div class="modal fade" id="modalInfoDisplay" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title text-success" id="myModalLabel"><b>NOTIFICATION</b></h4>
			</div>
			<div class="modal-body"><p class="text-center text-info"><b id="mgsInfoId">SUCESSFULLY</b></p></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal" style="width:4cm;" >OK</button>
			</div>
		</div>
	</div>
</div>



<!-- Modal -->
<div class="modal fade" id="modalNotificationDisplay" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title text-success" id="myModalLabel"><b>NOTIFICATION</b></h4>
			</div>
			<div class="modal-body"><p class="text-center text-info"><b id="mgsNotiInfoId">SUCESSFULLY</b></p></div>
			<div class="modal-footer">
				
			</div>
		</div>
	</div>
</div>






<!-------- dataBackup modal -------->

	<div class="modal fade" id="dataBackupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      
	      <div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> 
		      	<h4 class="modal-title" id="myModalLabel">Data Backup</h4>
	      </div>
	      
	      <div class="modal-body">
			 <div>
	       	  	
	       	  	
	       	  	
	       	</div>
	       		       
	        <!--
			<div id="shapeTableDivId" >
				<table id="shapeIdTbl" data-toggle="table"
					data-click-to-select="true" data-side-pagination="server"
					data-striped="true" data-height="350">
					<thead>
						<tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-align="left" data-sortable="true" style="font-weight: bolder;">SHAPE</th>
						</tr>
					</thead>
				</table>
			</div> -->
	       
	      </div>
	      
	      <div class="modal-footer">
	      <!-- 	<a href="javascript:popShape(1)" class="glyphicon glyphicon-refresh"></a>&nbsp;&nbsp;&nbsp;
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" onclick="javascript:popShapeSave()">Save changes</button> -->
	      </div>
	      
	    </div>
	  </div>
	</div>




<!-- Modal -->
<div class="modal fade" id="modalInfoDisplayTorerance" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:popTorerance('no')">
					<span aria-hidden="true">&times;</span>
				</button>
								<h4 class="modal-title text-success" id="myModalLabel"><b>NOTIFICATION</b></h4>
			</div>
			<div class="modal-body"><p class="text-center"><b id="mgsInfoIdTorerance">Do you want Allow</b></p></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:popTorerance('no')" style="width:3cm;" >No</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal" onclick="javascript:popTorerance('yes')" style="width:3cm;" >Yes</button>
			</div>
		</div>
	</div>
</div>





















<!-- <div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">

	<div class="modal-dialog">
		<div class="modal-content">

		    <div class="modal-header">
		        <h1>Please Wait</h1>
		    </div>
		    <div class="modal-body">
		        <div id="ajax_loader">
		            <img src="~/jewels/uploads/manufacturing/imgLoad.gif" style="display: block; margin-left: auto; margin-right: auto;">
		        </div>
		    </div>
    
    	</div>
    </div>
    
</div> -->






















