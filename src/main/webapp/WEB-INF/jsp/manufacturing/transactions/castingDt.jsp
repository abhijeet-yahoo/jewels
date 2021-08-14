
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>


<style>

.mySelected{
    background-color: #FFB6C1;
}

</style>


<div class="panel panel-primary" style="width: 100%">
	<div class="panel-heading">
	<label class="col-lg-2 col-sm-2 text-left">
	<span style="font-size: 18px;">Casting</span>
		</label>
		
	<div class="text-right">	
		<a id="designListBtn" class="btn btn-xs btn-info" style="font-size: 14px"  type="button" 
					href="/jewels/manufacturing/transactions/castingMt.html">Casting Listing</a>
	</div>
					
	</div>


	<form:form commandName="castingMt"
		action="/jewels/manufacturing/transactions/castingDt/add.html"
		cssClass="form-horizontal castingDtForm">


		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-3 col-sm-3">
					<label for="inputLabel3" class=".col-lg-2 text-right">Date
						:</label>
				</div>
				<div class="col-lg-3 col-sm-3">
					<label for="inputLabel3" class=".col-lg-2 text-right">Tree
						No :</label>
				</div>
				<div class="col-lg-3 col-sm-3">
					<label for="inputLabel3" class=".col-lg-2 text-right">Purity
						:</label>
				</div>
				<div class="col-lg-3 col-sm-3">
					<label for="inputLabel3" class=".col-lg-2 text-right">Color
						:</label>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-3 col-sm-3">
					<form:input path="cDate" cssClass="form-control"
						onChange="javascript:getTree(this.value)"  autocomplete="off"/>
					<form:errors path="cDate" />
				</div>
				<div class="col-lg-3 col-sm-3">
					<form:select path="treeNo" class="form-control"
						onChange="javascript:popPurityColorPcs();popCastingtDt();popCastingCompDt()">
						<form:option value="" label=" Select TreeNo " />
						<form:options items="${treeMap}" />
					</form:select>
				</div>
				<div class="col-lg-3 col-sm-3">
					<form:input path="purity" cssClass="form-control" />
					<form:errors path="purity" />
				</div>
				<div class="col-lg-3 col-sm-3">
					<form:input path="color" cssClass="form-control" />
					<form:errors path="color" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-3 col-sm-3">
					<label for="inputLabel3" class=".col-lg-2 text-right">Total
						Pcs Wt :</label>
				</div>
				<div class="col-lg-3 col-sm-3">
					<label for="inputLabel3" class=".col-lg-2 text-right">Issue Wt
						:</label>
				</div>
				<div class="col-lg-3 col-sm-3">
					<label for="inputLabel3" class=".col-lg-2 text-right">Comp Issue Wt
						:</label>
				</div>
				<div class="col-lg-3 col-sm-3">
					<label for="inputLabel3" class=".col-lg-2 text-right">Total
						Issue Wt :</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="col-lg-3 col-sm-3">
					<form:input path="pcsWt" cssClass="form-control" disabled="true" />
					<form:errors path="pcsWt" />
				</div>
				<div class="col-lg-3 col-sm-3">
					<input type="text" id="disIssWt" name="disIssWt"
						class="form-control" value="0.0" disabled="disabled" />
				</div>
				<div class="col-lg-3 col-sm-3">
					<input type="text" id="disCompWt" name="disCompWt"
						class="form-control" value="0.0" disabled="disabled" />
				</div>
				<div class="col-lg-3 col-sm-3">
					<input type="text" id="disTotIssWt" name="disTotIssWt"
						class="form-control" value="0.0" disabled="disabled" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
	</form:form>
</div>
<!-- ending first panel -->




<div class="panel panel-primary" style="width: 100%">
	<div class="row">
		<div class="col-xs-12">&nbsp;</div>
	</div>
	
	
	
	<div role="tabpanel">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#mainScreen" id="castDtMetalTabId"
				aria-controls="home" role="tab" data-toggle="tab">Bag Issue</a></li>
			<li role="presentation" ><a href="#castingDtComponent" id="castDtCompTabId"
				aria-controls="profile" role="tab" data-toggle="tab">Component Issue</a></li>
		</ul>
	
		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="mainScreen">
				<%@include file="castingDtMetal.jsp"%>
			</div>
			<div role="tabpanel" class="tab-pane" id="disableCastingDtComponent">
				<%@include file="castingDtComponent.jsp"%>
			</div>
		</div>
	</div>
		
		
		
</div> <!-- ending second panel -->




<script type="text/javascript">
	
	$(document).ready(
			function(e) {
				
				
				if (window.location.href.indexOf('vDt') != -1) {

				
					var vUrl = window.location.href;
					var vDate = vUrl.substring(window.location.href.indexOf('vDt')+3,window.location.href.indexOf('vtree'));
					$('#cDate').val(vDate);
					
					$('#cDate').trigger('onchange');
					var vvtree = vUrl.substring(window.location.href.indexOf('vtree')+5);
					
					
					setTimeout(function() {
					
						$('#treeNo').val(vvtree);
						$('#treeNo').trigger('onchange');
						
					}, 100);
	
					
					
					
				
					}
					 	
				
				
				
				$(".castingDtForm").validate(
						{
							rules : {

							},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							},
						});

				$("#cDate").datepicker({
					dateFormat : 'dd/mm/yy'
				});
				
				
				$("#" + document.querySelector("#disableCastingDtComponent").id).attr("id", "castingDtComponent");
				
				$('#castDtMetalTabId').on('click', function() {
					popCastingtDt();
				});
				
				
				

				 if (window.location.href.indexOf('date') != -1) {

					var abcx = window.location.href;

					var tempDate = abcx.substring(window.location.href.indexOf('date') + 5);
					temp = tempDate.split("_");
					$('#cDate').val(temp[0]);

					var tempTreeNoId = abcx.substring(window.location.href.indexOf('&treeId') + 8);
					temp1 = tempTreeNoId.split("_");
					
					settingTheData(temp1[0]);
					
					var xTotCompWt = abcx.substring(window.location.href.indexOf('&totCompWt') + 11);
					$('#disCompWt').val(xTotCompWt);
					

				}
				 
				 
				 $('#castDtCompTabId').on('click', function() {
					 popCastingCompDt();
					});
 
			});

	function settingTheData(vSel) {

		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/castingDt/fromTreeNo/toid.html' />?id='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#treeNo").html(data);
						$("#treeNo > [value=" + vSel + "]").attr("selected","true");
						popPurityColorPcs();
						popCastingtDt();
					}
				});
	}

	
	function getTree(vSel) {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/castingDt/tree.html' />?cDate='
							+ vSel,
					type : 'GET',
					success : function(data) {
						$("#treeNo").html(data);
					}
				});
	}
	

	function popPurityColorPcs() {
		$
				.ajax({
					url : '<spring:url value='/manufacturing/transactions/castingDt/purityColorPcs.html' />?treeNo='
							+ $('#treeNo').val()
							+ "&cDate="
							+ $('#cDate').val(),
					type : 'GET',
					success : function(data) {
						var temp = data.split("_");
						$('#purity').val(temp[0]);
						$('#color').val(temp[1]);
						$('#pcsWt').val(temp[2]);
						$('#castingMtPk').val(temp[3]);

					}
				});
	}
	
	

	function popCastingtDt() {
		if(!!$('#treeNo').val()){

			$("#castingDtTab")
					.bootstrapTable(
							'refresh',
							{
								url : "/jewels/manufacturing/transactions/castingDt/listing.html?treeNo="
										+ $('#treeNo').val()
										+ "&cDate="
										+ $('#cDate').val()
	
							});
	
			totalIssWt();
		}
	}


function goToNextPage() {
	

		var vTreeNo = $('#treeNo').val();

		if (!vTreeNo) {
			displayMsg(this, null, 'PLEASE SELECT THE TREE NO !');
		} else {
			window.location.href = "/jewels/manufacturing/transactions/castingDt/add.html?id="
					+ $('#castingMtPk').val().trim()
					+ "_"
					+ "&cDate="
					+ $('#cDate').val() + "_" + "&treeNo=" + $('#treeNo').val().trim() + "_" + "&totCompIssWt=" + $('#disCompWt').val().trim();
		}

	}

/* 	function getNumVal(dt) {
		if (typeof dt === 'undefined') {
		} else {
			dt = dt.substring(dt.indexOf("<strong>") + 8, dt
					.indexOf("</strong>"));
		}

		return dt;
	} */

	
	var oldwt;
	var stnTblRow = -1;
	$('#castingDtTab').bootstrapTable({})
			.on(
					'click-row.bs.table',

					function(e, row, $element) {

						/* var temp = getNumVal(jQuery.parseJSON(JSON.stringify(row)).transfer); */
						
						var temp = jQuery.parseJSON(JSON.stringify(row)).transfer;
						stnTblRow = $element.attr('data-index');

						 if (String(temp) == 'Yes') {
							//displayMsg(event, this,'Transfered done already');
							$("#mUpdateId").css('display', 'none');
						} else {
							
							//$('#fldMetalWt').val(getNumVal(jQuery.parseJSON(JSON.stringify(row)).metalWt));

							/* oldwt =getNumVal(jQuery.parseJSON(JSON.stringify(row)).metalWt); */
							 
							 oldwt =jQuery.parseJSON(JSON.stringify(row)).metalWt;
							 
							//$("#mUpdateId").css('display', 'block');
							//$('#fldMetalWt').focus();
						} 
						

					});
	
	
	
	
		
	
	
	
	 
		function metalWtFormatter(value, row, index){
			
			var tempId = 'metalwtval' + Number(index);
			
			var vId = row.id;
			
						
		    if (row.transfer === 'Yes') {
		    	
		    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateMetalWt(this,'+vId+')" disabled />';
		    	
		    }else{
		    	return '<input class="form-control data-input" id="'+ tempId+ '" name="'+ tempId+ '" style="width:65px" value="'+ value+ '" onchange="javascript:updateMetalWt(this,'+vId+')" />';
		    }
			
		    
			
		}
		
		function updateMetalWt(param,val){
		
			var tempId = param.id.substring(10);
	 		
	 		
	 		var balpcwt =parseFloat($('#pcsWt').val()-$('#disTotIssWt').val()).toFixed(3);
	 		
			var isswt=parseFloat(param.value).toFixed(3);
			
			var balpc =parseFloat(balpcwt)+parseFloat(oldwt);
			
						
			
			if(Number(balpc) >= Number(isswt) ){
				
				$('#metalwtval'+tempId).val(param.value);

				$('#castingDtTab').bootstrapTable('updateByUniqueId', {
		 	        id: val,
		 	        row: {
		 	        	metalWt : param.value
		 	        }
		 	      });
		 		
		 		
		 		
				
				updatetotalIssWt(val,param.value);
					
			}else{
				
				displayMsg(event, this ,"Total Issue Wt cannot be greater than Total Pcs Wt");
								
				$('#metalwtval'+tempId).val('0.00');
				
				$('#castingDtTab').bootstrapTable('updateByUniqueId', {
		 	        id: val,
		 	        row: {
		 	        	metalWt : 0.0,
		 	        }
		 	      });
		 		
				
				totalIssWt();
			}
			 
	 		
	
	 		
	 	}
		
	

	$('#fldMetalWt').on('blur', function(e) {
		
		$('#purity').focus();
		
		var balpcwt =parseFloat($('#pcsWt').val()-$('#disTotIssWt').val()).toFixed(3);
		var isswt=parseFloat($('#fldMetalWt').val()).toFixed(3);

		
		var balpc =parseFloat(balpcwt)+parseFloat(oldwt);
				
		if(Number(balpc) >= Number(isswt) ){
			
			
			$("#castingDtTab").bootstrapTable('updateRow', {
				index : stnTblRow,
				row : {
					state : false,
					metalWt : $('#fldMetalWt').val(),

				}
			});
			
			totalIssWt();
				
		}else{
			
			displayMsg(event, this ,"Total Issue Wt cannot be greater than Total Pcs Wt");
			$('#fldMetalWt').val('0.0');
			
		}
		
		

		
	});

	
	
	$(document)
			.on(
					'submit',
					'form#transferToTranMtt',
					function(e) {
						
						$('#transferSubmitBtn').attr('disabled', 'disabled');
						
						var data = $('#castingDtTab').bootstrapTable(
								'getSelections');

						var ids = $.map(data, function(item) {
							return item.id;
						});

						var metal = $.map(data, function(item) {
							return item.metalWt;
						});

						$('#pODIds').val(ids);
						$('#metalWtt').val(metal);

						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");

						$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,

									success : function(data, textStatus, jqXHR) {
										
										if (data === '1') {
											displayInfoMsg(this, null, 'DATA TRANSFERED SUCESSFULLY !');
											
											popCastingtDt();
											
										}else {
											
											displayMsg(this, null, data);
										}
										
										$('#transferSubmitBtn').removeAttr('disabled');

									},
									error : function(jqXHR, textStatus,
											errorThrown) {

										alert("Please select atleast one record to transfer and now refresh the browser");
										//alert("record not found");
									}

								});
						e.preventDefault(); //STOP default action

					});

	
	function totalIssWt() {
		var data = JSON.stringify($("#castingDtTab").bootstrapTable('getData'));
		var vMetal = 0.0;
		$.each(JSON.parse(data), function(idx, obj) {
			vMetal += Number(obj.metalWt);
		});
		$('#disIssWt').val(" " + parseFloat(vMetal).toFixed(3));
		
		totalFinalIssueWt();
	}
	
	
	
		
		$('#castingDtTab').bootstrapTable({})
		.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#castingDtTab").bootstrapTable('getData'));
				var vMetal = 0.0;
				
				var i=0;
				$.each(JSON.parse(data), function(idx, obj) {
					vMetal += Number(obj.metalWt);
					
					if(obj.transfer === 'Yes'){
						
						
						$('#castingDtTab tr:eq('+Number(idx+1)+')').addClass('mySelected');
						
					//	$('#castingDtTab tr:eq('+Number(idx+1)+')').attr('disabled','disabled');
						
						$("#castingDtTab tr.mySelected input, tr.mySelected select, tr.mySelected textarea").prop('disabled', true);
						
						$('#metalwtval'+i).attr('disabled','disabled');
						
						
						
					}
					Number(i++);
					
				});

				$('#disIssWt').val(" " + parseFloat(vMetal).toFixed(3));
				
				totalFinalIssueWt();
			})
			
		
		
			
			
			
		function deleteCastingDt(e,castDtId,status){
			
			
				displayDlg(
						e,
						'javascript:doDeleteCastDt('+castDtId+');',
						'Delete',
						'Do you want to Delete  ?',
						'Continue');
			
			
			
			
		}	
			
			
		function doDeleteCastDt(castDtId){
			
			$("#modalDialog").modal("hide");
			
			$.ajax({
				url : "/jewels/manufacturing/transactions/castingDt/delete/"+ castDtId + ".html",
				type : 'GET',
				success : function(data) {
					
					if(data ==='1'){
						popCastingtDt();	
					}else{
						displayMsg(this,null,'Can Not Delete ,Contact Admin');
					}				
					
				}
			});
			
			
		}	
			
			
			
			
			
			
		function returnToRecast(castDtId){
			
			
			
	 		$.ajax({
				url : "/jewels/manufacturing/transactions/castingDt/recast/"
						+ castDtId + ".html",
				type : 'GET',
				success : function(data) {
					if(data === "-2"){
						displayMsg(event, this,'Bag Return To Casting');
						popCastingtDt();
					}
				}
			});
	 		
	 		
	 	}
			
			
			
			
			
	
			
			
	/*-------------- for castingDt Component page --------------*/
		
		
		function popCastingCompDt(){
		
			if(!!$('#treeNo').val()){
				
				$("#castCompTableId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/transactions/castingCompDt/listing.html?treeNo="
									+ $('#treeNo').val()
									+ "&cDate="
									+ $('#cDate').val()
						});
				
			 }
			
		}
		
		
		function deleteCastingCompDt(castCompDtId){
	 		
	 		$.ajax({
				url : "/jewels/manufacturing/transactions/castingCompDt/delete/"
						+ castCompDtId + ".html",
				type : 'GET',
				success : function(data) {
					if(data === "-1"){
						popCastingCompDt();
					}
				}
			});
	 		
	 		
	 	}
			
		
		
		$('#compMetalWt').on('blur', function(e) {
			
			var balpcwt =parseFloat($('#pcsWt').val()-$('#disTotIssWt').val()).toFixed(3);
			var isswt=parseFloat($('#compMetalWt').val()).toFixed(3);

	
					
			if(Number(balpcwt) >= Number(isswt)){
				totalCompWt();	
			}else{
				displayMsg(event, this ,"Total Issue Wt cannot be greater than Total Pcs Wt");
				$('#compMetalWt').val('0.0');
			}
			
		});
		
		
		
		$('#castCompTableId').bootstrapTable({})
		.on(
			'load-success.bs.table',
			function(e, data) {
				var data = JSON.stringify($("#castCompTableId").bootstrapTable('getData'));

				var vComp = 0.0;
				
				$.each(JSON.parse(data), function(idx, obj) {
					vComp += Number(obj.metalWt);
				});
				
				$('#disCompWt').val(" " + parseFloat(vComp).toFixed(3));
				 totalFinalIssueWt();
				
			})
		
		
		
		function totalCompWt() {
			var data = JSON.stringify($("#castCompTableId").bootstrapTable('getData'));
			
			var vComp = 0.0;
		
			$.each(JSON.parse(data), function(idx, obj) {
				vComp += Number(obj.metalWt);
			});
			
			$('#disCompWt').val(" " + parseFloat(vComp).toFixed(3));
			 totalFinalIssueWt();
		}
		
		
		
		
		
		function totalFinalIssueWt(){
			var vTot = 0.0;
			vTot = parseFloat($('#disIssWt').val()) + parseFloat($('#disCompWt').val());
			$('#disTotIssWt').val(parseFloat(vTot).toFixed(3));
		}
		
		
		
		
		
		 function rowStyle(row, index) {
			 
	              if (row.transfer === 'Yes') {
			                return {
			                  classes: 'mySelected'
			                }
			              }
			              return {
			                css: {
			                  color: 'black'
			                }
			               }
			              
			          
			              
			  }
		 
		 
		 function updatetotalIssWt(id,metalWt){
			 
			 $.ajax({
				
				 url:"/jewels/manufacturing/transactions/castingDt/updateTotalIssWt.html?treeNo="
						+ $('#treeNo').val()+"&id="+id+"&metalWt="+metalWt,
				type : 'GET',
				success : function(data) {

					$('#disIssWt').val(parseFloat(data).toFixed(3));
						totalFinalIssueWt();
			}
				 
				 
			 });
		 }
		


</script>


<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/common/common.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />




