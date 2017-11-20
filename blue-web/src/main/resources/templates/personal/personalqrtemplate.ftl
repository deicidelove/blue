<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>选择模板</title>
    <script src="../js/common.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
    <link rel="stylesheet" href="../css/template.css" />
</head>
<script>
   var basePath = '${request.contextPath}';
   var urlMaps = ${urlMaps};
   var openId = "${openId}"
</script>

<body>
<div class="heade">
    <div class="heade_text">选择模板</div>
    <div class="heade_back">返回</div>
</div>
<div class="big_img">
    <div class="big_img_div">
        <img src="../images/icon-132.jpg" alt="大图" />
        <div class="qr_code">
            <img src="${qrCodeUrl! }" alt="二维码"/>
        </div>
    </div>
</div>
<div class="small_img">
    <div class="small_list box">
        <div class="flex-1 small_img01 small_img_boder" type="1"></div>
        <div class="flex-1 small_img02" type="2"></div>
        <div class="flex-1 small_img03" type="3"></div>
    </div>
    <div class="small_div">
        <!-- <span class="left small_div01"></span> -->
        <span class="right small_div02" id="generateImg">确定</span>
    </div>
</div>
</body>
</html>
<script src="../../js/jquery.js"></script>
<script src="../../js/bluecommon.js"></script>
<script src="../js/template.js?v=11"></script>