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
						if(!confirm("确认删除该项目吗？"))
								return;
						if(index >= this.projectList[index].length)
    						return;
						//开始删除
						var toDeleteProject = this.projectList[index];
						console.log(this.projectList);
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
  					    	 alert(data.message);
  					    	 console.log(data);
  					    	 console.log(this.projectList);
  					    	 school_showProject();
  					      }
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