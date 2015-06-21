package com.blog.control;

import com.blog.model.JSONModel;
import com.eclipsesource.json.JsonObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by menaka on 5/9/15.
 */
public class EditPost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getRemoteUser(),
                file = request.getParameter("file"),
                title = request.getParameter("blog_title"),
                content = request.getParameter("blog_content");
        RequestDispatcher dp = request.getRequestDispatcher("edit_post.jsp");
        dp.forward(request, response);
//        System.out.println("Edit post called by, "+uname +" for the blog " + title + " \n" + content + "\n on file: " + file );
//

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getRemoteUser(),
                file = request.getParameter("file"),
                title = request.getParameter("e_title"),
                content = request.getParameter("e_content"),
                delete = request.getParameter("delete");

        JSONModel model = new JSONModel();
        if (delete != null) {
            model.deleteFile(file + ".json");
            RequestDispatcher dp = request.getRequestDispatcher("/index.jsp");
            dp.forward(request, response);
        } else {
            JsonObject obj = model.readFromJSON(file + ".json");
            obj.set("title", title);
            obj.set("content", content);
            model.createJSONFile(file, obj);

            System.out.println("------------------------------");
            System.out.println(file + " " + title + " " + content);
            RequestDispatcher dp = request.getRequestDispatcher("/ReadJSON?id=" + file);
            dp.forward(request, response);
        }

    }

}
