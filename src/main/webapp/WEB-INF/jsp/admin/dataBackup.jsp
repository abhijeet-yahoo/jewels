<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>


<div class="panel" style="width: 100%">
	<div class="panel-body" style="height: 12cm">

		<div id="imgDivId" style="display: none" align="center">
			<img class="img-responsive" src="/jewels/uploads/manufacturing/dbimg.jpg" id="imagepreview"  alt="image" >
		</div>
		
		



	</div>

</div>


<script type="text/javascript">


	$(document).ready(function(e){
		confirmation(event)
	});
	
	
	
	
	
	function confirmation(e){
		dataBackupConfirm(
				e,
				'javascript:popupModel();',
				'DataBase Backup',
				'Do you want to take the database backup  ?',
				'Continue');
	}


	
	function popupModel(){
		
		$("#modalDialogDb").modal("hide");
		
	    $.blockUI({ message:'<h4><img src="/jewels/uploads/manufacturing/imgLoad.gif"/>&nbsp;&nbsp;Please Wait DataBackup In Progress</h4>' });
		$("#imgDivId").css('display','block');
		
		$.ajax({
			url:"/jewels/admin/dataBackup/confirm.html",
			type:"GET",
			success:function(data){
				$("#imgDivId").css('display','none');
				$.unblockUI();
				
				//alert(data);
				
				if(data === '0'){
					displayInfoMsg(this, null, 'DataBackup Process Comleted!');
				}else{
					alert("problem occoured in backup");
				}
				
				
			}
		}) 
		
	}

</script>



<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>


