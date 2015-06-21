<%@ page import="com.eclipsesource.json.JsonObject" %>
<%@ page import="com.blog.model.JSONModel" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: menaka
  Date: 5/11/15
  Time: 5:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="styles/bootstrap.css">
    <link rel="stylesheet" href="styles.css">
    <title>Site Statistics....</title>
    <%
        JSONModel m = new JSONModel();
        m.read();
        Map<String, JsonObject> map2 = m.getMap();
    %>
    <%!
      public String make(Map<String, JsonObject> map){
        String s = "";
        for(JsonObject j : map.values()){
          s = s + "<tr>" +
                  "<td>"+ j.get("title").asString() +"</td>" +
                  "<td>"+j.get("hits").asInt()+"</td>" +
                  "<td>"+j.get("comts").asInt()+"</td>" +
                  "</tr>";
        }

        return s;

      }
    %>
</head>
<body>
<div id="container">
  <div>
    <p>Statistics</p>
  </div>
  <div id="tbl" >
    <table id="table" border="1px">

        <thead>
        <tr>
            <th>Element</th>
            <th>Hits</th>
            <th>Comments</th>
        </tr>
        </thead>
        <tbody class="table table-striped">
      <%=make(map2)%>
        </tbody>
    </table>
  </div>
</div>

</body>
</html>
