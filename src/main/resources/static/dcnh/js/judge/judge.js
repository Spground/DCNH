/*
 * 获取管理员所分配的项目
 */
var all = 0;//所有项目
var graded = 1;//已评价
var notgrade = 2;//未评价

var judgeshowProject = null;

/*
 * 显示大创项目
 */
var projectList = [{
	title:"title",
	school:"school",
	participators:"participators",
	teacher:"teacher",
	mainCategory:"mainCategory",
	subCategory:"subCategory",
	score:"score"
}];

function getproject(){
	var kind = 0;
	$.ajax({
		url : '/dcnh/judgeshowproject',
		type : 'get',
		dataType : 'text',
		success : function(data) {
			$("#contains").html(data);
			updateProjectList();
		}
	});
}

function updateProjectList(){
	var val = $("#selectkind").val();
	var kind;
	if(val == "已评分"){
		kind = 1;
		$.ajax({
			url:'/dcnh/gradeprojectpage',
			type:'get',
			dataType:'text',
			success:function(data){
				$("#projectTable_div").html(data);
				getProjectList(kind);
			}
		});
		
	}
	else if(val == "未评分"){
		kind = 2;
		$.ajax({
			url:'/dcnh/notgradedproject',
			type:'get',
			dataType:'text',
			success:function(data){
				$("#projectTable_div").html(data);
				getProjectList(kind);
			}
			
		});
		
	}
	else
		{
			alert("### "+val);
		}
		//kind=0;
	//getProjectList(kind);
}

function getProjectList(kind){
	$.ajax({
		url:'/dcnh/getprojectlist',
		type:'get',
		dataType:'json',
		data:{
			kind:kind
		},//获取所有项目
		success:function(data){
		//	if(judgeshowProject == null)
		//	{
				//alert("@@@@@");
				judgeshowProject = new Vue({
					el:'#projectTable',
					data:{
						projectList:projectList
					}
				});
		//	}
			
			for(var index in data){
				if(data[index]!=null &&data[index].attachmentId !=null)
					data[index].attachmentId = "/getattachement/"+data[index].attachmentId;
			}
			judgeshowProject.projectList = data;
		//	alert("####");
		}
	})
}

/*
 * 评委为项目打分
 */
function grade(object){
	var projectId = $(object).attr("id");
	var score = $("#"+"score_"+projectId).val();
	//alert("#### + "+score);
	$.ajax({
		url:"/dcnh/addjudgement",
		type:"post",
		dataType:"json",
		data:{
			projectId:projectId,
			score:score
		},
		success:function(response){
			//if(response.)
			alert(response.message);
			updateProjectList();
		}
	});
}


