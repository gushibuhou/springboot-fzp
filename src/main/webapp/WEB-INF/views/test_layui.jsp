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
    <title>Layui HTML</title>
    <link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><i class="layui-icon layui-icon-release" style="font-size: 30px; color: #fff1fd;"></i>
            运营后台管理系统
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <!--        <ul class="layui-nav layui-layout-left">-->
        <!--            <li class="layui-nav-item"><a href="">控制台</a></li>-->
        <!--            <li class="layui-nav-item"><a href="">商品管理</a></li>-->
        <!--            <li class="layui-nav-item"><a href="">用户</a></li>-->
        <!--            <li class="layui-nav-item">-->
        <!--                <a href="javascript:;">其它系统</a>-->
        <!--                <dl class="layui-nav-child">-->
        <!--                    <dd><a href="">邮件管理</a></dd>-->
        <!--                    <dd><a href="">消息管理</a></dd>-->
        <!--                    <dd><a href="">授权管理</a></dd>-->
        <!--                </dl>-->
        <!--            </li>-->
        <!--        </ul>-->
        <ul class="layui-nav layui-layout-left" lay-filter="">
            <li class="layui-nav-item"><a href="">最新活动</a></li>
            <li class="layui-nav-item layui-this"><a href="">产品</a></li>
            <li class="layui-nav-item"><a href="">大数据</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">解决方案</a>
                <dl class="layui-nav-child"> <!-- 二级菜单 -->
                    <dd><a href="">移动模块</a></dd>
                    <dd><a href="">后台模版</a></dd>
                    <dd><a href="">电商平台</a></dd>
                </dl>
            </li>
        </ul>


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
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">所有商品</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="javascript:;">列表三</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-tab">
                <ul class="layui-tab-title">
                    <li class="layui-this">网站设置</li>
                    <li>用户管理</li>
                    <li>权限分配</li>
                    <li>商品管理</li>
                    <li>订单管理</li>
                </ul>
                <div class="layui-tab-content">
                    <div class="layui-tab-item layui-show">

                        <form class="layui-form" action="">
                            <div class="layui-form-item">
                                <label class="layui-form-label">输入框</label>
                                <div class="layui-input-block">
                                    <input type="text" name="title"  placeholder="请输入标题" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">开关</label>
                                <div class="layui-input-block">
                                    <input type="checkbox" name="switch" lay-skin="switch" value="siwc">
                                </div>
                            </div>

                            <select name="city" lay-verify="" lay-search>
                                <option value="">请选择一个城市</option>
                                <option value="010">成都</option>
                                <option value="021">重庆</option>
                                <option value="022" disabled>上海（禁用效果）</option>
                            </select>

                            <input type="checkbox" name="checkbox" title="写作"  lay-skin="primary" checked>

                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit lay-filter="submitForm">立即提交</button>
                                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                </div>
                            </div>
                        </form>

                    </div>
                    <div class="layui-tab-item">
                        进度条展示：<br/>
                        <div class="layui-progress" lay-showPercent="yes">
                            <div class="layui-progress-bar layui-bg-red" lay-percent="30%"></div>
                        </div>

                        <ul class="flow-default" id="flowTest"></ul>

                        <div class="site-demo-flow" id="flowImageTest">
                            <img lay-src="https://yqfile.alicdn.com/dbebfc5a62245eb4d7b911f73f1bb9721fa0ee1c.png">
                        </div>

                    </div>
                    <div class="layui-tab-item">

                        <div class="layui-collapse">
                            <div class="layui-colla-item">
                                <h2 class="layui-colla-title">杜甫</h2>
                                <div class="layui-colla-content layui-show">内容区域</div>
                            </div>
                            <div class="layui-colla-item">
                                <h2 class="layui-colla-title">李清照</h2>
                                <div class="layui-colla-content ">内容区域</div>
                            </div>
                            <div class="layui-colla-item">
                                <h2 class="layui-colla-title">鲁迅</h2>
                                <div class="layui-colla-content ">内容区域</div>
                            </div>
                        </div>

                    </div>
                    <div class="layui-tab-item">
                        <div class="layui-carousel" id="carouse">
                            <div carousel-item>
                                <div>条目1</div>
                                <div>条目2</div>
                                <div>条目3</div>
                                <div>条目4</div>
                                <div>条目5</div>
                            </div>
                        </div>
                        <table class="layui-table">
                            <thead>
                            <tr>
                                <th>昵称</th>
                                <th>加入时间</th>
                                <th>签名</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>贤心</td>
                                <td>2016-11-29</td>
                                <td>人生就像是一场修行</td>
                            </tr>
                            <tr>
                                <td>许闲心</td>
                                <td>2016-11-28</td>
                                <td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td>
                            </tr>
                            </tbody>
                        </table>

                    </div>
                    <div class="layui-tab-item">
                        <div id="test1">
                        </div>
                        <button type="button" class="layui-btn" id="uploadFile">
                            <i class="layui-icon">&#xe67c;</i>上传图片
                        </button>

                        <div id="treeTest"></div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
</body>
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css">
<script src="<%=path%>/layui/layui.js"></script>
<script>
    layui.use(['layer', 'form', 'element', 'laypage', 'upload', 'tree', 'carousel', 'flow'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,element = layui.element
            ,$ = layui.jquery
            ,laypage = layui.laypage
            ,upload = layui.upload
            ,tree = layui.tree
            ,carousel = layui.carousel
            ,flow = layui.flow;

        //TODO  do something
        //监听提交
        form.on('submit(submitForm)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });

        $('#userInfo').on('click', function () {
            // layer.open({
            //     type: 1,
            //     content: '传入任意的文本或html'
            // });
            //
            layer.open({
                type: 2,
                content: 'http://sentsin.com' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            });
        });



        //触发事件
        var active = {
            tabChange: function(){
                //切换到指定Tab项
                element.tabChange('demo', '22'); //切换到：用户管理
            }
        };

        var tablePage = laypage.render({
            elem: 'test1'
            ,count: 1000
            ,limit: 100
            ,limits: [100, 300, 500]
        });

        //执行实例
        var uploadInst = upload.render({
            elem: '#uploadFile' //绑定元素
            ,url: '/upload/' //上传接口
            ,done: function(res){
                layer.alert("回调完毕");
            }
            ,error: function(){
                layer.alert("回调异常");
            }
        });

        //渲染
        var treeTest = tree.render({
            elem: '#treeTest'
            ,data: [{
                title: '成都' //一级菜单
                ,children: [{
                    title: '金牛区' //二级菜单
                    ,children: [{
                        title: '环球广场' //三级菜单
                        ,children: [{
                            title: '爱达乐' //三级菜单
                            //…… //以此类推，可无限层级
                        }]
                    }]
                }]
            },{
                title: '重庆'
            }]
            ,click: function(obj){
                console.log(obj.data); //得到当前点击的节点数据
                console.log(obj.state); //得到当前节点的展开状态：open、close、normal
                console.log(obj.elem); //得到当前节点元素
                console.log(obj.data.children); //当前节点下是否有子节点
            }
            ,oncheck: function(obj){
                console.log(obj.data); //得到当前点击的节点数据
                console.log(obj.checked); //得到当前节点的展开状态：open、close、normal
                console.log(obj.elem); //得到当前节点元素
            }
        });

        carousel.render({
            elem: '#carouse'
            ,width: '100%' //设置容器宽度
            ,arrow: 'always' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });

        flow.load({
            elem: '#flowTest' //指定列表容器
            ,scrollElem: '.menu-botR'
            ,done: function(page, next) { //到达临界点（默认滚动触发），触发下一页
                //模拟数据插入
                setTimeout(function(){
                    var lis = [];
                    for(var i = 0; i < 3; i++){
                        lis.push('<li>'+ ( (page-1)*8 + i + 1 ) +'</li>')
                    }

                    //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                    //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                    next(lis.join(''), page < 4 ); //假设总页数为 10
                }, 500);
            }
            ,end:'到底了！'
        });

        //按屏加载图片
        flow.lazyimg({
            elem: '#flowImageTest img'
        });
    });
</script>
</html>
