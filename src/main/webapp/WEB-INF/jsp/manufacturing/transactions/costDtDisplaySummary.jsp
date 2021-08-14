<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel-body">

	<div class="container-fluid">
		<div class="row">
		
		

			<div id="costSumDivId">

	<div class="row">

					<div class="col-sm-4 form-group">

						<label for="totPcs" class="col-sm-4 control-label">Pcs</label>
						<div class="col-sm-6">
							<input type="text" id="totPcs" class="form-control" name="totPcs"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					
						<div class="col-sm-4 form-group">
						<label for="totStone" class="col-sm-4 control-label">Stone</label>
						<div class="col-sm-6">
							<input
						type="text" id="totStone" class="form-control" name="totStone"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>

				<div class="col-sm-4 form-group">
						<label for="totCompWt" class="col-sm-4 control-label">Finding Wt</label>
						<div class="col-sm-6">
							<input
						type="text" id="totCompWt" class="form-control" name="totCompWt"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>

					
					
					
					
				<div class="col-sm-4 form-group">
						<label for="totGrossWt" class="col-sm-4 control-label">Gross Wt</label>
						<div class="col-sm-6">
							<input type="text" id="totGrossWt" class="form-control"
								name="totGrossWt" readonly="readonly" style="text-align: right;"/>
						</div>


					</div>
				


				<div class="col-sm-4 form-group">
						<label for="totCarat" class="col-sm-4 control-label">Carat</label>
						<div class="col-sm-6">
							<input
						type="text" id="totCarat" class="form-control" name="totCarat"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-4 form-group">
						<label for="totCompVal" class="col-sm-4 control-label">Finding Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totCompVal" class="form-control" name="totCompVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>

				
					
					<div class="col-sm-4 form-group">
						<label for="totNetWt" class="col-sm-4 control-label">Net Wt</label>
						<div class="col-sm-6">
							<input type="text" id="totNetWt" class="form-control"
								name="totNetWt" readonly="readonly" style="text-align: right;"/>
						</div>


					</div>
					
					<div class="col-sm-4 form-group">
						<label for="totStnVal" class="col-sm-4 control-label">Stone Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totStnVal" class="form-control" name="totStnVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-4 form-group">
						<label for="totFobVal" class="col-sm-4 control-label">Fob</label>
						<div class="col-sm-6">
							<input
						type="text" id="totFobVal" class="form-control" name="totFobVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>

			<div class="col-sm-4 form-group">
						<label for="totMetalVal" class="col-sm-4 control-label">Metal Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totMetalVal" class="form-control" name="totMetalVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>

				<div class="col-sm-4 form-group">
						<label for="totSetVal" class="col-sm-4 control-label">Setting Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totSetVal" class="form-control" name="totSetVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-4 form-group">
						<label for="totDiscAmt" class="col-sm-4 control-label">Discount</label>
						<div class="col-sm-6">
							<input
						type="text" id="totDiscAmt" class="form-control" name="totDiscAmt"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>

					
		
			<div class="col-sm-4 form-group">
						<label for="totLabVal" class="col-sm-4 control-label">Labour Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totLabVal" class="form-control" name="totLabVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>


				
					
					<div class="col-sm-4 form-group">
						<label for="totHdlgVal" class="col-sm-4 control-label">Hdlg Value</label>
						<div class="col-sm-6">
							<input
						type="text" id="totHdlgVal" class="form-control" name="totHdlgVal"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>

			

				<div class="col-sm-4 form-group">
						<label for="totFinalPrice" class="col-sm-4 control-label"  style="font-weight: bold; color: blue; font-size: large;">Final Price</label>
						<div class="col-sm-6">
							<input	type="text" id="totFinalPrice" class="form-control" name="totFinalPrice"
						readonly="readonly" style="font-weight: bold; color: blue; font-size: large;text-align: right;"/>
						</div>
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

		$('#displaySummary').on('click', function() {
			 
			popCostSummary();
		
		});
		})

		function popCostSummary(){
			
			$.ajax({
				url:"/jewels/manufacturing/transactions/costingMt/dtItemSummary.html?mtId="+$('#costMtId').val(),
				type :'GET',
				dataType:'JSON',
				success: function(data){
					
					
					if(data !== "-1"){
						
						$.each(data,function(k,v){
							
							
							$('#costSumDivId  #'+k).val(v);
						});
						
						
						
					}else{
						displayMsg(this,null,'Error Contact Admin');
					}
					
				}	
			})
		}


</script>
