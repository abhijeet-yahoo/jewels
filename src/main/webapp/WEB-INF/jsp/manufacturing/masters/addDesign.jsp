<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>


<link href="/jewels/chosen/chosen.css" rel="stylesheet"/>

<script src="/jewels/chosen/chosen.jquery.js"></script>

<script src="/jewels/js/common/design.js"></script>

<c:if test="${success eq true}">
	<div class="col-xs-12">
		<div class="alert alert-success">Design ${action} successfully!</div>
	</div>
</c:if>

<c:if test="${success eq false}">
	<div class="col-xs-12">
		<div class="alert alert-danger"> ${action} !</div>
	</div>
</c:if>

<div class="form-group">
	<div id="mDivId" class="col-xs-12">
		<form:form commandName="design" id="designDivId"
			action="/jewels/manufacturing/masters/design/add.html"
			cssClass="form-horizontal designForm">
			<!-- For Order -->
			<div class="col-xs-12" id="oMsgId" style="display: none;">
				<!-- div class="row" -->
				&nbsp;
				<div class="alert alert-success message-text">Design ${action}
					successfully!</div>
				<!-- /div -->
			</div>
			<!-- For Order -->

			<!-- Column Sizing -->
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Group:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Create
							Date:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Design
							No:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Reference&nbsp;No:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Style
							No:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Version:</label>
					</div>
			</div>
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<form:select path="designGroup.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label="--- Select Group ---" />
							<form:options items="${groupList}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="designDt" cssClass="form-control"  style="font-size: 13px;" />
						<form:errors path="designDt" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="designNo" iddx="designDesignNo" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="designNo" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="refNo" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="refNo" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<%-- form:input path="styleNo" iddx="designStyleNo" cssClass="form-control" onBlur="javascript:getStyleDt();" / --%>
						<form:input path="styleNo" iddx="designStyleNo" cssClass="form-control" />
						<form:errors path="styleNo" />
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:input path="version" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="version" />
					</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Designer:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Category:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Sub
							Category:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Model
							/ Maker:</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Collection:</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Concept:</label>
					</div>
					
			</div>
			<div class="row">
					<div class="col-lg-2 col-sm-2">
						<form:select path="designerEmployee.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Designeer " />
							<form:options items="${designerMap}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:select path="category.id" class="form-control" style="font-size: 13px;"
							onChange="javascript:popSCat(this); javascript:popPSize(this);">
							<form:option value="" label="- Select Category -" />
							<form:options items="${categoryMap}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2" id="sCatId">
						<form:select path="subCategory.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label="- Select Sub Category ---" />
							<form:options items="${subCategoryMap}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:select path="modelMakerEmployee.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Model/Maker " />
							<form:options items="${modelMap}" />
						</form:select>
					</div>
					<div class="col-lg-2 col-sm-2">
						<form:select path="collectionMaster.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label="--- Select Collection ---" />
							<form:options items="${collectionMap}" />
						</form:select>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<form:select path="concept.id" class="form-control"
							onChange="javascript:popSCon(this);" style="font-size: 13px;">
							<form:option value="" label="--- Select Concept ---" />
							<form:options items="${conceptMap}" />
						</form:select>
					</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="row">
					
					
				 <div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Size:</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Process:</label>
					</div>	
					
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Wax
							Weight:</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">Silver
							Weight:</label>
					</div>
					
				<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Casted Weight:</label>
					</div> 
					
					
						<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Finish
							Weight:</label>
					</div>
			
				
					
					
			</div>
			<div class="row">
			
		
					<div class="col-lg-2 col-sm-2" id="pSizeId">
						<form:select path="productSize.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Size " />
							<form:options items="${productSizeMap}" />
						</form:select>
					</div>
					
						<div class="col-lg-2 col-sm-2">
						<form:select path="process.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label="--- Select Process ---" />
							<form:options items="${processMap}" />
						</form:select>
					</div>
					
					
					<div class="col-lg-2 col-sm-2">
						
							<form:input path="waxWt" cssClass="form-control" style="font-size: 13px;"  readonly="true"/>
						<form:errors path="waxWt" />
					</div> 
					  
					<div class="col-lg-2 col-sm-2">
						
						<form:input path="silverWt" cssClass="form-control" style="font-size: 13px;" readonly="true"/>
						<form:errors path="silverWt" />
					</div>
					
				<div class="col-lg-2 col-sm-2">
						<form:input path="grms" cssClass="form-control" style="font-size: 13px;" readonly="true" />
						<form:errors path="grms" />
					</div>
				
					<div class="col-lg-2 col-sm-2">
						<form:input path="finishWt" cssClass="form-control" style="font-size: 13px;" readonly="true" />
						<form:errors path="finishWt" />
					</div>	
					
				
					
			</div>
			<div class="row">
			
				<div class="col-xs-12">&nbsp;</div>
				
				
			
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Gross Weight:</label>
					</div>
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Cad Designer:</label>
					</div>
					
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Casted Pcs :</label>
					</div>
					

	
					<div class="col-lg-2 col-sm-2">
					<div class="checkbox" style="padding-left: 4px; ">
					<strong style="font-size: 13px;" >Exclusive Client :</strong>
							<form:checkbox path="exclusiveClient" value="1"  style="margin-left: 10px"/>
							
						</div>
						</div>
						
						
								<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Cust StyleNo :</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Cam Wt :</label>
					</div>
						
						
				</div>
				
				
				<div class="row">
				
				
					
					<div class="col-lg-2 col-sm-2">
						<form:input path="grossWt" cssClass="form-control" style="font-size: 13px;" readonly="true" />
						<form:errors path="grossWt" />
					</div>
					<div class="col-lg-2 col-sm-2">
					<form:select path="cadDesigner.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select CAD/Designer " />
							<form:options items="${cadDesignerMap}" />
						</form:select>
					</div>
				
				<div class="col-lg-2 col-sm-2">
						<form:input path="castedPcs" cssClass="form-control" style="font-size: 13px;"/>
						<form:errors path="castedPcs" />
					</div>
					
					<div class="col-lg-2 col-sm-2">
					<form:select path="party.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Client" />
							<form:options items="${partyMap}" />
						</form:select>
					</div>	
					
						
					<div class="col-lg-2 col-sm-2">
						<form:input path="custStyleNo" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="custStyleNo" />
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<form:input path="camWt" cssClass="form-control" style="font-size: 13px;"  />
						<form:errors path="camWt" />
					</div>
				
				
			</div>
			
		
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
				
					<div class="row">
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Sample Purity :</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Sample Wt :</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Model Modi Dt :</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Tot Stn :</label>
					</div>
					
							<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Tot Cts :</label>
					</div>
					
					<div class="col-lg-2 col-sm-2" style="display: none" id="laxmiMarketLabelDivId">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">MarketType
							:</label>
					</div>
			
					
				</div>
				
					<div class="row">
				
				
					
					<div class="col-lg-2 col-sm-2">
						<form:select path="purity.id" class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Purity" />
							<form:options items="${purityMap}" />
						</form:select>		
					</div>
				
				<div class="col-lg-2 col-sm-2">
						<form:input path="sampleWt" cssClass="form-control" style="font-size: 13px;"  />
						<form:errors path="sampleWt" />
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<form:input path="modelModiDt" cssClass="form-control"  style="font-size: 13px;" />
						<form:errors path="modelModiDt" />
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<form:input path="totStone" cssClass="form-control"  style="font-size: 13px;" readonly="true"/>
						<form:errors path="totStone" />
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<form:input path="totCarat" cssClass="form-control"  style="font-size: 13px;" readonly="true"/>
						<form:errors path="totCarat" />
					</div>
					
					<div class="col-lg-2 col-sm-2" style="display: none" id="laxmiMarketFieldDivId">
							<form:select path="marketTypeMast.id" class="form-control" style="font-size: 13px;">
								<form:option value="" label=" Select Market Type" />
								<form:options items="${marketTypeMastMap}" />
							</form:select>
						</div>
	
			
					
				</div>
				
				
				
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			
				<div class="row" style="display: none" id="laxmiLableDivId">

				 
				 <div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-left" style="font-size: 13px;">ProductType
							:</label>
					</div>
					
					
						<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Vendor :</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Vendor Style :</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Parent Style :</label>
					</div>
					
					<div class="col-lg-2 col-sm-2">
						<label for="inputLabel3" class=".col-lg-2 text-right" style="font-size: 13px;">Merchandiser :</label>
					</div>
					
				</div>
					
					<div class="row" style="display: none" id="laxmiFieldDivId">
					
						
	
					<div class="col-lg-2 col-sm-2">
							<form:select path="productTypeMast.id" class="form-control" style="font-size: 13px;">
								<form:option value="" label=" Select Product Type" />
								<form:options items="${productTypeMastMap}" />
							</form:select>
						</div>
					
					<div class="col-lg-2 col-sm-2">
					<form:select path="vendor.id"  class="form-control" style="font-size: 13px;">
							<form:option value="" label=" Select Vendor" />
							<form:options items="${vendorMap}" />
						</form:select>
					</div>	
					
					<div class="col-lg-2 col-sm-2">
						<form:input path="vendorStyle" cssClass="form-control"  style="font-size: 13px;" />
						<form:errors path="vendorStyle" />
					</div>
					
				<div class="col-lg-2 col-sm-2">
						<form:input path="parentStyle" cssClass="form-control"  style="font-size: 13px;" />
						<form:errors path="parentStyle" />
					</div>
					
					<div class="col-lg-2 col-sm-2">
							<form:select path="merchandiser.id" class="form-control" style="font-size: 13px;">
								<form:option value="" label=" Select Merchandiser" />
								<form:options items="${deptMerchandiserMap}" />
							</form:select>
						</div>
					
						</div>
				
				
				<div class="row">
				<div class="col-xs-12">&nbsp;</div>
				</div>
		
					
					<div class="row">
					<div class="col-lg-2 col-sm-2 text-right">
						<div class="checkbox">&nbsp;&nbsp;
							<form:checkbox path="silverModel" value="1" />
							<strong style="font-size: 13px;">Metal Mould</strong>
						</div>
					</div>
					<div class="col-lg-2 col-sm-2 text-center">
						<div class="checkbox">
							<form:checkbox path="cancel" value="1" />
							<strong style="font-size: 13px;">Cancel</strong>
						</div>
					</div>
					<%-- <div class="col-lg-2 col-sm-2 text-left">
						<div class="checkbox">
							<form:checkbox path="mustHave" value="1" />
							<strong style="font-size: 13px;">Must Have</strong>
						</div>
					</div> --%>
					<div class="col-lg-2 col-sm-2 text-left">
						<div class="checkbox">
							<form:checkbox path="rubberMould" value="1" />
							<strong style="font-size: 13px;">Rubber Mould</strong>
						</div>
					</div>
				
					
					<div class="col-lg-2 col-sm-2 text-left">
						<div class="checkbox">
							<form:checkbox path="liquidMould" value="1" />
							<strong style="font-size: 13px;">Liquid Mould</strong>
						</div>
					</div>
					
			
					
			</div>
			
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
		
				<div class="row">
					<div class="col-lg-12 col-sm-12">
						<label for="inputLabel3" class="text-right" style="font-size: 13px;">Remarks:</label>
						<form:input path="remarks" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="remarks" />
					</div>
			</div>
		
		
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
		
				<div class="row">
					<div class="col-lg-12 col-sm-12">
						<label for="inputLabel3" class="text-left" style="font-size: 13px;">Hold
							Remarks:</label>
						<form:input path="holdRemark" cssClass="form-control" style="font-size: 13px;" />
						<form:errors path="holdRemark" />
					</div>
			</div>
			<div class="row">
				<div class="col-xs-12">&nbsp;</div>
			</div>
			<div class="form-group">
				<div class="col-lg-12">
				
					<c:choose>
						<c:when test="${canAdd || canEdit}">
							<input type="submit" value="Save" class="btn btn-success" />
						</c:when>
						<c:otherwise>
							<input type="button" value="Save" class="btn btn-success" onClick="javascript:displayMsg(event, this)"/>
						</c:otherwise>
					</c:choose>
				
					 
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="oldStyleNo" />
					<input type="hidden" name="invImg1" id="invImg1" value="${image1}" />
					<input type="hidden" name="invImg2" id="invImg2" value="${image2}" />
					<input type="hidden" name="invImg3" id="invImg3" value="${image3}" />
					<input type="hidden" name="defaultImage" id="defaultImage" value="${defImgg}" />
					<input type="hidden" name="defImgChk" id="defImgChk" value="${defChk}"/>
					<input type="hidden" name="waxWtConv" id="waxWtConv" value="${waxWtConv}"  />
					<input type="hidden" name="imgChk" id="imgChk" />
					<input type="hidden" name="categoryId" id="categoryId" />
					<input type="hidden" name="collectionId" id="collectionId" />
					<input type="hidden" name="vendorId" id="vendorId"/>
					
					
				</div>
			</div>
		</form:form>
		
		
		<div style="display: none">		
				<form:form target="_blank"  id="styleNotMatchForm"
					action="/jewels/manufacturing/masters/reports/download/report.html"
					cssClass="form-horizontal orderFilter">
						<div class="form-group">
							<div class="col-lg-12 col-sm-12" style="text-align: center">
								<input type="submit" value="Generate Report" class="btn btn-primary" id="genDesignBiaDataRpt"/>
								<input type="hidden" id="timeValCommonPdf" name="timeValCommonPdf" /> 
							</div>
						</div>
				</form:form>
			</div>
		
		
		
	</div>


	<div id="rDivId" class="col-xs-3" style="display: none;">
		<div class="row">
			<div class="col-lg-12 col-sm-12">
				<label for="inputLabel3" class="control-label text-right" style="font-size: 13px;">Search by Style No:</label>
			</div>
			<div class="col-lg-12 col-sm-12">
				<input id="styleNoTmp" name="styleNoTmp" class="form-control" onblur="javascript:getStyleDt();" type="text">
			</div>
		</div>
		
		<div class="row">&nbsp;</div>

		<div class="panel panel-primary" style="width:100%; height:465px">
			<div class="panel-body"> 
				<div style="width:100%; height:325px; object-fit: cover" >
					<div class="row center-block">
						<span id='styleImgId'></span>
							 <a id="dImgHRId" href="" data-lighter>
								<img id="designImg" class="img-responsive " 
								src="/jewels/uploads/manufacturing/blank.png" />
							</a>
					</div>
				</div>
				<div class="row">&nbsp;</div>
				<div class="pull-center">
					<table id='stoneDtlsId' style="width:100%"
						class="line-items editable table table-bordered">
					</table>
				</div>
			</div>
		</div>
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
	</div> 
 </div>

<div class="row">
	<div class="form-group" style="display: none;"></div>
</div>
<div class="form-group" id="dsPId" style="display: none;">




	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Metal Details</span>
		</div>
	</div>

			<div class="col-xs-12">
				<div id="toolbarMetal">
				<c:choose>
					<c:when test="${canAdd}">
						<a class="btn btn-info" style="font-size: 15px" type="button"
						href="javascript:addMetal();"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Metal Details</a>
					</c:when>
					<c:when test="${stylenoautogeneration}">
						<a class="btn btn-info" style="font-size: 15px" type="button"
						href="javascript:addMetal();"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Metal Details</a>
					</c:when>
					<c:otherwise>
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onClick="javascript:displayMsg(event, this)"
							href="javascript:void(0)"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add Metal Details</a>
					</c:otherwise>
				</c:choose>
				
					
						
				</div>
				<div>
					<table id="metalTableId" data-toggle="table" data-toolbar="#toolbarMetal">
						<thead>
							<tr>
								<th data-field="partNm" data-sortable="false">Part</th>
								<th data-field="purity" data-sortable="false">Purity</th>
								<th data-field="color" data-align="left">Color</th>
								<th data-field="qty" data-sortable="false">Qty</th>
								<th data-field="perPcWaxWt" data-sortable="false">Per Pc Wax Wt</th>
								<th data-field="waxWt" data-sortable="false">Wax Wt</th>
								<th data-field="perPcSilverWt" data-sortable="false">Per Pc Silver Wt</th>
								<th data-field="silverWt" data-sortable="false">Silver Wt</th>
								<th data-field="rptWt" data-sortable="false">Rpt Wt</th>
								<th data-field="perPcMetalWt" data-sortable="false">Per Pc Metal Wt</th>
								<th data-field="metalWt" data-sortable="false">Metal Wt</th>
								<th data-field="metalRate" data-sortable="false">Metal Rate</th>
								<th data-field="perGramRate" data-sortable="false">Per Gram Rate</th>
								<th data-field="lossPerc" data-sortable="false">Loss %</th>
								<th data-field="metalValue" data-sortable="false">Metal Value</th>
								<th data-field="mainMetal" data-formatter="mianMetalFormatter">Main Metal</th>
						
								<th data-field="action1" data-align="center">Edit</th>
								<th data-field="action2" data-align="center">Delete</th>
						
							
							</tr>
						</thead>
					</table>
				</div>
			</div>
			
			
			
			<div class="form-group">
				<div id="rowMetalDtId">
				</div>
			</div>
			

	
	
	<div class="row">
		<div class="col-sm-12">&nbsp;</div>
	</div>


	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Stone Details</span>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row" id="forOrderDS">
			<div class="col-xs-12">
				<div id="toolbarDS">
				<c:choose>
					<c:when test="${canAdd}">
						<a class="btn btn-info" style="font-size: 15px" type="button"
						href="javascript:goToSType();"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Stone Details</a>
					</c:when>
					<c:when test="${stylenoautogeneration}">
						<a class="btn btn-info" style="font-size: 15px" type="button"
						href="javascript:goToSType();"><span
						class="glyphicon glyphicon-plus"></span>&nbsp;Add Stone Details</a>
					</c:when>
					
					
					<c:otherwise>
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onClick="javascript:displayMsg(event, this)"
							href="javascript:void(0)"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add Stone Details</a>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${canEdit}">
						<a class="btn btn-warning" style="font-size: 15px" type="button"
						href="javascript:updateStnRate();">&nbsp;Update Stn Rate</a>
					</c:when>
					<c:otherwise>
						<a class="btn btn-warning" style="font-size: 15px" type="button"
							onClick="javascript:displayMsg(event, this)"
							href="javascript:void(0)">&nbsp;Update Stn Rate</a>
					</c:otherwise>
				</c:choose>
				
					
						
						
				</div>
				<div>
					<table id="stoneDId" data-toggle="table" data-toolbar="#toolbarDS">
						<thead>
							<tr class="clickable">
								<th data-field="statechk" data-checkbox="true"></th>
								<th data-field="partNm" data-sortable="false">Part</th>
								<th data-field="stoneType" data-sortable="false">Stone Type</th>
								<th data-field="shape" data-align="left" data-sortable="false">Shape</th>
								<th data-field="subShape" data-sortable="false">Sub Shape</th>
								<th data-field="quality" data-sortable="false">Quality</th>
								<th data-field="breadth" data-sortable="false">Width</th>
								<th data-field="mm" data-sortable="false">Size/MM</th>
								<th data-field="sieve" data-sortable="false">Sieve</th>
								<th data-field="sizeGroup" data-sortable="false">Size Group</th>
								<th data-field="stones" data-sortable="false">Stone</th>
								<th data-field="carat" data-sortable="false">Carat</th>
								<th data-field="mcarat" data-sortable="false">MCarat</th>
								<th data-field="stnRate" data-sortable="false">Stone Rate</th>		
								<th data-field="setting" data-sortable="false">Setting</th>
								<th data-field="setType" data-sortable="false">Set Type</th>
								<th data-field="diaCateg" data-sortable="false">Dia Categ</th>
								<th data-field="centerStone" data-formatter="centerStoneFormatter">Center Stn</th>
						
								<th data-field="action1" data-align="center">Edit</th>
								<th data-field="action2" data-align="center">Delete</th>
							
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>

		<c:set var="option" value="Design Item" />
	</div>
	
	
		<div class="row">
		<div class="col-sm-12">
					<div class="col-sm-2" id="shapeDivId" >
							<select class="form-control">
							<option value="">Select Shape</option>
							</select>
							
							</div>
							
					<div class="col-sm-2" id="qltyDivId" >
							<select class="form-control">
							<option value="">Select Quality</option>
							</select>
							
					</div>
					<a class="btn btn-danger" type="button" onClick="javascript:updateQuality()">Update Quality</a>
					<span style="display:inline-block; width: 6cm;"></span>
					Total Stone : <input type="text" id="vTotalStones" name="vTotalStones" value="${totalStones}"
									maxlength="7" size="7" disabled="disabled" /> 
						&nbsp;&nbsp;
					Total Carat : <input type="text" id="vTotalCarats" name="vTotalCarats" value="${totalCarats}"  
									maxlength="7" size="7" disabled="disabled" />	
									</div>
				</div>


		<%-- 	<table class="line-items editable table">
				<tr class="panel-heading">
					<th>&nbsp;&nbsp;Total Stone : <input type="text"
						id="vTotalStones" name="vTotalStones" value="${totalStones}"
						maxlength="7" size="7" disabled="disabled" /> &nbsp;&nbsp;Total Carat
						: <input type="text" id="vTotalCarats" name="vTotalCarats"
						value="${totalCarats}" maxlength="7" size="7" disabled="disabled" />
					</th>
				</tr>
			</table> --%>

			<div id="rowOStnDtId">
			</div>


	
	
	
	
	
	<div class="row">
		<div class="col-sm-12">&nbsp;</div>
	</div>


	<div class="row">
		<div class="col-lg-12 col-sm-12">
			<span class="col-lg-12 col-sm-12 label label-default"
				style="font-size: 18px;">Component Details</span>
		</div>
	</div>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12">
				<div id="toolbarDC">
				<c:choose>
					<c:when test="${canAdd}">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							href="javascript:goToComponent();"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add Component Details</a>
					</c:when>
					<c:when test="${stylenoautogeneration}">
						<a class="btn btn-info" style="font-size: 15px" type="button"
							href="javascript:goToComponent();"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add Component Details</a>
					</c:when>
					
					
					<c:otherwise>
						<a class="btn btn-info" style="font-size: 15px" type="button"
							onClick="javascript:displayMsg(event, this)"
							href="javascript:void(0)"><span
							class="glyphicon glyphicon-plus"></span>&nbsp;Add Component Details</a>
					</c:otherwise>
				</c:choose>
				</div>
				<div>
					<table id="stoneDCId" data-toggle="table" data-toolbar="#toolbarDC"	>
						<thead>
							<tr>
								<th data-field="component">Component</th>
								<th data-field="purity">Purity</th>
								<th data-field="color">Color</th>
								<th data-field="quantity">Quantity</th>
								<th data-field="compWt">Component Wt</th>
						
								<th data-field="action1">Edit</th>
								<th data-field="action2">Delete</th>
								
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		
		
		
		<div class="form-group">
				<div id="rowComponentDtId">
				</div>
			</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
</div>

<script type="text/javascript">

var tempDesignId = 0;
var canViewFlag = false;
var viewFlg =false;
	$(document)
			.ready(
					function(e) {
						
						
					
					
						$('#stoneDId').on('click', '.clickable-row', function(event) {
							//alert("XXXX");
							  $(this).addClass('bg-info').siblings().removeClass('bg-info');
							}); 
						
						if('${companyName}' =='nuance'){


							
							$.validator.setDefaults({ ignore: ":hidden:not(select.form-control)" })
						
							if ($("select.form-control").length > 0) {
						    $("select.form-control").each(function() {
						        if ($(this).attr('required') !== undefined) {
						            $(this).on("change", function() {
						                $(this).valid();
						            });
						        }
						    });
						}
							
							$(".designForm")
							.validate(
									{
										rules : {
										
											designNo : {
												required : true,
											},
											styleNo : {
												required : true,
											},
											
											'designGroup.id':{
												required : true,
											},
											
											'cadDesigner.id':{
												required : true,
											},
											
											castedPcs : {
												required : true,
												greaterThan : "0,0",
											},
											'party.id' : {
												required : true,
											},
											'category.id' : {
												required : true,
											}
										},
										highlight : function(element) {
											$(element).closest('.form-group')
													.removeClass('has-success')
													.addClass('has-error');
										},
										unhighlight : function(element) {
											$(element).closest('.form-group')
													.removeClass('has-error')
													.addClass('has-success');
										},
										messages : {
											styleNo : {
												remote : "StyleNo With Version already exists"
											},
											version : {
												remote : "StyleNo plus Version already exists"
											},
											designNo : {
												remote : "DesignNo already exists"
											},
											castedPcs : {
												greaterThan : "This field is required"
											}
										}
									});
						}else{


							
							$.validator.setDefaults({ ignore: ":hidden:not(select.form-control)" })
						
							if ($("select.form-control").length > 0) {
						    $("select.form-control").each(function() {
						        if ($(this).attr('required') !== undefined) {
						            $(this).on("change", function() {
						                $(this).valid();
						            });
						        }
						    });
						}// end condition of validation for jquery chosen
						
							$(".designForm")
							.validate(
									{
										rules : {
							
											designNo : {
												required : true,
											},
											styleNo : {
												required : true,
											},
											'category.id' : {
												required : true,
											}
										},
										highlight : function(element) {
											$(element).closest('.form-group')
													.removeClass('has-success')
													.addClass('has-error');
										},
										unhighlight : function(element) {
											$(element).closest('.form-group')
													.removeClass('has-error')
													.addClass('has-success');
										},
										messages : {
											styleNo : {
												remote : "StyleNo With Version already exists"
											},
											version : {
												remote : "StyleNo plus Version already exists"
											},
											designNo : {
												remote : "DesignNo already exists"
											}
										}
									});					
						}
						
					

						$("#designNo").autocomplete({
							source : "/jewels/manufacturing/masters/designNo/list.html",
							minLength : 2
						});
						
						$("#styleNoTmp").autocomplete({
							source : "/jewels/manufacturing/masters/styleNo/list.html",
							minLength : 2,

						    change: function (event, ui) {
			                    if (ui.item == null || ui.item == undefined) {
			                        $("#styleNoTmp").val("");
			                    }
			                }
						});

						$("#parentStyle").autocomplete({
							source : "/jewels/manufacturing/masters/styleNo/list.html",
							minLength : 2,

						    change: function (event, ui) {
			                    if (ui.item == null || ui.item == undefined) {
			                        $("#parentStyle").val("");
			                    }
			                }
						});
						

						
						

						$(document).ready(function(e) {
							
							$.validator.setDefaults({ ignore: ":hidden:not(select.form-control)" })
						
							if ($("select.form-control").length > 0) {
						    $("select.form-control").each(function() {
						        if ($(this).attr('required') !== undefined) {
						            $(this).on("change", function() {
						                $(this).valid();
						            });
						        }
						    });
						}// end condition of validation for jquery chosen


							
							$(".designStoneForm").validate(
							{
								rules : {
									'partNm.id' :{
										required : true,
									},
									'stoneType.id' : {
										required : true,
									},
									'shape.id' : {
										required : true,
									},
									size : {
										required : true,
									},
									stone : {
										required : true,
									},
									carat : {
										required : true,
									},
									
								},
								highlight : function(element) {
									$(element).closest('.form-group')
											.removeClass('has-success')
											.addClass('has-error');
								},
								unhighlight : function(element) {
									$(element).closest('.form-group')
											.removeClass('has-error')
											.addClass('has-success');
								},
								messages : {
									name : {
										remote : "Design already exists"
									}
								}
							});
						});


						
						$.validator.setDefaults({ ignore: ":hidden:not(select.form-control)" })
					
						if ($("select.form-control").length > 0) {
					    $("select.form-control").each(function() {
					        if ($(this).attr('required') !== undefined) {
					            $(this).on("change", function() {
					                $(this).valid();
					            });
					        }
					    });
					}// end condition of validation for jquery chosen

					
						$(".designComponentForm").validate({
							rules : {
								'component.id' : {
									required : true,
								},
							},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
							}
						});

						$("#designDt").datepicker({
							dateFormat : 'dd/mm/yy'
						});
						
						$("#modelModiDt").datepicker({
							dateFormat : 'dd/mm/yy'
						});

						

						if ($('#defaultImage').val().trim().length > 0) {
							
							
								var imgName = $('#defaultImage').val();
								
								
								 $.ajax({
  									  url:'/jewels/uploads/manufacturing/design/'+ imgName,
  									  type:'HEAD',
   									 error: function()
								    {
								       // alert("in error");
								       $("#img1Lb").css("display", "block");
								       $("#img2Lb").css("display", "block");
								       $("#img3Lb").css("display", "block");
								    },
								    success: function()
								    {
								         
								        $('input#dfImage')[$('#defImgChk').val()-1].checked = true;
								       
								        $('#designImg').attr('src','/jewels/uploads/manufacturing/design/'+ imgName);
								        
								        
								        
								        $('#dImgHRId').attr('href','/jewels/uploads/manufacturing/design/'+ imgName);
								    }
								}); 
								

						}
						
						 if ($('#invImg1').val().indexOf('blank.png')) {
							$("#img1Lb").css("display", "block");
						}
						if ($('#invImg2').val().indexOf('blank.png')) {
							$("#img2Lb").css("display", "block");
						}
						if ($('#invImg3').val().indexOf('blank.png')) {
							$("#img3Lb").css("display", "block");
						} 
						
						

						$('input#dfImage').on(
								'change',
								function() {
									$('input#dfImage').not(this).prop('checked', false);
									var chkTmp = $('input[name="dfImage"]:checked').serialize();
									chkTmp = chkTmp.substring(chkTmp.indexOf('=')+1);
									
									var temp = "";
									
									if(chkTmp == 1){
										temp = $('#invImg1').val();
									}
									
									if(chkTmp == 2){
										temp = $('#invImg2').val();
									
									}
									
									if(chkTmp == 3){										
										temp = $('#invImg3').val();
										
									}
								
									$('#defaultImage').val(temp);
									$('#defImgChk').val(chkTmp);
								});
						
						 popShapeDropDown();


							
							
						if (window.location.href.indexOf('edit') != -1) {

							if('${stylenoautogeneration}' == 'true'){

								$('#laxmiLableDivId').css('display', 'block');
								$('#laxmiFieldDivId').css('display', 'block');
								$('#laxmiMarketLabelDivId').css('display', 'block');
								$('#laxmiMarketFieldDivId').css('display', 'block');
								
								
								$('#designDivId #vendorId').val($('#designDivId #vendor\\.id').val());
								$('#designDivId #categoryId').val($('#designDivId #category\\.id').val());
								$('#designDivId #collectionId').val($('#designDivId #collectionMaster\\.id').val());

								$('#designDivId #designNo').attr("readonly",true);
								$('#designDivId #styleNo').attr("readonly",true);
								
								$('#designDivId #category\\.id').attr("disabled","disabled");				
								$('#designDivId #collectionMaster\\.id').attr("disabled","disabled");				
								$('#designDivId #vendor\\.id').attr("disabled","disabled");		

								//combobox with jquery chosen
								$('#designDivId #designGroup\\.id').chosen( { width : '150px'});
								$('#designDivId #designerEmployee\\.id').chosen({width : '150px'});
								$('#designDivId #subCategory\\.id').chosen({width : '150px'});
								$('#designDivId #modelMakerEmployee\\.id').chosen({width : '150px'});
								$('#designDivId #concept\\.id').chosen({width : '150px'});
								$('#designDivId #productSize\\.id').chosen({width : '150px'});
								$('#designDivId #process\\.id').chosen({width : '150px'});
								$('#designDivId #cadDesigner\\.id').chosen({width : '150px'});
								$('#designDivId #party\\.id').chosen({width : '150px'});
								$('#designDivId #purity\\.id').chosen({width : '150px'});
								$('#designDivId #party\\.id').chosen({width : '150px'});
								$('#designDivId #marketTypeMast\\.id').chosen({width : '150px'});
								$('#designDivId #productTypeMast\\.id').chosen({width : '150px'});
								$('#designDivId #merchandiser\\.id').chosen({width : '150px'});
										

								}
							
							$("#mDivId").attr('class', 'col-xs-9');
							$("#rDivId").css('display', 'block');
							$("#dsPId").css('display', 'block');
							$('#copyDesignId').removeAttr('disabled','disabled');

							//$("#"+ document.querySelector("#disableDesignComponent").id).attr("id", "designComponent");
							$("#"+ document.querySelector("#disableDesignPurityWeight").id).attr("id", "designPurityWeight");
							$("#"+ document.querySelector("#disableUploadImages").id).attr("id", "uploadImages");
							$("#"+ document.querySelector("#disableDesignCost").id).attr("id", "designCost");
							
							var tempUrl = window.location.href;
							tempDesignId = tempUrl.substring(tempUrl.indexOf("edit/")+5,tempUrl.indexOf(".html"));
							popDesignPurityList();
							popDesignStone();
							sumDStoneDtls();
							popDesignMetal();
							popDesignComponent();
							
						}else if (window.location.href.indexOf('view') != -1) {
							canViewFlag = true;
							viewFlg = true;
							$("#mDivId").attr('class', 'col-xs-9');
							$("#rDivId").css('display', 'block');
							$("#dsPId").css('display', 'block');

							$("#"+ document.querySelector("#disableUploadImages").id).attr("id", "uploadImages");
							$("#"+ document.querySelector("#disableDesignCost").id).attr("id", "designCost");
							$("#"+ document.querySelector("#disableDesignPurityWeight").id).attr("id", "designPurityWeight");
							
							
							var tempUrl = window.location.href;
							tempDesignId = tempUrl.substring(tempUrl.indexOf("view/")+5,tempUrl.indexOf(".html"));
							
							popDesignStone();
							sumDStoneDtls();
							popDesignMetal();
							popDesignComponent();
							
							$('#designMainDivId :input').attr('disabled',true);
							$('#designMainDivId .btn').hide();
							$('#designListBtn').show();
							 $('#styleNoTmp').attr('disabled',false);
						}else {
							$("#designDt").val(new Date().toLocaleDateString('en-GB'));
							$("#modelModiDt").val(new Date().toLocaleDateString('en-GB'));
							$("#designNo").focus();
							$('#copyDesignId').attr('disabled',true);
						}
						
						
						
						$.validator.setDefaults({ ignore: ":hidden:not(select.form-control)" })
					
						if ($("select.form-control").length > 0) {
					    $("select.form-control").each(function() {
					        if ($(this).attr('required') !== undefined) {
					            $(this).on("change", function() {
					                $(this).valid();
					            });
					        }
					    });
					}// end condition of validation for jquery chosen
						
						
						$(".designMetalForm").validate({
							rules : {
								'purity.id' : {
									required : true,
								},
								'color.id' : {
									required : true,
								},
								'partNm.id' : {
									required : true,
								},
								metalPcs : {
									required : true,
									greaterThan : "0,0",
								},
							},
							highlight : function(element) {
								$(element).closest('.form-group')
										.removeClass('has-success')
										.addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group')
										.removeClass('has-error')
										.addClass('has-success');
							},
							messages : {
								metalPcs : {
									greaterThan : "This field is required"
								}
							},
						});
						
						
						
						
					});

	$('#stoneDId')
		.bootstrapTable({}).on('click-row.bs.table',
			function(e, row, $element) {
				$("#rowOStnDtId").css('display','none');
				 //$(this).addClass('bg-info').siblings().removeClass('active');
			});

	function calSlvrWt() {
		
		
		if (Number($('#waxWt').val()) > 0) {
			$('#silverWt').val(parseFloat($('#waxWt').val() * 10.5).toFixed(3));
			$('#grms').val(parseFloat($('#waxWt').val() * $('#waxWtConv').val()).toFixed(3));
		}
	}

	function calWaxWt() {
		if (Number($('#silverWt').val()) > 0) {
			$('#waxWt').val(parseFloat($('#silverWt').val() / 10.5).toFixed(3));
			$('#grms').val(parseFloat($('#waxWt').val() * $('#waxWtConv').val()).toFixed(3));
		}
	}

	function sumDStoneDtls() {
		$('#stoneDId')
				.bootstrapTable({})
				.on(
						'load-success.bs.table',
						function(e, data) {
							var data = JSON.stringify($("#stoneDId")
									.bootstrapTable('getData'));

							var cnt = 0;
							var htStones = new Object();
							var htCarat = new Object();

							var vStones = 0.0;
							var vCarat = 0.0;
							$.each(JSON.parse(data), function(idx, obj) {
								vStones += Number(obj.stones);
								vCarat += Number(obj.carat);

								if (htCarat.hasOwnProperty(obj.stoneType)) {
								} else {
									htCarat[obj.stoneType] = 0;
									htStones[obj.stoneType] = 0;
								}

								++cnt;
							});

							// show the values stored
							$
									.each(
											JSON.parse(data),
											function(idx, obj) {
												if (htCarat
														.hasOwnProperty(obj.stoneType)) {
													htCarat[obj.stoneType] = Number(htCarat[obj.stoneType])
															+ Number(obj.carat);
													htStones[obj.stoneType] = Number(htStones[obj.stoneType])
															+ Number(obj.stones);
												}
											});

							if (cnt > 0) {
								var str = '';
								str += '<tr style="font-size: 11px; width:100%;"><th>Stone Type</th><th class="text-right">Stones</th><th class="text-right">Carat</th></tr>';
								for ( var key in htCarat) {
									str += '<tr style="font-size: 11px"><td class="capitalise"><b>'
											+ key.toLowerCase()
											+ '</b></td><td class="text-right"><b>'
											+ htStones[key]
											+ '</b></td><td class="text-right"><b>'
											+ parseFloat(htCarat[key]).toFixed(
													3) + '</b></td></tr>';
								}
								$('#stoneDtlsId').html(str);
							}

							$('#vTotalStones').val(" " + vStones);
							$('#vTotalCarats').val(
									" " + parseFloat(vCarat).toFixed(3));

							$('#totStone').val(" " + vStones);
							$('#totCarat').val(
									" " + parseFloat(vCarat).toFixed(3));

							
						});
	}

/* 	function designDtDelete(e, id, dt) {
		doDelete(e, "javascript:deleteDesignDt(" + id + ", " + dt + ");");
	}

	function deleteDesignDt(id, dt) {
		$("#modalRemove").modal("hide");

		var url = "/jewels/manufacturing/masters/design/delete/" + id;
		if (dt == 1) {
			url = "/jewels/manufacturing/masters/designStone/delete/" + id;
		} else if (dt == 2) {
			url = "/jewels/manufacturing/masters/designComponent/delete/" + id;
		}

		$.ajax({
			url : url + ".html",
			type : 'GET',
			success : function(data, textStatus, jqXHR) {
				if (dt == 1) {
					popDesignStone();
					$('form#designStone').trigger("reset");
				} else if (dt == 2) {
					popDesignComponent();
					$('form#designComponent').trigger("reset");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
	}
 */
	function goToSType() {
		$("#rowOStnDtId").css('display','block');
		$.ajax({
			url : "/jewels/manufacturing/masters/designStone/add.html?designId="+tempDesignId,
			type : 'GET',
			success : function(data) {
				$("#dsId").val('');
				$("#rowOStnDtId").html(data);
				//$('#stoneType\\.id').focus();
				popPartNm(tempDesignId);
				
				$('#rowOStnDtId #partNm\\.id').chosen();
				$('#rowOStnDtId #stoneType\\.id').chosen().trigger('chosen:activate');
				$('#rowOStnDtId #shape\\.id').chosen();
				$('#rowOStnDtId #subShape\\.id').chosen();
				$('#rowOStnDtId #quality\\.id').chosen();
				$('#rowOStnDtId #size\\.id').chosen();
				$('#rowOStnDtId #setting\\.id').chosen();
				$('#rowOStnDtId #settingType\\.id').chosen();
				
			}
		});
	}

	function getStyleDt() {
		
		var vStyleNo = $("#styleNoTmp").val();
		
	/*  if(!viewFlg){
			designSave();
				}	 */
		
		
		$.ajax({
			
			url : "<spring:url value='/manufacturing/masters/design/getStyleId.html' />",
			type : "get",
			data : {
				styleNo : vStyleNo				
			},
			
			success : function(data) {
				
				if (data != '-1') {
					
					if (window.location.href.indexOf('view') != -1){
						window.location.href = '/jewels/manufacturing/masters/design/view/'+data+'.html';
					}else{
						window.location.href = '/jewels/manufacturing/masters/design/edit/'+data+'.html';	
					}
					
					
				}
			}
		});

	

		
	}

	var isEdit = false;
	function editDStone(dsId) {
		$("#rowOStnDtId").css('display','block');
		$.ajax({
			url : "/jewels/manufacturing/masters/designStone/edit/" + dsId
					+ ".html",
			type : 'GET',
			success : function(data) {
				$("#rowOStnDtId").html(data);

				$('#vSieve').val($('#sieve').val());
//				$('#vSizeGroupStr').val($('#sizeGroupStr').val());

				isEdit = true;

				
				$('#rowOStnDtId #partNm\\.id').chosen();
				$('#rowOStnDtId #stoneType\\.id').chosen();
				$('#rowOStnDtId #shape\\.id').chosen();
				$('#rowOStnDtId #subShape\\.id').chosen();
				$('#rowOStnDtId #quality\\.id').chosen();
				$('#rowOStnDtId #size\\.id').chosen();
				$('#rowOStnDtId #setting\\.id').chosen();
				$('#rowOStnDtId #settingType\\.id').chosen();
			}
		});
	}

	$(document).on('submit', 'form#designStone', function(e) {
		$('#dsStyleNo').val($('#styleNo').val());
		$('#dsVersion').val($('#version').val());
		$('#sieve').val($('#vSieve').val());
		$('#sizeGroupStr').val($('#vSizeGroupStr').val());

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
 				popDesignStone();
				sumDStoneDtls();
				updateGrossWt();
			//	$('form#designStone').trigger("reset");

				$('#rowOStnDtId #size').val('');
				$('#rowOStnDtId #vSieve').val('');
				$('#rowOStnDtId #vSizeGroupStr').val('');
				$('#rowOStnDtId #stone').val('');
				$('#rowOStnDtId #carat').val('');
				$('#rowOStnDtId #mcarat').val('');
				$('#rowOStnDtId #stnRate').val('');
				$('#rowOStnDtId #diaCateg').val('');
				$('#rowOStnDtId #breadth').val('');
				$('#rowOStnDtId #setting\\.id').val('').trigger('chosen:updated');
				$('#rowOStnDtId #settingType\\.id').val('').trigger('chosen:updated');
				$('form#designStone').trigger('chosen:reset');

				if (isEdit) {
					$("#rowOStnDtId").css('display','none');
					isEdit = false;
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});

	function getPointer() {
		$.ajax({
			url : '/jewels/manufacturing/masters/designStone/stonePointer.html',
			type : "GET",
			data : {shape : $("#shape\\.id :selected").text(), size : $("#size").val()} ,
			success : function(data, textStatus, jqXHR) {
				$("#carat").val(parseFloat(Number(data) * $("#stone").val()).toFixed(3));
				$("#mcarat").val(parseFloat(Number(data) * $("#stone").val()).toFixed(3));
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});		
	}

var isCompEdit=false;
	$(document).on('submit', 'form#designComponent', function(e) {
		$('#dcStyleNo').val($('#styleNo').val());
		$('#dcVersion').val($('#version').val());

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				
				if(data === '2'){
					popCancelDesignComponent();
					popDesignComponent();
					updateGrossWt();
				}else{
							
					popDesignComponent();
					updateGrossWt();
					
					$('#designComponent input[type="text"],textarea,input[type="password"]').val('');
					$('#designComponent input[type="number"]').val('0');
					$('#designComponent').find('input[type=checkbox]:checked').removeAttr('checked');
					$('#dcId').val('');
					$('#designComponent').find('select').val('');
				
				}
				
				popDesignComponent();
				updateGrossWt();
				
				$('form#designComponent').trigger("reset");
				if (isCompEdit) {
					$("#rowComponentDtId").css('display', 'none');
					isEdit = false;
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});

	
	function editDComponent(dsId) {
		$.ajax({
			url : "/jewels/manufacturing/masters/designComponent/edit/" + dsId
					+ ".html",
			type : 'GET',
			success : function(data) {
				$("#rowComponentDtId").css('display', 'block');
				$("#rowComponentDtId").html(data);
				isCompEdit=true;
				
				
				$('#rowComponentDtId #component\\.id').chosen();
				$('#rowComponentDtId #purity\\.id').chosen();
				$('#rowComponentDtId #color\\.id').chosen();
				
			}
		});
	}

	function goToComponent() {
		
		
		$("#rowComponentDtId").css('display','block');
		$.ajax({
			url : "/jewels/manufacturing/masters/designComponent/add.html",
			type : 'GET',
			success : function(data) {
				$("#dcId").val('');
				$("#rowComponentDtId").html(data);
				//$('#component\\.id').focus();

				
				$('#rowComponentDtId #component\\.id').chosen().trigger('chosen:activate');;
				$('#rowComponentDtId #purity\\.id').chosen();
				$('#rowComponentDtId #color\\.id').chosen();
				
			}
		});
		
		
		
		
		/* $.ajax({
			url : "/jewels/manufacturing/masters/designComponent/edit/0.html",
			type : 'GET',
			success : function(data) {
				$("#dcId").val('');

				$("#rowComponentDtId").css('display', 'block');
				$('form#designComponent input[type="text"],texatrea').val('');
				$('form#designComponent select').val('');
				$('#component\\.id').focus();
				isCompEdit=false;

			}
		}); */
	}

	function stonesTotal(fVal) {
		$('#vTotalStone').val('200');
		return "100";
	}
	
	
	function popCancelBtn(){
		$('#designStone').css('display','none');
	}

	
	
	function updateStnRate(){
		
		$.ajax({
			
			url:"/jewels/manufacturing/masters/updateDesignStnRate.html?styleId="+tempDesignId,
			type:"GET",
			success:function(data){
				
				if(data === '-1'){
					displayInfoMsg(this, null, ' Stone Rate Applied Successfully ! ');
				}else{
					displayMsg(this, null, ' Error occoured , contact admin ! ');
				}
				
			}
			
			
		})
		
		
	}
	
	
	function centerStoneFormatter(value) {
		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}
		
		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';
		
	}
	
	
	
	//----------------Metal details -------------//
	var defaultPurity;
	var defaultColor;
	function addMetal() {
		
		$("#rowMetalDtId").css('display','block');
		$.ajax({
			url : "/jewels/manufacturing/masters/designMetal/add.html",
			type : 'GET',
			success : function(data) {
				$("#desMetalId").val('');
				$("#rowMetalDtId").html(data);
				//$('#purity\\.id').focus();

				//Jquery Chosen ComboBox
				$('#rowMetalDtId #partNm\\.id').chosen();
				$('#rowMetalDtId #purity\\.id').chosen().trigger('chosen:activate');;
				$('#rowMetalDtId #color\\.id').chosen();
			}
		});
		
		
		setTimeout(function(){
			$.ajax({
				url : "/jewels/manufacturing/masters/designMetal/defPurity.html",
				type : 'GET',
				success : function(pData) {
					
					defaultPurity=pData;
					$('#designMetalId #purity\\.id').val(pData);
					
				}	
			});

			
		}, 200)
		
		
			setTimeout(function(){
			$.ajax({
				url : "/jewels/manufacturing/masters/designMetal/defColor.html",
				type : 'GET',
				success : function(pData) {
					defaultColor=pData;							
					$('#designMetalId #color\\.id').val(pData);
					
														
				}	
			});

			$('#designMetalId #metalPcs').val(1);
		}, 300)
		
		
	}
	
	
	
	$('#metalTableId').bootstrapTable({}).on('click-row.bs.table',
		function(e, row, $element) {
			$("#rowMetalDtId").css('display','none');
		});
	 
	
	function editMetal(id){
		$.ajax({
			url : "/jewels/manufacturing/masters/designMetal/edit/"+id+".html",
			type : 'GET',
			success : function(data) {
				$("#rowMetalDtId").css('display', 'block');
				$("#rowMetalDtId").html(data);


				//Jquery Chosen ComboBox
				$('#rowMetalDtId #partNm\\.id').chosen();
				$('#rowMetalDtId #purity\\.id').chosen();
				$('#rowMetalDtId #color\\.id').chosen();
			}
		});
	}
	
	
	function popDesignMetal(){
	  $("#metalTableId").bootstrapTable('refresh',{
					url : "/jewels/manufacturing/masters/designMetal/listing.html?designId="+tempDesignId+"&canViewFlag="+canViewFlag,
				});
	}
	
	
	$(document).on('submit', 'form#designMetalId', function(e) {
		$('#desPkId').val(tempDesignId);

		var postData = $(this).serializeArray();
		var formURL = $(this).attr("action");

		$.ajax({
			url : formURL,
			type : "POST",
			data : postData,
			success : function(data, textStatus, jqXHR) {
				
				
				
			
				if(data === '-2'){
					popCancelDesignMetal();
					popDesignMetal();
				

				}else if(data === '-3'){
					displayMsg(this,null,"only one record can be main metal");	
				}else if(data === '-4'){
					displayMsg(this,null,"Duplicate Entry of Part Found");
				}else{
					popDesignMetal();
					
					$('#designMetalId input[type="text"],textarea,input[type="password"]').val('');
					$('#designMetalId input[type="number"]').val('0');
					$('#designMetalId').find('input[type=checkbox]:checked').removeAttr('checked');
					$('#desMetalId').val('');
					$('#designMetalId').find('select').val('');	
					
					$('#designMetalId #color\\.id').val(defaultColor);
					$('#designMetalId #purity\\.id').val(defaultPurity);
				}
				
				setTimeout(function(){
					calculateMetalSum();	
				}, 500)
				
				
				
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
			}
		});
		e.preventDefault(); //STOP default action
	});

	function deleteDesignMetal(e,id){
		displayDlg(e, 'javascript:confirmDeleteDM('+id+');','Design Metal', 'Do You Want To Delete', 'Continue');
	}
	 
	
	function confirmDeleteDM(id){
		$("#modalDialog").modal("hide");
		
		$.ajax({
			url : "/jewels/manufacturing/masters/designMetal/delete/"+id+".html",
			type : 'GET',
			success : function(data) {
				popDesignMetal();
				setTimeout(function(){
					calculateMetalSum();	
				}, 500)
			}
		});
	}
	 
	
	function mianMetalFormatter(value) {
		var booleanValue;
		if (typeof (value) === "boolean") {
			booleanValue = (value);
		} else {
			booleanValue = (value == 'true');
		}
		
		var checked = (booleanValue ? (booleanValue == true ? 'checked' : '') : '');
		return '<input  type="checkbox" ' + checked + ' disabled="true"   />';
	}
	
	
	function popCancelDesignMetal(){
		$('#designMetalId input[type="text"],textarea,input[type="password"]').val('');
		$('#designMetalId input[type="number"]').val('0');
		$('#designMetalId').find('input[type=checkbox]:checked').removeAttr('checked');
		$('#desMetalId').val('');
		$('#designMetalId').find('select').val('');
		$("#rowMetalDtId").css('display','none');
	}
	
	
	
	function popCancelDesignComponent(){
		$('#designComponent input[type="text"],textarea,input[type="password"]').val('');
		$('#designComponent input[type="number"]').val('0');
		$('#designComponent').find('input[type=checkbox]:checked').removeAttr('checked');
		$('#dcId').val('');
		$('#designComponent').find('select').val('');
		$("#rowComponentDtId").css('display','none');
	}
	
	
	function popPartNm(tempId){
		$.ajax({
			url:"/jewels/manufacturing/masters/designMetal/getPartNm.html?designId="+tempId,
			type:"GET",
			success:function(data){
				console.log("data === "+data);
				if(data !== ""){
					$('#designStone #partNm\\.id').val(data);
				}
			}
		})
	}
	
	
	
	
	
	
	
	//------------------Design Copy -----//
	
	function popDesignCopySave(){
		
		if($('#designNoId').val()=== "" ||$('#styleNoId').val()=== ""){
			displayMsg(this, null, 'Please Insert Style no & Design No');
		}else {
			
			 $("#myDesignCopyModal").modal("hide");
				$.ajax({ 
					url : "/jewels/manufacturing/masters/design/designCopy.html",
					type : "POST",
					data : {
						styleId :tempDesignId,
						styleNo :$('#styleNoId').val(),		
						designNo :$('#designNoId').val(),
						versionNo:$('#versionId').val(),
					},
					 success : function(data, textStatus, jqXHR) {	
							
					 	 if(data.indexOf("success") != -1){
							 $("#myDesignCopyModal").modal("hide");

					 		 var pkId = data.split("_");
					
							 displayInfoMsg(this, null, 'Design Copy Successfully');
							 window.location.href ="/jewels/manufacturing/masters/design/edit/"+pkId[1]+".html";
							 
						  }else if(data ==="-1"){
							 displayMsg(this, null, 'Error: Style No Already Exists , Can Not Copy');
						 }else if(data ==="-2"){
							 displayMsg(this, null, 'Error: Design No Already Exists , Can Not Copy');
						 }
					 	 
					 	 
					 	 $("#styleNoId").val("");
						 $("#designNoId").val("");
					},
					error : function(jqXHR, textStatus,
							errorThrown) {
						
					} 
					
				});  
			 }
		 } 
	
	
	
	function calculateMetalSum(){
		
				
		var data = JSON.stringify($("#metalTableId")
				.bootstrapTable('getData'));
		

		var vWaxWt = 0.0;
		var vSilverWt = 0.0;
		var vgrms=0.0;
		var vfinishWt=0.0;
		
		$.each(JSON.parse(data), function(idx, obj) {
			vWaxWt += Number(obj.waxWt);
			vSilverWt += Number(obj.silverWt);
			vgrms += Number(obj.metalWt);
			vfinishWt +=Number(obj.metalWt-((obj.metalWt*obj.lossPerc)/100));
			
			

			
		});

	
		
		$('#waxWt').val(parseFloat(vWaxWt).toFixed(3));
		$('#silverWt').val(parseFloat(vSilverWt).toFixed(3));
		$('#grms').val(parseFloat(vgrms).toFixed(3));
		$('#finishWt').val(parseFloat(vfinishWt).toFixed(3));
		
		
		/* $('#waxWt').trigger("onchange");
		
		$('#silverWt').trigger("onchange"); */
		
	/* 	$("form#design").submit(); */
	
	
	setTimeout(function(){
		
		$.ajax({
			url:"/jewels/manufacturing/masters/designMetal/updateDesign.html?designId="+tempDesignId+"&waxWt="+$('#waxWt').val()+
			"&silverWt="+$('#silverWt').val()+"&grms="+$('#grms').val()+"&finishWt="+$('#finishWt').val(),
			type:"GET",
			success:function(data){
				
				updateGrossWt();
			
			}
		})
		
	}, 200)
	
		
	}
	
	function updateGrossWt(){
		
		$.ajax({
			url:"/jewels/manufacturing/masters/design/updateDesignGrossWt.html?designId="+tempDesignId,
			type:"GET",
			success:function(data){
				$('#grossWt').val(data);
			
			}
		})
		
	}
	
	
	
	//------------------Design Component -----//
	
		
	function popShapeDropDown() {
		$.ajax({
			url : '/jewels/manufacturing/masters/shape/dropDownlist.html',
			type : 'GET',
			success : function(data) {
				$("#shapeDivId").html(data);

				$('#shapeDivId #shapeDropDownId').chosen();
			}
		});
	}
	
	function popQualityDropDown(val){
		
		var e = document.getElementById("shapeDropDownId");
		var shpId = e.options[e.selectedIndex].value;
		
		$.ajax({
			url : '/jewels/manufacturing/masters/orderQuality/list.html?shapeId=' + shpId+'&qualityDropDownId=qltyDropDownId',
			type : 'GET',
			success : function(data) {
				$("#qltyDivId").html(data);

				$('#qltyDivId #qltyDropDownId').chosen();
			}
		});
		
	}
	
	
	function updateQuality(){
	
	var e = document.getElementById("shapeDropDownId");
	var shpId = e.options[e.selectedIndex].value;
	
	var e = document.getElementById("qltyDropDownId");
	var qualityId = e.options[e.selectedIndex].value;
	
	
	 $.ajax({
			url : "/jewels/manufacturing/masters/designStone/updateQlty.html?designId="+tempDesignId+"&shapeId="+shpId+"&qualityId="+qualityId,
			type : 'GET',
			success : function(data) {
				
				if(data === "1"){
					
					$('#qltyDropDownId').val('');
					popDesignStone();
				}else{
					displayMsg(this, null, 'Can not Update Quality Contact Admin !');
				}
			}
		});
}
	
	
	
	function popDesignPurityList(){
		  $("#designPurityTblId").bootstrapTable('refresh',{
						url : "/jewels/manufacturing/masters/designPurity/listing.html?purityId="+$('#purity\\.id').val()+"&sampleWt="+$('#sampleWt').val()
								+"&deisgnWaxWt="+$('#waxWt').val(),
					});
		}
	
	
	function designBiodataRpt(){
		
		
		$
		.ajax({
			url : "/jewels/manufacturing/masters/deisgnBiodata/report.html?designId="+tempDesignId,
			type : 'POST',
			success : function(data, textStatus, jqXHR) {
				
				$('#timeValCommonPdf').val(data);
				$("#genDesignBiaDataRpt").trigger('click');
				
				
				
				
			}
		});
	}
	 
</script>

<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">

<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script src="/jewels/js/lighter/jquery.lighter.js" type="text/javascript"></script>

<link href="/jewels/css/lighter/jquery.lighter.css" rel="stylesheet" type="text/css" />

<link href="/jewels/css/common/common.css" rel="stylesheet" type="text/css" />
	

<c:set var="optionText" value="Delete" />

<script src="/jewels/js/common/common.js"></script>
