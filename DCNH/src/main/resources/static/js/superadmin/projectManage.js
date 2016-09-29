var projectList = [{
	
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
		  url: '/setprojectmanageinfo',
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
		url:"/grouping",
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
		  url: '/getprojectmanagepage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#contains").html(data);
	      }
	      });
}

/*
 * 显示所有项目 
 */
function showAllProject(){
	$.ajax({
		url:'/getallproject',
		type:'get',
		dataType:'text',
		success:function(data){
			
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