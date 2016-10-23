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
		  url: '/getaddprojectbutton',
	      type: 'get',
	      dataType: 'text',
	      success: function(data){
	    	  $("#contains").html(data);
	      	}
	      });
}

function school_showProjectpage(){
	$.ajax({
		  url: '/getshowprojectpage',
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
		url:'/getprojectlist',
		type:'get',
		dataType:'json',
		success:function(data){
			for(var index in data){
				data[index].attachementId = data[index].attachementId
			}
			school_showProjectVue.projectList = data;
		}
	});
}



