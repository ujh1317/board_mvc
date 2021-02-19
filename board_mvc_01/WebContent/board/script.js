if(document.getElementById("check").checked){
	document.getElementById("non_check").disabled = ture;
}//checkbox

function writeCheck(){
	
	if($('#title').val()==''){
		alert("제목을 입력하십시요.");
		$('#title').focus();
		return false;
	}//#title
	if($('#content').val()==""){
		alert("내용을 입력하십시오.");
		$('#content').focus();
		return false;
	}//#content
	
	return true;
 }//inputCheck()

function loginCheck(){
	if(document.loginForm.id.value==''){
		alert("id를 입력하시오.");
		document.loginForm.id.focus();
		return false;
	}
	if(document.loginForm.pw.value==''){
		alert("pwd를 입력하시오.");
		document.loginForm.pw.focus();
		return false;
	}
	return true;
}

}//del()