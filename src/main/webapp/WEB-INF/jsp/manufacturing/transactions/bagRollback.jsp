<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<style>
.bacground{
bgcolor:"red";
}

.mySelected{
    background-color: #FFB6C1;
}

</style>


<div class="panel panel-primary" style="width: 100%">

	<div class="panel-heading">
		<span style="font-size: 18px;">Bag Rollback</span>
	</div>


	<div class="panel-body">
	
	


	<div class="form-group">
	
		
		<div class="col-xs-12">
		
			<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Bag:</label>
						</div>
						
						
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Client:</label>
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Order
								No:</label>
						</div>
						
						
						<div class="col-lg-2 col-sm-2">
							<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Style
								No:</label>
						</div>
											</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="bagMtName" name="bagMtName" class="form-control" onchange="javascript:popBagDetails()">
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="partyName" name="partyName" class="form-control" readonly="readonly">
						</div>
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="invNo" name="invNo" class="form-control" readonly="readonly">
						</div>
						
						<div class="col-lg-2 col-sm-2">
							<input type="text" id="styleNo" name="styleNo" class="form-control" readonly="readonly">
						</div>
						
					</div>
				</div>
	
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
				</div>
				
				
				<div class="row">
					<div class="col-xs-12">&nbsp;</div>
					<input type="hidden" id="orderDtId" name="orderDtId" />
				</div>
				
		
		
		</div>
	
	
	
		
	
	
	</div>
	
	
	<div class="col-xs-10">
	
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-lg-12 col-sm-12 label label-default" style="font-size: 16px;">Bag Details</span>
			</div>
		</div>
	</div>
	
	
	<div class="form-group">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div>
						<table id="tranMtTblId" data-toggle="table"
							data-toolbar="#toolbarDt1" data-click-to-select="false"
							data-side-pagination="server"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="300">
							<thead>
								<tr>
									<th data-field="state" data-checkbox="true"></th>
									<th data-field="tranDate">Trandate</th>
									<th data-field="deptNm">Dept</th>
									<th data-field="deptFromNm">Dept From</th>
									<th data-field="deptToNm">Dept To</th>
									<th data-field="recWt" >Rec Wt</th>
									<th data-field="issueWt">Issue Wt</th>
									<th data-field="createdBy">Created By</th>
									<th data-field="action1" >Rollback</th>
																	
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	</div>
	
	<div id="odImgDivId" class="col-lg-2 col-md-3 col-sm-4 col-xs-6 center-block">
			<div class="panel panel-primary" style=" height:170px;">
				<div class="panel-body">
					<div style="width: 150px; height: 50px" class="col-sm-4">
						<a id="oImgHRId" href="/jewels/uploads/manufacturing/blank.png"
							data-lighter> <img id="ordImgId" class="img-responsive"
							src="/jewels/uploads/manufacturing/blank.png" />
						</a>
					</div>
				</div>
			</div>
		</div>
	
	

	</div>
</div>



<script type="text/javascript">
	$(document)
			.ready(
					function(e) {
						
						
						$("#bagMtName").autocomplete({
							source : "<spring:url value='/manufacturing/transactions/jobBag/list.html' />",
							minLength : 2
						});
						
						
					});
	
	
	function popBagDetails(){
		
		if(!!$('#bagMtName').val()){
			$.ajax({
				url:"/jewels/manufacturing/transactions/jobBag/diamond/details.html?bagName="+$('#bagMtName').val(),
				type:"GET",
				datatype:"JSON",
				success:function(data){
					
					if(data ==='-1'){
						displayMsg(this,null,'Error : Please Check Bag No ,Bag No not Valid');
						$('#transferBtnId').prop('disabled', true);
						
					}else{
						
						  $.each(JSON.parse(data), function(key,value){
						        if(key === 'image'){
						        	$('#ordImgId').attr('src', value);
									$('#oImgHRId').attr('href', value);
						        }else{
						        	$('#'+key).val(value)	
						        }
						    });
						  
						  popOrderStnDt();
						  
							$('#transferBtnId').prop('disabled', false);
						
					}
					
			
				  
				}
			});	
		}else{
			displayMsg(this,null,"Please Enter Bag No");
		}
	}
	
	function popOrderStnDt() {

		$("#tranMtTblId")
			.bootstrapTable('refresh',{
				url : "/jewels/manufacturing/transactions/bagRollback/listing.html?bagName="+$('#bagMtName').val()
			});
	}
	
	
	function popBagRollback(mtId){
		 $.blockUI({ message: '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>' });	
		
		$.ajax({
			
			url:"/jewels/manufacturing/transactions/transfer/rollBack.html?mtId="+mtId,
			type:"GET",
			success:function(data){
				$.unblockUI();
				if(data ==="1"){
					popOrderStnDt();	
				}else{
					displayMsg(this,null,data);
				}
				
				
			}
		
			
			
		});
		
		
	};
	
	
	</script>	
					

<link rel="stylesheet" href="<spring:url value='/css/jquery/jquery-ui.min.css' />">

<script src="<spring:url value='/js/jquery/jquery-ui.min.js' />"></script>

<script src="<spring:url value='/js/common/design.js' />"></script>

<script src="<spring:url value='/js/common/common.js' />"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />					
<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>
					
					