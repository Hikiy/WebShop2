/**
 * 
 */
$(function() {
	var shopId = getQueryString('shopId');
	var isEdit = shopId ? true : false;
	// 从后台获取店铺的基本信息（区域信息、店铺分类信息)，填充到两个控件：shop-category area
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	var registerShopUrl = '/o2o/shopadmin/registershop';
	var shopInfoUrl = '/o2o/shopadmin/getshopbyid?shopId=' + shopId;
	var editShopUrl='/o2o/shopadmin/modifyshop';
	if(!isEdit){
		getShopInitInfo();
	}else{
		getShopInfo(shopId);
	}
	function getShopInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				var shop=data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				var shopCategory='<option data-id="'
					+shop.shopCategory.shopCategoryId+'" selected>'
					+shop.shopCategory.shopCategoryName+'</option>';
				var tempAreaHtml = '';
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);
				// 不可选择
				$('#shop-category').attr('disabled','disabled');
				$('#area').html(tempAreaHtml);
				// 默认选择
				$("#area option[data-id='"+area.areaId+"']").attr("selected","selected");
			}
		});
	}
	
	function getShopInitInfo() {
		// 第一个参数是访问的url，第二个是回调的方法
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				// 遍历列表，保存option内容
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.shopCategoryId
							+ '">' + item.shopCategoryName + '</option>';
				});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
	}
	// 点击提交的时候，获取表单的内容
	$('#submit').click(
			function() {
				var shop = {};
				if(isEdit){
					shop.shopId=shopId;
				}
				shop.shopName = $('#shop-name').val();
				shop.shopAddr = $('#shop-addr').val();
				shop.phone = $('#shop-phone').val();
				shop.shopDesc = $('#shop-desc').val();
				shop.shopCategory = {
					shopCategoryId : $('#shop-category').find('option')
							.not(function() {
								return !this.selected;
							}).data('id')
				};
				shop.area = {
					areaId : $('#area').find('option').not(function() {
						return !this.selected;
					}).data('id')
				};
				var shopImg = $('#shop-img')[0].files[0];
				var formData = new FormData();
				formData.append('shopImg', shopImg);
				formData.append('shopStr', JSON.stringify(shop));
				var verifyCodeActual = $('#j_captcha').val();
				if (!verifyCodeActual) {
					$toast('请输入验证码！');
					return;
				}
				formData.append('verifyCodeActual', verifyCodeActual);

				$.ajax({
					url : (isEdit?editShopUrl:registerShopUrl),
					type : 'POST',
					data : formData,
					// 既要传文件，又要传文字
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
						if (data.success) {
							$.toast('提交成功');
						} else {
							$.toast('提交失败,请检查验证码等信息');
						}
						$('#captcha_img').click();
					}
				});
			});
})