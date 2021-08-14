<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading" >
		<span style="font-size: 18px;">PD Search</span>
	</div>
	
	<div class="panel-body">
	
		
		
		<form:form commandName="pdcTranMt">
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-1 text-right control-label">Style No:</label>
					<div class="col-sm-2">
						<form:input path="design.styleNo" cssClass="form-control" onBlur="javascript:popDetails();" />
						<form:errors path="design.styleNo" />
					</div>
				</div>
				
				<div class="col-xs-12">
					<hr style="width: 100%; color: red; height: 2px; background-color: red;" />
				</div>
		
		</form:form>
		
		
		<div class="col-sm-12">
		
		
			<div class="col-sm-10">
				
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-left"
								style="font-size: 13px;">Kt :</label>
						</div>
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-left"
								style="font-size: 13px;">Current Department :</label>
						</div>
						<div class="col-lg-4 col-sm-4">
							<label for="inputLabel3" class=".col-lg-2 text-left"
								style="font-size: 13px;">Gross Wt :</label>
						</div>
					</div>
				</div>
					
				
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-4 col-sm-4">
							<input type="text" id="kt" name="kt" class="form-control" readonly="true">
						</div>
						<div class="col-lg-4 col-sm-4">
							<input type="text" id="currentDept" name="currentDept" class="form-control" readonly="true">
						</div>
						<div class="col-lg-4 col-sm-4">
							<input type="text" id="grossWt" name="grossWt" class="form-control" readonly="true">
						</div>
					</div>
				</div>
				
				
				
		
			</div>
		
		
			
				
			<div id="odImgDivId" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 center-block">
				<div class="panel panel-primary" style=" height:170px">
					<div class="panel-body">
						<div style="width: 150px; height: 50px">
							<a id="oImgHRId" href="/jewels/uploads/manufacturing/blank.png" data-lighter> 
								<img id="ordImgId" class="img-responsive" src="/jewels/uploads/manufacturing/blank.png" />
							</a>
						</div>
					</div>
				</div>
			</div>
			
	
		</div>
	
	
	</div>
</div>


<script type="text/javascript">


	$(document).ready(function(e){
		
		$("#design\\.styleNo").autocomplete({
			source : "/jewels/manufacturing/masters/styleNo/list.html",
			minLength : 2
		});
		
		$("#design\\.styleNo").focus();
		
		
	});





	
	
	function popDetails(){
		
		if(!!$('#design\\.styleNo').val()){
			
			if($('#design\\.styleNo').val().indexOf("[") != -1 && $('#design\\.styleNo').val().indexOf("]") != -1){
				
				var tempDesignNo = $('#design\\.styleNo').val();
				var tempStyleNo = $.trim(tempDesignNo.substring(0,tempDesignNo.indexOf("[")));
				var tempVersion = $.trim(tempDesignNo.substring(tempDesignNo.indexOf("[")+1,tempDesignNo.indexOf("]")));
				
				$.ajax({
					url:"/jewels/manufacturing/transactions/pdSearch/displayResult.html?styleNo="+tempStyleNo
							+"&version="+tempVersion,
					type:"GET",
					success:function(data){
						
						if(data === '-1'){
							displayMsg(this, null, 'No Such Design Present Or Problem In Getting Record With '+$('#design\\.styleNo').val()+' Contact Admin');
							$('#kt').val('');
							$('#currentDept').val('');
							$('#grossWt').val('');
							$('#ordImgId').attr('src', 'blank.png');
							$('#oImgHRId').attr('href', 'blank.png');
						}else if(data === '-2'){
							displayMsg(this, null, 'Design Not Found On Floor');
							$('#kt').val('');
							$('#currentDept').val('');
							$('#grossWt').val('');
							$('#ordImgId').attr('src', 'blank.png');
							$('#oImgHRId').attr('href', 'blank.png');
						}else{
							var tempVal = data.split("#"); 
							$('#kt').val(tempVal[0]);
							$('#currentDept').val(tempVal[1]);
							$('#grossWt').val(tempVal[2]);
							$('#ordImgId').attr('src', tempVal[3]);
							$('#oImgHRId').attr('href', tempVal[3]);
							
						}
						
						
					}
				})
				
			}else{
				displayMsg(this, null, 'Please Select The StyleNo With Version For Accurate Result ');	
			}
		}
		
	}
	
	
	
	


</script>


<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/design.js"></script>

<script src="/jewels/js/common/order.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

