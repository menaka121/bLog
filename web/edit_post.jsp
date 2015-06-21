<%--
  Created by IntelliJ IDEA.
  User: menaka
  Date: 5/9/15
  Time: 5:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <link rel="stylesheet" href="styles/bootstrap.css">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div id="container">
    <div id="menu" class="thr">
        <a href="index.jsp">Home</a>
        <%--<a href="/bLoG/logout">Logout</a>--%>
        <a href="/logout">Logout</a>
    </div>
    <%
    String uname = request.getRemoteUser(),
            file = request.getParameter("file"),
            title = request.getParameter("blog_title"),
            content = request.getParameter("blog_content");
    %>
<form id="edit_post" method="get" action="edit_post">
    <input type="hidden" name="file" value="<%=file%>">
    <table>
        <tr>
            <td>Title:</td>
            <td><textarea name="e_title" cols="50"><%=title%></textarea></td>
        </tr>
        <tr>
            <td>Content:</td>
            <td><textarea name="e_content" cols="50" rows="10"><%=content%></textarea></td>
        </tr>
        <tr>
            <td><input type="checkbox" name="delete" value="true">Delete post</td>
            <td><input type="submit" name="submit" value="Save changes...."></td>
        </tr>

    </table>
</form>
</div>
</body>
</html>
