<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="panel-body">

	<!-- <div class="container-fluid">
		<div class="row">
		
		

			<div id="consigDtSummaryDivId">
			
			
			<div class="row">

					<div class="col-sm-4 form-group">

						<label for="totPcs" class="col-sm-4 control-label">Pcs</label>
						<div class="col-sm-6">
							<input type="text" id="totPcs" class="form-control" name="totPcs"
								readonly="readonly" style="text-align: right;"/>
						</div>

					</div>
					
					
						<div class="col-sm-4 form-group">
						<label for="grossWt" class="col-sm-4 control-label">Gross Wt</label>
						<div class="col-sm-6">
							<input
						type="text" id="grossWt" class="form-control" name="grossWt"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>

				<div class="col-sm-4 form-group">
						<label for="netWt" class="col-sm-4 control-label">Net Wt</label>
						<div class="col-sm-6">
							<input
						type="text" id="netWt" class="form-control" name="netWt"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-4 form-group">
						<label for="pureWt" class="col-sm-4 control-label">Pure Wt</label>
						<div class="col-sm-6">
							<input
						type="text" id="pureWt" class="form-control" name="pureWt"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-4 form-group">
						<label for="stone" class="col-sm-4 control-label">Stone</label>
						<div class="col-sm-6">
							<input
						type="text" id="stone" class="form-control" name="stone"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-4 form-group">
						<label for="carat" class="col-sm-4 control-label">Carat</label>
						<div class="col-sm-6">
							<input
						type="text" id="carat" class="form-control" name="carat"
						readonly="readonly" style="text-align: right;"/> 
						</div>
					</div>
					
					<div class="col-sm-4 form-group">
						<label for="colStone" class="col-sm-4 control-label">Col Stone</label>
						<div class="col-sm-6">
							<input
						type="text" id="colStone" class="form-control" name="colStone"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					<div class="col-sm-4 form-group">
						<label for="colCarat" class="col-sm-4 control-label">Col Carat</label>
						<div class="col-sm-6">
							<input
						type="text" id="colCarat" class="form-control" name="colCarat"
						readonly="readonly" style="text-align: right;"/>
						</div>
					</div>
					
					
					


</div>
			
			
			</div>
			
			
			</div>
			
		</div> -->
		
		
		
		
	
</div>			



<script type="text/javascript">

$(document)
.ready(
		function(e) {

		$('#consigDtSummaryId').on('click', function() {
			 
			popConsigSummary();
		
		});
		})
		
		
			function popConsigSummary(){

			$.ajax({
				url:"/jewels/marketing/transactions/consigMt/dtSummary.html?mtId="+mtid,
				type :'GET',
				dataType:'JSON',
				success: function(data){

					
					
					if(data !== "-1"){
						
						$.each(data,function(k,v){

							console.log('k    '+k);
							console.log('v    '+v);
							
							$('#consigDtSummaryDivId  #'+k).val(v);
						});
						
					}else{
						displayMsg(this,null,'Error Contact Admin');
					}
					
				}	
			})
		}
		
		
		</script>