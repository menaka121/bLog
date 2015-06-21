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
 * Created by menaka on 5/10/15.
 */
public class Approve extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("com_id");
        String filename = request.getParameter("path");
        JSONModel model = new JSONModel();
        JsonObject obj = model.readFromJSON(filename + ".json");
        JsonArray array = obj.get("comments").asArray();
        JsonObject obj2 = array.get(Integer.parseInt(id)).asObject();
        obj2.set("Authed", true);
        array.set(Integer.parseInt(id), obj2);
        obj.set("comments", array);

        new JSONModel().createJSONFile(filename, obj);
        RequestDispatcher d = request.getRequestDispatcher("/ReadJSON?id=" + filename);
        d.forward(request, response);

    }
}
