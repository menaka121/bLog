<!Doctype html>
<%--
  Created by IntelliJ IDEA.
  User: menaka
  Date: 5/8/15
  Time: 12:38 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="styles/bootstrap.css">
    <link rel="stylesheet" href="styles.css">
    <%@ page import="com.blog.model.JSONModel" %>
    <%@ page import="java.util.LinkedList" %>
    <title>-----This is 'The bLoG'-----</title>
    <% %>
    <%
        JSONModel model = new JSONModel();

        LinkedList<String> l = model.latestTen();
        String m = request.getParameter("readAll");
        String user = request.getRemoteUser();
    %>
    <%!
        public String readMore(LinkedList<String> l) {
            String s = "<p><h5>The all blogs....</h5></p>";
            if (l.size() == 0) return "There are no blog posts to view.. :-(";
            for (int i = 0; i < l.size(); i++) {
                s = s + l.get(i);
            }
            return s + "<a  class=\"btn btn-primary btn-lg\" href=\"index.jsp\">Show less....</a>";
        }

        public String read(LinkedList<String> l) {
            String s = "<p><h5>The most recent bolog posts are</h5> </p>";
            if (l.size() == 0) return "There are no blog posts to view.. :-(";
            for (int i = 0; i < l.size() && i < 10; i++) {
                s = s + l.get(i);
            }

            return l.size() > 10 ? s + "<a  class=\"btn btn-primary btn-lg\" href=\"index.jsp?readAll=true\">See more....</a>" : s;
        }
    %>

</head>
<body>


<div id="container">

    <div id="Title">
        <p id="top">Welcome to bLoG......</p><br>
        <p id="botton">Your one and only blogging agennt....</p>
    </div>
    <div id="menu">
        <a href="/bLoG/stat.jsp">Statistics</a>
        <h3><%=user != null ? "Hi, you are Logged in as "
                + user
                + " <a href=\"/bLoG/logout\">Logout</a>"
                + "<br>"
                + "<form id=\"new post\" method=\"post\" action=\"new_post\">" +
                "<input type=\"submit\" class=\"btn btn-primary\" name=\"submit\" value=\"Create a new Post...\">" +
                "</form>"
                : "You have to be logged in to create a new blog..."
                + "<br>"
                + "<a href=\"new_post\" >Login</a>" %>
        </h3>
    </div>
    <div id="recent_posts">
        <br>
        <ul class="list-group">
            <%=m != null ? readMore(l) : read(l)%>
        </ul>
    </div>


</div>

</body>
</html>
