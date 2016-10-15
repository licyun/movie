<%--
  User: 李呈云
  Date: 2016/10/14
  Time: 21:16
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>movie</title>
    <c:import url="common/inc.jsp"/>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">视频站</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">免费影片</a></li>
                <li><a href="#">用户影片</a></li>
                <li><a href="#">会员影片</a></li>
                <li><a href="#">点播影片</a></li>
            </ul>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="<%=path%>/img/kittens.jpg"
                     alt="通用的占位符缩略图">
                <div class="caption">
                    <h3>缩略图标签</h3>
                    <p>一些示例文本。一些示例文本。</p>
                    <p>
                        <a href="#" class="btn btn-primary" role="button">
                            按钮
                        </a>
                        <a href="#" class="btn btn-default" role="button">
                            按钮
                        </a>
                    </p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="<%=path%>/img/kittens.jpg"
                     alt="通用的占位符缩略图">
                <div class="caption">
                    <h3>缩略图标签</h3>
                    <p>一些示例文本。一些示例文本。</p>
                    <p>
                        <a href="#" class="btn btn-primary" role="button">
                            按钮
                        </a>
                        <a href="#" class="btn btn-default" role="button">
                            按钮
                        </a>
                    </p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="<%=path%>/img/kittens.jpg"
                     alt="通用的占位符缩略图">
                <div class="caption">
                    <h3>缩略图标签</h3>
                    <p>一些示例文本。一些示例文本。</p>
                    <p>
                        <a href="#" class="btn btn-primary" role="button">
                            按钮
                        </a>
                        <a href="#" class="btn btn-default" role="button">
                            按钮
                        </a>
                    </p>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="<%=path%>/img/kittens.jpg"
                     alt="通用的占位符缩略图">
                <div class="caption">
                    <h3>缩略图标签</h3>
                    <p>一些示例文本。一些示例文本。</p>
                    <p>
                        <a href="#" class="btn btn-primary" role="button">
                            按钮
                        </a>
                        <a href="#" class="btn btn-default" role="button">
                            按钮
                        </a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
