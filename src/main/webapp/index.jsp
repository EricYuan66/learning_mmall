<!--解决乱码-->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
    <title>DuoMall商城后端演示</title>
    <style type="text/css">
        body{
            background-image: url(background/4.png);
        }
    </style>
</head>
<body>
<h1 style="text-align: center">欢迎来到DuoMall商城后端接口演示界面！！</h1><br>


<!--用户登陆-->
<form action="/user/register.do" method="post">
    用户名&nbsp;&nbsp;<input type="text" name="username">
    密码&nbsp;&nbsp;<input type="password" name="password">
    邮箱&nbsp;&nbsp;<input type="text" name="email">
    电话&nbsp;&nbsp;<input type="text" name="phone">
    找回密码的问题&nbsp;&nbsp;<input type="text" name="question">
    答案&nbsp;&nbsp;<input type="text" name="answer">
    &nbsp;&nbsp;<input type="submit" value="注册">
</form>

<!--注册-->
<form action="/user/login.do" method="post">
    用户名&nbsp;&nbsp;<input type="text" name="username">
    密码&nbsp;&nbsp;<input type="password" name="password">
    &nbsp;&nbsp;<input type="submit" value="登录">
</form>

<!--商品展示-->
<p style="text-align: center"><a href="http://www.duomall.top/product/list.do?keyword=i&orderBy=price_asc">展示所有商品</a></p>

<!--添加商品-->
<form action="/cart/add.do" method="post">
    商品ID号&nbsp;&nbsp;<input type="text" name="productId"><br>
    添加数量&nbsp;&nbsp;<input type="text" name="count">
    &nbsp;&nbsp;<input type="submit" value="添加">
</form>

<!--购物车商品展示-->
<p style="text-align: center"><a href="http://www.duomall.top/cart/list.do">购物车内商品展示</a></p>

<!--下单-->
<p style="text-align: center"><a href="http://www.duomall.top/order/create.do?shippingId=29">提交订单</a></p>

<!--付款前订单详情-->
<form action="/order/detail.do" method="post">
    刚生成的订单号&nbsp;&nbsp;<input type="text" name="orderNo">
    &nbsp;&nbsp;<input type="submit" value="查看订单付款前的详情">
</form>

<!--支付，生成付款二维码-->
<form action="/order/pay.do" method="post">
    刚生成的订单号&nbsp;&nbsp;<input type="text" name="orderNo">
    &nbsp;&nbsp;<input type="submit" value="付款">
</form>

<!--付款后订单详情-->
<form action="/order/detail.do" method="post">
    刚生成的订单号&nbsp;&nbsp;<input type="text" name="orderNo">
    &nbsp;&nbsp;<input type="submit" value="查看订单付款后的详情">
</form>

SpringMVC上传文件
<br>
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="上传文件">
</form>

<br><br><br>
<h2 style="text-align: right">Power by Yuan Qinan</h2>
<h2 style="text-align: right">Email: yqn1992311@outlook.com</h2>
<%--富文本图片上传--%>
<%--<form name="form2" action="/manage/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">--%>
    <%--<input type="file" name="upload_file"/>--%>
    <%--<input type="submit" value="富文本图片上传">--%>
<%--</form>--%>
</body>
</html>
