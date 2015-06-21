<%--
  Created by IntelliJ IDEA.
  User: menaka
  Date: 5/8/15
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="styles/bootstrap.css">
    <link rel="stylesheet" href="styles.css">
    <% String user = request.getRemoteUser(); %>
    <title>Welcome <%=user%> </title>

</head>
<body>
<div id="container">
    <div id="menu">
        <a href="index.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>

    <div>
        Hi <%=user%>! Welcome to the bLoG...!!!
    </div>

    <div id="for_approval">
        <p>There are new comments need your approval...!!!</p>
    </div>

    <br>

    <hr>

    <div id="create">
        <h3><p>Create your new blog</p></h3>
        <form id="newPost" class="form-horizontal" name="newPostForm" action="new_blog" method="get">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">Blog Title</label>
                <div class="col-sm-10">
                    <textarea rows="2" class="form-control" id="inputEmail3" cols="100" name="title" spellcheck="true"></textarea>
                    <%--<input type="text" class="form-control" id="inputEmail3" placeholder="Blog Title">--%>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Blog Content</label>
                <div class="col-sm-10">
                     <textarea rows="20" class="form-control" id="inputPassword3" cols="100" name="content" spellcheck="true"></textarea>
                    <%--<input type="password" class="form-control" id="inputPassword3" placeholder="Password">--%>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-default" value="Create Post">
                </div>
            </div>
        </form>
    </div>

</div>

</body>
</html>
