function accountManage() {
	setAccountMangePage();
}

function setAccountMangePage() {
	$.ajax({
		url : '/getaccountmanagepage',
		type : 'get',
		dataType : 'text',
		success : function(data) {
			$("#contains").html(data);
		}
	});
}

var category;

function setAddPrjctPage() {
	alert("setAddPrjctPage();");
	$.ajax({
		url : '/getaddprjctpage',
		type : 'get',
		dataType : 'text',
		success : function(data) {		
			$("#add_prjct_container").html(data);
			//初始化上传文件
			$("#attachement_file").fileinput({
				language : 'zh',
				allowedFileExtensions : [ "zip", "rar"], 
				uploadUrl : "/addnewproject",
				uploadAsync : true,
				showPreview : false,
				removeClass : "btn btn-danger",
				showUpload: true, //是否显示上传按钮
				elErrorContainer : "#fileError",
			});
			$.ajax({
				url : "/getcategory",
				type : 'get',
				dataType : 'json',
				success : function(data0) {
					category = data0;
					//填充一级选择框
					$("#category").empty();
					$("#category").append("<option>----选择一级分类----</option>");
					for(var item in category) {
						$("#category").append("<option>" + item + "</option>");
					}
					
					//填充二级选择框
					$("#category").change(function() {
						$("#subcategory").empty();
						$("#subcategory").append("<option>----选择二级分类----</option>");
						var key = $('#category >option:selected').val();
						var subCategory = category[key];
						if(subCategory == null)
							return;
						for(var item0 in subCategory) {
							$("#subcategory").append("<option>" + subCategory[item0] + "</option>");
						}
					});
					
				}
			});
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
		url : '/addnewuser',
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
