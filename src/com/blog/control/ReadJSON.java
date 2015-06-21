package com.blog.control;

import com.blog.model.JSONModel;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by menaka on 5/8/15.
 */
public class ReadJSON extends HttpServlet {

    public static String readJSON(JsonObject object) {
        return object.get("Name").asString() + "<br>" + object.get("content").asString();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("id");
        String user = request.getRemoteUser();
        response.setHeader("type", "text/html");
        JSONModel model = new JSONModel();
        JsonObject obj = model.readFromJSON(filename + ".json");
        String title = obj.get("title").toString().replaceAll("\"",""),
                content = obj.get("content").toString().replaceAll("\"", "");
        JsonArray array = obj.get("comments").asArray();
        int hits = obj.get("hits").asInt();
        obj.set("hits", hits+1);
        model.createJSONFile(filename, obj);
        String comments = "", com_ap = "";
        if (array.size() > 1) {
            for (int i = 1; i < array.size(); i++) {
                JsonObject j = array.get(i).asObject();
                if (j.get("Authed").asBoolean()) comments = comments + "<li id=\"comment\">" + readJSON(j) + "</li>";
                else {
                    com_ap = com_ap
                            + "<li id=\"comment\">" + readJSON(j)
                            + "<form method=\"get\" action=\"approve\">" +
                            "<input type=\"hidden\" name=\"path\" value=\"" + filename + "\">" +
                            "<input type=\"hidden\" name=\"com_id\" value=\"" + i + "\">" +
                            "<input type=\"submit\" value=\"Approve...\"></form>" + "</li>";
                }
            }
        }
        request.setAttribute("title", title);
        request.setAttribute("content", content);
        request.setAttribute("file", filename);
        request.setAttribute("comments", comments);
        request.setAttribute("com_ap", com_ap);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_post.jsp");
        dispatcher.forward(request, response);
        dispatcher.include(request, response);

    }
}
