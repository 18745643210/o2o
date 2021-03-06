$(function () {
    var productId = getQueryString('productId');
    //通过id获取商品的url
    var infoUrl ='/o2o/shopadmin/getproductbyid?productId='+productId;
    //获取当前店铺设定的商品类别列表
    var categoryUrl='/o2o/shopadmin/getproductcategorylist';
    //更新商品信息
    var productPostUrl='/o2o/shopadmin/modifyproduct';
    //由于商品编辑和添加使用同一个页面
    //该标识符标明是添加还是编辑
    var isEdit= false;
    if(productId){
        getInfo(productId);
        isEdit=true;
    }else {
        getCategory();
        productPostUrl ='/o2o/shopadmin/addproduct';
    }

    function getInfo(id) {
        $
            .getJSON(
                infoUrl,
                function(data) {
                    if (data.success) {
                        var product = data.product;
                        $('#product-name').val(product.productName);
                        $('#product-desc').val(product.productDesc);
                        $('#priority').val(product.priority);
                        $('#normal-price').val(product.normalPrice);
                        $('#promotion-price').val(
                            product.promotionPrice);

                        var optionHtml = '';
                        var optionArr = data.productCategoryList;
                        var optionSelected = product.productCategory.productCategoryId;
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


    //为商品添加操作提供该店铺下所有商品类别列表
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

    $('.detail-img-div').on('change', '.detail-img:last-child', function() {
        if ($('.detail-img').length < 6) {
            $('#detail-img').append('<input type="file" class="detail-img">');
        }
    });

    //提交按钮的事件响应，分别对商品添加和编辑操作做不同响应
    $('#submit').click(
        function() {
            //创建商品json对象
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

            var thumbnail = $('#small-img')[0].files[0];
            console.log(thumbnail);
            var formData = new FormData();
            formData.append('thumbnail', thumbnail);
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

})