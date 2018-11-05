$(function() {
	getlist();
	function getlist(e) {
		$.ajax({
			url : "/o2o/shopadmin/getshoplist",
			type : "get",
			dataType : "json",
			success : function(data) {
				if (data.success) {
					handleList(data.shopList);
					handleUser(data.user);
				}
			}
		});
	}
	// 显示用户名字
	function handleUser(data) {
		$('#user-name').text(data.name);
	}
	// 遍历列表，拼接html标签，放到html的块中
	function handleList(data) {
		var html = '';
		data.map(function(item, index) {
			html += '<div class="row row-shop"><div class="col-40">'
					+ item.shopName + '</div><div class="col-40">'
					+ shopStatus(item.enableStatus)
					+ '</div><div class="col-20">'
					+ goShop(item.enableStatus, item.shopId) + '</div></div>';

		});
		// 放入id为shop-wrap的块中
		$('.shop-wrap').html(html);
	}
	function goShop(status, id) {
		if (status != 0 && status != -1) {
			return '<a href="/o2o/shop/shopmanage?shopId=' + id + '">进入</a>';
		} else {
			return '';
		}
	}

	function shopStatus(status) {
		if (status == 0) {
			return '审核中';
		} else if (status == -1) {
			return '店铺非法';
		} else {
			return '审核通过';
		}
	}

});