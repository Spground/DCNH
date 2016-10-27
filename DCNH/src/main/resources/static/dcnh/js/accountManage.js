function accountManage() {
	setAccountMangePage();
}

function setAccountMangePage() {
	$.ajax({
		url : '/dcnh/getaccountmanagepage',
		type : 'get',
		dataType : 'text',
		success : function(data) {
			$("#contains").html(data);
		}
	});
}

function addNewAccount() {

	var userName = $("#userName").val();
	var school = $("#school").val();
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	if (!check()) {
		return;
	}
	$.ajax({
		url : '/dcnh/addnewuser',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if (data.code == 0) {
				alert("添加成功");
				clear();
			} else {
				alert(data.message);
			}
		}
	});
}

function check() {
	var userName = $("#userName").val();
	var school = $("#school").val();
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();

	if (school == "" || school == null) {
		alert("学校信息不能为空");
		return false;
	}

	if (password1 != password2) {
		alert("密码不一致");
		return false;
	}

	if (userName == "" || userName == null) {
		alert("用户名不能为空");
		return false;
	}
}

function clear() {
	$("#userName").val("");
	$("#school").val("");
	$("#password1").val("");
	$("#password2").val("");
}
