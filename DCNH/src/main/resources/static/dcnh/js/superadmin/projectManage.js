var projectList = [{
	title:"title",
    school:"school",
    participators:"participators",
    mainCategory:"mainCategory",
    subCategory:"subCategory",
    score:"1",
    attachmentId:"123"
}];

var showProject;

function setProjectManageInfo(){
	//var projectGroupCount = $("#projectGroupCount").val();
	var judgeGroupCount = $("#judgeGroupCount").val();
//	alert(projectGroupCount+" ### "+judgeGroupCount);
	var param = {
			judgeGroupCount:judgeGroupCount
	};
	
	$.ajax({
		  url: '/dcnh/setprojectmanageinfo',
	      type: 'post',
	      dataType: 'json',
	      data:param,
	      success: function(data){
	    	  alert(data.message);
	      	}
	      });
	
}

function projectGrouping(){
	$.ajax({
		url:"/dcnh/grouping",
		type:'post',
		dataType:'json',
		success:function(data){
			alert(data.message);
		}
	});
}

function getProjectGroupingResult(){
	
}

/*
 * 显示项目管理页面
 */
function showProjectManagepage(){
	
	$.ajax({
		  url: '/dcnh/getprojectmanagepage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#contains").html(data);
	      }
	      });
}

/*
 * 获取项目展示页
 */
function showProjectpage(){
	$.ajax({
		url:'/dcnh/getshowprojectpage',
		type:'get',
		dataType:'text',
		success:function(data){
			$("#contains").html(data);
			showProject =   new Vue({
    			el:'#projectTable',
    			data:{
    				projectList:projectList
    			}
    		});
			showAllProject();
		}
	});
}

/*
 * 显示所有项目 
 */
function showAllProject(){
	$.ajax({
		url:'/dcnh/getallproject',
		type:'get',
		dataType:'json',
		success:function(data){
			for(var index in data){
				if(data[index]!=null &&data[index].attachmentId !=null)
					data[index].attachmentId = "/getattachement/"+data[index].attachmentId;
			}
			showProject.projectList = data;
		}
	});
}

/*
 * 显示指定分类的项目
 */
function showCategoryProject(){
	$.ajax({
		
	});
}