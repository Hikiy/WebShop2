/**
 * 
 */
$(function() {
	var resetpwdurl = '/o2o/local/resetpwd';
	var frontendurl = '/o2o/frontend/index';
	$('#submit').click(
		function() {
			var formData = new FormData();
			formData.append('username', $('#username').val());
			formData.append('password', $('#password').val());
			formData.append('newpassword', $('#newpassword').val());
			var verifyCodeActual = $('#j_captcha').val();
			if (!verifyCodeActual) {
				alert('请输入验证码！');
				return;
			}
			if($('#newpasswordconfirm').val()!=$('#newpassword').val()){
				alert('密码两次输入不正确！');
				return;
			}
			formData.append('verifyCodeActual', verifyCodeActual);
			$.ajax({
				url : (resetpwdurl),
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
					if (data.success) {
						$.toast('修改密码成功,返回首页');
						setTimeout(function(){
							window.location.href=frontendurl; 
						},2000);
					} else {
						$.toast('修改密码失败，'+data.errMsg);
					}
					$('#captcha_img').click();
				}
			});
		})
})