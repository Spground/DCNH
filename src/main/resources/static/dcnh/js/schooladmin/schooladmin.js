/*
 * 获取管理员所分配的项目
 */
var school_all = 0;//所有项目
var school_graded = 1;//已评价
var school_notgrade = 2;//未评价

var school_projectList = [ {
	title : "title",
	school : "school",
	participators : "participators",
	mainCategory : "mainCategory",
	subCategory : "subCategory",
	score : "1",
	attachmentId : "123"
} ];

var school_showProjectVue;
var deletedIndex;

function getaddProjectPage() {
	$.ajax({
		url : '/dcnh/getaddprojectbutton',
		type : 'get',
		dataType : 'text',
		success : function(data) {
			$("#contains").html(data);
		}
	});
}

function school_showProjectpage() {
	$.ajax({
		url : '/dcnh/getshowprojectpage',
		type : 'get',
		dataType : 'text',
		success : function(data) {
			$("#contains").html(data);
			school_showProjectVue = new Vue({
				el : '#projectTable',
				data : {
					projectList : school_projectList
				},
				methods : {//绑定删除函数
					deleteProject : function(index) {
						console.log(index);
						modalConfirm("确认删除该项目吗？", function() {
							if(index >= school_showProjectVue.projectList.length)
	    						return;
							//开始删除
							var toDeleteProject = school_showProjectVue.projectList[index];
							//开始删除项目
							$.ajax({
	  						  url: '/dcnh/schooladmin/deleteproject',
	  					      type: 'post',
	  					      dataType: 'json',
	  					      data:{
	  					    	projectid: toDeleteProject.projectId,
	  					    	maincategory: toDeleteProject.mainCategory,
	  					    	school: toDeleteProject.school
	  					      },
	  					      success : function(data) {
	  					    	 modalAlert("删除成功");
	  					    	 school_showProject();
	  					      },
	  					      error: function(data) {
	  					    	modalAlert("删除失败，请稍后再试");
	  					      }
							});
						});						
					}
				}
			});
			school_showProject();
		}
	});
}

function school_showProject() {
	$.ajax({
		url : '/dcnh/getprojectlist',
		type : 'get',
		dataType : 'json',
		data : {
			kind : school_all
		},
		success : function(data) {
			for ( var index in data) {
				if (data[index] != null && data[index].attachmentId != null)
					data[index].attachmentId = "/dcnh/getattachement/"
							+ data[index].attachmentId;
			}
			school_showProjectVue.projectList = data;
		}
	});
}