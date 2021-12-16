<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo"><i class="layui-icon layui-icon-release" style="font-size: 30px; color: #fff1fd;"></i>
                三宝管理系统
            </div>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <button class="layui-btn" style="width: 130px;">查看消息<span class="layui-badge layui-bg-gray">10</span></button>
                </li>
                <li class="layui-nav-item" style="width: 120px;">
                    <a href="javascript:;">
                        个人中心
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a id="userInfo">基本资料<span class="layui-badge-dot layui-bg-orange" /></a></dd>
                        <dd><a href="">安全设置<span class="layui-badge">99+</span></a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">退出</a></li>
            </ul>
        </div>
        <div class="layui-side layui-bg-black">
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree" lay-filter="test">
                    <li class="layui-nav-item">
                        <a href="<%=basePath%>hello" target="voiceList">hello</a>
                        <dl class="layui-nav-child">
                            <a href="<%=basePath%>test_ajax" target="voiceList">ajax</a>
                            <a href="<%=basePath%>test_layui" target="voiceList">layui</a>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="<%=basePath%>test_ajax" target="voiceList">ajax</a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="<%=basePath%>test_layui" target="voiceList">layui</a>
                    </li>
                </ul>
        </div>
        <div class="layui-body">
            <iframe class="layadmin-iframe ifrem_voice" src="<%=basePath%>hello" width="100%" height="700px"frameborder="0" name="voiceList" scrolling="no"></iframe>
        </div>
    </div>
</body>
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script src="<%=path%>/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/webjars/jquery/2.1.4/jquery.js"></script>
<script>
    layui.use('element', function(){
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function(elem){
            //console.log(elem)
            layer.msg(elem.text());
        });
    });
</script>
</html>