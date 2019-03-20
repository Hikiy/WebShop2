/**
 * 
 */
$(function() {
	var loginurl = '/o2o/local/login';
	var frontendurl = '/o2o/frontend/index';
	var registerurl = '/o2o/auth/register';
	$('#submit').click(
		function() {
			var formData = new FormData();
			formData.append('username', $('#username').val());
			formData.append('password', $('#password').val());
			var verifyCodeActual = $('#j_captcha').val();
			if (!verifyCodeActual) {
				alert('请输入验证码！');
				return;
			}
			formData.append('verifyCodeActual', verifyCodeActual);
			$.ajax({
				url : (loginurl),
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
					if (data.success) {
						$.toast('登录成功,返回首页');
						setTimeout(function(){
							window.location.href=frontendurl; 
						},2000);
					} else {
						$.toast('登录失败'+data.errorMsg);
					}
					$('#captcha_img').click();
				}
			});
		});
	$('#register').click(
		function() {
			window.location.href=registerurl;
		}
	)
})