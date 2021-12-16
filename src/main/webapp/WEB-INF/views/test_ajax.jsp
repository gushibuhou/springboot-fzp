<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--远程引入包--%>
<%--<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>--%>
<%--本地引入包--%>
<%--<script type="text/javascript" src="<%=path%>/js/jquery-3.4.1.min.js"></script>--%>
<%--pom引入包--%>
<script type="text/javascript" src="<%=path%>/webjars/jquery/2.1.4/jquery.js"></script>
<script src="<%=path%>/webjars/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=path%>/webjars/bootstrap/css/bootstrap.min.css" />
<style>
    table{
        border-collapse:collapse;
    }
    table,th,td{
        border: 1px solid black;
    }
    .top30{
        margin-top: 30px;
    }
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="<%=basePath%>">
        <title>springboot-test_ajax.jsp</title>
    </head>
    <body>
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#">Home</a></li>
            <li role="presentation"><a href="#">Profile</a></li>
            <li role="presentation"><a href="#">Messages</a></li>
        </ul>
        <div  align="center">
            <div>
            -----------------测试传递单个参数  start------------
            </div>
            <input type="text" name="accountIdcard" id="accountIdcard" value="1" /><label id="accountMsg"></label>
            <div>
            -----------------测试传递单个参数  end------------
            </div>
        </div>
        <div class="top30" align="center">
            <div>
            -----------------测试传递多个简单参数  start------------
            </div>
            <div>
            <input type="text" name="accountMulSimId" id="accountMulSimId" value="2" />
            <input type="text" name="accountMulSimName" id="accountMulSimName" value="aaa" />
            <input type="button" name="butMulSim" id="butMulSim" value="查询" />
            <table>
                <thead>
                    <tr>
                        <th>序号</th>
                        <th>id</th>
                        <th>name</th>
                    </tr>
                </thead>
                <tbody name="tbodyMulSim" id="tbodyMulSim">

                </tbody>
            </table>
            </div>
            <div>
            -----------------测试传递多个简单参数  end------------
            </div>
        </div>


    </body>

    <script type="text/javascript">
        $(document).ready(function () {
            alert("加载完成")
        })
        <%--传递单个参数,返回boolean  start --%>
        $(function(){
             $("#accountIdcard").blur(function() { // 失去焦点
                var accountIdcard = $("#accountIdcard").val();
                $.ajax({
                    url:"<%=basePath%>test_ajax/checkAccount",
                    type:"post",
                    data:{accountId:accountIdcard},
                    success:function (data) {
                        //接收单个参数和前端交互
                        //这里做一些你想做的。
                        //data(success中的data)就是后台响应传递来的数据，具体请看接收参数模块
                        if(data){
                            $("#accountMsg").text("用户已存在");
                        }else{
                            $("#accountMsg").text("用户不存在");
                        }
                    },
                    error:function () {
                        alert("错误！")
                    }
                })
            });
        });
        <%--传递单个参数,返回boolean   end --%>

        <%--传递多个简单参数,返回list  start--%>
        $("#butMulSim").click(function(){
            var id=$("#accountMulSimId").val();
            var name = $("#accountMulSimName").val();
            var args={accountId:id,accountName:name}
            $.ajax({
                url:"<%=basePath%>test_ajax/register",
                cache:false,
                data:args,
                type:"post",
                success:function(result){
                    $("#tbodyMulSim").html("");//清空tbody
                    $.each(result,function (index,item) {
                        //往tbody里面追加内容
                        //Number(index + 1) 转成序号
                        $("#tbodyMulSim").append("<tr><td>" + Number(index + 1) + "</td><td>" + item.id + "</td><td>" + item.name + "</td></tr>");
                    })
                }
            });
        });

        <%--传递多个简单参数,返回list  end--%>

    </script>
</html>
