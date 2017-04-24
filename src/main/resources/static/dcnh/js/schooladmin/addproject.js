//添加项目的js
function postNewProject() {
	// 验证是否为空
	var data = $("#mform").serializeArray();
	console.log($("#mform").serializeArray());
	for ( var index in data) {
		var formItem = data[index];
		if ((formItem.name == "category" && formItem.value == "----选择一级分类----")
				|| (formItem.name == "subcategory" && formItem.value == "----选择二级分类----")) {
			modalAlert("请选项目类别！");
			return;
		}
		if (formItem.value == "") {
			modalAlert("请检查字段不能为空！");
			return;
		}
	}
	
	if($("#attachementId").val() == "") {
		modalAlert("请上传附件！");
		return;
	}
	
	//获取项目成员数组
	var participatorsArray = [];
	var i = 0;
	for(var index in data) {
		var formItem = data[index];
		if(formItem.name == "participators")
			participatorsArray[i++] = formItem.value;
	}
	
	console.log(participatorsArray);
	//开始上传项目
	$.ajax({
		url:"/dcnh/addnewproject",
		type:"POST",
		dataType:"json",
		data:{
			category:$("#category").val(),
			subcategory:$("#subcategory").val(),
			school:$("#school").val(),
			teacher:$("#teacher").val(),
			prjct_title:$("#prjct_title").val(),
			participators:participatorsArray,
			attachement_id:$("#attachementId").val()
		},
		success:function(data) {
			//清空，附件ID
			$("#attachementId").val("");
			resetForm();
			modalAlert(data.message);	
		},
		error:function() {
			resetForm();
			modalAlert("项目上传失败，请重新尝试！");
		}
	});
}

//重置表单
function resetForm() {
	$("#mform").remove();
	console.log("删除表单")
}