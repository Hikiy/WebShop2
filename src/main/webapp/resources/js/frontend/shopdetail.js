$(function() {
	var loading = false;
	//返回最大条数，超过时禁止后台访问
	var maxItems = 20;
	//一页最多商品数
	var pageSize = 10;
	//列出商品列表的URL
	var listUrl = '/o2o/frontend/listproductsbyshop';
	//默认页码1
	var pageNum = 1;
	//获取地址栏中的shopId
	var shopId = getQueryString('shopId');
	//用于查询的条件
	var productCategoryId = '';
	var productName = '';

	var searchDivUrl = '/o2o/frontend/listshopdetailpageinfo?shopId='
			+ shopId;
	//加载店铺信息和商品类别信息
	getSearchDivData();
	//预先加载商品信息
	addItems(pageSize, pageNum);
	
	//获取店铺信息和商品类别信息
	function getSearchDivData() {
		var url = searchDivUrl;
		$
				.getJSON(
						url,
						function(data) {
							if (data.success) {
								var shop = data.shop;
								$('#shop-cover-pic').attr('src', shop.shopImg);
								$('#shop-update-time').html(
										new Date(shop.lastEditTime)
												.Format("yyyy-MM-dd"));
								$('#shop-name').html(shop.shopName);
								$('#shop-desc').html(shop.shopDesc);
								$('#shop-addr').html(shop.shopAddr);
								$('#shop-phone').html(shop.phone);
								//获取后台返回的商品列表
								var productCategoryList = data.productCategoryList;
								var html = '';
								//遍历商品列表生成前端的标签
								productCategoryList
										.map(function(item, index) {
											html += '<a href="#" class="button" data-product-search-id='
													+ item.productCategoryId
													+ '>'
													+ item.productCategoryName
													+ '</a>';
										});
								//将标签添加到相应HTML组件
								$('#shopdetail-button-div').html(html);
							}
						});
	}
	
	/**
	 * 获取示商品列表信息
	 * @param pageSize
	 * @param pageIndex
	 * @returns
	 */
	function addItems(pageSize, pageIndex) {
		// 拼接查询URL
		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize + '&productCategoryId=' + productCategoryId
				+ '&productName=' + productName + '&shopId=' + shopId;
		loading = true;
		$.getJSON(url, function(data) {
			if (data.success) {
				maxItems = data.count;
				var html = '';
				data.productList.map(function(item, index) {
					html += '' + '<div class="card" data-product-id='
							+ item.productId + '>'
							+ '<div class="card-header">' + item.productName
							+ '</div>' + '<div class="card-content">'
							+ '<div class="list-block media-list">' + '<ul>'
							+ '<li class="item-content">'
							+ '<div class="item-media">' + '<img src="'
							+ item.imgAddr + '" width="44">' + '</div>'
							+ '<div class="item-inner">'
							+ '<div class="item-subtitle">' + item.productDesc
							+ '</div>' + '</div>' + '</li>' + '</ul>'
							+ '</div>' + '</div>' + '<div class="card-footer">'
							+ '<p class="color-gray">'
							+ new Date(item.lastEditTime).Format("yyyy-MM-dd")
							+ '更新</p>' + '<span>点击查看</span>' + '</div>'
							+ '</div>';
				});
				//将“卡片”（SUI Mobile中的名称）组合添加到组件中
				$('.list-div').append(html);
				//获取“卡片”总数，当与查询条件的总数一致则停止加载
				var total = $('.list-div .card').length;
				if (total >= maxItems) {
					/**这个注释的方法是因为会导致bug，使改变查询条件时，不再加载
					// 加载完毕，则注销无限加载事件，以防不必要的加载
					$.detachInfiniteScroll($('.infinite-scroll'));
					// 删除加载提示符
					$('.infinite-scroll-preloader').remove();
					**/
					//加载完毕，则隐藏加载提示符，以防不必要的加载
					$('.infinite-scroll-preloader').hide();
				}else{
					$('.infinite-scroll-preloader').show();
				}
				//若不是，则页码+1，继续加载新的店铺
				pageNum += 1;
				//加载结束，可以再次加载
				loading = false;
				//刷新页面，显示新架子啊的店铺
				$.refreshScroller();
			}
		});
	}
	//下滑屏幕自动分页搜索
	$(document).on('infinite', '.infinite-scroll-bottom', function() {
		if (loading)
			return;
		addItems(pageSize, pageNum);
	});
	//选择新的商品类别后，重置页码，清空商品列表，按照新条件查询
	$('#shopdetail-button-div').on(
			'click',
			'.button',
			function(e) {
				//获取商品类别Id
				productCategoryId = e.target.dataset.productSearchId;
				if (productCategoryId) {
					//原先有选择条件，则清楚原本选择的条件，改成新的条件
					if ($(e.target).hasClass('button-fill')) {
						$(e.target).removeClass('button-fill');
						productCategoryId = '';
					} else {
						$(e.target).addClass('button-fill').siblings()
								.removeClass('button-fill');
					}
					$('.list-div').empty();
					pageNum = 1;
					addItems(pageSize, pageNum);
				}
			});
	//点击商品的卡片进入详情页
	$('.list-div')
			.on(
					'click',
					'.card',
					function(e) {
						var productId = e.currentTarget.dataset.productId;
						window.location.href = '/o2o/frontend/productdetail?productId='
								+ productId;
					});
	//查询名字改变后，重置页码，清空商品列表，按照新条件查询
	$('#search').on('change', function(e) {
		productName = e.target.value;
		$('.list-div').empty();
		pageNum = 1;
		addItems(pageSize, pageNum);
	});
	//右侧菜单栏
	$('#me').click(function() {
		$.openPanel('#panel-left-demo');
	});
	$.init();
});
