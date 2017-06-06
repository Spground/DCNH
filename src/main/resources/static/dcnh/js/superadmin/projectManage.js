var projectList = [ {
	title : "title",
	school : "school",
	participators : "participators",
	mainCategory : "mainCategory",
	subCategory : "subCategory",
	score : "1",
	attachmentId : "123"
} ];

var showProject;

function setProjectManageInfo() {
	//var projectGroupCount = $("#projectGroupCount").val();
	var judgeGroupCount = $("#judgeGroupCount").val();
	//	alert(projectGroupCount+" ### "+judgeGroupCount);
	var param = {
		judgeGroupCount : judgeGroupCount
	};

	$.ajax({
		url : '/dcnh/setprojectmanageinfo',
		type : 'post',
		dataType : 'json',
		data : param,
		success : function(data) {
			alert(data.message);
		}
	});

}

function projectGrouping() {
	$.ajax({
		url : "/dcnh/grouping",
		type : 'post',
		dataType : 'json',
		success : function(data) {
			alert(data.message);
		}
	});
}

function getProjectGroupingResult() {

}

/*
 * 显示项目管理页面
 */
function showProjectManagepage() {

	$.ajax({
		url : '/dcnh/getprojectmanagepage',
		type : 'get',
		dataType : 'text',
		success : function(data) {
			$("#contains").html(data);
		}
	});
}

/*
 * 获取项目展示页
 */
function showProjectpage() {
	$.ajax({
		url : '/dcnh/getshowprojectpage',
		type : 'get',
		dataType : 'text',
		success : function(data) {
			$("#contains").html(data);
			showProject = new Vue({
				el : '#projectTable',
				data : {
					projectList : projectList
				},
				methods : {//绑定删除函数
					deleteProject : function(index) {
						if (index >= showProject.projectList.length)
							return;
						var toDeleteProject = showProject.projectList[index];
						console.log(showProject.projectList);
						modalConfirm("确认删除该项目吗？", function() {
							//开始删除项目
							$.ajax({
								url : '/dcnh/deleteproject',
								type : 'post',
								dataType : 'json',
								data : {
									projectid : toDeleteProject.projectId,
									school : toDeleteProject.school,
									maincategory : toDeleteProject.mainCategory
								},
								success : function(data) {
									modalAlert("删除成功！");
									showAllProject();
								},
								error : function(data) {
									modalAlert("删除失败！");
								}
							});
						});

					}
				}
			});
			showAllProject();
		}
	});
}

/*
 * 显示所有项目 
 */
function showAllProject() {
	$.ajax({
		url : '/dcnh/getallproject',
		type : 'get',
		dataType : 'json',
		success : function(data) {
			for ( var index in data) {
				if (data[index] != null
						&& data[index].attachment != null && data[index].attachment.attachmentId != null)
					data[index].attachmentId = "/dcnh/getattachement/"
							+ data[index].attachment.attachmentId;
			}
			showProject.projectList = data;
		}
	});
}

/*
 * 显示指定分类的项目
 */
function showCategoryProject() {
	$.ajax({

	});
}