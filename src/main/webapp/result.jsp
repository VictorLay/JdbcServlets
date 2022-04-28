<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8" content="text/html">
    <title>Result</title>
</head>
<body>
<% List<User> infos = (List<User>)session.getAttribute("infos"); %>
<table border="1" class="table" align="center">
    <tr bgcolor="#7fffd4">
        <td>Имя участника</td>
        <td>Телефон</td>
        <td>Оперативная память</td>
        <td>Количество ядер</td>
        <td>Память</td>
        <td>Комментарии</td>
    </tr>
        <% for(User info:infos){%>
    <tr>
        <td><%=info.getUserName()%></td>
        <td><%=info.getPhoneName()%></td>
        <td><%=info.getRAM()%></td>
        <td><%=info.getCore()%></td>
        <td><%=info.getMemory()%></td>
        <td><%=info.getWishes()%></td>
    </tr>
        <%}%>
</body>
</html>
