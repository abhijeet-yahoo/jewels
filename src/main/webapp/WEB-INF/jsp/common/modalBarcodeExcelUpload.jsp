<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


	<div class="modal fade" id="myBarcodeExcelUploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  >
	   <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content modelid">

	      
			<div class="modal-header">
		       <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="popCloseItemMaster();"><span aria-hidden="true">&times;</span></button> 
		  
	      </div>
	      	      
	      
  <div class="modal-body">
          <div class = "panel panel-warning" style="border-style: dotted;">
	      <div class="panel-body" style="height: 100%">
	  
	      	<div class="form-group">
					<div class="col-sm-12">
						<div class="col-sm-4">
							<input type="file" name="file" id="fileBarcode" /> 
						</div>	
						<div class="col-sm-4">	
							 <button type="button" class="btn btn-warning" id="fileBarcodeBtn">
	    						 <span class="glyphicon glyphicon-upload">&nbsp;UPLOAD</span>
	  						 </button> 
		                </div>		
					</div>
					<input type="hidden" id="tempFileName" name="tempFileName"/>
			</div>
		
			</div></div>
			</div>
			
			</div>
			
			</div>
			
			</div>
			
			
			

<script type="text/javascript">


$(document).ready(function(e){


});


$(document)
     .on("click","#fileBarcodeBtn",
         function() {

         console.log('inn')
         	processUpload();
         });
         
         

var files = [];
	$(document)
		.on("change","#fileBarcode",
           function(event) {
             files=event.target.files;
             $('#tempFileName').val($.map(files, function(val) { return val.name; }));
      });


function processUpload(){
	
    var oMyForm = new FormData();
    oMyForm.append("file", files[0]);


    console.log('xxx    '+$('#tempFileName').val());
    
    	$.ajax({
         	dataType : 'json',
             url : "/jewels/marketing/transactions/barcodeFilter/excelUpload.html?tempExcelFile="+$('#tempFileName').val(),
             data : oMyForm,
             type : "POST",
             enctype: 'multipart/form-data',
             processData: false, 
             contentType:false,
             success : function(result) {
            	
             	if(result === -2){
             		displayMsg(this,null,"Error : Contact Support");
             	}else if(result === -1){
             		 $('#myBarcodeExcelUploadModal').modal('hide');
             		popBarcodeListing();

             	}
             },
             error : function(jqXHR, textStatus, errorThrown){
             	displayMsg(this,null,"Error Technical: Contact Support");
             }
         });
   }
   
   
   

   



</script>