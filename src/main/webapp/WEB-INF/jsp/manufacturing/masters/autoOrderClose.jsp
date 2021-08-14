<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%;height: 200px;">
<div class="row">&nbsp;&nbsp;&nbsp;&nbsp;</div>

	&nbsp;&nbsp;&nbsp;
	<div align="center">
		<a class="btn btn-info" style="font-size: 15px;" type="button"
			onclick="javascript:popOrderClose(event);"><span
			class="glyphicon glyphicon-remove"></span>&nbsp;Auto Order Close</a>
	</div>

</div>


<script type="text/javascript">


   function popOrderClose(e){
	   
	   displayDlg(
				e,
				'javascript:doAutoOrderClose();',
				'Order Close',
				'Do you want to Order Close  ?',
				'Continue');
   }


   function doAutoOrderClose(){
	   $("#modalDialog").modal("hide");
	   
	   $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait.......<h5>Do Not Hit On Screen</h5></h1>' });
	   
	   $.ajax({
			url : "/jewels/manufacturing/masters/autoOrderClose/closed.html",
			type : 'POST',
			success : function(data) {
				 $.unblockUI();
				 
				 if(data === "-1"){
					 displayInfoMsg(this,null,"Order Cloed Successfully");
				 }else if (data === "-2") {
					 displayInfoMsg(this,null,"Error Occured");
				}
				
			}
		});
   }
   
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<script src="/jewels//js/common/blockUserInterface.js"></script>