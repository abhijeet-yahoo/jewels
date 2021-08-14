<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<style>
input[type=file] {
	float: left;
}
</style>


<div class="container-fluid">
	<h4>Upload image(s) for Email Concept</h4>
	<hr />
	
	<!-- // Email Image -->

	<form class="imageformEmail" action="javascript:void(0)">
		<div class="form-group">
			<div class="col-xs-12">
				<div class="col-lg-12">
					<label for="emailImage" class="col-lg-2 control-label" style="font-size: 13px;">Email Image :</label>
				</div>

				<div class="col-lg-12">
					<input type="file" name="file" id="file1" /> 
					<input type="hidden" name="imgId1" id="imgId1" value="" />
					<button class="btn btn-sm btn-info upload" type="submit">Upload File</button>
					<p />
					<div class="progress progress-striped active">
						<div class="progress-bar" style="width: 0%"></div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	<!-- // Rough Image -->

	<form class="imageformRough" action="javascript:void(0)">
		<div class="form-group">
			<div class="col-xs-12">
				<div class="col-lg-12">
					<label for="roughImage" class="col-lg-2 control-label" style="font-size: 13px;">Rough Image :</label>
				</div>

				<div class="col-lg-12">
					<input type="file" name="file" id="file3" /> <input type="hidden" name="imgId3" id="imgId3" value="" />
					<button class="btn btn-sm btn-info upload" type="submit">Upload File</button>
					<p />
					<div class="progress progress-striped active">
						<div class="progress-bar" style="width: 0%"></div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	
	
	
	<!-- // Design Image -->
	<form class="imageformDesign" action="javascript:void(0)">
		<div class="form-group">
			<div class="col-xs-12">
				<div class="col-lg-12">
					<label for="designImage" class="col-lg-2 control-label" style="font-size: 13px;">Design Image :</label>
				</div>

				<div class="col-lg-12">
					<input type="file" name="file" id="file4" /> 
					<input type="hidden" name="imgId4" id="imgId4" value="" />
					<button class="btn btn-sm btn-info upload" type="submit">Upload File</button>
					<p />
					<div class="progress progress-striped active">
						<div class="progress-bar" style="width: 0%"></div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	
	<!-- // cad Image -->
	
	<form class="imageformCad" action="javascript:void(0)">
		<div class="form-group">
			<div class="col-xs-12">
				<div class="col-lg-12">
					<label for="cadImage" class="col-lg-2 control-label" style="font-size: 13px;">Cad Image :</label>
				</div>

				<div class="col-lg-12">
					<input type="file" name="file" id="file2" /> <input type="hidden" name="imgId2" id="imgId2" value="" />
					<button class="btn btn-sm btn-info upload" type="submit">Upload File</button>
					<p />
					<div class="progress progress-striped active">
						<div class="progress-bar" style="width: 0%"></div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
	
	

	 <div class="form-group">
		<div class="row">
			<div class="col-md-3 col-sm-4 col-xs-6">
				<strong>Email Image</strong>
				<img id="img1" class="img-responsive" src="${uploadFilePathEmail}${emailImg}" style="height : 180px;width: 100%"/>
			</div>
			<div class="col-md-3 col-sm-4 col-xs-6">
				<strong>Rough Image</strong>
				<img id="img3" class="img-responsive" src="${uploadFilePathRough}${roughImg}" style="height : 180px;width: 100%"/>
			</div>
			<div class="col-md-3 col-sm-4 col-xs-6">
				<strong>Design Image</strong>
				<img id="img3" class="img-responsive" src="${uploadFilePathDesign}${designImg}" style="height : 180px;width: 100%"/>
			</div>
			<div class="col-md-3 col-sm-4 col-xs-6">
				<strong>Cad Image</strong>
				<img id="img2" class="img-responsive" src="${uploadFilePathCad}${cadImg}" style="height : 180px;width: 100%"/>
			</div>
			
		</div>
	</div>
	
	
</div> 



<script type="text/javascript">


//---//--------------Email Image--//

$(document).on('submit', 'form.imageformEmail', function(e) {
	
	var imgNm = $("#file1").val();
	var vImgNm = imgNm.substring(12);
	var xImg = vImgNm.substring(vImgNm.lastIndexOf("."));
	var upImage = $('#emailConIdMax').val()+xImg;
	$form = $(this);
	$form.find('.progress-bar').width('0%');
	var fdata = new FormData($form[0]);
	var request = new XMLHttpRequest();
	request.upload.addEventListener('progress', function(e) {
		var percent = Math.round((e.loaded / e.total) * 100);
		$form.find('.progress-bar').width(percent + '%')
				.html(percent + '%');
	});

	request.addEventListener('load', function(e) {
		$form.find('.progress-bar').addClass('.progress-bar-success progress-bar-striped').html(
				'upload completed...........');
	
	});
	
	
	$("#emailImage").val(upImage);
	request.open('post', '/jewels/manufacturing/transactions/emailConcept/emailImage/upload.html?jid='+upImage);
	request.send(fdata);
});



//---//--------------Cad Image----//
$(document).on('submit', 'form.imageformCad', function(e) {
	
	var imgNm = $("#file2").val();
	var vImgNm = imgNm.substring(12);
	var xImg = vImgNm.substring(vImgNm.lastIndexOf("."));
	var upImage = $('#emailConIdMax').val()+xImg;
	$form = $(this);
	$form.find('.progress-bar').width('0%');
	var fdata = new FormData($form[0]);
	var request = new XMLHttpRequest();
	request.upload.addEventListener('progress', function(e) {
		var percent = Math.round((e.loaded / e.total) * 100);
		$form.find('.progress-bar').width(percent + '%')
				.html(percent + '%');
	});

	request.addEventListener('load', function(e) {
		$form.find('.progress-bar').addClass('.progress-bar-success progress-bar-striped').html(
				'upload completed...........');
	
	});
	
	
	$("#cadImage").val(upImage);
	request.open('post', '/jewels/manufacturing/transactions/emailConcept/cadImage/upload.html?jid='+upImage);
	request.send(fdata);
});


//---//-------//-------Rough Image--//

$(document).on('submit', 'form.imageformRough', function(e) {
	
	var imgNm = $("#file3").val();
	var vImgNm = imgNm.substring(12);
	var xImg = vImgNm.substring(vImgNm.lastIndexOf("."));
	var upImage = $('#emailConIdMax').val()+xImg;
	$form = $(this);
	$form.find('.progress-bar').width('0%');
	var fdata = new FormData($form[0]);
	var request = new XMLHttpRequest();
	request.upload.addEventListener('progress', function(e) {
		var percent = Math.round((e.loaded / e.total) * 100);
		$form.find('.progress-bar').width(percent + '%')
				.html(percent + '%');
	});

	request.addEventListener('load', function(e) {
		$form.find('.progress-bar').addClass('.progress-bar-success progress-bar-striped').html(
				'upload completed...........');
	
	});
	
	
	$("#roughImage").val(upImage);
	request.open('post', '/jewels/manufacturing/transactions/emailConcept/roughImage/upload.html?jid='+upImage);
	request.send(fdata);
});



//---//--------------Design Image--//

$(document).on('submit', 'form.imageformDesign', function(e) {
	
	var imgNm = $("#file4").val();
	var vImgNm = imgNm.substring(12);
	var xImg = vImgNm.substring(vImgNm.lastIndexOf("."));
	var upImage = $('#emailConIdMax').val()+xImg;
	$form = $(this);
	$form.find('.progress-bar').width('0%');
	var fdata = new FormData($form[0]);
	var request = new XMLHttpRequest();
	request.upload.addEventListener('progress', function(e) {
		var percent = Math.round((e.loaded / e.total) * 100);
		$form.find('.progress-bar').width(percent + '%')
				.html(percent + '%');
	});

	request.addEventListener('load', function(e) {
		$form.find('.progress-bar').addClass('.progress-bar-success progress-bar-striped').html(
				'upload completed...........');
	
	});
	
	
	$("#designImage").val(upImage);
	request.open('post', '/jewels/manufacturing/transactions/emailConcept/designImage/upload.html?jid='+upImage);
	request.send(fdata);
});


function emailConceptSave() {
	$("form#emailConcept").submit();
}


</script>