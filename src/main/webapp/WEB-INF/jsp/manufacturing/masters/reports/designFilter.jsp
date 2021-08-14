<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalDesign.jsp"%>

<%@ include file="/WEB-INF/jsp/common/modalDesignExcelUpload.jsp"%>


<%@ include file="/WEB-INF/jsp/common/modal.jsp"%>

<style>
textarea {
    resize: none;
}
</style>

<div class="panel panel-primary" style="width: 100%;">
	<div class="panel-heading" >
		<span id="heading" style="font-size: 18px;"></span>
	</div>

	<div class="panel-body">
	
		<!-- <label>filters</label> -->
	
		
		<div class="col-sm-12">
		
		
			 <div class="col-sm-3">
				<label>Design Group</label><span style="display:inline-block; width:3.6cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myDesignGroupModal" onclick="javascript:popDesignGroup(0)" ></button>
				<div>
					<textarea  name="designGrpTextBox" id="designGrpTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea> 
				</div>
			</div> 
		
		
			
			<div class="col-sm-3">
				<label>Category </label><span style="display:inline-block; width:4.4cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myCategoryModal" onclick="javascript:popCategory(0)" ></button>
				<div>
					<textarea  name="categTextBox" id="categTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea> 
				</div>
			</div>
			
			
			<div class="col-sm-3">
				<label>Sub Category</label><span style="display:inline-block; width:3.6cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#mySubCategoryModal" onclick="javascript:popSubCategory(0)" ></button>
				<div>
					<textarea  name="subCategTextBox" id="subCategTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea>
				</div>
			</div>
			
			<div class="col-sm-3">
				<label>Collection</label><span style="display:inline-block; width:4.3cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myCollectionModal" onclick="javascript:popCollection(0)" ></button>
				<div>
					<textarea  name="collectionTextBox" id="collectionTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea>
				</div>
			</div>
			
		</div>
		
		
		
		
		<div class="col-sm-12">
		
		
		    <div class="col-sm-3">
				<label>Concept</label><span style="display:inline-block; width:4.6cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myConceptModal" onclick="javascript:popConcept(0)" ></button>
				<div>
					<textarea  name="conceptTextBox" id="conceptTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea>
				</div>
			</div>
		
			<div class="col-sm-3">
				<label>Sub Concept</label><span style="display:inline-block; width:3.7cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#mySubConceptModal" onclick="javascript:popSubConcept(0)" ></button>
				<div>
					<textarea  name="subConceptTextBox" id="subConceptTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea> 
				</div>
			</div>
			
	<!-- 		<div class="col-sm-3">
				<label>Look </label><span style="display:inline-block; width:5.2cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myLookMasterModal" onclick="javascript:popLookMaster(0)" ></button>
				<div>
					<textarea  name="lookMasterTextBox" id="lookMasterTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea> 
				</div>
			</div>
		
			<div class="col-sm-3">
				<label>Pattern</label><span style="display:inline-block; width:4.8cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myPatternMasterModal" onclick="javascript:popPatternMaster(0)" ></button>
				<div>
					<textarea  name="patternMasterTextBox" id="patternMasterTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea> 
				</div>
			</div> -->
			
				<div class="col-sm-3">
				<label>Stone Type</label><span style="display:inline-block; width:4.1cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myStoneTypeModal" onclick="javascript:popStoneType(0)" ></button>
				<div>
					<textarea  name="stoneTypeTextBox" id="stoneTypeTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea> 
				</div>
			</div>
			
			
			<div class="col-sm-3">
				<label>Shape</label><span style="display:inline-block; width:5cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myShapeModal" onclick="javascript:popShape(0)" ></button>
				<div>
					<textarea  name="shapeTextBox" id="shapeTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea>
				</div>
			</div>
		
		</div>
		
		<div class="col-sm-12">
		
		
		
		     <div class="col-sm-3">
				<label>Size Group</label><span style="display:inline-block; width:4.1cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#mySizeGroupModal" onclick="javascript:popSizeGroup(0)" ></button>
				<div>
					<textarea  name="sizeGroupTextBox" id="sizeGroupTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea>
				</div>
			</div> 
		
		    <div class="col-sm-3">
				<label>Setting Type</label><span style="display:inline-block; width:3.8cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#mySettingTypeModal" onclick="javascript:popSettingType(0)" ></button>
				<div>
					<textarea  name="settingTypeTextBox" id="settingTypeTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea>
				</div>
			</div>
			
			<div class="col-sm-3">
				<label>Client</label><span style="display:inline-block; width:5.1cm; "></span>
					<button  class="glyphicon glyphicon-eject" data-toggle="modal" data-target="#myClientModal" onclick="javascript:popParty(0)" ></button>
				<div>
					<textarea  name="clientTextBox" id="clientTextBox" style="height: 2cm; width:7cm"  disabled="disabled" ></textarea>
				</div>
			</div>
			
		</div>
		
		
		
		<div class="col-sm-12">
			
				<div id="goldWtId" class="col-xs-3" >
			
							<label for="inputLabel4" class=".col-lg-2 text-right">Gold Wt </label>
							
						<div class="panel panel-primary" style="width: 92%;border-color: gray;">
						
						
						
							
							
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-6 col-sm-6" >
										<label for="inputLabel6" class=".col-lg-6 text-right">From Wt : </label>
									</div>
									
									
									<div class="col-lg-6 col-sm-6" >
										<label for="inputLabel6" class=".col-lg-6 text-right">To Wt : </label>
									</div>
									
									
									
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-xs-12">
								
								<div class="col-lg-6 col-sm-6" >
										<input type="text" class="form-control" name ="fromGoldWt" id="fromGoldWt"  />
									</div>
									
									<div class="col-lg-6 col-sm-6" >
										<input type="text" class="form-control" name ="toGoldWt" id="toGoldWt"  />
									</div>
								
								</div>
							</div>
							
							
							
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
							
						</div>
							
					</div>
			
			
			
			
			
			
					<div id="caratId" class="col-xs-3" >
					
					<label for="inputLabel4" class=".col-lg-2 text-right">Carat </label>
					
						<div class="panel panel-primary" style="width: 92%;border-color: gray;">
						
					
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-6 col-sm-6" >
										<label for="inputLabel4" class=".col-lg-6 text-right">FromCart: </label>
									</div>
									
									
									<div class="col-lg-6 col-sm-6" >
										<label for="inputLabel4" class=".col-lg-6 text-right">To Carat : </label>
									</div>
									
									
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-xs-12">
								
									<div class="col-lg-6 col-sm-6" >
										<input type="text" class="form-control" name ="fromCarat" id="fromCarat"  />
									</div>
									
									<div class="col-lg-6 col-sm-6" >
										<input type="text" class="form-control" name ="toCarat" id="toCarat"  />
									</div>
								
								</div>
							</div>
							
							
							
							
							
							
							
							
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
							
						</div>
					
					</div>	
			
			
			
			
				
				
				<div id="betweenPeriod" class="col-xs-3" >	
					
					<label for="inputLabel4" class=".col-lg-2 text-right">Between Period </label>
					
						<div class="panel panel-primary" style="width: 92%;border-color: gray;">
						
						
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-6 col-sm-6" >
										<label for="inputLabel6" class=".col-lg-6 text-right">From Dt : </label>
									</div>
									
									<div class="col-lg-6 col-sm-6" >
										<label for="inputLabel4" class=".col-lg-6 text-right">To Dt : </label>
									</div>
									
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-xs-12">
								
									<div class="col-lg-6 col-sm-6" >
										<input type="text" class="form-control" name ="fromBetDate" id="fromBetDate" />
									</div>
									
									<div class="col-lg-6 col-sm-6" >
										<input type="text" class="form-control" name ="toBetDate" id="toBetDate" />
									</div>
								
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
							
						</div>
							
					</div>	
					
					
				<div id="betweenFinalPrice" class="col-xs-3" >	
					
					<label for="inputLabel4" class=".col-lg-2 text-right">Price Range </label>
					
						<div class="panel panel-primary" style="width: 92%;border-color: gray;">
						
						
							<div class="row">
								<div class="col-xs-12">
									<div class="col-lg-6 col-sm-6" >
										<label for="inputLabel6" class=".col-lg-6 text-right">From Price : </label>
									</div>
									
									<div class="col-lg-6 col-sm-6" >
										<label for="inputLabel4" class=".col-lg-6 text-right">To Price : </label>
									</div>
									
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-xs-12">
								
									<div class="col-lg-6 col-sm-6" >
										<input type="number" class="form-control" name ="fromPrice" id="fromPrice"  value="0"/>
									</div>
									
									<div class="col-lg-6 col-sm-6" >
										<input type="number" class="form-control" name ="toPrice" id="toPrice" value="0"/>
									</div>
								
								</div>
							</div>
							
							
							<div class="row">
								<div class="col-xs-12">&nbsp;</div>
							</div>
							
						</div>
							
					</div>	
			
			
			
		
		</div>
		
		
			
			<div class="row">
			
					
				<div class="col-xs-5">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" name="cancel" id="cancel" ><strong>&nbsp;Cancel</strong></label> &nbsp;&nbsp;&nbsp;
					<!-- <label><input type="checkbox" name="bbc" id="bbc"><strong>&nbsp;BBC</strong></label> &nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" name="all" id="all">&nbsp;<strong>All</strong></label> &nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" name="mustHave" id="mustHave">&nbsp;<strong>Must Have</strong></label>&nbsp;&nbsp;&nbsp;
					<label><input type="checkbox" name="msi" id="msi">&nbsp;<strong>MSI</strong></label> -->
				</div>
						
				<!-- <div class="col-xs-2">
					<select class="form-control" name="bbcType" id="bbcType">
							<option value="0">-- Select bbc type -- </option>
	 						<option value="BL">Blue</option>
	 						<option value="BK">Black</option>
	 						<option value="BW">Brown</option>
	 						<option value="BB">BK And BW</option>
	 						<option value="CN">Canary</option>
	 						<option value="MD">Modify</option>
	 						<option value="PN">Pink</option>
					</select>
				</div> -->
					
				<div class="col-xs-5" align="left">
					<input type="button" value="Load Data" class="btn btn-warning" onclick="javascript:popDesignDetails()" />
					<input type="hidden" name="pDesignGroupIds" id="pDesignGroupIds" value="" />
					<input type="hidden" name="pCategoryIds" id="pCategoryIds" value="" />
					<input type="hidden" name="pSubCategoryIds" id="pSubCategoryIds" value=""  />
				    <input type="hidden" name="pConceptIds" id="pConceptIds" value=""  />
					<input type="hidden" name="pSubConceptIds" id="pSubConceptIds" value=""  />
					<input type="hidden" name="pStoneTypeIds" id="pStoneTypeIds" value=""  />
					<input type="hidden" name="pShapeIds" id="pShapeIds" value=""  />
					<input type="hidden" name="pSizeGroupIds" id="pSizeGroupIds" value=""  />
					<input type="hidden" name="pSettingTypeIds" id="pSettingTypeIds" value=""  />
					<input type="hidden" name="pFromGoldWt" id="pFromGoldWt"  value="" />
					<input type="hidden" name="pToGoldWt" id="pToGoldWt"  value=""  />
					<input type="hidden" name="pFromCarat" id="pFromCarat"  value=""  />
					<input type="hidden" name="pToCarat" id="pToCarat"  value=""  />  
					<input type="hidden" name="pFromBetDate" id="pFromBetDate"  value=""  />  
					<input type="hidden" name="pToBetDate" id="pToBetDate"  value=""  />
					<input type="hidden" name="cancelStatus" id="cancelStatus"  value=""  />
					<input type="hidden" name="bbcStatus" id="bbcStatus"  value=""  />
					<input type="hidden" name="allStatus" id="allStatus"  value=""  />
					<input type="hidden" name="mustHaveStatus" id="mustHaveStatus"  value=""  />
					<input type="hidden" name="msiStatus" id ="msiStatus" value="" />
					<input type="hidden" name="mCondStr" id="mCondStr"  value=""  />
     				 <input type="hidden" name="pCollectionIds" id="pCollectionIds" value=""  />
     				 <input type="hidden" name="pFromPrice" id="pFromPrice"  value=""  />
					<input type="hidden" name="pToPrice" id="pToPrice"  value=""  />  
					<input type="hidden" name="pLookIds" id="pLookIds"  value=""  />
					<input type="hidden" name="pPatternIds" id="pPatternIds"  value=""  />
					<input type="hidden" name="pClientIds" id="pClientIds"  value=""  />
					
				</div>
			
			</div>
		
		
		
		
		
		
		
	<div id="designDetailsTable">
		  
		  
		  	<div class="col-xs-12">
				<hr style="width: 100%; color: red; height: 3px; background-color: darkblue;" />
		    </div>
		  
			<div class="container-fluid">
					<div class="row" id="forDesignDetailsTab">
	
						<div id="odDivId" class="col-xs-10">
						<div class="col-xs-3">
							<input type="search" id="searchDesignFilterList"  class="search form-control" placeholder="Search" />
						</div>
						
						<div>&nbsp;</div>
						
							<div>
								<table id="designDetailTableId"  data-toggle="table"
									data-toolbar="#toolbar" data-pagination="true"
									data-side-pagination="server"
									data-page-list="[5, 10, 20, 50, 100, 200, 500, 1000]" data-height="350"   >
									<thead>
										<tr class="btn-primary">
											<th data-field="state" data-checkbox="true"></th>
											<th data-field="styleNo" data-align="left">Design No</th>
											<th data-field="group" data-align="left">Group</th>
											<th data-field="category" data-align="left">Category</th>
											<th data-field="subCategory" data-align="left">SubCategory</th>
											<th data-field="grossWt" data-align="right">Gross Wt</th>
											<th data-field="stone" data-align="right">Stone</th>
											<th data-field="carat" data-align="right">Carat</th>
										</tr>
									</thead>
								</table>
							</div>
								
								
								
						</div>
		
		
		
						<div id="odImgDivId" class="col-xs-2 center-block">
							
							<div style="height:52px">&nbsp;</div>
							<div class="panel panel-primary" style="width:100%; height:267px">
								<div class="panel-body">
									<div style="width:100%; height:142px">
										<div class="row center-block">
											<span id='styleImgId'></span> 
											<!-- <a id="oImgHRId" href="" data-lighter>
												<img id="ordImg" class="img-responsive" src="/jewels/uploads/manufacturing/blank.png" />
											</a> -->
											<div id="tempImgDivId">
										
									</div>
											
										</div>
									</div>
									<div style="height:15px">&nbsp;</div>
									<div class="pull-left">
										<table id='stoneDtlsId' style="width:100%"
											class="line-items editable table table-bordered">
										</table>
									</div>
								</div>
							</div>
						</div>
						
					</div>
		
					
			</div>
			
			
			<div class="row">
			   <div class="col-xs-12">&nbsp;</div>
			</div>
				
			
	 </div>	
		
		
		
	<div class="row">
		<div class="form-group">
			<div class="col-xs-12">
				<span class="col-lg-12 col-sm-12 label label-default" style="font-size: 16px;">Selected Design List</span>
			</div>
		</div>
	</div>
		
		
		<div>&nbsp;</div>
		
		
		<div class="container-fluid">
			<div class="row">
				
				<div id="toolbar2"></div>
				<div class="col-xs-3">
					
					<input type="search" id="searchFinalDesignList"  class="search form-control" placeholder="Search" />
						
				</div>
				
				<div class="col-xs-2">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onclick="javascript:designUploadExcel();"> <span
							class="glyphicon glyphicon-plus"></span>&nbsp;Upload Excel
						</a>
						</div>
						
				
				&nbsp;<a href="javascript:popDeleteAllFinalList()" class="glyphicon glyphicon-refresh" style="color: #F97203;text-decoration: none;"><span class="glyphicon glyphicon-trash"></span>All</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:deleteFinalTableOnSelect()" class="glyphicon glyphicon-refresh" style="color: #CC03F9 ;text-decoration: none;"><span class="glyphicon glyphicon-trash"></span>Selected</a>
				
				
				
				<div>
					<table id="finaldesignListTableId"  data-toggle="table"
						data-toolbar="#toolbar2" data-pagination="false"
						data-side-pagination="server"
					    data-height="450">
						<thead>
							<tr class="btn-primary">
								<th data-field="state" data-checkbox="true"></th>
								<th data-field="styleNo" data-align="left">Design No</th>
								<th data-field="group" data-align="left">Group</th>
								<th data-field="category" data-align="left">Category</th>
								<th data-field="subCategory" data-align="left">SubCategory</th>
								<th data-field="grossWt" data-align="right">Gross Wt</th>
								<th data-field="stone" data-align="right">Stone</th>
								<th data-field="carat" data-align="right">Carat</th>
							</tr>
						</thead>
					</table>
				</div>
						
				<!-- <div id="content"></div>
				<div id="dynapagin"></div>	 -->
						
						
				
		
			 </div>

			
		</div>
		
		
		
		
	<!-- report format ang grouping filters -->
	
 	<div class="col-xs-12">
		
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<form:form commandName="design" id="dispDesignRptId"
			action="/jewels/manufacturing/masters/reports/displayReports.html"
			cssClass="form-horizontal dispDesignRptIdFilterForm">
			
			<div align="left" style="font-style : italic  ;color: red ; font-weight: bold;  ">
				<span style="font-size: 17px ;">&nbsp;&nbsp;&nbsp;Grouping / Ordering</span>
			</div> 
				
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Level 1 :</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Level 2 :</label>
					</div>
					<div class="col-lg-3 col-sm-3">
						<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Level 3 :</label>
					</div>
					
					
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
						 <div id="img1Id">
					   		<form:select path="image1"  class="form-control" onchange="javascript:popLevelTwo();popLevelThree();">
								<form:option value="" label="- Select Level 1 -" />
								<form:options items="${comboLevelOne}" />
							</form:select>
						 </div>
					</div>
					<div class="col-lg-3 col-sm-3">
						<div id="img2Id">
						   		<form:select path="image2"  class="form-control" onchange="javascript:popLevelOne();popLevelThree();">
									<form:option value="" label="- Select Level 2 -" />
									<form:options items="${comboLevelTwo}" />
								</form:select>
							</div>
					</div>
					<div class="col-lg-3 col-sm-3">
						<div id="img3Id">
						   		<form:select path="image3"  class="form-control" onchange="javascript:popLevelTwo();popLevelOne();">
									<form:option value="" label="- Select Level 3 -" />
									<form:options items="${comboLevelThree}" />
								</form:select>
							</div>
					</div>
					
					
				</div>
			</div>
				
				
				
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>	
			
			
			<div id="reportFormatId" style="display: block">
			
				<div align="left" style="font-style : italic  ;color: red ; font-weight: bold;  ">
					<span style="font-size: 17px ;">&nbsp;&nbsp;&nbsp;Report Format</span>
				</div> 
					
				<div class="row">
					<div class="col-xs-12">
					<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Purity :</label>
						</div>
						<div class="col-lg-3 col-sm-3">
							<label for="inputLabel3" class=".col-lg-3 text-left" style="font-size: 13px;">Format :</label>
						</div>
					</div>	
				</div>	
				
				<div class="row">
					<div class="col-xs-12">
						<div class="col-lg-3 col-sm-3">
							<form:select path="purity.id"  class="form-control" >
										<form:option value="" label="- Select Purity -" />
										<form:options items="${purityMap}" />
							</form:select>
						</div>
						
						<div class="col-lg-3 col-sm-3">
							<form:select path="defaultImage"  class="form-control" >
										<form:option value="" label="- Select Format -" />
										<form:options items="${reportFormat}" />
							</form:select>
						</div>
					</div>	
				</div>	
				
			</div>
				
				
				
				
				

		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		<div class="row">
			<div class="col-xs-12">&nbsp;</div>
		</div>
		
		
		<div class="col-xs-12">
			<div class="row">
				<div class="col-sm-2"></div>
				  <div class="col-sm-2" >
					<input type="submit" value="Generate Report" name="genRpt" id="genRpt" class="btn btn-success" style="text-decoration:inherit; "/>
					<input type="hidden" name="xml" id="xml" value="${xml}"  />
					<input type="hidden" name="pStyleIds" id="pStyleIds" value=""  />
					<input type="hidden" name="pGroup1" id="pGroup1" />
					<input type="hidden" name="pGroup2" id="pGroup2" />
					<input type="hidden" name="pGroup3" id="pGroup3" />
					<input type="hidden" name="pRepFormat" id="pRepFormat" />
					 <input type="hidden" name="pmOpt" 	   id="pmOpt" value=""  />
				 </div>
				
				 <div class="col-sm-2" >
				 <input type="submit" value="Generate Excel" name="genRptExcel" id="genRptExcel" class="btn btn-danger" style="text-decoration:inherit; "/>
				 </div>
				
				 <div class="col-sm-2">
				 	<input type="button" value="Generate All Report" name="genRptAll" id="genRptAll" class="btn btn-warning" onclick="javascript:popAllReport(1)" />
					 	<input type="hidden" name="allpDesignGroupIds" id="allpDesignGroupIds"/>
						<input type="hidden" name="allpCategoryIds" id="allpCategoryIds"/>
						<input type="hidden" name="allpSubCategoryIds" id="allpSubCategoryIds"/>
					    <input type="hidden" name="allpConceptIds" id="allpConceptIds"/>
						<input type="hidden" name="allpSubConceptIds" id="allpSubConceptIds"/>
						<input type="hidden" name="allpStoneTypeIds" id="allpStoneTypeIds"/>
						<input type="hidden" name="allpShapeIds" id="allpShapeIds"/>
						<input type="hidden" name="allpSizeGroupIds" id="allpSizeGroupIds"/>
						<input type="hidden" name="allpSettingTypeIds" id="allpSettingTypeIds" />
						<input type="hidden" name="allpFromGoldWt" id="allpFromGoldWt"/>
						<input type="hidden" name="allpToGoldWt" id="allpToGoldWt"/>
						<input type="hidden" name="allpFromCarat" id="allpFromCarat"/>
						<input type="hidden" name="allpToCarat" id="allpToCarat"/>  
						<input type="hidden" name="allpFromBetDate" id="allpFromBetDate"/>  
						<input type="hidden" name="allpToBetDate" id="allpToBetDate" />
						<input type="hidden" name="allcancelStatus" id="allcancelStatus" />
						<input type="hidden" name="allbbcStatus" id="allbbcStatus"/>
						<input type="hidden" name="pallStatus" id="pallStatus"/>
						<input type="hidden" name="allmustHaveStatus" id="allmustHaveStatus"/>
						<input type="hidden" name="allmsiStatus" id="allmsiStatus"/>
						<input type="hidden" name="allmCondStr" id="allmCondStr"/>
						<input type="hidden" name="allLookIds" id="allLookIds"   />
						<input type="hidden" name="allPatternIds" id="allPatternIds"  />
						<input type="hidden" name="allClientIds" id="allClientIds"  />
						<input type="hidden" name="purityIds" id="purityIds"/>
						<input type="hidden" name="pAllOpt" id="pAllOpt"/>
						
						
				 </div>
				 
				  <div class="col-sm-2" >
				 <input type="submit" value="Generate All Excel" name="genRptAllExcel" id="genRptAllExcel" class="btn btn-danger" onclick="javascript:popAllReport(2)" style="text-decoration:inherit;"/>
				 </div>
				 
				<div class="col-sm-4"></div>
			
			</div>
		</div>
		
		
		
		
		</form:form>
		
		
	</div>		
		
		 
		
		
		
		
	<div style="display: none">
		<form:form target="_blank"
			action="/jewels/manufacturing/masters/reports/download/DesignCatlogReport.html"
			cssClass="form-horizontal orderFilter">
				<div class="form-group">
					<div class="col-lg-12 col-sm-12" style="text-align: center">
						<input type="submit" value="Generate Report" class="btn btn-primary" id="genDesignReport"/>
						<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
					</div>
				</div>
		</form:form>
	</div>
		
	
	
	<div style="display: none">
		<form:form target="_blank"
			action="/jewels/manufacturing/masters/reports/download/DesignCatlogReportAll.html"
			cssClass="form-horizontal orderFilter">
				<div class="form-group">
					<div class="col-lg-12 col-sm-12" style="text-align: center">
						<input type="submit" value="Generate Report All" class="btn btn-primary" id="genDesignReportAll"/>
						<input type="hidden" id="timeValCommonPdfAll" name="timeValCommonPdfAll" /> 
					</div>
				</div>
		</form:form>
	</div>	
		
		
		 <!-- Download Common Excel Report -->
			 
			 <div style="display: none">
				<form:form 	action="/jewels/manufacturing/masters/reports/download/Common/excelReport.html"
						cssClass="form-horizontal">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Excel Report" class="btn btn-primary" id="generateExcelReportss"/>
							</div>
						</div>
						
					<input type='hidden' name='pFileName' id='pFileName'/>
						
				</form:form>
			 </div>
			 
			 	 <!-- Download Common Excel Report All -->
			 
			 <div style="display: none">
				<form:form 	action="/jewels/manufacturing/masters/reports/download/Common/excelReportAll.html"
						cssClass="form-horizontal">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Excel Report" class="btn btn-primary" id="generateExcelReportssAll"/>
							</div>
						</div>
						
					<input type='hidden' name='pFileNameAll' id='pFileNameAll'/>
						
				</form:form>
			 </div>
	
	
	
	
	</div> <!-- ending the panel body -->
	
</div> <!-- ending the main panel -->


<script type="text/javascript">


$(document).ready(function(e){
	
	
	
	
	
	
	if($('#xml').val() === 'DesignBioData'){
		$('#reportFormatId').css('display','none');
		$("#heading").text("Design BioData");
	}else{
		$("#heading").text("Design Catalog");
		
	}
	
	
	
	
	$(".dispDesignRptIdFilterForm")
		.validate(
			{
				rules : {
					
					defaultImage : {
						required : true,
					},
					'purity.id' : {
						required : true,

					}
				},
				highlight : function(element) {
					$(element).closest(
							'.form-group')
							.removeClass(
									'has-success')
							.addClass('has-error');
				},
				unhighlight : function(element) {
					$(element)
							.closest('.form-group')
							.removeClass(
									'has-error')
							.addClass('has-success');
				},
				
			});
	

		
	//--------------------//
	
	
	 $("#fromBetDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		
		$("#toBetDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		
		

	//-----//------Search --- Function-------//
	
	
	
	//----------designGroup Search-------//

		$("#searchDesignGroup").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#designGroupIdTbl tr").each(function(index) {
		
		        if (index != 0) {
		        	
		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();  
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
	
	
		

	
	//----------category Search-------//
	
		$("#searchCategory").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#categoryIdTbl tr").each(function(index) {
		
		        if (index != 0) {
		        	
		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();  
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
	
	
	
	
		//----------subcategory Search-------//
		
		$("#searchSubCategory").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#subCategoryIdTbl tr").each(function(index) {
		
		        if (index != 0) {
		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();
		            var subCatId = $row.find('td:eq(2)').text();
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0 && subCatId.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
	
		
	//----------Concept Search-------//
		
		$("#searchConcept").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#conceptIdTbl tr").each(function(index) {
		
		        if (index != 0) {
		        	
		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();  
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
	
	
	//----------Collection Search-------//
		
		$("#searchCollection").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#collectionIdTbl tr").each(function(index) {
		
		        if (index != 0) {
		        	
		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();  
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
	

	//----------SubConcept Search-------//
		
		$("#searchSubConcept").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#subConceptIdTbl tr").each(function(index) {
		
		        if (index != 0) {
		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();
		            var subConId = $row.find('td:eq(2)').text();
		            
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0 && subConId.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
	
	
	
	//----------stoneType Search-------//
		
		$("#searchStoneType").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#stoneTypeIdTbl tr").each(function(index) {
		
		        if (index != 0) {

		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();  
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});

	
		//----------shape Search-------//
		
		$("#searchShape").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#shapeIdTbl tr").each(function(index) {
		
		        if (index != 0) {

		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();  
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
		
		
		//----------SizeGroup Search-------//
		
		$("#searchSizeGroup").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#sizeGroupIdTbl tr").each(function(index) {
		
		        if (index != 0) {

		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();  
		            var sizeGrpid = $row.find('td:eq(2)').text();  
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0 && sizeGrpid.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
		
		
	
	//----------SettingType Search-------//
		
		$("#searchSettingType").on("keyup", function() {
		    var value = $(this).val();
		    
		    $("#settingTypeIdTbl tr").each(function(index) {
		    	
		        if (index != 0) {
		            $row = $(this);
		            var id = $row.find('td:eq(1)').text();  
		            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
		                $(this).hide();
		            }
		            else {
		                $(this).show();
		            }
		        }
		    });
		});
	
	

		//----------Client Search-------//
			
			$("#searchClient").on("keyup", function() {
			    var value = $(this).val();
			    
			    $("#clientIdTbl tr").each(function(index) {
			    	
			        if (index != 0) {
			            $row = $(this);
			            var id = $row.find('td:eq(1)').text();  
			            if (id.toLowerCase().indexOf(value.toLowerCase()) != 0) {
			                $(this).hide();
			            }
			            else {
			                $(this).show();
			            }
			        }
			    });
			});

		
	
		
		//------final table search ------ //-----

		$("#searchFinalDesignList").on("keyup", function() {
			    var value = $(this).val();
			    
			    $("#finaldesignListTableId tr").each(function(index) {
			
			        if (index != 0) {
			        	
			            $row = $(this);
			            var id1 = $row.find('td:eq(0)').text();
			            var id2 = $row.find('td:eq(1)').text();
			            var id3 = $row.find('td:eq(2)').text();
			            var id4 = $row.find('td:eq(3)').text();
			            
			            if (id1.toLowerCase().indexOf(value.toLowerCase()) != 0 && id2.toLowerCase().indexOf(value.toLowerCase()) != 0 && 
			            		id3.toLowerCase().indexOf(value.toLowerCase()) != 0 && id4.toLowerCase().indexOf(value.toLowerCase()) != 0) {
			                $(this).hide();
			            }
			            else {
			                $(this).show();
			            }
			        }
			    });
			}); 
		
		
	
	
		//----------Design Details Search-------//
	
		$("#searchDesignFilterList").on("keyup", function() {
			
			 var value = $(this).val();
			 if(value.length > 3){
				 searchList(value);
			 }else if(value.length <= 0){
				 popDesignDetails();
			 }
			
			
		});
	
	
	
	
	}); //ending document ready function


	
	//--function to load data---//
		
	
	
		//--design Group-----//
		
		var desGrpStatus = "false";
		function popDesignGroup(val) {
			if(val === 0){
				if(desGrpStatus === 'false'){
					$("#designGroupIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/designGroup/listing.html?opt=3",
					});
					desGrpStatus = true;
				}
			}else{
				$("#designGroupIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/designGroup/listing.html?opt=3",
				});
				$('#designGrpTextBox').val('');
			}
			
		}
	
		
		function popDesignGrpSave(){
			
			$("#myDesignGroupModal").modal("hide");
			var	data = $('#designGroupIdTbl').bootstrapTable('getSelections');
			var	designGrpNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = designGrpNm.toString().replace(/,/g, "\n");
			$('#designGrpTextBox').val(tempRes);
			
		}
	
	

		
		//--category-----//
		
		var categStatus = "false";
		function popCategory(val) {
			if(val === 0){
				if(categStatus === 'false'){
					$("#categoryIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/category/listing.html?opt=3",
					});
					categStatus = true;
				}
			}else{
				$("#categoryIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/category/listing.html?opt=3",
				});
				$('#categTextBox').val('');
			}
			
			
				
		}
	
		
		function popCategSave(){
			
			$("#myCategoryModal").modal("hide");
			var	data = $('#categoryIdTbl').bootstrapTable('getSelections');
			var	categNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = categNm.toString().replace(/,/g, "\n");
			$('#categTextBox').val(tempRes);
		}

		
		
		
		//--subcategory-----//
		
		var subCategStatus = "false";
		function popSubCategory(val) {
			if(val === 0){
				if(subCategStatus === 'false'){
					$("#subCategoryIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/subCategory/listing.html?opt=3",
					});
					subCategStatus = true;
				}
			}else{
				$("#subCategoryIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/subCategory/listing.html?opt=3",
				});
				$('#subCategTextBox').val('');
			}
			
			
				
		}
	
		
		function popSubCategSave(){
			
			$("#mySubCategoryModal").modal("hide");
			var	data = $('#subCategoryIdTbl').bootstrapTable('getSelections');
			var	subCategNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = subCategNm.toString().replace(/,/g, "\n");
			$('#subCategTextBox').val(tempRes);
		}


		
		//--collection-----//
		
		var collectionStatus = "false";
		function popCollection(val) {
			if(val === 0){
				if(collectionStatus === 'false'){
					$("#collectionIdTbl").bootstrapTable('refresh', {
						url : '/jewels/manufacturing/masters/collection/listing.html?opt=3',
					});
					collectionStatus = true;
				}
			}else{
				$("#collectionIdTbl").bootstrapTable('refresh', {
					url : '/jewels/manufacturing/masters/collection/listing.html?opt=3',
				});
				$('#collectionTextBox').val('');
			}
			
			
				
		}
	
		
		function popCollectionSave(){
			
			$("#myCollectionModal").modal("hide");
			var	data = $('#collectionIdTbl').bootstrapTable('getSelections');
			var	collectionNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = collectionNm.toString().replace(/,/g, "\n");
			$('#collectionTextBox').val(tempRes);
		}
		
		
		

		//--concept-----//
		
		var conceptStatus = "false";
		function popConcept(val) {
			if(val === 0){
				if(conceptStatus === 'false'){
					$("#conceptIdTbl").bootstrapTable('refresh', {
						url : '/jewels/manufacturing/masters/concept/listing.html?opt=3',
					});
					conceptStatus = true;
				}
			}else{
				$("#conceptIdTbl").bootstrapTable('refresh', {
					url : '/jewels/manufacturing/masters/concept/listing.html?opt=3',
				});
				$('#conceptTextBox').val('');
			}
			
			
				
		}
	
		
		function popConceptSave(){
			
			$("#myConceptModal").modal("hide");
			var	data = $('#conceptIdTbl').bootstrapTable('getSelections');
			var	conceptNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = conceptNm.toString().replace(/,/g, "\n");
			$('#conceptTextBox').val(tempRes);
		}

		
		
		
		
		
		
		
		//--subconcept -----//
		
		var subConceptStatus = "false";
		function popSubConcept(val) {
			if(val === 0){
				if(subConceptStatus === 'false'){
					$("#subConceptIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/subConcept/listing.html?opt=3",
					});
					subConceptStatus = true;
				}
			}else{
				$("#subConceptIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/subConcept/listing.html?opt=3",
				});
				$('#subConceptTextBox').val('');
			}
			
		}
	
		
		function popSubConceptSave(){
			
			$("#mySubConceptModal").modal("hide");
			var	data = $('#subConceptIdTbl').bootstrapTable('getSelections');
			var	subConceptNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = subConceptNm.toString().replace(/,/g, "\n");
			$('#subConceptTextBox').val(tempRes);
			
		}
	
	

		
		//--Stonetype-----//
		
		var stoneTypeStatus = "false";
		function popStoneType(val) {
			if(val === 0){
				if(stoneTypeStatus === 'false'){
					$("#stoneTypeIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/stoneType/listing.html?opt=1"
					});
					stoneTypeStatus = true;
				}
			}else{
				$("#stoneTypeIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/stoneType/listing.html?opt=1"
				});
				$('#stoneTypeTextBox').val('');
			}
			
			
				
		}
	
		
		function popStoneTypeSave(){
			
			$("#myStoneTypeModal").modal("hide");
			var	data = $('#stoneTypeIdTbl').bootstrapTable('getSelections');
			var	stoneTypeNm = $.map(data, function(item) {
					return item.name;
				});
			
			var tempRes = stoneTypeNm.toString().replace(/,/g, "\n");
			$('#stoneTypeTextBox').val(tempRes);
		}

		
//--Look Master-----//
			
		var lookStatus = "false";
		function popLookMaster(val) {
			if (val === 0) {
				if (lookStatus === 'false') {
					$("#lookIdTbl").bootstrapTable('refresh', {
						url : "/jewels/manufacturing/masters/lookmast/listing.html?opt=3"
					});
					lookStatus = true;
				}
			} else {
				$("#lookIdTbl").bootstrapTable('refresh', {
					url : "/jewels/manufacturing/masters/lookmast/listing.html?opt=3"
				});
				$('#lookMasterTextBox').val('');
			}
	
		}
	
		function popLookSave() {

		$("#myLookMasterModal").modal("hide");
		var data = $('#lookIdTbl').bootstrapTable('getSelections');
		var lookNm = $.map(data, function(item) {
			return item.name;
		});

		var tempRes = lookNm.toString().replace(/,/g, "\n");
		$('#lookMasterTextBox').val(tempRes);
	}

	//--Pattern Master-----//

	var patternStatus = "false";
	function popPatternMaster(val) {
		if (val === 0) {
			if (patternStatus === 'false') {
				$("#patternIdTbl")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/manufacturing/masters/patternmast/listing.html?opt=3"
								});
				patternStatus = true;
			}
		} else {
			$("#patternIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/patternmast/listing.html?opt=3"
			});
			$('#patternMasterTextBox').val('');
		}

	}

	function popPatternSave() {

		$("#myPatternMasterModal").modal("hide");
		var data = $('#patternIdTbl').bootstrapTable('getSelections');
		var patternNm = $.map(data, function(item) {
			return item.name;
		});

		var tempRes = patternNm.toString().replace(/,/g, "\n");
		$('#patternMasterTextBox').val(tempRes);
	}

	
	//--shape-----//

	var shapeStatus = "false";
	function popShape(val) {
		if (val === 0) {
			if (shapeStatus === 'false') {
				$("#shapeIdTbl")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/manufacturing/masters/shape/listing.html?opt=3"
								});
				shapeStatus = true;
			}
		} else {
			$("#shapeIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/shape/listing.html?opt=3"
			});
			$('#shapeTextBox').val('');
		}

	}

	function popShapeSave() {

		$("#myShapeModal").modal("hide");
		var data = $('#shapeIdTbl').bootstrapTable('getSelections');
		var shapeNm = $.map(data, function(item) {
			return item.name;
		});

		var tempRes = shapeNm.toString().replace(/,/g, "\n");
		$('#shapeTextBox').val(tempRes);
	}

	//--Size Group-----//

	var sizeGroupStatus = "false";
	function popSizeGroup(val) {
		if (val === 0) {
			if (sizeGroupStatus === 'false') {
				$("#sizeGroupIdTbl")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/manufacturing/masters/sizeGroup/listing.html?opt=3"
								});
				sizeGroupStatus = true;
			}
		} else {
			$("#sizeGroupIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/sizeGroup/listing.html?opt=3"
			});
			$('#sizeGroupTextBox').val('');
		}

	}

	function popSizeGroupSave() {

		$("#mySizeGroupModal").modal("hide");
		var data = $('#sizeGroupIdTbl').bootstrapTable('getSelections');
		var sizeGroupNm = $.map(data, function(item) {
			return item.name;
		});

		var tempRes = sizeGroupNm.toString().replace(/,/g, "\n");
		$('#sizeGroupTextBox').val(tempRes);

	}

	//--Setting Type-----//

	var settingTypeStatus = "false";
	function popSettingType(val) {
		if (val === 0) {
			if (settingTypeStatus === 'false') {
				$("#settingTypeIdTbl")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/manufacturing/masters/settingType/listing.html?opt=3"
								});
				settingTypeStatus = true;
			}
		} else {
			$("#settingTypeIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/settingType/listing.html?opt=3"
			});
			$('#settingTypeTextBox').val('');
		}

	}

	function popSettingTypeSave() {

		$("#mySettingTypeModal").modal("hide");
		var data = $('#settingTypeIdTbl').bootstrapTable('getSelections');
		var settingTypeNm = $.map(data, function(item) {
			return item.name;
		});

		var tempRes = settingTypeNm.toString().replace(/,/g, "\n");
		$('#settingTypeTextBox').val(tempRes);

	}
	
	
	//--Client-----//

	var clientStatus = "false";
	function popParty(val) {
		if (val === 0) {
			if (clientStatus === 'false') {
				$("#clientIdTbl")
						.bootstrapTable(
								'refresh',
								{
									url : "/jewels/manufacturing/masters/client/listing.html"
								});
				clientStatus = true;
			}
		} else {
			$("#clientIdTbl").bootstrapTable('refresh', {
				url : "/jewels/manufacturing/masters/client/listing.html"
			});
			$('#clientTextBox').val('');
		}

	}

	function popClientSave() {

		$("#myClientModal").modal("hide");
		var data = $('#clientIdTbl').bootstrapTable('getSelections');
		var clientNm = $.map(data, function(item) {
			return item.name;
		});

		var tempRes = clientNm.toString().replace(/,/g, "\n");
		$('#clientTextBox').val(tempRes);

	}


	//--------popTable------------//---------//

	function popDesignDetails() {

		$
				.ajax({
					url : "/jewels/manufacturing/masters/reports/updateCount.html",
					type : 'GET',
					success : function(data) {

						$('#genRpt').removeAttr('disabled');

						$('html, body').animate({
							scrollTop : $("#designDetailsTable").offset().top
						}, 1000);

						var data = "";
						var ids = "";

						data = $('#designGroupIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pDesignGroupIds').val(ids);

						data = $('#categoryIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pCategoryIds').val(ids);

						data = $('#subCategoryIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pSubCategoryIds').val(ids);

						data = $('#conceptIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pConceptIds').val(ids);

						data = $('#collectionIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pCollectionIds').val(ids);

						data = $('#subConceptIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pSubConceptIds').val(ids);

						data = $('#stoneTypeIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pStoneTypeIds').val(ids);

						data = $('#shapeIdTbl').bootstrapTable('getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pShapeIds').val(ids);

						data = $('#sizeGroupIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pSizeGroupIds').val(ids);

						data = $('#settingTypeIdTbl').bootstrapTable(
								'getSelections');
						ids = $.map(data, function(item) {
							return item.id;
						});
						$('#pSettingTypeIds').val(ids);
						
						
								data = $('#lookIdTbl').bootstrapTable(
								'getSelections');
								ids = $.map(data, function(item) {
								return item.id;
								});
								$('#pLookIds').val(ids);
						
								data = $('#patternIdTbl').bootstrapTable(
								'getSelections');
								ids = $.map(data, function(item) {
								return item.id;
								});
								$('#pPatternIds').val(ids);
								
								data = $('#clientIdTbl').bootstrapTable(
								'getSelections');
								ids = $.map(data, function(item) {
								return item.id;
								});
								$('#pClientIds').val(ids);
								
								
								
								
				
						$('#pFromGoldWt').val($("#fromGoldWt").val());
						$('#pToGoldWt').val($("#toGoldWt").val());
						$('#pFromCarat').val($("#fromCarat").val());
						$('#pToCarat').val($("#toCarat").val());
						$('#pFromBetDate').val($("#fromBetDate").val());
						$('#pToBetDate').val($("#toBetDate").val());
						$('#pFromPrice').val($("#fromPrice").val());
						$('#pToPrice').val($("#toPrice").val());

						if ($('#cancel').prop('checked') == true) {
							$('#cancelStatus').val(1);
						} else {
							$('#cancelStatus').val(0);
						}

						if ($('#bbc').prop('checked') == true) {
							$('#bbcStatus').val(1);
						} else {
							$('#bbcStatus').val(0);
						}

						if ($('#all').prop('checked') == true) {
							$('#allStatus').val(1);
						} else {
							$('#allStatus').val(0);
						}

						if ($('#mustHave').prop('checked') == true) {
							$('#mustHaveStatus').val(1);
						} else {
							$('#mustHaveStatus').val(0);
						}

						if ($('#msi').prop('checked') == true) {
							$('#msiStatus').val(1);
						} else {
							$('#msiStatus').val(0);
						}

						$('#mCondStr').val($('#bbcType').val());

						$("#designDetailTableId")
								.bootstrapTable(
										'refresh',
										{

											url : "/jewels/manufacturing/masters/reports/designFilter/listing.html?pDesignGroupIds="
													+ $('#pDesignGroupIds')
															.val()
													+ "&pCategoryIds="
													+ $('#pCategoryIds').val()
													+ "&pSubCategoryIds="
													+ $('#pSubCategoryIds')
															.val()
													+ "&pConceptIds="
													+ $('#pConceptIds').val()
													+ "&pSubConceptIds="
													+ $('#pSubConceptIds')
															.val()
													+ "&pStoneTypeIds="
													+ $('#pStoneTypeIds').val()
													+ "&pShapeIds="
													+ $('#pShapeIds').val()
													+ "&pSizeGroupIds="
													+ $('#pSizeGroupIds').val()
													+ "&pSettingTypeIds="
													+ $('#pSettingTypeIds')
															.val()
													+ "&pFromGoldWt="
													+ $('#pFromGoldWt').val()
													+ "&pToGoldWt="
													+ $('#pToGoldWt').val()
													+ "&pFromCarat="
													+ $('#pFromCarat').val()
													+ "&pToCarat="
													+ $('#pToCarat').val()
													+ "&pFromPrice="
													+ $('#pFromPrice').val()
													+ "&pToPrice="
													+ $('#pToPrice').val()
													+ "&pFromBetDate="
													+ $('#pFromBetDate').val()
													+ "&pToBetDate="
													+ $('#pToBetDate').val()
													+ "&cancelStatus="
													+ $('#cancelStatus').val()
													+ "&bbcStatus="
													+ $('#bbcStatus').val()
													+ "&allStatus="
													+ $('#allStatus').val()
													+ "&mustHaveStatus="
													+ $('#mustHaveStatus')
															.val()
													+ "&pCollectionIds="
													+ $('#pCollectionIds')
															.val()
													+ "&mCondStr="
													+ $('#mCondStr').val()
													+ "&msiStatus="
													+ $('#msiStatus').val()
													+  "&pLookIds="
													+ $('#pLookIds').val()
													+  "&pPatternIds="
													+ $('#pPatternIds').val()
													+ "&pClientIds="
													+$('#pClientIds').val(),

										});

					}
				})

	}

	//----------searchList--------//----//

	function searchList(val) {

		// $('#searchCode').val(val);

		$("#designDetailTableId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/reports/designFilterSearch/listing.html?styleNm="
									+ val

						});

	}

	//----------//-----image display (on click)-------//

	$('#designDetailTableId')
			.bootstrapTable({})
			.on(
					'click-row.bs.table',
					function(e, row, $element) {

						var defImage = jQuery.parseJSON(JSON.stringify(row)).defaultImage;
						
						
						
						$('#tempImgDivId').empty();
						var tempDiv = '<a id="oImgHRId" href="/jewels/uploads/manufacturing/design/'+defImage+'" data-lighter> <img id="ordImgId" class="img-responsive" style="height: 100px" src="/jewels/uploads/manufacturing/design/'+defImage+'" /></a>'
						$('#tempImgDivId').append(tempDiv);
						

				/* 		if ((defImage != 'null')
								&& ($.trim(defImage).length > 0)) {
							$('#ordImg').attr(
									'src',
									'/jewels/uploads/manufacturing/design/'
											+ defImage);
							$('#oImgHRId').attr(
									'href',
									'/jewels/uploads/manufacturing/design/'
											+ defImage);
						} */

					});

	// ----------//-- Record Dump in Final Table (on Select)-----//

	$('#designDetailTableId')
			.bootstrapTable({})
			.on(
					'check.bs.table',
					function(e, row, $element) {

						//alert(row);
						var ids = $.map($("#finaldesignListTableId")
								.bootstrapTable('getData'), function(row) {
							return row.styleId;
						});

						ids = ids + '';
						var temp = ids.split(",");
						var tempStyleId = jQuery.parseJSON(JSON.stringify(row)).styleId;

						var status = true

						for (i = 0; i < temp.length; i++) {

							if (temp[i] === tempStyleId) {
								status = false;
							}

						}

						if (status) {

							$("#finaldesignListTableId")
									.bootstrapTable(
											'insertRow',
											{
												index : 0,
												row : {
													styleNo : jQuery
															.parseJSON(JSON
																	.stringify(row)).styleNo,
													group : jQuery
															.parseJSON(JSON
																	.stringify(row)).group,
													category : jQuery
															.parseJSON(JSON
																	.stringify(row)).category,
													subCategory : jQuery
															.parseJSON(JSON
																	.stringify(row)).subCategory,
													grossWt : jQuery
															.parseJSON(JSON
																	.stringify(row)).grossWt,
													stone : jQuery
															.parseJSON(JSON
																	.stringify(row)).stone,
													carat : jQuery
															.parseJSON(JSON
																	.stringify(row)).carat,
													styleId : jQuery
															.parseJSON(JSON
																	.stringify(row)).styleId,
												}
											});

						}

					});

	// ----------//-- Record Dump in Final Table (on Select All)-----//

	$('#designDetailTableId')
			.bootstrapTable({})
			.on(
					'check-all.bs.table',
					function(e, row, $element) {

						if ($('#finaldesignListTableId').bootstrapTable(
								'getData').length <= 3000) {

							var selData = JSON.stringify($(
									"#designDetailTableId").bootstrapTable(
									'getSelections'));
							var finalData = JSON.stringify($(
									"#finaldesignListTableId").bootstrapTable(
									'getData'));

							$
									.ajax({
										url : "/jewels/manufacturing/masters/reports/designFilter/finalTablelisting.html",
										data : {
											selData : selData,
											finalData : finalData
										},
										type : 'POST',
										success : function(data) {

											var obj = JSON.parse(data);
											$('#finaldesignListTableId')
													.bootstrapTable("load", obj);

										}
									})

						} else {
							displayMsg(this, null, ' Limit is over ');

						}

					});

	/* function popFinalTable(){
	
	$("#finaldesignListTableId")
	.bootstrapTable(
			'refresh',
			{
				
				//url : "/jewels/manufacturing/masters/reports/designFilter/finalTablelisting.html"
				"total":9,"rows": [{"id":"46659","styleNo":"ANC107 [  ] ","category":"ANCLET","subCategory":"ANC","group":"MK","grossWt":"13.99","stone":"294","carat":"1.646","defaultImage":"ANC107.jpg","styleId":"46659"},{"id":"46658","styleNo":"ANC103 [  ] ","category":"ANCLET","subCategory":"ANC","group":"MK","grossWt":"9.77","stone":"154","carat":"1.086","defaultImage":"ANC103.jpg","styleId":"46658"},{"id":"46657","styleNo":"ANC101 [  ] ","category":"ANCLET","subCategory":"ANC","group":"MK","grossWt":"9.11","stone":"14","carat":"0.246","defaultImage":"ANC101.jpg","styleId":"46657"},{"id":"46625","styleNo":"ANC102 [  ] ","category":"ANCLET","subCategory":"ANC","group":"MK","grossWt":"10.96","stone":"182","carat":"0.901","defaultImage":"ANC102.jpg","styleId":"46625"},{"id":"46623","styleNo":"ANC106 [  ] ","category":"ANCLET","subCategory":"ANC","group":"MK","grossWt":"9.64","stone":"42","carat":"0.224","defaultImage":"ANC106.jpg","styleId":"46623"}]
			});
	
	} */

	function deleteFinalTableOnSelect() {
		var ids = $.map($("#finaldesignListTableId").bootstrapTable(
				'getSelections'), function(row) {
			return row.styleId;
		});

		$("#finaldesignListTableId").bootstrapTable('remove', {
			field : 'styleId',
			values : ids
		});

	}

	function popDeleteAllFinalList() {

		/* $('#finaldesignListTableId').empty(); */
		var ids = $.map($("#finaldesignListTableId").bootstrapTable('getData'),
				function(row) {
					return row.styleId;
				});

		$("#finaldesignListTableId").bootstrapTable('remove', {
			field : 'styleId',
			values : ids
		});

	}

	//---------------//--generate report button------//
	
	
	//optional hidden parameter for toggle excel and report button  --
	
	var mOpt;
	$("#genRpt").click(function() {
		mOpt=0;		
		});
	
	$("#genRptExcel").click(function() {
		mOpt=1;
		
		});
	

	$(document)
			.on(
					'submit',
					'form#dispDesignRptId',
					function(e) {

						$
								.blockUI({
									message : '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>'
								});

						//$('#genRpt').attr('disabled', 'disabled');

						if(mOpt === 0){
							$('#genRpt').attr('disabled', 'disabled');
							}else if(mOpt === 1){
							$('#genRptExcel').attr('disabled', 'disabled');
							}

						var data = $('#finaldesignListTableId').bootstrapTable('getData');

						var ids = $.map(data, function(item) {
							return item.styleId;
						});
	
						$('#pStyleIds').val(ids);
						$('#pmOpt').val(mOpt);

						//----Group--------//--------//

						$('#pGroup1').val($('#image1').val());
						$('#pGroup2').val($('#image2').val());
						$('#pGroup3').val($('#image3').val());

						//-----report format-----//
						$('#pRepFormat').val($('#defaultImage').val());
						$('#purityIds').val($('#purity\\.id').val());	

						var postData = $(this).serializeArray();
						var formURL = $(this).attr("action");

				 	$
								.ajax({
									url : formURL,
									type : "POST",
									data : postData,

									success : function(data, textStatus, jqXHR) {

										$.unblockUI();

										if (data === '-1') {
											displayMsg(this, null,
													'Select Atleast One Record !');
										} else if (data === '-2') {
											displayMsg(this, null,
													'Error Occoured , contact admin !');
										} else {

								if (mOpt === 0) {
												$('#timeValCommonPdf')
														.val(data);
												$("#genDesignReport").trigger(
														'click');
											} else if (mOpt === 1) {

												$('#pFileName').val(data);
												$("#generateExcelReportss").trigger('click');
											}
										}

										$('#genRptExcel')
												.removeAttr('disabled');

										$('#genRpt').removeAttr('disabled');

									},
									error : function(jqXHR, textStatus,
											errorThrown) {

									}

								});

						e.preventDefault();

					});

	//-----level -- functions-------//

	function popLevelOne() {

		var tempLevOne = $('#image1').val();
		var pGroup = $('#image2').val() + "," + $('#image3').val();

		$
				.ajax({
					url : "/jewels/manufacturing/masters/reports/design/levelOne/combo.html?group="
							+ pGroup,
					type : "GET",
					success : function(data) {
						$('#img1Id').html(data);
						$("#image1 > [value=" + tempLevOne + "]").attr(
								"selected", "true");

					}
				});

	}

	function popLevelTwo() {

		var tempLevTwo = $('#image2').val();
		var pGroup = $('#image1').val() + "," + $('#image3').val();

		$
				.ajax({
					url : "/jewels/manufacturing/masters/reports/design/levelTwo/combo.html?group="
							+ pGroup,
					type : "GET",
					success : function(data) {
						$('#img2Id').html(data);
						$("#image2 > [value=" + tempLevTwo + "]").attr(
								"selected", "true");
					}
				});

	}

	function popLevelThree() {

		var tempLevThree = $('#image3').val();
		var pGroup = $('#image1').val() + "," + $('#image2').val();

		$
				.ajax({
					url : "/jewels/manufacturing/masters/reports/design/levelThree/combo.html?group="
							+ pGroup,
					type : "GET",
					success : function(data) {
						$('#img3Id').html(data);
						$("#image3 > [value=" + tempLevThree + "]").attr(
								"selected", "true");
					}
				});

	}



	function popAllReport(pAllopt) {
		
		

		if ((!!$('#defaultImage').val() && $('#purity\\.id').val() != '') || $('#defaultImage').val()=='Design Summary') {

			$
					.blockUI({
						message : '<h1><img src="/jewels/uploads/manufacturing/imgLoad.gif" /> Please Wait...</h1>'
					});

			if(pAllopt === 1){
				$('#genRptAll').attr('disabled', 'disabled');
					}else{
						$('#genRptAllExcel').attr('disabled', 'disabled');
						}
			

			var data = "";
			var ids = "";
			

			data = $('#designGroupIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allpDesignGroupIds').val(ids);

			data = $('#categoryIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allpCategoryIds').val(ids);

			data = $('#subCategoryIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allpSubCategoryIds').val(ids);

			data = $('#conceptIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allpConceptIds').val(ids);

			data = $('#subConceptIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allpSubConceptIds').val(ids);

			data = $('#stoneTypeIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allpStoneTypeIds').val(ids);

			data = $('#shapeIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allpShapeIds').val(ids);

			data = $('#sizeGroupIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allpSizeGroupIds').val(ids);

			data = $('#settingTypeIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allpSettingTypeIds').val(ids);

			data = $('#lookIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allLookIds').val(ids);

			data = $('#patternIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allPatternIds').val(ids);

			data = $('#clientIdTbl').bootstrapTable('getSelections');
			ids = $.map(data, function(item) {
				return item.id;
			});
			$('#allClientIds').val(ids);

			$('#allpFromGoldWt').val($("#fromGoldWt").val());
			$('#allpToGoldWt').val($("#toGoldWt").val());
			$('#allpFromCarat').val($("#fromCarat").val());
			$('#allpToCarat').val($("#toCarat").val());
			$('#allpFromBetDate').val($("#fromBetDate").val());
			$('#allpToBetDate').val($("#toBetDate").val());

			if ($('#cancel').prop('checked') == true) {
				$('#allcancelStatus').val(1);
			} else {
				$('#allcancelStatus').val(0);
			}

			if ($('#bbc').prop('checked') == true) {
				$('#allbbcStatus').val(1);
			} else {
				$('#allbbcStatus').val(0);
			}

			if ($('#all').prop('checked') == true) {
				$('#pallStatus').val(1);
			} else {
				$('#pallStatus').val(0);
			}

			if ($('#mustHave').prop('checked') == true) {
				$('#allmustHaveStatus').val(1);
			} else {
				$('#allmustHaveStatus').val(0);
			}

			if ($('#msi').prop('checked') == true) {
				$('#allmsiStatus').val(1);
			} else {
				$('#allmsiStatus').val(0);
			}

			$('#allmCondStr').val($('#bbcType').val());

			//----Group--------//--------//

			$('#pGroup1').val($('#image1').val());
			$('#pGroup2').val($('#image2').val());
			$('#pGroup3').val($('#image3').val());

			//-----report format-----//
			$('#pRepFormat').val($('#defaultImage').val());
			$('#purityIds').val($('#purity\\.id').val());

			$('#pAllOpt').val(pAllopt);

			$
					.ajax({
						url : "/jewels/manufacturing/masters/reports/popReport/all.html?pDesignGroupIds="
								+ $('#allpDesignGroupIds').val()
								+ "&pCategoryIds="
								+ $('#allpCategoryIds').val()
								+ "&pSubCategoryIds="
								+ $('#allpSubCategoryIds').val()
								+ "&pConceptIds="
								+ $('#allpConceptIds').val()
								+ "&pSubConceptIds="
								+ $('#allpSubConceptIds').val()
								+ "&pStoneTypeIds="
								+ $('#allpStoneTypeIds').val()
								+ "&pShapeIds="
								+ $('#allpShapeIds').val()
								+ "&pSizeGroupIds="
								+ $('#allpSizeGroupIds').val()
								+ "&pSettingTypeIds="
								+ $('#allpSettingTypeIds').val()
								+ "&pFromGoldWt="
								+ $('#allpFromGoldWt').val()
								+ "&pToGoldWt="
								+ $('#allpToGoldWt').val()
								+ "&pFromCarat="
								+ $('#allpFromCarat').val()
								+ "&pToCarat="
								+ $('#allpToCarat').val()
								+ "&pFromBetDate="
								+ $('#allpFromBetDate').val()
								+ "&pToBetDate="
								+ $('#allpToBetDate').val()
								+ "&cancelStatus="
								+ $('#allcancelStatus').val()
								+ "&bbcStatus="
								+ $('#allbbcStatus').val()
								+ "&allStatus="
								+ $('#pallStatus').val()
								+ "&mustHaveStatus="
								+ $('#allmustHaveStatus').val()
								+ "&mCondStr="
								+ $('#allmCondStr').val()
								+ "&pGroup1="
								+ $('#image1').val()
								+ "&pGroup2="
								+ $('#image2').val()
								+ "&pGroup3="
								+ $('#image3').val()
								+ "&pRepFormat="
								+ $('#defaultImage').val()
								+ "&xml="
								+ $('#xml').val()
								+ "&msiStatus="
								+ $('#allmsiStatus').val()
								+ "&pLookIds="
								+ $('#allLookIds').val()
								+ "&pPatternIds="
								+ $('#allPatternIds').val()
								+ "&pClientIds="
								+ $('#allClientIds').val()
								+ "&purityIds="
								+ $('#purityIds').val()
								+ "&pAllOpt="
								+ $('#pAllOpt').val(),

								
						type : "POST",
						success : function(data) {

							$.unblockUI();

							if (data === '-1') {
								displayMsg(this, null,
										'Select Atleast One Record !');
							} else if (data === '-2') {
								displayMsg(this, null,
										'Error Occoured , contact admin !');
							}else if (data === '-3') {
								displayMsg(this, null,'Report Not Appliacable For Generate All Button!');
								}
							 else {
								if(pAllopt === 1){
									$('#timeValCommonPdfAll').val(data);
									$("#genDesignReportAll").trigger('click');
								} else if (pAllopt === 2) {
											$('#pFileNameAll').val(data);
											$("#generateExcelReportssAll").trigger('click');
										}
								
							}

							$('#genRptAll').removeAttr('disabled');
							$('#genRptAllExcel').removeAttr('disabled');

						}
					});

		} else {

			displayMsg(this, null, 'Purity and Format is mandatary');

		}

	}

	function designUploadExcel() {
		$('#myDsignExcelUploadModal').modal('show');
		$('#fileDesign').val('');
	}

	function popDesignFinalListing() {
		$("#finaldesignListTableId")
				.bootstrapTable(
						'refresh',
						{
							url : "/jewels/manufacturing/masters/reports/designFilter/tableListing.html"
						});
	}
</script>


<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script	src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css"	rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />

<script src="<spring:url value='/js/common/blockUserInterface.js' />"></script>


