<%--
  Created by IntelliJ IDEA.
  User: menaka/ waruna
  Date: 5/8/15
  Time: 3:05 PM
  To change this template use File | Settings | File Templates.
--%>
<!Doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="styles/bootstrap.css">
    <link rel="stylesheet" href="styles.css">
    <%@ page import="com.blog.model.JSONModel" %>
    <%
        String title = request.getAttribute("title").toString();
        String content = request.getAttribute("content").toString();
        String path = request.getAttribute("file").toString();
        String user = request.getRemoteUser();
        JSONModel jsonMod = new JSONModel();
    %>
    <title><%=title%>
    </title>
</head>
<body>
<div id="container">
    <div id="menu">
        <a href="index.jsp">Home</a>
        <%=user != null ? "<a href=\"/bLoG/logout\">Logout</a>" : "<a href=\"new_post\">Login</a>"%>
    </div>

    <div id="blog_title">
        <h3>
            <%=title%>
        </h3>
    </div>


    <div id="blog_content">
        <p>
            <%=content%>
            <%--<%=path%>--%>
        </p>

    </div>

    <div>
        <%=user != null ?

                "<form method=\"POST\" action=\"edit_post\">" +
                        "<input type=\"hidden\" name=\"file\" value=\"" + path + "\">" +
                        "<input type=\"hidden\" name=\"blog_title\" value=\"" + title + "\">" +
                        "<input type=\"hidden\" name=\"blog_content\" value=\"" + content + "\">" +
                        "<input type=\"submit\" name=\"submit\" value=\"Edit\"></form>" : ""%>
    </div>


    <div id="comments">
        <div id="authorized">
            <ul>
                <%--TODO: Display the approved comments here....--%>
                <%=request.getAttribute("comments") != "" ? request.getAttribute("comments").toString() : "Be the first one to comment..." %>
            </ul>
        </div>
        <%=user != null ?
                request.getAttribute("com_ap") != "" ? "<hr>These comments need your approval....<div id=\"toAuthor\"><ul>" + request.getAttribute("com_ap").toString() + "</ul></div><hr>" :
                        "<hr>You have no comments to approve...<hr>" : ""%>

    </div>

    <div id="commenting">
        <form id="addComment" method="get" action="comment">
            <table>
                <tr>
                    <td>Add comment...</td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><textarea name="name" rows="1" cols="50"></textarea></td>
                </tr>
                <tr>
                    <td>Comment:</td>
                    <td><textarea name="comment" rows="10" cols="50"></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <input type="hidden" name="title" value="<%=title%>">
                    <input type="hidden" name="filePath" value="<%=path%>">
                    <td><input type="submit" value="Post"></td>
                </tr>
            </table>
        </form>
    </div>
    <pre>

    </pre>
</div>
</body>
</html>
