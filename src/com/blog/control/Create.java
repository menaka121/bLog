package com.blog.control;

import com.blog.model.BlogModel;
import com.blog.model.JSONModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by menaka on 5/8/15.
 */
@WebServlet(name = "Create_or_Edit")
public class Create extends HttpServlet {

    private String userName;
    private String blogTitle;
    private String blogContent;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //create a new post based on the form submitted.
        this.userName = request.getRemoteUser();
        this.blogTitle = request.getParameter("title");
        this.blogContent = request.getParameter("content");

        response.setHeader("type", "text/html");

        BlogModel post = new BlogModel(this.userName, this.blogTitle, this.blogContent);
        JSONModel json = new JSONModel(post);
        json.jsonParser();
        json.createJSONFile();

        request.setAttribute("result", "Your blog is created successfully...!!! :)");
        PrintWriter out = response.getWriter();
        out.write("<html><head><title>Congratulations</title></head><body>");
        out.write("Your Blog is created <br>");
//        request.getRequestDispatcher("/new_post.jsp").forward(request, response);
        out.write("<a href=\"new_post\">Back</a>" + " | " + "<a href=\"index.jsp\">Home</a>");

        out.write("</body></html>");
        System.out.println(json.toString());
        System.out.println("--------------------------------------\n\n\n");
        new JSONModel();
    }


}
