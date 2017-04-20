/*
 * 获取管理员所分配的项目
 */
var school_all = 0;//所有项目
var school_graded = 1;//已评价
var school_notgrade = 2;//未评价

var school_projectList = [{
	title:"title",
    school:"school",
    participators:"participators",
    mainCategory:"mainCategory",
    subCategory:"subCategory",
    score:"1",
    attachmentId:"123"
}];

var school_showProjectVue;

function getaddProjectPage(){
	$.ajax({
		  url: '/dcnh/getaddprojectbutton',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#contains").html(data);
	      	}
	      });
}

function school_showProjectpage(){
	$.ajax({
		  url: '/dcnh/getshowprojectpage',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#contains").html(data);
	    	  school_showProjectVue =   new Vue({
	    			el:'#projectTable',
	    			data:{
	    				projectList:school_projectList
	    			}
	    		});
	    	  school_showProject();
	      	}
	      });
}

function school_showProject(){
	$.ajax({
		url:'/dcnh/getprojectlist',
		type:'get',
		dataType:'json',
		data:{
			kind: school_all
		},
		success:function(data){
			for(var index in data){
				if(data[index]!=null &&data[index].attachmentId !=null)
					data[index].attachmentId = "/getattachement/"+data[index].attachmentId;
			}
			school_showProjectVue.projectList = data;
		}
	});
}



