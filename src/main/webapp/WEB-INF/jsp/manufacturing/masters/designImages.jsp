<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglib.jsp"%>

<style>
input[type=file] {
	float: left;
}
</style>

<div class="container-fluid">
	<h4>Upload image(s) for Design Master</h4>
	<hr />

	<form class="imageformOne" action="javascript:void(0)">
		<div class="form-group">
			<div class="col-xs-12">
				<div class="col-lg-12">
					<label for="image1" class="col-lg-2 control-label" style="font-size: 13px;">Image1 :</label>
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

	<form class="imageformTwo" action="javascript:void(0)">
		<div class="form-group">
			<div class="col-xs-12">
				<div class="col-lg-12">
					<label for="image2" class="col-lg-2 control-label" style="font-size: 13px;">Image2 :</label>
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

	<form class="imageformThree" action="javascript:void(0)">
		<div class="form-group">
			<div class="col-xs-12">
				<div class="col-lg-12">
					<label for="image3" class="col-lg-2 control-label" style="font-size: 13px;">Image3 :</label>
				</div>

				<div class="col-lg-12">
					<button class="btn btn-sm btn-info upload" type="submit">Upload File</button>
					<input type="file" name="file" id="file3" /> <input type="hidden" name="imgId3" id="imgId3" value="" />
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
				<div  id="img1Lb" class="checkbox" style="display:none;">
					<label><input id="dfImage" name="dfImage" value="1" type="checkbox">Set as default</label>
				</div>
				<div>
				<img id="img1" class="img-responsive" src="${uploadFilePath}${image1}" />
				</div>
				
					<label for="image3" class="col-lg-2 control-label" style="font-size: 13px;">${image1}</label>
			</div>
			<div class="col-md-3 col-sm-4 col-xs-6">
				<div id="img2Lb" class="checkbox" style="display:none;">
					<label><input id="dfImage" name="dfImage" value="2" type="checkbox">Set as default</label>
				</div>
				<div>
				<img id="img2" class="img-responsive" src="${uploadFilePath}${image2}" />
				</div>
				
				<label for="image3" class="col-lg-2 control-label" style="font-size: 13px;">${image2}</label>
			</div>
			<div class="col-md-3 col-sm-4 col-xs-6">
				<div id="img3Lb" class="checkbox" style="display:none;">
					<label><input id="dfImage" name="dfImage" value="3" type="checkbox">Set as default</label>
				</div>
				<div>
				<img id="img3" class="img-responsive" src="${uploadFilePath}${image3}" />
				</div>
				
				<label for="image3" class="col-lg-2 control-label" style="font-size: 13px;">${image3}</label>
			</div>
		</div>
	</div>
</div> 

<script>

	
//---//--------------one Image--//

$(document).on('submit', 'form.imageformOne', function(e) {
	
	var imgNm = $("#file1").val();
	var vImgNm = imgNm.substring(12);

	console.log('vImgNm    '+vImgNm)
	
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
	
	
	
	$("#invImg1").val(vImgNm);
	$("#img1Lb").css("display", "block");
	
	var chkTmp = $('input[name="dfImage"]:checked').serialize();
	chkTmp = chkTmp.substring(chkTmp.indexOf('=')+1);
	var temp = "";
	if(chkTmp == 1){
		temp = $('#invImg1').val();
		$('#defaultImage').val(temp);
		$('#defImgChk').val(chkTmp);
	}
	
	
	request.open('post', '/jewels/manufacturing/masters/design/image/upload.html?jid='+vImgNm);
	request.send(fdata);
});

	


//---//--------------two Image--//

$(document).on('submit', 'form.imageformTwo', function(e) {
	
	var imgNm = $("#file2").val();
	var vImgNm = imgNm.substring(12);
	
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
	
	
	$("#invImg2").val(vImgNm);
	$("#img2Lb").css("display", "block");
	
	var chkTmp = $('input[name="dfImage"]:checked').serialize();
	chkTmp = chkTmp.substring(chkTmp.indexOf('=')+1);
	var temp = "";
	if(chkTmp == 2){
		temp = $('#invImg2').val();
		$('#defaultImage').val(temp);
		$('#defImgChk').val(chkTmp);
	}
	
	request.open('post', '/jewels/manufacturing/masters/design/image/upload.html?jid='+vImgNm);
	request.send(fdata);
});






//---//--------------three Image--//


$(document).on('submit', 'form.imageformThree', function(e) {
	
	var imgNm = $("#file3").val();
	var vImgNm = imgNm.substring(12);
	
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
	
	
	$("#invImg3").val(vImgNm);
	$("#img3Lb").css("display", "block");
	
	var chkTmp = $('input[name="dfImage"]:checked').serialize();
	chkTmp = chkTmp.substring(chkTmp.indexOf('=')+1);
	var temp = "";
	if(chkTmp == 3){
		temp = $('#invImg3').val();
		$('#defaultImage').val(temp);
		$('#defImgChk').val(chkTmp);
	}
	
	request.open('post', '/jewels/manufacturing/masters/design/image/upload.html?jid='+vImgNm);
	request.send(fdata);
});



	function designSave() {
		$("form#designDivId").submit();
	}

</script>

<%@include file="/WEB-INF/jsp/common/modal.jsp"%>

