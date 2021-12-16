<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="<%=basePath%>">
        <title>springboot-hello1.jsp</title>
    </head>
    <body>

    </body>

    <script language="javascript" type="text/javascript">
        window.location.href="http://10.1.7.37:8089/sbrpt/decision/view/report?viewlet=ems%252Fmonthrpt%252FSB_O_N_AR_MONTH.cpt&op=view";
    </script>
</html>
