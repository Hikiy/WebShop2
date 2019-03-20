$(function() {
	//获取头条列表和店铺分类信息的URL
    var url = '/o2o/frontend/listmainpageinfo';
    //获取头条列表和店铺分类信息
    $.getJSON(url, function (data) {
        if (data.success) {
            var headLineList = data.headLineList;
            var swiperHtml = '';
            //遍历头条列表并拼接标签
            headLineList.map(function (item, index) {
                swiperHtml += ''
                            + '<div class="swiper-slide img-wrap">'
                            +      '<img class="banner-img" src="'+ item.lineImg +'" alt="'+ item.lineName +'">'
                            + '</div>';
            });
            //将拼接的标签放进块中
            $('.swiper-wrapper').html(swiperHtml);
            //幻灯片的时间，单位毫秒
            $(".swiper-container").swiper({
                autoplay: 3000,
                //对幻灯片操作时，是否停止播放
                autoplayDisableOnInteraction: false
            });
            //获取店铺分类列表并拼接标签
            var shopCategoryList = data.shopCategoryList;
            var categoryHtml = '';
            shopCategoryList.map(function (item, index) {
                categoryHtml += ''
                             +  '<div class="col-50 shop-classify" data-category='+ item.shopCategoryId +'>'
                             +      '<div class="word">'
                             +          '<p class="shop-title">'+ item.shopCategoryName +'</p>'
                             +          '<p class="shop-desc">'+ item.shopCategoryDesc +'</p>'
                             +      '</div>'
                             +      '<div class="shop-classify-img-warp">'
                             +          '<img class="shop-img" src="'+ item.shopCategoryImg +'">'
                             +      '</div>'
                             +  '</div>';
            });
            $('.row').html(categoryHtml);
        }
    });
    
    //“我”的侧边栏点击事件
    $('#me').click(function () {
        $.openPanel('#panel-left-demo');
    });
    
    //店铺类别按钮点击事件
    $('.row').on('click', '.shop-classify', function (e) {
        var shopCategoryId = e.currentTarget.dataset.category;
        var newUrl = '/o2o/frontend/shoplist?parentId=' + shopCategoryId;
        window.location.href = newUrl;
    });
});
