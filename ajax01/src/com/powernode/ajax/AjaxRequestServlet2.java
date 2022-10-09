package com.powernode.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajaxrequest2")
public class AjaxRequestServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usercode = request.getParameter("usercode");
        String username = request.getParameter("username");


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<font color='red'>welcome," + usercode + " " + username + "!~</font>");
    }
}
