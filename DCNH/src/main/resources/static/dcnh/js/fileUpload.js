var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
var mData = null;

function fileChange(target) {
	var fileSize = 0;
	var filetypes = [".rar", ".zip"];
	var filepath = target.value;
	var filemaxsize = 1024 * 50;// 50M
	if (filepath) {
		var isnext = false;
		var fileend = filepath.substring(filepath.indexOf("."));
		if (filetypes && filetypes.length > 0) {
			for (var i = 0; i < filetypes.length; i++) {
				if (filetypes[i] == fileend) {
					isnext = true;
					break;
				}
			}
		}
		if (!isnext) {
			modalAlert("不接受此文件类型!");
			target.value = "";
			return false;
		}
	} else {
		return false;
	}
	if (isIE && !target.files) {
		var filePath = target.value;
		var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		if (!fileSystem.FileExists(filePath)) {
			modalAlert("附件不存在，请重新输入!");
			return false;
		}
		var file = fileSystem.GetFile(filePath);
		fileSize = file.Size;
	} else {
		fileSize = target.files[0].size;
	}

	var size = fileSize / 1024;
	if (size > filemaxsize) {
		modalAlert("附件大小不能大于" + filemaxsize / 1024 + "M！");
		target.value = "";
		return false;
	}
	if (size <= 0) {
		modalAlert("附件大小不能为0M！");
		target.value = "";
		return false;
	}
}

function fileUpload() {
	alert("fileUpload");
	formDataArray = $("#mform").serializeArray();
	alert(submitBtn.data());
	submitBtn.data().submit();
}