/**
 * 通用的modal js
 */

//警告模态框
function modalAlert(str) {
	$("#alertModal").modal("hide");
	$("#alertContent").html(str);
	$("#alertModal").modal("show");
}

//确认模态框
function modalConfirm(str, callback) {
	$("#confirmModal").modal("hide");
	$("#confirmContent").html(str);
    $("#confirmOkBtn").click(function() {
    	$("#confirmModal").modal("hide");
    	callback();
    	$("#confirmOkBtn").unbind();//清除所有之前 的历史绑定
    });
	$("#confirmModal").modal("show");
}