function checkEmail(){
	var email = $("#email").val();
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	if(!reg.test(email)){
		alert("请填入有效的邮箱");
		// $("#email").focus();
		return false;
	}
	return true;
}
