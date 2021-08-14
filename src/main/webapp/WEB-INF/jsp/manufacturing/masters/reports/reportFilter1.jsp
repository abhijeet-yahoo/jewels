<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<div class="row">
	<div id="mDivId" class="col-xs-2">
		<table id="selIdTbl" data-click-to-select="true" 
			data-side-pagination="server"
			data-striped="true" data-height="200">
		    <thead>
		        <tr class="btn-primary">
					<th data-field="state" data-checkbox="true"></th>
					<th data-field="name" data-align="left" data-sortable="true">Drop Downs</th>
		        </tr>
		    </thead>
		</table>
	</div>
	<div id="mDivId" class="col-xs-2">
		<input type="button" id="genId" value="Select the drop down(s) for the report" class="btn btn-primary" />
		<br><br>
	</div>
</div>

<div class="row">&nbsp;</div>

<div class="row">
	<form:form commandName="design" action="/jewels/manufacturing/masters/reports/generateReport.html"
		cssClass="form-horizontal reportFilter1">


		<div>
			<span class="col-lg-12 col-sm-12" >
				<input type="button" value="Select Categories" class="btn-primary" onclick="javascript:call();"/>
			</span> 
		</div>
		
		<div id="mDivId" class="col-xs-2">
			<table id="catIdTbl" data-toggle="table" 
				data-click-to-select="true" 
				data-side-pagination="server"
				data-striped="true" data-height="300">
				<thead>
					
					<tr >
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true">Category</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="mDivId" class="col-xs-2">
			<table id="subCatIdTbl" data-toggle="table"
				data-click-to-select="true" 
				data-side-pagination="server"
				data-striped="true" data-height="300">
				<thead>
					<tr class="btn-primary">
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true">Sub Category</th>
					</tr>
				</thead>
			</table>
			<span class="col-lg-12 col-sm-12" style="font-size: 0px;">&nbsp;
				<input type="button" id="subCatDtls" value="Select Sub Categories" class="btn btn-primary" />
			</span>
		</div>
		<div id="mDivId" class="col-xs-2">
			<table id="conIdTbl" data-toggle="table"
				data-click-to-select="true" 
				data-side-pagination="server"
				data-striped="true" data-height="300">
				<thead>
					<tr class="btn-primary">
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true">Concept</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="mDivId" class="col-xs-2">
			<table id="subConIdTbl" data-toggle="table"
				data-click-to-select="true" 
				data-side-pagination="server"
				data-striped="true" data-height="300">
				<thead>
					<tr class="btn-primary">
						<th data-field="state" data-checkbox="true"></th>
						<th data-field="name" data-align="left" data-sortable="true">Sub Concept</th>
					</tr>
				</thead>
			</table>
			<span class="col-lg-12 col-sm-12" style="font-size: 0px;">&nbsp;
				<input type="button" id="subConDtls" value="Select Sub Concepts" class="btn btn-primary" />
			</span>
		</div>

		<div class="row">
			<span class="col-lg-12 col-sm-12" style="font-size: 7px;">&nbsp;</span>
		</div>

		<div class="form-group">
			<div class="col-lg-12 col-lg-offset-0">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="Generate Report" class="btn btn-primary" />

				<input type="hidden" name="procName" id="procName" value="${procName}"  />
				<input type="hidden" name="filterForm" id="filterForm" value="${filterForm}"  />
				<input type="hidden" name="xml" id="xml" value="${xml}"  />
				<input type="hidden" name="pCatIds" id="pCatIds" value=""  />
				<input type="hidden" name="pSubCatIds" id="pSubCatIds" value=""  />
			</div>
		</div>
	</form:form>
</div>



<link rel="stylesheet" href="/jewels/css/jquery/jquery-ui.min.css">
<script src="/jewels/js/jquery/jquery-ui.min.js"></script>

<script>
$(document).ready(function(e) {
});

$('#genId').on('click', function(event) {
	var data = $('#selIdTbl').bootstrapTable('getSelections');
	var ids = $.map(data, function(item) {
		return item.id;
	});

	var b = '"' + ids + '"'; 
	var list = b.split(",");
	for (x=0; x<list.length; x++) {
		popData(ids[x]);
	}

	event.preventDefault();
});

function popData(opt) {
	if (opt == 1) {
		$("#catIdTbl").bootstrapTable('refresh',
		{
			url : '/jewels/manufacturing/masters/category/listing.html?opt=3'
		});
	}
	if (opt == 3) {
		$("#conIdTbl").bootstrapTable('refresh',
		{
			url : '/jewels/manufacturing/masters/concept/listing.html?opt=3'
		});
	}
}

$('#subCatDtls').on('click', function(event) {
	var data = $('#catIdTbl').bootstrapTable('getSelections');
	var ids = $.map(data, function(item) {
		return item.id;
	});
	
	alert("----------->>>>>>>"+ids);

	$("#subCatIdTbl").bootstrapTable('refresh',
	{
		url : '/jewels/manufacturing/masters/reports/child/list.html?parent=catId&ids='+ids+'&order=asc'
	});

	event.preventDefault();
});

$('#subConDtls').on('click', function(event) {
	var data = $('#conIdTbl').bootstrapTable('getSelections');
	var ids = $.map(data, function(item) {
		return item.id;
	});

	$("#subConIdTbl").bootstrapTable('refresh',
	{
		url : '/jewels/manufacturing/masters/reports/child/list.html?parent=conId&ids='+ids+'&order=asc'
	});

	event.preventDefault();
});

$('#design').submit(function(e) {
	var data = $('#catIdTbl').bootstrapTable('getSelections');
	var ids = $.map(data, function(item) {
		return item.id;
	});
	$('#pCatIds').val(ids);

	data = $('#subCatIdTbl').bootstrapTable('getSelections');
	ids = $.map(data, function(item) {
		return item.id;
	});
	$('#pSubCatIds').val(ids);
});

var data = 
	[
	    {
	        "id": 1,
	        "name": "Category"
	    },
	    {
	        "id": 2,
	        "name": "Sub Category"
	    },
	    {
	        "id": 3,
	        "name": "Concept"
	    },
	    {
	        "id": 4,
	        "name": "Sub Concept"
	    },
	    {
	        "id": 5,
	        "name": "Stone Type"
	    }
	];

	$(function () {
	    $('#selIdTbl').bootstrapTable({
	        data: data
	    });
	});
	
	
	
	
	
	
	function call()
	{
		$("#catIdTbl").bootstrapTable('refresh',
				{
					url : '/jewels/manufacturing/masters/category/listing.html?opt=1'
				});
	}
	
</script>

<!--  
INSERT INTO `hiramoti`.`reports` (`ReportId`, `ReportNm`, `ProcedureNm`) VALUES ('1', 'reportOne', 'proc_one');
-->
