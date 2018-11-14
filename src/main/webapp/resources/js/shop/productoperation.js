$(function() {
	//获取商品id，判断是否是编辑还是新建
	var productId = getQueryString('productId');
	//通过productId获取信息
	var infoUrl = '/o2o/shopadmin/getproductbyid?productId=' + productId;
	//通过店铺Id获取商品分类信息
	var categoryUrl = '/o2o/shopadmin/getproductcategorylist';
	//修改商品的URL
	var productPostUrl = '/o2o/shopadmin/modifyproduct';
	//标志位，用于判断此页面是用来编辑商品的还是新增商品的
	var isEdit = false;
	//若是编辑商品，那就需要先获取商品的信息
	if (productId) {
		getInfo(productId);
		isEdit = true;
	} else {
		//若是新建商品，则先获取商品的分类信息，并将URL改成添加商品的URL
		getCategory();
		productPostUrl = '/o2o/shopadmin/addproduct';
	}
	
	//用于获取商品信息
	function getInfo(id) {
		$
				.getJSON(
						infoUrl,
						function(data) {
							if (data.success) {
								//从JSON中获取product信息并赋值给表单
								var product = data.product;
								$('#product-name').val(product.productName);
								$('#product-desc').val(product.productDesc);
								$('#priority').val(product.priority);
								$('#normal-price').val(product.normalPrice);
								$('#promotion-price').val(
										product.promotionPrice);
								
								//获取当前商品类别和商品类别列表
								var optionHtml = '';
								var optionArr = data.productCategoryList;
								var optionSelected = product.productCategory.productCategoryId;
								//遍历生成商品类别列表
								optionArr
										.map(function(item, index) {
											var isSelect = optionSelected === item.productCategoryId ? 'selected'
													: '';
											optionHtml += '<option data-value="'
													+ item.productCategoryId
													+ '"'
													+ isSelect
													+ '>'
													+ item.productCategoryName
													+ '</option>';
										});
								$('#category').html(optionHtml);
							}
						});
	}

	//用于获取商品分类信息
	function getCategory() {
		$.getJSON(categoryUrl, function(data) {
			if (data.success) {
				var productCategoryList = data.data;
				var optionHtml = '';
				productCategoryList.map(function(item, index) {
					optionHtml += '<option data-value="'
							+ item.productCategoryId + '">'
							+ item.productCategoryName + '</option>';
				});
				$('#category').html(optionHtml);
			}
		});
	}
	//绑定详情图上传的控件，使最后一个控件被上传后新增一个控件，但控制大小在6以内
	$('.detail-img-div').on('change', '.detail-img:last-child', function() {
		if ($('.detail-img').length < 6) {
			$('#detail-img').append('<input type="file" class="detail-img">');
		}
	});
	//提交按钮事件对商品编辑和商品添加采取不同操作
	$('#submit').click(
			function() {
				//创建JSON对象，并获取表单的信息
				var product = {};
				product.productName = $('#product-name').val();
				product.productDesc = $('#product-desc').val();
				product.priority = $('#priority').val();
				product.normalPrice = $('#normal-price').val();
				product.promotionPrice = $('#promotion-price').val();
				product.productCategory = {
					productCategoryId : $('#category').find('option').not(
							function() {
								return !this.selected;
							}).data('value')
				};
				product.productId = productId;
				//获取缩略图文件流
				var thumbnail = $('#small-img')[0].files[0];
				//创建表单对象，用于传递给后端
				var formData = new FormData();
				formData.append('thumbnail', thumbnail);
				//遍历详情图，获取文件流，这里index是从0开始，所以后端遍历的时候也使用从0开始
				$('.detail-img').map(
						function(index, item) {
							if ($('.detail-img')[index].files.length > 0) {
								formData.append('productImg' + index,
										$('.detail-img')[index].files[0]);
							}
						});
				formData.append('productStr', JSON.stringify(product));
				var verifyCodeActual = $('#j_captcha').val();
				if (!verifyCodeActual) {
					$.toast('请输入验证码！');
					return;
				}
				formData.append("verifyCodeActual", verifyCodeActual);
				$.ajax({
					url : productPostUrl,
					type : 'POST',
					data : formData,
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
						if (data.success) {
							$.toast('提交成功！');
							$('#captcha_img').click();
						} else {
							$.toast('提交失败！');
							$('#captcha_img').click();
						}
					}
				});
			});

});