package com.blog.control;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import org.apache.tomcat.util.buf.*;

public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getRemoteUser();
        System.out.println("get " + authHeader);
        response.addHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "-1");
        request.setAttribute("username", authHeader);
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getRemoteUser();
        System.out.println("post " + authHeader);
        response.addHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "-1");
        request.setAttribute("username", authHeader);
        RequestDispatcher rd = request.getRequestDispatcher("/new_post.jsp");
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}