package com.blog.control;

import com.blog.model.Comment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by menaka on 5/9/15.
 */
public class AddComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("type", "text/html");
        String name = request.getParameter("name");
        String comment = request.getParameter("comment");
        String f = request.getParameter("filePath");

        System.out.println(f + ": " + comment + " " + name);
        request.getAttribute("comment");
        Comment com = new Comment(comment, name);
        com.createComment(f);
        request.setAttribute("result", "Your comment is added successfully");
        RequestDispatcher d = request.getRequestDispatcher("/ReadJSON?id=" + f);
        d.forward(request, response);
    }


}
